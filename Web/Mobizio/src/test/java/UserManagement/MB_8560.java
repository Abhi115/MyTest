package UserManagement;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.datamodel.UserModel;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import com.mobizio.selenium.framework.Utilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_8560.java
 ClassName    : MB_8560
 Summary      : Verify last updated column is localized after creating and updating any client with tenant admin.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      :   test_MB_8560_VerifyLastUpdatedColumnIsLocalizedAfterCreatingAndUpdatingAnyClientWithTenantAdmin
 ===============================================================================================================================*/

public class MB_8560 extends BaseTest {

  private UserModel userModel;
  private long timeStamp;
  private int randomInt;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_8560(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    userModel = ModelGenerator.generateNewUserDetails(false);
    timeStamp = Utilities.getTimeStamp();
    randomInt = Utilities.getRandomInteger(0, 4);
  }

  @Test(description = "Verify last updated column is localized after creating and updating any client with tenant admin")
  public void test_MB_8560_VerifyLastUpdatedColumnIsLocalizedAfterCreatingAndUpdatingAnyClientWithTenantAdmin() throws Exception {
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

      reportLog("Enter new user details");
      userPage.enterNewUserDetails(userModel, loggedInUserType, "Tenant Administrator");

      reportLog("Click on create button");
      userPage.clickOnCreateButton();

      reportLog("Verify user page");
      userPage.verifyUsersPage();

      reportLog("Verify newly created user on user page ");
      userPage.verifyCreatedUserOnUserPage(userModel.getUserName());

      reportLog("Verify Last Updated column localised once new user is created");
      dashBoardPage.verifyLastUpdatedLocalized();

      reportLog("Click on created user button");
      userPage.clickOnCreatedUser();

      reportLog("Edit user details");
      userPage.editUser(userModel, timeStamp, randomInt);

      reportLog("Click on update button");
      userPage.clickOnUpdateButton();

      reportLog("Verify Last Updated column localised once user details is edited");
      dashBoardPage.verifyLastUpdatedLocalized();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();

    }
  }
}