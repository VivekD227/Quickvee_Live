package api.testCases;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endPoints.DashBoardSaleCountViewReportEndPoints;
import api.payLoad.DashBoardSaleCountViewReportPayload;
import io.restassured.response.Response;

public class TestCase_DashBoardSaleCountViewReport {

	public DashBoardSaleCountViewReportPayload dashBoard_saleCountViewReport;
	public Properties p;

	@BeforeClass
		public void setup() throws IOException {

		dashBoard_saleCountViewReport = new DashBoardSaleCountViewReportPayload();
			FileReader file = new FileReader("./src/test/resources/routes.properties");
			p = new Properties();
			p.load(file);
			
			dashBoard_saleCountViewReport.setMerchantId(p.getProperty("merchantId"));
			dashBoard_saleCountViewReport.setDate_Range(p.getProperty("date_range"));
			dashBoard_saleCountViewReport.setEmail(p.getProperty("email"));
			dashBoard_saleCountViewReport.setTokenId(p.getProperty("token_id"));
			dashBoard_saleCountViewReport.setLoginType(p.getProperty("login_type"));

	}
	
	@Test(priority = 1)
	public void saleCountViewReportDashBoard() {

	    Response response = DashBoardSaleCountViewReportEndPoints.SaleCountViewReport(dashBoard_saleCountViewReport);

	    response.then().log().all();

	    Assert.assertEquals(response.getStatusCode(), 200);

	    boolean status = response.jsonPath().getBoolean("status");
	    
	    Assert.assertTrue(status, "Expected status = true");
	    
	    int storeCount = response.jsonPath().getList("filter_revenue_data").size();
	    boolean found = false;
	    
	    for(int i = 0; i < storeCount; i++) {
	    	
	    	String storeName = response.jsonPath().getString("filter_revenue_data ["+ i + "].store_name");
	    	if(storeName.equals("Chain Smoker")){
	    		
	    		found = true;
	    		
	    		List<Double> revenueList = response.jsonPath().getList("filter_revenue_data ["+ i + "].revenue_by_day", Double.class);
	    		Assert.assertEquals(revenueList.get(0), 14, 0.01); // margin of error
				Assert.assertEquals(revenueList.get(2), 33, 0.01);
				Assert.assertEquals(revenueList.get(4), 2, 0.01);
				Assert.assertEquals(revenueList.get(5), 2, 0.01);
				Assert.assertEquals(revenueList.get(6), 3, 0.01);
			
			break;
	    	}
	    }

	}
}
