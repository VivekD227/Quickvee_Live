package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import utilities.baseClass;

@Listeners(utilities.TestListener.class)

public class testCases_CustomerLogin extends baseClass {

	@Test(priority = 0)
	public void quickveeLogoAndTextDisplayed() {

		logger.info("");
		logger.info("Validate whether the Quickvee logo, Customer Login text and welcome text is visible or not?");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		Assert.assertTrue(customerLogin.quickveeLogoDisplay(), "Quickvee logo is not displayed");
		Assert.assertTrue(customerLogin.customerLoginTextDisplay(), "Customer Login text is not displayed");
		Assert.assertTrue(customerLogin.welcomeTextDisplay(), "Welcome Text is not displayed");

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 1)
	public void forgotPasswordTextClass() {

		logger.info("");
		logger.info(
				"Validate 'Forgotten Password' link is available in the Login page and is working and it is clickable");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		Assert.assertTrue(customerLogin.forgotPasswordDisplay(), "Forgot Password is not displayed");

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 2)
	public void forgotPasswordClickable() {
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.forgotPasswordClick();
		logger.info("User click on forgot password button");

		String expectedUrl = "https://quickvee.com/forgot-password";

		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		Assert.assertTrue(customerLogin.isInForgotPasswordPage(), "Not in Forgot Password Page");

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 3)
	public void orSignInClass() {

		logger.info("");
		logger.info("Validate whether the \"\"Or Sign in with\"\" text is visible or not");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		Assert.assertTrue(customerLogin.orSignInTextDisplay(), "Sign In text is not visible");
		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 4)
	public void validEmailPass() throws InterruptedException {

		logger.info("");
		logger.info("Validate logging into the Application using valid credentials");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.setEmail(p.getProperty("customerEmail"));
		String emailPut = customerLogin.getEmail();
		logger.info("Email: " + emailPut);

		customerLogin.setPassword(p.getProperty("customerPassword"));
		String passwordPut = customerLogin.getPassword();
		logger.info("Password: " + passwordPut);

		customerLogin.loginBtnClick();
		logger.info("User click on customer login button to login in website");

		Thread.sleep(2000);
		String originalWindow = driver.getWindowHandle();
		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		String expectedUrl = "https://quickvee.com/";
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

		homePage.loggedIn();
		logger.info("User is on " + driver.getCurrentUrl() + " url");
	}

