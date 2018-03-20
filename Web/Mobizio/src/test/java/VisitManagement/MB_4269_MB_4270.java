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
 File Name    : MB_4269_MB_4270.java
 ClassName    : MB_4269_MB_4270
 Summary      : MB-4269.Verify search functionality works for tenant admin at task management page.
              : MB-4270.Verify search functionality works for branch admin at task management page.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_4269_MB_4270_VerifySearchFunctionalityForTenantANDBranchAdminAtVisitManagement
 ===============================================================================================================================*/

public class MB_4269_MB_4270 extends BaseTest {

  Properties prop = new Properties();
  private Configuration propertyReader;
  private TaskModel taskModel;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_4269_MB_4270(String browser) {
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
  }

  @Test(description = "MB_4269_MB_4270_VerifySearchFunctionalityForTenantANDBranchAdminAtVisitManagement")
  public void test_MB_4269_MB_4270_VerifySearchFunctionalityForTenantANDBranchAdminAtVisitManagement()
      throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {

      reportLog("Login with user name ");
      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on visit");
      taskPage = dashBoardPage.clickOnVisit();

      reportLog("click on new task");
      taskPage.clickOnNewVisit();

      //SettingRandomVisitID
      taskModel.setvisitID(prop.getProperty("visitID") + Utilities.getRandomInteger(0, 100000));

      reportLog("enter visit details and create the visit");
      taskPage.enterNewVisitDetail(taskModel, loggedInUserType);

      reportLog(
          "Verifying Search Functionality for Branch Admin and tenant Admin At Visit Management page   ");
      taskPage.verifySearchFunctionalityOnTaskPage(taskModel, loggedInUserType);

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog(Constants.TEXT_TEST_PASSED);
    }
  }
}
