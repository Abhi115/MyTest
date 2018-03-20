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
 File Name    : MB_8494_MB_8495_MB_8504_MB_8505.java
 ClassName    : MB_8494_MB_8495_MB_8504_MB_8505
 Summary      : MB-8505.Verify That Tenant Admin Is Able To Create a Task. MB-8504.Verify That Branch Admin Is
               * Able To Create a Task. MB-8495.Verify last updated column is localized after creating task with
               * tenant admin. MB-8494.Verify last updated column is localized after creating and task with
               * branch admin.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_8494_MB_8495_MB_8504_MB_8505_VerifyThatTenantAdminANDBranchAdminIsAbleToCreateATask
 ===============================================================================================================================*/

public class MB_8494_MB_8495_MB_8504_MB_8505 extends BaseTest {

  private Configuration propertyReader;
  private TaskModel taskModel;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_8494_MB_8495_MB_8504_MB_8505(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE, BRANCH_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    taskModel = new TaskModel();
    propertyReader = new Configuration(FileNames.TaskProperties.toString());
    Properties prop = propertyReader.readApplicationData();
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
  }

  @Test(description = "MB_8494_MB_8495_MB_8504_MB_8505_VerifyThatTenantAdminANDBranchAdminIsAbleToCreateATask")
  public void test_MB_8494_MB_8495_MB_8504_MB_8505_VerifyThatTenantAdminANDBranchAdminIsAbleToCreateATask()
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
      Properties prop = propertyReader.readApplicationData();
      taskModel.setvisitID(prop.getProperty("visitID") + Utilities.getRandomInteger(0, 100000));

      reportLog("Enter visit details and create the visit");
      taskPage.enterNewVisitDetail(taskModel, loggedInUserType);

      reportLog("Verify visit name");
      taskPage.verifyCreatedTaskOnTaskPage(taskModel.getName());

      reportLog("Verify Last Update column after Creating a Task is Localized");
      dashBoardPage.verifyLastUpdatedLocalized();

      reportLog("Click on search button");
      taskPage.clickOnSearch();

      reportLog("Search task by taskID name");
      taskPage.searchByVisitID(taskModel.getvisitID());

      reportLog("Verify visit name");
      taskPage.verifyCreatedTaskOnTaskPage(taskModel.getName());

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog(Constants.TEXT_TEST_PASSED);
    }
  }
}

