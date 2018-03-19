package com.pointr.tests.building;

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

public class BuildingExportData extends BaseTest{

	@Test(dataProvider = "BuildingJsonData", dataProviderClass = APIDataProvider.class)
	public void test_Building_Export_Data(String type, String json, boolean isArray) throws Exception {
		
		String poiId = "poi"+Utilities.getTimeStamp();
		Headers headers = getAllHeaders(authenticationToken, "TestUser");

		// Updating the id field in JSON
		JSONParser parser = new JSONParser();
		Object jsonObj = JsonFileUtil.updateJsonValue(parser.parse(json), "id", poiId);
		Object jsonObject = JsonFileUtil.updateJsonValue(jsonObj, "level", "0");
		 
		String jsonString = jsonTOString(jsonObject);
		
		// Send post request
		Response saveResponse = given().headers(headers).formParam("jsondata", jsonString).formParam("isarray", isArray)
				.when().post(GlobalConstant.BuildingSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(saveResponse, GlobalConstant.HTTP_OK, "");
		
		// publish data
		Response publishResponse= given().headers(headers).formParam("poid", poiId).when().post(GlobalConstant.PublishBuilderSuffix);
		
		// Assertion response
		//RestUtil.verifyPublishResponse(publishResponse);
		
		//Export Data 
		Response exportResponse =given().headers(headers).when().get(GlobalConstant.ExportData);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(exportResponse, GlobalConstant.HTTP_OK, "");
		
		//verify published id in export result
		exportResponse.then().body("body.poi[0].id", hasItem(poiId));
	}
}
