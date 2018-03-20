package com.mobizio.pages;

import com.mobizio.datamodel.UserModel;
import com.mobizio.enums.Action;
import com.mobizio.enums.ValidationMessage.Validation;
import com.mobizio.selenium.framework.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class UserPage extends BasePage {

  @FindBy(id = "user-table_wrapper")
  private WebElement usersPage;

  @FindBy(id = "addNewUserBtn")
  private WebElement newUserBtn;

  @FindBy(id = "editUserBtn")
  private WebElement editBtn;

  @FindBy(id = "lnkEditPassword")
  private WebElement editPasswordBtn;

  @FindBy(xpath = "//span[text()='Edit Password']")
  private WebElement editPassword;

  @FindBy(xpath = "//li[contains(text(),'New User')]")
  private WebElement newUserPage;

  @FindBy(name = "userName")
  private WebElement userNameField;

  @FindBy(name = "email")
  private WebElement emailIdField;

  @FindBy(name = "password")
  private WebElement passwordField;

  @FindBy(name = "confirmPassword")
  private WebElement confirmPasswordField;

  @FindBy(name = "firstName")
  private WebElement firstNameField;

  @FindBy(name = "lastName")
  private WebElement lastNameField;

  @FindBy(name = "roleId")
  private WebElement userTypeField;

  @FindBy(name = "tenantCarerId")
  private WebElement tenantUserIdField;

  @FindBy(xpath = "//div[contains(@id,'branchNameInput')]")
  private WebElement branchField;

  @FindBy(xpath = "//div[contains(@id,'tenantNameInput')]")
  private WebElement inputTenantName;

  @FindBy(xpath = "//input[@id='s2id_autogen2_search']")
  private WebElement branchNameField;

  @FindBy(xpath = "//input[contains(@aria-activedescendant,'select2-result')]")
  private WebElement searchSelectTextBox;

  @FindBy(name = "btnSave")
  private WebElement createBtn;

  @FindBy(xpath = "//table[@id='user-table']/tbody/tr[1]//a")
  private WebElement createdUser;

  @FindBy(xpath = "//table[@id='user-table']/tbody/tr")
  private WebElement totalUsers;

  @FindBy(name = "dob")
  private WebElement dobField;

  @FindBy(name = "title")
  private WebElement titleField;

  @FindBy(name = "gender")
  private WebElement genderField;

  @FindBy(name = "firstLine")
  private WebElement addressLine1Field;

  @FindBy(name = "secondLine")
  private WebElement addressLine2Field;

  @FindBy(name = "city")
  private WebElement cityField;

  @FindBy(name = "state")
  private WebElement countyField;

  @FindBy(name = "postcode")
  private WebElement postcodeField;

  @FindBy(name = "country")
  private WebElement countryField;

  @FindBy(name = "latitude")
  private WebElement latitudeField;

  @FindBy(name = "longitude")
  private WebElement longitudeField;

  @FindBy(name = "primaryTelephone")
  private WebElement primaryTelephoneField;

  @FindBy(name = "secondaryTelephone")
  private WebElement secondaryTelephoneField;

  @FindBy(xpath = "//span[text()='Username']/parent::label/following-sibling::div/span")
  private WebElement username;

  @FindBy(xpath = "//input[@id='userPin']")
  private WebElement pinField;

  @FindBy(xpath = "//a[@id='btnCancel']")
  private WebElement cancelBtn;

  @FindBy(id = "btnSave")
  private WebElement updateButton;

  @FindBy(xpath = "//div[@id='user-table_filter']//input[@type='search']")
  private WebElement searchUser;

  @FindBy(xpath = "//div[@id='user-table_length']/label/select")
  private WebElement recordsPerPage;

  @FindBy(id = "setRandomPasswordInput")
  private WebElement setRandomPassword;

  @FindBy(xpath = "//table[@id='user-table']/tbody/tr/td[4]//span[3]")
  private WebElement setWebAccessTrue;

  @FindBy(xpath = "//table[@id='user-table']/tbody/tr/td[4]//span")
  private WebElement setWebAccessfalse;

  @FindBy(id = "sharedValueInput")
  private WebElement isSharedField;

  @FindBy(xpath = "//ul[@role='alert']/li")
  private WebElement invalidEmailValidationMessage;

  @FindBy(xpath = "//span[text()='Username']/../../div[@class='controls']")
  private WebElement editedUsername;

  @FindBy(xpath = "//span[contains(text(),'First')]/../../div")
  private WebElement editedFirstname;

  @FindBy(xpath = "//span[text()='Title']/../../div[@class='controls']")
  private WebElement editedTitle;

  @FindBy(xpath = "//span[contains(text(),'Last')]/../../div")
  private WebElement editedLastname;

  @FindBy(xpath = "//span[contains(text(),'Line 1')]/../../div")
  private WebElement editedAddressline1;

  @FindBy(xpath = "//span[contains(text(),'Line 2')]/../../div")
  private WebElement editedAddressline2;

  @FindBy(xpath = "//span[text()='City']/../../div")
  private WebElement editedCityName;

  @FindBy(xpath = "//span[text()='County']/../../div")
  private WebElement editedCountyName;

  @FindBy(xpath = "//span[text()='Postcode']/../../div")
  private WebElement editedPostCode;

  @FindBy(xpath = "//span[text()='Country']/../../div")
  private WebElement editedCountryName;

  @FindBy(xpath = "//span[text()='Latitude']/../../div")
  private WebElement editedLatitude;

  @FindBy(xpath = "//span[text()='Longitude']/../../div")
  private WebElement editedLongitude;

  @FindBy(xpath = "//span[contains(text(),'Primary')]/../../div")
  private WebElement editedPrimaryTelephone;

  @FindBy(xpath = "//span[contains(text(),'Secondary')]/../../div")
  private WebElement editedSecondaryTelephone;

  @FindBy(xpath = "//span[contains(text(),'type')]/../../div")
  private WebElement editedUserType;

  @FindBy(xpath = "//span[contains(text(),'Tenant')]/../../div")
  private WebElement editedTenantuserId;

  @FindBy(id = "tenantCarerIdValueInput")
  private WebElement tenantUserId;

  @FindBy(xpath = "//span[text()='Gender']/../../div")
  private WebElement editedGender;

  @FindBy(xpath = "//span[text()='Email']/../../div[@class='controls']")
  private WebElement editedEmail;

  @FindBy(className = "icheckbox_square-blue checked hover")
  private WebElement isSharedCheckBox;

  @FindBy(xpath = "//*[@id='user-table_filter']//input")
  private WebElement searchBox;

  @FindBy(className = "dataTables_empty")
  private WebElement userRecordsValidationMessage;

  @FindBy(xpath = "//*[@class='dl-horizontal']/dd")
  private WebElement userUniquenameValidation;

  public UserPage(WebDriver driver) {
    super(driver);
  }

  /**
   * Verify Users Page
   */
  public void verifyUsersPage() {
    _waitForJStoLoad();
    waitForElement(usersPage);
    Assert.assertTrue(isElementPresent(usersPage), "Element not found");
  }

  /**
   * Click on new user button
   */
  public void clickOnNewUserButton() {
    _waitForJStoLoad();
    waitForElement(newUserBtn);
    clickOn(newUserBtn);
  }

  /**
   * Verify New User Page
   */
  public void verifyNewUserPage() {
    _waitForJStoLoad();
    waitForElement(newUserPage);
    Assert.assertEquals(newUserPage.getText(), Action.NewUserPage.toString(),
        "NewUser page text does not match");
  }

  /**
   * click on create button
   */
  public void clickOnCreateButton() {
    try {
      clickOn(createBtn);
    } catch (NoSuchElementException e) {
      System.out.println("Cannot click on create button");
    }
  }

  /**
   * Verify Created User On User Page
   *
   * @param userName: Enter user name
   */
  public void verifyCreatedUserOnUserPage(String userName) {
    _waitForJStoLoad();
    Assert.assertEquals(createdUser.getText(), userName, "User name does not match");
  }

  /**
   * Search for user
   *
   * @param searchContent: Enter content to be searched
   */
  public void searchFor(String searchContent) {
    _waitForJStoLoad();
    clickOn(searchUser);
    inputText(searchUser, searchContent);
    searchUser.sendKeys(Keys.ENTER);
    waitForElement(createdUser);
    _waitForJStoLoad();

  }

  /**
   * click on created user
   */
  public void clickOnCreatedUser() {
    waitAndClick(createdUser);
  }

  /**
   * Click on edit button.
   */
  public void clickOnEditButton() {
    javascriptButtonClick(editBtn);
  }

  /**
   * Edit user details
   *
   * @param userModel: Enter user model
   */
  public void editUser(UserModel userModel, long timeStamp, int randomInt) {
    waitAndClick(editBtn);
    Assert.assertEquals(username.getTagName(), "span", "Web element is not in span hence it is editable");
    inputText(emailIdField, timeStamp + userModel.getEmail());
    Assert.assertTrue(isElementPresent(pinField), "pin field not present");
    inputText(pinField, userModel.getPin());
    inputText(firstNameField, timeStamp + userModel.getFirstName());
    inputText(lastNameField, timeStamp + userModel.getLastName());
    selectDropDownByText(userTypeField, userModel.getUserType());
    Assert.assertEquals(tenantUserIdField.getAttribute("disabled"), "true");
    if (userModel.getBranch() != null) {
      clickOn(branchField);
      inputText(branchNameField, userModel.getBranch());
      waitForElement(searchSelectTextBox);
      branchNameField.sendKeys(Keys.ENTER);
    }
    if (userModel.getDob() != null) {
      inputText(dobField, userModel.getDob());
    }
    if (userModel.getTitle() != null) {
      selectDropDownByValue(titleField, userModel.getTitle());
    }
    if (userModel.getGender() != null) {
      selectDropDownByValue(genderField, userModel.getGender());
    }
    if (userModel.getAddressLine1() != null) {
      inputText(addressLine1Field, timeStamp + userModel.getAddressLine1());
    }
    if (userModel.getAddressLine2() != null) {
      inputText(addressLine2Field, timeStamp + userModel.getAddressLine2());
    }
    if (userModel.getCity() != null) {
      inputText(cityField, timeStamp + userModel.getCity());
    }
    if (userModel.getCounty() != null) {
      inputText(countyField, timeStamp + userModel.getCounty());
    }
    if (userModel.getPostCode() != null) {
      inputText(postcodeField, timeStamp + userModel.getPostCode());
    }
    if (userModel.getCountry() != null) {
      selectDropDownByValue(countryField, userModel.getCountry());
    }
    if (userModel.getLatitude() != null) {
      inputText(latitudeField, randomInt + userModel.getLatitude());
    }
    if (userModel.getLongitude() != null) {
      inputText(longitudeField, randomInt + userModel.getLongitude());
    }
    if (userModel.getPrimaryTelephone() != null) {
      inputText(primaryTelephoneField, timeStamp + userModel.getPrimaryTelephone());
    }
    if (userModel.getSecondaryTelephone() != null) {
      inputText(secondaryTelephoneField, timeStamp + userModel.getSecondaryTelephone());
    }
  }

  /**
   * Click on update button
   */
  public void clickOnUpdateButton() {
    Assert.assertTrue(isElementPresent(updateButton), "Update button is not visible");
    waitForElement(updateButton);
    clickOn(updateButton);
  }

  public void verifyEditedUserDetails(UserModel userModel, long timeStamp, int randomInt) {
    _waitForJStoLoad();
    Assert.assertEquals(editedUsername.getText(), userModel.getUserName(), "User name does not match");
    Assert.assertEquals(editedFirstname.getText(), timeStamp + userModel.getFirstName(), "User first name does not match");
    Assert.assertEquals(editedLastname.getText(), timeStamp + userModel.getLastName(), "user last name does not match");
    Assert.assertEquals(editedAddressline1.getText(), timeStamp + userModel.getAddressLine1(), "Address line 1 does not match");
    Assert.assertEquals(editedAddressline2.getText(), timeStamp + userModel.getAddressLine2(), "Address line 2 does not match");
    Assert.assertEquals(editedCityName.getText(), timeStamp + userModel.getCity(), "City name does not match");
    Assert.assertEquals(editedCountyName.getText(), timeStamp + userModel.getCounty());
    Assert.assertEquals(editedCountryName.getText(), userModel.getCountry(), "Country does not match");
    Assert.assertEquals(editedLongitude.getText(), randomInt + userModel.getLongitude() + ".0", "Longitude does not match");
    Assert.assertEquals(editedLatitude.getText(), randomInt + userModel.getLatitude() + ".0", "Latitude does not match");
    Assert.assertEquals(editedPrimaryTelephone.getText(), timeStamp + userModel.getPrimaryTelephone(), "Primary telephone does not match");
    Assert.assertEquals(editedSecondaryTelephone.getText(), timeStamp + userModel.getSecondaryTelephone(), "Secondry telephone does not match");
    Assert.assertEquals(editedUserType.getText(), userModel.getUserType(), "user type does not match");
    Assert.assertEquals(editedTenantuserId.getText(), userModel.getTenantUserId(), "Tenant user id does not match");
    Assert.assertEquals(editedCountryName.getText(), userModel.getCountry(), "Country name does not match");
    Assert.assertEquals(editedCountyName.getText(), timeStamp + userModel.getCounty(), "County name does not match");
    Assert.assertEquals(editedGender.getText(), userModel.getGender(), "Gender does not match");
    Assert.assertEquals(editedUserType.getText(), userModel.getUserType(), "User type does not match");
    Assert.assertEquals(editedTitle.getText(), userModel.getTitle(), "Title does not match");
    Assert.assertEquals(editedEmail.getText(), timeStamp + userModel.getEmail(), "Email does not match");

  }

  /**
   * Verify Edit Password Page
   */
  public void verifyEditPasswordPage() {
    _waitForJStoLoad();
    waitForElement(editPassword);
    Assert.assertEquals(editPassword.getText(), Action.EditPassword.toString(),
        "Password does not match");
  }

  /**
   * Edit user password
   *
   * @param usermodel: User model
   */
  public void editUserPassword(UserModel usermodel) {
    clickOn(createdUser);
    clickOn(editPasswordBtn);
    this.verifyEditPasswordPage();
    inputText(passwordField, usermodel.getPassword());
    inputText(confirmPasswordField, usermodel.getConfirmPassword());
    clickOn(createBtn);
  }

  /**
   * Enter new user details
   *
   * @param userModel: Enter user details
   * @param loginUser: Enter login user name
   * @param userType: Enter user type
   */
  public void enterNewUserDetails(UserModel userModel, String loginUser, String userType) {

    inputText(userNameField, userModel.getUserName());
    inputText(emailIdField, userModel.getEmail());
    if (!userType.equals("Reset")) {
      inputText(passwordField, userModel.getPassword());
      inputText(confirmPasswordField, userModel.getConfirmPassword());
      selectDropDownByText(userTypeField, userType);
    } else {
      clickOn(setRandomPassword);
      selectDropDownByText(userTypeField, "Carer");
    }
    inputText(firstNameField, userModel.getFirstName());
    inputText(lastNameField, userModel.getLastName());
    if (!userType.equals("System Administrator")) {
      inputText(tenantUserIdField, userModel.getTenantUserId());
    }
    if (loginUser.equals("Tenant") && (userType.equals("Branch Administrator") || userType
        .equals("Carer") || userType.equals("Reset"))) {
      clickOn(branchField);
      inputText(branchNameField, userModel.getBranch());
      waitForElement(searchSelectTextBox);
      waitForElement(newUserPage);
      branchNameField.sendKeys(Keys.ENTER);
    }
    if (loginUser.equals("System") && userType.equals("Tenant Administrator")) {
      clickOn(inputTenantName);
      inputText(branchNameField, userModel.getTenant());
      waitForElement(searchSelectTextBox);
      waitForElement(newUserPage);
      branchNameField.sendKeys(Keys.ENTER);
    }

    if (userModel.getDob() != null) {
      inputText(dobField, userModel.getDob());
    }

    if (userModel.getTitle() != null) {
      selectDropDownByValue(titleField, userModel.getTitle());
    }

    if (userModel.getGender() != null) {
      selectDropDownByValue(genderField, userModel.getGender());
    }

    if (userModel.getAddressLine1() != null) {
      inputText(addressLine1Field, userModel.getAddressLine1());
    }

    if (userModel.getAddressLine2() != null) {
      inputText(addressLine2Field, userModel.getAddressLine2());
    }

    if (userModel.getCity() != null) {
      inputText(cityField, userModel.getCity());
    }

    if (userModel.getCounty() != null) {
      inputText(countyField, userModel.getCounty());
    }

    if (userModel.getPostCode() != null) {
      inputText(postcodeField, userModel.getPostCode());
    }

    if (userModel.getCountry() != null) {
      selectDropDownByValue(countryField, userModel.getCountry());
    }

    if (userModel.getLatitude() != null) {
      inputText(latitudeField, userModel.getLatitude());
    }

    if (userModel.getLongitude() != null) {
      inputText(longitudeField, userModel.getLongitude());
    }

    if (userModel.getPrimaryTelephone() != null) {
      inputText(primaryTelephoneField, userModel.getPrimaryTelephone());
    }

    if (userModel.getSecondaryTelephone() != null) {
      inputText(secondaryTelephoneField, userModel.getSecondaryTelephone());
    }
  }

  /**
   * click on web access to set web access true
   */
  public void setWebAccessTrue() {

    if (isElementPresent(setWebAccessfalse)) {
      clickOn(setWebAccessTrue);
    }
  }

  /**
   * Mark carer as non-shared
   *
   * @param carer: Enter carer name
   */
  public void markCarerAsNonShared(String carer) {
    _waitForJStoLoad();
    this.searchFor(carer);
    waitForElement(createdUser);
    clickOn(createdUser);
    waitForElement(editBtn);
    clickOn(editBtn);
    _waitForJStoLoad();
    clickOn(isSharedField);
    waitForElement(createBtn);
    clickOn(createBtn);
  }

  /**
   * Verify validation message
   *
   * @param field: Enter field
   */
  public void verifyValidationMessage(String field) {
    WebElement element = null;
    switch (field) {
      case "email":
        element = emailIdField;
        break;

      case "username":
        element = userNameField;
        break;

      case "firstname":
        element = firstNameField;
        break;

      case "lastname":
        element = lastNameField;
        break;

      case "userid":
        element = tenantUserIdField;
        break;
    }
    Assert.assertNotNull(element, "Element null");
    JavascriptExecutor js = (JavascriptExecutor) driver;
    Assert.assertEquals(js.executeScript("return arguments[0].validationMessage;", element), Validation.PleaseFillThisField.toString(), "Validation not matched");
  }

  /**
   * Verify email input should have a character limit of 255 when creating a user
   */
  public void verifyEmailFieldLimit() {
    Assert.assertEquals(emailIdField.getAttribute("value").length(), 255, "Length does not match");
  }

  /**
   * Verify invalid email validation message.
   */
  public void verifyInvalidEmailValidation() {
    Assert.assertEquals(invalidEmailValidationMessage.getText(), Validation.InvalidEmail.toString());
  }

  /**
   * #sharedValueInput Uncheck 'Is Shared' option
   */

  public void uncheckIsSharedCheckBox() {
    scrollPageThroughWebElement(isSharedField);
    Actions actions = new Actions(driver);
    actions.click(isSharedField).perform();
    //javascriptButtonClick(isSharedField);
  }

  /**
   * @param userName User name
   */
  public void filterUser(String userName) {
    inputText(searchBox, userName);
    searchBox.sendKeys(Keys.ENTER);
  }

  /**
   * Verify user does not exi
   */
  public void verifyUserDoesNotExist() {
    _waitForJStoLoad();
    waitForElement(userRecordsValidationMessage);
    Assert.assertEquals(userRecordsValidationMessage.getText(), Validation.NoDataAvailable.toString(), "User found in table");
  }

  /**
   * Verify validation message displayed for unique user
   */
  public void verifyUserNameUniqueValidation(){
    Assert.assertEquals(userUniquenameValidation.getText(),Validation.UniqueUserNameValidation.toString(),"User name validation does not match");
  }
}

