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

import io.restassured.http.Headers;
import io.restassured.response.Response;

public class AddBuidingWithSetOfValues extends BaseTest {
	
	private JSONObject buildingJsonObject;

	@BeforeMethod
	public void readJsonFile() throws Exception {
		buildingJsonObject = JsonFileUtil.readJsonData(GlobalConstant.building, BuilingJsonFile.toString(), true);
	}
	
	/**
	 * Test Building API with different-2 set of data 
	 * @param key : key for value like id, name, type etc.
	 * @param value : value of corresponding key
	 * @param httpCode : http response code like 200 or 500
	 * @param errorMsg: error message of response which we need to verify
	 * @throws Exception : Exception
	 */
	@Test(dataProvider = "BuildingTestData", dataProviderClass = APIDataProvider.class)
	public void testAddBuidingWithSetOfValues(String key, String value, int httpCode, String errorMsg) throws Exception {

		Headers headers = getAllHeaders(authenticationToken, "TestUser");

		// Updating the json field value	
		JSONObject jsonObject = JsonFileUtil.updatePoiJsonInput(buildingJsonObject, key, value);
				
		// Send post request
		Response response = given().headers(headers).formParam("jsondata", jsonObject.toJSONString()).formParam("isarray", false)
				.when().post(GlobalConstant.BuildingSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, httpCode, errorMsg);
	}
	
	/**
	  * Sending POST request with Invalid Token
	  * 
	  * @throws Exception
	  */
	 @Test
	 public void testAddBuildingWithInvalidToken() throws Exception {

	  Headers headers = getAllHeaders("7836b48b4n09n4072n094n72", deviceIdentifier);

	  // Updating the description field in JSON
	  JSONObject updatedJSON = JsonFileUtil.updateJsonValue(buildingJsonObject, "description", "Testing Edit end point");

	  // Send post request
	  Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
	    .formParam("isarray", false).when().post(GlobalConstant.BuildingSuffix);

	  // Assertion response
	  RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	 }
	 
	 /**
	  * Sending POST request with blank Token
	  * 
	  * @throws Exception
	  */
	 @Test
	 public void testAddBuildingWithBlankToken() throws Exception {

	  Headers headers = getAllHeaders("", deviceIdentifier);

	  // Updating the description field in JSON
	  JSONObject updatedJSON = JsonFileUtil.updateJsonValue(buildingJsonObject, "description", "Testing Edit end point");
	  
	  // Send post request
	  Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
	    .formParam("isarray", false).when().post(GlobalConstant.BuildingSuffix);

	  // Assertion response
	  RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	 }
	 
	 /**
	  * Sending POST request with blank device identifier
	  * 
	  * @throws Exception
	  */
	 @Test
	 public void testAddBuildingWithBlankDeviceIdentifier() throws Exception {

	  Headers headers = getAllHeaders(authenticationToken, "");

	  // Updating the description field in JSON
	  JSONObject updatedJSON = JsonFileUtil.updateJsonValue(buildingJsonObject, "description", "Testing Edit end point");

	  // Send post request
	  Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
	    .formParam("isarray", false).when().post(GlobalConstant.BuildingSuffix);

	  // Assertion response
	  RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	 }
	 
	 /**
	  * Sending POST request with Invalid device identifier
	  * 
	  * @throws Exception
	  */
	 @Test
	 public void testAddBuildingWithInvalidDeviceIdentifier() throws Exception {

	  Headers headers = getAllHeaders(authenticationToken, "360test");

	  // Updating the description field in JSON
	  JSONObject updatedJSON = JsonFileUtil.updateJsonValue(buildingJsonObject, "description", "Testing Edit end point");

	  // Send post request
	  Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
	    .formParam("isarray", false).when().post(GlobalConstant.BuildingSuffix);

	  // Assertion response
	  RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	 }
	 
	 /**
	  * Sending POST request with isarray true
	  * 
	  * @throws Exception
	  */
	 @Test
	 public void testAddBuildingWithIsArrayTrue() throws Exception {

	  Headers headers = getAllHeaders(authenticationToken, deviceIdentifier);

	  // Updating the description field in JSON
	  JSONObject updatedJSON = JsonFileUtil.updateJsonValue(buildingJsonObject, "description", "Testing Edit end point");

	  // Send post request
	  Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
	    .formParam("isarray", true).when().post(GlobalConstant.BuildingSuffix);

	  // Assertion response
	  RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_INTERNAL_SERVER_ERROR, GlobalConstant.JSON_NOT_PARSE);
	 }

}
