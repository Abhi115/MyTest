package com.edureka.registration;

import org.testng.annotations.Test;

import com.edureka.pages.ChangePasswordPage;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class VerifyUserAbleToSuccessfullyChangeThePassword extends DriverTestCase{
    private DashboardPage dashboardPage;
    private SignInModalPage signInModalPage;
    private UserHomePage userHomePage;
    private ChangePasswordPage changePasswordPage;

    static String email;
    static String password;
    static String exisitngPassword;
    static String newPassword;

    @Test
    public void test_20VerifyErrorForWrongExistingPasswordAndCorrectNewPassword() throws Exception {

        try {

            // Navigate to app url]
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetup();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify Login is selected as default
            addLog("Verify Login is selected as default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // Login Application
            addLog(" Login Application");
            userHomePage= signInModalPage.loginApp();

            // Verify User Home Page
            addLog("User has logged in successfully and Verify User Home Page");
            userHomePage=userHomePage.verifyUserPage();

            // Click on Profile Dropdown
            String userName = propertyReader.readRunTimeData("UserName");
            addLog("Click on Profile Dropdown");
            userHomePage=userHomePage.clickOnProfileDropdown(userName);

            // Click on Change Password Link
            addLog("Click on Change Password Link");
            changePasswordPage= userHomePage.clickOnchangePasswordLink();

            // Verify Change Password Page
            addLog("Verify Change Password Page");
            changePasswordPage = changePasswordPage.verifyChangPasswordPage();

            // Enter wrong existing Password and correct new password
            exisitngPassword= propertyReader.readTestData("InCorrectPassword");
            newPassword= propertyReader.readTestData("NewPassword");
            newPassword= newPassword+randomString(2);
            addLog("Enter wrong existing Password and correct new password");
            changePasswordPage = changePasswordPage.changePassword(exisitngPassword,newPassword);

            // Verify Error message for Enter wrong existing Password and correct new password
            String errorMessage = propertyReader.readTestData("ErrorForWrongExistingPassword");
            addLog("Verify Error message for Enter wrong existing Password and correct new password");
            changePasswordPage = changePasswordPage.verifyErrorForChangePassword(errorMessage);


        }
        catch (final Error e) {
            captureScreenshot("test_20VerifyErrorForWrongExistingPasswordAndCorrectNewPassword");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_20VerifyErrorForWrongExistingPasswordAndCorrectNewPassword");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_20VerifyErrorForWrongExistingPasswordAndCorrectNewPassword"})
    public void test_019VerifyUserAbleToSuccessfullyChangeThePassword() throws Exception {

        try {
            // Change Password
            exisitngPassword= propertyReader.readTestData("Password");
            addLog("Change Password");
            changePasswordPage = changePasswordPage.changePassword(exisitngPassword, newPassword);

            // Verify Password has been changed successfully
            addLog("Verify Password has been changed successfully");
            String succesMessage = propertyReader.readTestData("ChangePasswordMessage");
            changePasswordPage = changePasswordPage.verifyPasswordChanged(succesMessage);

            // Click on Profile Dropdown
            String userName = propertyReader.readRunTimeData("UserName");
            addLog("Click on Profile Dropdown");
            changePasswordPage=changePasswordPage.clickOnProfileDropdown(userName);  

            // Logout Application
            addLog("Logout Application");
            dashboardPage=changePasswordPage.logout();

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify LogIn Is Default
            addLog("Verify LogIn Is Default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // Enter email and password
            email = propertyReader.readRunTimeData("Email_Id");
            password = propertyReader.readTestData("Password");
            addLog("Enter email and password");
            signInModalPage= signInModalPage.enterEmailAndPassword(email, password);

            //  Verify user is not able to Logged in with old password
            String errorMessage = propertyReader.readTestData("ErrorMessageForInvalidCredetials");
            addLog("Verify user is not able to Logged in with old password");
            signInModalPage=  signInModalPage.verifyErrorForLogIn(errorMessage);

            // Enter email and password
            addLog("Enterl null in email and password fields");
            signInModalPage= signInModalPage.enterEmailAndPassword(email, newPassword);

            //  Click On Start Learning Button 
            addLog("Click on Start Learning button");
            userHomePage =  signInModalPage.clickStartLearningButton(UserHomePage.class);

            // Verify User able to Login with new updated password
            addLog("Verify User able to Login with new updated password");
            userHomePage=userHomePage.verifyUserPage();
            
        }
        catch (final Error e) {
            captureScreenshot("test_019VerifyUserAbleToSuccessfullyChangeThePassword");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_019VerifyUserAbleToSuccessfullyChangeThePassword");
            throw e;
        }
    }
}