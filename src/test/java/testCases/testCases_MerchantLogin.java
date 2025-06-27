//validate whether the merchant is going in proper page after clicking on merchant login or not

package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.*;

import pageObjects.CustomerLogin;
import pageObjects.HomePage;
import pageObjects.MerchantLogin;

@Listeners(utilities.TestListener.class)

public class testCases_MerchantLogin {
	WebDriver driver;
	HomePage homePage;
	CustomerLogin customerLogin;
	MerchantLogin merchantLogin;

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://quickvee.com/");
		homePage = new HomePage(driver);
		customerLogin = new CustomerLogin(driver);
		merchantLogin = new MerchantLogin(driver);
	}

	@Test(priority = 1)
	public void validatingCorrectPage() {

		System.out.println(
				"validate whether the merchant is going in proper page after clicking on merchant login or not");
		homePage.loginBtn();

		customerLogin.merchantLoginClick();
		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		String expectedUrl = "https://www.quickvee.com/merchants/login";

		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		System.out.println("We are in Merchant Login Page");

	}

	@Test(priority = 2)
	public void quickveeLogoDisplay() {

		System.out.println("Validate whether the quickvee logo is displayed or not");
		homePage.loginBtn();

		customerLogin.merchantLoginClick();

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		String expectedUrl = "https://www.quickvee.com/merchants/login";

		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		System.out.println("We are in Merchant Login Page");
		MerchantLogin merchantLogin = new MerchantLogin(driver);

		merchantLogin.quickveeLogoDisplay();
		Assert.assertTrue(merchantLogin.quickveeLogoDisplay(), "Quickvee logo is not displayed");

	}

	@Test(priority = 3)
	public void loginValidCredential() throws InterruptedException {
		System.out.println("Validate logging into the Application using valid credentials");
		homePage.loginBtn();

		customerLogin.merchantLoginClick();

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		merchantLogin.setStoreName("superadmin");
		System.out.println("Store Name: " + merchantLogin.getStoreName());

		merchantLogin.setUserName("superadmin");
		System.out.println("User Name: " + merchantLogin.getUserName());

		merchantLogin.setPassword("S3CUrePaSsw@rd!25");
		System.out.println("Password: " + merchantLogin.getPassword());

		merchantLogin.loginBtnClick();
		// System.out.println("The Url is: " + driver.getCurrentUrl());

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();
		System.out.println("Redirected URL after login: " + actualUrl);

		String expectedUrl = "https://www.quickvee.com/merchants/users/unapprove";
		Assert.assertEquals(actualUrl, expectedUrl, "Unexpected login redirection");
		String superAdmin = "Superadmin";
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='admin_medium']")).getText(), superAdmin);

	}

	@Test(priority = 4)
	public void loginInValidCredential() throws InterruptedException {
		System.out.println(
				"Validate logging into the Application using invalid credentials (i.e. Invalid storename, Invalid email address and Invalid Password)");
		homePage.loginBtn();

		customerLogin.merchantLoginClick();

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		merchantLogin.setStoreName("superadmins");
		System.out.println("Store Name: " + merchantLogin.getStoreName());

		merchantLogin.setUserName("superadmins");
		System.out.println("User Name: " + merchantLogin.getUserName());

		merchantLogin.setPassword("S3CUrePaSsw@rd!25s");
		System.out.println("Password: " + merchantLogin.getPassword());

		merchantLogin.loginBtnClick();
		// System.out.println("The Url is: " + driver.getCurrentUrl());

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();
		System.out.println("Redirected URL after login: " + actualUrl);

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
	}

	@Test(priority = 5)
	public void invalidEmail() throws InterruptedException {
		System.out.println("Verify logging into the Application using invalid email address and valid Password, valid storeName)");
		homePage.loginBtn();

		customerLogin.merchantLoginClick();

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		merchantLogin.setStoreName("superadmin");
		System.out.println("Store Name: " + merchantLogin.getStoreName());

		merchantLogin.setUserName("superadmins");
		System.out.println("User Name: " + merchantLogin.getUserName());

		merchantLogin.setPassword("S3CUrePaSsw@rd!25");
		System.out.println("Password: " + merchantLogin.getPassword());

		merchantLogin.loginBtnClick();
		// System.out.println("The Url is: " + driver.getCurrentUrl());

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();
		System.out.println("Redirected URL after login: " + actualUrl);

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
	}

	@Test(priority = 6)
	public void invalidPassword() throws InterruptedException {
		System.out.println("Validate logging into the Application using valid email address and invalid Password and valid Storename)");
		homePage.loginBtn();

		customerLogin.merchantLoginClick();

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		merchantLogin.setStoreName("superadmin");
		System.out.println("Store Name: " + merchantLogin.getStoreName());

		merchantLogin.setUserName("superadmin");
		System.out.println("User Name: " + merchantLogin.getUserName());

		merchantLogin.setPassword("S3CUrePaSsw@rd!25s");
		System.out.println("Password: " + merchantLogin.getPassword());

		merchantLogin.loginBtnClick();
		// System.out.println("The Url is: " + driver.getCurrentUrl());

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();
		System.out.println("Redirected URL after login: " + actualUrl);

		String expectedUrl = "https://www.quickvee.com/merchants/login";
		Assert.assertEquals(actualUrl, expectedUrl, "Unexpected login redirection");

		Thread.sleep(500);
		String errorMessage = "Incorrect Username & Password";
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Incorrect Username & Password')]"))
				.getText(), errorMessage);

	}

	@Test(priority = 7)
	public void invalidStorename() throws InterruptedException {
		System.out.println("Validate logging into the Application using valid email address and valid Password and Invalid Storename)");
		homePage.loginBtn();

		customerLogin.merchantLoginClick();

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		merchantLogin.setStoreName("superadmins");
		System.out.println("Store Name: " + merchantLogin.getStoreName());

		merchantLogin.setUserName("superadmin");
		System.out.println("User Name: " + merchantLogin.getUserName());

		merchantLogin.setPassword("S3CUrePaSsw@rd!25");
		System.out.println("Password: " + merchantLogin.getPassword());

		merchantLogin.loginBtnClick();
		// System.out.println("The Url is: " + driver.getCurrentUrl());

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();
		System.out.println("Redirected URL after login: " + actualUrl);

		String expectedUrl = "https://www.quickvee.com/merchants/login";
		Assert.assertEquals(actualUrl, expectedUrl, "Unexpected login redirection");

		Thread.sleep(500);
		String errorMessage = "Incorrect Username & Password";
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Incorrect Username & Password')]"))
				.getText(), errorMessage);

	}
	
	@Test(priority = 8)
	public void invalidStorenamePassword() throws InterruptedException {
		System.out.println("Validate logging into the Application using valid email address and Invalid Password and Invalid Storename)");
		homePage.loginBtn();

		customerLogin.merchantLoginClick();

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		merchantLogin.setStoreName("superadmins");
		System.out.println("Store Name: " + merchantLogin.getStoreName());

		merchantLogin.setUserName("superadmin");
		System.out.println("User Name: " + merchantLogin.getUserName());

		merchantLogin.setPassword("S3CUrePaSsw@rd!25s");
		System.out.println("Password: " + merchantLogin.getPassword());

		merchantLogin.loginBtnClick();
		// System.out.println("The Url is: " + driver.getCurrentUrl());

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();
		System.out.println("Redirected URL after login: " + actualUrl);

		String expectedUrl = "https://www.quickvee.com/merchants/login";
		Assert.assertEquals(actualUrl, expectedUrl, "Unexpected login redirection");

		Thread.sleep(500);
		String errorMessage = "Incorrect Username & Password";
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[contains(@class, 'MuiAlert-message') and contains(text(), 'Incorrect Username & Password')]"))
				.getText(), errorMessage);

	}
	@Test(priority = 9)
	public void InvalidEmailPassword() throws InterruptedException {
		System.out.println("Validate logging into the Application using Invalid email address and Invalid Password and valid Storename)");
		homePage.loginBtn();

		customerLogin.merchantLoginClick();

		String originalWindow = driver.getWindowHandle();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		merchantLogin.setStoreName("superadmin");
		System.out.println("Store Name: " + merchantLogin.getStoreName());

		merchantLogin.setUserName("superadmins");
		System.out.println("User Name: " + merchantLogin.getUserName());

		merchantLogin.setPassword("S3CUrePaSsw@rd!25");
		System.out.println("Password: " + merchantLogin.getPassword());

		merchantLogin.loginBtnClick();
		// System.out.println("The Url is: " + driver.getCurrentUrl());

		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();
		System.out.println("Redirected URL after login: " + actualUrl);

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
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
