package api.testCases;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endPoints.DashBoardProfitGenerateViewReportEndPoints;
import api.payLoad.DashBoardProfitGenerateViewReportPayload;
import io.restassured.response.Response;

public class TestCase_DashBoardProfitGenerateViewReport {

	public DashBoardProfitGenerateViewReportPayload dashBoard_profitGenerate;
	public Properties p;

	@BeforeClass
	public void setup() throws IOException {

		dashBoard_profitGenerate = new DashBoardProfitGenerateViewReportPayload();
		FileReader file = new FileReader("./src/test/resources/routes.properties");
		p = new Properties();
		p.load(file);

		dashBoard_profitGenerate.setMerchant_id(p.getProperty("merchantId"));
		dashBoard_profitGenerate.setDate_range(p.getProperty("date_range"));
		dashBoard_profitGenerate.setEmail(p.getProperty("email"));
		dashBoard_profitGenerate.setToken_id(p.getProperty("token_id"));
		dashBoard_profitGenerate.setLogin_type(p.getProperty("login_type"));

	}

	@Test(priority = 1)
	public void profitGenrateViewReport() {

		Response response = DashBoardProfitGenerateViewReportEndPoints
				.ProfitGenerateEndPoints(dashBoard_profitGenerate);

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

		boolean status = response.jsonPath().getBoolean("status");

		Assert.assertTrue(status, "Expected status = true");
		boolean found = false;
		
		List<Object> filterDate = response.jsonPath().getList("filter_revenue_data");
		int storeCount = filterDate.size();
		//System.out.println(storeCount);
		for (int i = 0; i < storeCount; i++) {

			Map<String, Object> item = (Map<String, Object>) filterDate.get(i);
			String store = (String) item.get("store_name");
			if (store.equalsIgnoreCase("Chain Smoker")) {
				found = true;
				List<Object> revenue = (List<Object>)item.get("revenue_by_day");

				System.out.println("Store Name: " + store + " Revenue: " + revenue);
				System.out.println("Revenue: "+ revenue.get(0));

				break;
			}
		}
	}
}
