package DeviceManagement;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.DeviceModel;
import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_3779.java
 ClassName    : MB_3779
 Summary      : Verify Tenant Admin User Is Able To Add A Device
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_3779_VerifyTenantAdminUserIsAbleToAddADevice
 ===============================================================================================================================*/
public class MB_3779 extends BaseTest {

  private DeviceModel deviceModel;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_3779(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    deviceModel = ModelGenerator.generateDevice();
  }

  @Test(description = "MB_3779_VerifyTenantAdminUserIsAbleToAddADevice")
  public void test_MB_3779_VerifyTenantAdminUserIsAbleToAddADevice() throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {

      reportLog("Login with user name ");
      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on Configuration Menu");
      dashBoardPage.clickOnConfiguration();

      reportLog("Click on Device");
      devicePage = dashBoardPage.clickOnDevice();

      reportLog("Click on new device button");
      devicePage.clickOnNewDevice();

      reportLog("Enter device details");
      devicePage.enterNewDeviceDetail(deviceModel);

      reportLog("Verify device name");
      devicePage.verifyCreatedDeviceOnDevicePage(deviceModel.getUUID());

      reportLog("Verify Last updated Column after creating a device");
      dashBoardPage.verifyLastUpdatedLocalized();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog(Constants.TEXT_TEST_PASSED);
    }
  }
}
