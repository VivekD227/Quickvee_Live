package api.endPoints;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import api.payLoad.DashBoardRevenuePayload;

public class DashBoardRevenueEndPoints {
	
	public static Response revenueData(DashBoardRevenuePayload payload) {
		Response res = given()
				.multiPart("merchant_id", payload.getMerchantId())
                .multiPart("start_date", payload.getStart_date())
                .multiPart("end_date", payload.getEnd_date())
                .multiPart("date_range", payload.getToken_id())
                .multiPart("token_id", payload.getToken_id())
                .multiPart("login_type", payload.getLogin_type())
               
            .when()
                .post(Routes.urlDashBoardRevenue);
                
           
		    return res;
	}
}
