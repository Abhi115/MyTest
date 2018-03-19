package com.pointr.tests.mapdesigner;

import static io.restassured.RestAssured.given;

import io.restassured.http.Headers;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import com.pointr.dataprovider.APIDataProvider;
import com.pointr.utils.BaseTest;
import com.pointr.utils.GlobalConstant;
import com.pointr.utils.JsonFileUtil;
import com.pointr.utils.RestUtil;
import com.pointr.utils.Utilities;

public class MapDesignerExportData extends BaseTest{

	
	@Test(dataProvider = "MapDesigner", dataProviderClass = APIDataProvider.class)
	public void test_Map_Designer_Export_Data(String type, String json, boolean isArray) throws Exception {
		
		String poiID= "poi"+Utilities.getTimeStamp();
		Headers headers = getAllHeaders(authenticationToken, "TestUser");

		// Updating the id field in JSON
		JSONParser parser = new JSONParser();
		Object jsonObj = JsonFileUtil.updateJsonValue(parser.parse(json), "id", poiID);
		Object jsonObject = JsonFileUtil.updateJsonValue(jsonObj, "level", "0");
		 
		String jsonString = jsonTOString(jsonObject);
		
		// Send post request
		Response saveResponse = given().headers(headers).formParam("jsondata", jsonString).formParam("isarray", isArray)
				.when().post(GlobalConstant.MapDesignerSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(saveResponse, GlobalConstant.HTTP_OK, "");
		
		// publish data
		Response publishResponse= given().headers(headers).formParam("poid", poiID).when().post(GlobalConstant.MapDesignerPublishSuffix);
		
		// Assertion response
		//RestUtil.verifyPublishResponse(publishResponse);
		
		//Export Data 
		Response exportResponse =given().headers(headers).when().get(GlobalConstant.ExportData);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(exportResponse, GlobalConstant.HTTP_OK, "");
		
		//verify published id in export result
		exportResponse.then().body("body.poi[0].id", hasItem(poiID));
	}
}
