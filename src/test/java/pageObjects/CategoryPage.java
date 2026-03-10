package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utilities.basePage;

public class CategoryPage extends basePage {

	public CategoryPage(WebDriver driver) {
		super(driver);

	}

	By categoryText = By.xpath("//li[contains(text(),'In order to use the Quickvee app one Category is r')]");

	By categoriesText = By.xpath("//span[normalize-space()='Categories']");

	By addCategoryBtn = By.xpath("//p[normalize-space()='Add Category']");

	By categoryPresent = By.xpath("//tr[contains(@class,'MuiTableRow-root')]");

	By viewItem = By.xpath("//tbody/tr[1]/td[3]/div[1]/span[1]/p[1]/p[1]");

	By viewCatName = By.cssSelector("div[class='q-add-categories-section-header text-[18px]']");

	By tooltipTextLocator = By.xpath("//div[contains(@class, 'MuiTooltip-tooltip')]");

	By closeBtn = By.xpath("//div[@class='flex justify-between gap-4']//img[@alt='icon']");

	By newCatText = By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 text-justify css-1erbx20']");

	By addText = By.xpath("//span[normalize-space()='Add New Category']");

	By categoryInput = By.xpath("//input[@id='size-small-standard']");

	By descInput = By.xpath("//textarea[@id='description']");

	By selectTax = By.xpath("//div[@class='flex items-center justify-between cursor-pointer p-2']");

	By usePointsCheckbox = By.xpath("//span[normalize-space()='Use Points']/parent::label//input[@type='checkbox']");
	By earnPointsCheckbox = By.xpath("//span[normalize-space()='Earn Points']/parent::label//input[@type='checkbox']");
	By lotteryCheckbox = By.xpath("//span[normalize-space()='Lottery']/parent::label//input[@type='checkbox']");
	By ebtCheckbox = By
			.xpath("//span[normalize-space()='EBT/SNAP Eligibility']/parent::label//input[@type='checkbox']");

	public int categoryCount() {
		visiblityOfElement(categoryPresent);
		return driver.findElements(categoryPresent).size();
	}

	public String firstCategoryName() {
		String name = driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText();
		// System.out.println(name);
		return name;
	}

	public void viewItemClick() {
		elementClick(viewItem);
		driver.findElement(viewItem).click();
	}

	public String hoverAndViewTooltipText() {
		org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
		actions.moveToElement(driver.findElement(viewItem)).perform();
		visiblityOfElement(tooltipTextLocator);
		return driver.findElement(tooltipTextLocator).getText();
	}

	public void closeBtnClick() {
		visiblityOfElement(closeBtn);
		driver.findElement(closeBtn).click();
	}

	public String getviewCatName() {
		visiblityOfElement(viewCatName);
		return driver.findElement(viewCatName).getText();
	}

	public void checkCategory() throws InterruptedException {
		if (categoryCount() == 0) {
			Assert.fail();
		} else {
			Thread.sleep(3000);
			// System.out.println(categoryCount());
		}
	}

	public void presentCategory(String categoryName) {
		By categoryLocator = By
				.xpath("//tr[contains(@class,'MuiTableRow-root')]//td//div[normalize-space()='" + categoryName + "']");
		try {
			boolean isPresent = driver.findElement(categoryLocator).isDisplayed();
			if (isPresent) {
				System.out.println(categoryName + " category is present. PASS");
				Assert.assertTrue(true);
			} else {
				Assert.fail(categoryName + " category is present in DOM but not visible. FAIL");
			}
		} catch (Exception e) {
			Assert.fail(categoryName + " category is NOT present. FAIL");
		}
	}

	public void checkDeleteButtonNotPresent(String categoryName) {
		By deleteButtonLocator = By.xpath("//tr[contains(@class,'MuiTableRow-root') and .//div[normalize-space()='"
				+ categoryName + "']]//img[@alt='delete-icon']");
		try {
			boolean isPresent = driver.findElement(deleteButtonLocator).isDisplayed();
			if (isPresent) {
				Assert.fail("Delete button is explicitly present for " + categoryName + ". FAIL");
			} else {
				System.out.println("Delete button is present in DOM but hidden for " + categoryName + ". PASS");
				Assert.assertTrue(true);
			}
		} catch (Exception e) {
			System.out.println("Delete button is completely absent from DOM for " + categoryName + ". PASS");
			Assert.assertTrue(true);
		}
	}

	public String categoryTextDisaply() {
		visiblityOfElement(categoryText);
		return driver.findElement(categoryText).getText();
	}

	public String categoriesTextDisplay() {
		visiblityOfElement(categoriesText);
		return driver.findElement(categoriesText).getText();
	}

	public void addCategoryBtnClick() {
		elementClick(addCategoryBtn);
		driver.findElement(addCategoryBtn).click();
	}

	public String newCatTextDisplay() {
		visiblityOfElement(newCatText);
		return driver.findElement(newCatText).getText();
	}

	public String getaddText() {
		visiblityOfElement(addText);
		return driver.findElement(addText).getText();
	}

	public void addNewCatClick() {
		driver.findElement(addText).click();
	}

	public void setCategoryInput(String cName) {
		visiblityOfElement(categoryInput);
		driver.findElement(categoryInput).sendKeys(cName);
	}

	public void setDescInput(String desc) {
		visiblityOfElement(descInput);
		driver.findElement(descInput).sendKeys(desc);
	}

	public void selectTaxClick() {
		visiblityOfElement(selectTax);
		driver.findElement(selectTax).click();
	}

	public String selectTaxText() {
		return driver.findElement(selectTax).getText();

	}

	public void taxSelect(String tax_name) {
		WebElement tax = driver.findElement(By.xpath("//span[normalize-space()='" + tax_name + "']"));
		tax.click();
	}

	public boolean isUsePointsChecked() {
		return driver.findElement(usePointsCheckbox).isSelected();
	}

	public boolean isEarnPointsChecked() {
		return driver.findElement(earnPointsCheckbox).isSelected();
	}

	public boolean isLotteryChecked() {
		return driver.findElement(lotteryCheckbox).isSelected();
	}

	public boolean isEbtChecked() {
		return driver.findElement(ebtCheckbox).isSelected();
	}

	public void clickUsePoints() {
		elementClick(usePointsCheckbox);
		driver.findElement(usePointsCheckbox).click();
	}

	public void clickEarnPoints() {
		elementClick(earnPointsCheckbox);
		driver.findElement(earnPointsCheckbox).click();
	}

	public void clickEbt() {
		elementClick(ebtCheckbox);
		driver.findElement(ebtCheckbox).click();
	}
}