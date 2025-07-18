package api.testCases;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.*;

import api.endPoints.AdminLoginEndPoints;
import api.payLoad.AdminLoginPayload;
import io.restassured.response.Response;

public class TestCase_AdminLogin {
	public AdminLoginPayload admin_login;
	public Properties p;
	
	@BeforeClass
	public void setUp() throws IOException {
		
		admin_login = new AdminLoginPayload();
		
		FileReader file = new FileReader("./src/test/resources/routes.properties");
		p = new Properties();
		p.load(file);
		
		admin_login.setStoreName(p.getProperty("storename"));
		admin_login.setUserName(p.getProperty("username"));
		admin_login.setPassword(p.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void LoginAdmin() throws IOException {

	    Response response = AdminLoginEndPoints.admin_login(admin_login);

	    response.then().log().all();

	    Assert.assertEquals(response.getStatusCode(), 200);

	    boolean status = response.jsonPath().getBoolean("status");
	    String loginType = response.jsonPath().getString("login_type");
	    String tokenId = response.jsonPath().getString("token_id");

	    
	    Assert.assertTrue(status, "Expected status = true");
	    Assert.assertEquals(loginType, "superadmin");
	    System.out.println("Token Id: " + tokenId);
	    
	    p.setProperty("token_id", tokenId);
		FileWriter writer = new FileWriter("./src/test/resources/routes.properties");
		p.store(writer, "Updated token_id");
		writer.close();
	    
	}
}
