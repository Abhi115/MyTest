package com.mobizio.pages;

import com.mobizio.datamodel.ServiceModel;
import com.mobizio.selenium.framework.BasePage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ServicesPage extends BasePage {

  @FindBy(xpath = "//*[@id='servicesTable_paginate']/ul/li[3]/a")
  private WebElement nextPageLink;

  @FindBy(xpath = "(//span[contains(text(),'Test07')])[2]/..")
  private WebElement tableRow4;

  @FindBy(xpath = "//*[@id='serviceRolesBtn']")
  private WebElement roles;

  @FindBy(xpath = "//*[@id='serviceCaseListBtn']")
  private WebElement caseList;

  @FindBy(xpath = "//table[@id='servicesTable']/tbody/tr")
  private List<WebElement> serviceName;

  @FindBy(xpath = "//div[@id='servicesTable_paginate']/ul/li")
  private List<WebElement> totalPage;

  @FindBy(xpath = "//li[@class='next']/a")
  private WebElement nextPage;

  @FindBy(xpath = "//a[@id='serviceDataGroupsBtn']")
  private WebElement sections;

  @FindBy(xpath = "//a[@id='serviceFieldsBtn']")
  private WebElement field;

  @FindBy(xpath = "//a[@id='serviceUsersBtn']")
  private WebElement subscriptions;


  public ServicesPage(WebDriver driver) {
    super(driver);
  }

  /**
   * Navigate to next page through pagination.
   */
  public void navigateToNextPage() {
    waitForElement(nextPageLink);
    clickOn(nextPageLink);
    _waitForJStoLoad();
  }

  /**
   * click on service
   */
  public void clickOnService() {
    _waitForJStoLoad();
    waitForElement(tableRow4);
    clickOn(tableRow4);
    _waitForJStoLoad();
  }

  /**
   * Click on roles option
   *
   * @return: Instance of role page,once navigated to the roles page.
   */
  public RolePage clickOnroles() {
    waitForElement(roles);
    clickOn(roles);
    _waitForJStoLoad();
    return PageFactory.initElements(getDriver(), RolePage.class);
  }

  /**
   * Click on caselist to navigate to caselist page.
   *
   * @return: Instance of case list page.
   */
  public CaseListPage clickOnCaseList() {
    waitForElement(caseList);
    clickOn(caseList);
    _waitForJStoLoad();
    return PageFactory.initElements(getDriver(), CaseListPage.class);
  }

  /**
   * Click on service name
   *
   * @param serviceModel: Enter service details
   */
  public void clickOnServiceName(ServiceModel serviceModel) {
    _waitForJStoLoad();
    int pageCount = totalPage.size() - 2;
    boolean status = false;
    int currentPageNumber = 1;
    for (int j = 1; j <= pageCount; j++) {
      int count = serviceName.size();
      for (int i = 1; i <= count; i++) {
        WebElement serviceName = getDriver()
            .findElement(
                By.xpath("//table[@id='servicesTable']/tbody/tr[" + i + "]/td[1]//span"));
        String sName = serviceName.getText();
        if (sName.equals(serviceModel.getServiceName())) {
          clickOn(serviceName);
          status = true;
          break;
        }
      }

      if (status) {
        break;
      }

      if (currentPageNumber < pageCount) {
        clickOn(nextPage);
        currentPageNumber++;
      } else {
        break;
      }
    }
  }

  /**
   * click on sections
   *
   * @return: Instance of section page.
   */
  public SectionPage clickOnSections() {
    _waitForJStoLoad();
    clickOn(sections);
    return PageFactory.initElements(getDriver(), SectionPage.class);
  }

  /**
   * click on subscription
   *
   * @return: Instance of Subscription Page
   */
  public SubscriptionPage clickOnSubscriptions() {
    _waitForJStoLoad();
    clickOn(subscriptions);
    return PageFactory.initElements(getDriver(), SubscriptionPage.class);
  }
}
