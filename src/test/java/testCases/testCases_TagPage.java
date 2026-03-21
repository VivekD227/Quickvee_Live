package testCases;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utilities.DataGenerator;
import utilities.baseClass;

public class testCases_TagPage extends baseClass {

	public void navigateToTags() {
		merchantLogin.setStoreName(p.getProperty("merchantStoreName"));
		merchantLogin.setUserName(p.getProperty("merchantUserName"));
		merchantLogin.setPassword(p.getProperty("merchantPassword"));

		merchantLogin.loginBtnClick();
		Assert.assertTrue(dashboard.dashboard_titleDisplay(), "DashBoard Title is not displayed");

		dashboard.sideMenuVisible();
		Assert.assertEquals(dashboard.inventoryMenuText(), "Inventory");
		dashboard.inventoryMenuClick();
		Assert.assertEquals(dashboard.tagsMenuText(), "Tags");
		dashboard.tagsClick();
	}

	@BeforeMethod
	public void logTestName(Method method) {
		System.out.println("====== Running Test: " + method.getName() + " ======");
	}

	@Test(priority = 1)
	public void tagListingPageLoads() {
		navigateToTags();
		Assert.assertEquals(tag.tagsHeaderText(), "Tags", "Tags page title should match");
		System.out.println("Present Tag: " + tag.tagRowCount());

	}

	@Test(priority = 2)
	public void addNewTag() throws InterruptedException {
		// navigateToTags();
		String newTag = DataGenerator.generateRandomTagName("Tag");
		tag.addTagButtonClick();
		Assert.assertTrue(tag.AddtagDisplay());
		Assert.assertEquals(tag.AddTagText(), "Add Tag");
		tag.setTagName(newTag);
		tag.addSubmitClick();
		tag.assertAddedSuccessfullyDisplayed();
		Assert.assertTrue(tag.addedSuccessfullyText().contains("Added Successfully"));
		tag.presentTag(newTag);
		Thread.sleep(1000);
		tag.searchTag(newTag);
		tag.presentTag(newTag);
		tag.clearSearch();
	}

	//@Test(priority = 3)
	public void searchTagFiltersTable() {
		// navigateToTags();
		String firstName = tag.firstTagNameInTable();
		tag.searchTag(firstName);
		tag.presentTag(firstName);
		tag.clearSearch();
	}

	@Test(priority = 3)
	public void verifyTagNameMandatory() throws InterruptedException {
		tag.addTagButtonClick();
		tag.addSubmitClick();
		tag.waitForErrorMessageVisible();
		Assert.assertTrue(tag.isErrorMessageDisplayed(), "Validation error should show for empty tag name");
		Assert.assertEquals(tag.getErrorMessageText(), "Tag is required");
		tag.cancelBtnClick();
		Thread.sleep(1000);
		String firstName = tag.firstTagNameInTable();
		tag.addTagButtonClick();

		tag.setTagName(firstName);
		tag.addSubmitClick();
		tag.waitForErrorMessageVisible();
		Assert.assertTrue(tag.isErrorMessageDisplayed(), "Validation error should show for empty tag name");
		Assert.assertEquals(tag.getErrorMessageText(), "Tag already exists");

	}

	@Test(priority = 4)
	public void editTagName() throws InterruptedException {
		//navigateToTags();
		String baseName = DataGenerator.generateRandomTagName("TagEd");
		String editedName = DataGenerator.generateRandomTagName("TagEdit");
		tag.addTagButtonClick();
		tag.setTagName(baseName);
		tag.addSubmitClick();
		tag.assertAddedSuccessfullyDisplayed();
		tag.presentTag(baseName);
		tag.clickEditForTag(baseName);
		tag.setTagName(editedName);
		tag.saveBtnClick();
		tag.assertEditSuccessfullyDisplayed();
		tag.presentTag(editedName);

		Thread.sleep(1000);
		tag.searchTag(editedName);
		tag.presentTag(editedName);
		tag.clearSearch();
		System.out.println(editedName);;
		deleteTagWithConfirmation(editedName);

	}

	public void deleteTagWithConfirmation(String deleteName) throws InterruptedException {
	

		tag.clickDeleteForTag(deleteName);
		tag.assertDeleteConfirmationPopupDisplayed();
		tag.clickDeleteConfirmCancelButton();
		tag.presentTag(deleteName);

		tag.clickDeleteForTag(deleteName);
		tag.assertDeleteConfirmationPopupDisplayed();
		tag.clickDeleteConfirmYesButton();
		tag.assertdeleteSuccessfullymsg();
		Assert.assertEquals(tag.deletedSuccessfullyText(), "Deleted Successfully");
		tag.assertTagNotPresent(deleteName);

		tag.searchTag(deleteName);
		tag.assertTagNotPresent(deleteName);
		tag.clearSearch();
	}

}
