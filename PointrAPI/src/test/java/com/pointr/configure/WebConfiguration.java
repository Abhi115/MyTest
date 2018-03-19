package com.pointr.configure;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import com.pointr.dataprovider.APIDataProvider;
import com.pointr.utils.BaseTest;
import com.pointr.utils.GlobalConstant;
import com.pointr.utils.RestUtil;
import com.pointr.utils.Utilities;

public class WebConfiguration extends BaseTest{

//	/**
//	 * Sending GET request to retrieve Web Configuration with valid data
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test01_Retrieve_Web_Configuration_With_Valid_Data() throws Exception {
//
//		Headers allheaders = getAllHeaders(authenticationToken, deviceIdentifier);
//
//		// Send GET request
//		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveWebConfiguration);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");
//	}
//	
//	/**
//	 * Sending GET request to retrieve Web Configuration with invalid Token
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test02_Retrieve_Web_Configuration_With_Invalid_Token() throws Exception {
//
//		Headers allheaders = getAllHeaders("7836b48b4n09n4072n094n72", deviceIdentifier);
//
//		// Send GET request
//		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveWebConfiguration);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
//	
//	/**
//	 * Sending GET request to retrieve Web Configuration with blank Token
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test03_Retrieve_Web_Configuration_With_Blank_Token() throws Exception {
//
//		Headers allheaders = getAllHeaders("", deviceIdentifier);
//
//		// Send GET request
//		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveWebConfiguration);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
//	
//	/**
//	 * Sending GET request to retrieve Web Configuration with invalid Device Identifier
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test04_Retrieve_Web_Configuration_With_Invalid_Device_Identifier() throws Exception {
//
//		Headers allheaders = getAllHeaders(authenticationToken, "TestUser360");
//
//		// Send GET request
//		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveWebConfiguration);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
//	
//	/**
//	 * Sending GET request to retrieve Web Configuration with blank Device Identifier
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test05_Retrieve_Web_Configuration_With_Blank_Device_Identifier() throws Exception {
//
//		Headers allheaders = getAllHeaders(authenticationToken, "");
//
//		// Send GET request
//		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveWebConfiguration);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
	
	/** 
	 * Sending POST request to add web configuration with different-2 data set
	 * @param key
	 * @param value
	 * @param httpCode
	 * @param errorMsg
	 * @throws Exception
	 */
	@Test(dataProvider = "WebConfigurationSaveTestData", dataProviderClass = APIDataProvider.class)
	public void test06_Add_Web_Configuration(String parameterName, String parameterValue,int httpCode, String errorMsg) throws Exception 
	{
		String parameterDescriptionValue= parameterName+Utilities.getTimeStamp();
		String paramValue= parameterValue+Utilities.getTimeStamp();
		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		
		// Send post request
		Response saveResponse = given().headers(headers).formParam("ParameterDescription", parameterDescriptionValue).formParam("ParameterValue",paramValue)
				.formParam("ParameterDataType", "WebConfig").when().post(GlobalConstant.SaveWebConfiguration);

		// Assertion response
		RestUtil.verifyPoiResponseData(saveResponse, httpCode, errorMsg);
		
		// Send GET request
				Response getResponse = given().headers(headers).when().get(GlobalConstant.RetrieveWebConfiguration);
				
		// Assertion response
		RestUtil.verifyPoiResponseData(getResponse, GlobalConstant.HTTP_OK, "");
				
		int totalSize= getResponse.body().path("body.size()");
		int lastId=totalSize-1;
		int id= getResponse.body().path("body.id["+lastId+"]");
		getResponse.then().body("body.name", hasItem(parameterDescriptionValue));
				
		//send delete request
		Response deleteResponse = given().headers(headers).formParam("configurationId", id)
					.when().delete(GlobalConstant.DeleteWebConfiguration);
				
		// Assertion response
		RestUtil.verifyPoiResponseData(deleteResponse, GlobalConstant.HTTP_OK, "");
	}
	
	/**
	 * Sending post request to add Web Configuration with invalid Token
	 * 
	 * @throws Exception
	 */
	@Test
	public void test07_Add_Web_Configuration_With_Invalid_Token() throws Exception {

		Headers allheaders = getAllHeaders("7836b48b4n09n4072n094n72", deviceIdentifier);

		// Send post request
		Response response = given().headers(allheaders).when().post(GlobalConstant.SaveWebConfiguration);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending post request to add Web Configuration with blank Token
	 * 
	 * @throws Exception
	 */
	@Test
	public void test08_Add_Web_Configuration_With_Blank_Token() throws Exception {

		Headers allheaders = getAllHeaders("", deviceIdentifier);

		// Send post request
		Response response = given().headers(allheaders).when().post(GlobalConstant.SaveWebConfiguration);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending post request to add Web Configuration with invalid Device Identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void test09_Add_Web_Configuration_With_Invalid_Device_Identifier() throws Exception {

		Headers allheaders = getAllHeaders(authenticationToken, "TestUser360");

		// Send post request
		Response response = given().headers(allheaders).when().post(GlobalConstant.SaveWebConfiguration);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending post request to add Web Configuration with blank Device Identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void test10_Add_Web_Configuration_With_Blank_Device_Identifier() throws Exception {

		Headers allheaders = getAllHeaders(authenticationToken, "");

		// Send post request
		Response response = given().headers(allheaders).when().post(GlobalConstant.SaveWebConfiguration);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/** 
	 * Sending Post request to add web config with existing Parameter Name
	 */
	@Test
	public void test11_Add_Web_Configuration_With_Existing_Parameter_Name() throws Exception 
	{
		String parameterDescriptionValue= "test"+Utilities.getTimeStamp();
		String paramValue= "test"+Utilities.getTimeStamp();
		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		
		// Send post request
		Response saveResponse = given().headers(headers).formParam("ParameterDescription", parameterDescriptionValue).formParam("ParameterValue", paramValue)
				.formParam("ParameterDataType", "WebConfig").when().post(GlobalConstant.SaveWebConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(saveResponse, GlobalConstant.HTTP_OK, "");
		
		// Send post request
		Response response = given().headers(headers).formParam("ParameterDescription", parameterDescriptionValue).formParam("ParameterValue", paramValue)
						.formParam("ParameterDataType", "WebConfig").when().post(GlobalConstant.SaveWebConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_BAD_REQUEST, GlobalConstant.EXISTING_PARAMETER_NAME);	
	}
	
	/**
	 * Sending put request to Web Configuration with invalid Token
	 * 
	 * @throws Exception
	 */
	@Test
	public void test12_Edit_Web_Configuration_With_Invalid_Token() throws Exception {

		Headers allheaders = getAllHeaders("7836b48b4n09n4072n094n72", deviceIdentifier);

		// Send put request
		Response response = given().headers(allheaders).when().put(GlobalConstant.EditWebConfiguration);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending put request to Web Configuration with blank Token
	 * 
	 * @throws Exception
	 */
	@Test
	public void test13_Edit_Web_Configuration_With_Blank_Token() throws Exception {

		Headers allheaders = getAllHeaders("", deviceIdentifier);

		// Send put request
		Response response = given().headers(allheaders).when().put(GlobalConstant.EditWebConfiguration);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending put request to Web Configuration with invalid Device Identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void test14_Edit_Web_Configuration_With_Invalid_Device_Identifier() throws Exception {

		Headers allheaders = getAllHeaders(authenticationToken, "TestUser360");

		// Send put request
		Response response = given().headers(allheaders).when().put(GlobalConstant.EditWebConfiguration);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending put request to Web Configuration with blank Device Identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void test15_Edit_Web_Configuration_With_Blank_Device_Identifier() throws Exception {

		Headers allheaders = getAllHeaders(authenticationToken, "");

		// Send put request
		Response response = given().headers(allheaders).when().put(GlobalConstant.EditWebConfiguration);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending delete request to delete web config
	 * 
	 * @throws Exception
	 */
	@Test
	public void test16_Delete_Web_Configuration() throws Exception {

		String parameterDescription= "test"+Utilities.getTimeStamp();
		String parameterValue= "test"+Utilities.getTimeStamp();
		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		
		// Send post request
		Response saveResponse = given().headers(headers).formParam("ParameterDescription", parameterDescription).formParam("ParameterValue", parameterValue)
				.formParam("ParameterDataType", "WebConfig").when().post(GlobalConstant.SaveWebConfiguration);

		// Assertion response
		RestUtil.verifyPoiResponseData(saveResponse, GlobalConstant.HTTP_OK, "");
		
		// Send GET request
		Response getResponse = given().headers(headers).when().get(GlobalConstant.RetrieveWebConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(getResponse, GlobalConstant.HTTP_OK, "");
		
		int totalSize= getResponse.body().path("body.size()");
		int lastId=totalSize-1;
		int id= getResponse.body().path("body.id["+lastId+"]");
		getResponse.then().body("body.name", hasItem(parameterDescription));
		
		//send delete request
		Response deleteResponse = given().headers(headers).formParam("configurationId", id)
				.when().delete(GlobalConstant.DeleteWebConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(deleteResponse, GlobalConstant.HTTP_OK, "");
	}
	
	/**
	 * Sending Delete request to Web Configuration with invalid Token
	 * 
	 * @throws Exception
	 */
	@Test
	public void test17_Delete_Web_Configuration_With_Invalid_Token() throws Exception {

		Headers allheaders = getAllHeaders("7836b48b4n09n4072n094n72", deviceIdentifier);

		// Send Delete request
		Response response = given().headers(allheaders).when().delete(GlobalConstant.DeleteWebConfiguration);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending Delete request to Web Configuration with blank Token
	 * 
	 * @throws Exception
	 */
	@Test
	public void test18_Delete_Web_Configuration_With_Blank_Token() throws Exception {

		Headers allheaders = getAllHeaders("", deviceIdentifier);

		// Send Delete request
		Response response = given().headers(allheaders).when().delete(GlobalConstant.DeleteWebConfiguration);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending Delete request to Web Configuration with invalid Device Identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void test19_Delete_Web_Configuration_With_Invalid_Device_Identifier() throws Exception {

		Headers allheaders = getAllHeaders(authenticationToken, "TestUser360");

		// Send Delete request
		Response response = given().headers(allheaders).when().delete(GlobalConstant.DeleteWebConfiguration);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending Delete request to Web Configuration with blank Device Identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void test20_Delete_Web_Configuration_With_Blank_Device_Identifier() throws Exception {

		Headers allheaders = getAllHeaders(authenticationToken, "");

		// Send put request
		Response response = given().headers(allheaders).when().delete(GlobalConstant.DeleteWebConfiguration);

		// Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
}
