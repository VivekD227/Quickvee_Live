package pageObjects;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.basePage;


public class ProductsPage extends basePage {

	
	public ProductsPage(WebDriver driver) {
		super(driver);
	}

	By productsSectionHeader = By.xpath("(//*[normalize-space()='Products'])[1]");
	By addNewProductBtn = By.xpath("//p[normalize-space()='Add New Product']");
	By backToListingAddProductSpan = By.xpath("//span[normalize-space()='Add Product']");

	By titleInput = By.xpath("//input[@id='title']");
	By categoryDropdownTrigger = By.xpath("//div[@name='category']//div[@class='search-selected-item']");

	By tootTipClick = By.xpath("//div[@aria-label='A few special characters are not allowed: ( ~, /, \\, ,, - )']//*[name()='svg']");
	By muiTooltip = By.xpath("//div[contains(@class,'MuiTooltip-tooltip')]");
	
	By productTableRow = By.xpath("//tbody//tr[contains(@class,'MuiTableRow-root')]");
	By productSearchInput = By.xpath("//input[contains(@placeholder,'Search products')]");

	By categoryListbox = By.xpath("//*[@role='listbox']");
	By categoryOption = By.xpath("//*[@role='listbox']//li");
	By categoryDropdown = By.xpath("//div[@class='options-box custom-scroll']");
	/** Inputs under category field — prefer visible interactable one (not hidden). */
	By categoryPanelSearchInput = By.xpath("//div[contains(@class,'options-box')]//input");
	By categoryScopedInputs = By.xpath("//div[@name='category']//input[not(@type='hidden')]");
	By saveProductBtn = By.xpath("//button[normalize-space()='Save']");
	By addedSuccessfullyMsg = By.xpath("//div[contains(text(),'Added Successfully')]");

	public void waitForProductsListing() {
		visiblityOfElement(productTableRow);
	}
	
	public String muiTooltipText() {
		return driver.findElement(muiTooltip).getText();
	}
	
	public boolean categoryDropdownDisplay() {
		waitForAddProductForm();
		return driver.findElement(categoryDropdown).isDisplayed();
	}
	
	public void tootTipClicked() {
		clickWithJs(tootTipClick);
	}

	public int getProductTableRowCount() throws InterruptedException {
		waitForProductsListing();
		Thread.sleep(2000);
		return driver.findElements(productTableRow).size();
	}

	public By productTitleLinkByName(String productTitle) {
		return By.xpath("//a[normalize-space()='" + productTitle + "']");
	}

