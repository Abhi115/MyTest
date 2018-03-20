package Miscellaneous;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.datamodel.TaskTypeModel;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import com.mobizio.selenium.framework.Configuration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_3780.java
 ClassName    : MB_3780
 Summary      : Verify Tenant Admin Is Able To Create A Task Type
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version
 ===============================================================================================================================
 Remarks      :   test_MB_3780_VerifyTenantAdminIsAbleToCreateATaskType
 ===============================================================================================================================*/

public class MB_3780 extends BaseTest {

  private String[] loggedInUserTypes;
  private Configuration propertyReader;
  private TaskTypeModel taskTypeModel;
  private String randomString;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_3780(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    taskTypeModel = ModelGenerator.generateNewTaskType();
  }

  @Test(description = "MB_3780_VerifyTenantAdminIsAbleToCreateATaskType")
  public void test_MB_3780_VerifyTenantAdminIsAbleToCreateATaskType() throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {

      reportLog("Login with user name ");
      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("Verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on Configuration");
      dashBoardPage.clickOnConfiguration();

      reportLog("Click on task types");
      taskTypePage = dashBoardPage.clickOnTaskType();

      reportLog("Verify task type page");
      taskTypePage.verifyTaskTypePage();

      reportLog("Click on new task type button");
      taskTypePage.clickOnNewTaskTypeButton();

      reportLog("Enter name to create new task type");
      taskTypePage.enterName(taskTypeModel);

      reportLog("Associate forms to new task type");
      taskTypePage.associateForm(taskTypeModel);

      reportLog("Click on create button");
      taskTypePage.clickOnCreateButton();

      reportLog("Verify task type page");
      taskTypePage.verifyTaskTypePage();

      reportLog("Verify newly created task type");
      taskTypePage.verifyNewlyCreatedTasktype(taskTypeModel);

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog(Constants.TEXT_TEST_PASSED);
    }
  }
}

