package api.testCases;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.*;

import api.endPoints.CustomerRegisterEndPonts;
import api.payLoad.newCustomerPayload;
import api.utilities.commonFuntion;
import io.restassured.response.Response;

public class validCustomerRegister {

	public newCustomerPayload newRegister;
	public Properties p;

	@BeforeClass
	public void setup() throws IOException {

		newRegister = new newCustomerPayload();
		FileReader file = new FileReader("./src/test/resources/routes.properties");
		p = new Properties();
		p.load(file);

		newRegister.setFirstname(p.getProperty("newCustomerFirstName"));
		newRegister.setLastname(p.getProperty("newCustomerLastName"));
		newRegister.setEmail(commonFuntion.newEmail());
		newRegister.setPhone(p.getProperty("newCustomerPhoneNumber"));
		newRegister.setPassword(p.getProperty("newCustomerPassword"));
		newRegister.setGuest(p.getProperty("guest"));
		newRegister.setIp_address(p.getProperty("blank"));
		newRegister.setMerchant_id(p.getProperty("blank"));
	}

	@Test
	public void customervalid() {

		Response response = CustomerRegisterEndPonts.newCustomer(newRegister);

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	    Assert.assertEquals(response.getStatusCode(), 200);

	    int status = response.jsonPath().getInt("status");
	    String message = response.jsonPath().getString("message");

	    Assert.assertEquals(status, 200, "Expected status = 200");
	    Assert.assertEquals(message, "New Record add", "Expected error message mismatch");
		
	}
}
