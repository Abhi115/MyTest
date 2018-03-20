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
 File Name    : MB_8474.java
 ClassName    : MB_8474
 Summary      : MB-8474.Verify the Email contents for new user password requests and Email contents for the newly created user.
                Application Page Navigation || LoginPage--> DashboardPage; Dashboard--> UserPage
                UserPage(NewUser)--> LoginPage; LoginPage-->Mailinator(RandomPassword)--> LoginPage
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_8474_VerifyTheEmailContentsForNewUserRequests
 ===============================================================================================================================*/

public class MB_8474 extends BaseTest {

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)

  public MB_8474(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }
  private UserModel userModel;
  private String randomString = Utilities.randomString(4).toLowerCase();

  @BeforeMethod
  public void initData() throws Exception {
    Configuration propertyReader = new Configuration(FileNames.UserCarerProperties.toString());
    Properties prop=propertyReader.readApplicationData();
    userModel = new UserModel();
    userModel.setFirstName(prop.getProperty("firstName"));
    userModel.setUserName(userModel.getFirstName() + randomString);
    userModel.setLastName(prop.getProperty("lastName"));
    userModel.setEmail(userModel.getUserName() + "@mailinator.com");
    userModel.setBranch(prop.getProperty("branchName"));
    userModel.setUserType(prop.getProperty("userType"));
    userModel.setTenantUserId(prop.getProperty("tenantUserId") + randomString);
  }

  @Test(description = "MB_8474_VerifyTheEmailContentsForNewUserRequests")
  public void test_MB_8474_VerifyTheEmailContentsForNewUserRequests() throws Exception {

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
      userPage.enterNewUserDetails(userModel, loggedInUserType, "Reset");

      reportLog("Click on create button");
      userPage.clickOnCreateButton();

      reportLog("Verify newly created user on user page ");
      userPage.verifyCreatedUserOnUserPage(userModel.getUserName());

      reportLog("Verify newly created user on user page ");
      userPage.setWebAccessTrue();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog("Verify System generated password and Email Contents for the newly Created User");
      loginPage.verifyRandomPassword(userModel.getUserName());

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog(Constants.TEXT_TEST_PASSED);
    }
  }
}
