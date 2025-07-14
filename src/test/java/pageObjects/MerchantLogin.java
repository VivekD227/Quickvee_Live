package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.basePage;

public class MerchantLogin extends basePage {

	public MerchantLogin(WebDriver driver) {
		super(driver);

	}

	By quickveeLogo = By.xpath("//img[@alt='Quickvee']");

	By storeNameField = By.xpath("//input[@id=':r0:']");

	By userNameField = By.xpath("//input[@id=':r1:']");

	By passwordField = By.xpath("//input[@id=':r2:']");

	By forgotPasswordBtn = By.xpath("//a[normalize-space()='Forgot Password ?']");

	By loginBtn = By.xpath("//button[normalize-space()='Login']");

	By passwordShowHide = By.xpath("//span[@class='show-hide-button']");

	public void clearInputField(By locator) {
		WebElement element = driver.findElement(locator);
		element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

	}

	public boolean quickveeLogoDisplay() {
		visiblityOfElement(quickveeLogo);
		return driver.findElement(quickveeLogo).isDisplayed();
	}

	public void setStoreName(String storename) {
		visiblityOfElement(storeNameField);
		clearInputField(storeNameField);
		driver.findElement(storeNameField).sendKeys(storename);
	}

	public String getStoreName() {
		return driver.findElement(storeNameField).getAttribute("value");

	}

	public void setUserName(String username) {
		visiblityOfElement(userNameField);
		clearInputField(userNameField);
		driver.findElement(userNameField).sendKeys(username);
	}

	public String getUserName() {
		return driver.findElement(userNameField).getAttribute("value");

	}

	public void setPassword(String password) {
		visiblityOfElement(passwordField);
		clearInputField(passwordField);
		driver.findElement(passwordField).sendKeys(password);
	}

	public String getPassword() {
		return driver.findElement(passwordField).getAttribute("value");

	}

	public void loginBtnClick() {
		elementClick(loginBtn);
		driver.findElement(loginBtn).click();
	}

	public boolean forgotPasswordDisplay() {
		visiblityOfElement(forgotPasswordBtn);
		return driver.findElement(forgotPasswordBtn).isDisplayed();
	}

	public void forgotPasswordClick() {
		elementClick(forgotPasswordBtn);
		driver.findElement(forgotPasswordBtn).click();
	}

	public void tooglePassword() {
		elementClick(passwordShowHide);
		driver.findElement(passwordShowHide).click();
		System.out.println("Toggle button is clicked");
	}

//	public void pageSwitch() {
//		String originalWindow = driver.getWindowHandle();
//		
//		for(String windowHandle : driver.getWindowHandles()) {
//			if(!windowHandle.equals(originalWindow)) {
//				driver.switchTo().window(windowHandle);
//				break;
//			}
//		}
	// }

}