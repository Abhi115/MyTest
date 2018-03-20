package com.mobizio.pages;

import com.mobizio.Utilities.Constants;
import com.mobizio.datamodel.UserModel;
import com.mobizio.selenium.framework.BasePage;
import com.mobizio.selenium.framework.Configuration;
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends BasePage {

  @FindBy(id = "txtUserName")
  private WebElement userNameField;
  @FindBy(id = "txtPassword")
  private WebElement passwordField;
  @FindBy(xpath = "//input[@value='Login']")
  private WebElement loginButton;
  @FindBy(xpath = "//div[@id='login-reset-password']/a")
  private WebElement forgotPasswordLink;
  @FindBy(xpath = "//div[@class='alert-message alert-message-danger']")
  private WebElement failedLoginMessage;
  @FindBy(xpath = "//div[text()='Login Failed! Invalid username or password!']")
  private WebElement loginFailedMessage;
  @FindBy(xpath = "//label[@id='recaptcha-anchor-label']")
  private WebElement loginCaptcha;
  @FindBy(xpath = "//div[@class='form-group']/div")
  private WebElement forgotPasswordMessage;
  @FindBy(xpath = "//input[@class='btn-ps psmobile-page-actions-btn']")
  private WebElement resetSubmitbtn;
  @FindBy(id = "inboxfield")
  private WebElement input;
  @FindBy(xpath = "//*[@class='btn btn-dark']")
  private WebElement mailinatorGobtn;
  @FindBy(xpath = "//*[contains(@onclick,'showTheMessage')]")
  private WebElement mailinatorInboxMessage;
  @FindBy(xpath = "//body")
  private WebElement mailinatorResetMessageBody;
  @FindBy(xpath = "//iframe[@id='msg_body']")
  private WebElement iFrame;
  @FindBy(xpath = "//a[contains(text(),'Login')]")
  private WebElement loginlink;

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  /**
   * Verify login page after login the application.
   *
   * @return: Instance of login page.
   */
  public LoginPage verifyLoginPage() {
    waitForElement(userNameField);
    Assert.assertTrue(isElementPresent(userNameField), "User is not landing on login page");
    Assert.assertTrue(isElementPresent(loginButton), "User is not landing on login page");
    Assert.assertTrue(isElementPresent(passwordField), "User is not landing on login page");
    return this;
  }

  /**
   * Common Login method for all the scripts,which verify dashboard page is open after login
   *
   * @param role: Enter role
   * @throws Exception: Exception
   * @return: Instance of dashboard page
   */
  public DashboardPage login(String role) throws Exception {
    String userName = Configuration.readApplicationFile(role + "_Username");
    String password = Configuration.readApplicationFile(role + "_Password");
    inputText(userNameField, userName);
    inputText(passwordField, password);
    clickOn(loginButton);
    return PageFactory.initElements(getDriver(), DashboardPage.class);
  }

  /**
   * Verify login failed after three wrong attempts
   */
  public void verifyLoginFailed(String loginFailedText) {
    waitForElement(loginFailedMessage);
    Assert.assertEquals(loginFailedMessage.getText(), loginFailedText, "Login failed message does not match");
    Assert.assertTrue(isElementPresent(loginCaptcha), "Captcha not present");
  }

  /**
   * Verify forgot password
   *
   * @param username: Enter user name
   * @return: Instance of DashboardPage
   */
  public DashboardPage verifyForgotPassword(String username) {
    //Enter Username for Reset Password.
    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
    _waitForJStoLoad();
    javascriptButtonClick(forgotPasswordLink);
    _waitForJStoLoad();
    inputText(userNameField, username);
    clickOn(resetSubmitbtn);
    //Verify Email Contents of Reset Password and get the new password
    String resetpassword = getnewPasswordfromMailinator(username);
    String emailPattern = Constants.EMAIL_VALIDATION_MESSAGE;
    Assert.assertTrue(Pattern.matches(emailPattern, resetpassword), "Password not matched");
    resetpassword = resetpassword.substring(102, 110);
    //Switch to Mobizio Tab
    driver.switchTo().window(tabs.get(0));
    switchToTab();
    getDriver().switchTo().defaultContent();
    //Click on Login Link Present and Login with the reset password received from Email.
    waitForElement(loginlink);
    clickOn(loginlink);
    inputText(userNameField, username);
    inputText(passwordField, resetpassword);
    clickOn(loginButton);
    return PageFactory.initElements(getDriver(), DashboardPage.class);

  }

  /**
   * Get password from mailinator
   *
   * @return: Password
   */
  public String getnewPasswordfromMailinator(String username) {
    //Open Mailinator in new Tab
    ((JavascriptExecutor) driver).executeScript("window.open();");
    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(1));
    getDriver().navigate().to("http://mailinator.com");
    _waitForJStoLoad();
    inputText(input, username);
    clickOn(mailinatorGobtn);

    //open on email
    return getPasswordFromEmail();
  }

  /**
   * Get password from email
   *
   * @return: password from email
   */
  public String getPasswordFromEmail() {
    waitForElementPresent(mailinatorInboxMessage, 5);
    clickOn(mailinatorInboxMessage);
    _waitForJStoLoad();
    getDriver().switchTo().defaultContent();
    isElementPresent(iFrame);
    getDriver().switchTo().frame(iFrame);
    String password = getText(mailinatorResetMessageBody);
    System.out.println(password);
    return password;

  }

  /**
   * Verify random password for login
   *
   * @param username: Enter user name
   */
  public void verifyRandomPassword(String username) {

    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
    //Verify Email Contents for new user and get the password for newly created user.
    String randompassword = getnewPasswordfromMailinator(username);
    String regex = "Dear [a-zA-Z0-9]+, Welcome to Mobizio! Your log in details are: Username: [a-zA-Z0-9]+ Password: [A-Za-z0-9_@#*()&^%$!~]{8} PIN: [0-9]{4} The URL you will need to access the Mobizio system is [a-z A-Z]+.mobizio.com To use the App: Enter this URL into the 'Server' field of the App and use the username and PIN given above. To use the Web Console: Click on the link above to access the Web Console, and use the username and password provided above to get started! Regards, Mobizio Administrator";
    Assert.assertTrue(Pattern.matches(regex, randompassword), "Password does not match");
    randompassword = randompassword.substring(97, 105);
    //Switch to Mobizio Tab and Login with the Password received for the new User.
    driver.switchTo().window(tabs.get(0));
    switchToTab();
    getDriver().switchTo().defaultContent();
    inputText(userNameField, username);
    inputText(passwordField, randompassword);
    clickOn(loginButton);
  }

  /**
   * Login application from changed password
   *
   * @param userModel: Enter user details using model
   */
  public void loginWithChangedPassword(UserModel userModel) {
    inputText(userNameField, userModel.getUserName());
    inputText(passwordField, userModel.getChangePassword());
    clickOn(loginButton);
  }
}
