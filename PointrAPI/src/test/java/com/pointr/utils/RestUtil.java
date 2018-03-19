package com.pointr.utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class RestUtil {
	// Global Setup Variables
	public static String path; // Rest request path

	/*
	 *** Sets Base URI*** Before starting the test, we should set the
	 * RestAssured.baseURI
	 */
	public static void setBaseURI(String baseURI) {
		RestAssured.baseURI = baseURI;
	}

	/*
	 *** Sets base path*** Before starting the test, we should set the
	 * RestAssured.basePath
	 */
	public static void setBasePath(String basePathTerm) {
		RestAssured.basePath = basePathTerm;
	}

	/*
	 *** Reset Base URI (after test)*** After the test, we should reset the
	 * RestAssured.baseURI
	 */
	public static void resetBaseURI() {
		RestAssured.baseURI = null;
	}

	/*
	 *** Reset base path (after test)*** After the test, we should reset the
	 * RestAssured.basePath
	 */
	public static void resetBasePath() {
		RestAssured.basePath = null;
	}

	/*
	 *** Sets ContentType*** We should set content type as JSON or XML before
	 * starting the test
	 */
	public static void setContentType(ContentType Type) {
		given().contentType(Type);
	}

	/*
	 *** search query path of first example*** It is equal to
	 * "barack obama/videos.json?num_of_videos=4"
	 */
	public static void createSearchQueryPath(String searchTerm, String jsonPathTerm, String param, String paramValue) {
		path = searchTerm + "/" + jsonPathTerm + "?" + param + "=" + paramValue;
	}

	/*
	 *** Returns response*** We send "path" as a parameter to the Rest Assured'a
	 * "get" method and "get" method returns response of API
	 */
	public static Response getResponse() {
		return get(path);
	}

	/*
	 *** Returns JsonPath object*** First convert the API's response to String
	 * type with "asString()" method. Then, send this String formatted json
	 * response to the JsonPath class and return the JsonPath
	 */
	public static JsonPath getJsonPath(Response res) {
		String json = res.asString();
		// System.out.print("returned json: " + json +"\n");
		return new JsonPath(json);
	}

	/**
	 * 
	 * @param response
	 * @param statusCode
	 * @param errorMsg
	 */
	public static void verifyPoiResponseData(Response response, int statusCode, String errorMsg) {
		// Assertion response		
		if(errorMsg.equals("Invalid json string")) {			
			String msg = response.then().extract().response().path("status.errorMessage");
			Assert.assertTrue(msg.contains(errorMsg), "Not contains Expected "+errorMsg+" Actual "+msg);
		}
		else{
			response.then().body("status.errorMessage", equalTo(errorMsg));
		}
		response.then().body("status.code", equalTo(statusCode));
	}
 
	/**
	 * verifying the success msg after publishing the poi
	 * @param r
	 */
	public static void verifyPublishResponse(Response r) {		
		r.then().body("body.IBeacon", equalTo(GlobalConstant.SuccessMsg));
		r.then().body("body.Map", equalTo(GlobalConstant.SuccessMsg));
		r.then().body("body.Configuration", equalTo(GlobalConstant.SuccessMsg));
		r.then().body("body.Graph", equalTo(GlobalConstant.SuccessMsg));
		r.then().body("body.Pop", equalTo(GlobalConstant.SuccessMsg));
		r.then().body("body.Poi", equalTo(GlobalConstant.SuccessMsg));
		r.then().body("body.Zone", equalTo(GlobalConstant.SuccessMsg));
		r.then().body("body.Wall", equalTo(GlobalConstant.SuccessMsg));
		r.then().body("status.code", equalTo(GlobalConstant.HTTP_OK));
	}

}