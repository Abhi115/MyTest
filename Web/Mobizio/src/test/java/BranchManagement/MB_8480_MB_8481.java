package BranchManagement;

import com.mobizio.Utilities.Constants;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
/*==============================================================================================================================
 File Name    : MB_8480_MB_8481.java
 ClassName    : MB_8480_MB_8481
 Summary      : MB-8481-Verify records per page functionality works at branch management. MB-8480-Verify
                * Pagination functionality works at Branch Management Page.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_8480_MB_8481_VerifyRecordsPerPageAndPaginationFunctionalityWorksAtBranchManagement
 ===============================================================================================================================*/

public class MB_8480_MB_8481 extends BaseTest {

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)

  public MB_8480_MB_8481(String browser) {
    super(browser, new String[]{Constants.TENANT_ADMIN_ROLE});
  }

  @Test(description = "MB_8480_MB_8481_VerifyRecordsPerPageFunctionalityWorksAtBranchManagement")
  public void test_MB_8480_MB_8481_VerifyRecordsPerPageAndPaginationFunctionalityWorksAtBranchManagement()
      throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {
      reportLog("Login into the application");
      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("Verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on Configuration Menu");
      dashBoardPage.clickOnConfiguration();

      reportLog("Click on Branches");
      branchPage = dashBoardPage.clickOnBranch();

      reportLog("Verify pagination at branch management page");
      dashBoardPage.verifyPagination();

      reportLog("Verify records per page drop down value");
      dashBoardPage.verifyRecordsPerPageDropDownValue();

      reportLog("Verify records per page");
      dashBoardPage.verifyRecordsPerPage();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();
    }
  }
}
