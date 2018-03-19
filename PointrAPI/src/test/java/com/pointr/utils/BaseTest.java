package com.pointr.utils;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;

public class BaseTest {

	protected static String endPointUrl;
	protected static String authenticationToken;
	protected static String deviceIdentifier;

	@BeforeClass
	public void setup() throws Exception {
		endPointUrl = Configuration.readConfigFile("URL") + ":" + Configuration.readConfigFile("Port");
		RestAssured.baseURI = endPointUrl;
		authenticationToken = Configuration.readConfigFile("AuthenticationToken");
		deviceIdentifier = Configuration.readConfigFile("DeviceIdentifier");

	}

	public static Headers getAllHeaders(String authToken, String deviceId) {
		Header header1 = new Header("AuthenticationToken", authToken);
		Header header2 = new Header("DeviceIdentifier", deviceId);
		Header header3 = new Header("Content-Type", "application/x-www-form-urlencoded");
		List<Header> list = new LinkedList<Header>();

		list.add(header1);
		list.add(header2);
		list.add(header3);

		Headers headers = new Headers(list);
		return headers;
	}
	
	
	/**
	 * Convert JSON Object or JSON Array into String type
	 * @param jsonObject
	 * @return
	 */
	public String jsonTOString(Object jsonObject){
		String jsonString = null;
		if (jsonObject instanceof JSONArray) {
			jsonString = ((JSONArray) jsonObject).toJSONString();
		} else if (jsonObject instanceof JSONObject) {
			jsonString = ((JSONObject) jsonObject).toJSONString();
		}
		return jsonString;
	}

}
