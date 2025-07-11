//validate whether the merchant is going in proper page after clicking on merchant login or not

package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import utilities.baseClass;

@Listeners(utilities.TestListener.class)

public class testCases_MerchantLogin extends baseClass {

	@Test(priority = 1)
	public void validatingCorrectPage() {

		logger.info("");
		logger.info("validate whether the merchant is going in proper page after clicking on merchant login or not");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.merchantLoginClick();
		logger.info("User click on merchant login button");

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		String expectedUrl = "https://www.quickvee.com/merchants/login";

		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 2)
	public void quickveeLogoDisplay() {

		logger.info("");
		logger.info("Validate whether the quickvee logo is displayed or not");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.merchantLoginClick();
		logger.info("User click on merchant login button");

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		String expectedUrl = "https://www.quickvee.com/merchants/login";

		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

		merchantLogin.quickveeLogoDisplay();
		Assert.assertTrue(merchantLogin.quickveeLogoDisplay(), "Quickvee logo is not displayed");
		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 3)
	public void loginValidCredential() throws InterruptedException {

		logger.info("");
		logger.info("Validate logging into the Application using valid credentials");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.merchantLoginClick();
		logger.info("User click on merchant login button");

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		merchantLogin.setStoreName(p.getProperty("adminStoreName"));
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName(p.getProperty("adminUserName"));
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword(p.getProperty("adminPassword"));
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.loginBtnClick();
		logger.info("User click on login button to enter in merchant login");

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();

		String expectedUrl = "https://www.quickvee.com/merchants/users/unapprove";
		Assert.assertEquals(actualUrl, expectedUrl, "Unexpected login redirection");
		String superAdmin = "Superadmin";
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='admin_medium']")).getText(), superAdmin);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 4)
	public void loginInValidCredential() throws InterruptedException {

		logger.info("");
		logger.info(
				"Validate logging into the Application using invalid credentials (i.e. Invalid storename, Invalid email address and Invalid Password)");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.merchantLoginClick();
		logger.info("User click on merchant login button");

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		merchantLogin.setStoreName(p.getProperty("adminStoreNameInvalid"));
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName(p.getProperty("adminUserNameInvalid"));
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword(p.getProperty("adminPasswordInvalid"));
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.loginBtnClick();
		logger.info("User click on login button to enter in merchant login");

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();

		String expectedUrl = "https://www.quickvee.com/merchants/login";
		Assert.assertEquals(actualUrl, expectedUrl, "Unexpected login redirection");

		Thread.sleep(500);
		String errorMessage = "Incorrect Username & Password";
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Incorrect Username & Password')]"))
				.getText(), errorMessage);

