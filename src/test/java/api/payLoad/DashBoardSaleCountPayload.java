package api.payLoad;

public class DashBoardSaleCountPayload {

	private String merchantId;
	private String start_Date;
	private String end_Date;
	private String date_Range;
	private String tokenID;
	private String login_Type;
	
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getStart_Date() {
		return start_Date;
	}
	public void setStart_Date(String start_Date) {
		this.start_Date = start_Date;
	}
	public String getEnd_Date() {
		return end_Date;
	}
	public void setEnd_Date(String end_Date) {
		this.end_Date = end_Date;
	}
	public String getDate_Range() {
		return date_Range;
	}
	public void setDate_Range(String date_Range) {
		this.date_Range = date_Range;
	}
	public String getTokenID() {
		return tokenID;
	}
	public void setTokenID(String tokenID) {
		this.tokenID = tokenID;
	}
	public String getLogin_Type() {
		return login_Type;
	}
	public void setLogin_Type(String login_Type) {
		this.login_Type = login_Type;
	}
	

}
