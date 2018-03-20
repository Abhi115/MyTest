package VisitManagement;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

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
 File Name    : CreateTaskWithAllDetails.java
 ClassName    : CreateTaskWithAllDetails
 Summary      : Create Task With All Details
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_CreateTaskWithAllDetails
 ===============================================================================================================================*/
public class CreateTaskWithAllDetails extends BaseTest {

  private TaskModel taskModel;
  private String randomString;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public CreateTaskWithAllDetails(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    randomString = Utilities.randomString(4).toLowerCase();
    taskModel = new TaskModel();
    Configuration propertyReader = new Configuration(FileNames.TaskProperties.toString());
    Properties prop = new Properties();
    taskModel.setName(prop.getProperty("name"));
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

  @Test(description = "create new device with all fields")
  public void test_CreateTaskWithAllDetails() throws Exception {

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

      reportLog("enter visit details and create the visit");
      taskPage.enterNewVisitDetail(taskModel, randomString);

      reportLog("verify visit name");
      taskPage.verifyCreatedTaskOnTaskPage(taskModel.getName());

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("verify logout successfully");
      loginPage.verifyLoginPage();
    }
  }
}
