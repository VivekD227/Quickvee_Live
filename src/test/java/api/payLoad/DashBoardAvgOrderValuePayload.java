package api.payLoad;

public class DashBoardAvgOrderValuePayload {

	private String merchant_id;
	private String start_date;
	private String end_date;
	private String date_range;
	private String token_id;
	private String login_type;

	public String getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getDate_range() {
		return date_range;
	}

	public void setDate_range(String date_range) {
		this.date_range = date_range;
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
