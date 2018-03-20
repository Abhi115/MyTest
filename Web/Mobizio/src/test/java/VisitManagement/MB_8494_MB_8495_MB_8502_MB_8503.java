package VisitManagement;

import static com.mobizio.Utilities.Constants.BRANCH_ADMIN_ROLE;
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
 File Name    : MB_8494_MB_8495_MB_8502_MB_8503.java
 ClassName    : MB_8494_MB_8495_MB_8502_MB_8503
 Summary      : MB-8503.Verify That Tenant Admin Is Able To Edit a Task. MB-8502.Verify That Branch Admin Is
               * Able To Edit a Task. MB-8495.Verify last updated column is localized after updating task with
               * tenant admin. MB-8494.Verify last updated column is localized after updating and task with
               * branch admin.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_8494_MB_8495_MB_8502_MB_8503_VerifyTenantAdminANDBranchAdminIsAbleToEditTasks
 ===============================================================================================================================*/

public class MB_8494_MB_8495_MB_8502_MB_8503 extends BaseTest {

  private Configuration propertyReader;
  private TaskModel taskModel;
  private String randomString = Utilities.randomString(4).toLowerCase();
  Properties prop=new Properties();

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_8494_MB_8495_MB_8502_MB_8503(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE, BRANCH_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    taskModel = new TaskModel();
    propertyReader = new Configuration(FileNames.TaskProperties.toString());
    taskModel.setName(prop.getProperty("name") + Utilities.randomString(4).toLowerCase());
    taskModel.setTaskType(prop.getProperty("taskType"));
    taskModel.setStartTime(prop.getProperty("startTime"));
    taskModel.setEndTime(prop.getProperty("endTime"));
    taskModel.setStatus(prop.getProperty("status"));
    taskModel.setAssignee(prop.getProperty("assignee"));
    taskModel.setCustomer(prop.getProperty("customer"));
    taskModel.setBranch(prop.getProperty("branch"));
    taskModel.setAddressLine1(prop.getProperty("addressLine1"));
    taskModel.setAddressLine2(prop.getProperty("addressLine2"));
    taskModel.setCity(prop.getProperty("city"));
    taskModel.setCounty(prop.getProperty("county"));
    taskModel.setCountry(prop.getProperty("country"));
    taskModel.setPostcode(prop.getProperty("postcode"));
    taskModel.setLatitude(prop.getProperty("latitude"));
    taskModel.setLongitude(prop.getProperty("longitude"));
    taskModel.setNewTaskType(prop.getProperty("newTaskType"));
  }

  @Test(description = "create new device with all fields")
  public void test_MB_8494_MB_8495_MB_8502_MB_8503_VerifyTenantAdminANDBranchAdminIsAbleToEditTasks()
      throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {

      reportLog("Login with user name ");
      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("Verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on visit");
      taskPage = dashBoardPage.clickOnVisit();

      reportLog("Click on new task");
      taskPage.clickOnNewVisit();

      //SettingRandomVisitID
      taskModel.setvisitID(prop.getProperty("visitID") + Utilities.getRandomInteger(0, 100000));

      reportLog("Enter visit details and create the visit");
      taskPage.enterNewVisitDetail(taskModel, loggedInUserType);

      reportLog("verify visit name");
      taskPage.verifyCreatedTaskOnTaskPage(taskModel.getName());

      reportLog("Edit the Created Visit");
      taskPage.editVisit(taskModel, randomString);

      reportLog("Verify last updated column is localized after updating the Task");
      dashBoardPage.verifyLastUpdatedLocalized();

      reportLog("Verify the updated Task");
      taskPage.verifyEditedVisit(taskModel, randomString);

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog(Constants.TEXT_TEST_PASSED);
    }
  }
}


