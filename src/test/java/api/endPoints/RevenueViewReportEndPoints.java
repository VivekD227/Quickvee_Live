package api.endPoints;

import api.payLoad.RevenueViewReportPayload;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
public class RevenueViewReportEndPoints {
	
	public static Response dashboard_viewReport(RevenueViewReportPayload payload) {
		
		Response res = given()
				.multiPart("merchant_id", payload.getMerchant_id())
                .multiPart("date_range", payload.getDate_range())
                .multiPart("email", payload.getEmail())
                .multiPart("token_id", payload.getToken_id())
                .multiPart("login_type", payload.getLogin_type())
               
            .when()
                .post(Routes.urlDashBoardRevenueViewReport);
                
           
		    return res;							
	}

}
