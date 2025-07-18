package api.testCases;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.*;

import api.endPoints.DashBoardRevenueEndPoints;
import api.payLoad.DashBoardRevenuePayload;
import io.restassured.response.Response;

public class TestCase_DashBoardRevenue {

	public DashBoardRevenuePayload dashBoard_Revenue;
	public Properties p;

	@BeforeClass
		public void setup() throws IOException {

			dashBoard_Revenue = new DashBoardRevenuePayload();
			FileReader file = new FileReader("./src/test/resources/routes.properties");
			p = new Properties();
			p.load(file);
			
			dashBoard_Revenue.setMerchantId(p.getProperty("merchantId"));
			dashBoard_Revenue.setStart_date(p.getProperty("start_date"));
			dashBoard_Revenue.setEnd_date(p.getProperty("end_date"));
			dashBoard_Revenue.setDate_range(p.getProperty("date_range"));
			dashBoard_Revenue.setToken_id(p.getProperty("token_id"));
			dashBoard_Revenue.setLogin_type(p.getProperty("login_type"));

}
	

	@Test(priority = 1)
	public void RevenueDashBoard() {

	    Response response = DashBoardRevenueEndPoints.revenueData(dashBoard_Revenue);

	    response.then().log().all();

	    Assert.assertEquals(response.getStatusCode(), 200);

	    boolean status = response.jsonPath().getBoolean("status");
	    String total_revenue = response.jsonPath().getString("total_revenue_data");

	    Assert.assertTrue(status, "Expected status = true");
	    System.out.println("Total Revenue: " + total_revenue);
	}

}
