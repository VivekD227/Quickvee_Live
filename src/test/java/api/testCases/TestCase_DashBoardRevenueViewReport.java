package api.testCases;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endPoints.RevenueViewReportEndPoints;
import api.payLoad.RevenueViewReportPayload;
import io.restassured.response.Response;

public class TestCase_DashBoardRevenueViewReport {

	public RevenueViewReportPayload dashBoard_RevenueReport;
	public Properties p;

	@BeforeClass
	public void setup() throws IOException {

		dashBoard_RevenueReport = new RevenueViewReportPayload();
		FileReader file = new FileReader("./src/test/resources/routes.properties");
		p = new Properties();
		p.load(file);

		dashBoard_RevenueReport.setMerchant_id(p.getProperty("merchantId"));
		dashBoard_RevenueReport.setDate_range(p.getProperty("date_range"));
		dashBoard_RevenueReport.setEmail(p.getProperty("email"));
		dashBoard_RevenueReport.setToken_id(p.getProperty("token_id"));
		dashBoard_RevenueReport.setLogin_type(p.getProperty("login_type"));

	}

	@Test(priority = 1)
	public void RevenueReportDashBoard() {

		Response response = RevenueViewReportEndPoints.dashboard_viewReport(dashBoard_RevenueReport);

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

		boolean status = response.jsonPath().getBoolean("status");

		Assert.assertTrue(status, "Expected status = true");

		int storeCount = response.jsonPath().getList("filter_revenue_data").size();
		boolean found = false;

		for (int i = 0; i < storeCount; i++) {

			String storeName = response.jsonPath().getString("filter_revenue_data[" + i + "].store_name");
			System.out.println(storeName);
			if (storeName.equals("Chain Smoker")) {
				found = true;

				List<Double> revenueList = response.jsonPath().getList("filter_revenue_data[" + i + "].revenue_by_day",
						Double.class);
				System.out.println(revenueList);

				// Example validations (use actual expected values)
//				Assert.assertEquals(revenueList.get(1), 452.68, 0.01); // margin of error
//				Assert.assertEquals(revenueList.get(3), 1395.25, 0.01);
//				Assert.assertEquals(revenueList.get(5), 179.94, 0.01);
//				Assert.assertEquals(revenueList.get(6), 19.99, 0.01);

				break;
			}
		}

	}
}
