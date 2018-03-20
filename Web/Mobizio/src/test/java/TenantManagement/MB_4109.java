package TenantManagement;

import static com.mobizio.Utilities.Constants.SYSTEM_ADMIN_ROLE;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.datamodel.UserModel;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_4109.java
 ClassName    : MB_4109
 Summary      : MB-3888.Verify System Admin is able to create a Tenant Admin.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version
 ===============================================================================================================================
 Remarks      :   test_MB_4109_VerifySystemAdminIsAbleToCreateATenantAdmin
 ===============================================================================================================================*/
public class MB_4109 extends BaseTest {

  private UserModel userModel;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_4109(String browser) {
    super(browser, new String[]{SYSTEM_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    userModel = ModelGenerator.generateNewUserDetails(false);
  }

  @Test(description = "MB_4109_VerifySystemAdminIsAbleToCreateATenantAdmin")
  public void test_MB_4109_VerifySystemAdminIsAbleToCreateATenantAdmin() throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {

      reportLog("Login with user name ");
      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on Configuration");
      dashBoardPage.clickOnConfiguration();

      reportLog("Click on Users");
      userPage = dashBoardPage.clickOnUsers();

      reportLog("verify user page");
      userPage.verifyUsersPage();

      reportLog("Click on new user button");
      userPage.clickOnNewUserButton();

      reportLog("Cerify new user page");
      userPage.verifyNewUserPage();

      reportLog("Enter tenant admin details");
      userPage.enterNewUserDetails(userModel, loggedInUserType, "Tenant Administrator");

      reportLog("Click on create button");
      userPage.clickOnCreateButton();

      reportLog("Verify user page");
      userPage.verifyUsersPage();

      reportLog("Verify newly created user on user page ");
      userPage.verifyCreatedUserOnUserPage(userModel.getUserName());

      reportLog("Logout of the application");
      dashBoardPage.logOut();

      reportLog(Constants.TEXT_TEST_PASSED);

    }
  }
}

