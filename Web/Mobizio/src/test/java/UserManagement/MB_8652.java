package UserManagement;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.datamodel.UserModel;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import com.mobizio.selenium.framework.Configuration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_8652.java
 ClassName    : MB_8652
 Summary      : Verify Search Functionality Works For System Admin At UserManagement Page.

 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      :   test_MB_8652_VerifySearchFunctionalityWorksForSystemAdminAtUserManagementPage
 ===============================================================================================================================*/

public class MB_8652 extends BaseTest {

  Configuration propertyReader;
  private UserModel userModel;
  private String[] loggedInUserTypes;
  private String randomString;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_8652(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    userModel = ModelGenerator.generateNewUserDetails(true);
  }

  @Test(description = "create new user with tenant admin and verify system admin can search for user")
  public void test_MB_8652_VerifySearchFunctionalityWorksForSystemAdminAtUserManagementPage()
      throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {

      reportLog("Login with tenant admin");
      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("Verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on Configuration");
      dashBoardPage.clickOnConfiguration();

      reportLog("Click on Users");
      userPage = dashBoardPage.clickOnUsers();

      reportLog("Click on new user button");
      userPage.clickOnNewUserButton();

      reportLog("Verify new user page");
      userPage.verifyNewUserPage();

      reportLog("Enter new user details");
      userPage.enterNewUserDetails(userModel, loggedInUserType, "Tenant Administrator");

      reportLog("Click on create button");
      userPage.clickOnCreateButton();

      reportLog("Verify new users page");
      userPage.verifyUsersPage();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog("Login with System Admin");
      dashBoardPage = loginPage.login("System");

      reportLog("Verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on Configuration");
      dashBoardPage.clickOnConfiguration();

      reportLog("Click on Users");
      userPage = dashBoardPage.clickOnUsers();

      reportLog("Search for user with username");
      userPage.searchFor(userModel.getUserName());

      reportLog("Verify user on user page ");
      userPage.verifyCreatedUserOnUserPage(userModel.getUserName());

      reportLog("Search for user with first and last name");
      userPage.searchFor(userModel.getFirstName() + " " + userModel.getLastName());

      reportLog("Verify user on user page ");
      userPage.verifyCreatedUserOnUserPage(userModel.getUserName());

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog(Constants.TEXT_TEST_PASSED);
    }
  }

}
