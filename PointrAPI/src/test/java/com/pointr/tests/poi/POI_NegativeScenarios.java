package com.pointr.tests.poi;

import static com.pointr.utils.GlobalConstant.FileName.PoiJsonData;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pointr.utils.BaseTest;
import com.pointr.utils.GlobalConstant;
import com.pointr.utils.JsonFileUtil;
import com.pointr.utils.RestUtil;
import com.pointr.utils.Utilities;

import io.restassured.http.Headers;
import io.restassured.response.Response;

public class POI_NegativeScenarios extends BaseTest {

	private JSONObject poiJSONObject;

	@BeforeMethod
	public void readJsonFile() throws Exception {
		poiJSONObject = JsonFileUtil.readJsonData(GlobalConstant.poiinput, PoiJsonData.toString(), true);
	}

	/**
	 * Sending POST request with Same POI_Id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test01_POI_POST_EndPoint_Save_AlreadyExist() throws Exception {

		Headers allheaders = getAllHeaders(authenticationToken, deviceIdentifier);
		// reading json object from file
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(poiJSONObject, "id",
				"poi" + Utilities.getRandomNumber(10000,99999));

		// Send post request
		Response response = given().headers(allheaders).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).expect().statusCode(GlobalConstant.HTTP_OK).when()
				.post(GlobalConstant.PoISaveSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");

		// Send post request
		Response second_response = given().headers(getAllHeaders(authenticationToken, deviceIdentifier))
				.formParam("jsondata", updatedJSON.toJSONString()).formParam("isarray", false).when()
				.post(GlobalConstant.PoISaveSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(second_response, GlobalConstant.HTTP_INTERNAL_SERVER_ERROR,
				GlobalConstant.POI_EXIST);

	}

	/**
	 * Sending POST request with Invalid Token
	 * 
	 * @throws Exception
	 */
	@Test
	public void test02_POI_POST_EndPoint_Edit_InvalidToken() throws Exception {

		Headers headers = getAllHeaders("7836b48b4n09n4072n094n72", deviceIdentifier);

		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(poiJSONObject, "description", "Testing Edit end point");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.PoIEditSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}

	/**
	 * Sending POST request with Blank Token
	 * 
	 * @throws Exception
	 */
	@Test
	public void test03_POI_POST_EndPoint_Edit_BlankToken() throws Exception {

		Headers headers = getAllHeaders("", deviceIdentifier);

		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(poiJSONObject, "description", "Testing Edit end point");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.PoIEditSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}

