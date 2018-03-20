package UserManagement;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.ModelGenerator;
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
 File Name    : MB_3747.java
 ClassName    : MB_3747
 Summary      : Verify tenant admin is able to edit users.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      :   test_MB_3747_VerifyTenantAdminisAbleToEditUsers
 ===============================================================================================================================*/
public class MB_3747 extends BaseTest {

  Properties prop;
  private UserModel userModel;
  private long timeStamp;
  private int randomInt;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_3747(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    userModel = ModelGenerator.generateNewUserDetails(true);
    timeStamp = Utilities.getTimeStamp();
    randomInt = Utilities.getRandomInteger(0, 4);
    Configuration propertyReader = new Configuration(FileNames.UserCarerProperties.toString());
    prop = propertyReader.readApplicationData();
  }

  @Test(description = "MB_3747_VerifyTenantAdminIsAbleToEditUsers")
  public void test_MB_3747_VerifyTenantAdminIsAbleToEditUsers() throws Exception {
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

      reportLog("Click on created user button");
      userPage.clickOnCreatedUser();

      //Set user details
      userModel.setBranch(prop.getProperty("branchType"));
      userModel.setCountry(prop.getProperty("editedCountryName"));
      userModel.setGender(prop.getProperty("editedGender"));
      userModel.setDob(prop.getProperty("editedDob"));
      userModel.setUserType(prop.getProperty("editedUserType"));

      reportLog("Edit user details");
      userPage.editUser(userModel, timeStamp, randomInt);

      reportLog("Click on update button");
      userPage.clickOnUpdateButton();

      reportLog("Click on updated user");
      userPage.clickOnCreatedUser();

      reportLog("Verify edited user details");
      userPage.verifyEditedUserDetails(userModel, timeStamp, randomInt);

      reportLog(Constants.TEXT_TEST_PASSED);

    }
  }
}
