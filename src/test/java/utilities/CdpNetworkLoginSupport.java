package utilities;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

/**
 * Observes login API HTTP status via Chrome performance logs (Network events),
 * avoiding selenium-devtools-vNN / CDP version mismatches with the installed Chrome.
 * Matches either {@code Network.responseReceived} URL or correlates
 * {@code Network.requestWillBeSent} (login URL) with {@code responseReceived} (status).
 */
public final class CdpNetworkLoginSupport {

	private static final Path DEBUG_LOG = Paths.get("debug-e3b8c2.log");

	private CdpNetworkLoginSupport() {
	}

	/** Comma-separated fragments in config; all non-empty parts are OR-matched (case-sensitive). */
	public static String[] parseUrlFragments(String propertyValue) {
		if (propertyValue == null || propertyValue.isBlank()) {
			return new String[] { "create_session_pk", "LoginApiReact", "create_session" };
		}
		return Arrays.stream(propertyValue.split(",")).map(String::trim).filter(s -> !s.isEmpty())
				.toArray(String[]::new);
	}

	private static boolean matchesAnyFragment(String url, String[] fragments) {
		if (url == null || url.isEmpty()) {
			return false;
		}
		for (String f : fragments) {
			if (url.contains(f)) {
				return true;
			}
		}
		return false;
	}

