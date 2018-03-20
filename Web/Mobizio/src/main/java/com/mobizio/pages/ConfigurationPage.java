package com.mobizio.pages;

import com.mobizio.selenium.framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfigurationPage extends BasePage {

  @FindBy(xpath = "//span[contains(text(),'Users')]")
  private WebElement users;

  @FindBy(xpath = "//*[@id='servicesConfigMenu']/a")
  private WebElement services;

  public ConfigurationPage(WebDriver driver) {
    super(driver);

  }

  /**
   * Click on users
   */
  public void clickOnUsers() {
    waitForElement(users);
    clickOn(users);
  }

  /**
   * Click on service
   * @return: Instance of Service page
   */
  public ServicesPage clickOnServices() {
    clickOn(services);
    _waitForJStoLoad();
    return PageFactory.initElements(getDriver(), ServicesPage.class);
  }


}
