package com.pointr.tests.poi;

import static com.pointr.utils.GlobalConstant.FileName.*;
import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pointr.utils.BaseTest;
import com.pointr.utils.GlobalConstant;
import com.pointr.utils.JsonFileUtil;
import com.pointr.utils.RestUtil;
import com.pointr.utils.Utilities;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class POI_Flow extends BaseTest {

	private List<JSONObject> jsonList;
	private int add;
	private int edit;
	private int publish;
	private int delete;
	Properties properties;
	private int filesCount;

	@Parameters({ "filename" })
	@BeforeClass
	public void readJsonFile(String filenname) throws Exception {		
		File f = new File(GlobalConstant.poiinput);
		filesCount = Utilities.getFilesCount(f);

		jsonList = new ArrayList<JSONObject>();		
		jsonList.add(JsonFileUtil.readJsonData(GlobalConstant.poiinput, PoiJsonData.toString(), true));		
		jsonList.add(JsonFileUtil.readJsonData(GlobalConstant.poiinput, PoiInputOne.toString(), true));		
		jsonList.add(JsonFileUtil.readJsonData(GlobalConstant.poiinput, PoiInputTwo.toString(), true));
		System.out.println(JsonFileUtil.readJsonData(GlobalConstant.poiinput, PoiJsonData.toString(), true));
		//reading properties file data
		properties = new Properties();
		properties.load(new FileInputStream(GlobalConstant.poiInputs + "/" + filenname + ".properties"));
		add = Integer.parseInt(properties.getProperty("Add"));
		edit = Integer.parseInt(properties.getProperty("Edit"));
		publish = Integer.parseInt(properties.getProperty("Publish"));
		delete = Integer.parseInt(properties.getProperty("Delete"));
		if (add <= edit || add <= publish || add <= delete)
			Assert.fail("Please enter valid inputs |add:" + add + "|edit:" + edit + "|publish:" + publish + "|delete:"
					+ delete + "");

	}

	/**
	 * Sending POST request for adding,updating,publishing and deleting multiple POI's
	 * 
	 * @throws Exception
	 */
	@Test
	public void test01_POI_POST_ScenarioOne() throws Exception {

		Headers allheaders = getAllHeaders(authenticationToken, deviceIdentifier);

		int randomJSON = Utilities.getRandomNumber(1, filesCount);
		System.out.println(randomJSON);
		List<String> list = new ArrayList<String>();
	
		JSONObject updatedJSON;

		// Adding multiple POI on cloud
		for (int i = 0; i < add; i++) {
			String poi_ID = "poi" + Utilities.getTimeStamp();
			list.add(poi_ID);
			// Updating the description field in JSON
			updatedJSON = JsonFileUtil.updateJsonValue(jsonList.get(randomJSON), "id", poi_ID);

			// Send post request
			Response response = given().headers(allheaders).formParam("jsondata", updatedJSON.toJSONString())
					.formParam("isarray", false).when().post(GlobalConstant.PoISaveSuffix);

			// Assertion response
			RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");
		}

		// Updating multiple POI on cloud
		for (int i = 0; i < edit; i++) {
			String poi_ID = list.get(i);
			// Updating the description field in JSON
			updatedJSON = JsonFileUtil.updateJsonValue(jsonList.get(randomJSON), "id", poi_ID);
			updatedJSON = JsonFileUtil.updateJsonValue(jsonList.get(randomJSON), "name", "Edit_" + poi_ID);

			// Send post request
			Response response = given().headers(allheaders).formParam("jsondata", updatedJSON.toJSONString())
					.formParam("isarray", false).when().post(GlobalConstant.PoIEditSuffix);

			// Assertion response
			RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");
		}

		// Publishing multiple POI on cloud
		for (int i = 0; i < publish; i++) {
			String poi_ID = list.get(i);
			// Send post request for publishing the POI
			Response response = given().headers(allheaders).formParam("poiid", poi_ID).when().post(GlobalConstant.PoIpublishSuffix);

			// Assertion response after publishing the Poi
			RestUtil.verifyPublishResponse(response);
		}

		// Deleting multiple POI on cloud
		for (int i = 0; i < delete; i++) {
			String poi_ID = list.get(i);
			// Send post request for publishing the POI
			Response response = given().headers(allheaders).formParam("poiid", poi_ID).when().post(GlobalConstant.PoIDeleteSuffix);

			// Assertion response
			RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");
		}

	}

}
