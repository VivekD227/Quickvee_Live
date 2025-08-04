package api.endPoints;

import static io.restassured.RestAssured.given;

import api.payLoad.DashBoardAvgOrderValueViewReportPayload;
import io.restassured.response.Response;

public class DashBoardAvgOrderValueViewReportEndPoints {

	public static Response AvgOrderValueViewReport(DashBoardAvgOrderValueViewReportPayload payload) {
		
		Response res = given()
				.multiPart("merchant_id", payload.getMerchantId())
				.multiPart("date_range", payload.getDate_Range())
				.multiPart("email", payload.getEmail())
				.multiPart("token_id", payload.getTokenId())
				.multiPart("login_type", payload.getLoginType())
			.when()
				.post(Routes.urlDashBoardAvgOrderValueViewReport);
		
		return res;
	}
}
