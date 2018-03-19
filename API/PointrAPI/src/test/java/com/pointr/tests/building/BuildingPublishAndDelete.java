package com.pointr.tests.building;

import static com.pointr.utils.GlobalConstant.FileName.BuilingJsonFile;
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

public class BuildingPublishAndDelete extends BaseTest {

	private JSONObject buildingJsonObject;
	private Headers headers;
	private String builderID;

	@BeforeMethod
	public void addPoi() throws Exception {

		buildingJsonObject = JsonFileUtil.readJsonData(GlobalConstant.building, BuilingJsonFile.toString(), true);
		headers = getAllHeaders(authenticationToken, "TestUser");

		builderID = "Poi" + Utilities.getTimeStamp();
		buildingJsonObject = JsonFileUtil.updateJsonValue(buildingJsonObject, "id", builderID);

		// Add building if not exist
		Response response = given().headers(headers).formParam("jsondata", buildingJsonObject.toJSONString())
				.formParam("isarray", false).when().post(GlobalConstant.BuildingSuffix);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");
	}

	
	@Test
	public void testPublishBuilder() {
		// Send post request for publishing the POI
		Response response= given().headers(headers).formParam("poid", builderID).when()
				.post(GlobalConstant.PublishBuilderSuffix);

		// Assertion response after publishing the Building
		RestUtil.verifyPublishResponse(response);		
	}

	@Test(dependsOnMethods = "testPublishBuilder")
	public void testDeleteBuilder() {
		// Send post request for deleting building
		Response response = given().headers(headers).formParam("poid", builderID).when()
				.post(GlobalConstant.PoIDeleteSuffix);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");
	}

}
