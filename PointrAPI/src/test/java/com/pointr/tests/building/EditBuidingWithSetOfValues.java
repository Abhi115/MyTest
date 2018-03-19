package com.pointr.tests.building;

import static com.pointr.utils.GlobalConstant.FileName.BuilingJsonFile;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pointr.dataprovider.APIDataProvider;
import com.pointr.utils.BaseTest;
import com.pointr.utils.GlobalConstant;
import com.pointr.utils.JsonFileUtil;
import com.pointr.utils.RestUtil;
import com.pointr.utils.Utilities;

import io.restassured.http.Headers;
import io.restassured.response.Response;

public class EditBuidingWithSetOfValues extends BaseTest {

	private JSONObject buildingJsonObject;
	private Headers headers;

	@BeforeMethod
	public void addPoi() throws Exception {
		buildingJsonObject = JsonFileUtil.readJsonData(GlobalConstant.building, BuilingJsonFile.toString(), true);
		headers = getAllHeaders(authenticationToken, "TestUser");

		buildingJsonObject = JsonFileUtil.updateJsonValue(buildingJsonObject, "id", "Poi" + Utilities.getTimeStamp());
		
		// Add building if not exist
		Response response = given().headers(headers).formParam("jsondata", buildingJsonObject.toJSONString()).formParam("isarray", false)
				.when().post(GlobalConstant.BuildingSuffix);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");

	}

	@Test(dataProvider = "BuildingEditData", dataProviderClass = APIDataProvider.class)
	public void testEditBuildingWithSetOfValues(String key, String value, int httpCode, String errorMsg)
			throws Exception {
		
		// Updating the json field value
		JSONObject jsonObject = JsonFileUtil.updateJsonValue(buildingJsonObject, key, value);
		
		// Send post request
		Response response = given().headers(headers).formParam("jsondata", jsonObject.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.EditBuildingSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, httpCode, errorMsg);

	}
	

	/**
	 * Sending POST request with Invalid Token
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEditBuildingWithInvalidToken() throws Exception {

		Headers headers = getAllHeaders("7836b48b4n09n4072n094n72", deviceIdentifier);

		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(buildingJsonObject, "description", "Testing Edit end point");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.EditBuildingSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending POST request with blank Token
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEditBuildingWithBlankToken() throws Exception {

		Headers headers = getAllHeaders("", deviceIdentifier);

		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(buildingJsonObject, "description", "Testing Edit end point");
		
		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.EditBuildingSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending POST request with blank device identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEditBuildingWithBlankDeviceIdentifier() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, "");

		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(buildingJsonObject, "description", "Testing Edit end point");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.EditBuildingSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending POST request with Invalid device identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEditBuildingWithInvalidDeviceIdentifier() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, "360test");

		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(buildingJsonObject, "description", "Testing Edit end point");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.EditBuildingSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending POST request with isarray true
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEditBuildingWithIsArrayTrue() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, deviceIdentifier);

		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(buildingJsonObject, "description", "Testing Edit end point");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", true).when().post(GlobalConstant.EditBuildingSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_BAD_REQUEST, GlobalConstant.JSON_NOT_PARSE);
	}

}
