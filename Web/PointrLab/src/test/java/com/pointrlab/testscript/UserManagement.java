package com.pointrlab.testscript;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.pointrlab.constant.GlobalConstant.FixedValues;
import com.pointrlab.dataproviders.DataProviders;
import com.pointrlab.selenium.framework.BaseTest;
import com.pointrlab.selenium.framework.Utilities;

public class UserManagement extends BaseTest {

	@Factory(dataProvider = "Config", dataProviderClass = DataProviders.class)
	public UserManagement(String browser) {
		super(browser);
	}

	String userName = "testuser" + Utilities.getTimeStamp();
	String emailAddress = "test@gmail.com";

	/**
	 * Verify user is able to add role definition from "Role Management".
	 * 
	 * @throws Exception
	 */

	@Test
	void test01_AddNewRole() throws Exception {
		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on configuration");
		configurePage = homePage.clickOnConfigure();

		reportLog("verify configuration page");
		configurePage.verifyConfigurationPage();

		reportLog("click on user management");
		configurePage.clickOnUserManagementTab();

		reportLog("click on role management");
		configurePage.clickOnRoleManagement();

		reportLog("create new role");
		String randomString = "test" + Utilities.getTimeStamp();
		configurePage.createNewRole(randomString);

		reportLog("verify newly created role");
		configurePage.verifyCreatedRole(randomString);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	/**
	 * Verify user is able to add user definition for added role.
	 * 
	 * @throws Exception
	 */

	@Test
	void test02_AddNewUser() throws Exception {
		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on configuration");
		configurePage = homePage.clickOnConfigure();

		reportLog("verify configuration page");
		configurePage.verifyConfigurationPage();

		reportLog("click on user management");
		configurePage.clickOnUserManagementTab();

		reportLog("click on role management");
		configurePage.clickOnRoleManagement();

		reportLog("create new role");
		String randomString = "test" + Utilities.getTimeStamp();
		configurePage.createNewRole(randomString);

		reportLog("verify newly created role");
		configurePage.verifyCreatedRole(randomString);

		reportLog("click on user management to create new user");
		configurePage.clickOnUserMangement();

		reportLog("create new user");
		configurePage.createNewUser(userName, emailAddress, randomString);

		reportLog("verify newly created user");
		configurePage.verifyCreatedUser(userName);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	/**
	 * Verify user is able to include activities for added role definition.
	 * 
	 * @throws Exception
	 */
	@Test
	void test03_VerifyUserIsAbleToIncludeActivities() throws Exception {
		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on configuration");
		configurePage = homePage.clickOnConfigure();

		reportLog("verify configuration page");
		configurePage.verifyConfigurationPage();

		reportLog("click on user management");
		configurePage.clickOnUserManagementTab();

		reportLog("click on role management");
		configurePage.clickOnRoleManagement();

		reportLog("create new role");
		String randomString = "test" + Utilities.getTimeStamp();
		configurePage.createNewRole(randomString);

		reportLog("verify newly created role");
		configurePage.verifyCreatedRole(randomString);

		reportLog("Select newly created Role user");
		configurePage.clickOnNewRoleUser(randomString);

		reportLog("Select excluded activities option from the option");
		configurePage.selectExcludedActivities(
				FixedValues.ACCOUNTCONTROLLERLOGIN.toString(),
				FixedValues.ROLEMGMT.toString());

		reportLog("Click on arrow button to move the selected excluded option to included options");
		configurePage.clickOnAddButton(FixedValues.ROLEMGMT.toString());

		reportLog("Verify excluded option is displayed in included options");
		configurePage.verifyActivitesAdded(
				FixedValues.ACCOUNTCONTROLLERLOGIN.toString(),
				FixedValues.ROLEMGMT.toString());

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();

	}

	/**
	 * Verify user is able to exclude activities for added role definition.
	 * 
	 * @throws Exception
	 */

	@Test
	void test04_VerifyUserIsAbleToExcludeActivities() throws Exception {
		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on configuration");
		configurePage = homePage.clickOnConfigure();

		reportLog("verify configuration page");
		configurePage.verifyConfigurationPage();

		reportLog("click on user management");
		configurePage.clickOnUserManagementTab();

		reportLog("click on role management");
		configurePage.clickOnRoleManagement();

		reportLog("create new role");
		String randomString = "test" + Utilities.getTimeStamp();
		configurePage.createNewRole(randomString);

		reportLog("verify newly created role");
		configurePage.verifyCreatedRole(randomString);

		reportLog("Select newly created Role user");
		configurePage.clickOnNewRoleUser(randomString);

		reportLog("Select excluded activities option from the option");
		configurePage.selectExcludedActivities(
				FixedValues.ACCOUNTCONTROLLERLOGIN.toString(),
				FixedValues.ROLEMGMT.toString());

		reportLog("Click on arrow button to move the selected excluded option to included options");
		configurePage.clickOnAddButton(FixedValues.ROLEMGMT.toString());

		reportLog("Verify excluded option is displayed in included options");
		configurePage.verifyActivitesAdded(
				FixedValues.ACCOUNTCONTROLLERLOGIN.toString(),
				FixedValues.ROLEMGMT.toString());

		reportLog("select included activities");
		configurePage.selectIncludedActivity(
				FixedValues.ACCOUNTCONTROLLERLOGIN.toString(),
				FixedValues.ROLEMGMT.toString());

		reportLog("click on exclude button");
		configurePage.clickOnExcludeBtn(FixedValues.ROLEMGMT.toString());

		reportLog("verify activity is excluded");
		configurePage.verifyActivityIsExcluded();

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	/**
	 * Verify user is able to add Facilities for user.
	 * 
	 * @throws Exception
	 */

	@Test
	void test05_VerifyUserIsAbleToAddFacilityForUser() throws Exception {
		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on configuration");
		configurePage = homePage.clickOnConfigure();

		reportLog("verify configuration page");
		configurePage.verifyConfigurationPage();

		reportLog("click on user management");
		configurePage.clickOnUserManagementTab();

		reportLog("click on role management");
		configurePage.clickOnRoleManagement();

		reportLog("create new role");
		String randomString = "test" + Utilities.getTimeStamp();
		configurePage.createNewRole(randomString);

		reportLog("verify newly created role");
		configurePage.verifyCreatedRole(randomString);

		reportLog("click on user management to create new user");
		configurePage.clickOnUserMangement();

		reportLog("create new user");
		configurePage.createNewUser(userName, emailAddress, randomString);

		reportLog("verify newly created user");
		configurePage.verifyCreatedUser(userName);

		reportLog("click on facility Mangement");
		configurePage.clickOnFacilityManagement();

		reportLog("select user under facility management");
		configurePage.selectUserUnderFacilityMangement(userName);

		reportLog("Select excluded activities option from the option");
		configurePage.selectExcludedActivities(FixedValues.DEFAULT.toString(),
				FixedValues.FACILITYMGMT.toString());

		reportLog("Click on arrow button to move the selected excluded option to included options");
		configurePage.clickOnAddButton(FixedValues.FACILITYMGMT.toString());

		reportLog("Verify excluded option is displayed in included options");
		configurePage.verifyActivitesAdded(FixedValues.DEFAULT.toString(),
				FixedValues.FACILITYMGMT.toString());

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	/**
	 * Verify user is able to exclude Facilities for user.
	 * 
	 * @throws Exception
	 */
	@Test
	void test06_VerifyUserIsAbleToexcludeFacilityForUser() throws Exception {

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on configuration");
		configurePage = homePage.clickOnConfigure();

		reportLog("verify configuration page");
		configurePage.verifyConfigurationPage();

		reportLog("click on user management");
		configurePage.clickOnUserManagementTab();

		reportLog("click on role management");
		configurePage.clickOnRoleManagement();

		reportLog("create new role");
		String randomString = "test" + Utilities.getTimeStamp();
		configurePage.createNewRole(randomString);

		reportLog("verify newly created role");
		configurePage.verifyCreatedRole(randomString);

		reportLog("click on user management to create new user");
		configurePage.clickOnUserMangement();

		reportLog("create new user");
		configurePage.createNewUser(userName, emailAddress, randomString);

		reportLog("verify newly created user");
		configurePage.verifyCreatedUser(userName);

		reportLog("click on facility Mangement");
		configurePage.clickOnFacilityManagement();

		reportLog("select user under facility management");
		configurePage.selectUserUnderFacilityMangement(userName);

		reportLog("Select excluded activities option from the option");
		configurePage.selectExcludedActivities(FixedValues.DEFAULT.toString(),
				FixedValues.FACILITYMGMT.toString());

		reportLog("Click on arrow button to move the selected excluded option to included options");
		configurePage.clickOnAddButton(FixedValues.FACILITYMGMT.toString());

		reportLog("Verify excluded option is displayed in included options");
		configurePage.verifyActivitesAdded(FixedValues.DEFAULT.toString(),
				FixedValues.FACILITYMGMT.toString());

		reportLog("select included activities");
		configurePage.selectIncludedActivity(FixedValues.DEFAULT.toString(),
				FixedValues.FACILITYMGMT.toString());

		reportLog("click on exclude button");
		configurePage.clickOnExcludeBtn(FixedValues.FACILITYMGMT.toString());

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	/**
	 * verify user is able to update new user
	 * 
	 * @throws Exception
	 */
	@Test
	void test07_VerifyUserIsAbleToUpdateNewlyCreatedUser() throws Exception {

		String roleName = "test" + Utilities.getTimeStamp();
		String newUserName = "test" + Utilities.getTimeStamp();
		String newRoleName = "role" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on configuration");
		configurePage = homePage.clickOnConfigure();

		reportLog("verify configuration page");
		configurePage.verifyConfigurationPage();

		reportLog("click on user management");
		configurePage.clickOnUserManagementTab();

		reportLog("click on role management");
		configurePage.clickOnRoleManagement();

		reportLog("create new role");
		configurePage.createNewRole(roleName);

		reportLog("verify newly created role");
		configurePage.verifyCreatedRole(roleName);

		reportLog("create new role to update user");
		configurePage.createNewRole(newRoleName);

		reportLog("verify newly created role");
		configurePage.verifyCreatedRole(newRoleName);

		reportLog("click on user management to create new user");
		configurePage.clickOnUserMangement();

		reportLog("create new user");
		configurePage.createNewUser(userName, emailAddress, roleName);

		reportLog("verify newly created user");
		configurePage.verifyCreatedUser(userName);

		reportLog("update user");
		configurePage.updateUser(userName, newUserName, newRoleName);

		reportLog("verify updated user details");
		configurePage.verifyUserDetails(newUserName, newRoleName);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	/**
	 * Verify that user is added in facility management section.
	 * @throws Exception
	 */
	@Test
	public void test08_VerifyAddedUserInFacilityManagement() throws Exception {

		String roleName = "test" + Utilities.getTimeStamp();
		
		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on configuration");
		configurePage = homePage.clickOnConfigure();

		reportLog("verify configuration page");
		configurePage.verifyConfigurationPage();

		reportLog("click on user management");
		configurePage.clickOnUserManagementTab();

		reportLog("click on role management");
		configurePage.clickOnRoleManagement();

		reportLog("create new role");
		configurePage.createNewRole(roleName);

		reportLog("verify newly created role");
		configurePage.verifyCreatedRole(roleName);

		reportLog("click on user management to create new user");
		configurePage.clickOnUserMangement();

		reportLog("create new user");
		configurePage.createNewUser(userName, emailAddress, roleName);

		reportLog("verify newly created user");
		configurePage.verifyCreatedUser(userName);

		reportLog("Click on Facility Management");
		configurePage.clickOnFacilityManagement();

		reportLog("Verify user displayed in Facility Management");
		configurePage.verifyFacilityManagementUsers(userName);

	}

	/**
	 * Verify that role is added in User management section.
	 * @throws Exception
	 */
	@Test
	public void test09_VerifyAddedRoleInUserManagement() throws Exception {
		
		String roleName = "test" + Utilities.getTimeStamp();
		
		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on configuration");
		configurePage = homePage.clickOnConfigure();

		reportLog("verify configuration page");
		configurePage.verifyConfigurationPage();

		reportLog("click on user management");
		configurePage.clickOnUserManagementTab();

		reportLog("click on role management");
		configurePage.clickOnRoleManagement();

		reportLog("create new role");
		configurePage.createNewRole(roleName);

		reportLog("verify newly created role");
		configurePage.verifyCreatedRole(roleName);

		reportLog("Click on User Management");
		configurePage.clickOnUserMangement();

		reportLog("Verify role is displayed in User Management page");
		configurePage.verifyUserManagementRoles(roleName);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

}
