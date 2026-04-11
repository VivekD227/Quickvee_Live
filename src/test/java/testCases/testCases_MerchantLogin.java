//validate whether the merchant is going in proper page after clicking on merchant login or not

package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import utilities.baseClass;

@Listeners(utilities.TestListener.class)

public class testCases_MerchantLogin extends baseClass {

	// @Test(priority = 1)
	public void loginValidCredential() throws InterruptedException {

		logger.info("");
		logger.info("Validate whether the quickvee logo is displayed or not");
		logger.info("-----------------------");

		merchantLogin.quickveeLogoDisplay();
		Assert.assertTrue(merchantLogin.quickveeLogoDisplay(), "Quickvee logo is not displayed");
		logger.info("User is on " + driver.getCurrentUrl() + " url");

		logger.info("");
		logger.info("Login with valid merchant credentials");
		logger.info("-----------------------");

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

		Thread.sleep(2000);
		Assert.assertTrue(dashboard.dashboard_titleDisplay(), "DashBoard Title is not displayed");
		logger.info("User is on " + driver.getCurrentUrl() + " url");

		dashboard.menuClick();
		Thread.sleep(2000);
		dashboard.logoutClick();
		Thread.sleep(2000);
		Assert.assertTrue(merchantLogin.quickveeLogoDisplay(), "User is not logout");

	}

	// @Test(priority = 2)
	public void loginValidEmployeeCredential() throws InterruptedException {

		logger.info("");
		logger.info("Login with valid employee credentials");
		logger.info("-----------------------");

		merchantLogin.setStoreName("Chain");
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName("vivek@gmail.com");
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword("Vivek@123");
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.loginBtnClick();
		logger.info("User click on login button to enter in employee login");

		Thread.sleep(2000);
		Assert.assertTrue(dashboard.dashboard_titleDisplay(), "DashBoard Title is not displayed");
		logger.info("User is on " + driver.getCurrentUrl() + " url");

		dashboard.menuClick();
		Thread.sleep(2000);
		dashboard.logoutClick();
		Thread.sleep(2000);
		Assert.assertTrue(merchantLogin.quickveeLogoDisplay(), "User is not logout");

	}

