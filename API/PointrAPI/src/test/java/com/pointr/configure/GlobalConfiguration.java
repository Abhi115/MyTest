package com.pointr.configure;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import org.testng.annotations.Test;

import com.pointr.dataprovider.APIDataProvider;
import com.pointr.utils.BaseTest;
import com.pointr.utils.GlobalConstant;
import com.pointr.utils.RestUtil;
import com.pointr.utils.Utilities;

public class GlobalConfiguration extends BaseTest{

////	/**
////	 * Sending GET request to retrieve Global Configuration with valid data
////	 * 
////	 * @throws Exception
////	 */
////	@Test
////	public void test01_Retrieve_Global_Configuration_With_Valid_Data() throws Exception {
////
////		Headers allheaders = getAllHeaders(authenticationToken, deviceIdentifier);
////
////		// Send GET request
////		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveGlobalConfiguration);
////
////		// Assertion response
////		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");
////	}
////	
////	/**
////	 * Sending GET request to retrieve Global Configuration with invalid Token
////	 * 
////	 * @throws Exception
////	 */
////	@Test
////	public void test02_Retrieve_Global_Configuration_With_Invalid_Token() throws Exception {
////
////		Headers allheaders = getAllHeaders("7836b48b4n09n4072n094n72", deviceIdentifier);
////
////		// Send GET request
////		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveGlobalConfiguration);
////
////		// Assertion response
////		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
////	}
////	
////	/**
////	 * Sending GET request to retrieve Global Configuration with blank Token
////	 * 
////	 * @throws Exception
////	 */
////	@Test
////	public void test03_Retrieve_Global_Configuration_With_Blank_Token() throws Exception {
////
////		Headers allheaders = getAllHeaders("", deviceIdentifier);
////
////		// Send GET request
////		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveGlobalConfiguration);
////
////		// Assertion response
////		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
////	}
////	
////	/**
////	 * Sending GET request to retrieve Global Configuration with invalid Device Identifier
////	 * 
////	 * @throws Exception
////	 */
////	@Test
////	public void test04_Retrieve_Global_Configuration_With_Invalid_Device_Identifier() throws Exception {
////
////		Headers allheaders = getAllHeaders(authenticationToken, "TestUser360");
////
////		// Send GET request
////		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveGlobalConfiguration);
////
////		// Assertion response
////		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
////	}
////	
////	/**
////	 * Sending GET request to retrieve Global Configuration with blank Device Identifier
////	 * 
////	 * @throws Exception
////	 */
////	@Test
////	public void test05_Retrieve_Global_Configuration_With_Blank_Device_Identifier() throws Exception {
////
////		Headers allheaders = getAllHeaders(authenticationToken, "");
////
////		// Send GET request
////		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveGlobalConfiguration);
////
////		// Assertion response
////		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
////	}
//	
//	/** 
//	 * Sending put request to add global configuration with different-2 data set
//	 * @param key
//	 * @param value
//	 * @param httpCode
//	 * @param errorMsg
//	 * @throws Exception
//	 */
//	@Test(dataProvider = "GlobalConfigurationSaveTestData", dataProviderClass = APIDataProvider.class)
//	public void test06_Add_Global_Web_Configuration(String parameterName, Object parameterValue,String parameterDataType,int httpCode, String errorMsg) throws Exception 
//	{
//		Headers headers = getAllHeaders(authenticationToken, "TestUser");
//		String paramName= parameterName+Utilities.getTimeStamp();
//		Object paramValue= parameterValue;
//		
//		// Send put request
//		Response saveResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
//				.formParam("ParameterDataType",parameterDataType).when().put(GlobalConstant.SaveGlobalConfiguration);
//		
//		// Assertion response
//		RestUtil.verifyPoiResponseData(saveResponse, httpCode, errorMsg);
//	}
//	
//	/**
//	 * Sending put request to add Web Configuration with invalid Token
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test07_Add_Global_Configuration_With_Invalid_Token() throws Exception {
//
//		Headers allheaders = getAllHeaders("7836b48b4n09n4072n094n72", deviceIdentifier);
//
//		// Send put request
//		Response response = given().headers(allheaders).when().put(GlobalConstant.SaveGlobalConfiguration);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
//	
//	/**
//	 * Sending put request to add Web Configuration with blank Token
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test08_Add_Global_Configuration_With_Blank_Token() throws Exception {
//
//		Headers allheaders = getAllHeaders("", deviceIdentifier);
//
//		// Send put request
//		Response response = given().headers(allheaders).when().put(GlobalConstant.SaveGlobalConfiguration);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
//	
//	/**
//	 * Sending put request to add Web Configuration with invalid Device Identifier
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test09_Add_Global_Configuration_With_Invalid_Device_Identifier() throws Exception {
//
//		Headers allheaders = getAllHeaders(authenticationToken, "TestUser360");
//
//		// Send put request
//		Response response = given().headers(allheaders).when().put(GlobalConstant.SaveGlobalConfiguration);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
//	
//	/**
//	 * Sending put request to add Web Configuration with blank Device Identifier
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test10_Add_Global_Configuration_With_Blank_Device_Identifier() throws Exception {
//
//		Headers allheaders = getAllHeaders(authenticationToken, "");
//
//		// Send put request
//		Response response = given().headers(allheaders).when().put(GlobalConstant.SaveGlobalConfiguration);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
//	
//	/**
//	 * Sending put request to add Web Configuration with blank parameter
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test11_Add_Global_Web_Configuration_With_Blank_Parameter_Name() throws Exception 
//	{
//		Headers headers = getAllHeaders(authenticationToken, "TestUser");
//		
//		String paramName= "";
//		String paramValue= "test";
//		String parameterDataType= "String";
//		
//		// Send put request
//		Response saveResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
//				.formParam("ParameterDataType",parameterDataType).when().put(GlobalConstant.SaveGlobalConfiguration);
//		
//		// Assertion response
//		RestUtil.verifyPoiResponseData(saveResponse,GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.DESCRIPTION_REQUIRED);
//	}
//	
//	/**
//	 * Sending put request to add Web Configuration with existing parameter
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
//		// Send put request
//		Response saveResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
//				.formParam("ParameterDataType",parameterDataType).when().put(GlobalConstant.SaveGlobalConfiguration);
//		
//		// Assertion response
//		RestUtil.verifyPoiResponseData(saveResponse,GlobalConstant.HTTP_OK , "");
//		
//		// Send put request with existing parameter
//		Response response = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
//						.formParam("ParameterDataType",parameterDataType).when().put(GlobalConstant.SaveGlobalConfiguration);
//		
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response,GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.EXISTING_PARAMETER_NAME);
//	}
//	
//	/**
//	 * Sending put request to add Web Configuration with blank values
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test13_Add_Global_Web_Configuration_With_Blank_Values() throws Exception 
//	{
//		Headers headers = getAllHeaders(authenticationToken, "TestUser");
//		
//		String paramName= "test";
//		String paramValue= "";
//		String parameterDataType= "Boolean";
//		
//		// Send put request
//		Response saveResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
//				.formParam("ParameterDataType",parameterDataType).when().put(GlobalConstant.SaveGlobalConfiguration);
//		
//		// Assertion response
//		RestUtil.verifyPoiResponseData(saveResponse,GlobalConstant.HTTP_BAD_REQUEST ,GlobalConstant.VALUE_REQUIRED);
//	
//	}
	
	/**
	 * Sending put request to edit global Configuration with different-2 data set 
	 * 
	 * @throws Exception
	 */
	@Test(dataProvider = "GlobalConfigurationEditTestData", dataProviderClass = APIDataProvider.class)
	public void test14_Edit_Global_Configuration(String parameterName, Object parameterValue,String parameterDataType,Object editValue,int httpCode, String errorMsg) throws Exception 
	{
		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		String paramName= parameterName+Utilities.getTimeStamp();
		Object paramValue= parameterValue;
		
		// Send put request to add global configuration
		Response saveResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
				.formParam("ParameterDataType",parameterDataType).when().put(GlobalConstant.SaveGlobalConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(saveResponse, httpCode, errorMsg);
		
		// Send GET request
		Response getResponse = given().headers(headers).when().get(GlobalConstant.RetrieveGlobalConfiguration);
				
		// Assertion response
		RestUtil.verifyPoiResponseData(getResponse, httpCode, errorMsg);
		
		int totalSize= getResponse.body().path("body.size()");
		int id= getResponse.body().path("body.id["+(totalSize-1)+"]");
		getResponse.then().body("body.name["+(totalSize-1)+"]", equalTo(paramName));
		
		// Send put request to edit global configuration
		Response editResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",editValue)
						.formParam("ParameterDataType",parameterDataType).formParam("id",id).when().put(GlobalConstant.EditGlobalConfiguration);
		
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
		
		// Send put request to add global configuration
		Response saveResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
				.formParam("ParameterDataType",parameterDataType).when().put(GlobalConstant.SaveGlobalConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(saveResponse,GlobalConstant.HTTP_OK, "");
		
		// Send GET request
		Response getResponse = given().headers(headers).when().get(GlobalConstant.RetrieveGlobalConfiguration);
				
		// Assertion response
		RestUtil.verifyPoiResponseData(getResponse, GlobalConstant.HTTP_OK, "");
		
		int totalSize= getResponse.body().path("body.size()");
		int id= getResponse.body().path("body.id["+(totalSize-1)+"]");
		getResponse.then().body("body.name["+(totalSize-1)+"]", equalTo(paramName));
		
		// Send put request to edit global configuration
		Response editResponse = given().headers(headers).formParam("ParameterDescription", newParamName).formParam("ParameterValue",paramValue)
						.formParam("ParameterDataType",parameterDataType).formParam("id",id).when().put(GlobalConstant.EditGlobalConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(editResponse, GlobalConstant.HTTP_OK, "");
	}
	
	/**
	 * Sending delete request to delete global configuration 
	 * 
	 * @throws Exception
	 */
	@Test
	public void test16_Delete_Global_Configuration() throws Exception 
	{
		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		String paramName= "test"+Utilities.getTimeStamp();
		Object paramValue= "test";
		String parameterDataType= "String";
		
		// Send put request to add global configuration
		Response saveResponse = given().headers(headers).formParam("ParameterDescription", paramName).formParam("ParameterValue",paramValue)
				.formParam("ParameterDataType",parameterDataType).when().put(GlobalConstant.SaveGlobalConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(saveResponse,GlobalConstant.HTTP_OK, "");
		
		// Send GET request
		Response getResponse = given().headers(headers).when().get(GlobalConstant.RetrieveGlobalConfiguration);
				
		// Assertion response
		RestUtil.verifyPoiResponseData(getResponse, GlobalConstant.HTTP_OK, "");
		
		// Send delete request to delete global configuration
		Response deleteResponse = given().headers(headers).formParam("configurationDescription", paramName)
				.delete(GlobalConstant.DeleteGlobalConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(deleteResponse, GlobalConstant.HTTP_OK, "");
	}
	
	/**
	 * Sending delete request to delete global configuration with blank token
	 * 
	 * @throws Exception
	 */
	@Test
	public void test17_Delete_Global_Configuration_With_Blank_Token() throws Exception 
	{
		Headers headers = getAllHeaders("", "TestUser");
		String paramName= "test"+Utilities.getTimeStamp();

		// Send delete request to delete global configuration
		Response deleteResponse = given().headers(headers).formParam("configurationDescription", paramName)
				.delete(GlobalConstant.DeleteGlobalConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(deleteResponse,GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending delete request to delete global configuration with invalid token
	 * 
	 * @throws Exception
	 */
	@Test
	public void test18_Delete_Global_Configuration_With_Invalid_Token() throws Exception 
	{
		Headers headers = getAllHeaders("7836b48b4n09n4072n094n72", "TestUser");
		String paramName= "test"+Utilities.getTimeStamp();

		// Send delete request to delete global configuration
		Response deleteResponse = given().headers(headers).formParam("configurationDescription", paramName)
				.delete(GlobalConstant.DeleteGlobalConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(deleteResponse,GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending delete request to delete global configuration with blank device Identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void test19_Delete_Global_Configuration_With_Blank_Device_Identifier() throws Exception 
	{
		Headers headers = getAllHeaders(authenticationToken, "");
		String paramName= "test"+Utilities.getTimeStamp();

		// Send delete request to delete global configuration
		Response deleteResponse = given().headers(headers).formParam("configurationDescription", paramName)
				.delete(GlobalConstant.DeleteGlobalConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(deleteResponse,GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending delete request to delete global configuration with invalid device Identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void test20_Delete_Global_Configuration_With_Invalid_Device_Identifier() throws Exception 
	{
		Headers headers = getAllHeaders(authenticationToken, "TestUser360");
		String paramName= "test"+Utilities.getTimeStamp();

		// Send delete request to delete global configuration
		Response deleteResponse = given().headers(headers).formParam("configurationDescription", paramName)
				.delete(GlobalConstant.DeleteGlobalConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(deleteResponse,GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
	}
	
	/**
	 * Sending delete request to delete global configuration with non existing parameter name
	 * 
	 * @throws Exception
	 */
	@Test
	public void test21_Delete_Global_Configuration_With_Non_Existing_Parameter() throws Exception 
	{
		Headers headers = getAllHeaders(authenticationToken, "TestUser");
		String paramName= "test"+Utilities.getTimeStamp();

		// Send delete request to delete global configuration
		Response deleteResponse = given().headers(headers).formParam("configurationDescription", paramName)
				.delete(GlobalConstant.DeleteGlobalConfiguration);
		
		// Assertion response
		RestUtil.verifyPoiResponseData(deleteResponse,GlobalConstant.HTTP_INTERNAL_SERVER_ERROR, GlobalConstant.InternalServerError);
	}
}
