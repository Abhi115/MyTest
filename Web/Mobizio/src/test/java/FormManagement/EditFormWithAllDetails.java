package FormManagement;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.datamodel.FormModel;
import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
/*==============================================================================================================================
 File Name    : EditFormWithAllDetails.java
 ClassName    : EditFormWithAllDetails
 Summary      : Create New Form With All Details
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_CreateNewFormWithAllDetails
 ===============================================================================================================================*/

public class EditFormWithAllDetails extends BaseTest {

  private FormModel formModel;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public EditFormWithAllDetails(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    formModel = ModelGenerator.generateForm();
  }

  @Test(description = "CreateForm")
  public void test_CreateNewFormWithAllDetails() throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {

      reportLog("Login with user name ");
      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("Verify user login successfully");
      dashBoardPage.verifyLoginSuccess();

      reportLog("Click on Hamburger Menu");
      dashBoardPage.clickOnHamburgerIcon();

      reportLog("Click on Configuration Menu");
      dashBoardPage.clickOnConfiguration();

      reportLog("Click on form");
      formPage = dashBoardPage.clickOnForm();

      reportLog("Click on newform");
      formPage.clickOnNewForm();

      reportLog("Click on newform");
      formPage.clickOnFields();

      reportLog("add text field to form");
      formPage.dragAndDropTextField();

      reportLog("Click on added text field");
      formPage.clickOnTextField();

      reportLog("Fill details of added text field");
      formPage.fillDetailsInTextField(formModel);

      reportLog("Click on newform");
      formPage.clickOnFields();

      reportLog("Add number field to form");
      formPage.dragAndDropNumberField();

      reportLog("Click on number field");
      formPage.clickOnNumberField();

      reportLog("Select action for number field");
      formPage.selectValueInActionFieldForHide(formModel);

      reportLog("Select value in rule field");
      String hideValue = "form_component_1" + "=" + "Yes";
      formPage.enterValueInRuleField(hideValue);

      reportLog("Click on fields");
      formPage.clickOnFields();

      reportLog("Add paragraph field to form");
      formPage.dragAndDropParagraphField();

      reportLog("Click on paragraph field");
      formPage.clickOnParagraphField();

      reportLog("Select action for number field");
      formPage.selectValueInActionFieldForCalculate(formModel);

      reportLog("Select value in rule field");
      String calculateValue = "form_component_2" + "1";
      formPage.enterValueInRuleField(calculateValue);

      reportLog("Click On form setting");
      formPage.clickOnFormSettings();

      reportLog("Enter value of form name");
      formPage.enterValueInFormName(formModel);

      reportLog("Enter value of form header");
      formPage.enterValueInFormHeader(formModel);

      reportLog("Enter value of form instruction");
      formPage.enterValueInFormInstruction(formModel);

      reportLog("Enter value of required");
      formPage.enterValueInRequiredForm();

      reportLog("Click On save button");
      formPage.clickOnSave();

      reportLog("Click On publish button");
      formPage.clickOnPublish();

      reportLog("Verified created form name");
      String formname = formPage.getCreatedFormOnFormPage();

      reportLog("Searched created form name");
      formPage.searchCreatedFormOnFormPage(formname);

      reportLog("Open searched form on form page");
      formPage.openSearchedFormOnFormPage();

      reportLog("Edit searched form on form page");
      formPage.editSearchedFormOnFormPage();

      reportLog("Edit form name on form page");
      formPage.editValueOfFormName(formModel);

      reportLog("Edit form header on form page");
      formPage.editValueOfFormHeader(formModel);

      reportLog("Edit form instruction on form page");
      formPage.editValueOfFormInstruction(formModel);

      reportLog("Click on paragraph field");
      formPage.clickOnParagraphField();

      reportLog("Select action for number field");
      formPage.selectValueInActionFieldForHide(formModel);

      reportLog("Select value in rule field");
      String hideEditedValue = "form_component_1" + "=" + "Yes";
      formPage.enterValueInRuleField(hideEditedValue);

      reportLog("Click On save button");
      formPage.clickOnSave();

      reportLog("Click On publish button");
      formPage.clickOnPublish();

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();
    }
  }
}
