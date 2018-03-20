package CustomerManagement;

import static com.mobizio.Utilities.Constants.BRANCH_ADMIN_ROLE;

import com.mobizio.datamodel.CustomerModel;
import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_3608.java
 ClassName    : MB_3608
 Summary      : Verify Branch Admin Is Able To Create Customers
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_3608_VerifyBranchAdminIsAbleToCreateCustomers
 ===============================================================================================================================*/
public class MB_3608 extends BaseTest {

  private CustomerModel customerModel;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_3608(String browser) {
    super(browser, new String[]{BRANCH_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    customerModel = ModelGenerator.generateCustomer(false);
  }

  @Test(description = "MB_3608_VerifyBranchAdminIsAbleToCreateCustomers")
  public void test_MB_3608_VerifyBranchAdminIsAbleToCreateCustomers() throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {

      reportLog("Login with user name ");
      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("Verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on Customer");
      customerPage = dashBoardPage.clickOnCustomer();

      reportLog("Verify Customer Page");
      customerPage.verifyCustomerPage();

      reportLog("Click on new customer button");
      customerPage.clickOnAddNewCustomer();

      reportLog("Verify new customer page");
      customerPage.verifyNewCustomerPage();

      reportLog("Enter new customer details");
      customerPage.enterNewCustomerDetails(customerModel, loggedInUserType);

      reportLog("Click on create customer button");
      customerPage.clickOnCreateButton();

      reportLog("Verify Customer Page");
      customerPage.verifyCustomerPage();

      reportLog("Verify newly created customer on customer page ");
      customerPage.verifyCreatedCustomerOnCustomerPage(customerModel.getUserName());

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();
    }
  }
}
