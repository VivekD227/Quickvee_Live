package api.endPoints;

import static io.restassured.RestAssured.given;

import api.payLoad.DashBoardProfitGenerateViewReportPayload;
import io.restassured.response.Response;

public class DashBoardProfitGenerateViewReportEndPoints {

	public static Response ProfitGenerateEndPoints(DashBoardProfitGenerateViewReportPayload payload) {
		
		Response res = given()
				.multiPart("merchant_id", payload.getMerchant_id())
				.multiPart("date_range", payload.getDate_range())
				.multiPart("email", payload.getEmail())
				.multiPart("token_id", payload.getToken_id())
				.multiPart("login_type", payload.getLogin_type())
			.when()
				.post(Routes.urlDashBoardProfitGenerateReport);
		
		return res;
	}
		
	}

