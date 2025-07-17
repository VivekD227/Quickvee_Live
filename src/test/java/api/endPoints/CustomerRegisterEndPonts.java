package api.endPoints;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import api.payLoad.newCustomerPayload;

public class CustomerRegisterEndPonts {

	 public static Response newCustomer(newCustomerPayload payload) {

	        Response response = given()
	                .multiPart("firstname", payload.getFirstname())
	                .multiPart("lastname", payload.getLastname())
	                .multiPart("email", payload.getEmail())
	                .multiPart("phone", payload.getPhone())
	                .multiPart("password", payload.getPassword())
	                .multiPart("guest", payload.getGuest())
	                .multiPart("ip_address", payload.getIp_address())
	                .multiPart("merchant_id", payload.getMerchant_id())
	            .when()
	                .post(Routes.urlNewCustomerRegister);

	        return response;
	        
	 }
}