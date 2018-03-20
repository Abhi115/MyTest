package com.edureka.referralProcess;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.MyProfilePage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.RazorPayPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class VerifyNameEmailInUserTableIfUserRegisteredOurWebsite extends DriverTestCase{
    private DashboardPage dashboardPage;
    private UserHomePage userHomePage;
    private SelectedCoursePage selectedCoursePage;
    private OrderSummaryPage orderSummaryPage;
    private SignInModalPage signInModalPage;
    private RazorPayPage razorPayPage;
    private MyProfilePage myProfilePage;

    static String email;
    static String userName;
    static String referralEmail;
    static String referralLink;
    static String courseName;
    static String currency;
    static String bankName;

    @Test
    public void test_004VerifyCampaignAndLevelAssignment() throws Exception {

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

            // Verify LogIn Is Default
            addLog("Verify LogIn Is Default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // click on Sign up link
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();

            String edurekaDomain = propertyReader.readApplicationFile("EdurekaDomain");
            addLog("Sign up user");
            userHomePage= signInModalPage.signUp(UserHomePage.class, edurekaDomain);

            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyUserPage();

            // Select Course
            courseName = propertyReader.readTestData("BigData&Hadoop");
            addLog("Select Course " + courseName);
            selectedCoursePage=userHomePage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);

            // Click on Enroll Button
            addLog("Click on Enroll Button");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();

            // Change Currency
            currency= propertyReader.readTestData("INRCurrency");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Click on Razor pay securely button
            addLog("Click on Razor pay securely button");
            razorPayPage= orderSummaryPage.clickOnRazorPaySecurelyButton();

            // Verify Razor page
            addLog("Verify Razor page");
            razorPayPage=razorPayPage.verifyPage();

            // Click on Net Banking tab
            addLog("Click on Net Banking tab");
            razorPayPage=razorPayPage.clickOnNetBankingTab();

            // Select bank
            bankName = propertyReader.readTestData("Bank");
            addLog("Select Bank" +bankName +" from bank table ");
            razorPayPage=razorPayPage.selectBank(bankName);

            // Click on pay button
            addLog("Click on pay button");
            razorPayPage=razorPayPage.clickOnPayButton();

            // Get Parent window Id
            addLog("Get Parent Window ID");
            String parentWidnow = getParentWindowHandle();

            // Switch Child Window and Click on Succss button
            addLog("Switch Child Window and Click on Succss button");
            switchPreviewWindow();
            myProfilePage=razorPayPage.clickOnSuccessButton();

            // Switch to Parent Window and verify my Profile page
            addLog("Switch to Parent Window and verify my Profile page");
            switchParentWindow(parentWidnow);
            myProfilePage= myProfilePage.verifyPage();

            // Verify Payment success message
            addLog("Verify payment success message 'Thank you for making payment !'");
            myProfilePage= myProfilePage.verifySuccessPaymentMessage();

            // Click on Referral Tab
            addLog("Click on Referral Tab");
            myProfilePage=myProfilePage.clickOnReferralTab();

            // Get campaign and level Id from Ambassador table
            String email = propertyReader.readRunTimeData("Email_Id");
            addLog("Get campaign and level Id from Ambassador table");
            myProfilePage=myProfilePage.getCampaignAndLevelIdFromAmbassadorTable(email);

            //Verify Campaign and level Assignment
            String campaign_Id = propertyReader.readRunTimeData("Campaign_Id");
            String level_Id = propertyReader.readRunTimeData("Level_Id");
            addLog("Verify Campaign and level Assignment");
            myProfilePage=myProfilePage.verifyCampaignAndLevelAssignment(campaign_Id, level_Id);

        }
        catch (Error e) {
            captureScreenshot("test_004VerifyCampaignAndLevelAssignment");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_004VerifyCampaignAndLevelAssignment");
            throw e;
        }
    }

    @Test(dependsOnMethods={"test_004VerifyCampaignAndLevelAssignment"})
    public void test_017VerifyNameEmailInUserTableIfUserRegisteredOurWebsite() throws Exception {

        try {

            // Click on Refer Friend button
            addLog("Click on Refer Friend button");
            myProfilePage= myProfilePage.clickOnReferButton();

            // Enter Email
            referralEmail= randomString(3)+"@tech.edureka.in";
            propertyReader.updatePropertyTestData("RefferralEmail", referralEmail);
            addLog("Enter email "+referralEmail);
            myProfilePage=myProfilePage.referFriend(referralEmail);

            // Get Referral Link
            referralLink = myProfilePage.copyReferralLink();
            addLog("Copy Referral Link "+ referralLink);

            // Verify Success Message for sent refferral mail
            addLog("Verify Success Message for sent refferral mail");
            myProfilePage=myProfilePage.VerifySuccessRefferralMailSentMsg();

            String referralEmail_2 = randomString(4)+"@tech.edureka.in";
            myProfilePage=myProfilePage.referFriend(referralEmail_2);

            // Verify Success Message for sent refferral mail
            addLog("Verify Success Message for sent refferral mail");
            myProfilePage=myProfilePage.VerifySuccessRefferralMailSentMsg();

            // Close Refer pop up
            addLog("Close Refer pop up");
            myProfilePage=myProfilePage.closeReferPopUp();

            // Click on Profile Dropdown
            userName = propertyReader.readRunTimeData("UserName");
            Thread.sleep(5000);
            addLog("Click on Profile Dropdown");
            myProfilePage=myProfilePage.clickOnProfileDropdown(userName);  

            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = myProfilePage.logout();  

            // Verify Dashboard
            addLog("Verify Dashboard");
            dashboardPage=dashboardPage.verifyDashboard();

            // Navigate to the Referral Link
            addLog("Navigate to the "+referralLink);
            dashboardPage=dashboardPage.changeUrl(referralLink);

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify LogIn Is Default
            addLog("Verify LogIn Is Default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // click on Sign up link
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();

            //Sign up
            userName = propertyReader.readTestData("UserName");
            email = propertyReader.readRunTimeData("RefferralEmail");
            String phNumber = propertyReader.readTestData("PhoneNumber");
            String password = propertyReader.readTestData("Password");
            addLog("Sign up user B who is refferrad by A in TC_001");
            signInModalPage=signInModalPage.enterUserNameEmailAndPhoneNumnber(userName,email,phNumber);
            signInModalPage=signInModalPage.enterPassword(password);

            // Click on Sign create Account button
            addLog("Click on Sign create Account button");
            userHomePage=signInModalPage.clickOnCreateAccountButton();

            //Verify Edureka logo at at Home page
            addLog("Verify Edureka logo at at All Courses page");
            userHomePage=userHomePage.verifyEdurekaLogoOnHomePage();

            // Select Course
            courseName = propertyReader.readTestData("BigData&Hadoop");
            addLog("Select Course " +courseName);
            Thread.sleep(5000);
            selectedCoursePage=userHomePage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);

            // Click on Enroll Button
            addLog("Click on Enroll Button");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();

            // Change Currency
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Verify total Amount for USD is equal to Net price
            addLog("verify Total amount");
            orderSummaryPage=orderSummaryPage.verifyTotalAmount(currency);

            // Click on Razor pay securely button
            addLog("Click on Razor pay securely button");
            razorPayPage= orderSummaryPage.clickOnRazorPaySecurelyButton();

            // Verify Razor page
            addLog("Verify Razor page");
            Thread.sleep(5000);
            razorPayPage=razorPayPage.verifyRazorForReferral();

            // Click on Net Banking tab
            addLog("Click on Net Banking tab");
            razorPayPage=razorPayPage.clickOnNetBankingTab();

            // Select bank
            addLog("Select Bank " +bankName +" from bank table ");
            razorPayPage=razorPayPage.selectBank(bankName);

            // Click on pay button
            addLog("Click on pay button");
            razorPayPage=razorPayPage.clickOnPayButton();

            // Get Parent window Id
            addLog("Get Parent Window ID");
            String parentWidnow = getParentWindowHandle();

            // Switch Child Window and Click on Succss button
            addLog("Switch Child Window and Click on Succss button");
            switchPreviewWindow();
            myProfilePage=razorPayPage.clickOnSuccessButton();

            // Switch to Parent Window and verify my Profile page
            addLog("Switch to Parent Window and verify my Profile page");
            switchParentWindow(parentWidnow);
            myProfilePage= myProfilePage.verifyPage();

            // Verify Payment success message
            addLog("Verify payment success message 'Thank you for making payment !'");
            myProfilePage= myProfilePage.verifySuccessPaymentMessage();

            // Click on Referral Tab
            addLog("Click on Referral Tab");
            myProfilePage=myProfilePage.clickOnReferralTab();

            // Click on Refer Friend button
            addLog("Click on Refer Friend button");
            myProfilePage= myProfilePage.clickOnReferButton();

            // Get Referral Link
            referralLink = myProfilePage.copyReferralLink();
            addLog("Copy Referral Link "+ referralLink);

            // Close Refer pop up
            addLog("Close Refer pop up");
            myProfilePage=myProfilePage.closeReferPopUp();

            // Click on Profile Dropdown
            Thread.sleep(5000);
            addLog("Click on Profile Dropdown");
            myProfilePage=myProfilePage.clickOnProfileDropdown(userName);  

            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = myProfilePage.logout();  

            // Verify Dashboard
            addLog("Verify Dashboard");
            dashboardPage=dashboardPage.verifyDashboard();

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify LogIn Is Default
            addLog("Verify LogIn Is Default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // login user A
            addLog("Login user A");
            userHomePage= signInModalPage.loginApp();

            //Verify User Page
            addLog("Verify User Page");
            userHomePage = userHomePage.verifyUserPage();

            // Click on Profile Dropdown
            userName = propertyReader.readRunTimeData("UserName");
            addLog("Click profile dropdown");
            userHomePage= userHomePage.clickOnProfileDropdown(userName);

            // Select My Profile
            addLog("Select My Profile");
            myProfilePage=userHomePage.clickOnMyProfile();

            // Click on Referral Tab
            addLog("Click on Referral Tab");
            myProfilePage=myProfilePage.clickOnReferralTab();

            // Click on Referral Tab
            addLog("Click on Referral Tab");
            myProfilePage=myProfilePage.clickOnViewDetails();

            // Verify if user B is registered to our website then user's first name and last name should be there else email id of user B
            addLog("if user B is registered to our website then user's first name and last name should be there else email id of user B");
            myProfilePage=myProfilePage.verifyUserRegisteredOrNot(referralEmail);
            myProfilePage=myProfilePage.verifyUserRegisteredOrNot(referralEmail_2);
        }
        catch (Error e) {
            captureScreenshot("test_011VerifyValueOfCreditAvailableInsideReferralTab");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_011VerifyValueOfCreditAvailableInsideReferralTab");
            throw e;
        }
    }
}