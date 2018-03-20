package com.mobizio.pages;

import com.mobizio.datamodel.UserModel;
import com.mobizio.selenium.framework.BasePage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class DashboardPage extends BasePage {

  @FindBy(id = "usernameMenu")
  private WebElement userMenu;

  @FindBy(className = "navbar-toggle")
  private WebElement HamburgerIcon;

  @FindBy(xpath = "//li[@id='configurationMenu']/a/span[@class='icon-configuration']")
  private WebElement configuration;

  @FindBy(xpath = "//*[@id='tasksMenu']/a")
  private WebElement visit;

  @FindBy(xpath = "//*[@id='devicesConfigMenu']/a/span[@class='nav-element']")
  private WebElement device;

  @FindBy(xpath = "//*[@id='branchesConfigMenu']/a/span[@class='nav-element']")
  private WebElement branch;

  @FindBy(xpath = "//*[@class='navigation-header-profile-picture-wrapper profile-picture']//div[@class='avatar-circle']")
  private WebElement profilePic;

  @FindBy(xpath = "//*[@id='user-dropdown-menu']//span[text()='Log Out']")
  private WebElement logOutLink;

  @FindBy(xpath = "//ul[@id='user-dropdown-menu']/li/a/span[text()='Edit Profile']")
  private WebElement editProfile;

  @FindBy(id = "lnkEditPassword")
  private WebElement editPasswordBtn;

  @FindBy(xpath = "//li[@id='customersMenu']/a/span[@class='icon-user']")
  private WebElement customer;

  @FindBy(xpath = "//*[@id='formsConfigMenu']/a")
  private WebElement form;

  @FindBy(xpath = "//li[@id='usersConfigMenu']/a/span[@class='icon-user']")
  private WebElement user;

  @FindBy(xpath = "//li[@id='groupsConfigMenu']/a/span[@class='icon-groups']")
  private WebElement groups;

  @FindBy(xpath = "//li[@id='taskTypesConfigMenu']/a/span[@class='nav-element']")
  private WebElement taskTypes;

  @FindBy(xpath = "//li[@id='servicesConfigMenu']/a/span[@class='nav-element']")
  private WebElement services;

  @FindBy(css = "td.sorting_1")
  private WebElement lastUpdatedColumn;

  @FindBy(xpath = "//div[@class='table-responsive']/div[1]/table/tbody/tr[1]/td[9]")
  private WebElement lastUpdatedColumnTasks;

  @FindBy(id = "branchesTable")
  private WebElement branchTable;

  @FindBy(css = ".next>a")
  private WebElement paginationNext;

  @FindBy(xpath = "//li[@class='prev']/a")
  private WebElement paginationPrev;

  @FindBy(xpath = "//div[@class='table-responsive']//select")
  private WebElement recordsPerPage;

  @FindBy(xpath = "//div[@role='status']")
  private WebElement totalRecords;

  @FindBy(xpath = "//div[@class='table-responsive']//table/tbody/tr")
  private List<WebElement> branches;

  @FindBy(linkText = "Tenants")
  private WebElement tenants;

  public DashboardPage(WebDriver driver) {
    super(driver);
  }

  /**
   * Verify user is logged in successfully.
   */
  public DashboardPage verifyLoginSuccess() {
    _waitForJStoLoad();
    Assert.assertTrue(isElementPresent(userMenu), "User not login");
    Assert.assertTrue(isElementPresent(profilePic), "User not login");
    return this;
  }

  /**
   * Click On Hamburger Icon
   */
  public void clickOnHamburgerIcon() {
    waitForElement(HamburgerIcon);
    clickOn(HamburgerIcon);
  }

  /**
   * Click on configuration
   *
   * @return: Instance of configuration page.
   */
  public ConfigurationPage clickOnConfiguration() {
    waitForElement(configuration);
    clickOn(configuration);
    return PageFactory.initElements(getDriver(), ConfigurationPage.class);
  }

  /**
   * Click On visit
   *
   * @return: Instance of task page
   */
  public TaskPage clickOnVisit() {
    waitForElement(visit);
    clickOn(visit);
    return PageFactory.initElements(getDriver(), TaskPage.class);
  }

  /**
   * click On Device
   *
   * @return: Device page
   */
  public DevicePage clickOnDevice() {
    waitForElement(device);
    clickOn(device);
    return PageFactory.initElements(getDriver(), DevicePage.class);
  }

  /**
   * click On Branches
   *
   * @return: Instance of branch page
   */
  public BranchPage clickOnBranch() {
    waitForElement(branch);
    clickOn(branch);
    return PageFactory.initElements(getDriver(), BranchPage.class);
  }

  /**
   * Click On form link
   */
  public FormPage clickOnForm() {
    waitForElement(form);
    clickOn(form);
    return PageFactory.initElements(getDriver(), FormPage.class);
  }


  /**
   * Click On Customer link
   *
   * @return:Instance of Customer page
   */
  public CustomerPage clickOnCustomer() {
    waitForElement(customer);
    clickOn(customer);
    return PageFactory.initElements(getDriver(), CustomerPage.class);
  }

  /**
   * Logout the application
   *
   * @return: Instance of login page
   */
  public LoginPage logOut(){
    waitForElementClickable(userMenu,10);
    clickOn(userMenu);
    clickOn(logOutLink);
    return PageFactory.initElements(getDriver(), LoginPage.class);
  }

  /**
   * Click on user link
   *
   * @return: Instance of User Page
   */
  public UserPage clickOnUsers() {
    waitForElement(user);
    clickOn(user);
    return PageFactory.initElements(getDriver(), UserPage.class);
  }

  /**
   * Click on task type link
   *
   * @return: Instance of TaskType page
   */
  public TaskTypePage clickOnTaskType() {
    waitForElement(taskTypes);
    clickOn(taskTypes);
    return PageFactory.initElements(getDriver(), TaskTypePage.class);
  }

  /**
   * Click on services link
   *
   * @return: Instance of Service Page.
   */
  public ServicesPage clickOnServices() {
    waitForElement(services);
    clickOn(services);
    return PageFactory.initElements(getDriver(), ServicesPage.class);
  }

  /**
   * Click on groups link
   *
   * @return: Instance of group page
   */
  public GroupPage clickOnGroups() {
    waitForElement(groups);
    clickOn(groups);
    return PageFactory.initElements(getDriver(), GroupPage.class);
  }

  /**
   * Click on tenants link
   *
   * @return: Instance of Tenant Page
   */
  public TenantPage clickOnTenants() {
    waitForElement(tenants);
    clickOn(tenants);
    _waitForJStoLoad();
    return PageFactory.initElements(getDriver(), TenantPage.class);
  }

  /**
   * Verify that last update is localised
   */
  public void verifyLastUpdatedLocalized() throws Exception {
    _waitForJStoLoad();
    Date currentDate = new Date();
    String getDateTasks = getText(lastUpdatedColumn);
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yy hh:mm a");
    Date lastUpdatedTask = formatter.parse(getDateTasks);
    Assert.assertTrue((((currentDate.getTime() - lastUpdatedTask.getTime()) / (60 * 1000)) < 5), "Time difference " + "is not same");
  }

  /**
   * Verify pagination
   */
  public void verifyPagination() {
    _waitForJStoLoad();
    WebElement count = driver.findElement(
        By.xpath("//div[@class='dataTables_paginate paging_bootstrap pagination']/ul"));
    List<WebElement> pageCount = count.findElements(
        By.xpath("//div[@class='dataTables_paginate paging_bootstrap pagination']/ul/li"));
    System.out.println(pageCount.size());
    for (int i = 1; i < pageCount.size() - 2; i++) {
      if (paginationNext.isDisplayed() && paginationNext.isEnabled()) {
        try {
          clickOn(paginationNext);
          _waitForJStoLoad();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    for (int j = 1; j < pageCount.size() - 2; j++) {
      if (paginationPrev.isDisplayed() && paginationPrev.isEnabled()) {
        try {
          clickOn(paginationPrev);
          _waitForJStoLoad();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * Verify records per page drop-down value
   */
  public void verifyRecordsPerPageDropDownValue() {
    int[] records = {10, 25, 50, 100};
    _waitForJStoLoad();
    List<WebElement> options = new Select(recordsPerPage).getOptions();
    for (int i = 0; i < options.size(); i++) {
      Assert.assertEquals(Integer.parseInt(options.get(i).getText()), records[i],
          "Record is not same");
    }
  }

  /**
   * Verify records per page
   */
  public void verifyRecordsPerPage() {
    _waitForJStoLoad();
    Boolean status = false;
    List<WebElement> options = new Select(recordsPerPage).getOptions();
    String str1 = totalRecords.getText();
    String[] tmp1 = str1.split("f");
    String[] tmp2 = tmp1[1].split(" ");
    String str2 = tmp2[1].trim();
    int totalNoOfRecords = Integer.parseInt(str2.replaceAll(",", ""));
    for (int i = 0; i < options.size(); i++) {
      selectDropDownByIndex(recordsPerPage, i);
      _waitForJStoLoad();
      String record = options.get(i).getText();
      int selectedRecord = Integer.parseInt(record);
      int count = branches.size();
      if (selectedRecord == count) {
        status = true;
      } else if (totalNoOfRecords == count) {
        status = true;
        break;
      } else {
        status = false;
        break;
      }
    }
    Assert.assertTrue(status, "Status is not as expected");
  }

  /**
   * Change password
   *
   * @param userModel: Enter user details
   */
  public void changePassword(UserModel userModel) {
    findElement(By.id("txtUserName")).sendKeys(userModel.getUserName());
    findElement(By.id("txtPassword")).sendKeys(userModel.getPassword());
    findElement(By.xpath("//input[@value='Login']")).click();
    _waitForJStoLoad();
    clickOn(userMenu);
    clickOn(editProfile);
    _waitForJStoLoad();
    findElement(By.xpath("//div[@id='content']/section/div/ol/li[2]/a/span")).click();
    _waitForJStoLoad();
    clickOn(editPasswordBtn);
    findElement(By.id("passwordValueInput")).sendKeys(userModel.getChangePassword());
    findElement(By.id("confirmPasswordValueInput")).sendKeys(userModel.getChangePassword());
    findElement(By.id("btnSave")).click();
  }
}

