package Miscellaneous;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.datamodel.SectionModel;
import com.mobizio.datamodel.ServiceModel;
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
 File Name    : CreateNewSection.java
 ClassName    : CreateNewSection
 Summary      : Create New Section
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      :   test_CreateNewSection
 ===============================================================================================================================*/

public class CreateNewSection extends BaseTest {

  ServiceModel serviceModel;
  SectionModel sectionModel;
  String randomString = Utilities.randomString(4).toLowerCase();
  String[] loginUser = {"Tenant"};
  Properties prop;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public CreateNewSection(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    Configuration servicePropertyReader = new Configuration(FileNames.ServiceProperties.toString());
    Configuration sectionPropertyReader = new Configuration(FileNames.SectionProperties.toString());
    serviceModel = new ServiceModel();
    sectionModel = new SectionModel();
    prop=sectionPropertyReader.readApplicationData();
    prop=servicePropertyReader.readApplicationData();
    serviceModel.setServiceName(prop.getProperty("serviceName"));
    sectionModel.setName(prop.getProperty("sectionName") + randomString);
  }

  @Test(description = "create new section")
  public void test_CreateNewSection() throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {

      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("Verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on Configuration");
      dashBoardPage.clickOnConfiguration();

      reportLog("Click on services");
      dashBoardPage.clickOnServices();

      reportLog("Click on service name");
      servicesPage.clickOnServiceName(serviceModel);

      reportLog("Click on sections");
      servicesPage.clickOnSections();

      reportLog("Click on new section button");
      sectionPage.clickOnNewSection();

      reportLog("Enter new section name");
      sectionPage.enterNewSectionName(sectionModel);

      reportLog("Click on create button");
      sectionPage.clickOnCreateButton();

      reportLog("Verify newly created section");
      sectionPage.verifyNewlyCreatedSection(sectionModel);

      reportLog("Logout from application");
      dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();
    }
  }
}
