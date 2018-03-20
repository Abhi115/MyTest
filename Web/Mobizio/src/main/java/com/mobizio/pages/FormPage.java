package com.mobizio.pages;

import com.mobizio.datamodel.FormModel;
import com.mobizio.selenium.framework.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class FormPage extends BasePage {

  @FindBy(id = "addNewFormBtn")
  private WebElement newForm;

  @FindBy(xpath = "//*[@id='left']/ul/li[1]/a")
  private WebElement fields;

  @FindBy(xpath = "//*[@id='0']/li")
  private WebElement untitledForm;

  @FindBy(xpath = "//*[@id='BasicMenu']/li[1]/div/input[1]")
  private WebElement textField;

  @FindBy(xpath = "//*[@id='BasicMenu']/li[2]/div/input[@value='Number']")
  private WebElement numberField;

  @FindBy(xpath = "//*[@value='Paragraph Box']")
  private WebElement paragraphField;

  @FindBy(xpath = "//*[@value='Decimal']")
  private WebElement decimalField;

  @FindBy(xpath = "//*[@value='Multi-Select']")
  private WebElement multiSelectField;

  @FindBy(xpath = "//*[@value='Dropdown']")
  private WebElement dropDownField;

  @FindBy(xpath = "//*[@value='Time']")
  private WebElement timeField;

  @FindBy(xpath = "//*[@value='Date']")
  private WebElement dateField;

  @FindBy(xpath = "//*[@value='Radio Button']")
  private WebElement radioButtonField;

  @FindBy(xpath = "//*[@value='Checkbox']")
  private WebElement checkBoxField;

  @FindBy(xpath = "//*[@value='Signature']")
  private WebElement signatureField;

  @FindBy(xpath = "//*[@value='Photo']")
  private WebElement photoField;

  @FindBy(xpath = "//*[@value='Barcode']")
  private WebElement barcodeField;

  @FindBy(xpath = "//*[@value='Hyperlink']")
  private WebElement hyperlinkField;

  @FindBy(xpath = "//*[@value='Label']")
  private WebElement labelField;

  @FindBy(xpath = "//*[@value='Customer Picker']")
  private WebElement customerPickerField;

  @FindBy(xpath = "//*[@value='User Picker']")
  private WebElement userPickerField;

  @FindBy(xpath = "//*[@id='addTable']/button")
  private WebElement tableField;

  @FindBy(xpath = "//*[@id='addSection']/button")
  private WebElement sectionField;

  @FindBy(xpath = "//*[@id='actionD']")
  private WebElement action;

  @FindBy(name = "ruleD")
  private WebElement rule;

  @FindBy(xpath = "//*[@id='li_1']")
  private WebElement textFieldInUntitledForm;

  @FindBy(xpath = "//*[@id='li_2']")
  private WebElement numberFieldInUntitledForm;

  @FindBy(xpath = "//*[@id='li_3']")
  private WebElement paragraphFieldInUntitledForm;

  @FindBy(xpath = "//*[@id='label']")
  private WebElement labelOfTextField;

  @FindBy(name = "helptext")
  private WebElement instructionOfTextField;

  @FindBy(xpath = "//*[@id='default']")
  private WebElement defaultValueOfTextField;

  @FindBy(name = "required")
  private WebElement isRequiredTextField;

  @FindBy(xpath = "//*[@id='left']/ul/li/a[text()='Form Settings']")
  private WebElement formSetting;

  @FindBy(xpath = "//*[@id='nameValueInput']")
  private WebElement formName;

  @FindBy(name = "str_HeaderForm")
  private WebElement formHeader;

  @FindBy(name = "str_instructionsForm")
  private WebElement formInstruction;

  @FindBy(name = "requiredForm")
  private WebElement requiredForm;

  @FindBy(xpath = "//*[@id='btnActionSave']")
  private WebElement saveButton;

  @FindBy(id = "btnActionPublish")
  private WebElement publishButton;

  @FindBy(xpath = "//*[@id='formsTable']/tbody/tr[1]/td[1]")
  private WebElement formNameOnFormDetailPage;

  @FindBy(xpath = "//*[@id='formsTable_filter']/label/input")
  private WebElement searchFormOnFormDetailPage;

  @FindBy(xpath = "//*[@id='formsTable']/tbody/tr/td[1]/a")
  private WebElement searchFormLink;

  @FindBy(xpath = "//*[@id='content']/section/div/a[text()='Edit']")
  private WebElement editFormButton;

  public FormPage(WebDriver driver) {
    super(driver);
  }

  /**
   * click on add new branch button
   */
  public void clickOnNewForm() {
    _waitForJStoLoad();
    waitForElement(newForm);
    clickOn(newForm);
    _waitForJStoLoad();
  }

  /**
   * click on fields
   */
  public void clickOnFields() {
    _waitForJStoLoad();
    waitForElement(fields);
    clickOn(fields);
    _waitForJStoLoad();
  }

  /**
   * click on form setting
   */
  public void clickOnFormSettings() {
    waitForElement(formSetting);
    clickOn(formSetting);
    _waitForJStoLoad();
  }


  /**
   * click on save button
   */
  public void clickOnSave() {
    _waitForJStoLoad();
    waitForElement(saveButton);
    clickOn(saveButton);
    _waitForJStoLoad();
  }

  /**
   * click on publish button
   */
  public void clickOnPublish() {
    _waitForJStoLoad();
    waitForElement(publishButton);
    clickOn(publishButton);
    _waitForJStoLoad();
  }

  /**
   * Enter value in form name
   *
   * @param formModel: Enter form details
   */
  public void enterValueInFormName(FormModel formModel) {
    waitForElement(formName);
    inputText(formName, formModel.getFormName());
  }

  /**
   * Enter value in form header
   */
  public void enterValueInFormHeader(FormModel formModel) {
    waitForElement(formHeader);
    inputText(formHeader, formModel.getFormHeader());
  }

  /**
   * Enter value in instruction
   */
  public void enterValueInFormInstruction(FormModel formModel) {
    waitForElement(formInstruction);
    inputText(formInstruction, formModel.getFormInstruction());
  }

  /**
   * Enter value in required
   */
  public void enterValueInRequiredForm() {
    waitForElement(requiredForm);
    clickOn(requiredForm);
  }

  /**
   * Enter value in required field
   */
  public void dragAndDropTextField() {
    dragAndDrop(textField, untitledForm);

  }

  /**
   * Drag and drop number field to untitled form
   */
  public void dragAndDropNumberField() {
    dragAndDrop(numberField, untitledForm);

  }

  /**
   * Drag and drop paragraph field to untitled form
   */
  public void dragAndDropParagraphField() {
    dragAndDrop(paragraphField, untitledForm);

  }

  /**
   * Drag and drop decimal field to untitled form
   */
  public void dragAndDropDecimalField() {
    dragAndDrop(decimalField, untitledForm);

  }

  /**
   * Drag and drop multi-selected field
   */
  public void dragAndDropMultiSelectField() {
    dragAndDrop(multiSelectField, untitledForm);

  }

  /**
   * Drag and drop drop-down field
   */
  public void dragAndDropDropDownField() {
    dragAndDrop(dropDownField, untitledForm);

  }

  /**
   * Drag and drop time field
   */
  public void dragAndDropTimeField() {
    dragAndDrop(timeField, untitledForm);

  }

  /**
   * Drag and drop date field
   */
  public void dragAndDropDateField() {
    dragAndDrop(dateField, untitledForm);

  }

  /**
   * Drag and drop radio button field
   */
  public void dragAndDropRadioButtonField() {
    dragAndDrop(radioButtonField, untitledForm);

  }

  /**
   * Drag and drop checkbox field
   */
  public void dragAndDropCheckboxField() {
    dragAndDrop(checkBoxField, untitledForm);

  }

  /**
   * Drag and drop signature field
   */
  public void dragAndDropSignatureField() {
    dragAndDrop(signatureField, untitledForm);

  }

  /**
   * Drag and drop photo field
   */
  public void dragAndDropPhotoField() {
    dragAndDrop(photoField, untitledForm);

  }

  /**
   * Drag and drop barcode field
   */
  public void dragAndDropBarcodeField() {
    dragAndDrop(barcodeField, untitledForm);

  }

  /**
   * Drag and drop hyperlink field
   */
  public void dragAndDropHyperlinkField() {
    dragAndDrop(hyperlinkField, untitledForm);

  }

  /**
   * Drag and drop label field
   */
  public void dragAndDropLabelField() {
    dragAndDrop(labelField, untitledForm);

  }

  /**
   * Drag and drop customer-picker field
   */
  public void dragAndDropCustomerPickerField() {
    dragAndDrop(customerPickerField, untitledForm);

  }

  /**
   * Drag and drop user-picker field
   */
  public void dragAndDropUserPickerField() {
    dragAndDrop(userPickerField, untitledForm);

  }

  /**
   * Drag and drop table field
   */
  public void dragAndDropTableField() {
    dragAndDrop(tableField, untitledForm);

  }

  /**
   * Drag and drop section field
   */
  public void dragAndDropSectionField() {
    dragAndDrop(sectionField, untitledForm);

  }

  /**
   * Click on fields
   */
  public void clickOnTextField() {
    waitForElement(textFieldInUntitledForm);
    clickOn(textFieldInUntitledForm);
  }

  /**
   * Click on fields
   */
  public void clickOnNumberField() {
    waitForElement(numberFieldInUntitledForm);
    clickOn(numberFieldInUntitledForm);
  }

  /*
   * click on fields
   */
  public void clickOnParagraphField() {
    waitForElement(paragraphFieldInUntitledForm);
    clickOn(paragraphFieldInUntitledForm);
  }

  /**
   * select value of hide from dropdown
   *
   * @param formModel: Enter form details
   */
  public void selectValueInActionFieldForHide(FormModel formModel) {
    waitForElement(action);
    clickOn(action);
    selectDropDownByText(action, formModel.getHide());
  }

  /**
   * select value of calculate from drop down
   *
   * @param formModel: Enter form details
   */
  public void selectValueInActionFieldForCalculate(FormModel formModel) {
    waitForElement(action);
    clickOn(action);
    selectDropDownByText(action, formModel.getCalculate());
  }

  /**
   * Enter value in rule field for hide and calculate logic
   *
   * @param value: Enter string value
   * @throws InterruptedException: Interrupted exception
   */
  public void enterValueInRuleField(String value) {
    waitForElement(rule);
    waitForElementClickable(rule, 30);
    inputText(rule, value);
  }


  /**
   * Click on fields
   *
   * @param formModel: Enter s form details
   */
  public void fillDetailsInTextField(FormModel formModel) {
    waitForElement(labelOfTextField);
    clickOn(labelOfTextField);
    inputText(labelOfTextField, formModel.getName());
    waitForElement(instructionOfTextField);
    clickOn(instructionOfTextField);
    inputText(instructionOfTextField, formModel.getDescription());
    waitForElement(defaultValueOfTextField);
    clickOn(defaultValueOfTextField);
    inputText(defaultValueOfTextField, formModel.getDefaultValue());
    waitForElement(isRequiredTextField);
    clickOn(isRequiredTextField);
  }

  /**
   * Verify newly created customer on customer page
   */
  public void verifyCreatedFormOnFormPage(String expectedFormName) {
    String form = formNameOnFormDetailPage.getText();
    Assert.assertEquals(form, expectedFormName, "Form name does not match");
  }

  /**
   * Get newly created customer
   *
   * @return: Created form on form page.
   */
  public String getCreatedFormOnFormPage() {
    return formNameOnFormDetailPage.getText();
  }


  /**
   * search form
   *
   * @param formName: Enter form name
   */
  public void searchCreatedFormOnFormPage(String formName) {
    clickOn(searchFormOnFormDetailPage);
    inputText(searchFormOnFormDetailPage, formName);
    searchFormOnFormDetailPage.sendKeys(Keys.ENTER);
    _waitForJStoLoad();
  }

  /**
   * click on searched form
   */
  public void openSearchedFormOnFormPage() {
    clickOn(searchFormLink);
    _waitForJStoLoad();
  }

  /**
   * click on edit form
   */
  public void editSearchedFormOnFormPage() {
    clickOn(editFormButton);
    _waitForJStoLoad();
  }

  /**
   * Edit name of form
   *
   * @param formModel: Enter form details
   */
  public void editValueOfFormName(FormModel formModel) {
    waitForElement(formName);
    Assert.assertEquals(formName.getAttribute("readonly"), null, "Form attribute does not match");
    formName.clear();
    String editedFormNameValue = formModel.getFormName() + "edited";
    inputText(formName, editedFormNameValue);

  }

  /**
   * Edit name of form header
   *
   * @param formModel: Enter form details
   */
  public void editValueOfFormHeader(FormModel formModel) {
    waitForElement(formHeader);
    Assert.assertEquals(formHeader.getAttribute("readonly"), null, "Form header does not match");
    formHeader.clear();
    String editedFormNameValue = formModel.getFormHeader() + "edited";
    inputText(formHeader, editedFormNameValue);

  }

  /**
   * Edit name of form instruction
   *
   * @param formModel: Enter form details
   */
  public void editValueOfFormInstruction(FormModel formModel) {
    waitForElement(formInstruction);
    Assert.assertEquals(formInstruction.getAttribute("readonly"), null, "Form instruction does not match");
    formInstruction.clear();
    String editedFormNameValue = formModel.getFormInstruction() + "edited";
    inputText(formInstruction, editedFormNameValue);
  }

  /**
   * Edit value in rule field for hide and calculate logic
   *
   * @param value: Enter String value
   */
  public void editValueInRuleField(String value) {
    waitForElement(rule);
    Assert.assertEquals(rule.getAttribute("readonly"), null, "Form rule does not match");
    inputText(rule, value);
  }

}
