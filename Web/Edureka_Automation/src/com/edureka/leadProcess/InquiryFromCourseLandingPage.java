package com.edureka.leadProcess;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.util.DriverTestCase;

public class InquiryFromCourseLandingPage extends DriverTestCase{
    private DashboardPage dashboardPage;
    private SelectedCoursePage selectedCoursePage;

    @Test
    public void test_020InquiryFromCourseLandingPage() throws Exception {

        try {
            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetupForLead();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            //Select a course
            String course= propertyReader.readTestData("Course_SplunkDev_Admin");
            addLog("Select course "+course);
            selectedCoursePage= dashboardPage.selectCourse_NewAdd(course);

            // Verify Selected Course Page Is Open
            addLog("Verify Selected Course Page Is Open");
            selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(course);

            // Click on Query Box
            addLog("Click on Query Box");
            selectedCoursePage=selectedCoursePage.clickOnQueryBox();

            // Submit inquiry in drop query box
            addLog("Submit inquiry in drop query box");
            selectedCoursePage=selectedCoursePage.sendQuery();

            // Verify Inquiry has been submitted
            addLog("Verify Inquiry has been submitted");
            selectedCoursePage=selectedCoursePage.verifySumbitInquiry();

            // Database verification in user table
            addLog("Database verification in user table");
            selectedCoursePage=selectedCoursePage.dataVerificationInUserTable("1");
            
            // Database verification in User Lead Table
            String course__Id = propertyReader.readTestData("Course_Id_SplunkDev_Admin");
            String webSiteAction = propertyReader.readTestData("InquiryWebSiteActionOnCourseLadningPage");
            String  campaignName= propertyReader.readTestData("LeadCampaignName");

            // Verify Data in User Course table
            String  course_Group = propertyReader.readTestData("Course_Group_SplunkDev_Admin");
            // Verify Data for Inquiry
            String event = propertyReader.readTestData("Inquiry_Event");
            String event_Type=propertyReader.readTestData("CountryIndia");
            String level_id = propertyReader.readTestData("HomePage_Signup_level_id");
            addLog("Verify Data for Inquiry");
            selectedCoursePage=selectedCoursePage.verifyDataForInquiry(course__Id,webSiteAction,event,campaignName,course_Group,event_Type,level_id);

            // Verify Data in User Event Table
            //String event_Type=propertyReader.readTestData("EventType");
        }
        catch (final Error e) {
            captureScreenshot("test_020InquiryFromCourseLandingPage");
            throw e;
        } 
        catch (final Exception e) {
            captureScreenshot("test_020InquiryFromCourseLandingPage");
            throw e;
        }
    }
}