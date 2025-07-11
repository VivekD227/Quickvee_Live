package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import utilities.baseClass;

@Listeners(utilities.TestListener.class)

public class testCases_Register extends baseClass {

	@Test(priority = 1)
	public void onlyMandatoryField() throws InterruptedException {

		logger.info("");
		logger.info("Validate Registering an Account by providing only the Mandatory fields");
		logger.info("-----------------------");

		homePage.loginPageDisplay();
		logger.info("User is in Home Page");
		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setPhone(p.getProperty("newCustomerPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerPassword"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");

		String url = "https://quickvee.com";

		Assert.assertEquals(driver.getCurrentUrl(), url);
		logger.info("User is on " + driver.getCurrentUrl() + " url");
		Thread.sleep(2000);

	}

	@Test(priority = 2)
	public void validateSucessMessage() throws InterruptedException {

		logger.info("");
		logger.info(
				"Validate whether if we create a customer successfully then the name of login button is change as of name of customer");
		logger.info("-----------------------");

		homePage.loginPageDisplay();
		logger.info("User is in Home Page");
		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerPassword"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");

		homePage.loggedIn();

		Thread.sleep(2000);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 3)
	public void allFields() throws InterruptedException {

		logger.info("");
		logger.info("Validate the register field by providing all the information (LastName)");
		logger.info("-----------------------");

		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerPassword"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");

		homePage.loggedIn();
		String url = "https://quickvee.com";

		Assert.assertEquals(driver.getCurrentUrl(), url);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

		Thread.sleep(2000);

	}

	@Test(priority = 4)
	public void differentPasswordAndConfirmPassword() throws InterruptedException {

		logger.info("");
		logger.info(
				"Validate Registering an Account by entering different passwords into 'Password' and 'Password Confirm' fields");
		logger.info("-----------------------");

		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerPassword"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerInvalidConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");

