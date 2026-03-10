package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.DataGenerator;
import utilities.baseClass;

public class testCases_CategoryPage extends baseClass {
	
	String categoryNames = DataGenerator.generateRandomCategoryName("Cat");
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
		category.clickEarnPoints();
		category.clickEbt();

		Assert.assertTrue(category.isUsePointsChecked(), "Use Points should be checked after clicking");
		Assert.assertTrue(category.isEarnPointsChecked(), "Earn Points should be checked after clicking");
		Assert.assertTrue(category.isEbtChecked(), "EBT/SNAP Eligibility should be checked after clicking");
		Thread.sleep(8000);
	}
}
