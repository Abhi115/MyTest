package com.pointr.tests.building;

import static com.pointr.utils.GlobalConstant.FileName.BuilingJsonFile;
import static io.restassured.RestAssured.given;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pointr.utils.BaseTest;
import com.pointr.utils.GlobalConstant;
import com.pointr.utils.JsonFileUtil;
import com.pointr.utils.RestUtil;
import com.pointr.utils.Utilities;

public class BuildingPublish extends BaseTest{
	
	private JSONObject buildingJsonObject;

	@BeforeMethod
	public void readJsonFile() throws Exception {
		buildingJsonObject = JsonFileUtil.readJsonData(GlobalConstant.building, BuilingJsonFile.toString(), true);
	}
	
	/**
	 * Sending POST request for adding and publishing the building
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_Building_Publish() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, deviceIdentifier);
		String randomPoiId = "poi" + Utilities.getTimeStamp();

		// Updating the id field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(buildingJsonObject, "id", randomPoiId);

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.BuildingSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");

		// Send post request for publishing the building
		response = given().headers(headers).formParam("poid", randomPoiId).when().post(GlobalConstant.PublishBuilderSuffix);

		// Assertion response after publishing the building
		//RestUtil.verifyPublishResponse(response);
	}
	
	/**
	 * Sending POST request for adding and publishing the POI with invalid token
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_Publish_Building_With_Invalid_Token() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, deviceIdentifier);
		String randomPoiId = "poi" + Utilities.getTimeStamp();

		// Updating the id field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(buildingJsonObject, "id", randomPoiId);

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.BuildingSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");

		// Send post request for publishing the building
		Headers invalidHeaders = getAllHeaders("7836b48b4n09n4072n094n72", deviceIdentifier);
		response = given().headers(invalidHeaders).formParam("poid", randomPoiId).when().post(GlobalConstant.PublishBuilderSuffix);

		// Assertion response after publishing the building
		RestUtil.verifyPoiResponseData(response,GlobalConstant.HTTP_UNAUTHORIZED,GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending POST request for adding and publishing the POI with blank token
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_Publish_Building_With_Blank_Token() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, deviceIdentifier);
		String randomPoiId = "poi" + Utilities.getTimeStamp();

		// Updating the id field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(buildingJsonObject, "id", randomPoiId);

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.BuildingSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");

		// Send post request for publishing the building
		Headers invalidHeaders = getAllHeaders("", deviceIdentifier);
		response = given().headers(invalidHeaders).formParam("poid", randomPoiId).when().post(GlobalConstant.PublishBuilderSuffix);

		// Assertion response after publishing the building
		RestUtil.verifyPoiResponseData(response,GlobalConstant.HTTP_UNAUTHORIZED,GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending POST request for adding and publishing the POI with blank device identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_Publish_Building_With_Blank_Device_Identifier() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, deviceIdentifier);
		String randomPoiId = "poi" + Utilities.getTimeStamp();

		// Updating the id field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(buildingJsonObject, "id", randomPoiId);

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.BuildingSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");

		// Send post request for publishing the building
		Headers invalidHeaders = getAllHeaders(authenticationToken, "");
		response = given().headers(invalidHeaders).formParam("poid", randomPoiId).when().post(GlobalConstant.PublishBuilderSuffix);

		// Assertion response after publishing the building
		RestUtil.verifyPoiResponseData(response,GlobalConstant.HTTP_UNAUTHORIZED,GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending POST request for adding and publishing the POI with invalid device identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_Publish_Building_With_Invalid_Device_Identifier() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, deviceIdentifier);
		String randomPoiId = "poi" + Utilities.getTimeStamp();

		// Updating the id field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(buildingJsonObject, "id", randomPoiId);

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.BuildingSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");

		// Send post request for publishing the building
		Headers invalidHeaders = getAllHeaders(authenticationToken, "TestUser360");
		response = given().headers(invalidHeaders).formParam("poid", randomPoiId).when().post(GlobalConstant.PublishBuilderSuffix);

		// Assertion response after publishing the building
		RestUtil.verifyPoiResponseData(response,GlobalConstant.HTTP_UNAUTHORIZED,GlobalConstant.AUTHENTICATION_REQUIRED);
	}
}
