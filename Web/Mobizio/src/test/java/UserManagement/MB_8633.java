package UserManagement;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.datamodel.UserModel;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.enums.FileNames;
import com.mobizio.selenium.framework.BaseTest;
import com.mobizio.selenium.framework.Configuration;
import java.util.Properties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class MB_8633 extends BaseTest {

  Properties prop;
  private UserModel userModel;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_8633(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    userModel = ModelGenerator.generateNewUserDetails(true);
    Configuration propertyReader = new Configuration(FileNames.UserCarerProperties.toString());
    prop = propertyReader.readApplicationData();
  }

  @Test(description = "Verify branch admin can not see non shared user's details which belong to other branch")
  public void test_MB_8633_VerifyBranchAdminCanNotSeeNonSharedUserDetailsWhichBelongToOtherBranch() throws Exception {

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

      reportLog("Click on new user button");
      userPage.clickOnNewUserButton();

      reportLog("Verify new user page");
      userPage.verifyNewUserPage();

      //Set user details
      userModel.setBranch(prop.getProperty("northBranch"));
      reportLog("Enter new user details");
      userPage.enterNewUserDetails(userModel, loggedInUserType, "Carer");

      reportLog("Click on create button");
      userPage.clickOnCreateButton();

      reportLog("Verify user page");
      userPage.verifyUsersPage();

      reportLog("Verify newly created user on user page ");
      userPage.verifyCreatedUserOnUserPage(userModel.getUserName());

      reportLog("Click on created user button");
      userPage.clickOnCreatedUser();

      reportLog("Click on edit button");
      userPage.clickOnEditButton();

      reportLog("Uncheck Is Shared check box");
      userPage.uncheckIsSharedCheckBox();

      reportLog("Click on update button");
      userPage.clickOnUpdateButton();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog("Login with branch admin");
      dashBoardPage = loginPage.login(Constants.BRANCH_ADMIN_ROLE);

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

      reportLog("Filer user name");
      userPage.filterUser(userModel.getUserName());

      reportLog("Verify user on user page");
      userPage.verifyUserDoesNotExist();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();
    }
  }
}
