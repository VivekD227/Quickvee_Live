package api.endPoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import api.payLoad.DashBoardCustomerCountViewReportPayload;


public class DashBoardCustomerCountViewReportEndPoints {

	public static Response CustomerViewReport(DashBoardCustomerCountViewReportPayload payload) {
		
		Response res = given()
				.multiPart("merchant_id" , payload.getMerchantId())
				.multiPart("date_range" , payload.getDateRange())
				.multiPart("email" , payload.getEmail())
				.multiPart("token_id" , payload.getTokenId())
				.multiPart("login_type" , payload.getLoginType())
			.when()
				.post(Routes.urlDashBoardCustomerCountReport);
		
		return res;
	}
}
