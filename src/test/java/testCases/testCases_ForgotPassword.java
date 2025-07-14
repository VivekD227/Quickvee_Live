package testCases;

import org.testng.annotations.Listeners;
import org.testng.Assert;
import org.testng.annotations.*;

import utilities.GmailReader;
import utilities.baseClass;

@Listeners(utilities.TestListener.class)

public class testCases_ForgotPassword extends baseClass {

	@Test(priority = 1)
	public void directionURL() {

		logger.info("");
		logger.info("Validate the user is able to go fogot password page if he/she click on forgot password");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.forgotPasswordClick();
		logger.info("User click on forgot password button");

		String expectedUrl = "https://quickvee.com/forgot-password";

		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		Assert.assertTrue(customerLogin.isInForgotPasswordPage(), "Not in Forgot Password Page");

		logger.info("User is on " + driver.getCurrentUrl() + " url");
	}

	@Test(priority = 2)
	public void quickveeLogoAndTextDisplayed() {

		logger.info("");
		logger.info(
				"Validate whether the Quickvee Logo, Forgot Password Text and \"To reset your password, please enter your Email ID & follow the instructions.\" text is visible or not ");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.forgotPasswordClick();
		logger.info("User click on forgot password button");

		Assert.assertTrue(forgotPassword.quickveeLogoDisplay(), "Quickvee logo is not displayed");
		Assert.assertTrue(forgotPassword.otherTextDisplay(), "Forgot Password text is not displayed");

		String expectedUrl = "https://quickvee.com/forgot-password";

		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

		logger.info("User is on " + driver.getCurrentUrl() + " url");
	}

	@Test(priority = 3)
	public void emailSentMessage() throws InterruptedException {

		logger.info("");
		logger.info(
				"check whether if we put proper email and submit the button the Email Send, Please check your Email this message is displayed or not");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.forgotPasswordClick();
		logger.info("User click on forgot password button");

		forgotPassword.setEmailId(p.getProperty("forgotPasswordEmail"));
		forgotPassword.submitBtnClick();

		Thread.sleep(2000);
		String expectedUrl = "https://quickvee.com/forgot-password";

		Assert.assertEquals(forgotPassword.emailMessageDisplayed(), "Email Send, Please check your Email");
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 4)
	public void emailErrorMessage() throws InterruptedException {

		logger.info("");
		logger.info(
				"check whether if customer is putting the invalid email id then the success message is coming or not");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.forgotPasswordClick();
		logger.info("User click on forgot password button");

		forgotPassword.setEmailId(p.getProperty("forgotPasswordInvalidEmail"));
		forgotPassword.submitBtnClick();

		Thread.sleep(2000);
		String expectedUrl = "https://quickvee.com/forgot-password";

		Assert.assertEquals(forgotPassword.emailMessageDisplayed(), "This is not the Customer Email-ID.");
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 5)
	public void invalidEmailFormat() throws InterruptedException {

		logger.info("");
		logger.info("check whether if customer is putting invalid format email then what happen");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.forgotPasswordClick();
		logger.info("User click on forgot password button");

		forgotPassword.setEmailId(p.getProperty("forgotPasswordErrorFormatEmail"));
		forgotPassword.submitBtnClick();

		Thread.sleep(2000);
		String expectedUrl = "https://quickvee.com/forgot-password";

		Assert.assertEquals(forgotPassword.emailFormatMessage(), "Please enter valid email");
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 6)
	public void blankEmail() throws InterruptedException {

		logger.info("");
		logger.info("check whether if customer is putting blank email then what happen");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.forgotPasswordClick();
		logger.info("User click on forgot password button");

		forgotPassword.setEmailId(p.getProperty("Blank"));
		forgotPassword.submitBtnClick();

		Thread.sleep(2000);
		String expectedUrl = "https://quickvee.com/forgot-password";

		Assert.assertEquals(forgotPassword.emailFormatMessage(), "Please enter email");
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

		logger.info("User is on " + driver.getCurrentUrl() + " url");

	}

	@Test(priority = 0)
	public void validateResetLinkReceivedInEmail() throws InterruptedException {
		
		logger.info("");
		logger.info("check whether if we put proper email and submit the button the Email Send, Please check your Email this message is displayed or not");
		logger.info("-----------------------");

		homePage.loginBtn();
		logger.info("User click on login button");

		customerLogin.forgotPasswordClick();
		logger.info("User click on forgot password button");

		String userEmail = p.getProperty("forgotPasswordEmail");
		forgotPassword.setEmailId(userEmail);
		forgotPassword.submitBtnClick();
		logger.info("User click on submit button");

		Thread.sleep(15000);

		String gmailUsername = p.getProperty("gmailEmail");
		String gmailPassword = p.getProperty("gmailPassword");

		try {
			String resetLink = GmailReader.getResetLinkFromEmail(gmailUsername, gmailPassword);
			//System.out.println("Reset link: " + resetLink);
			Assert.assertNotNull(resetLink);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("GmailReader failed: " + e.getMessage());
		}
	}

}
