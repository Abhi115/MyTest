package VisitManagement;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.datamodel.BranchModel;
import com.mobizio.datamodel.UserModel;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.enums.FileNames;
import com.mobizio.selenium.framework.BaseTest;
import com.mobizio.selenium.framework.Configuration;
import com.mobizio.selenium.framework.Utilities;
import java.util.Properties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_4105.java
 ClassName    : MB_4105
 Summary      : Verify User Is Unable To Create A Task If Assignee Is Non Shared And Belongs To Some Other Branch Than Task
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_4105_Verify_User_Is_Unable_To_Create_A_Task_If_Assignee_Is_Non_Shared_And_Belongs_To_Some_Other_Branch_Than_Task
 ===============================================================================================================================*/

public class MB_4105 extends BaseTest {

  private UserModel userModel;
  private BranchModel branchModel;
  private final String randomString = Utilities.randomString(6).toLowerCase();

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_4105(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {

    //initialize branch data
    branchModel = new BranchModel();
    Configuration branchReader = new Configuration(FileNames.BranchProperties.toString());
    Properties prop=branchReader.readApplicationData();
    branchModel.setName(prop.getProperty("name") + randomString);
    branchModel.setTenantBranchID(prop.getProperty("tenantBranchID") + Utilities.getRandomInteger(0, 10000));
    branchModel.setPrimaryTelephone(prop.getProperty("primaryTelephone"));
    branchModel.setSecondaryTelephone(prop.getProperty("secondaryTelephone"));
    branchModel.setAddressLine1(prop.getProperty("addressLine1"));
    branchModel.setAddressLine2(prop.getProperty("addressLine2"));
    branchModel.setCity(prop.getProperty("city"));
    branchModel.setCounty(prop.getProperty("county"));
    branchModel.setCountry(prop.getProperty("country"));
    branchModel.setPostcode(prop.getProperty("postcode"));
    branchModel.setFax(prop.getProperty("fax"));
    branchModel.setAlertNotificationEmail(prop.getProperty("name") + randomString + prop.getProperty("alertNotificationEmail"));
    branchModel.setAlertNotificationPhoneNumber(prop.getProperty("alertNotificationPhoneNumber"));

    // initialize user data
    Configuration userReader = new Configuration(FileNames.UserCarerProperties.toString());
    Properties prop1=userReader.readApplicationData();
    userModel = new UserModel();
    userModel.setFirstName(prop1.getProperty("firstName"));
    userModel.setLastName(prop1.getProperty("lastName"));
    userModel.setEmail(userModel.getFirstName() + userModel.getLastName() + "@mailinator.com");
    userModel.setUserName(userModel.getFirstName() + userModel.getLastName() + randomString);
    userModel.setTenantUserId(prop1.getProperty("tenantUserId") + randomString);
    userModel.setPassword(prop1.getProperty("password"));
    userModel.setConfirmPassword(prop1.getProperty("confirmPassword"));
    userModel.setUserType(prop1.getProperty("userType"));
    userModel.setBranch(prop.getProperty("name") + randomString);
    userModel.setTitle(prop1.getProperty("title"));
    userModel.setDob(prop1.getProperty("dob"));
    userModel.setGender(prop1.getProperty("gender"));
    userModel.setAddressLine1(prop1.getProperty("addressLine1"));
    userModel.setAddressLine2(prop1.getProperty("addressLine2"));
    userModel.setPostCode(prop1.getProperty("postcode"));
    userModel.setCounty(prop1.getProperty("county"));
    userModel.setCountry(prop1.getProperty("country"));
    userModel.setLatitude(prop1.getProperty("latitude"));
    userModel.setLongitude(prop1.getProperty("longitude"));
    userModel.setPrimaryTelephone(prop1.getProperty("primaryTelephone"));
    userModel.setSecondaryTelephone(prop1.getProperty("secondaryTelephone"));
    userModel.setCity(prop1.getProperty("city"));
    userModel.setPin(prop1.getProperty("pin"));
  }

  @Test
  public void test_MB_4105_Verify_User_Is_Unable_To_Create_A_Task_If_Assignee_Is_Non_Shared_And_Belongs_To_Some_Other_Branch_Than_Task()
      throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {
      // login as Tenant Admin and create new branch
      reportLog("Login with tenant admin");
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

      reportLog("enter new branch details");
      branchPage.enterNewBranchDetail(branchModel);

      reportLog("verify new branch, it also indicates that we are on branch management page.");
      branchPage.verifyCreatedBranchOnBranchPage(branchModel.getName());

      //create new carer
      reportLog("Click on Users");
      userPage = dashBoardPage.clickOnUsers();

      reportLog("verify users page");
      userPage.verifyUsersPage();

      reportLog("click on new user button");
      userPage.clickOnNewUserButton();

      reportLog("verify new user page");
      userPage.verifyNewUserPage();

      reportLog("enter new user details");
      userPage.enterNewUserDetails(userModel, "Tenant", "Carer");

      reportLog("click on create button");
      userPage.clickOnCreateButton();

      reportLog("verify users page");
      userPage.verifyUsersPage();

      reportLog("verify newly created user on user page ");
      userPage.verifyCreatedUserOnUserPage(userModel.getUserName());

      reportLog("mark carer as non-shared");
      userPage.markCarerAsNonShared(userModel.getUserName());

      // logout from the application
      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("verify logout successfully");
      loginPage.verifyLoginPage();

			/*login as Branch Admin and verify that user is unable to create a task if assignee 
      is non shared and belongs to some other branch than task.*/

      reportLog("Login with tenant admin");
      dashBoardPage = loginPage.login("Branch");

      reportLog("Verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on visit");
      taskPage = dashBoardPage.clickOnVisit();

      reportLog("Verify that user is unable to create a task if assignee is non shared and belongs to some other branch than task");
      taskPage.verifyUserIsNotAbleToCreateTask(userModel.getUserName());
    }
  }
}
