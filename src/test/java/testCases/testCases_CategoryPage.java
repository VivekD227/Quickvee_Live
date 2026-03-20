package testCases;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utilities.DataGenerator;
import utilities.baseClass;

public class testCases_CategoryPage extends baseClass {
	
	String categoryNames = DataGenerator.generateRandomCategoryName("Cat");
	String cname = DataGenerator.generateRandomCategoryName("Lot");
	public void deleteCode() {
		merchantLogin.setStoreName(p.getProperty("merchantStoreName"));
		merchantLogin.setUserName(p.getProperty("merchantUserName"));
		merchantLogin.setPassword(p.getProperty("merchantPassword"));

		merchantLogin.loginBtnClick();
		Assert.assertTrue(dashboard.dashboard_titleDisplay(), "DashBoard Title is not displayed");

		dashboard.sideMenuVisible();
		String inventoy_text = "Inventory";
		Assert.assertEquals(dashboard.inventoryMenuText(), inventoy_text);
		dashboard.inventoryMenuClick();
		String category_text = "Categories";
		Assert.assertEquals(dashboard.categoryText(), category_text);
		dashboard.categoryClick();
	}

	@BeforeMethod
	public void logTestName(Method method) {
		System.out.println("====== Running Test: " + method.getName() + " ======");
	}
	
	@Test(priority = 1)
	public void categoryList() throws InterruptedException {

		merchantLogin.setStoreName(p.getProperty("merchantStoreName"));
		merchantLogin.setUserName(p.getProperty("merchantUserName"));
		merchantLogin.setPassword(p.getProperty("merchantPassword"));

		merchantLogin.loginBtnClick();
		Assert.assertTrue(dashboard.dashboard_titleDisplay(), "DashBoard Title is not displayed");

		dashboard.sideMenuVisible();
		String inventoy_text = "Inventory";
		Assert.assertEquals(dashboard.inventoryMenuText(), inventoy_text);
		dashboard.inventoryMenuClick();
		String category_text = "Categories";
		Assert.assertEquals(dashboard.categoryText(), category_text);
		dashboard.categoryClick();

		String cat_desc = "In order to use the Quickvee app one Category is required.";
		Assert.assertEquals(category.categoryTextDisaply(), cat_desc);

		String categories = "Categories";
		Assert.assertEquals(category.categoriesTextDisplay(), categories);
		category.checkCategory();
		category.presentCategory("Quickadd");
		category.checkDeleteButtonNotPresent("Quickadd");

		String categoryName = category.firstCategoryName();

		String expectedTooltip = "Click to view the products in this category";
		String actualTooltip = category.hoverAndViewTooltipText();
		Assert.assertEquals(actualTooltip, expectedTooltip, "Tooltip text did not match expected string.");

		category.viewItemClick();
		Thread.sleep(2000);
		String viewName = category.getviewCatName();
		Assert.assertEquals(categoryName, viewName);
		category.closeBtnClick();
	}

	@Test(priority = 2)
	public void newCategory() throws InterruptedException {
		//deleteCode();
		category.addCategoryBtnClick();
		String newName = "Before you can start using Quickvee POS, make sure to create at least one category.";
		Assert.assertEquals(category.newCatTextDisplay(), newName);
		String newTexts = "Add New Category";
		Assert.assertEquals(category.getaddText(), newTexts);

		category.setCategoryInput(categoryNames);
		category.setDescInput("test");
		category.selectTaxClick();
		category.taxSelect("DefaultTax");

		Assert.assertFalse(category.isUsePointsChecked(), "Use Points should be unchecked by default");
		Assert.assertFalse(category.isEarnPointsChecked(), "Earn Points should be unchecked by default");
		Assert.assertFalse(category.isLotteryChecked(), "Lottery should be unchecked by default");
		Assert.assertFalse(category.isEbtChecked(), "EBT/SNAP Eligibility should be unchecked by default");

		category.clickUsePoints();
		Thread.sleep(1000);
		category.clickEarnPoints();
		Thread.sleep(1000);
		category.clickEbt();
		Thread.sleep(3000);
		Assert.assertTrue(category.isUsePointsChecked(), "Use Points should be checked after clicking");
		Assert.assertTrue(category.isEarnPointsChecked(), "Earn Points should be checked after clicking");
		Assert.assertTrue(category.isEbtChecked(), "EBT/SNAP Eligibility should be checked after clicking");
		category.addBtnClick();
		Thread.sleep(2000);
		String success = "Added Successfully";
		category.assertAddedSuccessfullyDisplayed();
		Assert.assertEquals(category.AddedSuccessfullyText(), success);

		category.presentCategory(categoryNames);
		category.assertOnlineAndRegisterCheckmarksChecked();
	}

