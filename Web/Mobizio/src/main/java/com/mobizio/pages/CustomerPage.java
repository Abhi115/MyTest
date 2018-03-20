package com.mobizio.pages;


import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.CustomerModel;
import com.mobizio.datamodel.UserModel;
import com.mobizio.enums.Action;
import com.mobizio.selenium.framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CustomerPage extends BasePage {

  @FindBy(css = ".breadcrumb>li")
  private WebElement verifyCustomerPage;

  @FindBy(id = "addNewCustomerBtn")
  private WebElement addNewCustomerButton;

  @FindBy(xpath = "//li[contains(text(),'New Customer')]")
  private WebElement verifyNewCustomerPage;

  @FindBy(id = "usernameValueInput")
  private WebElement userNameField;

  @FindBy(name = "email")
  private WebElement emailInputField;

  @FindBy(name = "password")
  private WebElement passwordInputField;

  @FindBy(name = "confirmPassword")
  private WebElement confirmPasswordInputField;

  @FindBy(name = "firstName")
  private WebElement firstNameInputFiled;

  @FindBy(name = "lastName")
  private WebElement lastNameInputField;

  @FindBy(name = "tenantCustomerId")
  private WebElement customerIdInputField;

  @FindBy(xpath = "//div[contains(@id,'branchNameInput')]")
  private WebElement searchSelectTextBox;

  @FindBy(xpath = "//input[@id='s2id_autogen2_search']")
  private WebElement branchNameInputField;

  @FindBy(id = "btnSave")
  private WebElement createButton;

  @FindBy(xpath = "//span[@class='select2-match']")
  private WebElement selectValueFromBranchDropdown;

  @FindBy(xpath = "//table[@id='user-table']/tbody/tr[1]//a")
  private WebElement newCustomer;

  @FindBy(name = "title")
  private WebElement titleInputField;

  @FindBy(name = "gender")
  private WebElement genderDropDown;

  @FindBy(name = "firstLine")
  private WebElement addressLine1InputField;

  @FindBy(name = "secondLine")
  private WebElement addressLine2InputField;

  @FindBy(name = "city")
  private WebElement cityInputField;

  @FindBy(name = "state")
  private WebElement countyInputField;

  @FindBy(name = "postcode")
  private WebElement postcodeInputField;

  @FindBy(name = "country")
  private WebElement countryInputField;

  @FindBy(name = "latitude")
  private WebElement latitudeInputField;

  @FindBy(name = "longitude")
  private WebElement longitudeInputField;

  @FindBy(name = "primaryTelephone")
  private WebElement primaryTelephoneInputField;

  @FindBy(name = "secondaryTelephone")
  private WebElement secondaryTelephoneInputField;

  @FindBy(xpath = "//input[@id='nicknameValueInput']")
  private WebElement enterlikesToBeCalled;

  @FindBy(xpath = "//input[@id='nfcTagIdValueInput']")
  private WebElement enternfcTagContent;

  @FindBy(name = "dob")
  private WebElement dobInputField;

  @FindBy(xpath = "//input[contains(@aria-activedescendant,'select2-result')]")
  private WebElement waitForSearchSelectTextBox;

  @FindBy(id ="editUserBtn")
  private WebElement editCustomerButon;

  @FindBy(xpath = "//span[text()='Username']/../../div/span")
  private WebElement userName;

  @FindBy(name = "pin")
  private WebElement pintInputField;

  @FindBy(name = "roleId")
  private WebElement userTypeField;

  @FindBy(name = "tenantCustomerId")
  private WebElement tenantCustomerId;

  @FindBy(xpath = "//div[@id='s2id_branchNameInput']/a")
  private WebElement branchFieldDropDwon;

  @FindBy(name = "btnSave")
  private WebElement updateButton;

  @FindBy(xpath = "//span[text()='Username']/../../div")
  private WebElement editedUsername;

  @FindBy(xpath = "//span[text()='Email']/../../div")
  private WebElement editedEmail;

  @FindBy(xpath = "//span[text()='PIN']/../../div")
  private WebElement editedPin;

  @FindBy(xpath = "//span[text()='Title']/../../div")
  private WebElement editedTitle;

  @FindBy(xpath = "//span[contains(text(),'First')]/../../div")
  private WebElement editedFirstName;

  @FindBy(xpath = "//span[contains(text(),'Last')]/../../div")
  private WebElement editedlastname;

  @FindBy(xpath = "//span[contains(text(),'Gender')]/../../div")
  private WebElement editedGender;

  @FindBy(xpath = "//span[contains(text(),'Line 1')]/../../div")
  private WebElement editedAddressLine1;

  @FindBy(xpath = "//span[contains(text(),'Line 2')]/../../div")
  private WebElement editedAddressLine2;

  @FindBy(xpath = "//span[(text()='City')]/../../div")
  private WebElement editedCity;

  @FindBy(xpath = "//span[(text()='County')]/../../div")
  private WebElement editedCounty;

  @FindBy(xpath = "//span[(text()='Postcode')]/../../div")
  private WebElement editedPostCode;

  @FindBy(xpath = "//span[(text()='Country')]/../../div")
  private WebElement editedCountry;

  @FindBy(xpath = "//span[(text()='Latitude')]/../../div")
  private WebElement editedLatitude;

  @FindBy(xpath = "//span[(text()='Longitude')]/../../div")
  private WebElement editedLongitude;

  @FindBy(xpath = "//span[contains(text(),'Primary')]/../../div")
  private WebElement editedPrimaryTelephone;

  @FindBy(xpath = "//span[contains(text(),'Secondary')]/../../div")
  private WebElement editedSecondaryTelephone;

  @FindBy(xpath = "//span[contains(text(),'Branch')]/../../div")
  private WebElement editedBranch;



  public CustomerPage(WebDriver driver) {
    super(driver);
  }

  /**
   * Verify Customer Page
   */
  public void verifyCustomerPage() {
    _waitForJStoLoad();
    waitForElement(verifyCustomerPage);
    String customerPageText = verifyCustomerPage.getText();
    Assert.assertEquals(Action.Customers.toString(), customerPageText, "Customer page title is not same");
  }

  /**
   * Verify New Customer Page
   */
  public void verifyNewCustomerPage() {
    _waitForJStoLoad();
    waitForElement(verifyNewCustomerPage);
    String customerPageText = verifyNewCustomerPage.getText();
    Assert.assertEquals(Action.NewCustomer.toString(), customerPageText,
        "New Customer page title is not same");
  }

  /**
   * Click on add new customer button
   */
  public void clickOnAddNewCustomer() {
    waitForElement(addNewCustomerButton);
    clickOn(addNewCustomerButton);
  }

  /**
   * Click on create button on create new customer page
   */
  public void clickOnCreateButton() {
    waitForElement(createButton);
    clickOn(createButton);
  }

  /**
   * Enter customer details on create new customer page
   *
   * @param customerModel: Enter customer details
   * @param loginUser: User name
   */
  public void enterNewCustomerDetails(CustomerModel customerModel, String loginUser) {

    inputText(userNameField, customerModel.getUserName());
    inputText(emailInputField, customerModel.getEmail());
    inputText(passwordInputField, customerModel.getPassword());
    inputText(confirmPasswordInputField, customerModel.getConfirmPassword());
    inputText(firstNameInputFiled, customerModel.getFirstName());
    inputText(lastNameInputField, customerModel.getLastName());
    if (customerModel.getDob() != null) {
      clickOn(dobInputField);
      inputText(dobInputField, customerModel.getDob());
    }

    if (customerModel.getTitle() != null) {
      selectDropDownByValue(titleInputField, customerModel.getTitle());
    }

    if (customerModel.getGender() != null) {
      selectDropDownByValue(genderDropDown, customerModel.getGender());
    }

    if (customerModel.getAddressLine1() != null) {
      clickOn(addressLine1InputField);
      inputText(addressLine1InputField, customerModel.getAddressLine1());
    }

    if (customerModel.getAddressLine2() != null) {
      clickOn(addressLine2InputField);
      inputText(addressLine2InputField, customerModel.getAddressLine2());
    }

    if (customerModel.getCity() != null) {
      clickOn(cityInputField);
      inputText(cityInputField, customerModel.getCity());
    }

    if (customerModel.getCounty() != null) {
      clickOn(countyInputField);
      inputText(countyInputField, customerModel.getCounty());
    }

    if (customerModel.getPostCode() != null) {
      clickOn(postcodeInputField);
      inputText(postcodeInputField, customerModel.getPostCode());
    }

    if (customerModel.getCountry() != null) {
      selectDropDownByValue(countryInputField, customerModel.getCountry());
    }

    if (customerModel.getLatitude() != null) {
      clickOn(latitudeInputField);
      inputText(latitudeInputField, customerModel.getLatitude());
    }

    if (customerModel.getLongitude() != null) {
      clickOn(longitudeInputField);
      inputText(longitudeInputField, customerModel.getLongitude());
    }

    if (customerModel.getPrimaryTelephone() != null) {
      clickOn(primaryTelephoneInputField);
      inputText(primaryTelephoneInputField, customerModel.getPrimaryTelephone());
    }

    if (customerModel.getSecondaryTelephone() != null) {
      clickOn(secondaryTelephoneInputField);
      inputText(secondaryTelephoneInputField, customerModel.getSecondaryTelephone());
    }

    if (customerModel.getLikesToBeCalled() != null) {
      clickOn(enterlikesToBeCalled);
      inputText(enterlikesToBeCalled, customerModel.getLikesToBeCalled());
    }

    if (customerModel.getNfcTagContent() != null) {
      clickOn(enternfcTagContent);
      inputText(enternfcTagContent, customerModel.getNfcTagContent());
    }
    if (loginUser.equals("Tenant")) {
      waitForElement(searchSelectTextBox);
      clickOn(searchSelectTextBox);
      inputText(branchNameInputField, customerModel.getBranch());
      waitForElement(waitForSearchSelectTextBox);
      branchNameInputField.sendKeys(Keys.ENTER);
    }
    clickOn(customerIdInputField);
    inputText(customerIdInputField, customerModel.getCustomerId());
  }

  /**
   * Verify newly created customer on customer page
   *
   * @param expectedCustomerName: Enter customer name
   */
  public void verifyCreatedCustomerOnCustomerPage(String expectedCustomerName) {
    String customerName = newCustomer.getText();
    Assert.assertEquals(customerName, expectedCustomerName, "Customer name is not same");
  }

  /**
   * Click on created customer
   */
  public void clickOnNewlyCreatedCustomer(){
    waitAndClick(newCustomer);
  }

  public void editCustomerDetails(CustomerModel customerModel,long timeStamp,int randomInt,String loggedInUserType) throws Exception{
    Thread.sleep(5000);
    clickOn(editCustomerButon);
    Assert.assertEquals(userName.getTagName(), "span", "Web element is not in span hence it is editable");
    inputText(emailInputField, timeStamp + customerModel.getEmail());
    Assert.assertTrue(isElementPresent(pintInputField), "pin field not present");
    //inputText(pintInputField, customerModel.getPin());
    inputText(firstNameInputFiled, timeStamp + customerModel.getFirstName());
    inputText(lastNameInputField, timeStamp + customerModel.getLastName());
    Assert.assertEquals(tenantCustomerId.getAttribute("disabled"), "true");
    if (customerModel.getBranch() != null && loggedInUserType!= Constants.BRANCH_ADMIN_ROLE) {
      clickOn(branchFieldDropDwon);
      inputText(branchNameInputField, customerModel.getBranch());
      branchNameInputField.sendKeys(Keys.ENTER);
    }
    if (customerModel.getDob() != null) {
      inputText(dobInputField, customerModel.getDob());
    }
    if (customerModel.getTitle() != null) {
      selectDropDownByValue(titleInputField, customerModel.getTitle());
    }
    if (customerModel.getGender() != null) {
      selectDropDownByValue(genderDropDown, customerModel.getGender());
    }
    if (customerModel.getAddressLine1() != null) {
      inputText(addressLine1InputField, timeStamp + customerModel.getAddressLine1());
    }
    if (customerModel.getAddressLine2() != null) {
      inputText(addressLine2InputField, timeStamp + customerModel.getAddressLine2());
    }
    if (customerModel.getCity() != null) {
      inputText(cityInputField, timeStamp + customerModel.getCity());
    }
    if (customerModel.getCounty() != null) {
      inputText(countyInputField, timeStamp + customerModel.getCounty());
    }
    if (customerModel.getPostCode() != null) {
      inputText(postcodeInputField, timeStamp + customerModel.getPostCode());
    }
    if (customerModel.getCountry() != null) {
      selectDropDownByValue(countryInputField, customerModel.getCountry());
    }
    if (customerModel.getLatitude() != null) {
      inputText(latitudeInputField, randomInt + customerModel.getLatitude());
    }
    if (customerModel.getLongitude() != null) {
      inputText(longitudeInputField, randomInt + customerModel.getLongitude());
    }
    if (customerModel.getPrimaryTelephone() != null) {
      inputText(primaryTelephoneInputField, timeStamp + customerModel.getPrimaryTelephone());
    }
    if (customerModel.getSecondaryTelephone() != null) {
      inputText(secondaryTelephoneInputField, timeStamp + customerModel.getSecondaryTelephone());
    }
  }

  /**
   * Click on update button.
   */

  public void clickOnUpdate(){
    waitForElementPresent(updateButton,5);
    waitAndClick(updateButton);
  }

  /**
   * Verify edited login details
   * @param customerModel customer model
   * @param timeStamp Pass current Time stamp as random string
   * @param randomInt Random integer value
   */
  public void verifyEditedUserDetails(CustomerModel customerModel, long timeStamp, int randomInt){
    _waitForJStoLoad();
    Assert.assertEquals(editedUsername.getText(),customerModel.getUserName(),"User name does not match");
    Assert.assertEquals(editedEmail.getText(),timeStamp+customerModel.getEmail(),"Email does not match");
    Assert.assertEquals(editedFirstName.getText(),timeStamp+customerModel.getFirstName(),"First name does not match");
    Assert.assertEquals(editedlastname.getText(),timeStamp+customerModel.getLastName(),"last name does not match");
    Assert.assertEquals(editedGender.getText(),customerModel.getGender(),"Gender does not match");
    Assert.assertEquals(editedAddressLine1.getText(),timeStamp+customerModel.getAddressLine1(),"Address line 1 does not match");
    Assert.assertEquals(editedAddressLine2.getText(),timeStamp+customerModel.getAddressLine2(),"Address line 2 does not match");
    Assert.assertEquals(editedCity.getText(),timeStamp+customerModel.getCity(),"City does not match");
    Assert.assertEquals(editedCounty.getText(),timeStamp+customerModel.getCounty(),"Edited county does not match");
    Assert.assertEquals(editedPostCode.getText(),timeStamp+customerModel.getPostCode(),"Poest code does not match");
    Assert.assertEquals(editedCountry.getText(),customerModel.getCountry(),"Country does not match");
    Assert.assertEquals(editedLatitude.getText(),randomInt+customerModel.getLatitude()+".0","latitude does not match");
    Assert.assertEquals(editedLongitude.getText(),randomInt+customerModel.getLongitude()+".0","Longitude does not match");
    Assert.assertEquals(editedPrimaryTelephone.getText(),timeStamp+customerModel.getPrimaryTelephone(),"Primary telephone does not match");
    Assert.assertEquals(editedSecondaryTelephone.getText(),timeStamp+customerModel.getSecondaryTelephone(),"Secondary telephone does not match");
  }
}
