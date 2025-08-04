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

	//DashBoard Total Transaction
	public static String urlDashBoardSaleCount = base_Url+"/NewDashboardReact/sales_count_api";

	//DashBoard Total Transaction view Report
	public static String urlDashBoardSaleCountViewReport = base_Url+"/NewDashboardReact/store_sales_count_api";
	
	//DashBoard Unique Customer
	public static String urlDashBoardCustomerCount = base_Url+"/ReportingReactapi/customer_count_api";

	//DashBoard Unique Customer view report
	public static String urlDashBoardCustomerCountReport = base_Url+"/ReportingReactapi/customer_count_store_report";
	
	//DashBoard profit generated
	public static String urlDashBoardProfitGenerated = base_Url+"/NewDashboardReact/gross_profit_api";

	//DashBoard profit generated view report
	public static String urlDashBoardProfitGenerateReport = base_Url+"/NewDashboardReact/store_gross_profit_api";

	//DashBoard average order value report
	public static String urlDashBoardAvgOrderValue = base_Url+"/DashboardReactApi/avg_sale_value";

	//DashBoard average order value view report
	public static String urlDashBoardAvgOrderValueViewReport = base_Url+"/DashboardReactApi/avg_sale_value_report";

}
