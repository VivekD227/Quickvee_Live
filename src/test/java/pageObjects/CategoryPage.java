package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.basePage;

public class CategoryPage extends basePage {

	public CategoryPage(WebDriver driver) {
		super(driver);

	}
	
	By categoryText = By.xpath("//li[contains(text(),'In order to use the Quickvee app one Category is r')]");
	
	By categoriesText = By.xpath("//span[normalize-space()='Categories']");
	
	By addCategoryBtn = By.xpath("//p[normalize-space()='Add Category']");
	
	By categoryPresent = By.className("//tr[contains(@class,'MuiTableRow-root')]");
	




	public int categoryCount() {
		visiblityOfElement(categoryPresent);
		return driver.findElements(categoryPresent).size();
	}
	
}