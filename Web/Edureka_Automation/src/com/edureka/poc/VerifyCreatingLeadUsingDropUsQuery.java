package com.edureka.poc;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class VerifyCreatingLeadUsingDropUsQuery extends DriverTestCase{
    private DashboardPage dashboardPage;
    private SelectedCoursePage selectedCoursePage;
    private SignInModalPage signInModalPage;
    private UserHomePage userHomePage;

    @Test
    public void test_05VerifyCreatingLeadUsingDropUsQuery() throws Exception {

        try {
            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetup();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            // Select Course
            String courseName = propertyReader.readTestData("DataScienceCourse");
            addLog("Select Course");
            selectedCoursePage=dashboardPage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open.");
            selectedCoursePage=selectedCoursePage.verifySelectedCoursePage(courseName);

            // Click on Query Box
            addLog("Click on Query Box");
            selectedCoursePage=selectedCoursePage.clickOnQueryBox();

            // Submit inquiry in drop query box
            addLog("Submit inquiry in drop query box");
            selectedCoursePage=selectedCoursePage.sendQuery();

            // Verify Event Type Via Drop a Query
            addLog("Verify Event Type Via a Drop a Query");
            selectedCoursePage=selectedCoursePage.verifyEventTypeViaDropQuery();

            // Refresh the browser
            addLog("Refresh the browser");
            selectedCoursePage=selectedCoursePage.referhBrowser();

            // Click on Sign in Header
            addLog("Click on Sign in Header");
            signInModalPage=selectedCoursePage.clickSignInHeader();

            // Verify Login is selected as default
            addLog("Verify Login is selected as default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // click on Sign up link
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();

            // Sign up application by submitted query email
            addLog("Sign up application by submitted query email");
            userHomePage=signInModalPage.signUpForSubmittedQuery();

            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyUserPage();
        }
        catch (final Error e) {
            captureScreenshot("test_05VerifyCreatingLeadUsingDropUsQuery");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_05VerifyCreatingLeadUsingDropUsQuery");
            throw e;
        }
    }
}
