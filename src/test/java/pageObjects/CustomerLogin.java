package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.waitHelper;

public class CustomerLogin extends waitHelper {
	WebDriver driver;
	
	public CustomerLogin(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h1[normalize-space()='Customer Login']")
	WebElement isRegisterDisplay;
	
	@FindBy(xpath = "//a[normalize-space()='Register Now']")
	WebElement storeLoginBtn;
	
	By quickveeLogo = By.xpath("//img[@alt='Quickvee']");
	
	By customerLoginText = By.xpath("//h1[normalize-space()='Customer Login']");
	
	By welcomeText = By.xpath("//span[@class='sub-heading-from']");
	
	By emailIdField = By.xpath("//input[@id=':r0:']");
	
	By passwordField = By.xpath("//input[@id=':r1:']");
	
	By forgotPasswordBtn = By.xpath("//a[normalize-space()='Forgot Password ?']");
	
	By loginBtn = By.xpath("//button[normalize-space()='Login']");
	
	By orSignInText = By.xpath("//p[normalize-space()='Or Sign in with']");
	
	By googleLoginBtn = By.xpath("//button[normalize-space()='Log In with Google']");
	
	By registerCustomerText = By.xpath("//body/div[@id='root']/div[@class='main-authentication-component']/div[@class=' login-customer-form ']/form[@class='login-customer-form']/div[@class='login-customer-form']/p[1]");
	
	By merchantLoginText = By.xpath("//p[2]");
	
	By registerCustomerBtn = By.xpath("//a[normalize-space()='Register Now']");
	
	By merchantLoginBtn = By.xpath("//a[normalize-space()='Login']");
	
	By forgotPasswordPage = By.xpath("//h1[normalize-space()='Forgot Password']");
	
	public void clearInputField(By locator) {
	    WebElement element = driver.findElement(locator);
	    element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}
	
	public void isRegisterPage() {
		boolean isDisplayedRegister = isRegisterDisplay.isDisplayed();
		if(isDisplayedRegister) {
			System.out.println("We Clicked on Login Button");

			System.out.println("We are in Home Register Page");
			Assert.assertTrue(isDisplayedRegister);
		}
		else {
			Assert.assertTrue(false);
			
		}
	}
	
	public boolean quickveeLogoDisplay() {
		visiblityOfElement(quickveeLogo);
		return driver.findElement(quickveeLogo).isDisplayed();
	}
	
	public boolean customerLoginTextDisplay() {
		visiblityOfElement(customerLoginText);
		return driver.findElement(customerLoginText).isDisplayed();

	}
	
	public boolean welcomeTextDisplay() {
		visiblityOfElement(welcomeText);
		return driver.findElement(welcomeText).isDisplayed();

	}
	
	public void setEmail(String emailId) {
		visiblityOfElement(emailIdField);
		clearInputField(emailIdField);
		driver.findElement(emailIdField).sendKeys(emailId);
		
	}
	
	public String getEmail() {
		return driver.findElement(emailIdField).getAttribute("value");
	}
	
	public void setPassword(String pass) {
		visiblityOfElement(passwordField);
		clearInputField(passwordField);
		driver.findElement(passwordField).sendKeys(pass);
	}
	
	public String getPassword() {
		return driver.findElement(passwordField).getAttribute("value");
	}
	
	public boolean forgotPasswordDisplay() {
		visiblityOfElement(forgotPasswordBtn);
		return driver.findElement(forgotPasswordBtn).isDisplayed();
	}
	
	public void forgotPasswordClick() {
		elementClick(forgotPasswordBtn);
		driver.findElement(forgotPasswordBtn).click();
	}
	
	public void loginBtnClick() {
		elementClick(loginBtn);
		driver.findElement(loginBtn).click();
		System.out.println("Login button is clicked");
	}
	
	public boolean orSignInTextDisplay() {
		visiblityOfElement(orSignInText);
		return driver.findElement(orSignInText).isDisplayed();
	}
	
	public boolean googleLoginClick() {
		try {
			elementClick(googleLoginBtn); // custom helper, I assume
			driver.findElement(googleLoginBtn).click();
			System.out.println("The google login button is clicked");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
		
	public void registerCustomerTextDisplay() {
		visiblityOfElement(registerCustomerText);
		driver.findElement(registerCustomerText).isDisplayed();
	}
	
	public void merchantLoginTextDisplay() {
		visiblityOfElement(merchantLoginText);
		driver.findElement(merchantLoginText).isDisplayed();
	}
	
	public void registerCustomerClick() {
		elementClick(registerCustomerBtn);
		driver.findElement(registerCustomerBtn).click();
	}
	
	public void merchantLoginClick() {
		elementClick(merchantLoginBtn);
		driver.findElement(merchantLoginBtn).click();
	}
	
	public boolean isInForgotPasswordPage() {
		visiblityOfElement(forgotPasswordPage);
		return driver.findElement(forgotPasswordPage).isDisplayed();
	}
	
	public void RegisterBtnClick() {
		storeLoginBtn.click();
		System.out.println("User is click on Register button to create a New user");
	}
	
	
}
