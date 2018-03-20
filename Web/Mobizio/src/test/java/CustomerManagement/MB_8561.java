package CustomerManagement;

import static com.mobizio.Utilities.Constants.BRANCH_ADMIN_ROLE;

import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_8561.java
 ClassName    : MB_8561
 Summary      : Verify pagination functionality works for branch admin at client management page
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version
 ===============================================================================================================================
 Remarks      : test_MB_8561_VerifyPaginationFunctionalityWorksForBranchAdminAtClientManagementPage
 ===============================================================================================================================*/

public class MB_8561 extends BaseTest {

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_8561(String browser) {
    super(browser, new String[]{BRANCH_ADMIN_ROLE});
  }

  @Test(description = "Verify pagination functionality works for branch admin at client management page")
  public void test_MB_8561_VerifyPaginationFunctionalityWorksForBranchAdminAtClientManagementPage() throws Exception {

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