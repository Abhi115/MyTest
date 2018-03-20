package com.mobizio.mobile.ui.android;

import com.mobizio.mobile.core.MobileBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

public class LoginUI extends MobileBase {

  private final By serverUrlInput = By.id("com.mobizio.mobile:id/serverUrl");
  @AndroidFindBy(id = "com.mobizio.mobile:id/userName")
  private MobileElement userNameInput;
  @AndroidFindBy(id = "com.mobizio.mobile:id/pwd")
  private MobileElement pinInput;
  @AndroidFindBy(id = "com.mobizio.mobile:id/login")
  private MobileElement loginButton;

  public LoginUI(AppiumDriver<MobileElement> driver) {
    super(driver);
  }

  public LoginUI inputServerUrl(String serverUrl) {
    waitForElementPresent(serverUrlInput);
    setText(serverUrlInput, serverUrl);
    return this;
  }

  public LoginUI inputUserName(String userName) {
    setText(userNameInput, userName);
    return this;
  }

  public LoginUI inputPinNumber(String pinNumber) {
    setText(pinInput, pinNumber);
    return this;
  }

  public LoginUI clickOnLogin() {
    loginButton.click();
    return this;
  }

  public void loginInApp(String appUrl, String userName, String pass) {
    this.inputServerUrl(appUrl);
    this.inputUserName(userName);
    this.inputPinNumber(pass);
    this.clickOnLogin();
  }

}
