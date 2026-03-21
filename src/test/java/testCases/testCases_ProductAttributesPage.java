package testCases;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utilities.DataGenerator;
import utilities.baseClass;

/**
 * Product Attributes (/merchants/attributes). Unlike Brands/Tags: no search
 * bar, no row delete.
 */
public class testCases_ProductAttributesPage extends baseClass {

	public void navigateToProductAttributes() {
		merchantLogin.setStoreName(p.getProperty("merchantStoreName"));
		merchantLogin.setUserName(p.getProperty("merchantUserName"));
		merchantLogin.setPassword(p.getProperty("merchantPassword"));

		merchantLogin.loginBtnClick();
		Assert.assertTrue(dashboard.dashboard_titleDisplay(), "DashBoard Title is not displayed");

		dashboard.sideMenuVisible();
		Assert.assertEquals(dashboard.inventoryMenuText(), "Inventory");
		dashboard.inventoryMenuClick();
		Assert.assertEquals(dashboard.attributesMenuText(), "Product Attributes");
		dashboard.attributesClick();
	}

	@BeforeMethod
	public void logTestName(Method method) {
		System.out.println("====== Running Test: " + method.getName() + " ======");
	}

	@Test(priority = 1)
	public void productAttributesPageLoadsWithBusinessRules() {
		navigateToProductAttributes();
		Assert.assertEquals(productAttributes.attributesHeaderText(), "Product Attributes",
				"Page title should be Product Attributes");
		int count = productAttributes.attributeRowCount();
		Assert.assertTrue(count > 0, "Attribute row count must be greater than 0, was: " + count);
		System.out.println("Present attributes: " + count);

		productAttributes.assertDeleteControlNotPresentInAttributeTable();
	}

	@Test(priority = 2)
	public void addAttributeDialogHeaderAndMandatoryValidation() throws InterruptedException {
		// navigateToProductAttributes();
		productAttributes.addAttributeButtonClick();
		Assert.assertTrue(productAttributes.AddattributeDisplay());
		Assert.assertTrue(productAttributes.AddAttributeText().contains("Add Attribute"));
		productAttributes.addSubmitClick();
		productAttributes.waitForErrorMessageVisible();
		Assert.assertTrue(productAttributes.isErrorMessageDisplayed(),
				"Validation should show when attribute title is empty");
		productAttributes.cancelBtnClick();
	}

	@Test(priority = 3)
	public void firstAttributeRowHasTitleAndEditOpensDialog() throws InterruptedException {
		// navigateToProductAttributes();
		String title = productAttributes.firstAttributeTitleInTable();
		Assert.assertFalse(title.isEmpty(), "First attribute title should not be empty");
		productAttributes.presentAttribute(title);

		productAttributes.clickEditForAttribute(title);
		productAttributes.waitForAttributeDialog();
		productAttributes.cancelBtnClick();
	}

	@Test(priority = 4)
	public void addNewAttribute() throws InterruptedException {
		// navigateToProductAttributes();
		String name = DataGenerator.generateRandomAttributeName("Attr");
		productAttributes.addAttributeButtonClick();
		productAttributes.setAttributeName(name);
		productAttributes.addSubmitClick();
		productAttributes.assertAddedSuccessfullyDisplayed();
		productAttributes.presentAttribute(name);
	}
}
