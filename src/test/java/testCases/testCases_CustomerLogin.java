package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.CustomerLogin;
import pageObjects.HomePage;

@Listeners(utilities.TestListener.class)

public class testCases_CustomerLogin {

	WebDriver driver;
	HomePage homePage;
	CustomerLogin customerLogin;
	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://quickvee.com/");
		homePage = new HomePage(driver);
		customerLogin = new CustomerLogin(driver);

	}
	
	@Test (priority = 0)
	public void quickveeLogoAndTextDisplayed() {
		homePage.loginBtn();
		
		
		System.out.println("Validate whether the Quickvee logo, Customer Login text and welcome text is visible or not?");
		
		Assert.assertTrue(customerLogin.quickveeLogoDisplay(), "Quickvee logo is not displayed");
		Assert.assertTrue(customerLogin.customerLoginTextDisplay(), "Customer Login text is not displayed");
		Assert.assertTrue(customerLogin.welcomeTextDisplay(), "Welcome Text is not displayed");
	
	}
	@Test(priority = 1)
	public void forgotPasswordTextClass() {
		
		homePage.loginBtn();
		
		System.out.println("Validate 'Forgotten Password' link is available in the Login page and is working and it is clickable");
		Assert.assertTrue(customerLogin.forgotPasswordDisplay(), "Forgot Password is not displayed");
	}
	
	@Test(priority = 2)
	public void forgotPasswordClickable() {
		homePage.loginBtn();
		
		customerLogin.forgotPasswordClick();

		
		String expectedUrl = "https://quickvee.com/forgot-password";
		
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		Assert.assertTrue(customerLogin.isInForgotPasswordPage(), "Not in Forgot Password Page");
	}
	
	@Test(priority = 3)
	public void orSignInClass() {
		homePage.loginBtn();
		
		System.out.println("Validate whether the \"\"Or Sign in with\"\" text is visible or not");
		Assert.assertTrue(customerLogin.orSignInTextDisplay(), "Sign In text is not visible");
	}
	
	@Test(priority = 4)
	public void validEmailPass() throws InterruptedException {
		
		homePage.loginBtn();
		
		System.out.println("Validate logging into the Application using valid credentials");
		customerLogin.setEmail("vivek22@gmail.com");
		String emailPut = customerLogin.getEmail();
		System.out.println("Email: "+emailPut);
		
		customerLogin.setPassword("Vivek@123");
		String passwordPut = customerLogin.getPassword();
		System.out.println("Password: "+passwordPut);

		customerLogin.loginBtnClick();
		Thread.sleep(1000);
	//	System.out.println(driver.getCurrentUrl());
		String originalWindow = driver.getWindowHandle();
		for(String windowHandle : driver.getWindowHandles()) {
			if(!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		String expectedUrl = "https://quickvee.com/";
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		
		homePage.loggedIn();
		System.out.println("Customer Successfully Logged In");
		}
	
	@Test(priority = 5)
	public void InvalidEmailPass() throws InterruptedException {
		homePage.loginBtn();
		
		System.out.println("Validate logging into the Application using invalid credentials (i.e. Invalid email address and Invalid Password)");
		customerLogin.setEmail("vivek22@lgmail.com");
		String emailPut = customerLogin.getEmail();
		System.out.println("Email: "+emailPut);
		
		customerLogin.setPassword("Vivek@1232");
		String passwordPut = customerLogin.getPassword();
		System.out.println("Password: "+passwordPut);

		customerLogin.loginBtnClick();
		Thread.sleep(1000);
		
		String invalidError = "Invalid username or password";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='MuiAlert-message css-1xsto0d']")).getText(), invalidError);

		String expectedUrl = "https://quickvee.com/customer-login";
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		
		}
	
	@Test(priority = 6)
	public void invalidEmailValidPass() throws InterruptedException {
		homePage.loginBtn();
		
		System.out.println("Verify logging into the Application using invalid email address and valid Password");
		customerLogin.setEmail("vivek22@lgmail.com");
		String emailPut = customerLogin.getEmail();
		System.out.println("Email: "+emailPut);
		
		customerLogin.setPassword("Vivek@123");
		String passwordPut = customerLogin.getPassword();
		System.out.println("Password: "+passwordPut);

		customerLogin.loginBtnClick();
		Thread.sleep(1000);
		
		String invalidError = "Invalid username or password";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='MuiAlert-message css-1xsto0d']")).getText(), invalidError);

		String expectedUrl = "https://quickvee.com/customer-login";
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		
		}

	@Test(priority = 7)
	public void validEmailInvalidPass() throws InterruptedException {
		homePage.loginBtn();
		
		System.out.println("Validate logging into the Application using valid email address and invalid Password");
		customerLogin.setEmail("vivek22@gmail.com");
		String emailPut = customerLogin.getEmail();
		System.out.println("Email: "+emailPut);
		
		customerLogin.setPassword("Vivek@1232");
		String passwordPut = customerLogin.getPassword();
		System.out.println("Password: "+passwordPut);

		customerLogin.loginBtnClick();
		Thread.sleep(1000);
		
		String invalidError = "Invalid username or password";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='MuiAlert-message css-1xsto0d']")).getText(), invalidError);

		String expectedUrl = "https://quickvee.com/customer-login";
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		
		}
	
	@Test(priority = 8)
	public void blankEmailPass() throws InterruptedException {
		homePage.loginBtn();
		
		System.out.println("Validate logging into the Application without providing any credentials");
		customerLogin.setEmail("");
		String emailPut = customerLogin.getEmail();
		System.out.println("Email: "+emailPut);
		
		customerLogin.setPassword("");
		String passwordPut = customerLogin.getPassword();
		System.out.println("Password: "+passwordPut);

		customerLogin.loginBtnClick();
		Thread.sleep(1000);
		
		String emailError = "Please enter email";
		String passError = "Please enter password";

		String expectedUrl = "https://quickvee.com/customer-login";
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please enter email']")).getText(), emailError);
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please enter password']")).getText(), passError);
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		
		}

	@Test(priority = 9)
	public void blankEmailPass2() throws InterruptedException {
		homePage.loginBtn();
		
		System.out.println("Validate logging into the Application without providing any credentials");
		customerLogin.setEmail("");
		String emailPut = customerLogin.getEmail();
		System.out.println("Email: "+emailPut);
		
		customerLogin.setPassword("");
		String passwordPut = customerLogin.getPassword();
		System.out.println("Password: "+passwordPut);

		customerLogin.loginBtnClick();
		Thread.sleep(1000);
		
		String emailError = "Please enter email";
		String passError = "Please enter password";

		
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please enter email']")).getText(), emailError);
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please enter password']")).getText(), passError);
		
		}
	@Test(priority = 10)
	public void putEmailBlankPass() throws InterruptedException {
		homePage.loginBtn();
		
		
		customerLogin.setEmail("vivek.dubey22@gmail.com");
		String emailPut = customerLogin.getEmail();
		System.out.println("Email: "+emailPut);
		
		customerLogin.setPassword("");
		String passwordPut = customerLogin.getPassword();
		System.out.println("Password: "+passwordPut);

		customerLogin.loginBtnClick();
		Thread.sleep(1000);
		
		String passError = "Please enter password";

		
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please enter password']")).getText(), passError);
		
		}
	
	@Test(priority = 11)
	public void blankEmailPutPass() throws InterruptedException {
		homePage.loginBtn();
		
		
		customerLogin.setEmail("");
		String emailPut = customerLogin.getEmail();
		System.out.println("Email: "+emailPut);
		
		customerLogin.setPassword("Vivek@123");
		String passwordPut = customerLogin.getPassword();
		System.out.println("Password: "+passwordPut);

		customerLogin.loginBtnClick();
		Thread.sleep(1000);
		
		String emailError = "Please enter email";

		
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='Please enter email']")).getText(), emailError);
		
		}
	
	@Test(priority = 12)
	public void googleLoginButton() throws InterruptedException {
		homePage.loginBtn();
		
		System.out.println("Check whether the \"Log in with google\" button is working or not");
		customerLogin.setEmail("vivek22@gmail.com");
		String emailPut = customerLogin.getEmail();
		System.out.println("Email: "+emailPut);
		
		customerLogin.setPassword("Vivek@1232");
		String passwordPut = customerLogin.getPassword();
		System.out.println("Password: "+passwordPut);
		
		customerLogin.googleLoginClick();
		Assert.assertTrue(customerLogin.googleLoginClick(), "Button is not clicked");
		
	}
	@Test(priority = 13)
	public void checkRegisterCustomerText() throws InterruptedException {
		homePage.loginBtn();
		
		System.out.println("Check whether \"Don't have an account?Register Now\" text is displayed or not?");
		String registerCustomerText = "Don't have an account?Register Now";
		Assert.assertEquals(driver.findElement(By.xpath("//body/div[@id='root']/div[@class='main-authentication-component']/div[@class=' login-customer-form ']/form[@class='login-customer-form']/div[@class='login-customer-form']/p[1]")).getText(), registerCustomerText);

		}
	
	@Test(priority = 14)
	public void checkMerchantLoginText() throws InterruptedException {
		homePage.loginBtn();
		
		System.out.println("Check whether \"Are you a Merchant? Login\" text is displayed or not?");

		String merchantLoginText = "Are you a Merchant? Login";
		Assert.assertEquals(driver.findElement(By.xpath("//p[2]")).getText(), merchantLoginText);

		}
	
	@Test(priority = 15)
	public void clickRegisterNow() throws InterruptedException {
		homePage.loginBtn();
		
		System.out.println("Check whether the Register Now functionality is working as expected or not");
		customerLogin.registerCustomerClick();
		
		String expectedUrl = "https://quickvee.com/register";
		String customerRegisterText = "Customer Register";
		
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		Assert.assertEquals(driver.findElement(By.xpath("//h1[normalize-space()='Customer Register']")).getText(), customerRegisterText);

		}
	@Test(priority = 16)
	public void clickMerchantLogin() throws InterruptedException {
		homePage.loginBtn();
		
		
		customerLogin.merchantLoginClick();
		System.out.println("Check whether the  Login functionality is working as expected or not");
		String originalWindow = driver.getWindowHandle();
		customerLogin.merchantLoginClick();

		
		for(String windowHandle : driver.getWindowHandles()) {
			if(!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		
		
		String expectedUrl = "https://www.quickvee.com/merchants/login";
		
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

		}
	
	//Note: This code will write after the forgot password functionality completed.

	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(500);
		driver.quit();
	}
	
}
