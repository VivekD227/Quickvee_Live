package api.testCases;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endPoints.DashBoardAvgOrderValueViewReportEndPoints;
import api.endPoints.DashBoardSaleCountViewReportEndPoints;
import api.payLoad.DashBoardAvgOrderValueViewReportPayload;
import api.payLoad.DashBoardSaleCountViewReportPayload;
import io.restassured.response.Response;

public class TestCase_DashBoardAvgOrderValueViewReport {

	public DashBoardAvgOrderValueViewReportPayload dashBoard_avgOrderViewReport;
	public Properties p;

	@BeforeClass
	public void setup() throws IOException {

		dashBoard_avgOrderViewReport = new DashBoardAvgOrderValueViewReportPayload();
		FileReader file = new FileReader("./src/test/resources/routes.properties");
		p = new Properties();
		p.load(file);

		dashBoard_avgOrderViewReport.setMerchantId(p.getProperty("merchantId"));
		dashBoard_avgOrderViewReport.setDate_Range(p.getProperty("date_range"));
		dashBoard_avgOrderViewReport.setEmail(p.getProperty("email"));
		dashBoard_avgOrderViewReport.setTokenId(p.getProperty("token_id"));
		dashBoard_avgOrderViewReport.setLoginType(p.getProperty("login_type"));

	}

	@Test(priority = 1)
	public void avgOrderValueViewReport() {

		Response response = DashBoardAvgOrderValueViewReportEndPoints.AvgOrderValueViewReport(dashBoard_avgOrderViewReport);

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
				List<Object> revenue = (List<Object>) item.get("revenue_by_day");

				System.out.println("Store Name: " + store + " Revenue: " + revenue);
				System.out.println("Revenue: "+ revenue.get(0));
				break;
			}
		}

	}
}
