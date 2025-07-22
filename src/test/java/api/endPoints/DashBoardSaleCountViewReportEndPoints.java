package api.endPoints;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import api.payLoad.DashBoardSaleCountViewReportPayload;

public class DashBoardSaleCountViewReportEndPoints {

	public static Response SaleCountViewReport(DashBoardSaleCountViewReportPayload payload) {
		
		Response res = given()
				.multiPart("merchant_id", payload.getMerchantId())
				.multiPart("date_range", payload.getDate_Range())
				.multiPart("email", payload.getEmail())
				.multiPart("token_id", payload.getTokenId())
				.multiPart("login_type", payload.getLoginType())
			.when()
				.post(Routes.urlDashBoardSaleCountViewReport);
		
		return res;
	}
}
