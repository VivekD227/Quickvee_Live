package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class waitHelper {

	WebDriver driver;
	
	public waitHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	public void visiblityOfElement(By byVisible) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		wait.until(ExpectedConditions.visibilityOfElementLocated(byVisible));
	}
	
	
	public void elementClick(By byClick) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		wait.until(ExpectedConditions.elementToBeClickable(byClick));

	}
}
