package com.pointrlab.testscript;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.pointrlab.dataproviders.DataProviders;
import com.pointrlab.selenium.framework.BaseTest;
import com.pointrlab.selenium.framework.Utilities;

public class MapDesigner extends BaseTest {

	@Factory(dataProvider = "Config", dataProviderClass = DataProviders.class)
	public MapDesigner(String browser) {
		super(browser);
	}

	/**
	 * Verify that drawing icon are displayed on Map.
	 * 
	 * @throws Exception
	 */
	@Test
	void test01_VerifyDrawingIconsAreAvailable() throws Exception {
		String poiType = "Zone";
		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("select poi layer");
		mapDesignerPage.selectPOILayer(poiType);

		reportLog("click on + button to add layer");
		mapDesignerPage.clickOnPlusButtonToAddLayer();

		reportLog("verify draw poi tool bar options");
		mapDesignerPage.verifyPOIDrawToolBarOptions();

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	/**
	 * Verify User Is Able To Add Polygon On Solid Wall Layer
	 * 
	 * @throws Exception
	 */
	@Test
	void test02_VerifyUserIsAbleToAddPolygonOnSolidWallLayer() throws Exception {

		String poiLayer = "Solid Wall";
		String poiName = "name" + Utilities.getTimeStamp();
		String poiDescription = "description" + Utilities.getTimeStamp();
		String poiKeywords = "key1, key2" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("click on arrow icon on left of select poi layer dropdown");
		mapDesignerPage.clickOnSelectLayerArrowIcon();

		reportLog("delete all available poi");
		mapDesignerPage.deleteCreatedShapesOnPOILayers();

		reportLog("add polygon on solid wall layer");
		mapDesignerPage.addPOI(poiLayer);

		reportLog("enter poi information");
		mapDesignerPage.enterPOIInfo(poiName, poiDescription, poiKeywords);

		reportLog("click on poi save button");
		mapDesignerPage.clickOnPOISaveBtn();

		reportLog("verify added poi");
		mapDesignerPage.verifyAddedPOI(poiLayer, poiName, poiDescription,
				poiKeywords);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	/**
	 * Verify User Is Able To Add Polygon On Transparent Wall Layer
	 * 
	 * @throws Exception
	 */
	@Test
	void test03_VerifyUserIsAbleToAddPolygonOnTransparentWallLayer()
			throws Exception {

		String poiLayer = "Transparent Wall";
		String poiName = "name" + Utilities.getTimeStamp();
		String poiDescription = "description" + Utilities.getTimeStamp();
		String poiKeywords = "key1, key2" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("click on arrow icon on left of select poi layer dropdown");
		mapDesignerPage.clickOnSelectLayerArrowIcon();

		reportLog("delete all available poi");
		mapDesignerPage.deleteCreatedShapesOnPOILayers();

		reportLog("add polygon on Transparent Wall layer");
		mapDesignerPage.addPOI(poiLayer);

		reportLog("enter poi information");
		mapDesignerPage.enterPOIInfo(poiName, poiDescription, poiKeywords);

		reportLog("click on poi save button");
		mapDesignerPage.clickOnPOISaveBtn();

		reportLog("verify added poi");
		mapDesignerPage.verifyAddedPOI(poiLayer, poiName, poiDescription,
				poiKeywords);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	/**
	 * Verify User Is Able To Update Added Polygon On Solid Wall Layer
	 * 
	 * @throws Exception
	 */
	@Test
	void test04_VerifyUserIsAbleToUpdateAddedPolygonOnSolidWallLayer()
			throws Exception {

		String poiLayer = "Solid Wall";
		String poiName = "name" + Utilities.getTimeStamp();
		String poiDescription = "description" + Utilities.getTimeStamp();
		String poiKeywords = "key1, key2" + Utilities.getTimeStamp();
		String newPoiName = "test" + Utilities.getTimeStamp();
		String newPoiDescription = "test" + Utilities.getTimeStamp();
		String newPoiKeywords = "test key1, key2" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("click on arrow icon on left of select poi layer dropdown");
		mapDesignerPage.clickOnSelectLayerArrowIcon();

		reportLog("delete all available poi");
		mapDesignerPage.deleteCreatedShapesOnPOILayers();

		reportLog("add polygon on solid wall layer");
		mapDesignerPage.addPOI(poiLayer);

		reportLog("enter poi information");
		mapDesignerPage.enterPOIInfo(poiName, poiDescription, poiKeywords);

		reportLog("click on poi save button");
		mapDesignerPage.clickOnPOISaveBtn();

		reportLog("verify added poi");
		mapDesignerPage.verifyAddedPOI(poiLayer, poiName, poiDescription,
				poiKeywords);

		reportLog("update added poi details");
		mapDesignerPage.updateAddedPOI(poiLayer, newPoiName, newPoiDescription,
				newPoiKeywords);

		reportLog("click on update button");
		mapDesignerPage.clickOnUpdateBtn();

		reportLog("verify updated poi details");
		mapDesignerPage.verifyAddedPOI(poiLayer, newPoiName, newPoiDescription,
				newPoiKeywords);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	/**
	 * Verify User Is Able To Update Added Polygon On Transparent Wall Layer
	 * 
	 * @throws Exception
	 */
	@Test
	void test05_VerifyUserIsAbleToUpdateAddedPolygonOnTransparentWallLayer()
			throws Exception {

		String poiLayer = "Transparent Wall";
		String poiName = "name" + Utilities.getTimeStamp();
		String poiDescription = "description" + Utilities.getTimeStamp();
		String poiKeywords = "key1, key2" + Utilities.getTimeStamp();
		String newPoiName = "test" + Utilities.getTimeStamp();
		String newPoiDescription = "test" + Utilities.getTimeStamp();
		String newPoiKeywords = "test key1, key2" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("click on arrow icon on left of select poi layer dropdown");
		mapDesignerPage.clickOnSelectLayerArrowIcon();

		reportLog("delete all available poi");
		mapDesignerPage.deleteCreatedShapesOnPOILayers();

		reportLog("add polygon on transparent wall layer");
		mapDesignerPage.addPOI(poiLayer);

		reportLog("enter poi information");
		mapDesignerPage.enterPOIInfo(poiName, poiDescription, poiKeywords);

		reportLog("click on poi save button");
		mapDesignerPage.clickOnPOISaveBtn();

		reportLog("verify added poi");
		mapDesignerPage.verifyAddedPOI(poiLayer, poiName, poiDescription,
				poiKeywords);

		reportLog("update added poi details");
		mapDesignerPage.updateAddedPOI(poiLayer, newPoiName, newPoiDescription,
				newPoiKeywords);

		reportLog("click on update button");
		mapDesignerPage.clickOnUpdateBtn();

		reportLog("verify updated poi details");
		mapDesignerPage.verifyAddedPOI(poiLayer, newPoiName, newPoiDescription,
				newPoiKeywords);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	/**
	 * Verify User Is Able To Add Polygon On Zone Layer
	 * 
	 * @throws Exception
	 */
	@Test
	void test06_VerifyUserIsAbleToAddPolygonOnZoneLayer() throws Exception {

		String poiLayer = "Zone";
		String poiName = "name" + Utilities.getTimeStamp();
		String poiDescription = "description" + Utilities.getTimeStamp();
		String poiKeywords = "key1, key2" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("click on arrow icon on left of select poi layer dropdown");
		mapDesignerPage.clickOnSelectLayerArrowIcon();

		reportLog("delete all available poi");
		mapDesignerPage.deleteCreatedShapesOnPOILayers();

		reportLog("add polygon on zone layer");
		mapDesignerPage.addPOI(poiLayer);

		reportLog("enter poi information");
		mapDesignerPage.enterPOIInfo(poiName, poiDescription, poiKeywords);

		reportLog("click on poi save button");
		mapDesignerPage.clickOnPOISaveBtn();

		reportLog("verify added poi");
		mapDesignerPage.verifyAddedPOI(poiLayer, poiName, poiDescription,
				poiKeywords);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	/**
	 * Verify User Is Able To Update Added Polygon On Zone Layer
	 * 
	 * @throws Exception
	 */
	@Test
	void test07_VerifyUserIsAbleToUpdateAddedPolygonOnZoneLayer()
			throws Exception {

		String poiLayer = "Zone";
		String poiName = "name" + Utilities.getTimeStamp();
		String poiDescription = "description" + Utilities.getTimeStamp();
		String poiKeywords = "key1, key2" + Utilities.getTimeStamp();
		String newPoiName = "test" + Utilities.getTimeStamp();
		String newPoiDescription = "test" + Utilities.getTimeStamp();
		String newPoiKeywords = "test key1, key2" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("click on arrow icon on left of select poi layer dropdown");
		mapDesignerPage.clickOnSelectLayerArrowIcon();

		reportLog("delete all available poi");
		mapDesignerPage.deleteCreatedShapesOnPOILayers();

		reportLog("add polygon on Zone layer");
		mapDesignerPage.addPOI(poiLayer);

		reportLog("enter poi information");
		mapDesignerPage.enterPOIInfo(poiName, poiDescription, poiKeywords);

		reportLog("click on poi save button");
		mapDesignerPage.clickOnPOISaveBtn();

		reportLog("verify added poi");
		mapDesignerPage.verifyAddedPOI(poiLayer, poiName, poiDescription,
				poiKeywords);

		reportLog("update added poi details");
		mapDesignerPage.updateAddedPOI(poiLayer, newPoiName, newPoiDescription,
				newPoiKeywords);

		reportLog("click on update button");
		mapDesignerPage.clickOnUpdateBtn();

		reportLog("verify updated poi details");
		mapDesignerPage.verifyAddedPOI(poiLayer, newPoiName, newPoiDescription,
				newPoiKeywords);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	/**
	 * Verify User Is Able To Add Polygon On Asset Trigger Layer
	 * 
	 * @throws Exception
	 */
	@Test
	void test08_VerifyUserIsAbleToAddPolygonOnAssetTriggerLayer()
			throws Exception {

		String poiLayer = "Asset Trigger";
		String poiName = "name" + Utilities.getTimeStamp();
		String poiDescription = "description" + Utilities.getTimeStamp();
		String poiKeywords = "key1, key2" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("click on arrow icon on left of select poi layer dropdown");
		mapDesignerPage.clickOnSelectLayerArrowIcon();

		reportLog("delete all available poi");
		mapDesignerPage.deleteCreatedShapesOnPOILayers();

		reportLog("add polygon on Asset Trigger layer");
		mapDesignerPage.addPOI(poiLayer);

		reportLog("enter poi information");
		mapDesignerPage.enterPOIInfo(poiName, poiDescription, poiKeywords);

		reportLog("click on poi save button");
		mapDesignerPage.clickOnPOISaveBtn();

		reportLog("verify added poi");
		mapDesignerPage.verifyAddedPOI(poiLayer, poiName, poiDescription,
				poiKeywords);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	/**
	 * Verify User Is Able To Update Added Polygon On Asset Trigger Layer
	 * 
	 * @throws Exception
	 */
	@Test
	void test09_VerifyUserIsAbleToUpdateAddedPolygonOnAssetTriggerLayer()
			throws Exception {

		String poiLayer = "Asset Trigger";
		String poiName = "name" + Utilities.getTimeStamp();
		String poiDescription = "description" + Utilities.getTimeStamp();
		String poiKeywords = "key1, key2" + Utilities.getTimeStamp();
		String newPoiName = "test" + Utilities.getTimeStamp();
		String newPoiDescription = "test" + Utilities.getTimeStamp();
		String newPoiKeywords = "test key1, key2" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("click on arrow icon on left of select poi layer dropdown");
		mapDesignerPage.clickOnSelectLayerArrowIcon();

		reportLog("delete all available poi");
		mapDesignerPage.deleteCreatedShapesOnPOILayers();

		reportLog("add polygon on Asset Trigger layer");
		mapDesignerPage.addPOI(poiLayer);

		reportLog("enter poi information");
		mapDesignerPage.enterPOIInfo(poiName, poiDescription, poiKeywords);

		reportLog("click on poi save button");
		mapDesignerPage.clickOnPOISaveBtn();

		reportLog("verify added poi");
		mapDesignerPage.verifyAddedPOI(poiLayer, poiName, poiDescription,
				poiKeywords);

		reportLog("update added poi details");
		mapDesignerPage.updateAddedPOI(poiLayer, newPoiName, newPoiDescription,
				newPoiKeywords);

		reportLog("click on update button");
		mapDesignerPage.clickOnUpdateBtn();

		reportLog("verify updated poi details");
		mapDesignerPage.verifyAddedPOI(poiLayer, newPoiName, newPoiDescription,
				newPoiKeywords);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	/**
	 * Verify User Is Able To Add Polygon On External Trigger Layer
	 * 
	 * @throws Exception
	 */
	@Test
	void test10_VerifyUserIsAbleToAddPolygonOnExternalTriggerLayer()
			throws Exception {

		String poiLayer = "External Trigger";
		String poiName = "name" + Utilities.getTimeStamp();
		String poiDescription = "description" + Utilities.getTimeStamp();
		String poiKeywords = "key1, key2" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("click on arrow icon on left of select poi layer dropdown");
		mapDesignerPage.clickOnSelectLayerArrowIcon();

		reportLog("delete all available poi");
		mapDesignerPage.deleteCreatedShapesOnPOILayers();

		reportLog("add polygon on External Trigger layer");
		mapDesignerPage.addPOI(poiLayer);

		reportLog("enter poi information");
		mapDesignerPage.enterPOIInfo(poiName, poiDescription, poiKeywords);

		reportLog("click on poi save button");
		mapDesignerPage.clickOnPOISaveBtn();

		reportLog("verify added poi");
		mapDesignerPage.verifyAddedPOI(poiLayer, poiName, poiDescription,
				poiKeywords);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	/**
	 * Verify User Is Able To Update Added Polygon On External Trigger Layer
	 * 
	 * @throws Exception
	 */
	@Test
	void test11_VerifyUserIsAbleToUpdateAddedPolygonOnExternalTriggerLayer()
			throws Exception {

		String poiLayer = "External Trigger";
		String poiName = "name" + Utilities.getTimeStamp();
		String poiDescription = "description" + Utilities.getTimeStamp();
		String poiKeywords = "key1, key2" + Utilities.getTimeStamp();
		String newPoiName = "test" + Utilities.getTimeStamp();
		String newPoiDescription = "test" + Utilities.getTimeStamp();
		String newPoiKeywords = "test key1, key2" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("click on arrow icon on left of select poi layer dropdown");
		mapDesignerPage.clickOnSelectLayerArrowIcon();

		reportLog("delete all available poi");
		mapDesignerPage.deleteCreatedShapesOnPOILayers();

		reportLog("add polygon on External Trigger layer");
		mapDesignerPage.addPOI(poiLayer);

		reportLog("enter poi information");
		mapDesignerPage.enterPOIInfo(poiName, poiDescription, poiKeywords);

		reportLog("click on poi save button");
		mapDesignerPage.clickOnPOISaveBtn();

		reportLog("verify added poi");
		mapDesignerPage.verifyAddedPOI(poiLayer, poiName, poiDescription,
				poiKeywords);

		reportLog("update added poi details");
		mapDesignerPage.updateAddedPOI(poiLayer, newPoiName, newPoiDescription,
				newPoiKeywords);

		reportLog("click on update button");
		mapDesignerPage.clickOnUpdateBtn();

		reportLog("verify updated poi details");
		mapDesignerPage.verifyAddedPOI(poiLayer, newPoiName, newPoiDescription,
				newPoiKeywords);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	/**
	 * Verify User Is Able To Add Rectangle On Transparent Wall Layer
	 * 
	 * @throws Exception
	 */
	@Test
	public void test12_VerifyUserIsAbleToAddRectangleOnTransparentWallLayer()
			throws Exception {
		String poiLayer = "Transparent Wall";
		String poiName = "name" + Utilities.getTimeStamp();
		String poiDescription = "description" + Utilities.getTimeStamp();
		String poiKeywords = "key1, key2" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("click on arrow icon on left of select poi layer dropdown");
		mapDesignerPage.clickOnSelectLayerArrowIcon();

		reportLog("delete all available poi");
		mapDesignerPage.deleteCreatedShapesOnPOILayers();

		reportLog("add rectangle on Transparent Wall layer");
		mapDesignerPage.addRectangle(poiLayer);

		reportLog("enter poi information");
		mapDesignerPage.enterPOIInfo(poiName, poiDescription, poiKeywords);

		reportLog("click on poi save button");
		mapDesignerPage.clickOnPOISaveBtn();

		reportLog("verify added poi");
		mapDesignerPage.verifyAddedPOI(poiLayer, poiName, poiDescription,
				poiKeywords);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();

	}

	/**
	 * Verify User Is Able To Add Rectangle On Solid Wall Layer
	 * 
	 * @throws Exception
	 */
	@Test
	public void test13_VerifyUserIsAbleToAddRectangleSolidWallLayer()
			throws Exception {
		String poiLayer = "Solid Wall";
		String poiName = "name" + Utilities.getTimeStamp();
		String poiDescription = "description" + Utilities.getTimeStamp();
		String poiKeywords = "key1, key2" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("click on arrow icon on left of select poi layer dropdown");
		mapDesignerPage.clickOnSelectLayerArrowIcon();

		reportLog("delete all available poi");
		mapDesignerPage.deleteCreatedShapesOnPOILayers();

		reportLog("add rectangle on Solid Wall layer");
		mapDesignerPage.addRectangle(poiLayer);

		reportLog("enter poi information");
		mapDesignerPage.enterPOIInfo(poiName, poiDescription, poiKeywords);

		reportLog("click on poi save button");
		mapDesignerPage.clickOnPOISaveBtn();

		reportLog("verify added poi");
		mapDesignerPage.verifyAddedPOI(poiLayer, poiName, poiDescription,
				poiKeywords);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();

	}

	/**
	 * Verify User Is Able To Add Rectangle On Zone Layer
	 * 
	 * @throws Exception
	 */
	@Test
	public void test14_VerifyUserIsAbleToAddRectangleOnZoneLayer()
			throws Exception {
		String poiLayer = "Zone";
		String poiName = "name" + Utilities.getTimeStamp();
		String poiDescription = "description" + Utilities.getTimeStamp();
		String poiKeywords = "key1, key2" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("click on arrow icon on left of select poi layer dropdown");
		mapDesignerPage.clickOnSelectLayerArrowIcon();

		reportLog("delete all available poi");
		mapDesignerPage.deleteCreatedShapesOnPOILayers();

		reportLog("add rectangle on Zone layer");
		mapDesignerPage.addRectangle(poiLayer);

		reportLog("enter poi information");
		mapDesignerPage.enterPOIInfo(poiName, poiDescription, poiKeywords);

		reportLog("click on poi save button");
		mapDesignerPage.clickOnPOISaveBtn();

		reportLog("verify added poi");
		mapDesignerPage.verifyAddedPOI(poiLayer, poiName, poiDescription,
				poiKeywords);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}

	/**
	 * Verify User Is Able To Update Added Rectangle On Solid Wall Layer
	 * 
	 * @throws Exception
	 */
	@Test
	void test15_VerifyUserIsAbleToUpdateAddedRectangleOnSolidWallLayer()
			throws Exception {

		String poiLayer = "Solid Wall";
		String poiName = "name" + Utilities.getTimeStamp();
		String poiDescription = "description" + Utilities.getTimeStamp();
		String poiKeywords = "key1, key2" + Utilities.getTimeStamp();
		String newPoiName = "test" + Utilities.getTimeStamp();
		String newPoiDescription = "test" + Utilities.getTimeStamp();
		String newPoiKeywords = "test key1, key2" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("click on arrow icon on left of select poi layer dropdown");
		mapDesignerPage.clickOnSelectLayerArrowIcon();

		reportLog("delete all available poi");
		mapDesignerPage.deleteCreatedShapesOnPOILayers();

		reportLog("add rectangle on Solid Wall layer");
		mapDesignerPage.addRectangle(poiLayer);

		reportLog("enter poi information");
		mapDesignerPage.enterPOIInfo(poiName, poiDescription, poiKeywords);

		reportLog("click on poi save button");
		mapDesignerPage.clickOnPOISaveBtn();

		reportLog("verify added poi");
		mapDesignerPage.verifyAddedPOI(poiLayer, poiName, poiDescription,
				poiKeywords);

		reportLog("update added poi details");
		mapDesignerPage.updateAddedPOI(poiLayer, newPoiName, newPoiDescription,
				newPoiKeywords);

		reportLog("click on update button");
		mapDesignerPage.clickOnUpdateBtn();

		reportLog("verify updated poi details");
		mapDesignerPage.verifyAddedPOI(poiLayer, newPoiName, newPoiDescription,
				newPoiKeywords);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();

	}

	/**
	 * Verify User Is Able To Update Added Rectangle On Transparent Wall Layer
	 * 
	 * @throws Exception
	 */
	@Test
	void test16_VerifyUserIsAbleToUpdateAddedRectangleOnTransparentWallLayer()
			throws Exception {

		String poiLayer = "Transparent Wall";
		String poiName = "name" + Utilities.getTimeStamp();
		String poiDescription = "description" + Utilities.getTimeStamp();
		String poiKeywords = "key1, key2" + Utilities.getTimeStamp();
		String newPoiName = "test" + Utilities.getTimeStamp();
		String newPoiDescription = "test" + Utilities.getTimeStamp();
		String newPoiKeywords = "test key1, key2" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("click on arrow icon on left of select poi layer dropdown");
		mapDesignerPage.clickOnSelectLayerArrowIcon();

		reportLog("delete all available poi");
		mapDesignerPage.deleteCreatedShapesOnPOILayers();

		reportLog("add rectangle on Transparent Wall layer");
		mapDesignerPage.addRectangle(poiLayer);

		reportLog("enter poi information");
		mapDesignerPage.enterPOIInfo(poiName, poiDescription, poiKeywords);

		reportLog("click on poi save button");
		mapDesignerPage.clickOnPOISaveBtn();

		reportLog("verify added poi");
		mapDesignerPage.verifyAddedPOI(poiLayer, poiName, poiDescription,
				poiKeywords);

		reportLog("update added poi details");
		mapDesignerPage.updateAddedPOI(poiLayer, newPoiName, newPoiDescription,
				newPoiKeywords);

		reportLog("click on update button");
		mapDesignerPage.clickOnUpdateBtn();

		reportLog("verify updated poi details");
		mapDesignerPage.verifyAddedPOI(poiLayer, newPoiName, newPoiDescription,
				newPoiKeywords);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();

	}

	/**
	 * Verify User Is Able To Update Added Rectangle On Zone Layer
	 * 
	 * @throws Exception
	 */
	@Test
	public void test17_VerifyUserIsAbleToUpdateAddedRectangleOnZoneLayer()
			throws Exception {

		String poiLayer = "Zone";
		String poiName = "name" + Utilities.getTimeStamp();
		String poiDescription = "description" + Utilities.getTimeStamp();
		String poiKeywords = "key1, key2" + Utilities.getTimeStamp();
		String newPoiName = "test" + Utilities.getTimeStamp();
		String newPoiDescription = "test" + Utilities.getTimeStamp();
		String newPoiKeywords = "test key1, key2" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("click on arrow icon on left of select poi layer dropdown");
		mapDesignerPage.clickOnSelectLayerArrowIcon();

		reportLog("delete all available poi");
		mapDesignerPage.deleteCreatedShapesOnPOILayers();

		reportLog("add rectangle on Zone layer");
		mapDesignerPage.addRectangle(poiLayer);

		reportLog("enter poi information");
		mapDesignerPage.enterPOIInfo(poiName, poiDescription, poiKeywords);

		reportLog("click on poi save button");
		mapDesignerPage.clickOnPOISaveBtn();

		reportLog("verify added poi");
		mapDesignerPage.verifyAddedPOI(poiLayer, poiName, poiDescription,
				poiKeywords);

		reportLog("update added poi details");
		mapDesignerPage.updateAddedPOI(poiLayer, newPoiName, newPoiDescription,
				newPoiKeywords);

		reportLog("click on update button");
		mapDesignerPage.clickOnUpdateBtn();

		reportLog("verify updated poi details");
		mapDesignerPage.verifyAddedPOI(poiLayer, newPoiName, newPoiDescription,
				newPoiKeywords);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}
	
	/**
	 * Verify User Is Able To Add Rectangle On Asset Trigger Layer
	 * 
	 * @throws Exception
	 */
	@Test
	public void test18_VerifyUserIsAbleToAddRectangleOnAssetTriggerLayer()
			throws Exception {
		String poiLayer = "Asset Trigger";
		String poiName = "name" + Utilities.getTimeStamp();
		String poiDescription = "description" + Utilities.getTimeStamp();
		String poiKeywords = "key1, key2" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("click on arrow icon on left of select poi layer dropdown");
		mapDesignerPage.clickOnSelectLayerArrowIcon();

		reportLog("delete all available poi");
		mapDesignerPage.deleteCreatedShapesOnPOILayers();

		reportLog("add rectangle on Asset Trigger layer");
		mapDesignerPage.addRectangle(poiLayer);

		reportLog("enter poi information");
		mapDesignerPage.enterPOIInfo(poiName, poiDescription, poiKeywords);

		reportLog("click on poi save button");
		mapDesignerPage.clickOnPOISaveBtn();

		reportLog("verify added poi");
		mapDesignerPage.verifyAddedPOI(poiLayer, poiName, poiDescription,
				poiKeywords);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}
	
	/**
	 * Verify User Is Able To Add Rectangle On External Trigger Layer
	 * 
	 * @throws Exception
	 */
	@Test
	public void test19_VerifyUserIsAbleToAddRectangleOnExternalTriggerLayer()
			throws Exception {
		String poiLayer = "External Trigger";
		String poiName = "name" + Utilities.getTimeStamp();
		String poiDescription = "description" + Utilities.getTimeStamp();
		String poiKeywords = "key1, key2" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("click on arrow icon on left of select poi layer dropdown");
		mapDesignerPage.clickOnSelectLayerArrowIcon();

		reportLog("delete all available poi");
		mapDesignerPage.deleteCreatedShapesOnPOILayers();

		reportLog("add rectangle on External Trigger layer");
		mapDesignerPage.addRectangle(poiLayer);

		reportLog("enter poi information");
		mapDesignerPage.enterPOIInfo(poiName, poiDescription, poiKeywords);

		reportLog("click on poi save button");
		mapDesignerPage.clickOnPOISaveBtn();

		reportLog("verify added poi");
		mapDesignerPage.verifyAddedPOI(poiLayer, poiName, poiDescription,
				poiKeywords);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}
	
	/**
	 * Verify User Is Able To Add Rectangle On Trigger Layer
	 * 
	 * @throws Exception
	 */
	@Test
	public void test20_VerifyUserIsAbleToAddRectangleOnTriggerLayer()
			throws Exception {
		String poiLayer = "Trigger";
		String poiName = "name" + Utilities.getTimeStamp();
		String poiDescription = "description" + Utilities.getTimeStamp();
		String poiKeywords = "key1, key2" + Utilities.getTimeStamp();

		reportLog("login into the application");
		homePage = loginPage.login("username", "password");

		reportLog("Verify user is logged in successfully");
		homePage.verifyLogin();

		reportLog("click on map designer");
		mapDesignerPage = homePage.clickOnMapDesigner();

		reportLog("verify map designer page");
		mapDesignerPage.verifyMapDesignerPage();

		reportLog("click on arrow icon for level 1");
		mapDesignerPage.clickOnArrowIcon();

		reportLog("click on arrow icon on left of select poi layer dropdown");
		mapDesignerPage.clickOnSelectLayerArrowIcon();

		reportLog("delete all available poi");
		mapDesignerPage.deleteCreatedShapesOnPOILayers();

		reportLog("add rectangle on Trigger layer");
		mapDesignerPage.addRectangle(poiLayer);

		reportLog("enter poi information");
		mapDesignerPage.enterPOIInfo(poiName, poiDescription, poiKeywords);

		reportLog("click on poi save button");
		mapDesignerPage.clickOnPOISaveBtn();

		reportLog("verify added poi");
		mapDesignerPage.verifyAddedPOI(poiLayer, poiName, poiDescription,
				poiKeywords);

		reportLog("logout from the application");
		loginPage = homePage.logOut();

		reportLog("verify user is logged out from the application");
		loginPage.verifyLoginPage();
	}
}
