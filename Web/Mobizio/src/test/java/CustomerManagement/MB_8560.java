package CustomerManagement;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;
import static com.mobizio.enums.FileNames.CustomerProperties;

import com.mobizio.datamodel.CustomerModel;
import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import com.mobizio.selenium.framework.Configuration;
import com.mobizio.selenium.framework.Utilities;
import java.util.Properties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_8560.java
 ClassName    : MB_8560
 Summary      : Verify last updated column is localized after creating and updating any client with tenant admin.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      :   test_MB_8560_VerifyLastUpdatedColumnIsLocalizedAfterCreatingAndUpdatingAnyClientWithTenantAdmin
 ===============================================================================================================================*/

public class MB_8560 extends BaseTest {

  Properties prop;
  private CustomerModel customerModel;
  private long timeStamp;
  private int randomInt;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_8560(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    customerModel = ModelGenerator.generateCustomer(true);
    timeStamp = Utilities.getTimeStamp();
    randomInt = Utilities.getRandomInteger(0, 4);
    Configuration propertyReader = new Configuration(CustomerProperties.toString());
    prop = propertyReader.readApplicationData();
  }

  @Test(description = "Verify last updated column is localized after creating and updating any client with tenant admin")
  public void test_MB_8560_VerifyLastUpdatedColumnIsLocalizedAfterCreatingAndUpdatingAnyClientWithTenantAdmin() throws Exception {
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

      reportLog("click on new customer button");
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

      reportLog("Verify last updated column is localized");
      dashBoardPage.verifyLastUpdatedLocalized();

      reportLog("Click on created customer");
      customerPage.clickOnNewlyCreatedCustomer();

      //Set customer details
      customerModel.setBranch(prop.getProperty("editedBranchName"));
      customerModel.setGender(prop.getProperty("editedGenderName"));
      customerModel.setTitle(prop.getProperty("editedTitle"));
      customerModel.setDob(prop.getProperty("editedDOB"));
      customerModel.setCountry(prop.getProperty("editedCountry"));

      reportLog("Edit customer details");
      customerPage.editCustomerDetails(customerModel, timeStamp, randomInt,loggedInUserType);

      reportLog("Click on update button");
      customerPage.clickOnUpdate();

      reportLog("Verify last updated column is localized");
      dashBoardPage.verifyLastUpdatedLocalized();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();
    }
  }
}
