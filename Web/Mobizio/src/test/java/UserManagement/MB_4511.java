package UserManagement;

import static com.mobizio.Utilities.Constants.SYSTEM_ADMIN_ROLE;

import com.mobizio.Utilities.Constants;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
/*==============================================================================================================================
 File Name    : MB_4511.java
 ClassName    : MB_4511
 Summary      : MB-4511.Verify Pagination and records Per Page functionality at User management Page.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_4511_VerifyPaginationAndRecordsPerPageFunctionalityAtUserManagementPage
 ===============================================================================================================================*/
public class MB_4511 extends BaseTest {

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_4511(String browser) {
    super(browser, new String[]{SYSTEM_ADMIN_ROLE});
  }

  @Test(description = "MB_4511_VerifyPaginationAndRecordsPerPageFunctionalityAtUserManagementPage")
  public void test_MB_4511_VerifyPaginationAndRecordsPerPageFunctionalityAtUserManagementPage()
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

      reportLog("Click on Users");
      userPage = dashBoardPage.clickOnUsers();

      reportLog("verify user page");
      userPage.verifyUsersPage();

      reportLog("Verify pagination at groups management page");
      dashBoardPage.verifyPagination();

      reportLog("Verify pagination at groups management page");
      dashBoardPage.verifyRecordsPerPage();

      reportLog("Logout of the application");
      dashBoardPage.logOut();

      reportLog(Constants.TEXT_TEST_PASSED);

    }
  }
}

