package api.testCases;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endPoints.DashBoardSaleCountEndPoints;
import api.payLoad.DashBoardSaleCountPayload;
import io.restassured.response.Response;

public class TestCase_DashBoardSaleCount {

	public DashBoardSaleCountPayload dashBoard_saleCount;
	public Properties p;

	@BeforeClass
		public void setup() throws IOException {

		dashBoard_saleCount = new DashBoardSaleCountPayload();
			FileReader file = new FileReader("./src/test/resources/routes.properties");
			p = new Properties();
			p.load(file);
			
			dashBoard_saleCount.setMerchantId(p.getProperty("merchantId"));
			dashBoard_saleCount.setStart_Date(p.getProperty("start_date"));
			dashBoard_saleCount.setEnd_Date(p.getProperty("end_date"));
			dashBoard_saleCount.setDate_Range(p.getProperty("date_range"));
			dashBoard_saleCount.setTokenID(p.getProperty("token_id"));
			dashBoard_saleCount.setLogin_Type(p.getProperty("login_type"));

	
	}
	
	@Test(priority = 1)
	public void saleCountDashBoard() {

	    Response response = DashBoardSaleCountEndPoints.saleCountData(dashBoard_saleCount);

	    response.then().log().all();

	    Assert.assertEquals(response.getStatusCode(), 200);

	    boolean status = response.jsonPath().getBoolean("status");
	    int total_sale = response.jsonPath().getInt("total_sale_count");
	    
	    Assert.assertTrue(status, "Expected status = true");
	    Assert.assertEquals(total_sale, 1);
	    
	    
	}
}
