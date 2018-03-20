package BranchManagement;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.BranchModel;
import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import com.mobizio.selenium.framework.Utilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_8483.java
 ClassName    : MB_8483
 Summary      : MB-8483:Verify that tenant admin is able to Edit/update a Branch. MB-8479:Verify last updated
               * column is localized after creating and updating any branch.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_8483_VerifyTenantAdminIsAbleToEditBranch
 ===============================================================================================================================*/
public class MB_8483 extends BaseTest {

  private BranchModel branchModel;
  private int randomNumber;
  private String randomString;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_8483(String browser) {
    super(browser, new String[]{Constants.TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    branchModel = ModelGenerator.generateBranch();
    randomNumber = Utilities.getRandomInteger(0, 100);
    randomString = Utilities.randomString(4).toLowerCase();
  }

  @Test(description = "MB_8483_VerifyTenantAdminIsAbleToEditBranch")
  public void test_MB_8483_VerifyTenantAdminIsAbleToEditBranch() throws Exception {

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

      reportLog("Click on NewBranch Button");
      branchPage.clickOnNewBranch();

      reportLog("Enter new branch details");
      branchPage.enterNewBranchDetail(branchModel);

      reportLog("Click on newly created branch");
      branchPage.clickOnNewlyCreatedBranch();

      reportLog("Verify created branch details");
      branchPage.verifyBranchDetailsOnBranchPage(branchModel);

      reportLog("Click on branch page");
      branchPage.clickOnBranchPage();

      reportLog("Click on newly created branch");
      branchPage.clickOnNewlyCreatedBranch();

      reportLog("Click on edit branch button");
      branchPage.clickOnEditBranchButton();

      reportLog("Edit branch detail");
      branchPage.editBranchDetail(branchModel, randomNumber, randomString);

      reportLog("Click on update button");
      branchPage.clickOnUpdateButton();

      reportLog("Verify Last updated date and time is localized updating any branch");
      dashBoardPage.verifyLastUpdatedLocalized();

      reportLog("Click on edited branch");
      branchPage.clickOnNewlyCreatedBranch();

      reportLog("Verify edited branch details");
      branchPage.verifyEditedBranchDetails(branchModel, randomNumber, randomString);

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog(Constants.TEXT_TEST_PASSED);
    }
  }
}
