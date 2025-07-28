package api.endPoints;

import api.payLoad.DashBoardProfitGeneratePayload;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class DashBoardProfitGenerateEndPoints {

	public static Response ProfitGenerate(DashBoardProfitGeneratePayload payload) {
		
		Response res = given()
				.multiPart("merchant_id", payload.getMerchant_id())
				.multiPart("start_date", payload.getStart_date())
				.multiPart("end_date", payload.getEnd_date())
				.multiPart("date_range", payload.getDate_range())
				.multiPart("token_id", payload.getToken_id())
				.multiPart("login_type", payload.getLogin_type())
				.when()
					.post(Routes.urlDashBoardProfitGenerated);
		
		return res;
	}
}
