package TenantManagement;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.ServiceModel;
import com.mobizio.datamodel.TenantModel;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_3891.java
 ClassName    : MB_3891
 Summary      : MB-3891.Verify System Admin is able to Add a Service.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version
 ===============================================================================================================================
 Remarks      :   test_MB_3891_VerifySystemAdminIsAbleToAddAServices
 ===============================================================================================================================*/
public class MB_3891 extends BaseTest {

  private TenantModel tenantModel;
  private ServiceModel serviceModel;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_3891(String browser) {
    super(browser, new String[]{Constants.SYSTEM_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    tenantModel = new TenantModel();
    serviceModel = new ServiceModel();
  }

  @Test(description = "MB_3891_VerifySystemAdminIsAbleToAddAServices")
  public void test_MB_3891_VerifySystemAdminIsAbleToAddAServices() throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {
      reportLog("Login with user name ");
      dashBoardPage = loginPage.login("System");

      reportLog("verify user login successfully");
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

      reportLog("Add A new Service to created tenant Tenant");
      tenantPage.addAService(serviceModel);

      reportLog("Verify Added service into the tenant");
      tenantPage.verifyAddedService(serviceModel);

      reportLog("Logout of the application");
      dashBoardPage.logOut();

      reportLog(Constants.TEXT_TEST_PASSED);
    }
  }
}


