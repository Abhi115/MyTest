package GroupManagement;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.GroupModel;
import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

/*==============================================================================================================================
 File Name    : MB_3202.java
 ClassName    : MB_3202
 Summary      : MB-3202.Verify Tenant Admin is able to search Public Group.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      :   test_MB_3202_VerifySearchFunctionalityAtGroupsPage
 ===============================================================================================================================*/

public class MB_3202 extends BaseTest {

    private GroupModel groupModel;

    @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
    public MB_3202(String browser) {
        super(browser, new String[]{TENANT_ADMIN_ROLE});
    }

    @BeforeMethod
    public void initData() throws Exception {
        groupModel = ModelGenerator.generateGroupData();
    }

    /**
     * MB-3202.Verify Tenant Admin is able to search Public Group.
     */
    @Test(description = "MB_3202_VerifySearchFunctionalityAtGroupsPage")
    public void test_MB_3202_VerifySearchFunctionalityAtGroupsPage() throws Exception {

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

                reportLog("Vlick on new group button");
                groupPage.clickOnNewGroupButton();

                reportLog("Verify new Group Page");
                groupPage.verifyNewGroupPage();

                reportLog("Enter new group details");
                groupPage.enterNewGroupDetails(groupModel);

                reportLog("Click on create button");
                groupPage.clickOnCreateButton();

                reportLog("Verify Groups Page");
                groupPage.verifyGroupPage();

                reportLog("Verify newly created group");
                groupPage.verifyCreatedGroupOnGroupPage(groupModel.getName());

                reportLog("Verify newly created group");
                groupPage.verifySearchfunctionlaity(groupModel);

                reportLog("Logout from application");
                loginPage = dashBoardPage.logOut();

                reportLog("Verify logout successfully");
                loginPage.verifyLoginPage();

                reportLog(Constants.TEXT_TEST_PASSED);

            }
    }
}