	/**
	 * Sending POST request with Blank Device Identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void test04_POI_POST_EndPoint_Edit_BlankDeviceId() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, "");
		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(poiJSONObject, "description", "Testing Edit end point");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.PoIEditSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}

	/**
	 * Sending POST request with Invalid Device Identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void test05_POI_POST_EndPoint_Edit_InvalidDeviceId() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, "Test");
		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(poiJSONObject, "description", "Testing Edit end point");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.PoIEditSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}

	/**
	 * Sending POST request with Invalid Device Identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void test06_POI_POST_EndPoint_Save_InvalidDeviceId() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, "Test");
		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(poiJSONObject, "description", "Testing Edit end point");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.PoISaveSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}

	/**
	 * Sending POST request with Blank Device Identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void test07_POI_POST_EndPoint_Save_BlankDeviceId() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, "");
		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(poiJSONObject, "description", "Testing Edit end point");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.PoISaveSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}

	/**
	 * Sending POST request with with isArray true
	 * 
	 * @throws Exception
	 */
	@Test
	public void test08_POI_POST_EndPoint_Save_withIsArrayTrue() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(poiJSONObject, "description", "Testing Edit end point");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", true).when().post(GlobalConstant.PoISaveSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_INTERNAL_SERVER_ERROR, GlobalConstant.JSON_NOT_PARSE);

	}

	/**
	 * Sending POST request with isArray true
	 * 
	 * @throws Exception
	 */
	@Test
	public void test09_POI_POST_EndPoint_Edit_withIsArrayTrue() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(poiJSONObject, "description", "Testing Edit end point");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", true).when().post(GlobalConstant.PoIEditSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_BAD_REQUEST, GlobalConstant.JSON_NOT_PARSE);
	}

	/**
	 * Sending POST request with Blank POI Id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test10_POI_POST_EndPoint_Edit_with_Blank_POI_Id() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(poiJSONObject, "id", "");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.PoIEditSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_BAD_REQUEST, GlobalConstant.INVALID_POI);
	}

	/**
	 * Sending POST request with Blank POI Id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test11_POI_POST_EndPoint_Save_with_Blank_POI_Id() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(poiJSONObject, "id", "");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.PoISaveSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_INTERNAL_SERVER_ERROR, GlobalConstant.INVALID_POI);
	}

	/**
	 * Sending POST request with Blank Name
	 * 
	 * @throws Exception
	 */
	@Test
	public void test12_POI_POST_EndPoint_Edit_with_Blank_Name() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(poiJSONObject, "name", "");

		// Update POI Id randomly
		updatedJSON = JsonFileUtil.updateJsonValue(updatedJSON, "id", "poi" + Utilities.getTimeStamp());

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.PoIEditSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_BAD_REQUEST, GlobalConstant.POI_NOT_FOUND);
	}

	/**
	 * Sending POST request with Blank Name
	 * 
	 * @throws Exception
	 */
	@Test
	public void test13_POI_POST_EndPoint_Save_with_Blank_Name() throws Exception {
		Headers headers = getAllHeaders(authenticationToken, "TestUser");

		// Updating the description field in JSON
		JSONObject jsonObject = JsonFileUtil.updateJsonValue(poiJSONObject, "id",
				"poi" + Utilities.getRandomNumber(10000,99999));
		jsonObject = JsonFileUtil.updateJsonValue(jsonObject, "name", "");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", jsonObject.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.PoISaveSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_INTERNAL_SERVER_ERROR,
				GlobalConstant.ENTITY_VALIDATION_ERROR);
	}

	/**
	 * Sending POST request with Type 1
	 * 
	 * @throws Exception
	 */
	@Test
	public void test14_POI_POST_EndPoint_Edit_with_Type_One() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		// Updating the description field in JSON
		JSONObject jsonObject = JsonFileUtil.updateJsonValue(poiJSONObject, "type","1");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", jsonObject.toJSONString()).formParam("isarray", false)
				.when().post(GlobalConstant.PoIEditSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_BAD_REQUEST, GlobalConstant.TRIGGER_VALIDATION);
	}

	/**
	 * Sending POST request with Type 2
	 * 
	 * @throws Exception
	 */
	@Test
	public void test15_POI_POST_EndPoint_Edit_with_Type_Two() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		// Updating the description field in JSON
		JSONObject jsonObject = JsonFileUtil.updateJsonValue(poiJSONObject, "type","2");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", jsonObject.toJSONString()).formParam("isarray", false)
				.when().post(GlobalConstant.PoIEditSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_BAD_REQUEST, GlobalConstant.SOLIDWALL_VALIDATION);
	}

	/**
	 * Sending POST request with Type 1
	 * 
	 * @throws Exception
	 */
	@Test
	public void test16_POI_POST_EndPoint_Save_with_Type_One() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		// Updating the description field in JSON
		JSONObject jsonObject = JsonFileUtil.updateJsonValue(poiJSONObject, "type","1");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", jsonObject.toJSONString()).formParam("isarray", false)
				.when().post(GlobalConstant.PoISaveSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_INTERNAL_SERVER_ERROR,
				GlobalConstant.TRIGGER_VALIDATION);
	}

	/**
	 * Sending POST request with Type 2
	 * 
	 * @throws Exception
	 */
	@Test
	public void test17_POI_POST_EndPoint_Save_with_Type_Two() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		// Updating the description field in JSON
		JSONObject jsonObject = JsonFileUtil.updateJsonValue(poiJSONObject, "type","2");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", jsonObject.toJSONString()).formParam("isarray", false)
				.when().post(GlobalConstant.PoISaveSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_INTERNAL_SERVER_ERROR,
				GlobalConstant.SOLIDWALL_VALIDATION);
	}
	
	/**
	  * Sending POST request with Invalid Token
	  * 
	  * @throws Exception
	  */
	 @Test
	 public void test18_POI_POST_With_InvalidToken() throws Exception {

	  Headers headers = getAllHeaders("7836b48b4n09n4072n094n72", deviceIdentifier);

	  // Updating the description field in JSON
	  JSONObject updatedJSON = JsonFileUtil.updateJsonValue(poiJSONObject, "description", "Testing Edit end point");

	  // Send post request
	  Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
	    .formParam("isarray", false).when().post(GlobalConstant.PoISaveSuffix);

	  // Assertion response
	  RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	 }
	 
	 /**
	  * Sending POST request with blank Token
	  * 
	  * @throws Exception
	  */
	 @Test
	 public void test19_POI_POST_With_BlankToken() throws Exception {

	  Headers headers = getAllHeaders("", deviceIdentifier);

	  // Updating the description field in JSON
	  JSONObject updatedJSON = JsonFileUtil.updateJsonValue(poiJSONObject, "description", "Testing Edit end point");
	  
	  // Send post request
	  Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
	    .formParam("isarray", false).when().post(GlobalConstant.PoISaveSuffix);

	  // Assertion response
	  RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	 }
	
}
