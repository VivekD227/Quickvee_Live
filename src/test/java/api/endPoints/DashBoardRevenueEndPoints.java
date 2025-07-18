package api.endPoints;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import api.payLoad.DashBoardRevenuePayload;
import api.payLoad.RevenueviewReportPayload;

public class DashBoardRevenueEndPoints {
	
	public static Response revenueData(RevenueviewReportPayload payload) {
		Response res = given()
				.multiPart("merchant_id", payload.getMerchant_id())
                .multiPart("date_range", payload.getDate_range())
                .multiPart("email", payload.getEmail())
                .multiPart("token_id", payload.getToken_id())
                .multiPart("login_type", payload.getLogin_type())
               
            .when()
                .post(Routes.urlDashBoardRevenue);
                
           
		    return res;
	}
}
