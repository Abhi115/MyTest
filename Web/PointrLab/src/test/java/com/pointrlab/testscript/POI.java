package com.pointrlab.testscript;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.pointrlab.constant.GlobalConstant.FileNames;
import com.pointrlab.datamodel.POIModel;
import com.pointrlab.dataproviders.DataProviders;
import com.pointrlab.selenium.framework.BaseTest;
import com.pointrlab.selenium.framework.Configuration;
import com.pointrlab.selenium.framework.Utilities;

public class POI extends BaseTest {

	@Factory(dataProvider = "Config", dataProviderClass = DataProviders.class)
	public POI(String browser) {
		super(browser);
	}

	private POIModel poiModel;
	private Configuration propertyReader;

	@BeforeMethod
	void init() throws Exception {
		String randomString = Utilities.randomString(5);
		poiModel = new POIModel();
		propertyReader = new Configuration(FileNames.POIProperties.toString());
		poiModel.setPOIName(propertyReader.readApplicationData("poiName")
				+ randomString);
		poiModel.setPOIDescription(propertyReader
				.readApplicationData("poiDescription") + randomString);
		poiModel.setPOIkeywords(propertyReader.readApplicationData("keywords")
				+ randomString);
		poiModel.setPOIType(propertyReader.readApplicationData("poiType2"));
	}

	@Test
	void test01_Verify_POI_Draw_Tool_Bar_Options() throws Exception {
		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on POI from main menu");
		poiPage = homePage.clickOnPOI();

		reportLog("verify POI page");
		poiPage.verifyPOIPage();

		reportLog("click on add poi button");
		poiPage.clickOnAddPOIBtn();

		reportLog("verify poi draw tool bar options are present");
		poiPage.verifyPOIDrawToolBarOptions();

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	@Test
	void test02_Add_POI_Of_Type_Polygon() throws Exception {
		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on POI from main menu");
		poiPage = homePage.clickOnPOI();

		reportLog("verify POI page");
		poiPage.verifyPOIPage();

		reportLog("click on add poi button");
		poiPage.clickOnAddPOIBtn();

		reportLog("verify poi draw tool bar options are present");
		poiPage.verifyPOIDrawToolBarOptions();

		reportLog("draw a polygon");
		poiPage.drawPOI("Polygon");

		reportLog("add poi data");
		poiPage.enterPOIData(poiModel);
		poiPage.deleteAddedPOI();

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	@Test
	void test03_Delete_POI() throws Exception {
		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on POI from main menu");
		poiPage = homePage.clickOnPOI();

		reportLog("verify POI page");
		poiPage.verifyPOIPage();

		reportLog("click on add poi button");
		poiPage.clickOnAddPOIBtn();

		reportLog("verify poi draw tool bar options are present");
		poiPage.verifyPOIDrawToolBarOptions();

		reportLog("draw a polygon");
		poiPage.drawPOI("Polygon");

		reportLog("add poi data");
		poiPage.enterPOIData(poiModel);

		reportLog("delete added poi");
		poiPage.deleteAddedPOI();

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}
}
