package com.pointr.tests.poi;

import static com.pointr.utils.GlobalConstant.FileName.PoiJsonData;
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

import io.restassured.http.Headers;
import io.restassured.response.Response;

public class POI_PositiveScenarios extends BaseTest {

	private JSONObject poiJSONObject;

	@BeforeMethod
	public void readJsonFile() throws Exception {
		poiJSONObject = JsonFileUtil.readJsonData(GlobalConstant.poiinput, PoiJsonData.toString(), true);
	}

	/**
	 * Sending POST request for adding new entry of POI
	 * 
	 * @throws Exception
	 */
	@Test
	public void test01_POI_POST_Save() throws Exception {

		Headers allheaders = getAllHeaders(authenticationToken, deviceIdentifier);

		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(poiJSONObject, "id",
				"poi" + Utilities.getTimeStamp());

		// Send post request
		Response response = given().headers(allheaders).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).expect().statusCode(200).when().post(GlobalConstant.PoISaveSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");
	}

	/**
	 * Sending POST request for adding description to same POI
	 * 
	 * @throws Exception
	 */
	@Test
	public void test02_POI_POST_Edit() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, deviceIdentifier);

		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(poiJSONObject, "description", "Test Edit end point");
		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).expect().statusCode(GlobalConstant.HTTP_OK).when()
				.post(GlobalConstant.PoIEditSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");
	}

	/**
	 * Sending POST request for adding name in POI
	 * 
	 * @throws Exception
	 */
	@Test
	public void test03_POI_POST_EditName() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, deviceIdentifier);

		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(poiJSONObject, "name", "Testing360Logica");

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.PoIEditSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");

	}

	/**
	 * Sending POST request for adding and publishing the POI
	 * 
	 * @throws Exception
	 */
	@Test
	public void test04_POI_POST_EndPoint_Publish_Poi() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, deviceIdentifier);
		String randomPoiId = "poi" + Utilities.getTimeStamp();

		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(poiJSONObject, "id", randomPoiId);

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.PoISaveSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");

		// Send post request for publishing the POI
		response = given().headers(headers).formParam("poid", randomPoiId).when().post(GlobalConstant.PoIpublishSuffix);

		// Assertion response after publishing the Poi
		RestUtil.verifyPublishResponse(response);

	}

	/**
	 * Sending POST request for adding and publishing and deleting the POI
	 * 
	 * @throws Exception
	 */
	@Test
	public void test05_POI_POST_EndPoint_Add_Publish_Delete_Poi() throws Exception {

		Headers headers = getAllHeaders(authenticationToken, deviceIdentifier);
		String randomPoiId = "poi" + Utilities.getTimeStamp();

		// Updating the description field in JSON
		JSONObject updatedJSON = JsonFileUtil.updateJsonValue(poiJSONObject, "id", randomPoiId);

		// Send post request
		Response response = given().headers(headers).formParam("jsondata", updatedJSON.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.PoISaveSuffix);
		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");

		// Send post request for publishing the POI
		Response response_1 = given().headers(headers).formParam("poid", randomPoiId).when()
				.post(GlobalConstant.PublishBuilderSuffix);
		
		// Assertion response after publishing the Poi
		RestUtil.verifyPublishResponse(response_1);

		// Send post request for publishing the POI
		Response response_2 = given().headers(headers).formParam("poid", randomPoiId).when()
				.post(GlobalConstant.PoIDeleteSuffix);
		// Assertion response
		RestUtil.verifyPoiResponseData(response_2, GlobalConstant.HTTP_OK, "");

	}
	

	/** 
	 * Sending POST add request with different-2 data set
	 * @param key
	 * @param value
	 * @param httpCode
	 * @param errorMsg
	 * @throws Exception
	 */
	@Test(dataProvider = "PoiTestData", dataProviderClass = APIDataProvider.class)
	public void test6_POI_POST_EndPoint_Save(String key, String value, int httpCode, String errorMsg) throws Exception {
		Headers headers = getAllHeaders(authenticationToken, "TestUser");

		// Updating the description field in JSON
		JSONObject jsonObject = JsonFileUtil.updatePoiJsonInput(poiJSONObject, key, value );
		System.out.println(jsonObject);
		// Send post request
		Response response = given().headers(headers).formParam("jsondata", jsonObject.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.PoISaveSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, httpCode, errorMsg);
	}
	
	/** 
	 * Sending POST edit request with different-2 data set
	 * @param key
	 * @param value
	 * @param httpCode
	 * @param errorMsg
	 * @throws Exception
	 */
	@Test(dataProvider = "PoiTestDataForEdit", dataProviderClass = APIDataProvider.class)
	public void test07_POI_POST_EndPoint_Edit(String key, String value, int httpCode, String errorMsg) throws Exception {
		Headers headers = getAllHeaders(authenticationToken, "TestUser");

		// Updating the description field in JSON
		JSONObject jsonObject = JsonFileUtil.updateJsonValue(poiJSONObject, key, value );
		
		System.out.println(jsonObject.toJSONString());
		
		// Send post request
		Response response = given().headers(headers).formParam("jsondata", jsonObject.toJSONString()).formParam("isarray", false)
				.when().post(GlobalConstant.PoIEditSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, httpCode, errorMsg);
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @param httpCode
	 * @param errorMsg
	 * @throws Exception
	 */
	//@Test(dataProvider = "poiComplexTestData", dataProviderClass = APIDataProvider.class)
	public void test7_POI_Save_Complex_POI(String key, String json, int httpCode, String errorMsg) throws Exception {
		Headers headers = getAllHeaders(authenticationToken, "TestUser");

		// Updating the description field in JSON
		JSONParser parser = new JSONParser();
		JSONObject jsonObj =(JSONObject) parser.parse(json);
		JSONObject jsonObject = JsonFileUtil.updatePoiJsonInput(jsonObj, "id", 
				"poi" + Utilities.getTimeStamp());
		
		// Send post request
		Response response = given().headers(headers).formParam("jsondata", jsonObject.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.PoISaveSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, httpCode, errorMsg);
	}
}
