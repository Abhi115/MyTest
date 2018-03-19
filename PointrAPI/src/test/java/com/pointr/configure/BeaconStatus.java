package com.pointr.configure;

import static io.restassured.RestAssured.given;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import com.pointr.utils.BaseTest;
import com.pointr.utils.GlobalConstant;
import com.pointr.utils.RestUtil;


public class BeaconStatus extends BaseTest{
	
	/**
	 * Sending GET request to retrieve Beacon Status with valid data
	 * 
	 * @throws Exception
	 */
	@Test
	public void test01_Retrieve_Beacon_With_Valid_Data() throws Exception {

		Headers allheaders = getAllHeaders(authenticationToken, deviceIdentifier);

		// Send GET request
		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveBeaconStatus);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");
	}
	
	/**
	 * Sending GET request to retrieve Beacon Status with invalid Token
	 * 
	 * @throws Exception
	 */
	@Test
	public void test02_Retrieve_Beacon_With_Invalid_Token() throws Exception {

		Headers allheaders = getAllHeaders("7836b48b4n09n4072n094n72", deviceIdentifier);

		// Send GET request
		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveBeaconStatus);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending GET request to retrieve Beacon Status with blank Token
	 * 
	 * @throws Exception
	 */
	@Test
	public void test03_Retrieve_Beacon_With_Blank_Token() throws Exception {

		Headers allheaders = getAllHeaders("", deviceIdentifier);

		// Send GET request
		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveBeaconStatus);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending GET request to retrieve Beacon Status with invalid Device Identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void test04_Retrieve_Beacon_With_Invalid_Device_Identifier() throws Exception {

		Headers allheaders = getAllHeaders(authenticationToken, "TestUser360");

		// Send GET request
		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveBeaconStatus);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending GET request to retrieve Beacon Status with blank Device Identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void test05_Retrieve_Beacon_With_Blank_Device_Identifier() throws Exception {

		Headers allheaders = getAllHeaders(authenticationToken, "");

		// Send GET request
		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveBeaconStatus);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
}
