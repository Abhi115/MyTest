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

public class MB_4719 extends BaseTest {

  private UserModel userModel;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)

  public MB_4719(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    userModel = ModelGenerator.generateNewUserDetails(false);
  }

  @Test(description = "MB_4719_VerifyThatCorrectEmailValidationMessageShouldBeDisplayedWhenUserEntersInvalidEmailAddressWhileAddingAClient")
  public void MB_4719_VerifyThatCorrectEmailValidationMessageShouldBeDisplayedWhenUserEntersInvalidEmailAddressWhileAddingAClient() throws Exception {
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

      // set invalid email-id
      userModel.setEmail(Utilities.randomString(8));

      reportLog("enter new user details");
      userPage.enterNewUserDetails(userModel, loggedInUserType, "Tenant Administrator");

      reportLog("click on create button");
      userPage.clickOnCreateButton();

      reportLog("Verify invalid email validation message");
      userPage.verifyInvalidEmailValidation();
    }
  }
}