package com.pointrlab.testscript;


import org.testng.annotations.Factory;
import com.isometrix.pages.HomePage;
import org.testng.annotations.Test;

import com.isometrix.pages.HomePage;
import com.pointrlab.dataproviders.DataProviders;
import com.pointrlab.selenium.framework.BaseTest;
import com.pointrlab.selenium.framework.Configuration;
import com.pointrlab.selenium.framework.ExcelUtils;
import com.pointrlab.selenium.framework.Utilities;

public class LoginScript extends BaseTest {

	@Factory(dataProvider = "Config", dataProviderClass = DataProviders.class)
	public LoginScript(String browser) {
		super(browser);
	}

	// initialize variables
	String randomString = "test" + Utilities.getTimeStamp();
	
	
	

	/**
	 * Verify user is be able to login using a valid user-name and password and
	 * is able to logout from the application.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test01_Login_And_LogOut_With_Valid_Username_And_Password()
			throws Exception {
		System.out.println("jhjsa");
		String gyy = ExcelUtils.getCellData(1, 1);
		 
		String sPassword = ExcelUtils.getCellData(1, 2);
System.out.println("tets");
		Configuration obj = new Configuration("C://Users//abhishekbs//eclipse-workspace//PointrLab//src//test//resources//testdata//POI");
		String str =obj.readApplicationFile("username");
		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
		/*com.isometrix.pages.HomePage obj2 = new HomePage(getWebDriver());
		obj2.*/
	}

	/**
	 * Verify user is not able to login if user left User-name and Password
	 * field blank.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test02_Login_With_Blank_Username_And_Password()
			throws Exception {

		reportLog("login into the application with blank user name and password ");
		loginPage.verifyInvalidLogin("", "");
	}

	/**
	 * Verify user is not able to login if user inputs invalid user-name and
	 * valid password.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test03_Login_With_Invalid_Username_And_Valid_Password()
			throws Exception {

		reportLog("login into the application with invalid username and valid password ");
		loginPage.verifyInvalidLogin(randomString,
				Configuration.readApplicationFile("password"));
	}

	/**
	 * Verify user is not able to login if user inputs valid user-name and
	 * invalid password.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test04_Login_With_Valid_Username_And_Invalid_Password()
			throws Exception {

		reportLog("login into the application with valid username and invalid password ");
		loginPage.verifyInvalidLogin(
				Configuration.readApplicationFile("username"), randomString);
	}
}
