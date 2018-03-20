package com.mobizio.mobile.ui.ios;

import com.mobizio.mobile.core.MobileBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class LoginUI extends MobileBase {

  @iOSFindBy(xpath = "//XCUIElementTypeTextField[@name=\"emailAddress_textfield\"]")
  private MobileElement userNameInput;
  @iOSFindBy(xpath = "//XCUIElementTypeSecureTextField[@name=\"password_textfield\"]")
  private MobileElement pinInput;
  @iOSFindBy(xpath = "//XCUIElementTypeButton[@name=\"Login\"]")
  private MobileElement loginButton;

  public LoginUI(AppiumDriver<MobileElement> driver) {
    super(driver);
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
    ;
    return this;
  }

  public void loginInApp(String userName, String pass) {
    this.inputUserName(userName);
    this.inputPinNumber(pass);
    this.clickOnLogin();
  }

}
