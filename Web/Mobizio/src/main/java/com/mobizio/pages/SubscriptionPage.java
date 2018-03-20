package com.mobizio.pages;

import com.mobizio.datamodel.UserModel;
import com.mobizio.enums.Action;
import com.mobizio.selenium.framework.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class SubscriptionPage extends BasePage {

  @FindBy(xpath = "//div[@id='table_service_user_filter']/label/input")
  private WebElement search;

  @FindBy(xpath = "//span[contains(text(),'Subscriptions')]")
  private WebElement subscription;

  @FindBy(xpath = "//table[@id='table_service_user']//a[@class='subscribe-user']")
  private WebElement subscribe;

  @FindBy(xpath = "//button[contains(text(),'OK')]")
  private WebElement okButton;

  public SubscriptionPage(WebDriver driver) {
    super(driver);
  }

  /**
   * Search user for subscription
   *
   * @param userModel: Enter user details
   */
  public void searchUser(UserModel userModel) {
    _waitForJStoLoad();
    clickOn(search);
    inputText(search, userModel.getUserName());
    search.sendKeys(Keys.ENTER);
  }

  /**
   * Verify subscription page
   */
  public void verifySubscriptionPage() {
    _waitForJStoLoad();
    String subscriptionPageText = subscription.getText();
    Assert.assertEquals(Action.Subscriptions.toString(), subscriptionPageText,
        "Page text not found");
  }

  /**
   * click on subscribe button
   */
  public void clickOnSubscribe() {
    waitForElement(subscribe);
    clickOn(subscribe);
    _waitForJStoLoad();
    clickOn(okButton);
  }
}