		String emailError = "Invalid Username";
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Invalid Username']")).getText(),
				emailError);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 5)
	public void invalidEmail() throws InterruptedException {

		logger.info("");
		logger.info(
				"Verify logging into the Application using invalid email address and valid Password, valid storeName)");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.merchantLoginClick();
		logger.info("User click on merchant login button");

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		merchantLogin.setStoreName(p.getProperty("adminStoreName"));
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName(p.getProperty("adminUserNameInvalid"));
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword(p.getProperty("adminPassword"));
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.loginBtnClick();
		logger.info("User click on login button to enter in merchant login");

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();

		String expectedUrl = "https://www.quickvee.com/merchants/login";
		Assert.assertEquals(actualUrl, expectedUrl, "Unexpected login redirection");

		Thread.sleep(500);
		String errorMessage = "Incorrect Username & Password";
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Incorrect Username & Password')]"))
				.getText(), errorMessage);

		String emailError = "Invalid Username";
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Invalid Username']")).getText(),
				emailError);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 6)
	public void invalidPassword() throws InterruptedException {

		logger.info("");
		logger.info(
				"Validate logging into the Application using valid email address and invalid Password and valid Storename)");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.merchantLoginClick();
		logger.info("User click on merchant login button");

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		merchantLogin.setStoreName(p.getProperty("adminStoreName"));
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName(p.getProperty("adminUserName"));
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword(p.getProperty("adminPasswordInvalid"));
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.loginBtnClick();
		logger.info("User click on login button to enter in merchant login");

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();

		String expectedUrl = "https://www.quickvee.com/merchants/login";
		Assert.assertEquals(actualUrl, expectedUrl, "Unexpected login redirection");

		Thread.sleep(500);
		String errorMessage = "Incorrect Username & Password";
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Incorrect Username & Password')]"))
				.getText(), errorMessage);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 7)
	public void invalidStorename() throws InterruptedException {

		logger.info("");
		logger.info(
				"Validate logging into the Application using valid email address and valid Password and Invalid Storename)");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.merchantLoginClick();
		logger.info("User click on merchant login button");

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		merchantLogin.setStoreName(p.getProperty("adminStoreNameInvalid"));
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName(p.getProperty("adminUserName"));
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword(p.getProperty("adminPassword"));
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.loginBtnClick();
		logger.info("User click on login button to enter in merchant login");

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();

		String expectedUrl = "https://www.quickvee.com/merchants/login";
		Assert.assertEquals(actualUrl, expectedUrl, "Unexpected login redirection");

		Thread.sleep(500);
		String errorMessage = "Incorrect Username & Password";
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Incorrect Username & Password')]"))
				.getText(), errorMessage);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 8)
	public void invalidStorenamePassword() throws InterruptedException {

		logger.info("");
		logger.info(
				"Validate logging into the Application using valid email address and Invalid Password and Invalid Storename)");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.merchantLoginClick();
		logger.info("User click on merchant login button");

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		merchantLogin.setStoreName(p.getProperty("adminStoreNameInvalid"));
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName(p.getProperty("adminUserName"));
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword(p.getProperty("adminPasswordInvalid"));
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.loginBtnClick();
		logger.info("User click on login button to enter in merchant login");

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();

		String expectedUrl = "https://www.quickvee.com/merchants/login";
		Assert.assertEquals(actualUrl, expectedUrl, "Unexpected login redirection");

		Thread.sleep(500);
		String errorMessage = "Incorrect Username & Password";
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Incorrect Username & Password')]"))
				.getText(), errorMessage);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 9)
	public void InvalidEmailPassword() throws InterruptedException {

		logger.info("");
		logger.info(
				"Validate logging into the Application using Invalid email address and Invalid Password and valid Storename)");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.merchantLoginClick();
		logger.info("User click on merchant login button");

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		merchantLogin.setStoreName(p.getProperty("adminStoreName"));
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName(p.getProperty("adminUserNameInvalid"));
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword(p.getProperty("adminPasswordInvalid"));
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.loginBtnClick();
		logger.info("User click on login button to enter in merchant login");

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();

		String expectedUrl = "https://www.quickvee.com/merchants/login";
		Assert.assertEquals(actualUrl, expectedUrl, "Unexpected login redirection");

		Thread.sleep(500);
		String errorMessage = "Incorrect Username & Password";
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Incorrect Username & Password')]"))
				.getText(), errorMessage);

		String emailError = "Invalid Username";
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Invalid Username']")).getText(),
				emailError);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 10)
	public void invalidStoreNameEmail() throws InterruptedException {

		logger.info("");
		logger.info(
				"Validate logging into the Application using Invalid email address and valid Password and Invalid Storename)");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.merchantLoginClick();
		logger.info("User click on merchant login button");

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		merchantLogin.setStoreName(p.getProperty("adminStoreNameInvalid"));
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName(p.getProperty("adminUserNameInvalid"));
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword(p.getProperty("adminPassword"));
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.loginBtnClick();
		logger.info("User click on login button to enter in merchant login");

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();

		String expectedUrl = "https://www.quickvee.com/merchants/login";
		Assert.assertEquals(actualUrl, expectedUrl, "Unexpected login redirection");

		Thread.sleep(500);
		String errorMessage = "Incorrect Username & Password";
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Incorrect Username & Password')]"))
				.getText(), errorMessage);

		String emailError = "Invalid Username";
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Invalid Username']")).getText(),
				emailError);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 11)
	public void properErrorMessage() throws InterruptedException {

		logger.info("");
		logger.info("Validate whether the proper error message is displayed or not");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.merchantLoginClick();
		logger.info("User click on merchant login button");

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		merchantLogin.setStoreName(p.getProperty("adminStoreNameBlank"));
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName(p.getProperty("adminUserNameBlank"));
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword(p.getProperty("adminPasswordBlank"));
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.loginBtnClick();
		logger.info("User click on login button to enter in merchant login");

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();

		String expectedUrl = "https://www.quickvee.com/merchants/login";
		Assert.assertEquals(actualUrl, expectedUrl, "Unexpected login redirection");

		Thread.sleep(500);

		String storeError = "Store Name is required";
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Store Name is required']")).getText(),
				storeError);

		String emailError = "Username is required";
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Username is required']")).getText(),
				emailError);

		String passwordError = "Password is required";
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Password is required']")).getText(),
				passwordError);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 12)
	public void blankCredential() throws InterruptedException {

		logger.info("");
		logger.info("Validate logging into the Application without providing any credentials");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.merchantLoginClick();
		logger.info("User click on merchant login button");

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		merchantLogin.setStoreName(p.getProperty("adminStoreNameBlank"));
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName(p.getProperty("adminUserNameBlank"));
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword(p.getProperty("adminPasswordBlank"));
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.loginBtnClick();
		logger.info("User click on login button to enter in merchant login");

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();

		String expectedUrl = "https://www.quickvee.com/merchants/login";
		Assert.assertEquals(actualUrl, expectedUrl, "Unexpected login redirection");

		logger.info("User is on " + driver.getCurrentUrl() + " url");

		Thread.sleep(500);
	}
	
	@Test(priority = 13)
	public void passwordToggleButton() throws InterruptedException {

		logger.info("");
		logger.info("Check the password show and hide functionality");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.merchantLoginClick();
		logger.info("User click on merchant login button");

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		merchantLogin.setStoreName(p.getProperty("adminStoreName"));
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName(p.getProperty("adminUserName"));
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword(p.getProperty("adminPassword"));
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);
		
		merchantLogin.tooglePassword();
		logger.info("User click on toggle button to show the password");

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();

		String expectedUrl = "https://www.quickvee.com/merchants/login";
		Assert.assertEquals(actualUrl, expectedUrl, "Unexpected login redirection");

		logger.info("User is on " + driver.getCurrentUrl() + " url");

		Thread.sleep(500);
	}
	
	@Test(priority = 14)
	public void forgetPasswordVisible() throws InterruptedException {

		logger.info("");
		logger.info("Check whether the forgot password is visible in merchant login functionality");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.merchantLoginClick();
		logger.info("User click on merchant login button");

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		
		Assert.assertTrue(merchantLogin.forgotPasswordDisplay(), "The forgot password is not visible");

		logger.info("User is on " + driver.getCurrentUrl() + " url");

		Thread.sleep(500);
	}

	@Test(priority = 15)
	public void forgetPasswordClick() throws InterruptedException {

		logger.info("");
		logger.info("Check whether the forgot password is visible in merchant login functionality");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.merchantLoginClick();
		logger.info("User click on merchant login button");

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		
		merchantLogin.forgotPasswordClick();
		logger.info("User click on forgot password button");

		String forgetPasswordText = "Forgot Password";
		Assert.assertEquals(driver.findElement(By.xpath("//h1[normalize-space()='Forgot Password']")).getText(), forgetPasswordText);

		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://www.quickvee.com/merchants/forgot-password";
		Assert.assertEquals(actualUrl, expectedUrl, "Unexpected forgot password redirection");
		
		logger.info("User is on " + driver.getCurrentUrl() + " url");

		Thread.sleep(500);
	}
	
	@Test(priority = 16)
	public void merchantloginValidCredential() throws InterruptedException {

		logger.info("");
		logger.info("Validate logging into the Application using valid credentials");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.merchantLoginClick();
		logger.info("User click on merchant login button");

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		merchantLogin.setStoreName(p.getProperty("merchantStoreName"));
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName(p.getProperty("merchantUserName"));
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword(p.getProperty("merchantPassword"));
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.loginBtnClick();
		logger.info("User click on login button to enter in merchant login");

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();

		String expectedUrl = "https://www.quickvee.com/merchants";
		Assert.assertEquals(actualUrl, expectedUrl, "Unexpected login redirection");
		String merchantLoginText = "Chain Smoker";
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='admin_medium']")).getText(), merchantLoginText);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}
	
	@Test(priority = 17)
	public void merchantloginInValidCredential() throws InterruptedException {

		logger.info("");
		logger.info(
				"Validate logging into the Application using invalid credentials (i.e. Invalid storename, Invalid email address and Invalid Password)");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.merchantLoginClick();
		logger.info("User click on merchant login button");

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		merchantLogin.setStoreName(p.getProperty("merchantInvalidStoreName"));
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName(p.getProperty("merchantInvalidUserName"));
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword(p.getProperty("merchantInvalidPassword"));
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.loginBtnClick();
		logger.info("User click on login button to enter in merchant login");

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();

		String expectedUrl = "https://www.quickvee.com/merchants/login";
		Assert.assertEquals(actualUrl, expectedUrl, "Unexpected login redirection");

		Thread.sleep(500);
		String errorMessage = "Incorrect Username & Password";
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Incorrect Username & Password')]"))
				.getText(), errorMessage);

		String emailError = "Invalid Username";
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Invalid Username']")).getText(),
				emailError);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	
}