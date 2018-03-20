package com.mobizio.pages;

import com.mobizio.datamodel.DeviceModel;
import com.mobizio.selenium.framework.BasePage;
import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument.Field.Xpath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class DevicePage extends BasePage {

  @FindBy(css = "#addNewDeviceBtn>span")
  private WebElement newDevice;

  @FindBy(name = "uuid")
  private WebElement uuidInputField;

  @FindBy(name = "name")
  private WebElement nameInputField;

  @FindBy(name = "os")
  private WebElement operatingSystem;

  @FindBy(name = "model")
  private WebElement modelInputField;

  @FindBy(name = "phoneNo")
  private WebElement phoneNumber;

  @FindBy(xpath = "//*[@id='s2id_autogen1']")
  private WebElement searchAssigneeTextBox;

  @FindBy(className = "select2-choice")
  private WebElement searchBranchDropDownBox;

  @FindBy(xpath = "//span[contains(@class,'select2-match')]")
  private WebElement assigneeOption;

  @FindBy(id = "btnSave")
  private WebElement createButton;

  @FindBy(xpath = "//*[@id='s2id_autogen2_search']")
  private WebElement searchBranchBox;

  @FindBy(xpath = "//*[@id='devicesTable']/tbody/tr[1]/td[1]/a")
  private WebElement deviceName;

  @FindBy(id = "editDeviceBtn")
  private WebElement editDeviceBtn;

  @FindBy(xpath = "//input[@type='search']")
  private WebElement search;

  @FindBy(xpath = "//label[contains(text(),' UUID')]/following-sibling::div")
  private WebElement uuid;

  @FindBy(xpath = "//label[contains(text(),'Name')]/following-sibling::div")
  private WebElement deviceNameField;

  @FindBy(xpath = "//label[contains(text(),' Model')]/following-sibling::div")
  private WebElement model;

  @FindBy(xpath = "//label[contains(text(),' Operating System')]/following-sibling::div")
  private WebElement operatingSystemField;

  @FindBy(xpath = "//label[contains(text(),' Phone Number')]/following-sibling::div")
  private WebElement phoneNumberField;

  @FindBy(xpath = "//label[contains(text(),' Assignee')]/following-sibling::div")
  private WebElement assignee;

  @FindBy(xpath = "//table[@id='devicesTable']/tbody/tr/td/a")
  private WebElement UUId;

  @FindBy(xpath = "//table[@id='devicesTable']/tbody/tr/td[2]")
  private WebElement editableDeviceName;

  @FindBy(xpath="//table[@id='devicesTable']/tbody/tr/td[4]")
  private WebElement modelName;

  @FindBy(xpath = "//div[@id='psmobile-container']/div/div/fieldset/div/div/div/div")
  private WebElement deviceModel;

  @FindBy(xpath = "//table[@id='devicesTable']/tbody/tr/td[3")
  private WebElement deviceModelAndroid;

  @FindBy(xpath = "//table[@id='devicesTable']/tbody/tr/td[7]")
  private WebElement branch;

  @FindBy(xpath = "//div[@id='psmobile-container']/div/div/fieldset/div/div/div/div")
  private WebElement deviceModelName;

  @FindBy(xpath = "//div[@id='psmobile-container']/div/div/fieldset/div[4]/div[2]/div/div")
  private WebElement assigneeName;

  public DevicePage(WebDriver driver) {
    super(driver);
  }

  /**
   * Click on add new customer button
   */
  public void clickOnNewDevice() {
    _waitForJStoLoad();
    waitForElement(newDevice);
    clickOn(newDevice);
    _waitForJStoLoad();
  }

  /**
   * Create a device with its details
   *
   * @param deviceModel: Enter device details
   */
  public void enterNewDeviceDetail(DeviceModel deviceModel) {

    waitForElement(uuidInputField);
    clickOn(uuidInputField);
    inputText(uuidInputField, deviceModel.getUUID());

    waitForElement(nameInputField);
    clickOn(nameInputField);
    inputText(nameInputField, deviceModel.getName());

    waitForElement(operatingSystem);
    clickOn(operatingSystem);
    selectDropDownByText(operatingSystem, deviceModel.getOperatingSystemAndroid());

    waitForElement(modelInputField);
    clickOn(modelInputField);
    inputText(modelInputField, deviceModel.getModel());

    if (deviceModel.getAssignee() != null) {
      waitForElement(searchAssigneeTextBox);
      clickOn(searchAssigneeTextBox);
      inputText(searchAssigneeTextBox, deviceModel.getAssignee());
      waitForElement(assigneeOption);
      searchAssigneeTextBox.sendKeys(Keys.ENTER);
    }

    waitForElement(phoneNumber);
    clickOn(phoneNumber);
    inputText(phoneNumber, deviceModel.getPhoneNumber());

    waitForElement(searchBranchDropDownBox);
    clickOn(searchBranchDropDownBox);
    inputText(searchBranchBox, deviceModel.getBranch());
    waitForElement(assigneeOption);
    searchBranchBox.sendKeys(Keys.ENTER);

    waitForElement(createButton);
    clickOn(createButton);
    _waitForJStoLoad();
  }

  /**
   * Verify newly created device
   *
   * @param expectedDeviceName: Expected device name
   */
  public void verifyCreatedDeviceOnDevicePage(String expectedDeviceName) {
    String device = deviceName.getText();
    Assert.assertEquals(device, expectedDeviceName, "Device name does not match");
  }

  /**
   * Edit device details
   *
   * @param deviceModel: Enter device details
   * @param random: Enter random integer
   */
  public void editDevice(DeviceModel deviceModel, int random) {

    clickOn(deviceName);
    waitForElement(editDeviceBtn);
    clickOn(editDeviceBtn);

    waitForElement(uuidInputField);
    clickOn(uuidInputField);
    inputText(uuidInputField, deviceModel.getUUID() + random);

    waitForElement(nameInputField);
    clickOn(nameInputField);
    inputText(nameInputField, deviceModel.getName() + random);

    waitForElement(operatingSystem);
    clickOn(operatingSystem);
    selectDropDownByText(operatingSystem, deviceModel.getOperatingSystemiOS());

    waitForElement(modelInputField);
    clickOn(modelInputField);
    inputText(modelInputField, deviceModel.getModel() + random);

    if (deviceModel.getAssignee() != null) {
      waitForElement(searchAssigneeTextBox);
      clickOn(searchAssigneeTextBox);
      inputText(searchAssigneeTextBox, deviceModel.getNewAssignee());
      waitForElement(assigneeOption);
      searchAssigneeTextBox.sendKeys(Keys.ENTER);
    }

    waitForElement(phoneNumber);
    clickOn(phoneNumber);
    inputText(phoneNumber, deviceModel.getPhoneNumber() + random);

    waitForElement(createButton);
    clickOn(createButton);
    _waitForJStoLoad();
  }

  /**
   * Verify edited device details
   *
   * @param deviceModel: Pass reference of DeviceModel class
   * @param random: Pass random interger
   */
  public void verifyEditedDevice(DeviceModel deviceModel, int random) {
    clickOn(deviceName);
    _waitForJStoLoad();
    Assert.assertEquals(uuid.getText(), deviceModel.getUUID() + random, "UUID does not match");
    Assert.assertEquals(deviceNameField.getText(), deviceModel.getName() + random, "Device name does not match");
    Assert.assertEquals(model.getText(), deviceModel.getModel() + random);
    Assert.assertEquals(operatingSystemField.getText(), deviceModel.getOperatingSystemiOS(), "Operating system " + "field is not same");
    Assert.assertEquals(deviceModelName.getText(), deviceModel.getUUID() + random, "Device model is not same.");
    Assert.assertEquals(phoneNumberField.getText(), deviceModel.getPhoneNumber() + random, "Phone number field " + "is not same");
    Assert.assertTrue(assigneeName.getText().contains(deviceModel.getNewAssignee()), "Assignee name does not match");
  }

  /**
   * Verify search functionality
   *
   * @param deviceModel: Enter device model
   */
  public void verifySearchFunctionality(DeviceModel deviceModel) {

    _waitForJStoLoad();
    inputText(search, deviceModel.getUUID());
    Assert.assertEquals(UUId.getText(), deviceModel.getUUID(), "UUID does not match");
    inputText(search, deviceModel.getName());
    Assert.assertEquals(editableDeviceName.getText(), deviceModel.getName(), "Device name does not match");
    inputText(search, deviceModel.getModel());
    Assert.assertEquals(modelName.getText(), deviceModel.getModel(), "Device name does not match");
    inputText(search, deviceModel.getOperatingSystemAndroid());
    Assert.assertEquals(deviceModelAndroid.getText(), deviceModel.getOperatingSystemAndroid(), "Device model does not match");
    inputText(search, deviceModel.getBranch());
    Assert.assertEquals(branch.getText(), deviceModel.getBranch(), "Branch does not match");
  }
}
