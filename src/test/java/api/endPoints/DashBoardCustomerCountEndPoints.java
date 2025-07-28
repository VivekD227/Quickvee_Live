package api.endPoints;

import api.payLoad.DashBoardCustomerCountPayload;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class DashBoardCustomerCountEndPoints {

	public static Response customerCount(DashBoardCustomerCountPayload payload) {
			
		Response res = given()
				.multiPart("merchant_id", payload.getMerchantId())
				.multiPart("start_date", payload.getStartDate())
				.multiPart("end_date", payload.getEndDate())
				.multiPart("date_range", payload.getDateRange())
				.multiPart("token_id", payload.getTokenId())
				.multiPart("login_type", payload.getLoginType())
				
				.when()
					.post(Routes.urlDashBoardCustomerCount);
		
		return res;
	}
}
