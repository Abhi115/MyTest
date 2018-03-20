package BranchManagement;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.BranchModel;
import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
/*==============================================================================================================================
 File Name    : MB_8478_MB_8479.java
 ClassName    : MB_8478_MB_8479
 Summary      : Verify That Tenant Admin Redirect To Branch Admin Page After Create New Branch
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_8478_MB_8479_VerifyThatTenantAdminRedirectToBranchAdminPageAfterCreateNewBranch
 ===============================================================================================================================*/
public class MB_8478_MB_8479 extends BaseTest {

  private BranchModel branchModel;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_8478_MB_8479(String browser) {
    super(browser, new String[]{Constants.TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    branchModel = ModelGenerator.generateBranch();
  }

  @Test(description = "create new branch with all fields")
  public void test_MB_8478_MB_8479_VerifyThatTenantAdminRedirectToBranchAdminPageAfterCreateNewBranch()
      throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {
      //reportLog("Login with user name ");
      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on Configuration Menu");
      dashBoardPage.clickOnConfiguration();

      reportLog("Click on Branches");
      branchPage = dashBoardPage.clickOnBranch();

      reportLog("Click on NewBranch Button");
      branchPage.clickOnNewBranch();

      reportLog("Enter new branch details");
      branchPage.enterNewBranchDetail(branchModel);

      reportLog("verify new branch, it also verify that we are on branch management page.");
      branchPage.verifyCreatedBranchOnBranchPage(branchModel.getName());

      reportLog("Verify last updated date and time is Localized after creating New Branch");
      dashBoardPage.verifyLastUpdatedLocalized();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();
    }
  }
}
