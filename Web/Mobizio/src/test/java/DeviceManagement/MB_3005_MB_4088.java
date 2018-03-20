package DeviceManagement;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.DeviceModel;
import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import com.mobizio.selenium.framework.Utilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_3005_MB_4088.java
 ClassName    : MB_3005_MB_4088
 Summary      : Verify User Is Able To Edit Registered Device AND Verify Last Column Localized
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_3005_MB_4088_VerifyUserIsAbleToEditRegisteredDeviceANDVerifyLastColumnLocalized
 ===============================================================================================================================*/
public class MB_3005_MB_4088 extends BaseTest {

  private int random = Utilities.getRandomInteger(10, 10000);
  private DeviceModel deviceModel;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_3005_MB_4088(String browser) {

    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    deviceModel = ModelGenerator.generateDetailsToEditDevice();
  }

  @Test(description = "MB_3005_MB_4088_VerifyUserIsAbleToEditRegisteredDeviceANDVerifyLastColumnLocalized")
  public void test_MB_3005_MB_4088_VerifyUserIsAbleToEditRegisteredDeviceANDVerifyLastColumnLocalized()
      throws Exception {

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

      reportLog("Edit Created device ");
      devicePage.editDevice(deviceModel, random);

      reportLog("Verify Last updated Column after Editing a device");
      dashBoardPage.verifyLastUpdatedLocalized();

      reportLog("Verify edited device name");
      devicePage.verifyEditedDevice(deviceModel, random);

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog(Constants.TEXT_TEST_PASSED);
    }
  }
}
