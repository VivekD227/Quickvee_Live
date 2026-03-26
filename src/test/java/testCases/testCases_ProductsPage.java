package testCases;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utilities.DataGenerator;
import utilities.baseClass;

/**
 * Products listing &amp; non-variant add. Filters on listing are tested
 * manually per team plan.
 */
public class testCases_ProductsPage extends baseClass {

	public void navigateToProducts() {
		merchantLogin.setStoreName(p.getProperty("merchantStoreName"));
		merchantLogin.setUserName(p.getProperty("merchantUserName"));
		merchantLogin.setPassword(p.getProperty("merchantPassword"));

		merchantLogin.loginBtnClick();
		Assert.assertTrue(dashboard.dashboard_titleDisplay(), "DashBoard Title is not displayed");

		dashboard.sideMenuVisible();
		Assert.assertEquals(dashboard.inventoryMenuText(), "Inventory");
		dashboard.inventoryMenuClick();
		Assert.assertEquals(dashboard.productsMenuText(), "Products");
		dashboard.productsClick();
	}

	@BeforeMethod
	public void logTestName(Method method) {
		System.out.println("====== Running Test: " + method.getName() + " ======");
	}

	@Test(priority = 1)
	public void productListingRowCountAndSampleLink() throws InterruptedException {
		navigateToProducts();
		int count = products.getProductTableRowCount();
		System.out.println("Product Count: " + count);
	}

//	@Test(priority = 2)
	public void addProductRandomTitleAndSelectCategoryFromDropdown() throws InterruptedException {
		navigateToProducts();
		dashboard.inventoryMenuClick();
		dashboard.categoryClick();
		String categoryName = category.firstCategoryName();
		dashboard.inventoryMenuClick();
		dashboard.productsClick();

		String randomTitle = DataGenerator.generateRandomProductName("Prod");
		products.addNewProductClick();
		products.setProductTitle(randomTitle);
		products.openCategoryDropdown();
		products.selectCategoryByDisplayName(categoryName);

		Assert.assertEquals(products.getProductTitleValue(), randomTitle,
				"Product title field should show the random name entered");
		System.out.println("Random product title: " + randomTitle + ", category selected: " + categoryName);
	}

	@Test(priority = 3)
	public void addProductFormTitleTooltip() throws InterruptedException {
		//navigateToProducts();
		products.addNewProductClick();
		products.waitForAddProductForm();

		String randomTitle = DataGenerator.generateRandomProductName("Prod");
		products.setProductTitle(randomTitle);
		products.openCategoryDropdown();
		Assert.assertTrue(products.categoryDropdownDisplay());
		String cName = "Quickadd";
		products.searchCategory(cName);
		products.selectCategory(cName);
	}

}
