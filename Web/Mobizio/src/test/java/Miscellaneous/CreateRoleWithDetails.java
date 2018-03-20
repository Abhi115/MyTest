package Miscellaneous;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.datamodel.RoleModel;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.pages.ServicesPage;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class CreateRoleWithDetails extends BaseTest {

  private RoleModel roleModel;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public CreateRoleWithDetails(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    roleModel = ModelGenerator.generateNewRole();
  }

  @Test(description = "Create Roles with New Details")
  public void test_CreateNewBranchWithAllDetails() throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {

      reportLog("Login with user name ");
      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on Configuration Menu");
      configurationPage = dashBoardPage.clickOnConfiguration();

      reportLog("click on services");
      ServicesPage servicesPage = configurationPage.clickOnServices();

      reportLog("click on service name");
      servicesPage.clickOnService();

      reportLog("click on roles");
      rolePage = servicesPage.clickOnroles();

      reportLog("click on new role button");
      rolePage.clickOnNewRole();

      reportLog("enter roles details");
      rolePage.enterRoleDetails(roleModel);

      reportLog("verify new role created");
      rolePage.verifyNewRole(roleModel);

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("verify logout successfully");
      loginPage.verifyLoginPage();
    }
  }
}
