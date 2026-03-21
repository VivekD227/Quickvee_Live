package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utilities.basePage;

/**
 * Merchant Inventory &gt; Product Attributes (/merchants/attributes).
 * No search bar; attributes cannot be deleted from this UI.
 */
public class ProductAttributesPage extends basePage {

	public ProductAttributesPage(WebDriver driver) {
		super(driver);
	}

	By attributesHeader = By.xpath("(//*[normalize-space()='Product Attributes'])[1]");
	By addAttributeBtn = By.xpath("//p[contains(normalize-space(),'Add Attribute')]");
	By attributeTableRow = By.xpath("//tr[contains(@class,'MuiTableRow-root')]");

	/** Same id as Brand/Tag search — must NOT exist on Product Attributes page. */
	By brandStyleSearchInput = By.xpath("//input[@id='outlined-adornment-password']");
	By genericSearchPlaceholder = By.xpath("//input[contains(@placeholder,'Search')]");

	By attributeNameInputInDialog = By.xpath("//input[@id='size-small-standard']");
	By AddAttribute = By.xpath("//span[contains(normalize-space(),'Add Attribute')]");
	By addSubmitBtn = By.xpath("//button[normalize-space()='Add']");
	By saveBtn = By.xpath("//button[normalize-space()='Update']");
	By cancelBtn = By.xpath("//button[normalize-space()='Cancel']");

	By addedSuccessfullyMsg = By.xpath("//div[contains(text(),'Added Successfully')]");
	By editSuccessfullyMsg = By.xpath("//div[contains(text(),'Update Successfully')]");
	By errorMessage = By.xpath("//span[@class='input-error']");

	private By editImgForAttribute(String attributeTitle) {
		return By.xpath("//tr[contains(@class,'MuiTableRow-root') and .//td[normalize-space()='" + attributeTitle
				+ "']]//td[last()]//img");
	}

	public String attributesHeaderText() {
		visiblityOfElement(attributesHeader);
		return driver.findElement(attributesHeader).getText().trim();
	}

	public void addAttributeButtonClick() {
		clickWithJs(addAttributeBtn);
	}

	public void waitForAttributeDialog() {
		visiblityOfElement(attributeNameInputInDialog);
	}

	public void setAttributeName(String name) {
		waitForAttributeDialog();
		WebElement el = driver.findElement(attributeNameInputInDialog);
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

	public void assertEditSuccessfullyDisplayed() {
		visiblityOfElement(editSuccessfullyMsg);
		Assert.assertTrue(driver.findElement(editSuccessfullyMsg).isDisplayed(),
				"Update Successfully message should be displayed");
	}

	public String AddAttributeText() {
		visiblityOfElement(AddAttribute);
		return driver.findElement(AddAttribute).getText();
	}

	public boolean AddattributeDisplay() {
		return driver.findElement(AddAttribute).isDisplayed();
	}

	/** Count of data rows in the attributes table (must be &gt; 0 for a valid tenant). */
	public int attributeRowCount() {
		visiblityOfElement(attributeTableRow);
		return driver.findElements(attributeTableRow).size();
	}

	public String firstAttributeTitleInTable() {
		visiblityOfElement(attributeTableRow);
		// Sort column first, Title second (typical layout)
		return driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText().trim();
	}

	public void presentAttribute(String attributeTitle) {
		By cell = By.xpath("//td[normalize-space()='" + attributeTitle + "']");
		visiblityOfElement(cell);
		Assert.assertTrue(driver.findElement(cell).isDisplayed(),
				attributeTitle + " should be visible in table");
	}

	public void clickEditForAttribute(String attributeTitle) {
		By edit = editImgForAttribute(attributeTitle);
		visiblityOfElement(edit);
		clickWithJs(edit);
	}

	
	public void assertDeleteControlNotPresentInAttributeTable() {
		List<WebElement> deleteIcons = driver.findElements(
				By.xpath("//tbody//tr[contains(@class,'MuiTableRow-root')]//img[@alt='delete-icon']"));
		List<WebElement> deleteImgByAlt = driver.findElements(
				By.xpath("//tbody//tr[contains(@class,'MuiTableRow-root')]//img[contains(@alt,'elete')]"));
		Assert.assertTrue(deleteIcons.isEmpty(),
				"Delete icon (delete-icon) must not be present for attributes — found " + deleteIcons.size());
		Assert.assertTrue(deleteImgByAlt.isEmpty(),
				"No delete/trash img in attribute rows — found " + deleteImgByAlt.size());
	}

	public void waitForErrorMessageVisible() {
		org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver,
				java.time.Duration.ofSeconds(10));
		wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(errorMessage));
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
