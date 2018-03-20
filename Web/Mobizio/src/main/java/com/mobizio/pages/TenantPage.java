package com.mobizio.pages;

import static org.testng.Assert.assertEquals;

import com.mobizio.datamodel.ServiceModel;
import com.mobizio.datamodel.TenantModel;
import com.mobizio.selenium.framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


public class TenantPage extends BasePage {

  @FindBy(xpath = "//button[@class='btn btn-ps psmobile-page-actions-btn pull-right visits-export']")
  private WebElement plusBtn;

  @FindBy(id = "nameValueInput")
  private WebElement inputTenantName;

  @FindBy(id = "websiteValueInput")
  private WebElement inputWebsite;

  @FindBy(id = "primaryTelephoneValueInput")
  private WebElement inputPrimaryPhoneNumber;

  @FindBy(id = "secondaryTelephoneValueInput")
  private WebElement inputSecondaryPhoneNumber;

  @FindBy(id = "firstLineValueInput")
  private WebElement inputAddress1;

  @FindBy(id = "secondLineValueInput")
  private WebElement inputaddress2;

  @FindBy(id = "cityValueInput")
  private WebElement inputcity;

  @FindBy(id = "stateValueInput")
  private WebElement inputcounty;

  @FindBy(id = "countryValueInput")
  private WebElement inputCountry;

  @FindBy(id = "postcodeValueInput")
  private WebElement inputPostCode;

  @FindBy(id = "faxValueInput")
  private WebElement inputFax;

  @FindBy(id = "tenant-logo-file-selector-link")
  private WebElement uploadPicture;

  @FindBy(id = "btnSave")
  private WebElement saveBtn;

  @FindBy(xpath = "//table[@id='tenantsTable']/tbody/tr/td/a")
  private WebElement tenantName;

  @FindBy(xpath = "//table[@id='tenantsTable']/tbody/tr/td[2]")
  private WebElement primaryTelephone;

  @FindBy(xpath = "//table[@id='tenantsTable']/tbody/tr/td[3]")
  private WebElement fax;

  @FindBy(xpath = "//table[@id='tenantsTable']/tbody/tr/td[4]")
  private WebElement website;

  @FindBy(xpath = "//table[@id='tenantsTable']/tbody/tr/td/a")
  private WebElement clickOnCreatedTenant;

  @FindBy(xpath = "//button[@class='btn btn-ps psmobile-page-actions-btn pull-right visits-export']")
  private WebElement addServiceBtn;

  @FindBy(id = "nameValueInput")
  private WebElement inputServiceName;

  @FindBy(xpath = "//table[@id='servicesTable']/tbody/tr/td/a")
  private WebElement serviceName;


  public TenantPage(WebDriver driver) {
    super(driver);

  }

  /**
   * Click on plush button
   */
  public void clickOnPlusButton() {
    waitForElement(plusBtn);
    clickOn(plusBtn);

  }

  /**
   * Enter new Tenant details
   *
   * @param tenantModel: Pass reference of TenantModel class as an argument.
   * @throws InterruptedException: Throws Exception
   */
  public void addNewTenantDetails(TenantModel tenantModel) {

    _waitForJStoLoad();
    waitForElementPresent(inputTenantName, 5);
    inputText(inputTenantName, tenantModel.getTenantName());
    inputText(inputPrimaryPhoneNumber, tenantModel.getPrimaryTelephone());
    inputText(inputWebsite, tenantModel.getWebsite());
    inputText(inputAddress1, tenantModel.getAddress1());
    inputText(inputaddress2, tenantModel.getAddress2());
    inputText(inputcity, tenantModel.getCity());
    inputText(inputcounty, tenantModel.getCounty());
    inputText(inputCountry, tenantModel.getCounty());
    inputText(inputPostCode, tenantModel.getPostCode());
    inputText(inputFax, tenantModel.getFax());
    clickOn(saveBtn);
  }

  /**
   * Verify newly created tenants on tenant page.
   *
   * @param tenantModel: Enter tenant model
   */
  public void verifyCreatedTenantOnTenantsPage(TenantModel tenantModel) {
    _waitForJStoLoad();
    Assert.assertEquals(tenantModel.getTenantName(), tenantName.getText(),
        "Tenant name does not match");
    Assert.assertEquals(tenantModel.getPrimaryTelephone(), primaryTelephone.getText(),
        "Tenant model does not match");
    Assert.assertEquals(tenantModel.getFax(), fax.getText(), "Fax does not match");
  }

  /**
   * Add services
   *
   * @param serviceModel: Enter service details
   */
  public void addAService(ServiceModel serviceModel) {
    _waitForJStoLoad();
    clickOn(clickOnCreatedTenant);
    _waitForJStoLoad();
    clickOn(addServiceBtn);
    waitForElement(inputServiceName);
    inputText(inputServiceName, serviceModel.getServiceName());
    clickOn(saveBtn);
  }

  /**
   * Verify added services
   *
   * @param serviceModel: Enter service details
   */
  public void verifyAddedService(ServiceModel serviceModel) {
    _waitForJStoLoad();
    assertEquals(serviceName.getText(), serviceModel.getServiceName());
  }
}
