package com.edureka.registration;

import org.testng.annotations.Test;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class ClickPasswordResetAndDontResetPassword extends DriverTestCase{
    private DashboardPage dashboardPage;
    private SignInModalPage signInModalPage;
    private UserHomePage userHomePage;

    static String email;
    static String password;
    @Test
    public void test_036ClickPasswordResetAndDontResetPassword() throws Exception {

        try {

            // Navigate to app url
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

            // Click on forgot password link
            addLog("Click on forgot password link");
            signInModalPage=signInModalPage.clickOnForgotPassword();

            // Verify Login is selected as default
            addLog("Verify Login is selected as default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // Enter incorrect email
            email = propertyReader.readRunTimeData("Email_Id");
            password= null;
            addLog("Enter correct email and incorrect password");
            signInModalPage= signInModalPage.enterEmailAndPassword(email, password);

            // Click on forgot password link
            addLog("Click on forgot password link");
            signInModalPage=signInModalPage.clickOnForgotPassword();

            //Verify the form when verification code has been sent on given email Id
            addLog("Verify the form when verification code has been sent on given email Id");
            signInModalPage=signInModalPage.verifySuccessMessageInGreen();

            // Close sign in module
            addLog("Close sign in module");
            dashboardPage=  signInModalPage.closeSignInModule();

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


        } catch (final Error e) {
            captureScreenshot("test_036ClickPasswordResetAndDontResetPassword");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_036ClickPasswordResetAndDontResetPassword");
            throw e;
        }
    }
}