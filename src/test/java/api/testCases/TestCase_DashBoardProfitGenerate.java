package api.testCases;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.*;

import api.endPoints.DashBoardProfitGenerateEndPoints;
import api.payLoad.DashBoardProfitGeneratePayload;
import io.restassured.response.Response;

public class TestCase_DashBoardProfitGenerate {

	public DashBoardProfitGeneratePayload profit_Generate;
	public Properties p;

	@BeforeClass
	public void setup() throws IOException {

		profit_Generate = new DashBoardProfitGeneratePayload();
		FileReader file = new FileReader("./src/test/resources/routes.properties");
		p = new Properties();
		p.load(file);

		profit_Generate.setMerchant_id(p.getProperty("merchantId"));
		profit_Generate.setStart_date(p.getProperty("start_date"));
		profit_Generate.setEnd_date(p.getProperty("end_date"));
		profit_Generate.setDate_range(p.getProperty("date_range"));
		profit_Generate.setToken_id(p.getProperty("token_id"));
		profit_Generate.setLogin_type(p.getProperty("login_type"));

	}
	
	@Test
	public void ProfitGenerateDashBoard() {
		
		Response response = DashBoardProfitGenerateEndPoints.ProfitGenerate(profit_Generate);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		boolean status = response.jsonPath().getBoolean("status");
		String total_profit = response.jsonPath().getString("total_gross_profit");
		Assert.assertTrue(status, "The status is false");
		Assert.assertEquals(total_profit, "276.1");
		
		List<Object> grossProfit = response.jsonPath().getList("gross_profit_data");
		for(int i = 0; i < grossProfit.size(); i++) {
			Map<String, Object> item = (Map<String, Object>) grossProfit.get(i);
			Object day_count = item.get("day_count");
			Object gross_profit = item.get("gross_profit");
	        System.out.println("Day: " + day_count + ", Sale Count: " + gross_profit);
		}
	}
}
