package api.payLoad;

public class RevenueViewReportPayload {
	
	private String merchant_id;
	private String date_range;
	private String email;
	private String token_id;
	private String login_type;
	
	
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getDate_range() {
		return date_range;
	}
	public void setDate_range(String date_range) {
		this.date_range = date_range;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken_id() {
		return token_id;
	}
	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}
	public String getLogin_type() {
		return login_type;
	}
	public void setLogin_type(String login_type) {
		this.login_type = login_type;
	}
	
}