	// #region agent log
	private static void agentLog(String hypothesisId, String location, String message, JSONObject data) {
		try {
			JSONObject o = new JSONObject();
			o.put("sessionId", "e3b8c2");
			o.put("hypothesisId", hypothesisId);
			o.put("location", location);
			o.put("message", message);
			o.put("timestamp", System.currentTimeMillis());
			if (data != null) {
				o.put("data", data);
			}
			Files.write(DEBUG_LOG, (o.toString() + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
					StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (Exception ignored) {
		}
	}
	// #endregion

	public static WatchHandle startWatching(ChromeDriver driver, String[] urlFragments) {
		int drained = drainPerformanceLogs(driver);
		JSONObject d = new JSONObject();
		d.put("drainedEntries", drained);
		d.put("urlFragments", new JSONArray(Arrays.asList(urlFragments)));
		agentLog("H3", "CdpNetworkLoginSupport.startWatching", "after drain", d);
		return new WatchHandle(driver, urlFragments);
	}

	private static int drainPerformanceLogs(ChromeDriver driver) {
		try {
			LogEntries entries = driver.manage().logs().get(LogType.PERFORMANCE);
			int n = 0;
			for (LogEntry ignored : entries) {
				n++;
			}
			return n;
		} catch (Exception e) {
			JSONObject err = new JSONObject();
			err.put("error", e.getClass().getSimpleName());
			err.put("errorMessage", e.getMessage());
			agentLog("H5", "CdpNetworkLoginSupport.drainPerformanceLogs", "drain failed", err);
			return -1;
		}
	}

	private static void trackRequestIfMatches(JSONObject message, String[] fragments, Set<String> trackedRequestIds) {
		if (!"Network.requestWillBeSent".equals(message.optString("method", ""))) {
			return;
		}
		JSONObject params = message.optJSONObject("params");
		if (params == null || !params.has("request")) {
			return;
		}
		String url = params.getJSONObject("request").optString("url", "");
		if (!matchesAnyFragment(url, fragments)) {
			return;
		}
		String requestId = params.optString("requestId", "");
		if (!requestId.isEmpty()) {
			trackedRequestIds.add(requestId);
		}
	}

	private static void addSampleUrl(JSONObject message, LinkedHashSet<String> sample, int max) {
		if (sample.size() >= max) {
			return;
		}
		if (!"Network.responseReceived".equals(message.optString("method", ""))) {
			return;
		}
		JSONObject params = message.optJSONObject("params");
		if (params == null || !params.has("response")) {
			return;
		}
		String url = params.getJSONObject("response").optString("url", "");
		if (!url.isEmpty()) {
			sample.add(url);
		}
	}

	/**
	 * Status when this response is the login API: URL fragment match OR tracked requestId from
	 * requestWillBeSent.
	 */
	private static Integer statusIfLoginResponse(JSONObject message, String[] fragments,
			Set<String> trackedRequestIds) {
		try {
			if (!"Network.responseReceived".equals(message.optString("method", ""))) {
				return null;
			}
			JSONObject params = message.getJSONObject("params");
			String requestId = params.optString("requestId", "");
			JSONObject response = params.optJSONObject("response");
			String url = response != null ? response.optString("url", "") : "";
			boolean matchUrl = matchesAnyFragment(url, fragments);
			boolean matchId = !requestId.isEmpty() && trackedRequestIds.contains(requestId);
			if (!matchUrl && !matchId) {
				return null;
			}
			if (response == null || !response.has("status")) {
				return null;
			}
			return response.getInt("status");
		} catch (Exception e) {
			return null;
		}
	}

	private static void trackAndExtractFromEntry(LogEntry entry, String[] fragments, Set<String> trackedRequestIds,
			LinkedHashSet<String> sampleUrls, int maxSamples) {
		try {
			JSONObject root = new JSONObject(entry.getMessage());
			if (!root.has("message")) {
				return;
			}
			JSONObject message = root.getJSONObject("message");
			trackRequestIfMatches(message, fragments, trackedRequestIds);
			addSampleUrl(message, sampleUrls, maxSamples);
		} catch (Exception ignored) {
		}
	}

	public static final class WatchHandle {
		private final ChromeDriver driver;
		private final String[] urlFragments;

		WatchHandle(ChromeDriver driver, String[] urlFragments) {
			this.driver = driver;
			this.urlFragments = urlFragments;
		}

		public int waitForMatchingResponse(Duration timeout) throws InterruptedException {
			long end = System.currentTimeMillis() + timeout.toMillis();
			int pollCount = 0;
			int networkResponsesSeen = 0;
			Set<String> trackedRequestIds = new java.util.HashSet<>();
			LinkedHashSet<String> sampleUrls = new LinkedHashSet<>();

			while (System.currentTimeMillis() < end) {
				pollCount++;
				LogEntries entries;
				try {
					entries = driver.manage().logs().get(LogType.PERFORMANCE);
				} catch (Exception e) {
					Thread.sleep(100);
					continue;
				}
				for (LogEntry entry : entries) {
					trackAndExtractFromEntry(entry, urlFragments, trackedRequestIds, sampleUrls, 80);
					try {
						JSONObject root = new JSONObject(entry.getMessage());
						if (root.has("message")) {
							String method = root.getJSONObject("message").optString("method", "");
							if ("Network.responseReceived".equals(method)) {
								networkResponsesSeen++;
							}
						}
					} catch (Exception ignored) {
					}
					try {
						JSONObject root = new JSONObject(entry.getMessage());
						if (root.has("message")) {
							Integer st = statusIfLoginResponse(root.getJSONObject("message"), urlFragments,
									trackedRequestIds);
							if (st != null) {
								JSONObject d = new JSONObject();
								d.put("httpStatus", st);
								d.put("pollCount", pollCount);
								d.put("trackedRequestIdsSize", trackedRequestIds.size());
								d.put("runId", "post-fix-v2");
								agentLog("H_vf", "WatchHandle.waitForMatchingResponse", "matched response", d);
								return st.intValue();
							}
						}
					} catch (Exception ignored) {
					}
				}
				Thread.sleep(100);
			}
			JSONObject d = new JSONObject();
			d.put("pollCount", pollCount);
			d.put("lastNetworkResponseEventsAccum", networkResponsesSeen);
			d.put("trackedRequestIdsSize", trackedRequestIds.size());
			d.put("sampleResponseUrls", new JSONArray(sampleUrls));
			d.put("runId", "post-fix-v2");
			agentLog("H_vf", "WatchHandle.waitForMatchingResponse", "timeout no match", d);
			return -1;
		}

		public void stop() {
			drainPerformanceLogs(driver);
		}

		public String describeFragments() {
			return String.join(",", urlFragments);
		}
	}
}
