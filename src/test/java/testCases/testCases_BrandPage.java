package testCases;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utilities.DataGenerator;
import utilities.baseClass;

public class testCases_BrandPage extends baseClass {

	public void navigateToBrands() {
		merchantLogin.setStoreName(p.getProperty("merchantStoreName"));
		merchantLogin.setUserName(p.getProperty("merchantUserName"));
		merchantLogin.setPassword(p.getProperty("merchantPassword"));

		merchantLogin.loginBtnClick();
		Assert.assertTrue(dashboard.dashboard_titleDisplay(), "DashBoard Title is not displayed");

		dashboard.sideMenuVisible();
		Assert.assertEquals(dashboard.inventoryMenuText(), "Inventory");
		dashboard.inventoryMenuClick();
		Assert.assertEquals(dashboard.brandsMenuText(), "Brands");
		dashboard.brandsClick();
	}

	@BeforeMethod
	public void logTestName(Method method) {
		System.out.println("====== Running Test: " + method.getName() + " ======");
	}

	@Test(priority = 1)
	public void brandListingPageLoads() {
		navigateToBrands();
		Assert.assertEquals(brand.brandsHeaderText(), "Brands", "Brands page title should match");
		System.out.println("Present Brand: " + brand.brandRowCount());

	}

	@Test(priority = 2)
	public void addNewBrand() throws InterruptedException {
		// navigateToBrands();
		String newBrand = DataGenerator.generateRandomBrandName("Brand");
		brand.addBrandButtonClick();
		Assert.assertTrue(brand.AddbrandDisplay());
		Assert.assertEquals(brand.AddBrandText(), "Add Brand");
		brand.setBrandName(newBrand);
		brand.addSubmitClick();
		brand.assertAddedSuccessfullyDisplayed();
		Assert.assertTrue(brand.addedSuccessfullyText().contains("Added Successfully"));
		brand.presentBrand(newBrand);
		Thread.sleep(1000);
		brand.searchBrand(newBrand);
		brand.presentBrand(newBrand);
		brand.clearSearch();
	}

	//@Test(priority = 3)
	public void searchBrandFiltersTable() {
		// navigateToBrands();
		String firstName = brand.firstBrandNameInTable();
		brand.searchBrand(firstName);
		brand.presentBrand(firstName);
		brand.clearSearch();
	}

	@Test(priority = 3)
	public void verifyBrandNameMandatory() throws InterruptedException {
		brand.addBrandButtonClick();
		brand.addSubmitClick();
		brand.waitForErrorMessageVisible();
		Assert.assertTrue(brand.isErrorMessageDisplayed(), "Validation error should show for empty brand name");
		Assert.assertEquals(brand.getErrorMessageText(), "Brand is required");
		brand.cancelBtnClick();
		Thread.sleep(1000);
		String firstName = brand.firstBrandNameInTable();
		brand.addBrandButtonClick();

		brand.setBrandName(firstName);
		brand.addSubmitClick();
		brand.waitForErrorMessageVisible();
		Assert.assertTrue(brand.isErrorMessageDisplayed(), "Validation error should show for empty brand name");
		Assert.assertEquals(brand.getErrorMessageText(), "Brand already exists");

	}

	@Test(priority = 4)
	public void editBrandName() throws InterruptedException {
		//navigateToBrands();
		String baseName = DataGenerator.generateRandomBrandName("BrandEd");
		String editedName = DataGenerator.generateRandomBrandName("BrandEdit");
		brand.addBrandButtonClick();
		brand.setBrandName(baseName);
		brand.addSubmitClick();
		brand.assertAddedSuccessfullyDisplayed();
		brand.presentBrand(baseName);
		brand.clickEditForBrand(baseName);
		brand.setBrandName(editedName);
		brand.saveBtnClick();
		brand.assertEditSuccessfullyDisplayed();
		brand.presentBrand(editedName);

		Thread.sleep(1000);
		brand.searchBrand(editedName);
		brand.presentBrand(editedName);
		brand.clearSearch();
		System.out.println(editedName);;
		deleteBrandWithConfirmation(editedName);
		
	}

//	@Test(priority = 5)
	public void deleteBrandWithConfirmation(String deleteName) throws InterruptedException {
	//	navigateToBrands();
//		String toDelete = DataGenerator.generateRandomBrandName(deleteName);
//		brand.addBrandButtonClick();
//		brand.setBrandName(toDelete);
//		brand.addSubmitClick();
//		brand.assertAddedSuccessfullyDisplayed();
//		brand.presentBrand(toDelete);

		brand.clickDeleteForBrand(deleteName);
		brand.assertDeleteConfirmationPopupDisplayed();
		brand.clickDeleteConfirmCancelButton();
		brand.presentBrand(deleteName);

		brand.clickDeleteForBrand(deleteName);
		brand.assertDeleteConfirmationPopupDisplayed();
		brand.clickDeleteConfirmYesButton();
		brand.assertdeleteSuccessfullymsg();
		Assert.assertEquals(brand.deletedSuccessfullyText(), "Deleted Successfully");
		brand.assertBrandNotPresent(deleteName);
		
		brand.searchBrand(deleteName);
		brand.assertBrandNotPresent(deleteName);
		brand.clearSearch();
	}

}
