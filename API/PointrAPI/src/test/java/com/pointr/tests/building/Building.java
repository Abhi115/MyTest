package com.pointr.tests.building;

import static io.restassured.RestAssured.given;

import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import com.pointr.dataprovider.APIDataProvider;
import com.pointr.utils.BaseTest;
import com.pointr.utils.GlobalConstant;
import com.pointr.utils.JsonFileUtil;
import com.pointr.utils.RestUtil;
import com.pointr.utils.Utilities;

import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Building extends BaseTest {

	/**
	 * Create Different-2 type of Building data will be provided using Data provider
	 * @param key : 
	 * @param json
	 * @param httpCode
	 * @param errorMsg
	 * @throws Exception
	 */
	@Test(dataProvider = "BuildingJsonData", dataProviderClass = APIDataProvider.class)
	public void testAddAllBuidingType(String type, String json, boolean isArray) throws Exception {

		Headers headers = getAllHeaders(authenticationToken, "TestUser");

		// Updating the description field in JSON
		JSONParser parser = new JSONParser();
		Object jsonObject = JsonFileUtil.updateJsonValue(parser.parse(json), "id",
				"poi" + Utilities.getTimeStamp());
		
		String jsonString = jsonTOString(jsonObject);
		
		// Send post request
		Response response = given().headers(headers).formParam("jsondata", jsonString).formParam("isarray", isArray)
				.when().post(GlobalConstant.BuildingSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");
	}	
}
