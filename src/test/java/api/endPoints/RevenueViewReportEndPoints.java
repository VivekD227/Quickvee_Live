package api.endPoints;

import api.payLoad.RevenueviewReportPayload;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
public class RevenueViewReportEndPoints {
	
	public static void dashboard_viewReport(RevenueviewReportPayload payload) {
		
		Response res = given()
							.multiport("")
	}

}