	@Test(priority = 5)
	public void InvalidEmailPass() throws InterruptedException {

		logger.info("");
		logger.info(
				"Validate logging into the Application using invalid credentials (i.e. Invalid email address and Invalid Password)");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.setEmail(p.getProperty("customerInvalidEmail"));
		String emailPut = customerLogin.getEmail();
		logger.info("Email: " + emailPut);

		customerLogin.setPassword(p.getProperty("customerInvalidPassword"));
		String passwordPut = customerLogin.getPassword();
		logger.info("Password: " + passwordPut);

		customerLogin.loginBtnClick();
		logger.info("User click on customer login button to login in website");

		Thread.sleep(2000);

		String invalidError = "Invalid username or password";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='MuiAlert-message css-1xsto0d']")).getText(),
				invalidError);

		String expectedUrl = "https://quickvee.com/customer-login";
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 6)
	public void invalidEmailValidPass() throws InterruptedException {

		logger.info("");
		logger.info("Verify logging into the Application using invalid email address and valid Password");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.setEmail(p.getProperty("customerInvalidEmail"));
		String emailPut = customerLogin.getEmail();
		logger.info("Email: " + emailPut);

		customerLogin.setPassword(p.getProperty("customerPassword"));
		String passwordPut = customerLogin.getPassword();
		logger.info("Password: " + passwordPut);

		customerLogin.loginBtnClick();
		logger.info("User click on customer login button to login in website");

		Thread.sleep(1000);

		String invalidError = "Invalid username or password";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='MuiAlert-message css-1xsto0d']")).getText(),
				invalidError);

		String expectedUrl = "https://quickvee.com/customer-login";
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 7)
	public void validEmailInvalidPass() throws InterruptedException {

		logger.info("");
		logger.info("Validate logging into the Application using valid email address and invalid Password");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.setEmail(p.getProperty("customerEmail"));
		String emailPut = customerLogin.getEmail();
		logger.info("Email: " + emailPut);

		customerLogin.setPassword(p.getProperty("customerInvalidPassword"));
		String passwordPut = customerLogin.getPassword();
		logger.info("Password: " + passwordPut);

		customerLogin.loginBtnClick();
		logger.info("User click on customer login button to login in website");

		Thread.sleep(1000);

		String invalidError = "Invalid username or password";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='MuiAlert-message css-1xsto0d']")).getText(),
				invalidError);

		String expectedUrl = "https://quickvee.com/customer-login";
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 8)
	public void blankEmailPass() throws InterruptedException {

		logger.info("");
		logger.info("Validate logging into the Application without providing any credentials");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.setEmail(p.getProperty("customerBlankEmail"));
		String emailPut = customerLogin.getEmail();
		logger.info("Email: " + emailPut);

		customerLogin.setPassword(p.getProperty("customerBlankPassword"));
		String passwordPut = customerLogin.getPassword();
		logger.info("Password: " + passwordPut);

		customerLogin.loginBtnClick();
		logger.info("User click on customer login button to login in website");

		Thread.sleep(1000);

		String emailError = "Please enter email";
		String passError = "Please enter password";

		String expectedUrl = "https://quickvee.com/customer-login";

		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please enter email']")).getText(),
				emailError);
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please enter password']")).getText(),
				passError);
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 9)
	public void blankEmailPass2() throws InterruptedException {

		logger.info("");
		logger.info("Validate logging into the Application without providing any credentials");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.setEmail(p.getProperty("customerBlankEmail"));
		String emailPut = customerLogin.getEmail();
		logger.info("Email: " + emailPut);

		customerLogin.setPassword(p.getProperty("customerBlankPassword"));
		String passwordPut = customerLogin.getPassword();
		logger.info("Password: " + passwordPut);

		customerLogin.loginBtnClick();
		logger.info("User click on customer login button to login in website");

		Thread.sleep(1000);

		String emailError = "Please enter email";
		String passError = "Please enter password";

		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please enter email']")).getText(),
				emailError);
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please enter password']")).getText(),
				passError);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 10)
	public void putEmailBlankPass() throws InterruptedException {

		logger.info("");
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.setEmail(p.getProperty("customerEmail"));
		String emailPut = customerLogin.getEmail();
		logger.info("Email: " + emailPut);

		customerLogin.setPassword(p.getProperty("customerBlankPassword"));
		String passwordPut = customerLogin.getPassword();
		logger.info("Password: " + passwordPut);

		customerLogin.loginBtnClick();
		logger.info("User click on customer login button to login in website");

		Thread.sleep(1000);

		String passError = "Please enter password";

		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please enter password']")).getText(),
				passError);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 11)
	public void blankEmailPutPass() throws InterruptedException {

		logger.info("");
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.setEmail(p.getProperty("customerBlankEmail"));
		String emailPut = customerLogin.getEmail();
		logger.info("Email: " + emailPut);

		customerLogin.setPassword(p.getProperty("customerPassword"));
		String passwordPut = customerLogin.getPassword();
		logger.info("Password: " + passwordPut);

		customerLogin.loginBtnClick();
		logger.info("User click on customer login button to login in website");

		Thread.sleep(1000);

		String emailError = "Please enter email";

		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please enter email']")).getText(),
				emailError);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 12)
	public void googleLoginButton() throws InterruptedException {

		logger.info("");
		logger.info("Check whether the \"Log in with google\" button is working or not");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.setEmail(p.getProperty("customerEmail"));
		String emailPut = customerLogin.getEmail();
		logger.info("Email: " + emailPut);

		customerLogin.setPassword(p.getProperty("customerPassword"));
		String passwordPut = customerLogin.getPassword();
		logger.info("Password: " + passwordPut);

		customerLogin.googleLoginClick();
		logger.info("User click on google login");

		Assert.assertTrue(customerLogin.googleLoginClick(), "Button is not clicked");

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 13)
	public void checkRegisterCustomerText() throws InterruptedException {

		logger.info("");
		logger.info("Check whether \"Don't have an account?Register Now\" text is displayed or not?");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		String registerCustomerText = "Don't have an account?Register Now";
		Assert.assertEquals(driver.findElement(By.xpath(
				"//body/div[@id='root']/div[@class='main-authentication-component']/div[@class=' login-customer-form ']/form[@class='login-customer-form']/div[@class='login-customer-form']/p[1]"))
				.getText(), registerCustomerText);

	}

	@Test(priority = 14)
	public void checkMerchantLoginText() throws InterruptedException {

		logger.info("");
		logger.info("Check whether \"Are you a Merchant? Login\" text is displayed or not?");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		String merchantLoginText = "Are you a Merchant? Login";
		Assert.assertEquals(driver.findElement(By.xpath("//p[2]")).getText(), merchantLoginText);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 15)
	public void clickRegisterNow() throws InterruptedException {

		logger.info("");
		logger.info("Check whether the Register Now functionality is working as expected or not");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		System.out.println("Check whether the Register Now functionality is working as expected or not");
		customerLogin.registerCustomerClick();
		logger.info("User click on Register Now button");

		String expectedUrl = "https://quickvee.com/register";
		String customerRegisterText = "Customer Register";

		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		Assert.assertEquals(driver.findElement(By.xpath("//h1[normalize-space()='Customer Register']")).getText(),
				customerRegisterText);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 16)
	public void clickMerchantLogin() throws InterruptedException {

		logger.info("");
		logger.info("Check whether the  Login functionality is working as expected or not");
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

	// Note: This code will write after the forgot password functionality completed.

}
