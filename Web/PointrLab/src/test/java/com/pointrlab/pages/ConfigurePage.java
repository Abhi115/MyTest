package com.pointrlab.pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.pointrlab.selenium.framework.BasePage;

public class ConfigurePage extends BasePage {

	public ConfigurePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[@name='userManagement']")
	private WebElement userManagementTab;

	@FindBy(xpath = "//a[contains(text(),'Role Management')]")
	private WebElement roleManagement;

	@FindBy(xpath = "//input[@id='roleName']")
	private WebElement roleNameField;
	@FindBy(xpath = "//form[@id='addNewRole']//button")
	private WebElement addNewButton;

	@FindBy(xpath = "//select[@id='rolesSelect']")
	private WebElement roles;

	@FindBy(xpath = "//li[@id='userNav']/a")
	private WebElement userMangement;

	@FindBy(xpath = "//input[@id='userName']")
	private WebElement userNameField;
	@FindBy(xpath = "//input[@id='userMailAddress']")
	private WebElement emailField;
	@FindBy(xpath = "//select[@id='userRoles']")
	private WebElement userRoles;
	@FindBy(xpath = "//form[@id='addNewUser']//button")
	private WebElement addNewUserButton;

	@FindBy(xpath = "//input[@id='userNameText']")
	private List<WebElement> allUsers;

	@FindBy(xpath = "//select[@name='roles']")
	private WebElement roleList;

	@FindBy(xpath = "//select[@name='otherActivities']")
	private WebElement roleMangementExcludeActivityDropDown;
	@FindBy(xpath = "//select[@id='excludedFacilitySelect']")
	private WebElement facilityMangementExcludeActivityDropDown;

	@FindBy(xpath = "//button[@id='rightArrowButton']")
	private WebElement addActivitiesBtn;
	@FindBy(xpath = "//button[@id='facilityRightArrowButton']")
	private WebElement facilityMangementIncludeBtn;

	@FindBy(xpath = "//select[@name='activitiesSelect']")
	private WebElement roleMangementIncludedActivitesDropDown;
	@FindBy(xpath = "//select[@id='includedFacilitySelect']")
	private WebElement facilityMangementIncludedActivitesDropDown;

	@FindBy(xpath = "//select[@id='activitiesSelect']")
	private WebElement includeActivitiesItemValue;

	@FindBy(xpath = "//button[@id='leftArrowButton']")
	private WebElement roleMgmtExcludeButton;
	@FindBy(xpath = "//button[@id='facilityLeftArrowButton']")
	private WebElement facilityMgmtExcludeButton;

	@FindBy(xpath = "//li[@id='facilityNav']")
	private WebElement facilitymangement;

	@FindBy(xpath = "//select[@name='usersSelect']")
	private WebElement facilityMangementUsers;

	@FindBy(xpath = "//select[@name='usersSelect']")
	private WebElement allFacilityMgmtUsers;

	@FindBy(xpath = "//select[@id='userRoles']")
	private WebElement allUserMgmtRoles;

	/**
	 * verify configuration page
	 */
	public void verifyConfigurationPage() {
		_waitForJStoLoad();
		verifyURL("Configuration");
	}

	/**
	 * click on user management tab
	 */

	public void clickOnUserManagementTab() {
		waitAndClick(userManagementTab);
	}

	/**
	 * click on user management tab
	 */
	public void clickOnRoleManagement() {
		waitAndClick(roleManagement);
	}

	/**
	 * create new role
	 * 
	 * @param roleName
	 */
	public void createNewRole(String roleName) {
		inputText(roleNameField, roleName);
		waitAndClick(addNewButton);
	}

