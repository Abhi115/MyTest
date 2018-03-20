package GroupManagement;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.GroupModel;
import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class MB_3203 extends BaseTest {
    private GroupModel groupModel;

    @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
    public MB_3203(String browser) {
        super(browser, new String[]{TENANT_ADMIN_ROLE});
    }

    @BeforeMethod
    public void initData() throws Exception {
        groupModel=ModelGenerator.generateGroupData();
    }

    /**
     * MB-3203.Verify Tenant Admin is able to Add Public Group.
     */
    @Test(description = "MB_3203_VerifyTenantAdminIsAbleToCreateAPublicGroup")
    public void test_MB_3203_VerifyTenantAdminIsAbleToCreateAPublicGroup() throws Exception {

            for (String loggedInUserType : loggedInUserTypes) {

                reportLog("Login with user name ");
                dashBoardPage = loginPage.login(loggedInUserType);

                reportLog("verify user login successfully");
                dashBoardPage.verifyLoginSuccess();

                reportLog("Click on Hamburger Menu");
                dashBoardPage.clickOnHamburgerIcon();

                reportLog("Click on Configuration");
                dashBoardPage.clickOnConfiguration();

                reportLog("Click on Groups");
                groupPage = dashBoardPage.clickOnGroups();

                reportLog("verify Groups Page");
                groupPage.verifyGroupPage();

                reportLog("click on new group button");
                groupPage.clickOnNewGroupButton();

                reportLog("verify new Group Page");
                groupPage.verifyNewGroupPage();

                reportLog("enter new group details");
                groupPage.enterNewGroupDetails(groupModel);

                reportLog("click on create button");
                groupPage.clickOnCreateButton();

                reportLog("verify Groups Page");
                groupPage.verifyGroupPage();

                reportLog("verify newly created group");
                groupPage.verifyCreatedGroupOnGroupPage(groupModel.getName());

                reportLog("Logout from application");
                loginPage = dashBoardPage.logOut();

                reportLog("verify logout successfully");
                loginPage.verifyLoginPage();

                reportLog(Constants.TEXT_TEST_PASSED);

            }
    }
}
