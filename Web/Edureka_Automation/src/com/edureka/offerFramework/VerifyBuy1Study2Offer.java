package com.edureka.offerFramework;

import org.testng.annotations.Test;

import com.edureka.pages.AdminAddOfferPage;
import com.edureka.pages.AdminDashboard;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class VerifyBuy1Study2Offer extends DriverTestCase{
    private DashboardPage dashboardPage;
    private SignInModalPage signInModalPage;
    private AdminDashboard adminDashboard;
    private UserHomePage userHomePage;
    private AdminAddOfferPage adminAddOfferPage;
    private SelectedCoursePage selectedCoursePage;

    static String email;
    static String password;

    @Test
    public void test_04VerifyBuy1Study2Offer() throws Exception {

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

            // Login Application through Admin credentials
            email = propertyReader.readApplicationFile("Admin_UserName");
            password = propertyReader.readApplicationFile("Admin_Password");
            addLog("Login Application through Admin credentials");
            signInModalPage= signInModalPage.enterEmailAndPassword(email, password);

            // Click on Start Learning Course button
            addLog("Click on Start Learning Course button");
            userHomePage=signInModalPage.clickStartLearningButton(UserHomePage.class);

            // Verify Admin User Home page
            addLog("Verify Admin User Home page");
            userHomePage=userHomePage.verifyAdminUserPage();

            // Click on Profile dropdown
            String userName = propertyReader.readTestData("Admin_UserName");
            addLog("Click on Profile dropdown");
            userHomePage= userHomePage.clickOnProfileDropdown(userName);

            // Select Admin from Prfile Dropdown
            addLog("Select Admin from Profile dropdown");
            adminDashboard=userHomePage.selectAdmin();

            // Verify Admin Dashboard
            addLog("Verify Admin Dashboard");
            adminDashboard= adminDashboard.verifyAdminDashboard();

            // Select Menu Tab
            String menuTab = propertyReader.readTestData("Tab_Offer_Admin");
            addLog("Select Menu Tab " +menuTab);
            adminDashboard=adminDashboard.selectMenuTab(menuTab);

            // Select Create Offer from Offer Admin
            String createOffer= propertyReader.readTestData("Create_Offer");
            addLog("Select Create Offer from offer Admin");
            adminAddOfferPage=adminDashboard.selectOptionFromAdminOfferTab(createOffer);

            // Verify Admin Add Offer page
            addLog("Verify Admin Add Offer Page");
            adminAddOfferPage = adminAddOfferPage.verifyAdminAddOfferPage();

            // Create Flat Discount
            String offerType = propertyReader.readTestData("B1SN_Discount_Type");
            String discountTitle= propertyReader.readTestData("Title_Discount");
            addLog("Create Flat Discount");
            adminAddOfferPage=adminAddOfferPage.createDiscount(offerType, discountTitle);

            // Check User, Customer and Repeat Customer check boxes
            addLog("Check User, Customer and Repeat Customer check boxes");
            adminAddOfferPage=adminAddOfferPage.checkUser_Customer_RepeatCustomer();

            // Click on Submit Button
            addLog("Click on Submit Button");
            adminAddOfferPage=adminAddOfferPage.clickSubmitButton();

            String course = propertyReader.readTestData("Course_AndroidDevelopment");
            String pinkMessage = adminAddOfferPage.getPinkBannerMessage(course);

            // select Banner Header
            addLog("Select Banner Header");
            adminAddOfferPage=adminAddOfferPage.selectBannerHeader();

            // Get Parent window Id
            addLog("Get Parent Window ID");
            String parentWidnow = getParentWindowHandle();

            // Switch window and Select banner
            String bannerType = propertyReader.readTestData("Banner_Holy");
            addLog("Select Banner " +bannerType);
            switchPreviewWindow();
            adminAddOfferPage=adminAddOfferPage.selectBanner(bannerType);

            // Switch to Parent Window and select Enter Message Near Upcoming Batches
            addLog("Switch to Parent Window and select Enter Message Near Upcoming Batches");
            switchParentWindow(parentWidnow);
            adminAddOfferPage= adminAddOfferPage.enterMessageNearUpcomingBatches();

            // Enter Banner Text
            String bannerText = propertyReader.readTestData("Banner_Text");
            addLog("Enter Banner Text");
            switchPreviewWindow();
            adminAddOfferPage= adminAddOfferPage.enterBannerText(bannerText);

            // Get and Update Offer Id
            addLog("Get and Update Offer Id");
            switchParentWindow(parentWidnow);
            adminAddOfferPage=adminAddOfferPage.getOfferId();

            // Select Status
            String status = propertyReader.readTestData("Active_Status");
            addLog("select "+status+" from status dropdown");
            adminAddOfferPage= adminAddOfferPage.selectStatus(status);

            // Click on Edureka Logo
            addLog("Click on Edureka Logo");
            userHomePage=adminAddOfferPage.clickOnLogo();

            // Click on Profile dropdown
            addLog("Click on Profile dropdown");
            userHomePage= userHomePage.clickOnProfileDropdown(userName);

            // Logout Application
            addLog("Logout Application");
            dashboardPage=userHomePage.logout();

            // Navaigte to Course Landing Page
            String course_DataScience = propertyReader.readTestData("DataScienceCourse");
            addLog("Navaigte to Course Landing Page");
            selectedCoursePage=dashboardPage.selectCourse(course_DataScience);

            // Verify Data 
            addLog("Verify Data");
            selectedCoursePage=selectedCoursePage.verifyDataForOfferFramework(discountTitle);

            // Search Course
            addLog("Search Course " +course);
            selectedCoursePage=selectedCoursePage.searchCourse(course);

            // Verify Banner is appearing
            String banner = propertyReader.readTestData("Banner_Holy_Image");
            addLog("Verify "+banner+" Banner is appearing");
            selectedCoursePage=selectedCoursePage.verifyBanner(banner);

            // Verify Pink Message 
            addLog("Verify "+pinkMessage+ " under selected course");
            selectedCoursePage=selectedCoursePage.verifyPinkMessage(pinkMessage);
            
            // Verify Data for offer framework
            addLog("Verify Data for offer framework");
            selectedCoursePage= selectedCoursePage.verifyDataForOfferFramework(discountTitle);

            // Verify There must be 6 entries with currency from 1-6
            addLog("Verify There must be 6 entries with currency from 1-6");
            selectedCoursePage= selectedCoursePage.currencyVerificationInDatabase_For_OfferFramework();

        }

        catch (final Error e) {
            captureScreenshot("test_04VerifyBuy1Study2Offer");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_04VerifyBuy1Study2Offer");
            throw e;
        }
    }
}
