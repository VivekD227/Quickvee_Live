package api.testCases;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endPoints.DashBoardAvgOrderValueEndPoints;
import api.payLoad.DashBoardAvgOrderValuePayload;
import io.restassured.response.Response;

public class TestCase_DashBoardAvgOrderValue {

	public DashBoardAvgOrderValuePayload avg_OrderValue;
	public Properties p;

	@BeforeClass
	public void setup() throws IOException {

		avg_OrderValue = new DashBoardAvgOrderValuePayload();
		FileReader file = new FileReader("./src/test/resources/routes.properties");
		p = new Properties();
		p.load(file);

		avg_OrderValue.setMerchant_id(p.getProperty("merchantId"));
		avg_OrderValue.setStart_date(p.getProperty("start_date"));
		avg_OrderValue.setEnd_date(p.getProperty("end_date"));
		avg_OrderValue.setDate_range(p.getProperty("date_range"));
		avg_OrderValue.setToken_id(p.getProperty("token_id"));
		avg_OrderValue.setLogin_type(p.getProperty("login_type"));

	}
	
	@Test
	public void AvgOrderValueDashBoard() {
		Response response = DashBoardAvgOrderValueEndPoints.DashBoardAvgValue(avg_OrderValue);
		response.then().log().all();
		
		  Assert.assertEquals(response.getStatusCode(), 200);

		    boolean status = response.jsonPath().getBoolean("status");
		    Assert.assertTrue(status, "Expected status = true");
		    
		    List<Object> filterrevenueData = response.jsonPath().getList("filter_revenue_data");
		    
		    for(int i = 0; i < filterrevenueData.size(); i++) {
		    	Map<String, Object> item = (Map<String, Object>) filterrevenueData.get(i);
		    	String day_count = String.valueOf(item.get("day_count"));
		    	String total_revenues = String.valueOf(item.get("total_revenue"));
		    	
		        System.out.println("Day: " + day_count + ", Revenue: " + total_revenues);	    	
		    }
	}

}
