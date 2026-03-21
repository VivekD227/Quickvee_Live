package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.basePage;


public class TagPage extends basePage {

	public TagPage(WebDriver driver) {
		super(driver);
	}

	By tagsHeader = By.xpath("//span[normalize-space()='Tags']");
	By addTagBtn = By.xpath("//p[normalize-space()='Add Tag']");
	By searchInput = By.xpath("//input[@id='outlined-adornment-password']");
	By tagTableRow = By.xpath("//tr[contains(@class,'MuiTableRow-root')]");

	By tagNameInputInDialog = By.xpath("//input[@id='size-small-standard']");
	By AddTag = By.xpath("//span[normalize-space()='Add Tag']");
	By addSubmitBtn = By.xpath("//button[normalize-space()='Add']");
	By saveBtn = By.xpath("//button[normalize-space()='Update']");
	By cancelBtn = By.xpath("//button[normalize-space()='Cancel']");

	By addedSuccessfullyMsg = By.xpath("//div[contains(text(),'Added Successfully')]");
	By editSuccessfullyMsg = By.xpath("//div[contains(text(),'Update Successfully')]");
	By deleteSuccessfullymsg = By.xpath("//div[contains(text(),'Deleted Successfully')]");
	By errorMessage = By.xpath("//span[@class='input-error']");

	By deleteConfirmIcon = By.xpath("//img[@alt='Delete-icon']");
	By deleteConfirmMessage = By.xpath("//span[contains(text(),'Are you sure you want to')]");
	By deleteConfirmYesBtn = By.xpath("//button[normalize-space()='Delete']");
	By deleteConfirmCancelBtn = By.xpath("//button[normalize-space()='Cancel']");

	private By editImgForTag(String tagName) {
		return By.xpath("//tr[contains(@class,'MuiTableRow-root') and .//td[normalize-space()='" + tagName
				+ "']]//td[2]//img");
	}

	private By deleteImgForTag(String tagName) {
		return By.xpath("//tr[contains(@class,'MuiTableRow-root') and .//td[normalize-space()='" + tagName
				+ "']]//td[3]//img");
	}

	public String tagsHeaderText() {
		visiblityOfElement(tagsHeader);
		return driver.findElement(tagsHeader).getText();
	}

	public void addTagButtonClick() {
		clickWithJs(addTagBtn);
	}

	public void waitForTagDialog() {
		visiblityOfElement(tagNameInputInDialog);
	}

	public void setTagName(String name) {
		waitForTagDialog();
		WebElement el = driver.findElement(tagNameInputInDialog);
		el.click();
		el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		el.sendKeys(name);
	}

	public void addSubmitClick() {
		elementClick(addSubmitBtn);
		driver.findElement(addSubmitBtn).click();
	}

	public void saveBtnClick() {
		elementClick(saveBtn);
		driver.findElement(saveBtn).click();
	}

	public void cancelBtnClick() {
		elementClick(cancelBtn);
		driver.findElement(cancelBtn).click();
	}

	public void assertAddedSuccessfullyDisplayed() {
		visiblityOfElement(addedSuccessfullyMsg);
		Assert.assertTrue(driver.findElement(addedSuccessfullyMsg).isDisplayed(),
				"Added Successfully message should be displayed");
	}

	public String addedSuccessfullyText() {
		return driver.findElement(addedSuccessfullyMsg).getText();
	}

	public void assertdeleteSuccessfullymsg() {
		visiblityOfElement(deleteSuccessfullymsg);
		Assert.assertTrue(driver.findElement(deleteSuccessfullymsg).isDisplayed(),
				"Deleted Successfully message should be displayed");
	}

	public String deletedSuccessfullyText() {
		return driver.findElement(deleteSuccessfullymsg).getText();
	}

	public void assertEditSuccessfullyDisplayed() {
		visiblityOfElement(editSuccessfullyMsg);
		Assert.assertTrue(driver.findElement(editSuccessfullyMsg).isDisplayed(),
				"Updated Successfully message should be displayed");
	}

	public void searchTag(String text) {
		visiblityOfElement(searchInput);
		WebElement el = driver.findElement(searchInput);
		el.click();
		el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		el.sendKeys(text);
	}

	public String AddTagText() {
		visiblityOfElement(AddTag);
		return driver.findElement(AddTag).getText();
	}

	public boolean AddtagDisplay() {
		return driver.findElement(AddTag).isDisplayed();
	}

	public void clearSearch() {
		visiblityOfElement(searchInput);
		WebElement el = driver.findElement(searchInput);
		el.click();
		el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		el.sendKeys(Keys.DELETE);
	}

	public int tagRowCount() {
		visiblityOfElement(tagTableRow);
		return driver.findElements(tagTableRow).size();
	}

	public String firstTagNameInTable() {
		visiblityOfElement(tagTableRow);
		return driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText().trim();
	}

	public void presentTag(String tagName) {
		By cell = By.xpath("//td[normalize-space()='" + tagName + "']");
		visiblityOfElement(cell);
		Assert.assertTrue(driver.findElement(cell).isDisplayed(), tagName + " tag should be visible in table");
	}

	public void clickEditForTag(String tagName) {
		By edit = editImgForTag(tagName);
		visiblityOfElement(edit);
		clickWithJs(edit);
	}

	public void clickDeleteForTag(String tagName) {
		By del = deleteImgForTag(tagName);
		visiblityOfElement(del);
		clickWithJs(del);
	}

	public void assertDeleteConfirmationPopupDisplayed() {
		visiblityOfElement(deleteConfirmIcon);
		visiblityOfElement(deleteConfirmMessage);
		Assert.assertTrue(driver.findElement(deleteConfirmIcon).isDisplayed(),
				"Delete confirmation icon should be displayed");
		Assert.assertTrue(driver.findElement(deleteConfirmMessage).isDisplayed(),
				"Delete confirmation message should be displayed");
	}

	public void clickDeleteConfirmYesButton() {
		clickWithJs(deleteConfirmYesBtn);
	}

	public void clickDeleteConfirmCancelButton() {
		elementClick(deleteConfirmCancelBtn);
		driver.findElement(deleteConfirmCancelBtn).click();
	}

	public void assertTagNotPresent(String tagName) {
		By cell = By.xpath("//td[normalize-space()='" + tagName + "']");
		try {
			if (driver.findElement(cell).isDisplayed()) {
				Assert.fail(tagName + " should not be present after delete");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			Assert.assertTrue(true, tagName + " successfully removed");
		}
	}

	public void waitForErrorMessageVisible() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
	}

	public boolean isErrorMessageDisplayed() {
		try {
			return driver.findElement(errorMessage).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public String getErrorMessageText() {
		visiblityOfElement(errorMessage);
		return driver.findElement(errorMessage).getText();
	}
}
