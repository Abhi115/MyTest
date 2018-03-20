package GroupManagement;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.Utilities.Constants;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_4219.java
 ClassName    : MB_4219
 Summary      : MB-4219.Verify Pagination and Records Per page functionality At Group Management Page.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      :   test_MB_4219_VerifyPaginationAndRecordsPerPageFunctionalityAtGroupManagementPage
 ===============================================================================================================================*/

public class MB_4219 extends BaseTest {

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_4219(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @Test(description = "MB_4219_VerifyPaginationAndRecordsPerPageFunctionalityAtGroupManagementPage")
  public void test_MB_4219_VerifyPaginationAndRecordsPerPageFunctionalityAtGroupManagementPage()
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

      reportLog("Verify pagination at groups management page");
      dashBoardPage.verifyPagination();

      reportLog("Verify pagination at groups management page");
      dashBoardPage.verifyRecordsPerPage();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog(Constants.TEXT_TEST_PASSED);

    }
  }
}
