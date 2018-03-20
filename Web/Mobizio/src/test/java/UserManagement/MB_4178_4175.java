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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
/*==============================================================================================================================
 File Name    : MB_4178_4175.java
 ClassName    : MB_4178_4175
 Summary      : Verify Email Is Required Field And Has Char Limit While Creating A New User
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_4178_4175_VerifyEmailIsRequiredFieldAndHasCharLimitWhileCreatingANewUser
 ===============================================================================================================================*/
public class MB_4178_4175 extends BaseTest {

    @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
    public MB_4178_4175(String browser) {
        super(browser, new String[]{TENANT_ADMIN_ROLE});
    }

    private UserModel userModel;

    @BeforeMethod
    public void initData() throws Exception {
        userModel=ModelGenerator.generateNewUserDetails(false);
    }

    @Test(description = "MB_4178_4175_Verify_Email_Is_Required_Field_And_Have_255_Char_Limit_While_Creating_A_New_User")
    public void test_MB_4178_4175_VerifyEmailIsRequiredFieldAndHasCharLimitWhileCreatingANewUser()
            throws Exception {

            for (String loggedInUserType : loggedInUserTypes) {
                // verify email input should have a character limit of 255 when creating a user
                reportLog("Login with user name ");
                dashBoardPage = loginPage.login("Tenant");

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
                userPage.enterNewUserDetails(userModel, "Tenant", "Carer");

                reportLog("verify email field limit");
                userPage.verifyEmailFieldLimit();

                // verify email is required field
                reportLog("enter new user details");
                userModel.setEmail(Utilities.randomString(241) + "@mailinator.com");
                //userModel.setEmail("");
                userPage.enterNewUserDetails(userModel, "Tenant", "Carer");

                reportLog("click on create button");
                userPage.clickOnCreateButton();

                reportLog("verify email is required field");
                userPage.verifyValidationMessage("email");

                reportLog("Logout from application");
                loginPage = dashBoardPage.logOut();

                reportLog("verify logout successfully");
                loginPage.verifyLoginPage();

                reportLog(Constants.TEXT_TEST_PASSED);
            }
    }
}
