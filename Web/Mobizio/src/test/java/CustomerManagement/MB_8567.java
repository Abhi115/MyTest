package CustomerManagement;

import static com.mobizio.Utilities.Constants.BRANCH_ADMIN_ROLE;

import com.mobizio.datamodel.CustomerModel;
import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import com.mobizio.selenium.framework.Utilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_8567.java
 ClassName    : MB_8567
 Summary      : Verify branch admin is able to edit customers
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_8567_VerifyBranchAdminIsAbleToEditCustomers
 ===============================================================================================================================*/

public class MB_8567 extends BaseTest {

  private CustomerModel customerModel;
  private long timeStamp;
  private int randomInt;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_8567(String browser) {
    super(browser, new String[]{BRANCH_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    customerModel = ModelGenerator.generateCustomer(true);
    timeStamp = Utilities.getTimeStamp();
    randomInt = Utilities.getRandomInteger(0, 4);
  }

  @Test(description = "MB_8567_VerifyBranchAdminIsAbleToEditCustomers")
  public void test_MB_8567_VerifyBranchAdminIsAbleToEditCustomers() throws Exception {

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

      reportLog("Click on new created customer");
      customerPage.clickOnNewlyCreatedCustomer();

      reportLog("Edit customer details");
      customerPage.editCustomerDetails(customerModel, timeStamp, randomInt, loggedInUserType);

      reportLog("Click on update button");
      customerPage.clickOnUpdate();

      reportLog("Click on new created customer");
      customerPage.clickOnNewlyCreatedCustomer();

      reportLog("Verify edited details");
      customerPage.verifyEditedUserDetails(customerModel, timeStamp, randomInt);

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();
    }
  }
}