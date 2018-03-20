package VisitManagement;

import static com.mobizio.Utilities.Constants.BRANCH_ADMIN_ROLE;
import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.Utilities.Constants;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_4271_MB_4272_MB_4273_MB_4274.java
 ClassName    : MB_4271_MB_4272_MB_4273_MB_4274
 Summary      : MB-4271.Verify pagination functionality works for branch admin at task management page.
               * MB-4272.Verify pagination functionality works for tenant admin at task management page.
               * MB-4273.Verify records per page functionality works for tenant admin at task management page.
               * MB-4274.Verify records per page functionality works for branch admin at task management page.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_4271_MB_4272_MB_4273_MB_4274VerifyPaginationANDRecordsPerPageForBranchANDTenantAdmin
 ===============================================================================================================================*/

public class MB_4271_MB_4272_MB_4273_MB_4274 extends BaseTest {

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_4271_MB_4272_MB_4273_MB_4274(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE, BRANCH_ADMIN_ROLE});
  }

  @Test(description = "Verify Pagination and Records Per Page functionality")
  public void test_MB_4271_MB_4272_MB_4273_MB_4274VerifyPaginationANDRecordsPerPageForBranchANDTenantAdmin()
      throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {

      reportLog("Login with user name ");
      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("Verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on visit");
      taskPage = dashBoardPage.clickOnVisit();

      reportLog("Verify Pagination functionality");
      dashBoardPage.verifyPagination();

      reportLog("Verify records per page drop down value");
      dashBoardPage.verifyRecordsPerPageDropDownValue();

      reportLog("Verify records per page");
      dashBoardPage.verifyRecordsPerPage();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog(Constants.TEXT_TEST_PASSED);
    }
  }
}
