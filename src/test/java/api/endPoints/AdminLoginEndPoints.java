package api.endPoints;

import api.payLoad.AdminLoginPayload;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class AdminLoginEndPoints {

	public static Response admin_login(AdminLoginPayload payload) {
		
		Response res = given()
						.multiPart("username", payload.getUserName())
		                .multiPart("password", payload.getPassword())
		                .multiPart("storename", payload.getStoreName())
                
		              .when()
		              	.post(Routes.urlAdminLogin);
			
			return res;
	}
}
