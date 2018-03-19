package com.pointr.tests.mapdesigner;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pointr.dataprovider.APIDataProvider;
import com.pointr.utils.BaseTest;
import com.pointr.utils.GlobalConstant;
import com.pointr.utils.JsonFileUtil;
import com.pointr.utils.RestUtil;
import com.pointr.utils.Utilities;
import static com.pointr.utils.GlobalConstant.FileName.*;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class MapDesigner extends BaseTest {

	private JSONObject mapDesignerJsonObject;
	
	@BeforeMethod
	public void readJsonFile() throws Exception {
		mapDesignerJsonObject = JsonFileUtil.readJsonData(GlobalConstant.mapDesigner, MapDesignerFile.toString(), true);
	}
	
	/**
	 * Create Different-2 type of Map Designer, data will be provided using Data provider
	 * @param key : 
	 * @param json
	 * @param httpCode
	 * @param errorMsg
	 * @throws Exception
	 */
	@Test(dataProvider = "MapDesigner", dataProviderClass = APIDataProvider.class)
	public void testAddAllMapDesignerType(String type, String json, boolean isArray) throws Exception {

		Headers headers = getAllHeaders(authenticationToken, "TestUser");

		// Updating the description field in JSON
		JSONParser parser = new JSONParser();
		Object jsonObject = JsonFileUtil.updateJsonValue(parser.parse(json), "id", "poi" + Utilities.getTimeStamp());
		
		String jsonString = jsonTOString(jsonObject);
		
		// Send post request
		Response response = given().headers(headers).formParam("jsondata", jsonString).formParam("isarray", isArray)
				.when().post(GlobalConstant.MapDesignerSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");
	}
	
	/**
	 * Test MapDesigner with different-2 set of data
	 * @param key :  key for value like id, name, type etc.
	 * @param value: value of corresponding key
	 * @param httpCode: http response code like 200 or 500
	 * @param errorMsg: error message of response which we need to verify
	 * @throws Exception: Exception
	 */
	@Test(dataProvider = "MapDesignerTestData", dataProviderClass = APIDataProvider.class)
	public void testAddMapDesignerWithSetOfValues(String key, String value, int httpCode, String errorMsg) throws Exception
	{
		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		
		// Updating the json field value	
		JSONObject jsonObject = JsonFileUtil.updatePoiJsonInput(mapDesignerJsonObject, key, value);
		
		// Send post request
		Response response = given().headers(headers).formParam("jsondata", jsonObject.toJSONString()).formParam("isarray", false)
						.when().post(GlobalConstant.MapDesignerSuffix);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(response, httpCode, errorMsg);
	}
	
	/**
	  * Sending POST request with Invalid Token
	  * 
	  * @throws Exception
	  */
	 @Test
	 public void testAddMapDesignerWithInvalidToken() throws Exception {

	  Headers headers = getAllHeaders("7836b48b4n09n4072n094n72", deviceIdentifier);

	  // Updating the description field in JSON
	  JSONObject updatedJSON = JsonFileUtil.updateJsonValue(mapDesignerJsonObject, "description", "Testing Edit end point");

	  // Send post request
	  Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
	    .formParam("isarray", false).when().post(GlobalConstant.MapDesignerSuffix);

	  // Assertion response
	  RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	 }
	 
	 /**
	  * Sending POST request with blank Token
	  * 
	  * @throws Exception
	  */
	 @Test
	 public void testAddMapDesignerWithBlankToken() throws Exception {

	  Headers headers = getAllHeaders("", deviceIdentifier);

	  // Updating the description field in JSON
	  JSONObject updatedJSON = JsonFileUtil.updateJsonValue(mapDesignerJsonObject, "description", "Testing Edit end point");
	  
	  // Send post request
	  Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
	    .formParam("isarray", false).when().post(GlobalConstant.MapDesignerSuffix);

	  // Assertion response
	  RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	 }
	 
	 /**
	  * Sending POST request with blank device identifier
	  * 
	  * @throws Exception
	  */
	 @Test
	 public void testAddMapDesignerWithBlankDeviceIdentifier() throws Exception {

	  Headers headers = getAllHeaders(authenticationToken, "");

	  // Updating the description field in JSON
	  JSONObject updatedJSON = JsonFileUtil.updateJsonValue(mapDesignerJsonObject, "description", "Testing Edit end point");

	  // Send post request
	  Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
	    .formParam("isarray", false).when().post(GlobalConstant.MapDesignerSuffix);

	  // Assertion response
	  RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	 }
	 
	 /**
	  * Sending POST request with Invalid device identifier
	  * 
	  * @throws Exception
	  */
	 @Test
	 public void testAddMapDesignerWithInvalidDeviceIdentifier() throws Exception {

	  Headers headers = getAllHeaders(authenticationToken, "360test");

	  // Updating the description field in JSON
	  JSONObject updatedJSON = JsonFileUtil.updateJsonValue(mapDesignerJsonObject, "description", "Testing Edit end point");

	  // Send post request
	  Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
	    .formParam("isarray", false).when().post(GlobalConstant.MapDesignerSuffix);

	  // Assertion response
	  RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	 }
	 
	 /**
	  * Sending POST request with isarray true
	  * 
	  * @throws Exception
	  */
	 @Test
	 public void testAddMapDesignerWithIsArrayTrue() throws Exception {

	  Headers headers = getAllHeaders(authenticationToken, deviceIdentifier);

	  // Updating the description field in JSON
	  JSONObject updatedJSON = JsonFileUtil.updateJsonValue(mapDesignerJsonObject, "description", "Testing Edit end point");

	  // Send post request
	  Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
	    .formParam("isarray", true).when().post(GlobalConstant.MapDesignerSuffix);

	  // Assertion response
	  RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_INTERNAL_SERVER_ERROR, GlobalConstant.JSON_NOT_PARSE);
	 }
	 
	 /**
	  * Edit MapDesigner with different-2 set of data
	  * @param key :  key for value like id, name, type etc.
	  * @param value: value of corresponding key
	  * @param httpCode: http response code like 200 or 500
	  * @param errorMsg: error message of response which we need to verify
	  * @throws Exception: Exception
	  */
	 @Test(dataProvider = "MapDesignerEditTestData", dataProviderClass = APIDataProvider.class)
	 public void testEditMapDesignerWithSetOfValues(String key, String value, int httpCode, String errorMsg) throws Exception
	 {
	  Headers headers = getAllHeaders(authenticationToken, "TestUser");
	  
	  // Updating the json field value 
	  JSONObject jsonObject = JsonFileUtil.updateJsonValue(mapDesignerJsonObject, key, value);
	  
	  // Send put request
	  Response response = given().headers(headers).formParam("jsondata", jsonObject.toJSONString()).formParam("isarray", false)
	      .when().put(GlobalConstant.MapDesignerSuffix);
	  
	  // Assertion response
	  RestUtil.verifyPoiResponseData(response, httpCode, errorMsg);
	 }
	 
	 /**
	  * Sending PUT request with Invalid Token
	  * 
	  * @throws Exception
	  */
	 @Test
	 public void testEditMapDesignerWithInvalidToken() throws Exception {

	  Headers headers = getAllHeaders("7836b48b4n09n4072n094n72", deviceIdentifier);

	  // Updating the description field in JSON
	  JSONObject updatedJSON = JsonFileUtil.updateJsonValue(mapDesignerJsonObject, "description", "Testing Edit end point");

	  // Send post request
	  Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
	    .formParam("isarray", false).when().put(GlobalConstant.MapDesignerSuffix);

	  // Assertion response
	  RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	 }
	 
	 /**
	  * Sending PUT request with blank Token
	  * 
	  * @throws Exception
	  */
	 @Test
	 public void testEditMapDesignerWithBlankToken() throws Exception {

	  Headers headers = getAllHeaders("", deviceIdentifier);

	  // Updating the description field in JSON
	  JSONObject updatedJSON = JsonFileUtil.updateJsonValue(mapDesignerJsonObject, "description", "Testing Edit end point");
	  
	  // Send post request
	  Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
	    .formParam("isarray", false).when().put(GlobalConstant.MapDesignerSuffix);

	  // Assertion response
	  RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	 }
	 
	 /**
	  * Sending PUT request with blank device identifier
	  * 
	  * @throws Exception
	  */
	 @Test
	 public void testEditMapDesignerWithBlankDeviceIdentifier() throws Exception {

	  Headers headers = getAllHeaders(authenticationToken, "");

	  // Updating the description field in JSON
	  JSONObject updatedJSON = JsonFileUtil.updateJsonValue(mapDesignerJsonObject, "description", "Testing Edit end point");

	  // Send post request
	  Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
	    .formParam("isarray", false).when().put(GlobalConstant.MapDesignerSuffix);

	  // Assertion response
	  RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	 }
	 
	 /**
	  * Sending PUT request with Invalid device identifier
	  * 
	  * @throws Exception
	  */
	 @Test
	 public void testEditMapDesignerWithInvalidDeviceIdentifier() throws Exception {

	  Headers headers = getAllHeaders(authenticationToken, "360test");

	  // Updating the description field in JSON
	  JSONObject updatedJSON = JsonFileUtil.updateJsonValue(mapDesignerJsonObject, "description", "Testing Edit end point");

	  // Send post request
	  Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
	    .formParam("isarray", false).when().put(GlobalConstant.MapDesignerSuffix);

	  // Assertion response
	  RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	 }
	 
	 /**
	  * Sending PUT request with isarray true
	  * 
	  * @throws Exception
	  */
	 @Test
	 public void testEditMapDesignerWithIsArrayTrue() throws Exception {

	  Headers headers = getAllHeaders(authenticationToken, deviceIdentifier);

	  // Updating the description field in JSON
	  JSONObject updatedJSON = JsonFileUtil.updateJsonValue(mapDesignerJsonObject, "description", "Testing Edit end point");

	  // Send post request
	  Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
	    .formParam("isarray", true).when().put(GlobalConstant.MapDesignerSuffix);

	  // Assertion response
	  RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_BAD_REQUEST, GlobalConstant.JSON_NOT_PARSE);
	 }
}
