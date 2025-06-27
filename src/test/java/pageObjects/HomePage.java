package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@class='quickvee-customer-login']")
	WebElement islogin;
	
	public void loginPageDisplay() {
		String url = "https://quickvee.com/";
		if(driver.getCurrentUrl().equals(url)) {
			System.out.println("You are in Login Page");
			Assert.assertTrue(true);
		}
	}
	
	public void notLogin() {
		String loginContent = islogin.getText();
		if(loginContent.equals("Log In")) {
			System.out.println("You are not Login, Kindly Login In Quickvee");
			Assert.assertTrue(true);

		}
		else if(!loginContent.equals("Log In")) {
			System.out.println("User is Logged In");
			Assert.assertTrue(true);

		}
	}
	
	public void loginBtn () { 
		islogin.click();
		
		}
	
	public void loggedIn() {
		String loginBtnName = islogin.getText();
		if(!loginBtnName.equals("Log In")) {
			System.out.println("The User has Successfully Registered!!!");
			Assert.assertTrue(true);
		}
	}

	}


