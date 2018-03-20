package UserManagement;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.UserModel;
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
 File Name    : MB_8475.java
 ClassName    : MB_8475
 Summary      : MB-8475.Verify BranchAdmin is able to reset password. Created by rahulp on 6/20/2017.
                Application Page Navigation || LoginPage--> DashboardPage; Dashboard--> UserPage
                UserPage(NewUser)--> LoginPage; LoginPage-->(NewUser)DashboardPage; DashboardPage--> LoginPage;
                LoginPage(ForgotPassford)-->Login(ResetPassword)
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_8475_VerifyBranchAdminIsAbleToResetPassword
 ===============================================================================================================================*/

public class MB_8475 extends BaseTest {

  Configuration propertyReader;
  private UserModel userModel;
  private String randomString = Utilities.randomString(4).toLowerCase();

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_8475(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    Configuration propertyReader = new Configuration(FileNames.UserCarerProperties.toString());
    userModel = new UserModel();
    Properties prop = propertyReader.readApplicationData();
    userModel.setFirstName(prop.getProperty("firstName"));
    userModel.setUserName(userModel.getFirstName() + randomString);
    userModel.setLastName(prop.getProperty("lastName"));
    userModel.setEmail(userModel.getUserName() + "@mailinator.com");
    userModel.setPassword(prop.getProperty("password"));
    userModel.setConfirmPassword(prop.getProperty("confirmPassword"));
    userModel.setBranch(prop.getProperty("branchName"));
    userModel.setUserType(prop.getProperty("userType"));
    userModel.setTenantUserId(prop.getProperty("tenantUserId") + randomString);
  }

  @Test(description = "Verify carer user is able to reset Password")
  public void test_MB_8475_VerifyBranchAdminIsAbleToResetPassword() throws Exception {

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

      reportLog("Verify user page");
      userPage.verifyUsersPage();

      reportLog("Click on new user button");
      userPage.clickOnNewUserButton();

      reportLog("Verify new user page");
      userPage.verifyNewUserPage();

      reportLog("Enter new user details");
      userPage.enterNewUserDetails(userModel, loggedInUserType, "Branch Administrator");

      reportLog("Click on create button");
      userPage.clickOnCreateButton();

      reportLog("Verify user page");
      userPage.verifyUsersPage();

      reportLog("Verify newly created user on user page ");
      userPage.verifyCreatedUserOnUserPage(userModel.getUserName());

      reportLog("Setting the web Access Property from Default to True ");
      userPage.setWebAccessTrue();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog("Verify Reset Password for newly created Branch Amdin && Verify the Email Contents of the Reset Password");
      loginPage.verifyForgotPassword(userModel.getUserName());

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog(Constants.TEXT_TEST_PASSED);
    }
  }
}