		String url = "https://quickvee.com/register";
		Assert.assertEquals(driver.getCurrentUrl(), url);
		String errorMessage = "Confirm Password not matching";

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Confirm Password not matching']")).getText(),
				errorMessage);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

		Thread.sleep(2000);

	}

	@Test(priority = 5)
	public void properValidationMessage() throws InterruptedException {

		logger.info("");
		logger.info(
				"Verify proper notification messages are displayed for the mandatory fields, when you don't provide any fields in the 'Register Account' page and submit");
		logger.info("-----------------------");

		homePage.loginPageDisplay();
		logger.info("User is in Home Page");
		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerBlankFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerBlankLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerBlankPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(p.getProperty("newCustomerBlankUserName"));
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerBlankPassword"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerBlankConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");

		String firstNameErrorMessage = "Please enter First Name";
		String phoneErrorMessage = "Please enter phone number";
		String emailErrorMessage = "Please enter email";
		String passwordErrorMessage = "Please enter password";
		String confirmpasswordErrorMessage = "Please enter re-enter password";
		String CaptchaErrorMessage = "Please check captcha";

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Please enter First Name']")).getText(),
				firstNameErrorMessage);
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Please enter phone number']")).getText(),
				phoneErrorMessage);
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please enter email']")).getText(),
				emailErrorMessage);
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please enter password']")).getText(),
				passwordErrorMessage);
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Please enter re-enter password']")).getText(),
				confirmpasswordErrorMessage);
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please check captcha']")).getText(),
				CaptchaErrorMessage);

		String url = "https://quickvee.com/register";
		Assert.assertEquals(driver.getCurrentUrl(), url);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

		Thread.sleep(2000);

	}

	@Test(priority = 6)
	public void existingEmail() throws InterruptedException {

		logger.info("");
		logger.info(
				"Verify Registering an Account by providing the existing account details (i.e. existing email address)");
		logger.info("-----------------------");

		homePage.loginPageDisplay();
		logger.info("User is in Home Page");
		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(p.getProperty("newCustomerExistingUserName"));
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerPassword"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");

		String url = "https://quickvee.com/register";
		Assert.assertEquals(driver.getCurrentUrl(), url);

		String emailExistError = "Please Enter valid record";

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='MuiAlert-message css-1xsto0d']")).getText(),
				emailExistError);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

		Thread.sleep(2000);

	}

	@Test(priority = 7)
	public void invalidEmailFormat() throws InterruptedException {

		logger.info("");
		logger.info("Validate Registering an Account by providing an invalid email address into the E-Mail field");
		logger.info("-----------------------");

		homePage.loginPageDisplay();
		logger.info("User is in Home Page");
		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(p.getProperty("newCustomerInvalidUserName"));
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerPassword"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");

		String emailInvalidError = "Please enter valid email";

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Please enter valid email']")).getText(),
				emailInvalidError);

		String url = "https://quickvee.com/register";
		Assert.assertEquals(driver.getCurrentUrl(), url);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

		Thread.sleep(2000);

	}

	@Test(priority = 8)
	public void invalidPhoneNumber() throws InterruptedException {

		logger.info("");
		logger.info("Verify Registering an Account by providing an invalid phone number");
		logger.info("-----------------------");

		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerInvalidPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerPassword"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");

		String phoneErrorMessage = "Phone no not valid";

		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Phone no not valid']")).getText(),
				phoneErrorMessage);
		String url = "https://quickvee.com/register";
		Assert.assertEquals(driver.getCurrentUrl(), url);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

		Thread.sleep(2000);

	}

	@Test(priority = 9)
	public void invalidFirstName() throws InterruptedException {
		logger.info("");
		logger.info("Validate the register of account by providing Invalid first name input");
		logger.info("-----------------------");

		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerInvalidFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerPassword"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");

		String firstNameError = "Name only contain alphabet";

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Name only contain alphabet']")).getText(),
				firstNameError);
		String url = "https://quickvee.com/register";
		Assert.assertEquals(driver.getCurrentUrl(), url);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

		Thread.sleep(2000);

	}

	@Test(priority = 10)
	public void invalidLastName() throws InterruptedException {

		logger.info("");
		logger.info("Validate the register of account by providing Invalid last name input");
		logger.info("-----------------------");

		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerInvalidLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerPassword"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");

		String lastNameError = "Name only contain alphabet";

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Name only contain alphabet']")).getText(),
				lastNameError);
		String url = "https://quickvee.com/register";
		Assert.assertEquals(driver.getCurrentUrl(), url);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

		Thread.sleep(2000);
	}

	@Test(priority = 11)
	public void invalidPasswordSC() throws InterruptedException {
		logger.info("");
		logger.info(
				"Validate whether the Password fields in the Register Account page are following Password Complexity Standards");
		logger.info("-----------------------");

		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerInvalidPassword"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");

		String passwordError = "Password should contain Special Character";

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Password should contain Special Character']"))
						.getText(),
				passwordError);

		String url = "https://quickvee.com/register";
		Assert.assertEquals(driver.getCurrentUrl(), url);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

		Thread.sleep(2000);

	}

	@Test(priority = 12)
	public void invalidPasswordNumber() throws InterruptedException {

		logger.info("");
		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerInvalidPasswordNumber"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");

		String passwordError = "Password should contain number";

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Password should contain number']")).getText(),
				passwordError);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

		Thread.sleep(2000);

	}

	@Test(priority = 13)
	public void invalidPasswordLC() throws InterruptedException {

		logger.info("");
		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerInvalidPasswordLC"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");

		String passwordError = "Password should contain Lowercase letter";

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Password should contain Lowercase letter']"))
						.getText(),
				passwordError);

		Thread.sleep(2000);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 14)
	public void invalidPasswordUC() throws InterruptedException {

		logger.info("");

		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerInvalidPasswordUC"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");

		String passwordError = "Password should contain Uppercase letter";

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Password should contain Uppercase letter']"))
						.getText(),
				passwordError);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

		Thread.sleep(2000);

	}

	@Test(priority = 15)
	public void invalidPasswordLength() throws InterruptedException {

		logger.info("");
		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerInvalidPasswordUCLength"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");

		String passwordError = "Length should be greater or equal to 8";

		Assert.assertEquals(driver
				.findElement(By.xpath("//span[normalize-space()='Length should be greater or equal to 8']")).getText(),
				passwordError);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

		Thread.sleep(2000);

	}

	@Test(priority = 16)
	public void displayOtherText() throws InterruptedException {

		logger.info("");
		logger.info(
				"Validate whether the Quickvee image, Customer Register Text and Discover shopping delights! text visible or not");
		logger.info("-----------------------");

		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		String textRegister = "Customer Register";
		String customerText = "Discover shopping delights!";

		boolean image = driver.findElement(By.xpath("//img[@alt='Quickvee']")).isDisplayed();
		Assert.assertEquals(driver.findElement(By.xpath("//img[@alt='Quickvee']")).isDisplayed(), image);

		Assert.assertEquals(driver.findElement(By.xpath("//h1[normalize-space()='Customer Register']")).getText(),
				textRegister);

		Assert.assertEquals(driver
				.findElement(By.xpath("//span[text()='Discover shopping delights!' and @class='sub-heading-from']"))
				.getText(), customerText);

		logger.info("All the information is displayed if the test case is pass");

	}

	@Test(priority = 17)
	public void alreadyAccountText() throws InterruptedException {

		logger.info("");
		logger.info("Check whether the text \\\"Already have an account?Login\\\" field is displayed or not");
		logger.info("-----------------------");

		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		String alreadyAccount = "Already have an account?Login";

		Assert.assertEquals(driver.findElement(By.xpath("//form[1]//div[2]//p[1]")).getText(), alreadyAccount);
		logger.info("The text is: " + alreadyAccount);

	}

	@Test(priority = 18)

	public void merchantAccountText() throws InterruptedException {

		logger.info("");
		logger.info("Check whether the text \"Already have an account?Login\" field is displayed or not");
		logger.info("-----------------------");

		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		String merchantAccount = "Are you a Merchant?Login";

		Assert.assertEquals(driver.findElement(By.xpath("//form[1]//div[2]//p[2]")).getText(), merchantAccount);
		logger.info("The text is: " + merchantAccount);

	}

	@Test(priority = 19)
	public void customerLoginButtonCheck() throws InterruptedException {
		logger.info("");
		logger.info(
				"check whether when we click on login button of \"Already have an account?Login\" then it allows customer login or not");
		logger.info("-----------------------");

		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.customerLoginButton();
		logger.info("Customer Login button is clicked");

		String url = "https://quickvee.com/customer-login";
		String customerLoginText = "Customer Login";
		String welcomeText = "Welcome Back, Login to Shop";

		Assert.assertEquals(driver.getCurrentUrl(), url);
		Assert.assertEquals(driver.findElement(By.xpath("//h1[normalize-space()='Customer Login']")).getText(),
				customerLoginText);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='sub-heading-from']")).getText(), welcomeText);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 20)

	public void merchantLoginButtonCheck() throws InterruptedException {

		logger.info("");
		logger.info(
				"check whether when we click on login button of \\\"Are you a Merchant?\\\" then it allows customer login or not");
		logger.info("-----------------------");

		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		String originalWindow = driver.getWindowHandle();
		register.merchantLoginButton();
		logger.info("User click on merchant login button");

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		String url = "https://www.quickvee.com/merchants/login";

		Assert.assertEquals(driver.getCurrentUrl(), url, "Merchant login tab URL validation failed");
		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 21)
	public void firstNameBlank() throws InterruptedException {

		logger.info("");
		logger.info(
				"Check whether we skip one of any mandatory field and click on register then, the new customer is register or not?");
		logger.info("-----------------------");

		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerBlankFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerPassword"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");

		String url = "https://quickvee.com/register";
		String ExpectedError = "Please enter First Name";

		Assert.assertEquals(driver.getCurrentUrl(), url);
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Please enter First Name']")).getText(),
				ExpectedError);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

		Thread.sleep(2000);

	}

	@Test(priority = 22)
	public void phoneBlank() throws InterruptedException {

		logger.info("");
		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerBlankPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerPassword"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");

		String url = "https://quickvee.com/register";
		String ExpectedError = "Please enter phone number";

		Assert.assertEquals(driver.getCurrentUrl(), url);
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Please enter phone number']")).getText(),
				ExpectedError);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

		Thread.sleep(2000);

	}

	@Test(priority = 23)
	public void emailBlank() throws InterruptedException {

		logger.info("");
		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(p.getProperty("newCustomerBlankUserName"));
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerPassword"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");
		String url = "https://quickvee.com/register";
		String ExpectedError = "Please enter email";

		Assert.assertEquals(driver.getCurrentUrl(), url);
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please enter email']")).getText(),
				ExpectedError);

		Thread.sleep(2000);

	}

	@Test(priority = 24)
	public void passwordBlank() throws InterruptedException {

		logger.info("");
		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerBlankPassword"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");
		String url = "https://quickvee.com/register";
		String ExpectedError = "Please enter password";

		Assert.assertEquals(driver.getCurrentUrl(), url);
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please enter password']")).getText(),
				ExpectedError);

		Thread.sleep(2000);

	}

	@Test(priority = 25)
	public void confirmPasswordBlank() throws InterruptedException {

		logger.info("");
		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerPassword"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerBlankConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");

		String url = "https://quickvee.com/register";
		String ExpectedError = "Confirm Password not matching";

		Assert.assertEquals(driver.getCurrentUrl(), url);
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Confirm Password not matching']")).getText(),
				ExpectedError);

		Thread.sleep(2000);

	}

	@Test(priority = 26)
	public void uncheckCheckBox() throws InterruptedException {

		logger.info("");
		logger.info("Check whether we uncheck the capcta checkbox then customer can register the account of not");
		logger.info("-----------------------");

		homePage.loginPageDisplay();
		logger.info("User is in Home Page");

		homePage.notLogin();
		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.isRegisterPage();
		customerLogin.RegisterBtnClick();
		logger.info("User click on new register button");

		register.setFirstName(p.getProperty("newCustomerFirstName"));
		String firstNameField = register.getFirstName();
		logger.info("First Name: " + firstNameField);

		register.setLastName(p.getProperty("newCustomerLastName"));
		String lastNameField = register.getLastName();
		logger.info("Last Name: " + lastNameField);

		register.setPhone(p.getProperty("newCustomerPhoneNumber"));
		String phoneFiled = register.getPhone();
		logger.info("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		logger.info("Username: " + usernameField);

		register.setPassword(p.getProperty("newCustomerPassword"));
		String passwordField = register.getPassword();
		logger.info("Password: " + passwordField);

		register.setConfirmPassword(p.getProperty("newCustomerConfirmPassword"));
		String CpasswordField = register.getConfirPassword();
		logger.info("Confirm Password: " + CpasswordField);

		register.clickRegister();
		logger.info("User click on registered button");

		String firstNameError = "Please check captcha";

		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please check captcha']")).getText(),
				firstNameError);
		String url = "https://quickvee.com/register";
		Assert.assertEquals(driver.getCurrentUrl(), url);
		logger.info("User is on " + driver.getCurrentUrl() + " url");

		Thread.sleep(2000);

	}

}
