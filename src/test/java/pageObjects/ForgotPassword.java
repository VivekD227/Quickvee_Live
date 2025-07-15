package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.basePage;

public class ForgotPassword extends basePage {

	public ForgotPassword(WebDriver driver) {
		super(driver);
	}

	By quickveeLogo = By.xpath("//img[@alt='Quickvee']");

	By forgotPassword_Text = By.xpath("//h1[normalize-space()='Forgot Password']");

	By otherText = By.xpath("//span[@class='sub-heading-from']");

	By emailField = By.xpath("//input[@id='outlined-size-small']");

	By submit_Btn = By.xpath("//button[normalize-space()='Submit']");
	
	By emailSentMessage = By.xpath("//div[@class='MuiAlert-message css-1xsto0d']");
	
	By errorEmailMessage = By.xpath("//span[@class='input-error']");
	
	By emailFieldMerchant = By.xpath("//input[@id=':r3:']");
			
	By submit_Btn_Merchant = By.xpath("//button[normalize-space()='submit']");
	
	public boolean quickveeLogoDisplay() {
		visiblityOfElement(quickveeLogo);
		return driver.findElement(quickveeLogo).isDisplayed();
	}

	public boolean otherTextDisplay() {
		visiblityOfElement(forgotPassword_Text);
		return driver.findElement(forgotPassword_Text).isDisplayed();
	}

	public void setEmailId(String email) {
		visiblityOfElement(emailField);
		driver.findElement(emailField).sendKeys(email);
	}
	
	public void setEmailIdMerchant(String email) {
		visiblityOfElement(emailFieldMerchant);
		driver.findElement(emailFieldMerchant).sendKeys(email);
	}
	public String getEmailId() {
		return driver.findElement(emailField).getAttribute("value");

	}
	
	public void submitBtnClick() {
		elementClick(submit_Btn);
		driver.findElement(submit_Btn).click();
	}
	
	public void submitBtnClickMerchant() {
		elementClick(submit_Btn_Merchant);
		driver.findElement(submit_Btn_Merchant).click();
	}
	
	public String emailMessageDisplayed() {
		visiblityOfElement(emailSentMessage);
		return driver.findElement(emailSentMessage).getText();

	}
	
	public String emailFormatMessage() {
		visiblityOfElement(errorEmailMessage);
		return driver.findElement(errorEmailMessage).getText();

	}
}
