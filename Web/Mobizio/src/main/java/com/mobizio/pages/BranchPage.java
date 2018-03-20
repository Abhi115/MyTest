package com.mobizio.pages;

import com.mobizio.datamodel.BranchModel;
import com.mobizio.enums.Action;
import com.mobizio.selenium.framework.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class BranchPage extends BasePage {

  @FindBy(id = "addNewBranchBtn")
  private WebElement newBranchBtn;

  @FindBy(css = "#nameValueInput")
  private WebElement nameInput;

  @FindBy(name = "tenantBranchId")
  private WebElement tenantBranchIDInput;

  @FindBy(name = "primaryTelephone")
  private WebElement primaryTelephoneInput;

  @FindBy(name = "secondaryTelephone")
  private WebElement secondaryTelephoneInput;

  @FindBy(name = "firstLine")
  private WebElement addressLine1Input;

  @FindBy(name = "secondLine")
  private WebElement addressLine2Input;

  @FindBy(name = "city")
  private WebElement cityInput;

  @FindBy(name = "state")
  private WebElement countyInput;

  @FindBy(name = "country")
  private WebElement countryInput;

  @FindBy(name = "postcode")
  private WebElement postcodeInput;

  @FindBy(name = "fax")
  private WebElement faxInput;

  @FindBy(name = "active")
  private WebElement isActiveCheckbox;

  @FindBy(css = "#alertNotificationEmailsValueInput")
  private WebElement alertNotificationEmailInput;

  @FindBy(name = "alertNotificationPhoneNumbers")
  private WebElement alertNotificationPhoneNumberInput;

  @FindBy(name = "btnSave")
  private WebElement createBranchBtn;

  @FindBy(xpath = "(//div[@id='branchesTable_filter']/label/input")
  private WebElement searchBranchInput;

  @FindBy(xpath = "//table[@id='branchesTable']/tbody/tr/td[1]/a")
  private WebElement searchedBranchLink;

  @FindBy(id = "btnSave")
  private WebElement updateBranchBtn;

  @FindBy(xpath = "//*[@id='branchesTable']/tbody/tr[1]/td[1]")
  private WebElement branchListFirstEntryName;

  @FindBy(xpath = "//*[@id='branchesTable_filter']/label/input")
  private WebElement branchListFilterInput;

  @FindBy(xpath = "//*[@id='branchesTable']/tbody/tr/td[1]/a")
  private WebElement branchListFirstEntryHyperLink;

  @FindBy(id = "editBranchBtn")
  private WebElement editBranchBtn;

  @FindBy(xpath = "//table[@id='branchesTable']/tbody/tr[1]/td[1]/a")
  private WebElement createdBranch;

  @FindBy(css = ".breadcrumb a")
  private WebElement branchTextLink;

  @FindBy(xpath = "//li[text()='Edit']")
  private WebElement editBranchPage;

  @FindBy(xpath = "//li[text()='Branches']")
  private WebElement branchPage;

  @FindBy(xpath = "//label[contains(text(),'Primary Telephone')]/following-sibling::div")
  private WebElement primaryTelephoneText;

  @FindBy(xpath = "//label[contains(text(),'Secondary Telephone')]/following-sibling::div")
  private WebElement secondaryTelephoneText;

  @FindBy(xpath = "//label[contains(text(),'Address Line2')]/following-sibling::div")
  private WebElement addressLine2Text;

  @FindBy(xpath = "//label[contains(text(),'Is Active?')]/following-sibling::div")
  private WebElement isActive;

  @FindBy(xpath = "//label[contains(text(),'Alert Notification Phone Number')]/following-sibling::div")
  private WebElement alertNotificationPhoneNumber;

  @FindBy(xpath = "//label[contains(text(),'Name')]/following-sibling::div")
  private WebElement branchNameText;

  @FindBy(xpath = "//label[contains(text(),'Primary Telephone')]/following-sibling::div")
  private WebElement primaryTelephoneNumberText;

  @FindBy(xpath = "//label[contains(text(),'Secondary Telephone')]/following-sibling::div")
  private WebElement secondaryTelephoneNumberText;

  @FindBy(xpath = "//label[contains(text(),'Address Line1')]/following-sibling::div")
  private WebElement addressLine1Text;

  @FindBy(xpath = "//label[contains(text(),'Address Line2')]/following-sibling::div")
  private WebElement addressLineTwoText;

  @FindBy(xpath = "//label[contains(text(),'City')]/following-sibling::div")
  private WebElement city;

  @FindBy(xpath = "//label[contains(text(),'County')]/following-sibling::div")
  private WebElement countyField;

  @FindBy(xpath = "//label[contains(text(),'Country')]/following-sibling::div")
  private WebElement countryField;

  @FindBy(xpath = "//label[contains(text(),'Postcode')]/following-sibling::div")
  private WebElement postcode;

  @FindBy(xpath = "//label[contains(text(),'Fax')]/following-sibling::div")
  private WebElement fax;

  @FindBy(xpath = "//label[contains(text(),'Is Active?')]/following-sibling::div")
  private WebElement isActiveField;

  @FindBy(xpath = "//label[contains(text(),'Alert Notification Email')]/following-sibling::div")
  private WebElement alertNotificationEmail;

  @FindBy(xpath = "//label[contains(text(),'Alert Notification Phone Number')]/following-sibling::div")
  private WebElement alertNotificationPhoneNumberField;

  public BranchPage(WebDriver driver) {
    super(driver);
  }

  /**
   * click on add new branch button
   */
  public void clickOnNewBranch() {
    _waitForJStoLoad();
    waitForElement(newBranchBtn);
    clickOn(newBranchBtn);
    _waitForJStoLoad();
  }

  /**
   * Enter new branch details
   *
   * @param branchModel: Enter branch details
   */
  public void enterNewBranchDetail(BranchModel branchModel) {

    waitForElement(nameInput);
    inputText(nameInput, branchModel.getName());

    waitForElement(tenantBranchIDInput);
    inputText(tenantBranchIDInput, branchModel.getTenantBranchID());

    waitForElement(primaryTelephoneInput);
    inputText(primaryTelephoneInput, branchModel.getPrimaryTelephone());

    waitForElement(secondaryTelephoneInput);
    inputText(secondaryTelephoneInput, branchModel.getSecondaryTelephone());

    waitForElement(addressLine1Input);
    inputText(addressLine1Input, branchModel.getAddressLine1());

    waitForElement(addressLine2Input);
    inputText(addressLine2Input, branchModel.getAddressLine2());

    waitForElement(cityInput);
    inputText(cityInput, branchModel.getCity());

    waitForElement(countyInput);
    inputText(countyInput, branchModel.getCounty());

    waitForElement(countryInput);
    inputText(countryInput, branchModel.getCountry());

    waitForElement(postcodeInput);
    inputText(postcodeInput, branchModel.getPostcode());

    waitForElement(faxInput);
    inputText(faxInput, branchModel.getFax());

    waitForElement(alertNotificationEmailInput);
    inputText(alertNotificationEmailInput, branchModel.getAlertNotificationEmail());

    waitForElement(alertNotificationPhoneNumberInput);
    inputText(alertNotificationPhoneNumberInput, branchModel.getAlertNotificationPhoneNumber());

    waitForElement(createBranchBtn);
    clickOn(createBranchBtn);
    _waitForJStoLoad();
  }

  /**
   * verify newly created branch on branch page
   *
   * @param expectedBranchName: Branch name
   */
  public void verifyCreatedBranchOnBranchPage(String expectedBranchName) {
    _waitForJStoLoad();
    String branch = branchListFirstEntryName.getText();
    Assert.assertEquals(branch, expectedBranchName);
    Assert.assertEquals(branchPage.getText(), "Branches");
  }

  /**
   * Verify branch details on branch page
   *
   * @param branchModel: Enter branch details
   */
  public void verifyBranchDetailsOnBranchPage(BranchModel branchModel) {
    String branchNameText = "//*[@id='psmobile-container']//div[text()=" + "'" + branchModel.getName() + "']";
    Assert.assertEquals(findElement(ByLocator(branchNameText)).getText(), branchModel.getName(), "Branch name " + "does not match");

    String tenantBranchIdText = "//*[@id='psmobile-container']//div[text()=" + "'" + branchModel.getTenantBranchID() + "']";
    Assert.assertEquals(findElement(ByLocator(tenantBranchIdText)).getText(), branchModel.getTenantBranchID(), "Tenant branch id does not match");

    Assert.assertEquals(primaryTelephoneText.getText(), branchModel.getPrimaryTelephone(), "Primary telephone " + "number does not match");

    Assert.assertEquals(secondaryTelephoneText.getText(), branchModel.getSecondaryTelephone(), "Secondry " + "telephone number does not match");

    String addressLine1Text = "//*[@id='psmobile-container']//div[text()=" + "'" + branchModel.getAddressLine1() + "']";
    Assert.assertEquals(findElement(ByLocator(addressLine1Text)).getText(), branchModel.getAddressLine1(), "Address 1 does not match");

    Assert.assertEquals(addressLine2Text.getText(), branchModel.getAddressLine2(), "Address 2 does not match");

    String city = "//*[@id='psmobile-container']//div[text()=" + "'" + branchModel.getCity() + "']";
    Assert.assertEquals(findElement(ByLocator(city)).getText(), branchModel.getCity(), "City does not match");

    String county = "//*[@id='psmobile-container']//div[text()=" + "'" + branchModel.getCounty() + "']";
    Assert.assertEquals(findElement(ByLocator(county)).getText(), branchModel.getCounty(), "County does not match");

    String country = "//*[@id='psmobile-container']//div[text()=" + "'" + branchModel.getCountry() + "']";
    Assert.assertEquals(findElement(ByLocator(country)).getText(), branchModel.getCountry(), "Country does not " + "match");

    String postcode = "//*[@id='psmobile-container']//div[text()=" + "'" + branchModel.getPostcode() + "']";
    Assert.assertEquals(findElement(ByLocator(postcode)).getText(), branchModel.getPostcode(), "Post code is not " + "same");

    String fax = "//*[@id='psmobile-container']//div[text()=" + "'" + branchModel.getFax() + "']";
    Assert.assertEquals(findElement(ByLocator(fax)).getText(), branchModel.getFax(), "Fax is not same");

    Assert.assertEquals(isActive.getText(), "Yes");

    String alertNotificationEmail = "//*[@id='psmobile-container']//div[text()=" + "'" + branchModel.getAlertNotificationEmail() + "']";
    Assert.assertEquals(findElement(ByLocator(alertNotificationEmail)).getText(), branchModel.getAlertNotificationEmail(), "Alert notification meesage is not same");

    Assert.assertEquals(alertNotificationPhoneNumber.getText(), branchModel.getAlertNotificationPhoneNumber(), "Alert phone number message is not same");
  }

  /**
   * Get created branch
   *
   * @return: Newly created branch name
   */
  public String getCreatedBranchOnBranchPage() {
    return branchListFirstEntryName.getText();
  }

  /**
   * search created branch on branch page
   *
   * @param branchName: Enter Branch name
   */
  public void searchCreatedBranchOnBranchPage(String branchName) {
    clickOn(branchListFilterInput);
    inputText(branchListFilterInput, branchName);
    branchListFilterInput.sendKeys(Keys.ENTER);
    _waitForJStoLoad();
  }

  /**
   * click on created branch
   */
  public void clickOnSearchedBranchOnBranchPage() {
    clickOn(branchListFirstEntryHyperLink);
    _waitForJStoLoad();
  }

  /**
   * click on edit branch button on branch page
   */
  public void clickOnEditBranchOnBranchPage() {
    clickOn(editBranchBtn);
    _waitForJStoLoad();
  }

  /**
   * click on newly created branch
   */
  public void clickOnNewlyCreatedBranch() {
    _waitForJStoLoad();
    waitForElement(createdBranch);
    clickOn(createdBranch);
    _waitForJStoLoad();
  }

  /**
   * click on branch page
   */
  public void clickOnBranchPage() {
    clickOn(branchTextLink);
  }

  /**
   * click on edit branch button
   */
  public void clickOnEditBranchButton() {
    _waitForJStoLoad();
    waitForElement(editBranchBtn);
    clickOn(editBranchBtn);
  }

  /**
   * Edit branch details
   *
   * @param branchModel: Enter branch model
   * @param randomNumber :Random number
   * @param randomString: Random string
   */
  public void editBranchDetail(BranchModel branchModel, int randomNumber, String randomString) {
    _waitForJStoLoad();
    Assert.assertEquals(editBranchPage.getText(), Action.Edit.toString(), "Page title does not match");

    waitForElement(nameInput);
    inputText(nameInput, branchModel.getName() + randomString);

    Assert.assertEquals(tenantBranchIDInput.getAttribute("readonly"), "true", "Branch id is not equal");

    waitForElement(primaryTelephoneInput);
    inputText(primaryTelephoneInput, branchModel.getPrimaryTelephone() + randomNumber);

    waitForElement(secondaryTelephoneInput);
    inputText(secondaryTelephoneInput, branchModel.getSecondaryTelephone() + randomNumber);

    waitForElement(addressLine1Input);
    inputText(addressLine1Input, branchModel.getAddressLine1() + randomString);

    waitForElement(addressLine2Input);
    inputText(addressLine2Input, branchModel.getAddressLine2() + randomString);

    waitForElement(cityInput);
    inputText(cityInput, branchModel.getCity() + randomString);

    waitForElement(countyInput);
    inputText(countyInput, branchModel.getCounty() + randomString);

    waitForElement(countryInput);
    inputText(countryInput, branchModel.getCountry() + randomString);

    waitForElement(postcodeInput);
    inputText(postcodeInput, branchModel.getPostcode() + randomNumber);

    waitForElement(faxInput);
    inputText(faxInput, branchModel.getFax() + randomNumber);

    waitForElement(alertNotificationEmailInput);
    inputText(alertNotificationEmailInput, randomString + branchModel.getAlertNotificationEmail());

    waitForElement(alertNotificationPhoneNumberInput);
    inputText(alertNotificationPhoneNumberInput, branchModel.getAlertNotificationPhoneNumber() + randomNumber);
  }

  /**
   * Click on Update button
   */
  public void clickOnUpdateButton() {
    waitForElement(updateBranchBtn);
    clickOn(updateBranchBtn);
  }

  /**
   * Verify edited branch details on branch page
   *
   * @param branchModel: Enter branch model
   * @param randomNumber: Random number
   * @param randomString: Random string
   */
  public void verifyEditedBranchDetails(BranchModel branchModel, int randomNumber, String randomString) {

    Assert.assertEquals(branchNameText.getText(), branchModel.getName() + randomString, "Branch name does not " + "match");
    Assert.assertEquals(primaryTelephoneNumberText.getText(), branchModel.getPrimaryTelephone() + randomNumber, "Primary telephone does not match");
    Assert.assertEquals(secondaryTelephoneNumberText.getText(), branchModel.getSecondaryTelephone() + randomNumber, "Secondry telephone does not match");
    Assert.assertEquals(addressLine1Text.getText(), branchModel.getAddressLine1() + randomString, "Address 1 " + "does not match");
    Assert.assertEquals(addressLineTwoText.getText(), branchModel.getAddressLine2() + randomString, "Address 2 " + "does not match");
    Assert.assertEquals(city.getText(), branchModel.getCity() + randomString, "City does not match");
    Assert.assertEquals(countyField.getText(), branchModel.getCounty() + randomString, "County does not match");
    Assert.assertEquals(countryField.getText(), branchModel.getCountry() + randomString, "Country does not match");
    Assert.assertEquals(postcode.getText(), branchModel.getPostcode() + randomNumber, "Post code does not match");
    Assert.assertEquals(fax.getText(), branchModel.getFax() + randomNumber, "Fax does not match");
    Assert.assertEquals(isActiveField.getText(), "Yes");
    Assert.assertEquals(alertNotificationEmail.getText(), randomString + branchModel.getAlertNotificationEmail(), "Email Alert notification does not match");
    Assert.assertEquals(alertNotificationPhoneNumberField.getText(), branchModel.getAlertNotificationPhoneNumber() + randomNumber, "Phone Alert notification does not match");
  }
}
