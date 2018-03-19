package com.pointr.configure;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pointr.dataprovider.APIDataProvider;
import com.pointr.utils.BaseTest;
import com.pointr.utils.GlobalConstant;
import com.pointr.utils.RestUtil;
import com.pointr.utils.Utilities;

public class Configuration extends BaseTest{

//	/**
//	 * Sending GET request to retrieve Configuration with valid data
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test01_Retrieve_Configuration_With_Valid_Data() throws Exception {
//
//		Headers allheaders = getAllHeaders(authenticationToken, deviceIdentifier);
//
//		// Send GET request
//		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveConfiguration);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");
//	}
//	
//	/**
//	 * Sending GET request to retrieve Configuration with invalid Token
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test02_Retrieve_Configuration_With_Invalid_Token() throws Exception {
//
//		Headers allheaders = getAllHeaders("7836b48b4n09n4072n094n72", deviceIdentifier);
//
//		// Send GET request
//		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveConfiguration);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
//	
//	/**
//	 * Sending GET request to retrieve Configuration with blank Token
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test03_Retrieve_Configuration_With_Blank_Token() throws Exception {
//
//		Headers allheaders = getAllHeaders("", deviceIdentifier);
//
//		// Send GET request
//		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveConfiguration);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
//	
//	/**
//	 * Sending GET request to retrieve Configuration with invalid Device Identifier
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test04_Retrieve_Configuration_With_Invalid_Device_Identifier() throws Exception {
//
//		Headers allheaders = getAllHeaders(authenticationToken, "TestUser360");
//
//		// Send GET request
//		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveConfiguration);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
//	
//	/**
//	 * Sending GET request to retrieve Configuration with blank Device Identifier
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test05_Retrieve_Configuration_With_Blank_Device_Identifier() throws Exception {
//
//		Headers allheaders = getAllHeaders(authenticationToken, "");
//
//		// Send GET request
//		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveConfiguration);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
	
//	/** 
//	 * Sending post request to add global configuration with different-2 data set
//	 * @param key
//	 * @param value
//	 * @param httpCode
//	 * @param errorMsg
//	 * @throws Exception
//	 */
//	@Test(dataProvider = "ConfigurationSaveTestData", dataProviderClass = APIDataProvider.class)
//	public void test06_Add_Web_Configuration(String parameterName, Object parameterValue,String parameterDataType,int httpCode, String errorMsg) throws Exception 
//	{
//		Headers headers = getAllHeaders(authenticationToken, "TestUser");
//		String paramName= parameterName+Utilities.getTimeStamp();
//		Object paramValue= parameterValue;
//		
//		// Send post request
//		Response saveResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
//				.formParam("ParameterDataType",parameterDataType).when().post(GlobalConstant.SaveConfiguration);
//		
//		// Assertion response
//		RestUtil.verifyPoiResponseData(saveResponse, httpCode, errorMsg);
//	}
//	
//	/**
//	 * Sending post request to add Configuration with invalid Token
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test07_Add_Configuration_With_Invalid_Token() throws Exception {
//
//		Headers allheaders = getAllHeaders("7836b48b4n09n4072n094n72", deviceIdentifier);
//
//		// Send post request
//		Response response = given().headers(allheaders).when().post(GlobalConstant.SaveConfiguration);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
//	
//	/**
//	 * Sending post request to add Configuration with blank Token
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test08_Add_Configuration_With_Blank_Token() throws Exception {
//
//		Headers allheaders = getAllHeaders("", deviceIdentifier);
//
//		// Send post request
//		Response response = given().headers(allheaders).when().post(GlobalConstant.SaveConfiguration);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
//	
//	/**
//	 * Sending post request to add Configuration with invalid Device Identifier
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test09_Add_Configuration_With_Invalid_Device_Identifier() throws Exception {
//
//		Headers allheaders = getAllHeaders(authenticationToken, "TestUser360");
//
//		// Send post request
//		Response response = given().headers(allheaders).when().post(GlobalConstant.SaveConfiguration);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
//	
//	/**
//	 * Sending post request to add Configuration with blank Device Identifier
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test10_Add_Configuration_With_Blank_Device_Identifier() throws Exception {
//
//		Headers allheaders = getAllHeaders(authenticationToken, "");
//
//		// Send post request
//		Response response = given().headers(allheaders).when().post(GlobalConstant.SaveConfiguration);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
//	
//	/**
//	 * Sending post request to add Configuration with blank parameter
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test11_Add_Web_Configuration_With_Blank_Parameter_Name() throws Exception 
//	{
//		Headers headers = getAllHeaders(authenticationToken, "TestUser");
//		
//		String paramName= "";
//		String paramValue= "test";
//		String parameterDataType= "String";
//		
//		// Send post request
//		Response saveResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
//				.formParam("ParameterDataType",parameterDataType).when().post(GlobalConstant.SaveConfiguration);
//		
//		// Assertion response
//		RestUtil.verifyPoiResponseData(saveResponse,GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.DESCRIPTION_REQUIRED);
//	}
//	
//	/**
//	 * Sending post request to add Configuration with existing parameter
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test12_Add_Global_Web_Configuration_With_Existing_Parameter_Name() throws Exception 
//	{
//		Headers headers = getAllHeaders(authenticationToken, "TestUser");
//		
//		String paramName= "test"+Utilities.getTimeStamp();
//		String paramValue= "test";
//		String parameterDataType= "String";
//		
//		// Send post request
//		Response saveResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
//				.formParam("ParameterDataType",parameterDataType).when().post(GlobalConstant.SaveConfiguration);
//		
//		// Assertion response
//		RestUtil.verifyPoiResponseData(saveResponse,GlobalConstant.HTTP_OK , "");
//		
//		// Send post request with existing parameter
//		Response response = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
//						.formParam("ParameterDataType",parameterDataType).when().post(GlobalConstant.SaveConfiguration);
//		
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response,GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.EXISTING_PARAMETER_NAME);
//	}
//	
//	/**
//	 * Sending post request to add Configuration with blank values
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test13_Add_Web_Configuration_With_Blank_Values() throws Exception 
//	{
//		Headers headers = getAllHeaders(authenticationToken, "TestUser");
//		
//		String paramName= "test";
//		String paramValue= "";
//		String parameterDataType= "Boolean";
//		
//		// Send post request
//		Response saveResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
//				.formParam("ParameterDataType",parameterDataType).when().post(GlobalConstant.SaveConfiguration);
//		
//		// Assertion response
//		RestUtil.verifyPoiResponseData(saveResponse,GlobalConstant.HTTP_BAD_REQUEST ,GlobalConstant.VALUE_REQUIRED);
//	}
	
	/**
	 * Sending put request to edit Configuration with different-2 data set 
	 * 
	 * @throws Exception
	 */
	@Test(dataProvider = "ConfigurationEditTestData", dataProviderClass = APIDataProvider.class)
	public void test14_Edit_Configuration(String parameterName, Object parameterValue,String parameterDataType,Object editValue,int httpCode, String errorMsg) throws Exception 
	{
		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		String paramName= parameterName+Utilities.getTimeStamp();
		Object paramValue= parameterValue;
		
		// Send post request to add configuration
		Response saveResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
				.formParam("ParameterDataType",parameterDataType).when().post(GlobalConstant.SaveConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(saveResponse, httpCode, errorMsg);
		
		// Send GET request
		Response getResponse = given().headers(headers).when().get(GlobalConstant.RetrieveConfiguration);
				
		// Assertion response
		RestUtil.verifyPoiResponseData(getResponse, httpCode, errorMsg);
		
		int totalSize= getResponse.body().path("body.size()");
		int id= getResponse.body().path("body.id["+(totalSize-1)+"]");
		getResponse.then().body("body.name["+(totalSize-1)+"]", equalTo(paramName));
		
		// Send put request to edit configuration
		Response editResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",editValue)
						.formParam("ParameterDataType",parameterDataType).formParam("id",id).when().put(GlobalConstant.EditConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(editResponse,httpCode, errorMsg);
	}
	
	/**
	 * Sending put request to edit parameter field 
	 * 
	 * @throws Exception
	 */
	@Test
	public void test15_Edit_Parameter_Field() throws Exception 
	{
		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		String paramName= "test"+Utilities.getTimeStamp();
		Object paramValue= "test";
		String parameterDataType= "String";
		String newParamName= "360"+Utilities.getTimeStamp();
		
		// Send post request to add configuration
		Response saveResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
				.formParam("ParameterDataType",parameterDataType).when().post(GlobalConstant.SaveConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(saveResponse,GlobalConstant.HTTP_OK, "");
		
		// Send GET request
		Response getResponse = given().headers(headers).when().get(GlobalConstant.RetrieveConfiguration);
				
		// Assertion response
		RestUtil.verifyPoiResponseData(getResponse, GlobalConstant.HTTP_OK, "");
		
		int totalSize= getResponse.body().path("body.size()");
		int id= getResponse.body().path("body.id["+(totalSize-1)+"]");
		getResponse.then().body("body.name["+(totalSize-1)+"]", equalTo(paramName));
		
		// Send put request to edit configuration
		Response editResponse = given().headers(headers).formParam("ParameterDescription", newParamName).formParam("ParameterValue",paramValue)
						.formParam("ParameterDataType",parameterDataType).formParam("id",id).when().put(GlobalConstant.EditConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(editResponse, GlobalConstant.HTTP_OK, "");
	}
	
	/**
	 * Sending delete request to delete configuration 
	 * 
	 * @throws Exception
	 */
	@Test
	public void test16_Delete_Configuration() throws Exception 
	{
		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		String paramName= "test"+Utilities.getTimeStamp();
		Object paramValue= "test";
		String parameterDataType= "String";
		
		// Send post request to add configuration
		Response saveResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
				.formParam("ParameterDataType",parameterDataType).when().post(GlobalConstant.SaveConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(saveResponse,GlobalConstant.HTTP_OK, "");
		
		// Send GET request
		Response getResponse = given().headers(headers).when().get(GlobalConstant.RetrieveConfiguration);
				
		// Assertion response
		RestUtil.verifyPoiResponseData(getResponse, GlobalConstant.HTTP_OK, "");
		
		int totalSize= getResponse.body().path("body.size()");
		int id= getResponse.body().path("body.id["+(totalSize-1)+"]");
		getResponse.then().body("body.name["+(totalSize-1)+"]", equalTo(paramName));
		
		// Send delete request to delete configuration
		Response deleteResponse = given().headers(headers).formParam("configurationId", id)
				.delete(GlobalConstant.DeleteConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(deleteResponse, GlobalConstant.HTTP_OK, "");
	}
	
	/**
	 * Sending delete request to delete configuration with blank token
	 * 
	 * @throws Exception
	 */
	@Test
	public void test17_Delete_Configuration_With_Blank_Token() throws Exception 
	{
		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		String paramName= "test"+Utilities.getTimeStamp();
		Object paramValue= "test";
		String parameterDataType= "String";
		
		// Send post request to add configuration
		Response saveResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
				.formParam("ParameterDataType",parameterDataType).when().post(GlobalConstant.SaveConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(saveResponse,GlobalConstant.HTTP_OK, "");
		
		// Send GET request
		Response getResponse = given().headers(headers).when().get(GlobalConstant.RetrieveConfiguration);
				
		// Assertion response
		RestUtil.verifyPoiResponseData(getResponse, GlobalConstant.HTTP_OK, "");
		
		int totalSize= getResponse.body().path("body.size()");
		int id= getResponse.body().path("body.id["+(totalSize-1)+"]");
		getResponse.then().body("body.name["+(totalSize-1)+"]", equalTo(paramName));
		
		headers = getAllHeaders("", "TestUser");
		
		// Send delete request to delete configuration
		Response deleteResponse = given().headers(headers).formParam("configurationId", id)
				.delete(GlobalConstant.DeleteConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(deleteResponse,GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending delete request to delete configuration with invalid token
	 * 
	 * @throws Exception
	 */
	@Test
	public void test18_Delete_Configuration_With_Invalid_Token() throws Exception 
	{
		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		String paramName= "test"+Utilities.getTimeStamp();
		Object paramValue= "test";
		String parameterDataType= "String";
		
		// Send post request to add configuration
		Response saveResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
				.formParam("ParameterDataType",parameterDataType).when().post(GlobalConstant.SaveConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(saveResponse,GlobalConstant.HTTP_OK, "");
		
		// Send GET request
		Response getResponse = given().headers(headers).when().get(GlobalConstant.RetrieveConfiguration);
				
		// Assertion response
		RestUtil.verifyPoiResponseData(getResponse, GlobalConstant.HTTP_OK, "");
		
		int totalSize= getResponse.body().path("body.size()");
		int id= getResponse.body().path("body.id["+(totalSize-1)+"]");
		getResponse.then().body("body.name["+(totalSize-1)+"]", equalTo(paramName));
		
		headers = getAllHeaders("7836b48b4n09n4072n094n72", "TestUser");
		
		// Send delete request to delete configuration
		Response deleteResponse = given().headers(headers).formParam("configurationId", id)
				.delete(GlobalConstant.DeleteConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(deleteResponse,GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending delete request to delete configuration with blank device Identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void test19_Delete_Configuration_With_Blank_Device_Identifier() throws Exception 
	{
		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		String paramName= "test"+Utilities.getTimeStamp();
		Object paramValue= "test";
		String parameterDataType= "String";
		
		// Send post request to add configuration
		Response saveResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
				.formParam("ParameterDataType",parameterDataType).when().post(GlobalConstant.SaveConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(saveResponse,GlobalConstant.HTTP_OK, "");
		
		// Send GET request
		Response getResponse = given().headers(headers).when().get(GlobalConstant.RetrieveConfiguration);
				
		// Assertion response
		RestUtil.verifyPoiResponseData(getResponse, GlobalConstant.HTTP_OK, "");
		
		int totalSize= getResponse.body().path("body.size()");
		int id= getResponse.body().path("body.id["+(totalSize-1)+"]");
		getResponse.then().body("body.name["+(totalSize-1)+"]", equalTo(paramName));
		
		headers = getAllHeaders("authenticationToken", "");
		
		// Send delete request to delete configuration
		Response deleteResponse = given().headers(headers).formParam("configurationId", id)
				.delete(GlobalConstant.DeleteConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(deleteResponse,GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending delete request to delete configuration with invalid device Identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void test20_Delete_Configuration_With_Invalid_Device_Identifier() throws Exception 
	{
		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		String paramName= "test"+Utilities.getTimeStamp();
		Object paramValue= "test";
		String parameterDataType= "String";
		
		// Send post request to add configuration
		Response saveResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
				.formParam("ParameterDataType",parameterDataType).when().post(GlobalConstant.SaveConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(saveResponse,GlobalConstant.HTTP_OK, "");
		
		// Send GET request
		Response getResponse = given().headers(headers).when().get(GlobalConstant.RetrieveConfiguration);
				
		// Assertion response
		RestUtil.verifyPoiResponseData(getResponse, GlobalConstant.HTTP_OK, "");
		
		int totalSize= getResponse.body().path("body.size()");
		int id= getResponse.body().path("body.id["+(totalSize-1)+"]");
		getResponse.then().body("body.name["+(totalSize-1)+"]", equalTo(paramName));
		
		headers = getAllHeaders("authenticationToken", "TestUser360");
		
		// Send delete request to delete configuration
		Response deleteResponse = given().headers(headers).formParam("configurationId", id)
				.delete(GlobalConstant.DeleteConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(deleteResponse,GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending delete request to delete configuration with non existing id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test21_Delete_Configuration_With_Non_Existing_ID() throws Exception 
	{
		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		int id= Utilities.getRandomNumber(1, 4);

		// Send delete request to delete configuration
		Response deleteResponse = given().headers(headers).formParam("configurationId", id)
				.delete(GlobalConstant.DeleteConfiguration);
		
		// Assertion response
		deleteResponse.then().body("status.code", equalTo(GlobalConstant.HTTP_NOT_FOUND));
		deleteResponse.then().body("status.errorMessage",equalTo((GlobalConstant.DATA_NOT_FOUND).replace("d", "D").replace(".", "")));
	}
}
