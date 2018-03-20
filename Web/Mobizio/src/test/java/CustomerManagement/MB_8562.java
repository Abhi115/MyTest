package CustomerManagement;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_8562.java
 ClassName    : MB_8562
 Summary      : Verify pagination functionality works for tenant admin at client management page.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version
 ===============================================================================================================================
 Remarks      : test_MB_8562_VerifyPaginationFunctionalityWorksForTenantAdminAtClientManagementPage
 ===============================================================================================================================*/

public class MB_8562 extends BaseTest {

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_8562(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @Test(description = "Verify pagination functionality works for branch admin at client management page")
  public void test_MB_8562_VerifyPaginationFunctionalityWorksForBranchAdminAtClientManagementPage() throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {

      reportLog("Login with user name ");
      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("Verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on Customer");
      customerPage = dashBoardPage.clickOnCustomer();

      reportLog("Verify pagination on customer page");
      dashBoardPage.verifyPagination();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();
    }
  }
}

