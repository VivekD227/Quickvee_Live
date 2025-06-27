package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.CustomerLogin;
import pageObjects.HomePage;
import pageObjects.RegisterPage;

@Listeners(utilities.TestListener.class)

public class testCases_Register {

	WebDriver driver;
	HomePage loginpage;
	CustomerLogin homepage;
	RegisterPage register;

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://quickvee.com");
		loginpage = new HomePage(driver);
		homepage = new CustomerLogin(driver);
		register = new RegisterPage(driver);

	}

	@Test(priority = 1)
	public void onlyMandatoryField() throws InterruptedException {

		loginpage.loginPageDisplay();
		loginpage.notLogin();
		loginpage.loginBtn();
		System.out.println("");

		homepage.isRegisterPage();
		homepage.RegisterBtnClick();
		System.out.println("");

		System.out.println("Validate Registering an Account by providing only the Mandatory fields");
		System.out.println("-----------------------");
		register.setFirstName("Vivek");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setPhone("8928185554");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("Vivek@123");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("Vivek@123");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

		String url = "https://quickvee.com";

		Assert.assertEquals(driver.getCurrentUrl(), url);

		Thread.sleep(2000);

	}

	@Test(priority = 2)
	public void validateSucessMessage() throws InterruptedException {
		loginpage.loginPageDisplay();
		loginpage.notLogin();
		loginpage.loginBtn();
		System.out.println("");

		homepage.isRegisterPage();
		homepage.RegisterBtnClick();
		System.out.println("");

		System.out.println(
				"Validate whether if we create a customer successfully then the name of login button is change as of name of customer");
		System.out.println("-----------------------");
		register.setFirstName("Vivek");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("Dubey");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("8928185554");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("Vivek@123");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("Vivek@123");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

		loginpage.loggedIn();

		Thread.sleep(2000);

	}

	@Test(priority = 3)
	public void allFields() throws InterruptedException {
		loginpage.loginPageDisplay();
		loginpage.notLogin();
		loginpage.loginBtn();
		System.out.println("");

		homepage.isRegisterPage();
		homepage.RegisterBtnClick();
		System.out.println("");

		System.out.println("Validate the register field by providing all the information (LastName)");

		register.setFirstName("Vivek");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("Dubey");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("8928185554");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("Vivek@123");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("Vivek@123");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

		loginpage.loggedIn();
		String url = "https://quickvee.com";

		Assert.assertEquals(driver.getCurrentUrl(), url);

		Thread.sleep(2000);

	}

	@Test(priority = 4)
	public void differentPasswordAndConfirmPassword() throws InterruptedException {
		System.out.println(
				"Validate Registering an Account by entering different passwords into 'Password' and 'Password Confirm' fields");
		loginpage.loginPageDisplay();
		loginpage.notLogin();
		loginpage.loginBtn();
		System.out.println("");

		homepage.isRegisterPage();
		homepage.RegisterBtnClick();
		System.out.println("");

		register.setFirstName("Vivek");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("Dubey");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("8928185554");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("Vivek@123");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("Vivek123");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();
		String url = "https://quickvee.com/register";
		Assert.assertEquals(driver.getCurrentUrl(), url);
		String errorMessage = "Confirm Password not matching";

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Confirm Password not matching']")).getText(),
				errorMessage);

		Thread.sleep(2000);

	}

	@Test(priority = 5)
	public void properValidationMessage() throws InterruptedException {
		System.out.println(
				"Verify proper notification messages are displayed for the mandatory fields, when you don't provide any fields in the 'Register Account' page and submit");
		loginpage.loginPageDisplay();
		loginpage.notLogin();
		loginpage.loginBtn();
		System.out.println("");

		homepage.isRegisterPage();
		homepage.RegisterBtnClick();
		System.out.println("");

		register.setFirstName("");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername("");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

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
		Thread.sleep(2000);

	}

	@Test(priority = 6)
	public void existingEmail() throws InterruptedException {
		System.out.println(
				"Verify Registering an Account by providing the existing account details (i.e. existing email address)");
		loginpage.loginPageDisplay();
		loginpage.notLogin();
		loginpage.loginBtn();
		System.out.println("");

		homepage.isRegisterPage();
		homepage.RegisterBtnClick();
		System.out.println("");

		register.setFirstName("Vivek");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("Dubey");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("9876542313");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername("vivek.dubey521@gmail.com");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("Vivek@123");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("Vivek@123");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

		String url = "https://quickvee.com/register";
		Assert.assertEquals(driver.getCurrentUrl(), url);

		String emailExistError = "Please Enter valid record";

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='MuiAlert-message css-1xsto0d']")).getText(),
				emailExistError);

		Thread.sleep(2000);

	}

	@Test(priority = 7)
	public void invalidEmailFormat() throws InterruptedException {
		System.out
				.println("Validate Registering an Account by providing an invalid email address into the E-Mail field");
		loginpage.loginPageDisplay();
		loginpage.notLogin();
		loginpage.loginBtn();
		System.out.println("");

		homepage.isRegisterPage();
		homepage.RegisterBtnClick();
		System.out.println("");

		register.setFirstName("Vivek");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("Dubey");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("9876542313");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername("vivek.dubey521gmail.com");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("Vivek@123");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("Vivek@123");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

		String emailInvalidError = "Please enter valid email";

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Please enter valid email']")).getText(),
				emailInvalidError);

		String url = "https://quickvee.com/register";
		Assert.assertEquals(driver.getCurrentUrl(), url);

		Thread.sleep(2000);

	}

	@Test(priority = 8)
	public void invalidPhoneNumber() throws InterruptedException {
		System.out.println("Verify Registering an Account by providing an invalid phone number");
		loginpage.loginPageDisplay();
		loginpage.notLogin();
		loginpage.loginBtn();
		System.out.println("");

		homepage.isRegisterPage();
		homepage.RegisterBtnClick();
		System.out.println("");

		register.setFirstName("Vivek");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("Dubey");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("98765");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("Vivek@123");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("Vivek@123");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

		String phoneErrorMessage = "Phone no not valid";

		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Phone no not valid']")).getText(),
				phoneErrorMessage);
		String url = "https://quickvee.com/register";
		Assert.assertEquals(driver.getCurrentUrl(), url);

		Thread.sleep(2000);

	}

	@Test(priority = 9)
	public void invalidFirstName() throws InterruptedException {

		System.out.println("Validate the register of account by providing Invalid first name input");
		loginpage.loginPageDisplay();
		loginpage.notLogin();
		loginpage.loginBtn();
		System.out.println("");

		homepage.isRegisterPage();
		homepage.RegisterBtnClick();
		System.out.println("");

		register.setFirstName("Vive@k");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("Dubey");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("9876542313");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("Vivek@123");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("Vivek@123");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

		String firstNameError = "Name only contain alphabet";

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Name only contain alphabet']")).getText(),
				firstNameError);
		String url = "https://quickvee.com/register";
		Assert.assertEquals(driver.getCurrentUrl(), url);

		Thread.sleep(2000);

	}

	@Test(priority = 10)
	public void invalidLastName() throws InterruptedException {
		System.out.println("Validate the register of account by providing Invalid last name input");
		loginpage.loginPageDisplay();
		loginpage.notLogin();
		loginpage.loginBtn();
		System.out.println("");

		homepage.isRegisterPage();
		homepage.RegisterBtnClick();
		System.out.println("");

		register.setFirstName("Vivek");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("Dube@y");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("9876542313");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("Vivek@123");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("Vivek@123");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

		String lastNameError = "Name only contain alphabet";

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Name only contain alphabet']")).getText(),
				lastNameError);
		String url = "https://quickvee.com/register";
		Assert.assertEquals(driver.getCurrentUrl(), url);

		Thread.sleep(2000);
	}

	@Test(priority = 11)
	public void invalidPasswordSC() throws InterruptedException {
		System.out.println(
				"Validate whether the Password fields in the Register Account page are following Password Complexity Standards");
		loginpage.loginPageDisplay();
		loginpage.notLogin();
		loginpage.loginBtn();
		System.out.println("");

		homepage.isRegisterPage();
		homepage.RegisterBtnClick();
		System.out.println("");

		register.setFirstName("Vivek");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("Dubey");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("9876542313");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("Vivek123");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("Vivek@123");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

		String passwordError = "Password should contain Special Character";

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Password should contain Special Character']"))
						.getText(),
				passwordError);

		String url = "https://quickvee.com/register";
		Assert.assertEquals(driver.getCurrentUrl(), url);
		Thread.sleep(2000);

	}

	@Test(priority = 12)
	public void invalidPasswordNumber() throws InterruptedException {

		register.setFirstName("Vivek");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("Dubey");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("9876542313");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("Vivek@");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("Vivek@123");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

		String passwordError = "Password should contain number";

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Password should contain number']")).getText(),
				passwordError);

		Thread.sleep(2000);

	}

	@Test(priority = 13)
	public void invalidPasswordLC() throws InterruptedException {

		register.setFirstName("Vivek");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("Dubey");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("9876542313");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("123@C");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("Vivek@123");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

		String passwordError = "Password should contain Lowercase letter";

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Password should contain Lowercase letter']"))
						.getText(),
				passwordError);

		Thread.sleep(2000);

	}

	@Test(priority = 14)
	public void invalidPasswordUC() throws InterruptedException {

		register.setFirstName("Vivek");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("Dubey");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("9876542313");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("123@c");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("Vivek@123");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

		String passwordError = "Password should contain Uppercase letter";

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Password should contain Uppercase letter']"))
						.getText(),
				passwordError);

		Thread.sleep(2000);

	}

	@Test(priority = 15)
	public void invalidPasswordLength() throws InterruptedException {

		register.setFirstName("Vivek");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("Dubey");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("9876542313");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("123@Cc");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("Vivek@123");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

		String passwordError = "Length should be greater or equal to 8";

		Assert.assertEquals(driver
				.findElement(By.xpath("//span[normalize-space()='Length should be greater or equal to 8']")).getText(),
				passwordError);

		Thread.sleep(2000);

	}

	@Test(priority = 16)
	public void displayOtherText() throws InterruptedException {
		System.out.println(
				"Validate whether the Quickvee image, Customer Register Text and Discover shopping delights! text visible or not");
		loginpage.loginPageDisplay();
		loginpage.notLogin();
		loginpage.loginBtn();
		System.out.println("");

		homepage.isRegisterPage();
		homepage.RegisterBtnClick();
		System.out.println("");

		String textRegister = "Customer Register";
		String customerText = "Discover shopping delights!";

		boolean image = driver.findElement(By.xpath("//img[@alt='Quickvee']")).isDisplayed();
		Assert.assertEquals(driver.findElement(By.xpath("//img[@alt='Quickvee']")).isDisplayed(), image);

		Assert.assertEquals(driver.findElement(By.xpath("//h1[normalize-space()='Customer Register']")).getText(),
				textRegister);

		Assert.assertEquals(driver
				.findElement(By.xpath("//span[text()='Discover shopping delights!' and @class='sub-heading-from']"))
				.getText(), customerText);

	}

	@Test(priority = 17)
	public void alreadyAccountText() throws InterruptedException {
		System.out.println("Check whether the text \"Already have an account?Login\" field is displayed or not");
		loginpage.loginPageDisplay();
		loginpage.notLogin();
		loginpage.loginBtn();
		System.out.println("");

		homepage.isRegisterPage();
		homepage.RegisterBtnClick();
		System.out.println("");

		String alreadyAccount = "Already have an account?Login";

		Assert.assertEquals(driver.findElement(By.xpath("//form[1]//div[2]//p[1]")).getText(), alreadyAccount);

	}

	@Test(priority = 18)

	public void merchantAccountText() throws InterruptedException {
		System.out.println("Check whether the text \"Already have an account?Login\" field is displayed or not");
		loginpage.loginPageDisplay();
		loginpage.notLogin();
		loginpage.loginBtn();
		System.out.println("");

		homepage.isRegisterPage();
		homepage.RegisterBtnClick();
		System.out.println("");

		String merchantAccount = "Are you a Merchant?Login";

		Assert.assertEquals(driver.findElement(By.xpath("//form[1]//div[2]//p[2]")).getText(), merchantAccount);

	}

	@Test(priority = 19)
	public void customerLoginButtonCheck() throws InterruptedException {

		System.out.println(
				"check whether when we click on login button of \"Already have an account?Login\" then it allows customer login or not");
		loginpage.loginPageDisplay();
		loginpage.notLogin();
		loginpage.loginBtn();
		System.out.println("");

		homepage.isRegisterPage();
		homepage.RegisterBtnClick();
		System.out.println("");

		register.customerLoginButton();
		String url = "https://quickvee.com/customer-login";
		String customerLoginText = "Customer Login";
		String welcomeText = "Welcome Back, Login to Shop";

		Assert.assertEquals(driver.getCurrentUrl(), url);
		Assert.assertEquals(driver.findElement(By.xpath("//h1[normalize-space()='Customer Login']")).getText(),
				customerLoginText);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='sub-heading-from']")).getText(), welcomeText);

	}

	@Test(priority = 20)

	public void merchantLoginButtonCheck() throws InterruptedException {
		System.out.println(
				"check whether when we click on login button of \"Are you a Merchant?\" then it allows customer login or not");
		loginpage.loginPageDisplay();
		loginpage.notLogin();
		loginpage.loginBtn();
		System.out.println("");

		homepage.isRegisterPage();
		homepage.RegisterBtnClick();
		System.out.println("");

		String originalWindow = driver.getWindowHandle();
		register.merchantLoginButton();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		String url = "https://www.quickvee.com/merchants/login";

		Assert.assertEquals(driver.getCurrentUrl(), url, "Merchant login tab URL validation failed");

	}

	@Test(priority = 21)
	public void firstNameBlank() throws InterruptedException {
		System.out.println(
				"Check whether we skip one of any mandatory field and click on register then, the new customer is register or not?");
		loginpage.loginPageDisplay();
		loginpage.notLogin();
		loginpage.loginBtn();
		System.out.println("");

		homepage.isRegisterPage();
		homepage.RegisterBtnClick();
		System.out.println("");

		register.setFirstName("");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("Dubey");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("8928185554");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("Vivek@123");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("Vivek@123");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

		String url = "https://quickvee.com/register";
		String ExpectedError = "Please enter First Name";

		Assert.assertEquals(driver.getCurrentUrl(), url);
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Please enter First Name']")).getText(),
				ExpectedError);

		Thread.sleep(2000);

	}

	@Test(priority = 22)
	public void phoneBlank() throws InterruptedException {

		register.setFirstName("Vivek");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("Dubey");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("Vivek@123");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("Vivek@123");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

		String url = "https://quickvee.com/register";
		String ExpectedError = "Please enter phone number";

		Assert.assertEquals(driver.getCurrentUrl(), url);
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[normalize-space()='Please enter phone number']")).getText(),
				ExpectedError);

		Thread.sleep(2000);

	}

	@Test(priority = 23)
	public void emailBlank() throws InterruptedException {

		register.setFirstName("Vivek");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("Dubey");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("78459621245");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername("");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("Vivek@123");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("Vivek@123");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

		String url = "https://quickvee.com/register";
		String ExpectedError = "Please enter email";

		Assert.assertEquals(driver.getCurrentUrl(), url);
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please enter email']")).getText(),
				ExpectedError);

		Thread.sleep(2000);

	}

	@Test(priority = 24)
	public void passwordBlank() throws InterruptedException {

		register.setFirstName("Vivek");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("Dubey");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("78459621245");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("Vivek@123");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

		String url = "https://quickvee.com/register";
		String ExpectedError = "Please enter password";

		Assert.assertEquals(driver.getCurrentUrl(), url);
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please enter password']")).getText(),
				ExpectedError);

		Thread.sleep(2000);

	}

	@Test(priority = 25)
	public void confirmPasswordBlank() throws InterruptedException {

		register.setFirstName("Vivek");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("Dubey");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("78459621245");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("Vivek@123");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

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
		System.out
				.println("Check whether we uncheck the capcta checkbox then customer can register the account of not");
		loginpage.loginPageDisplay();
		loginpage.notLogin();
		loginpage.loginBtn();
		System.out.println("");

		homepage.isRegisterPage();
		homepage.RegisterBtnClick();
		System.out.println("");

		register.setFirstName("Vivek");
		String firstNameField = register.getFirstName();
		System.out.println("First Name: " + firstNameField);

		register.setLastName("Dubey");
		String lastNameField = register.getLastName();
		System.out.println("Last Name: " + lastNameField);

		register.setPhone("9876542313");
		String phoneFiled = register.getPhone();
		System.out.println("Phone Number: " + phoneFiled);

		register.setUsername(register.newEmail() + "@gmail.com");
		String usernameField = register.getUsername();
		System.out.println("Username: " + usernameField);

		register.setPassword("Vivek@123");
		String passwordField = register.getPassword();
		System.out.println("Password: " + passwordField);

		register.setConfirmPassword("Vivek@123");
		String CpasswordField = register.getConfirPassword();
		System.out.println("Confirm Password: " + CpasswordField);

		register.clickRegister();

		String firstNameError = "Please check captcha";

		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please check captcha']")).getText(),
				firstNameError);
		String url = "https://quickvee.com/register";
		Assert.assertEquals(driver.getCurrentUrl(), url);

		Thread.sleep(2000);

	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(1000);
		driver.quit();
	}

}