	public boolean isProductWithTitlePresent(String productTitle) {
		try {
			return driver.findElement(productTitleLinkByName(productTitle)).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void assertProductPresent(String productTitle) {
		By link = productTitleLinkByName(productTitle);
		visiblityOfElement(link);
		Assert.assertTrue(driver.findElement(link).isDisplayed(),
				"Product link should be visible: " + productTitle);
	}

	public void addNewProductClick() {
		clickWithJs(addNewProductBtn);
	}

	public void backToProductsListingClick() {
		clickWithJs(backToListingAddProductSpan);
	}

	public void waitForAddProductForm() {
		visiblityOfElement(titleInput);
	}

	public void setProductTitle(String title) {
		waitForAddProductForm();
		WebElement el = driver.findElement(titleInput);
		el.click();
		el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		el.sendKeys(title);
	}

	public String getProductTitleValue() {
		waitForAddProductForm();
		return driver.findElement(titleInput).getAttribute("value");
	}

	public void openCategoryDropdown() {
		waitForAddProductForm();
		visiblityOfElement(categoryDropdownTrigger);
		clickWithJs(categoryDropdownTrigger);
	}

	private WebElement firstDisplayedEnabledInput(List<WebElement> candidates) {
		for (WebElement e : candidates) {
			try {
				if (e.isDisplayed() && e.isEnabled()) {
					return e;
				}
			} catch (Exception ignored) {
			}
		}
		return null;
	}

	private void jsClick(WebElement el) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
	}

	/**
	 * Sets category filter text. Picks a visible input under {@code name='category'}; uses JS
	 * value + input events if React does not accept plain sendKeys.
	 */
	private void agentLogSearchCategory(String message, String hypothesisId, int extra) {
		// #region agent log
		try {
			String json = "{\"sessionId\":\"6f24ea\",\"timestamp\":" + System.currentTimeMillis()
					+ ",\"location\":\"ProductsPage.searchCategory\",\"message\":\"" + message
					+ "\",\"data\":{\"n\":" + extra + "},\"hypothesisId\":\"" + hypothesisId + "\"}\n";
			Files.write(Paths.get("debug-6f24ea.log"), json.getBytes(StandardCharsets.UTF_8),
					StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (Exception ignored) {
		}
		// #endregion
	}

	/**
	 * Types into the category dropdown's search field. Never use sendKeys on
	 * {@link #categoryDropdownTrigger} — it is a div and throws
	 * ElementNotInteractableException.
	 */
	public void searchCategory(String cName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryDropdown));

		List<WebElement> inPanel = driver.findElements(categoryPanelSearchInput);
		List<WebElement> inScope = driver.findElements(categoryScopedInputs);
		agentLogSearchCategory("candidate_panel_count", "B", inPanel.size());
		agentLogSearchCategory("candidate_scope_count", "B", inScope.size());

		WebElement input = firstDisplayedEnabledInput(inPanel);
		if (input == null) {
			input = firstDisplayedEnabledInput(inScope);
		}
		if (input == null && !inScope.isEmpty()) {
			input = inScope.get(inScope.size() - 1);
		}
		if (input == null && !inPanel.isEmpty()) {
			input = inPanel.get(inPanel.size() - 1);
		}
		Assert.assertNotNull(input, "No category search input found under name=category / options-box");

		wait.until(ExpectedConditions.visibilityOf(input));
		jsClick(input);
		input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		input.sendKeys(Keys.DELETE);
		input.sendKeys(cName);

		String valueAfter = input.getAttribute("value");
		boolean ok = valueAfter != null && valueAfter.contains(cName);
		if (!ok) {
			agentLogSearchCategory("sendKeys_empty_using_js_fallback", "C",
					valueAfter != null ? valueAfter.length() : -1);
			((JavascriptExecutor) driver).executeScript(
					"var el=arguments[0], v=arguments[1]; el.focus(); el.value=v;"
							+ "el.dispatchEvent(new Event('input',{bubbles:true}));"
							+ "el.dispatchEvent(new Event('change',{bubbles:true}));",
					input, cName);
		}
		String finalVal = input.getAttribute("value");
		boolean finalOk = finalVal != null && finalVal.contains(cName);
		agentLogSearchCategory("searchCategory_done", "A", finalOk ? 1 : 0);
	}

	public void selectCategory(String selectCat) {
		By option = By.xpath("//span[contains(@class,'item') and normalize-space()='" + selectCat + "']");
		visiblityOfElement(option);
		clickWithJs(option);
	}

	public void waitForCategoryDropdownOpen() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryListbox));
	}

	public int getVisibleCategoryOptionCountInDropdown() {
		waitForCategoryDropdownOpen();
		return driver.findElements(categoryOption).size();
	}

	public void selectCategoryByDisplayName(String categoryName) {
		waitForCategoryDropdownOpen();
		By option = By.xpath("//*[@role='listbox']//span[normalize-space()='" + categoryName + "']");
		visiblityOfElement(option);
		clickWithJs(option);
		new Actions(driver).sendKeys(Keys.ESCAPE).perform();
	}

	
//	public void hoverProductTitleField() {
//		waitForAddProductForm();
//		WebElement el = driver.findElement(titleInput);
//		new Actions(driver).moveToElement(el).pause(Duration.ofMillis(400)).perform();
//	}

	
	public void assertAddedSuccessfullyDisplayed() {
		visiblityOfElement(addedSuccessfullyMsg);
		Assert.assertTrue(driver.findElement(addedSuccessfullyMsg).isDisplayed(),
				"Added Successfully should display after saving product");
	}

	public void searchProduct(String text) {
		visiblityOfElement(productSearchInput);
		WebElement el = driver.findElement(productSearchInput);
		el.click();
		el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		el.sendKeys(text);
	}

	public void clearProductSearch() {
		visiblityOfElement(productSearchInput);
		WebElement el = driver.findElement(productSearchInput);
		el.click();
		el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		el.sendKeys(Keys.DELETE);
	}
}
