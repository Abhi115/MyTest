package FormManagement;

import static com.mobizio.Utilities.Constants.TENANT_ADMIN_ROLE;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.FormModel;
import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
/*==============================================================================================================================
 File Name    : MB_3781.java
 ClassName    : MB_3781
 Summary      : Verify Tenant Admin Is Able To Create A Form
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_3781_VerifyTenantAdminIsAbleToCreateAForm
 ===============================================================================================================================*/

public class MB_3781 extends BaseTest {

  private FormModel formModel;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_3781(String browser) {
    super(browser, new String[]{TENANT_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    formModel = ModelGenerator.generateForm();
  }

  @Test(description = "VerifyTenantAdminIsAbleToCreateAForm")
  public void test_MB_3781_VerifyTenantAdminIsAbleToCreateAForm() throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {

      reportLog("Login with user name ");
      dashBoardPage = loginPage.login(loggedInUserType);

      reportLog("verify user login successfully");
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

      reportLog("Add text field to form");
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

      reportLog("Click on fields");
      formPage.clickOnFields();

      reportLog("Add decimal field to form");
      formPage.dragAndDropDecimalField();

      reportLog("Add multiselect field to form");
      formPage.dragAndDropMultiSelectField();

      reportLog("Add dropdown field to form");
      formPage.dragAndDropDropDownField();

      reportLog("Add time field to form");
      formPage.dragAndDropTimeField();

      reportLog("Add date field to form");
      formPage.dragAndDropDateField();

      reportLog("Add radio button field to form");
      formPage.dragAndDropRadioButtonField();

      reportLog("Add checkbox field to form");
      formPage.dragAndDropCheckboxField();

      reportLog("Add signature field to form");
      formPage.dragAndDropSignatureField();

      reportLog("add photo field to form");
      formPage.dragAndDropPhotoField();

      reportLog("Add barcode field to form");
      formPage.dragAndDropBarcodeField();

      reportLog("Add hyperlink field to form");
      formPage.dragAndDropHyperlinkField();

      reportLog("Add label field to form");
      formPage.dragAndDropLabelField();

      reportLog("Add customer picker field to form");
      formPage.dragAndDropCustomerPickerField();

      reportLog("Add user picker field to form");
      formPage.dragAndDropUserPickerField();

      reportLog("Add table field to form");
      formPage.dragAndDropTableField();

      reportLog("Add section field to form");
      formPage.dragAndDropSectionField();

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

      reportLog("Elick On save button");
      formPage.clickOnSave();

      reportLog("Click On publish button");
      formPage.clickOnPublish();

      reportLog("Verified created form name");
      formPage.verifyCreatedFormOnFormPage(formModel.getFormName());

      reportLog("Logout from application");
      loginPage = dashBoardPage.logOut();

      reportLog("Verify logout successfully");
      loginPage.verifyLoginPage();

      reportLog(Constants.TEXT_TEST_PASSED);
    }
  }
}
