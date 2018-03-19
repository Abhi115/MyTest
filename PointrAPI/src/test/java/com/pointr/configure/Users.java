package com.pointr.configure;

import static io.restassured.RestAssured.given;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import org.testng.annotations.Test;

import com.pointr.dataprovider.APIDataProvider;
import com.pointr.utils.BaseTest;
import com.pointr.utils.GlobalConstant;
import com.pointr.utils.RestUtil;
import com.pointr.utils.Utilities;

public class Users extends BaseTest {

//	/**
//	 * Sending GET request to retrieve Users with valid data
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test01_Retrieve_Users_With_Valid_Data() throws Exception {
//
//		Headers allheaders = getAllHeaders(authenticationToken, deviceIdentifier);
//
//		// Send GET request
//		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveUsers);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, "");
//	}
//	
//	/**
//	 * Sending GET request to retrieve Users with invalid Token
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test02_Retrieve_Users_With_Invalid_Token() throws Exception {
//
//		Headers allheaders = getAllHeaders("7836b48b4n09n4072n094n72", deviceIdentifier);
//
//		// Send GET request
//		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveUsers);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
//	
//	/**
//	 * Sending GET request to retrieve Users with blank Token
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test03_Retrieve_Users_With_Blank_Token() throws Exception {
//
//		Headers allheaders = getAllHeaders("", deviceIdentifier);
//
//		// Send GET request
//		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveUsers);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
//	
//	/**
//	 * Sending GET request to retrieve Users with invalid Device Identifier
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test04_Retrieve_Users_With_Invalid_Device_Identifier() throws Exception {
//
//		Headers allheaders = getAllHeaders(authenticationToken, "TestUser360");
//
//		// Send GET request
//		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveUsers);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
//	
//	/**
//	 * Sending GET request to retrieve Users with blank Device Identifier
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test05_Retrieve_Users_With_Blank_Device_Identifier() throws Exception {
//
//		Headers allheaders = getAllHeaders(authenticationToken, "");
//
//		// Send GET request
//		Response response = given().headers(allheaders).when().get(GlobalConstant.RetrieveUsers);
//
//		// Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_UNAUTHORIZED, GlobalConstant.AUTHENTICATION_REQUIRED);
//	}
//	
	/**
	 * Sending post request to add new user with different-2 data set
	 * 
	 * @throws Exception
	 */
	@Test(dataProvider = "RegisterUser", dataProviderClass = APIDataProvider.class)
	public void test06_Add_New_User(String userName, String password, String roleId,int httpCode, String errorMsg) throws Exception {

		Headers allheaders = getAllHeaders(authenticationToken, deviceIdentifier);
		
		//sending post request to add new user
		Response response= given().headers(allheaders).formParam("userName", userName+Utilities.getTimeStamp()).formParam("password", password).formParam("confirmPassword", password)
				.formParam("role_Id",roleId).when().post(GlobalConstant.RegisterUser);
		
		//Assertion response
		RestUtil.verifyPoiResponseData(response, httpCode, errorMsg);
	}
//	
//	/**
//	 * Sending post request to add new user with 100 characters in user name
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test07_Add_New_User_With_100_Characters_In_UserName() throws Exception {
//
//		Headers allheaders = getAllHeaders(authenticationToken, deviceIdentifier);
//		
//		//initialize form data
//		String userName= Utilities.randomString(100);
//		String password= "360test";
//		String roleId= "1";
//		
//		//sending post request to add new user
//		Response response= given().headers(allheaders).formParam("userName", userName).formParam("password", password).formParam("confirmPassword", password)
//				.formParam("role_Id",roleId).when().post(GlobalConstant.RegisterUser);
//		
//		//Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_OK, GlobalConstant.USER_REGISTERED);
//	}
//	
//	/**
//	 * Sending post request to add new user without user name
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test08_Add_New_User_Without_UserName() throws Exception {
//
//		Headers allheaders = getAllHeaders(authenticationToken, deviceIdentifier);
//		
//		//initialize form data
//		String userName= "";
//		String password= "360test";
//		String roleId= "1";
//		
//		//sending post request to add new user
//		Response response= given().headers(allheaders).formParam("userName", userName).formParam("password", password).formParam("confirmPassword", password)
//				.formParam("role_Id",roleId).when().post(GlobalConstant.RegisterUser);
//		
//		//Assertion response
//		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_BAD_REQUEST, GlobalConstant.USER_NAME_REQUIRED);
//	}

	/**
	 * Sending post request to add new user when password and confirm password field does not match
	 * 
	 * @throws Exception
	 */
	@Test
	public void test09_Add_New_User_When_Password_And_ConfirmPassword_DoesNotMatch() throws Exception {

		Headers allheaders = getAllHeaders(authenticationToken, deviceIdentifier);
		
		String userName= "test"+Utilities.getTimeStamp();
		String password= "360test";
		String confirmPassword= "360test"+Utilities.getTimeStamp();
		String roleId= "1";
		
		//sending post request to add new user
		Response response= given().headers(allheaders).formParam("userName", userName).formParam("password", password).formParam("confirmPassword", confirmPassword)
				.formParam("role_Id",roleId).when().post(GlobalConstant.RegisterUser);
		
		//Assertion response
		RestUtil.verifyPoiResponseData(response, GlobalConstant.HTTP_BAD_REQUEST, GlobalConstant.PASSWORD_NOT_MATCHED);
	}
	
	/**
	 * Sending post request to edit password
	 * 
	 * @throws Exception
	 */
	@Test
	public void test10_Edit_Password() throws Exception {

		Headers allheaders = getAllHeaders(authenticationToken, deviceIdentifier);
		
		String userName= "test"+Utilities.getTimeStamp();
		String password= "360test";
		String newPassword= "test@360";
		String roleId= "1";
		
		//sending post request to add new user
		Response saveResponse= given().headers(allheaders).formParam("userName", userName).formParam("password", password).formParam("confirmPassword", password)
				.formParam("role_Id",roleId).when().post(GlobalConstant.RegisterUser);
		
		//Assertion response
		RestUtil.verifyPoiResponseData(saveResponse, GlobalConstant.HTTP_OK, GlobalConstant.USER_REGISTERED);
		
		//sending post request to edit password
		Response editResponse= given().headers(allheaders).formParam("userName", userName).formParam("password", newPassword).formParam("confirmPassword", newPassword)
						.formParam("role_Id",roleId).when().post(GlobalConstant.RegisterUser);
				
		//Assertion response
		RestUtil.verifyPoiResponseData(editResponse, GlobalConstant.HTTP_OK, GlobalConstant.INFORMATION_UPDATED);
	}
	
	/**
	 * Sending post request to edit password with blank in password field
	 * 
	 * @throws Exception
	 */
	@Test
	public void test11_Edit_Password_With_blank_Password_Field() throws Exception {

		Headers allheaders = getAllHeaders(authenticationToken, deviceIdentifier);
		
		String userName= "test"+Utilities.getTimeStamp();
		String password= "360test";
		String newPassword= "";
		String roleId= "1";
		
		//sending post request to add new user
		Response saveResponse= given().headers(allheaders).formParam("userName", userName).formParam("password", password).formParam("confirmPassword", password)
				.formParam("role_Id",roleId).when().post(GlobalConstant.RegisterUser);
		
		//Assertion response
		RestUtil.verifyPoiResponseData(saveResponse, GlobalConstant.HTTP_OK, GlobalConstant.USER_REGISTERED);
		
		//sending post request to edit password
		Response editResponse= given().headers(allheaders).formParam("userName", userName).formParam("password", newPassword).formParam("confirmPassword", newPassword)
						.formParam("role_Id",roleId).when().post(GlobalConstant.RegisterUser);
				
		//Assertion response
		RestUtil.verifyPoiResponseData(editResponse, GlobalConstant.HTTP_BAD_REQUEST, GlobalConstant.PASSWORD_REQUIRED);
	}
	
	/**
	 * Sending post request to edit role type
	 * 
	 * @throws Exception
	 */
	@Test
	public void test12_Edit_Role() throws Exception {

		Headers allheaders = getAllHeaders(authenticationToken, deviceIdentifier);
		
		String userName= "test"+Utilities.getTimeStamp();
		String password= "360test";
		String roleId= "1";
		String newRoleId= "2";
		
		//sending post request to add new user
		Response saveResponse= given().headers(allheaders).formParam("userName", userName).formParam("password", password).formParam("confirmPassword", password)
				.formParam("role_Id",roleId).when().post(GlobalConstant.RegisterUser);
		
		//Assertion response
		RestUtil.verifyPoiResponseData(saveResponse, GlobalConstant.HTTP_OK, GlobalConstant.USER_REGISTERED);
		
		//sending post request to edit role
		Response editResponse= given().headers(allheaders).formParam("userName", userName).formParam("password", password).formParam("confirmPassword", password)
						.formParam("role_Id",newRoleId).when().post(GlobalConstant.RegisterUser);
				
		//Assertion response
		RestUtil.verifyPoiResponseData(editResponse, GlobalConstant.HTTP_OK, GlobalConstant.INFORMATION_UPDATED);
	}
	
}
