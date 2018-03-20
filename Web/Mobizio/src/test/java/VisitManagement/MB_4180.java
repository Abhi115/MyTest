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
 File Name    : MB_4180.java
 ClassName    : MB_4180
 Summary      : MB-4180.Verify Task Search Should Work For Carer
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_4180_VerifyTaskSearchShouldWorkForCarer
 ===============================================================================================================================*/
public class MB_4180 extends BaseTest {

  private Configuration propertyReader;
  private TaskModel taskModel;
  Properties prop=new Properties();

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_4180(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
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
    taskModel.setAssignee(prop.getProperty("carerAssignee"));
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

  @Test(description = "MB_4180_VerifyTaskSearchShouldWorkForCarer")
  public void test_MB_4180_VerifyTaskSearchShouldWorkForCarer() throws Exception {

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

      taskModel.setvisitID(prop.getProperty("visitID") + Utilities.getRandomInteger(0, 100000));

      reportLog("enter visit details and create the visit");
      taskPage.enterNewVisitDetail(taskModel, loggedInUserType);

      reportLog("VerifyingSearch Functionality on Visit page ");
      taskPage.verifySearchFunctionalityOnTaskPage(taskModel, loggedInUserType);

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog("Login with Carer");
      dashBoardPage = loginPage.login("Carer");

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on visit");
      taskPage = dashBoardPage.clickOnVisit();

      reportLog("Verify Visit Search should work for carer on Visit management Page");
      taskPage.verifySearchFunctionalityOnTaskPage(taskModel, "Carer");

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog(Constants.TEXT_TEST_PASSED);
    }
  }
}