	@Test(priority = 3)
	public void verifyCategoryNameMandatory() throws InterruptedException {
		//deleteCode();
		
		category.addCategoryBtnClick();

		category.setDescInput("test");
		category.selectTaxClick();
		category.taxSelect("DefaultTax");

		category.addBtnClick();
		Thread.sleep(1000);
		String titleError = "Title is required";
		Assert.assertTrue(category.isErrorMessageDisplayed(), "Error message should be displayed when category name is empty");
		Assert.assertEquals(category.getErrorMessageText(), titleError);
		category.addNewCatClick();
	}

	@Test(priority = 4)
	public void verifyCategoryNameUniqueness() throws InterruptedException {
	//	deleteCode();
		Thread.sleep(2000);
		String existTitle = category.firstCategoryName();
		category.addCategoryBtnClick();
		category.setCategoryInput(existTitle);
		category.setDescInput("test");
		category.selectTaxClick();
		category.taxSelect("DefaultTax");
		category.addBtnClick();
		Thread.sleep(1000);
		String titleError = "The name is Already exist";
		Assert.assertTrue(category.isErrorMessageDisplayed(), "Error message should be displayed for duplicate category name");
		Assert.assertEquals(category.getErrorMessageText(), titleError);
		category.addNewCatClick();

	}

	@Test(priority = 5)
	public void verifyMultipleTaxSelection() throws InterruptedException {
		//deleteCode();
		String multiTaxCategoryName = DataGenerator.generateRandomCategoryName("Cat");
		category.addCategoryBtnClick();

		category.setCategoryInput(multiTaxCategoryName);
		category.setDescInput("test");
		category.selectMultipleTaxes("DefaultTax", "GSTs");
		Thread.sleep(500);

		category.addBtnClick();
		Thread.sleep(2000);
		category.assertAddedSuccessfullyDisplayed();
		category.presentCategory(multiTaxCategoryName);
	}

	@Test(priority = 6)
	public void verifyLotteryCheckboxFunctionality() throws InterruptedException {
		//deleteCode();
		category.addCategoryBtnClick();

		category.setCategoryInput(categoryNames);
		category.setDescInput("test");

		Assert.assertFalse(category.isLotteryChecked(), "Lottery should be unchecked by default");

		category.clickLottery();
		Thread.sleep(500);
		Assert.assertTrue(category.isLotteryChecked(), "Lottery should be checked after clicking");

		Assert.assertFalse(category.doesTaxDropdownOpenOnClick(), "Tax dropdown should not open when Lottery is checked (dropdown disabled)");
		Assert.assertFalse(category.isUsePointsEnabled(), "Use Points should be disabled when Lottery is checked");
		Assert.assertFalse(category.isEarnPointsEnabled(), "Earn Points should be disabled when Lottery is checked");
		Assert.assertFalse(category.isEbtEnabled(), "EBT/SNAP Eligibility should be disabled when Lottery is checked");
		category.addNewCatClick();
	}

