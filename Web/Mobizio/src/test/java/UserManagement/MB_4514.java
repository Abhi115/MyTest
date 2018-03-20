package UserManagement;

import static com.mobizio.Utilities.Constants.SYSTEM_ADMIN_ROLE;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.datamodel.UserModel;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.enums.FileNames;
import com.mobizio.selenium.framework.BaseTest;
import com.mobizio.selenium.framework.Configuration;
import com.mobizio.selenium.framework.Utilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_4514.java
 ClassName    : MB_4514
 Summary      : Verify System Admin Is Able To Change Password.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_4514_VerifySystemAdminIsAbleToChangePassword
 ===============================================================================================================================*/
public class MB_4514 extends BaseTest {

    private UserModel userModel;
    private String randomString;

    @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
    public MB_4514(String browser) {
        super(browser, new String[]{SYSTEM_ADMIN_ROLE});
    }

    @BeforeMethod
    public void initData() throws Exception {
        userModel=ModelGenerator.generateNewUserDetails(false);
    }

    @Test(description = "MB_4514_VerifySystemAdminIsAbleToChangePassword")
    public void test_MB_4514_VerifySystemAdminIsAbleToChangePassword() throws Exception {

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
                userPage.enterNewUserDetails(userModel, loggedInUserType, "System Administrator");

                reportLog("click on create button");
                userPage.clickOnCreateButton();

                reportLog("verify user page");
                userPage.verifyUsersPage();

                reportLog("verify newly created user on user page ");
                userPage.verifyCreatedUserOnUserPage(userModel.getUserName());

                reportLog("Setting The Web Access Property as True");
                userPage.setWebAccessTrue();

                reportLog("Logout from application");
                loginPage = dashBoardPage.logOut();

                reportLog("verify logout successfully");
                loginPage.verifyLoginPage();

                //Set password
                userModel.setChangePassword(userModel.getPassword()+Utilities.getRandomInteger(0, 10000));

                reportLog("Verify System Admin can Change/Update his own password.");
                dashBoardPage.changePassword(userModel);

                reportLog("Logout from application");
                loginPage = dashBoardPage.logOut();

                reportLog("login with Changed password");
                loginPage.loginWithChangedPassword(userModel);

                reportLog("verify user login successfully");
                dashBoardPage.verifyLoginSuccess();

                reportLog("Logout from application");
                loginPage = dashBoardPage.logOut();

                reportLog(Constants.TEXT_TEST_PASSED);
            }
    }
}
