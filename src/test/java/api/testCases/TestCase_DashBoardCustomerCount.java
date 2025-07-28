package api.testCases;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endPoints.DashBoardCustomerCountEndPoints;
import api.payLoad.DashBoardCustomerCountPayload;
import io.restassured.response.Response;

public class TestCase_DashBoardCustomerCount {

	public DashBoardCustomerCountPayload customer_count;
	public Properties p;

	@BeforeClass
	public void setup() throws IOException {

		customer_count = new DashBoardCustomerCountPayload();
		FileReader file = new FileReader("./src/test/resources/routes.properties");
		p = new Properties();
		p.load(file);

		customer_count.setMerchantId(p.getProperty("merchantId"));
		customer_count.setStartDate(p.getProperty("start_date"));
		customer_count.setEndDate(p.getProperty("end_date"));
		customer_count.setDateRange(p.getProperty("date_range"));
		customer_count.setTokenId(p.getProperty("token_id"));
		customer_count.setLoginType(p.getProperty("login_type"));

	}

	@Test
	public void CustomerCountDashBoard() {
		
		Response response = DashBoardCustomerCountEndPoints.customerCount(customer_count);
		response.then().log().all();
				
		int totalCustomer = response.jsonPath().getInt("total_customer_count");
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(totalCustomer, 0);

		List<Object> filterCustomerCount = response.jsonPath().getList("filter_customer_count");
		//System.out.println(filterCustomerCount);
		int customerCount = filterCustomerCount.size();

		for(int i = 0; i < customerCount; i++) {
			Map<String, Object> item = (Map<String, Object>) filterCustomerCount.get(i);
			Object dayCount = item.get("day_count");
			Object custCount = item.get("total_customer");

	        System.out.println("Day: " + dayCount + ", Revenue: " + custCount);	    	

		}
	}
}