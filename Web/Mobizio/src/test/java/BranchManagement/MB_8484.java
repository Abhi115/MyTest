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
 File Name    : MB_8484.java
 ClassName    : MB_8484
 Summary      : Verify That Tenant Admin Is Able To Create A Branch
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_8484_VerifyThatTenantAdminIsAbleToCreateABranch
 ===============================================================================================================================*/
public class MB_8484 extends BaseTest {

  private BranchModel branchModel;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_8484(String browser) {
    super(browser, new String[]{Constants.TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    branchModel = ModelGenerator.generateBranch();
  }

  @Test(description = "create new branch with all fields")
  public void test_MB_8484_VerifyThatTenantAdminIsAbleToCreateABranch() throws Exception {

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

      reportLog("Verify new branch, it also indicates that we are on branch management page.");
      branchPage.verifyCreatedBranchOnBranchPage(branchModel.getName());

      //MB-8482:Verify tenant admin is able to search a branch.
      reportLog("Search new branch");
      branchPage.searchCreatedBranchOnBranchPage(branchModel.getName());

      reportLog("Click on searched branch");
      branchPage.clickOnSearchedBranchOnBranchPage();

      reportLog("Verify branch detail on branch page");
      branchPage.verifyBranchDetailsOnBranchPage(branchModel);

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog(Constants.TEXT_TEST_PASSED);
    }
  }
}
