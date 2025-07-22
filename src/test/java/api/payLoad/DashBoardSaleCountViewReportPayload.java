package api.payLoad;

public class DashBoardSaleCountViewReportPayload {
	
	private String merchantId;
	private String date_Range;
	private String email;
	private String tokenId;
	private String loginType;
	
	
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getDate_Range() {
		return date_Range;
	}
	public void setDate_Range(String date_Range) {
		this.date_Range = date_Range;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

}
