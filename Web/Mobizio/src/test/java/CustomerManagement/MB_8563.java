package CustomerManagement;

import static com.mobizio.Utilities.Constants.BRANCH_ADMIN_ROLE;

import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_8563.java
 ClassName    : MB_8563
 Summary      : Verify records per page functionality works for branch admin at client management page.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version
 ===============================================================================================================================
 Remarks      : test_MB_8563_VerifyRecordsPerPageFunctionalityWorksForBranchAdminAtClientManagementPage
 ===============================================================================================================================*/

public class MB_8563 extends BaseTest {

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_8563(String browser) {
    super(browser, new String[]{BRANCH_ADMIN_ROLE});
  }

  @Test(description = "Verify records per page functionality works for branch admin at client management page")
  public void test_MB_8563_VerifyRecordsPerPageFunctionalityWorksForBranchAdminAtClientManagementPage() throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {

      reportLog("Login with user name ");
      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("Verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on Customer");
      customerPage = dashBoardPage.clickOnCustomer();

      reportLog("Verify drop down value");
      dashBoardPage.verifyRecordsPerPageDropDownValue();

      reportLog("Verify record per page");
      dashBoardPage.verifyRecordsPerPage();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();
    }
  }
}
