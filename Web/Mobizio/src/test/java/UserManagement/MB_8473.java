package UserManagement;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

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
 File Name    : MB_8473.java
 ClassName    : MB_8473
 Summary      :  Application Page Navigation || LoginPage--> DashboardPage; Dashboard--> UserPage
                 UserPage(NewUser)--> LoginPage; LoginPage-->(NewUser)DashboardPage; DashboardPage--> LoginPage;
                 LoginPage(ForgotPassford)-->Login(ResetPassword)
                 MB-8473.Verify Carer is able to reset password. Created by rahulp on 6/20/2017.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_8473_VerifyCarerIsAbleToResetPassword
 ===============================================================================================================================*/

public class MB_8473 extends BaseTest {

  Configuration propertyReader;
  private UserModel userModel;
  private String randomString = Utilities.randomString(4).toLowerCase();

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_8473(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    userModel = ModelGenerator.generateNewUserDetails(false);
  }

  @Test(description = "Verify carer user is able to reset Password")
  public void test_MB_8473_VerifyCarerIsAbleToResetPassword() throws Exception {

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

      reportLog("click on new user button");
      userPage.clickOnNewUserButton();

      reportLog("verify new user page");
      userPage.verifyNewUserPage();

      reportLog("enter new user details");
      userPage.enterNewUserDetails(userModel, loggedInUserType, "Carer");

      reportLog("click on create button");
      userPage.clickOnCreateButton();

      reportLog("verify user page");
      userPage.verifyUsersPage();

      reportLog("verify newly created user on user page ");
      userPage.verifyCreatedUserOnUserPage(userModel.getUserName());

      reportLog("Setting the web Access Property from Default to True ");
      userPage.setWebAccessTrue();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog("Verify Reset Password for newly created Carer && Verify the Email Contents of the Reset Password");
      loginPage.verifyForgotPassword(userModel.getUserName());

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog(Constants.TEXT_TEST_PASSED);

    }
  }
}