	/**
	 * verify created role
	 * 
	 * @param roleName
	 */
	public void verifyCreatedRole(String roleName) {
		waitForAjaxRequestsToComplete();
		List<WebElement> options = getDropDownOptions(roles);
		Boolean status = false;
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().equals(roleName)) {
				status = true;
				break;
			}
		}
		Assert.assertTrue(status);
	}

	/**
	 * click on user management
	 */
	public void clickOnUserMangement() {
		waitAndClick(userMangement);
	}

	/**
	 * create new user
	 * 
	 * @param userName
	 * @param mailAddress
	 * @param userType
	 */
	public void createNewUser(String userName, String mailAddress,
			String userType) {
		inputText(userNameField, userName);
		inputText(emailField, mailAddress);
		selectDropDownByText(userRoles, userType);
		clickOn(addNewUserButton);
	}

	/**
	 * verify created user
	 * 
	 * @param userName
	 */
	public void verifyCreatedUser(String userName) {
		_waitForJStoLoad();
		Boolean status = false;
		for (int i = 0; i < allUsers.size(); i++) {
			if (allUsers.get(i).getAttribute("value").equals(userName)) {
				status = true;
				break;
			}
		}
		Assert.assertTrue(status);
	}

	/**
	 * click on new user
	 * 
	 * @param roleName
	 */
	public void clickOnNewRoleUser(String roleName) {
		selectDropDownByText(roleList, roleName);
	}

	/**
	 * select excluded activity
	 * 
	 * @param activitiesName
	 * @param managementType
	 */
	public void selectExcludedActivities(String activitiesName,
			String managementType) {
		_waitForJStoLoad();
		WebElement element = null;
		switch (managementType) {
		case "Role Management":
			element = roleMangementExcludeActivityDropDown;
			break;

		case "Facility Management":
			element = facilityMangementExcludeActivityDropDown;
			break;
		default:
			Assert.assertNotNull(element);
		}
		selectDropDownByText(element, activitiesName);
	}

	/**
	 * click on add button
	 * 
	 * @param managementType
	 */
	public void clickOnAddButton(String managementType) {
		WebElement element = null;
		switch (managementType) {
		case "Role Management":
			element = addActivitiesBtn;
			break;

		case "Facility Management":
			element = facilityMangementIncludeBtn;
			break;
		default:
			Assert.assertNotNull(element);
		}
		clickOn(element);
		waitForAjaxRequestsToComplete();

	}

	/**
	 * verify that activities are added
	 * 
	 * @param activitiesName
	 * @param managementType
	 */
	public void verifyActivitesAdded(String activitiesName,
			String managementType) {
		WebElement element = null;
		switch (managementType) {
		case "Role Management":
			element = roleMangementIncludedActivitesDropDown;
			break;

		case "Facility Management":
			element = facilityMangementIncludedActivitesDropDown;
			break;
		default:
			Assert.assertNotNull(element);
		}
		Boolean status = false;
		List<WebElement> options = getDropDownOptions(element);
		for (int i = 0; i <= options.size() - 1; i++) {
			if (options.get(i).getText().equals(activitiesName)) {
				status = true;
				break;
			}
		}
		Assert.assertTrue(status);
	}

	/**
	 * select included activity
	 * 
	 * @param activitiesName
	 * @param managementType
	 */
	public void selectIncludedActivity(String activitiesName,
			String managementType) {
		WebElement element = null;
		switch (managementType) {
		case "Role Management":
			element = includeActivitiesItemValue;
			break;

		case "Facility Management":
			element = facilityMangementIncludedActivitesDropDown;
			break;
		default:
			Assert.assertNotNull(element);
		}
		selectDropDownByText(element, activitiesName);
	}

	/**
	 * Click on Exclude Button
	 * 
	 * @param managementType
	 */
	public void clickOnExcludeBtn(String managementType) {

		WebElement element = null;
		switch (managementType) {
		case "Role Management":
			element = roleMgmtExcludeButton;
			break;

		case "Facility Management":
			element = facilityMgmtExcludeButton;
			break;
		default:
			Assert.assertNotNull(element);
		}
		clickOn(element);
		waitForAjaxRequestsToComplete();
	}

	/**
	 * Verify activity is executed
	 */
	public void verifyActivityIsExcluded() {
		List<WebElement> options = getDropDownOptions(roleMangementIncludedActivitesDropDown);
		if (options.size() > 1) {
			Assert.assertTrue(false);
		}
	}

	/**
	 * click on facility management
	 */
	public void clickOnFacilityManagement() {
		waitAndClick(facilitymangement);
	}

	/**
	 * select user under facility
	 * 
	 * @param userName
	 */
	public void selectUserUnderFacilityMangement(String userName) {
		selectDropDownByText(facilityMangementUsers, userName);
	}

	/**
	 * update newly created user
	 * 
	 * @param userName
	 * @param newUserName
	 * @param newRole
	 */
	public void updateUser(String userName, String newUserName, String newRole) {
		for (int i = 0; i < allUsers.size(); i++) {
			if (allUsers.get(i).getAttribute("value").equals(userName)) {
				i++;
				WebElement editUserNameField = getDriver().findElement(
						By.xpath("(//input[@id='userNameText'])[" + i + "]"));
				WebElement editRoleNameField = getDriver().findElement(
						By.xpath("//table[@id='userGrid']/tbody/tr[" + i
								+ "]//select"));
				WebElement updateBtn = getDriver().findElement(
						By.xpath("//table[@id='userGrid']/tbody/tr[" + i
								+ "]//button"));
				inputText(editUserNameField, newUserName);
				selectDropDownByText(editRoleNameField, newRole);
				waitAndClick(updateBtn);
				break;
			}
		}
	}

	/**
	 * verify user details
	 * 
	 * @param newUserName
	 * @param newRole
	 */
	public void verifyUserDetails(String newUserName, String newRole) {
		_waitForJStoLoad();
		boolean status = false;
		for (int i = 0; i < allUsers.size(); i++) {
			if (allUsers.get(i).getAttribute("value").equals(newUserName)) {
				i++;
				WebElement editRoleNameField = getDriver().findElement(
						By.xpath("//table[@id='userGrid']/tbody/tr[" + i
								+ "]//select"));
				List<WebElement> options = getDropDownOptions(editRoleNameField);
				for (int j = 0; j < options.size(); j++) {
					if (options.get(j).getText().equals(newRole)) {
						Assert.assertTrue(options.get(j).isSelected());
						status = true;
						break;
					}
				}
				break;
			}

		}
		Assert.assertTrue("User is not updated", status);
	}

	/**
	 * Verify user displayed in Facility Management
	 * 
	 * @param userName
	 */
	public void verifyFacilityManagementUsers(String userName) {
		_waitForJStoLoad();
		Boolean status = false;
		List<WebElement> options = getDropDownOptions(allFacilityMgmtUsers);
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().equals(userName)) {
				status = true;
				break;
			}
		}
		Assert.assertTrue(status);

	}

	/**
	 * Verify role is displayed in User Management page
	 * 
	 * @param userName
	 */
	public void verifyUserManagementRoles(String userName) {
		_waitForJStoLoad();
		Boolean status = false;
		List<WebElement> options = getDropDownOptions(allUserMgmtRoles);
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().equals(userName)) {
				status = true;
				break;
			}
		}
		Assert.assertTrue(status);
	}
}
