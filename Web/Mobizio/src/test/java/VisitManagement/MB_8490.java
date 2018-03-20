package VisitManagement;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.TaskModel;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.enums.FileNames;
import com.mobizio.selenium.framework.BaseTest;
import com.mobizio.selenium.framework.Configuration;
import com.mobizio.selenium.framework.Utilities;
import java.util.Properties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_8490.java
 ClassName    : MB_8490
 Summary      : MB-8490.Verify when a task is reassigned to another user, a new task is created and old task's
                * status is changed to cancelled.. Created by rahulp on 6/30/2017.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_8490_VerifyTaskReassignmentCreatesNewTaskWithOutStandingStatus
 ===============================================================================================================================*/
public class MB_8490 extends BaseTest {

  private TaskModel taskModel;
  Properties prop=new Properties();
  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_8490(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    taskModel = new TaskModel();
    Configuration propertyReader = new Configuration(FileNames.TaskProperties.toString());
    taskModel.setName(prop.getProperty("name") + Utilities.randomString(4).toLowerCase());
    taskModel.setvisitID(prop.getProperty("visitID") + Utilities.getRandomInteger(0, 100000));
    taskModel.setTaskType(prop.getProperty("taskType"));
    taskModel.setStartTime(prop.getProperty("startTime"));
    taskModel.setEndTime(prop.getProperty("endTime"));
    taskModel.setStatus(prop.getProperty("status"));
    taskModel.setAssignee(prop.getProperty("assignee"));
    taskModel.setCustomer(prop.getProperty("customer"));
    taskModel.setBranch(prop.getProperty("branch"));
    taskModel.setAssociatedCase(prop.getProperty("associatedCase"));
    taskModel.setActivities(prop.getProperty("activities"));
    taskModel.setCarePlanId(prop.getProperty("carePlanId"));
    taskModel.setAddressLine1(prop.getProperty("addressLine1"));
    taskModel.setAddressLine2(prop.getProperty("addressLine2"));
    taskModel.setCity(prop.getProperty("city"));
    taskModel.setCounty(prop.getProperty("county"));
    taskModel.setCountry(prop.getProperty("country"));
    taskModel.setPostcode(prop.getProperty("postcode"));
    taskModel.setLatitude(prop.getProperty("latitude"));
    taskModel.setLongitude(prop.getProperty("longitude"));
    taskModel.setNewAssignee(prop.getProperty("newAssignee"));
  }

  @Test(description = "create new device with all fields")
  public void test_MB_8490_VerifyTaskReassignmentCreatesNewTaskWithOutStandingStatus()
      throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {

      //reportLog("Login with user name ");
      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("Verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on visit");
      taskPage = dashBoardPage.clickOnVisit();

      reportLog("Click on new task");
      taskPage.clickOnNewVisit();

      reportLog("Enter visit details and create the visit");
      taskPage.enterNewVisitDetail(taskModel, loggedInUserType);

      reportLog("Verify visit name");
      taskPage.verifyCreatedTaskOnTaskPage(taskModel.getName());

      reportLog("Get new task name");
      taskPage.getCreatedTaskOnTaskPage();

      reportLog("Click on search button");
      taskPage.clickOnSearch();

      reportLog("Search task by taskID name");
      taskPage.searchByVisitID(taskModel.getvisitID());

      reportLog("Click on searched task");
      taskPage.clickOnSearchedTask();

      reportLog("Click On edit button");
      taskPage.clickOnEditButton();

      reportLog("change assignee");
      taskPage.changeAssignee(taskModel);

      reportLog("Click On save button");
      taskPage.clickOnSaveButton();

      reportLog("Click on search button");
      taskPage.clickOnSearch();

      reportLog("Search task by taskID name");
      taskPage.searchByVisitID(taskModel.getvisitID());

      reportLog("Verifying Task Reassignment marks the old task as cancelled and create new outstanding task for a new user ");
      taskPage.verifyVisitReassignment();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog(Constants.TEXT_TEST_PASSED);
    }
  }
}