	// @Test(priority = 3)
	public void loginWithIncorrectPassword() throws InterruptedException {

		logger.info("");
		logger.info("Login with incorrect password");
		logger.info("-----------------------");

		merchantLogin.setStoreName("Chain");
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName("vivek@gmail.com");
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword("Vivek@1234");
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.loginBtnClick();
		logger.info("User click on login button to enter in employee login");

		Thread.sleep(1500);
		String errorMessage = "Incorrect Username & Password";
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Incorrect Username & Password')]"))
				.getText(), errorMessage);

	}

	// @Test(priority = 4)
	public void loginWithInvalidUsername() throws InterruptedException {

		logger.info("");
		logger.info("Login with invalid username");
		logger.info("-----------------------");

		merchantLogin.setStoreName("Chain");
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName("vivek12@gmail.com");
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword("Vivek@123");
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.loginBtnClick();
		logger.info("User click on login button to enter in employee login");

		Thread.sleep(1000);

		Thread.sleep(500);
		String errorMessage = "Incorrect Username & Password";
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Incorrect Username & Password')]"))
				.getText(), errorMessage);

		String emailError = "Invalid Username";
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Invalid Username']")).getText(),
				emailError);

	}

	// @Test(priority = 5)
	public void loginWithInvalidStoreName() throws InterruptedException {

		logger.info("");
		logger.info("Login with invalid store name");
		logger.info("-----------------------");

		merchantLogin.setStoreName("Chains");
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName("vivek@gmail.com");
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword("Vivek@123");
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.loginBtnClick();
		logger.info("User click on login button to enter in employee login");

		Thread.sleep(1000);

		Thread.sleep(500);
		String errorMessage = "Incorrect Username & Password";
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Incorrect Username & Password')]"))
				.getText(), errorMessage);

	}

	// @Test(priority = 6)
	public void loginWithEmptyFieldsEmployee() throws InterruptedException {

		logger.info("");
		logger.info("Login with empty fields");
		logger.info("-----------------------");

		merchantLogin.setStoreName("");
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName("");
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword("");
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.loginBtnClick();
		logger.info("User click on login button to enter in employee login");

		Thread.sleep(1000);

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

	}

	// @Test(priority = 7)
	public void loginWithOnlyUsernameEntered() throws InterruptedException {

		logger.info("");
		logger.info("Login with only username entered");
		logger.info("-----------------------");

		merchantLogin.setStoreName("");
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName("vivek@gmail.com");
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword("");
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.loginBtnClick();
		logger.info("User click on login button to enter in employee login");

		Thread.sleep(1000);

		String storeError = "Store Name is required";
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Store Name is required']")).getText(),
				storeError);

		String passwordError = "Password is required";
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Password is required']")).getText(),
				passwordError);

	}

	// @Test(priority = 8)
	public void loginWithOnlyPasswordEntered() throws InterruptedException {

		logger.info("");
		logger.info("Login with only password entered");
		logger.info("-----------------------");

		merchantLogin.setStoreName("");
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName("");
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword("Vivek@123");
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.loginBtnClick();
		logger.info("User click on login button to enter in employee login");

		Thread.sleep(1000);

		String storeError = "Store Name is required";
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Store Name is required']")).getText(),
				storeError);

		String emailError = "Username is required";
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Username is required']")).getText(),
				emailError);

	}

	// @Test(priority = 9)
	public void showPasswordIcon() throws InterruptedException {

		logger.info("");
		logger.info("Show password icon functionality");
		logger.info("-----------------------");

		merchantLogin.setStoreName("Chain");
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName("vivek@gmail.com");
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword("Vivek@123");
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		merchantLogin.tooglePassword();
		logger.info("User click on toggle button to show the password");

		Thread.sleep(1000);
	}

	// @Test(priority = 10)
	public void loginUsingKeyboardEnterKey() throws InterruptedException {

		logger.info("");
		logger.info("Login using keyboard enter key");
		logger.info("-----------------------");

		merchantLogin.setStoreName("Chain");
		String storeNameField = merchantLogin.getStoreName();
		logger.info("Store Name: " + storeNameField);

		merchantLogin.setUserName("vivek@gmail.com");
		String userNameField = merchantLogin.getUserName();
		logger.info("User Name: " + userNameField);

		merchantLogin.setPassword("Vivek@123");
		String passwordField = merchantLogin.getPassword();
		logger.info("Password: " + passwordField);

		driver.findElement(By.xpath("//button[normalize-space()='Login']")).sendKeys(org.openqa.selenium.Keys.ENTER);
		logger.info("User pressed ENTER key on login button to enter in employee login");

		Thread.sleep(2000);
		Assert.assertTrue(dashboard.dashboard_titleDisplay(), "DashBoard Title is not displayed");

		dashboard.menuClick();
		Thread.sleep(2000);
		dashboard.logoutClick();
		Thread.sleep(2000);
		Assert.assertTrue(merchantLogin.quickveeLogoDisplay(), "User is not logout");

	}

	@Test(priority = 11)
	public void navigateToForgotPasswordPage() throws InterruptedException {

		logger.info("");
		logger.info("Navigate to forgot password page");
		logger.info("-----------------------");

		merchantLogin.forgotPasswordClick();
		logger.info("User click on forgot password button");

		Thread.sleep(3000);
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://quickvee.com/merchants/forgot-password";
		Assert.assertEquals(actualUrl, expectedUrl, "Unexpected redirection to forgot password page");

		Assert.assertTrue(forgotPassword.otherTextDisplay(), "Forgot password page text not displayed");

		logger.info("");
		logger.info("Submit valid email on forgot password page");
		logger.info("-----------------------");

		forgotPassword.setEmailIdMerchant(p.getProperty("forgotPasswordEmail"));
		logger.info("Entered email: " + p.getProperty("merchantUserName"));

		forgotPassword.submitBtnClickMerchant();
		logger.info("User click on submit button");

		Thread.sleep(10000);

		String expectedMessage = "Please check your email for the password reset instructions.";
		String actualMessage = forgotPassword.emailMessageDisplayed();
		Assert.assertEquals(actualMessage, expectedMessage);
	}

	// @Test(priority = 12)
	// public void forgotPasswordValidEmail() throws InterruptedException {
	//
	// logger.info("");
	// logger.info("Submit valid email on forgot password page");
	// logger.info("-----------------------");
	// Thread.sleep(2000);
	// merchantLogin.forgotPasswordClick();
	// logger.info("User click on forgot password button");
	//
	// Thread.sleep(1000);
	//
	// forgotPassword.setEmailIdMerchant(p.getProperty("merchantUserName"));
	// logger.info("Entered email: " + p.getProperty("merchantUserName"));
	//
	// forgotPassword.submitBtnClickMerchant();
	// logger.info("User click on submit button");
	//
	// Thread.sleep(2000);
	//
	// String expectedMessage = "Instructions to reset your password have been sent
	// to your email";
	// String actualMessage = forgotPassword.emailMessageDisplayed();
	// Assert.assertEquals(actualMessage, expectedMessage);
	//
	// }

	//@Test(priority = 13)
	public void forgotPasswordInvalidEmail() throws InterruptedException {

		logger.info("");
		logger.info("Submit invalid email on forgot password page");
		logger.info("-----------------------");

		merchantLogin.forgotPasswordClick();
		logger.info("User click on forgot password button");

		Thread.sleep(1000);

		forgotPassword.setEmailIdMerchant("invalidmerchant@quickvee.xyz");
		logger.info("Entered invalid email");

		forgotPassword.submitBtnClickMerchant();
		logger.info("User click on submit button");

		Thread.sleep(2000);

		String expectedMessage = "Merchant Not Exist";
		String actualMessage = forgotPassword.emailMessageDisplayed();
		Assert.assertTrue(actualMessage.contains("Not Exist") || actualMessage.contains("not found"),
				"Error message not showing user doesn't exist");

	}

//	@Test(priority = 14)
	public void forgotPasswordEmptyEmail() throws InterruptedException {

		logger.info("");
		logger.info("Submit empty email on forgot password page");
		logger.info("-----------------------");

		merchantLogin.forgotPasswordClick();
		logger.info("User click on forgot password button");

		Thread.sleep(1000);

		forgotPassword.setEmailIdMerchant("");
		logger.info("Entered empty email");

		forgotPassword.submitBtnClickMerchant();
		logger.info("User click on submit button");

		Thread.sleep(1000);

		String expectedError = "Email is required";
		String actualError = forgotPassword.emailFormatMessage();
		Assert.assertEquals(actualError, expectedError);

	}

	//@Test(priority = 15)
	public void forgotPasswordEmailFormatValidation() throws InterruptedException {

		logger.info("");
		logger.info("Email format validation on forgot password page");
		logger.info("-----------------------");

		merchantLogin.forgotPasswordClick();
		logger.info("User click on forgot password button");

		Thread.sleep(1000);

		forgotPassword.setEmailIdMerchant("plainaddress");
		logger.info("Entered malformed email without @");

		forgotPassword.submitBtnClickMerchant();
		logger.info("User click on submit button");

		Thread.sleep(1000);

		// String expectedError = "Invalid Email";
		String actualError = forgotPassword.emailFormatMessage();
		Assert.assertTrue(actualError.contains("Invalid"), "Expected 'Invalid Email' error text");

	}

}