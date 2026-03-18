package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
	
	By saveBtn = By.xpath("//button[normalize-space()='Save']");
	
	By usePointsCheckbox = By.xpath("//label[.//span[text()='Use Points']]//input");
	By earnPointsCheckbox = By.xpath("//label[.//span[text()='Earn Points']]//input");
	By lotteryCheckbox = By.xpath("//label[.//span[text()='Lottery']]//input");
	By ebtCheckbox = By.xpath("//label[.//span[text()='EBT/SNAP Eligibility']]//input");
	
	By usePointsClick = By.xpath("//span[text()='Use Points']");
	By earnPointsClick = By.xpath("//span[text()='Earn Points']");
	By lotteryClick = By.xpath("//span[text()='Lottery']");
	By ebtClick = By.xpath("//span[text()='EBT/SNAP Eligibility']");
	
	By addBtn = By.xpath("//button[normalize-space()='Add']");
	By cancelBtn = By.xpath("//button[normalize-space()='Cancel']");
	By addedSuccessfullyMsg = By.xpath("//div[contains(text(),'Added Successfully')]");
	By errorMessage = By.xpath("//span[@class='error-message']");
	By categoryCheckmarkOnline = By.xpath("(//span[@class='category-checkmark'])[1]");
	By categoryCheckmarkRegister = By.xpath("(//span[@class='category-checkmark'])[4]");
	By editSuccessfullyMsg = By.xpath("//div[contains(text(),'Updated Successfully')]");

	// Delete confirmation popup
	By deleteConfirmIcon = By.xpath("//img[@alt='Delete-icon']");
	By deleteConfirmMessage = By.xpath("//span[contains(text(),'Are you sure you want to')]");
	By deleteConfirmYesBtn = By.xpath("//button[normalize-space()='Delete']");
	By deleteConfirmCancelBtn = By.xpath("//button[normalize-space()='Cancel']");

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
		clickWithJs(closeBtn);
	}

	public void cancelBtnClick() {
		visiblityOfElement(cancelBtn);
		driver.findElement(cancelBtn).click();
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
		clickWithJs(addCategoryBtn);
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
		clickWithJs(addText);
	}

	public void addBtnClick() {
		elementClick(addBtn);
		driver.findElement(addBtn).click();
	}

	public void assertAddedSuccessfullyDisplayed() {
		visiblityOfElement(addedSuccessfullyMsg);
		Assert.assertTrue(driver.findElement(addedSuccessfullyMsg).isDisplayed(), "Added Successfully message should be displayed");
	}
	
	public String AddedSuccessfullyText() {
		return driver.findElement(addedSuccessfullyMsg).getText();
	}
	
	public void assertEditSuccessfullyDisplayed() {
		visiblityOfElement(editSuccessfullyMsg);
		Assert.assertTrue(driver.findElement(editSuccessfullyMsg).isDisplayed(), "Added Successfully message should be displayed");
	}
	
	public String EditedSuccessfullyText() {
		return driver.findElement(editSuccessfullyMsg).getText();
	}

	public void assertOnlineAndRegisterCheckmarksChecked() {
		visiblityOfElement(categoryCheckmarkOnline);
		visiblityOfElement(categoryCheckmarkRegister);
		Assert.assertTrue(driver.findElement(categoryCheckmarkOnline).isDisplayed(), "Online checkmark should be checked automatically");
		Assert.assertTrue(driver.findElement(categoryCheckmarkRegister).isDisplayed(), "Register checkmark should be checked automatically");
	}

	public void assertOnlineCheckmarkUncheckedForLotteryCategory(String categoryName) {
		By onlineCheckmarkInRow = By.xpath("//tr[contains(@class,'MuiTableRow-root') and .//div[normalize-space()='" + categoryName + "']]//span[@class='category-checkmark'][1]");
		try {
			WebElement onlineCheckmark = driver.findElement(onlineCheckmarkInRow);
			Boolean hasSvg = (Boolean) ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return arguments[0].querySelector('svg') !== null;", onlineCheckmark);
			Assert.assertFalse(Boolean.TRUE.equals(hasSvg), "Online checkbox should be unchecked for lottery category (no checkmark SVG)");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			Assert.assertTrue(true, "Online checkmark not present when unchecked - expected for lottery category");
		}
	}

	public void setCategoryInput(String cName) {
		visiblityOfElement(categoryInput);
		driver.findElement(categoryInput).sendKeys(cName);
	}

	/**
	 * Replaces the category input value entirely (for edit flows). Uses Select All + Type
	 * to simulate real user input so React state updates correctly. Prevents catName + newCatName concatenation.
	 */
	public void setCategoryInputValue(String cName) {
		visiblityOfElement(categoryInput);
		WebElement el = driver.findElement(categoryInput);
		el.click();
		el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		el.sendKeys(cName);
	}

	public void clearCategoryInput() {
		visiblityOfElement(categoryInput);
		WebElement el = driver.findElement(categoryInput);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
			"var el = arguments[0]; el.value = ''; el.dispatchEvent(new Event('input', { bubbles: true })); el.dispatchEvent(new Event('change', { bubbles: true }));",
			el);
	}

	public String getErrorMessageText() {
		visiblityOfElement(errorMessage);
		return driver.findElement(errorMessage).getText();
	}

	public boolean isErrorMessageDisplayed() {
		try {
			return driver.findElement(errorMessage).isDisplayed();
		} catch (Exception e) {
			return false;
		}
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
		org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
		actions.sendKeys(org.openqa.selenium.Keys.ESCAPE).perform();
	}

	public void selectMultipleTaxes(String... taxNames) {
		for (String taxName : taxNames) {
			selectTaxClick();
			taxSelect(taxName);
		}
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
		elementClick(usePointsClick);
        driver.findElement(usePointsClick).click();
	}

	public void clickEarnPoints() {
		elementClick(earnPointsClick);
        driver.findElement(earnPointsClick).click();
	}

	public void clickEbt() {
		elementClick(ebtClick);
		driver.findElement(ebtClick).click();
	}

	public void clickLottery() {
		elementClick(lotteryClick);
		driver.findElement(lotteryClick).click();
	}

	public void clickEditForCategory(String categoryName) {
		By editBtnLocator = By.xpath("//tr[contains(@class,'MuiTableRow-root') and .//div[normalize-space()='" + categoryName + "']]//td[5]//div//span//img");
		visiblityOfElement(editBtnLocator);
		driver.findElement(editBtnLocator).click();
	}

	public void clickDeleteForCategory(String categoryName) {
		By deleteBtnLocator = By.xpath("//tr[contains(@class,'MuiTableRow-root') and .//div[normalize-space()='"
				+ categoryName + "']]//img[@alt='delete-icon']");
		visiblityOfElement(deleteBtnLocator);
		driver.findElement(deleteBtnLocator).click();
	}

	public void assertDeleteConfirmationPopupDisplayed() {
		visiblityOfElement(deleteConfirmIcon);
		visiblityOfElement(deleteConfirmMessage);
		Assert.assertTrue(driver.findElement(deleteConfirmIcon).isDisplayed(), "Delete confirmation icon should be displayed");
		Assert.assertTrue(driver.findElement(deleteConfirmMessage).isDisplayed(), "Delete confirmation message should be displayed");
	}

	public String getDeleteConfirmationMessageText() {
		visiblityOfElement(deleteConfirmMessage);
		return driver.findElement(deleteConfirmMessage).getText();
	}

	public void clickDeleteConfirmYesButton() {
		clickWithJs(deleteConfirmYesBtn);
	}

	public void clickDeleteConfirmCancelButton() {
		elementClick(deleteConfirmCancelBtn);
		driver.findElement(deleteConfirmCancelBtn).click();
	}

	public void assertCategoryNotPresent(String categoryName) {
		By categoryLocator = By.xpath("//tr[contains(@class,'MuiTableRow-root')]//td//div[normalize-space()='" + categoryName + "']");
		try {
			boolean isPresent = driver.findElement(categoryLocator).isDisplayed();
			if (isPresent) {
				Assert.fail(categoryName + " category should not be present after delete. FAIL");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			Assert.assertTrue(true, categoryName + " category successfully removed");
		}
	}

	public String getCategoryInputValue() {
		visiblityOfElement(categoryInput);
		return driver.findElement(categoryInput).getAttribute("value");
	}

	public String getDescInputValue() {
		visiblityOfElement(descInput);
		return driver.findElement(descInput).getAttribute("value");
	}

	public void assertTaxesSelected(String... expectedTaxNames) throws InterruptedException {
		selectTaxClick();
		Thread.sleep(500);
		try {
			for (String taxName : expectedTaxNames) {
				By taxOptionLocator = By.xpath("//li[(@role='option' or @role='menuitem') and .//span[normalize-space()='" + taxName + "']]");
				WebElement option = driver.findElement(taxOptionLocator);
				Boolean hasCheckmarkSvg = (Boolean) ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return arguments[0].querySelector('svg') !== null;", option);
				Assert.assertTrue(Boolean.TRUE.equals(hasCheckmarkSvg), "Tax '" + taxName + "' should be selected (checkmark SVG present)");
			}
		} finally {
			new org.openqa.selenium.interactions.Actions(driver).sendKeys(org.openqa.selenium.Keys.ESCAPE).perform();
		}
	}

	public boolean isCategoryInputEditable() {
		try {
			WebElement el = driver.findElement(categoryInput);
			if (!el.isEnabled()) return false;
			if (Boolean.TRUE.equals(Boolean.parseBoolean(el.getAttribute("readonly")))) return false;
			if ("true".equals(el.getAttribute("aria-disabled"))) return false;
			String cls = el.getAttribute("class") != null ? el.getAttribute("class") : "";
			if (cls.contains("Mui-disabled") || cls.contains("disabled")) return false;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isUsePointsEnabled() {
		return driver.findElement(usePointsCheckbox).isEnabled();
	}

	public boolean isEarnPointsEnabled() {
		return driver.findElement(earnPointsCheckbox).isEnabled();
	}

	public boolean isEbtEnabled() {
		return driver.findElement(ebtCheckbox).isEnabled();
	}

	public boolean isSelectTaxEnabled() {
		try {
			WebElement el = driver.findElement(selectTax);
			if (!el.isDisplayed()) return false;
			if (isElementDisabled(el)) return false;
			for (WebElement anc : el.findElements(By.xpath("./ancestor::*"))) {
				if (isElementDisabled(anc)) return false;
			}
			String pointerEvents = (String) ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
				"return window.getComputedStyle(arguments[0]).pointerEvents;", el);
			if ("none".equals(pointerEvents)) return false;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean doesTaxDropdownOpenOnClick() {
		try {
			long menuVisibleBefore = (Long) ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
				"return document.querySelectorAll('ul[role=listbox], ul[role=menu]').length;");
			driver.findElement(selectTax).click();
			Thread.sleep(400);
			long menuVisibleAfter = (Long) ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
				"return document.querySelectorAll('ul[role=listbox], ul[role=menu]').length;");
			new org.openqa.selenium.interactions.Actions(driver).sendKeys(org.openqa.selenium.Keys.ESCAPE).perform();
			return menuVisibleAfter > menuVisibleBefore;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean isElementDisabled(WebElement el) {
		try {
			String ariaDisabled = el.getAttribute("aria-disabled");
			String className = el.getAttribute("class") != null ? el.getAttribute("class") : "";
			return "true".equals(ariaDisabled) || className.contains("Mui-disabled") || className.contains("disabled");
		} catch (Exception e) {
			return false;
		}
	}
	
	public void saveBtnClick(){
		elementClick(saveBtn);
		driver.findElement(saveBtn).click();
	}

}