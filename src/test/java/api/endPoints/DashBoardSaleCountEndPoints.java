package api.endPoints;

import static io.restassured.RestAssured.given;

import api.payLoad.DashBoardSaleCountPayload;
import io.restassured.response.Response;

public class DashBoardSaleCountEndPoints {

	public static Response saleCountData(DashBoardSaleCountPayload payload) {
		
		Response res = given()
				.multiPart("merchant_id", payload.getMerchantId())
				.multiPart("start_date", payload.getStart_Date())
				.multiPart("end_date", payload.getEnd_Date())
				.multiPart("date_range", payload.getDate_Range())
				.multiPart("token_id", payload.getTokenID())
				.multiPart("login_type", payload.getLogin_Type())
			.when()
            .post(Routes.urlDashBoardSaleCount);
		
		return res;

	}
}
