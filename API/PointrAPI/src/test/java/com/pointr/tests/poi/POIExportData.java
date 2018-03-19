package com.pointr.tests.poi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import com.pointr.dataprovider.APIDataProvider;
import com.pointr.utils.BaseTest;
import com.pointr.utils.GlobalConstant;
import com.pointr.utils.JsonFileUtil;
import com.pointr.utils.RestUtil;
import com.pointr.utils.Utilities;

public class POIExportData extends BaseTest {

	@Test(dataProvider = "poiComplexTestData", dataProviderClass = APIDataProvider.class)
	public void test_POI_Export_Data(String type, String json, int statusCode, String msg) throws Exception {
		
		String randomID= "poi"+Utilities.getTimeStamp();
		Headers headers = getAllHeaders(authenticationToken, "TestUser");

		// Updating the id field in JSON
		JSONParser parser = new JSONParser();
		Object jsonObj = JsonFileUtil.updateJsonValue(parser.parse(json), "id", randomID);
		Object jsonObject = JsonFileUtil.updateJsonValue(jsonObj, "level", "0");
		 
		String jsonString = jsonTOString(jsonObject);
		
		// Send post request
		Response saveResponse = given().headers(headers).formParam("jsondata", jsonString).formParam("isarray", false)
				.when().post(GlobalConstant.PoISaveSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(saveResponse, statusCode, msg);
		
		// publish data
		Response publishResponse= given().headers(headers).formParam("poid", randomID).when().post(GlobalConstant.PoIpublishSuffix);
		
		// Assertion response
		//RestUtil.verifyPublishResponse(publishResponse);
		
		//Export Data 
		Response exportResponse =given().headers(headers).when().get(GlobalConstant.ExportData);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(exportResponse, GlobalConstant.HTTP_OK, "");
		
		//verify published id in export result
		exportResponse.then().body("body.poi[0].id", hasItem(randomID));
	}
}
