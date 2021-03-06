package com.edureka.registration;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.Mailnator;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class VerifyResendOfVerificationCodeFunctionality extends DriverTestCase{
    private DashboardPage dashboardPage;
    private UserHomePage userHomePage;
    private SignInModalPage signInModalPage;
    private Mailnator mailnator;

    @Test
    public void test_028VerifyResendOfVerificationCodeFunctionality() throws Exception {

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

            // click on Sign up link
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();

            // Sign up user
            String domainName= propertyReader.readApplicationFile("MailnatorDomain");
            addLog("Sign up user");
            userHomePage= signInModalPage.signUp(UserHomePage.class,domainName);

            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyUserPage();

            // Click on Profile dropdown
            String userName = propertyReader.readRunTimeData("UserName");
            addLog("Click on Profile dropdown");
            userHomePage= userHomePage.clickOnProfileDropdown(userName);

            // Logout Application
            addLog("Logout Application");
            dashboardPage=userHomePage.logout();

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify Login is selected as default
            addLog("Verify Login is selected as default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // Enter incorrect email
            String email = propertyReader.readRunTimeData("Email_Id");
            String password= null;
            addLog("Enter correct email and incorrect password");
            signInModalPage= signInModalPage.enterEmailAndPassword(email, password);

            // Click on forgot password link
            addLog("Click on forgot password link");
            signInModalPage=signInModalPage.clickOnForgotPassword();

            //Verify the form when verification code has been sent on given email Id
            addLog("Verify the form when verification code has been sent on given email Id");
            signInModalPage=signInModalPage.verifySuccessMsgForCode(email);

            // Click on Resend Password Link
            addLog("Click on Resend Password Link");
            signInModalPage=signInModalPage.clickOnResendLink();

            // Verify Verification code field and New password field is display
            addLog("Verify Verification code field and New password field is display");
            signInModalPage=signInModalPage.verifyVerificationCodeAndNewPasswordFields();

            // Navigate to mailnator
            addLog("Navigate to mailnator");
            mailnator = signInModalPage.navigateToMailnator();

            // Enter email
            email = propertyReader.readRunTimeData("Email_Id");
            addLog("Enter email "+email);
            mailnator = mailnator.enterEmail(email);

            // open Email
            addLog("Verify Multiple mails in Inbox");
            mailnator = mailnator.verifyMultipleMailsInInbox();


        }
        catch (final Error e) {
            captureScreenshot("test_028VerifyResendOfVerificationCodeFunctionality");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_028VerifyResendOfVerificationCodeFunctionality");
            throw e;
        }
    }
}