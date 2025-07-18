//Maintaining URLs
package api.endPoints;

public class Routes {
	
	public static String base_Url = "https://api-ci.quickvee.us";
	
	//New Customer register module
	public static String urlNewCustomerRegister = base_Url+"/CustomerLoginReact/register";
	
	//Admin Login API
	public static String urlAdminLogin = base_Url+"/LoginApiReact/create_session_pk";
	
	//DashBoard Revenue
	public static String urlDashBoardRevenue = base_Url+"/ReportingReactapi/revenue_data_api";
	
	//DashBoard Revenue view report
	public static String urlDashBoardRevenueViewReport = base_Url+"/ReportingReactapi/revenue_data_outlet_report";

}
