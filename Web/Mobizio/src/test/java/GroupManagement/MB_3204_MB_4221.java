package GroupManagement;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.GroupModel;
import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import com.mobizio.selenium.framework.Utilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
/*==============================================================================================================================
 File Name    : MB_3204_MB_4221.java
 ClassName    : MB_3204_MB_4221
 Summary      : MB-3204.Verify Tenant Admin is able to Edit A Group.s MB-4221.Verify last Updated column is Localized after Updating and Creating Groups.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      :   test_MB_3204_MB_4221_VerifyTenantAdminIsAbleToEditAGroupANDVerifyLastColumnLocalized
 ===============================================================================================================================*/

public class MB_3204_MB_4221 extends BaseTest {

    private GroupModel groupModel;
    private int random = Utilities.getRandomInteger(0, 10000);

    @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
    public MB_3204_MB_4221(String browser) {
        super(browser, new String[]{TENANT_ADMIN_ROLE});
    }

    @BeforeMethod
    public void initData() throws Exception {
        groupModel = ModelGenerator.generateGroupData();
    }

    @Test(description = "MB_3204_MB_4221_VerifyTenantAdminIsAbleToEditAGroupANDVerifyLastColumnLocalized")
    public void test_MB_3204_MB_4221_VerifyTenantAdminIsAbleToEditAGroupANDVerifyLastColumnLocalized()
        throws Exception {

        for (String loggedInUserType : loggedInUserTypes) {

            reportLog("Login with user name ");
            dashBoardPage = loginPage.login(loggedInUserType);

            reportLog("Verify user login successfully");
            dashBoardPage.verifyLoginSuccess();

            reportLog("Click on Hamburger Menu");
            dashBoardPage.clickOnHamburgerIcon();

            reportLog("Click on Configuration");
            dashBoardPage.clickOnConfiguration();

            reportLog("Click on Groups");
            groupPage = dashBoardPage.clickOnGroups();

            reportLog("Verify Groups Page");
            groupPage.verifyGroupPage();

            reportLog("Click on new group button");
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

            reportLog("Edit Created Group");
            groupPage.editGroup(groupModel, random);

            reportLog("Edit Created Group");
            dashBoardPage.verifyLastUpdatedLocalized();

            reportLog("Verify Updated Group");
            groupPage.verifyEditedGroup(groupModel, random);

            reportLog("Logout from application");
            loginPage = dashBoardPage.logOut();

            reportLog("Verify logout successfully");
            loginPage.verifyLoginPage();

            reportLog(Constants.TEXT_TEST_PASSED);

        }
    }
}
