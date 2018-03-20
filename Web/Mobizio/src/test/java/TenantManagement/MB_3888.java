package TenantManagement;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.TenantModel;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_3888.java
 ClassName    : MB_3888
 Summary      : MB-3888.Verify System Admin is able to Add a Tenant.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version
 ===============================================================================================================================
 Remarks      :   test_MB_3888_VerifySystemAdminIsAbleToAddATenant
 ===============================================================================================================================*/
public class MB_3888 extends BaseTest {

  private TenantModel tenantModel;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_3888(String browser) {
    super(browser, new String[]{Constants.SYSTEM_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    tenantModel = new TenantModel();
  }

  @Test(description = "MB_3888_VerifySystemAdminIsAbleToAddATenant")
  public void test_MB_3888_VerifySystemAdminIsAbleToAddATenant() throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {
      reportLog("Login with user name ");
      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("Verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on Configuration");
      dashBoardPage.clickOnConfiguration();

      reportLog("Click on Tenants");
      tenantPage = dashBoardPage.clickOnTenants();

      reportLog("Click on Plus button to add a Tenant");
      tenantPage.clickOnPlusButton();

      reportLog("Add New Tenant Details and Create");
      tenantPage.addNewTenantDetails(tenantModel);

      reportLog("Verify Created Tenant");
      tenantPage.verifyCreatedTenantOnTenantsPage(tenantModel);

      reportLog("Logout of the application");
      dashBoardPage.logOut();

      reportLog(Constants.TEXT_TEST_PASSED);

    }
  }
}

