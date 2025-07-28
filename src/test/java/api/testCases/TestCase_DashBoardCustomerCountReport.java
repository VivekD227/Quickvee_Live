package api.testCases;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.*;

import api.endPoints.DashBoardCustomerCountViewReportEndPoints;
import api.payLoad.DashBoardCustomerCountViewReportPayload;
import io.restassured.response.Response;

public class TestCase_DashBoardCustomerCountReport {

	public DashBoardCustomerCountViewReportPayload customer_countReport;
	public Properties p;

	@BeforeClass
	public void setup() throws IOException {

		customer_countReport = new DashBoardCustomerCountViewReportPayload();
		FileReader file = new FileReader("./src/test/resources/routes.properties");
		p = new Properties();
		p.load(file);

		customer_countReport.setMerchantId(p.getProperty("merchantId"));
		customer_countReport.setEmail(p.getProperty("email"));
		customer_countReport.setDateRange(p.getProperty("date_range"));
		customer_countReport.setTokenId(p.getProperty("token_id"));
		customer_countReport.setLoginType(p.getProperty("login_type"));

	}

	@Test
	public void CustomerReportDashBoard() {

		Response response = DashBoardCustomerCountViewReportEndPoints.CustomerViewReport(customer_countReport);
		response.then().log().all();
		boolean found = false;

		Assert.assertEquals(response.getStatusCode(), 200);
		boolean status = response.jsonPath().getBoolean("status");
		Assert.assertTrue(status, "The status is false");

		List<Object> filterData = response.jsonPath().getList("filter_revenue_data");
		// System.out.println(filterData);
		for (int i = 0; i < filterData.size(); i++) {
			Map<String, Object> item = (Map<String, Object>) filterData.get(i);
			
			String store = (String) item.get("store_name");
			if (store.equals("Chain Smoker")) {
				found = true;
				List<String> revenue = (List<String>) item.get("revenue_by_day");
				System.out.println("Store " + store + " Revenue " + revenue);
				break;
			}
		}

	}
}
