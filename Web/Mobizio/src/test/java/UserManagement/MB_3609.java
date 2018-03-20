package UserManagement;

import static com.mobizio.Utilities.Constants.BRANCH_ADMIN_ROLE;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.datamodel.UserModel;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import com.mobizio.selenium.framework.Configuration;
import com.mobizio.selenium.framework.Utilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_3609.java
 ClassName    : MB_3609
 Summary      : MB-3778:Verify Branch Admin Is Able To Create A Branch Admin and career User.
                  Application Page Navigation || LoginPage--> DashboardPage; Dashboard--> UserPage
                  UserPage(NewUser)--> LoginPage; LoginPage-->(NewUser)DashboardPage; DashboardPage--> LoginPage;
                  LoginPage(ForgotPassword)-->Login(ResetPassword)
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      :   test_MB_3609_VerifyThatBranchAdminIsAbleToCreateUsers
 ===============================================================================================================================*/

public class MB_3609 extends BaseTest {

  private Configuration propertyReader;
  private UserModel userModel;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_3609(String browser) {
    super(browser, new String[]{BRANCH_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    userModel = ModelGenerator.generateNewUserDetails(false);
  }

  @Test(description = "MB_3609_VerifyThatBranchAdminIsAbleToCreateUsers")
  public void test_MB_3609_VerifyThatBranchAdminIsAbleToCreateUsers() throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {

      reportLog("Login with user name ");
      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("Verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on Configuration");
      dashBoardPage.clickOnConfiguration();

      reportLog("Click on Users");
      userPage = dashBoardPage.clickOnUsers();

      reportLog("Verify user page");
      userPage.verifyUsersPage();

      //Create Branch Admin User
      userModel.setUserName(userModel.getUserName() + Utilities.randomString(5).toLowerCase());
      userModel.setTenantUserId(userModel.getTenantUserId() + Utilities.getRandomInteger(0, 10000));

      reportLog("Click on new user button");
      userPage.clickOnNewUserButton();

      reportLog("Verify new user page");
      userPage.verifyNewUserPage();

      reportLog("Enter new user details");
      userPage.enterNewUserDetails(userModel, loggedInUserType, "Branch Administrator");

      reportLog("click on create button");
      userPage.clickOnCreateButton();

      reportLog("Verify user page");
      userPage.verifyUsersPage();

      reportLog("Verify newly created user on user page ");
      userPage.verifyCreatedUserOnUserPage(userModel.getUserName());

      //Create carer User
      userModel.setUserName(userModel.getFirstName() + Utilities.randomString(5).toLowerCase());
      userModel.setTenantUserId(userModel.getTenantUserId() + Utilities.getRandomInteger(0, 10000));

      reportLog("click on new user button");
      userPage.clickOnNewUserButton();

      reportLog("verify new user page");
      userPage.verifyNewUserPage();

      reportLog("Enter new user details");
      userPage.enterNewUserDetails(userModel, loggedInUserType, "Carer");

      reportLog("Click on create button");
      userPage.clickOnCreateButton();

      reportLog("Verify user page");
      userPage.verifyUsersPage();

      reportLog("Verify newly created user on user page ");
      userPage.verifyCreatedUserOnUserPage(userModel.getUserName());

      reportLog("Verify newly created user on user page ");
      userPage.setWebAccessTrue();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog(Constants.TEXT_TEST_PASSED);
    }
  }
}
