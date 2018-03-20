package DeviceManagement;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.Utilities.Constants;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_4086_MB_4087.java
 ClassName    : MB_4086_MB_4087
 Summary      : MB-4086.Verify pagination functionality works for Tenant admin at Device management page.
                * MB-4087.Verify records per page functionality works for Tenant admin at Device management page.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_4086_MB_4087_VerifyPaginationANDRecordsPerPageFunctionalityAtDeviceManagementPage
 ===============================================================================================================================*/
public class MB_4086_MB_4087 extends
    BaseTest {

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_4086_MB_4087(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @Test(description = "MB_4086_MB_4087_VerifyPaginationANDRecordsPerPageFunctionalityAtDeviceManagementPage")
  public void test_MB_4086_MB_4087_VerifyPaginationANDRecordsPerPageFunctionalityAtDeviceManagementPage()
      throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {

      reportLog("Login with user name ");
      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("Verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on visit");
      dashBoardPage.clickOnConfiguration();

      reportLog("Click on Device");
      devicePage = dashBoardPage.clickOnDevice();

      reportLog("Verify Pagination functionality");
      dashBoardPage.verifyPagination();

      reportLog("verify records per page drop down value");
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