	@Test(priority = 7)
	public void addCategoryWithLotteryChecked() throws InterruptedException {
		//deleteCode();
		Thread.sleep(2000);
		category.addCategoryBtnClick();

		category.setCategoryInput(cname);
		category.setDescInput("test");
		
		category.clickLottery();
		Thread.sleep(500);

		category.addBtnClick();
		Thread.sleep(2000);
		category.assertAddedSuccessfullyDisplayed();
		category.presentCategory(cname);
		category.assertOnlineCheckmarkUncheckedForLotteryCategory(categoryNames);
	}

	@Test(priority = 8)
	public void validateCategoryDataOnEdit() throws InterruptedException {
		String catName = DataGenerator.generateRandomCategoryName("Cat");
		String catDesc = "Test description for edit validation";
		category.addCategoryBtnClick();

		category.setCategoryInput(catName);
		category.setDescInput(catDesc);
		category.selectMultipleTaxes("DefaultTax");
		category.clickUsePoints();
		category.clickEarnPoints();
		category.clickEbt();

		category.addBtnClick();
		Thread.sleep(2000);
		category.assertAddedSuccessfullyDisplayed();
		category.presentCategory(catName);

		category.clickEditForCategory(catName);
		Thread.sleep(1500);

		Assert.assertEquals(category.getCategoryInputValue(), catName, "Category name should match on edit");
		Assert.assertEquals(category.getDescInputValue(), catDesc, "Description should match on edit");
		category.assertTaxesSelected("DefaultTax");
		Assert.assertTrue(category.isUsePointsChecked(), "Use Points should be checked on edit");
		Assert.assertTrue(category.isEarnPointsChecked(), "Earn Points should be checked on edit");
		Assert.assertTrue(category.isEbtChecked(), "EBT/SNAP Eligibility should be checked on edit");

		String newCatName = DataGenerator.generateRandomCategoryName("EditCat");
		category.setCategoryInputValue(newCatName);
		category.selectTaxClick();
		category.taxSelect("GSTs");
		Thread.sleep(500);
		category.saveBtnClick();  
		Thread.sleep(2000);
		String editsucess = "Updated Successfully";
		category.assertEditSuccessfullyDisplayed();
		Assert.assertEquals(category.EditedSuccessfullyText(), editsucess);
		category.presentCategory(newCatName);
		category.clickEditForCategory(newCatName);
		Thread.sleep(1500);
		category.assertTaxesSelected("GSTs");
		category.cancelBtnClick();

	}

	@Test(priority = 9)
	public void verifyQuickaddCategoryTitleNotEditable() throws InterruptedException {
		category.presentCategory("Quickadd");
		category.clickEditForCategory("Quickadd");
		Thread.sleep(1500);

		Assert.assertFalse(category.isCategoryInputEditable(),
			"Category title should not be editable when editing Quickadd category");
		category.cancelBtnClick();
	}

	@Test(priority = 10)
	public void verifyDeleteConfirmationPopupDisplayed() throws InterruptedException {
//		deleteCode();
		String catName = DataGenerator.generateRandomCategoryName("DelCat");
		category.addCategoryBtnClick();
		category.setCategoryInput(catName);
		category.setDescInput("test");
		category.selectTaxClick();
		category.taxSelect("DefaultTax");
		category.addBtnClick();
		Thread.sleep(2000);
		category.presentCategory(catName);

		category.clickDeleteForCategory(catName);
		Thread.sleep(500);
		category.assertDeleteConfirmationPopupDisplayed();
		Assert.assertTrue(category.getDeleteConfirmationMessageText().contains("Are you sure you want to"),
			"Confirmation message should contain expected text");
		category.clickDeleteConfirmCancelButton();
		Thread.sleep(500);
		category.presentCategory(catName);
		
		Thread.sleep(1000);
		category.clickDeleteForCategory(catName);
		Thread.sleep(500);
		category.assertDeleteConfirmationPopupDisplayed();
		category.clickDeleteConfirmYesButton();
		Thread.sleep(2000);
		category.assertCategoryNotPresent(catName);
	}

}
