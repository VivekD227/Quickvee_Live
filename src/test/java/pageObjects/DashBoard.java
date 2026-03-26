package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.basePage;

public class DashBoard extends basePage {

	public DashBoard(WebDriver driver) {
		super(driver);

	}

	By dashboard_title = By.xpath("//h1[normalize-space()='Merchant Dashboard']");

	By menu = By.xpath("//body//div//img[2]");

	By logout = By.xpath("//li[3]");

	By sideMenu = By.xpath("//div[@class='flex items-center justify-between md:px-4 mx-2']//*[name()='svg']");

	By dashBoardMenu = By
			.cssSelector("div[class='flex items-center Dashboard-for-android bg-[#414F54] text-[#FFC400]']");

	By inventoryMenu = By.xpath(
			"//div[@class='relative Inventory-for-android']//div[contains(@class,'w-full flex items-center cursor-pointer')]");

	By category = By.xpath("//a[normalize-space()='Categories']");

	By brands = By.xpath("//a[normalize-space()='Brands']");

	By tags = By.xpath("//a[normalize-space()='Tags']");

	By attributes = By.xpath("//a[normalize-space()='Product Attributes']");

	By products = By.xpath("//a[normalize-space()='Products']");

	public boolean dashboard_titleDisplay() {
		visiblityOfElement(dashboard_title);
		return driver.findElement(dashboard_title).isDisplayed();
	}

	public void sideMenuClick() {
		elementClick(sideMenu);
		org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(sideMenu));
	}

	public boolean dashBoardMenuVisible() {
		try {
			return driver.findElement(dashBoardMenu).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void sideMenuVisible() {
		if (dashBoardMenuVisible() == false) {
			sideMenuClick();
		}
	}

	public void menuClick() {
		elementClick(menu);
		org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(menu));
	}

	public void inventoryMenuClick() {
		elementClick(inventoryMenu);
		org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(inventoryMenu));
	}

	public String inventoryMenuText() {
		visiblityOfElement(inventoryMenu);
		return driver.findElement(inventoryMenu).getText();
	}

	public void categoryClick() {
		elementClick(category);
		org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(category));
	}

	public String categoryText() {
		visiblityOfElement(category);
		return driver.findElement(category).getText();
	}

	public void brandsClick() {
		elementClick(brands);
		org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(brands));
	}

	public String brandsMenuText() {
		visiblityOfElement(brands);
		return driver.findElement(brands).getText();
	}

	public void tagsClick() {
		elementClick(tags);
		org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(tags));
	}

	public String tagsMenuText() {
		visiblityOfElement(tags);
		return driver.findElement(tags).getText();
	}

	public void attributesClick() {
		elementClick(attributes);
		org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(attributes));
	}

	public String attributesMenuText() {
		visiblityOfElement(attributes);
		return driver.findElement(attributes).getText();
	}

	public void productsClick() {
		elementClick(products);
		org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(products));
	}

	public String productsMenuText() {
		visiblityOfElement(products);
		return driver.findElement(products).getText();
	}

	public void logoutClick() {
		elementClick(logout);
		org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(logout));
	}
}