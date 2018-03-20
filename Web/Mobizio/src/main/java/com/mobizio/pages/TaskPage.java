package com.mobizio.pages;

import com.mobizio.datamodel.TaskModel;
import com.mobizio.enums.ValidationMessage.Validation;
import com.mobizio.selenium.framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TaskPage extends BasePage {

  @FindBy(xpath = "//*[@id='tasknameValueInput']")
  private WebElement visitName;

  @FindBy(id = "tenantTaskIdValueInput")
  private WebElement visitID;

  @FindBy(name = "taskTypeId")
  private WebElement taskTypeDropDown;

  @FindBy(name = "startDate")
  private WebElement startTime;

  @FindBy(name = "endDate")
  private WebElement endTime;

  @FindBy(name = "status")
  private WebElement status;

  @FindBy(xpath = "//*[@id='select2-chosen-1']")
  private WebElement assignee;

  @FindBy(xpath = "//*[@id='s2id_autogen1_search']")
  private WebElement assigneeSearch;

  @FindBy(id = "s2id_customerNameInput")
  private WebElement customer;

  @FindBy(xpath = "//*[@id='s2id_autogen2_search']")
  private WebElement customerSearch;

  @FindBy(id = "s2id_branchNameInput")
  private WebElement branch;

  @FindBy(xpath = "//*[@id='s2id_autogen3_search']")
  private WebElement branchSearch;

  @FindBy(name = "caseId")
  private WebElement associatedCase;

  @FindBy(name = "activities")
  private WebElement activities;

  @FindBy(id = "tenantWorkplanId")
  private WebElement carePlanId;

  @FindBy(name = "//*[@name='firstLine']")
  private WebElement addressLine1;

  @FindBy(name = "secondLine")
  private WebElement addressLine2;

  @FindBy(name = "city")
  private WebElement city;

  @FindBy(name = "state")
  private WebElement county;

  @FindBy(name = "postcode")
  private WebElement postcode;

  @FindBy(name = "country")
  private WebElement country;

  @FindBy(xpath = "//a[contains(text(),'Autofill Latitude & Longitude')]")
  private WebElement autoFillLatitudeLongitude;

  @FindBy(name = "latitude")
  private WebElement latitude;

  @FindBy(name = "longitude")
  private WebElement longitude;

  @FindBy(xpath = "//*[@id='addNewTaskBtn']/span")
  private WebElement newVisit;

  @FindBy(name = "btnSave")
  private WebElement createButton;

  @FindBy(xpath = "//*[@id='tasksTable']/tbody/tr[1]/td[1]")
  private WebElement taskName;

  @FindBy(id = "advanceSearchButton")
  private WebElement search;

  @FindBy(id = "taskNameSearchInput")
  private WebElement visitSearch;

  @FindBy(xpath = "//*[@id='searchButton']")
  private WebElement searchButton;

  @FindBy(xpath = "//*[@id='tasksTable']/tbody/tr/td[1]/a")
  private WebElement searchedTask;

  @FindBy(id = "editTaskBtn")
  private WebElement editButton;

  @FindBy(id = "btnSave")
  private WebElement updateButton;

  @FindBy(xpath = "//input[contains(@aria-activedescendant,'select2-result')]")
  private WebElement waitForSearchSelectTextBox;

  @FindBy(id = "tenantTaskSearchInput")
  private WebElement visitSearchByID;

  @FindBy(id = "customerSearchInput")
  private WebElement visitSearchByCustomer;

  @FindBy(id = "assigneeSearchInput")
  private WebElement visitSearchByAssignee;

  @FindBy(id = "statusSearchInput")
  private WebElement visitSearchByStatus;

  @FindBy(id = "startDateTime")
  private WebElement visitSearchByStartDate;

  @FindBy(xpath = "//button[@class='btn-success applyBtn btn btn-small']")
  private WebElement calenderApplyBtn;

  @FindBy(id = "branchSearchInput")
  private WebElement visitSearchByBranch;

  @FindBy(xpath = "//*[@id='tasksTable']/tbody/tr/td[2]")
  private WebElement searchedTaskID;

  @FindBy(xpath = "//*[@id='tasksTable']/tbody/tr/td[5]")
  private WebElement searchedTaskStatus;

  @FindBy(xpath = "//*[@id='tasksTable']/tbody/tr/td[6]")
  private WebElement searchedAssignee;

  @FindBy(xpath = "//*[@id='tasksTable']/tbody/tr/td[7]")
  private WebElement searchedCustomer;

  @FindBy(xpath = "//*[@id='tasksTable']/tbody/tr/td[8]")
  private WebElement searchedBranch;

  @FindBy(xpath = "//tr[@class='even']/td[5]")
  private WebElement outstanding;

  @FindBy(xpath = "//tr[@class='odd']/td[5]")
  private WebElement cancelled;

  @FindBy(xpath = "//table[@id='tasksTable']/tbody/tr[1]/td[1]/a")
  private WebElement createdVisit;

  @FindBy(id = "editTaskBtn")
  private WebElement editTaskBtn;

  @FindBy(id = "btnSave")
  private WebElement buttonUpdate;

  @FindBy(xpath = "//a[contains(text(),'Clear')]")
  private WebElement clearButton;

  @FindBy(xpath = "//ul[@id='select2-results-1']/li")
  private WebElement noResult;

  public TaskPage(WebDriver driver) {
    super(driver);
  }

  /**
   * click on add new branch button
   */
  public void clickOnNewVisit() {
    _waitForJStoLoad();
    waitForElement(newVisit);
    clickOn(newVisit);
    _waitForJStoLoad();
  }

  /**
   * Create a device with its details
   *
   * @param visitModel: Enter visit details
   * @param loginUser: Login user name
   */
  public void enterNewVisitDetail(TaskModel visitModel, String loginUser) {

    waitForElement(visitName);
    clickOn(visitName);
    inputText(visitName, visitModel.getName());

    clickOn(visitID);
    inputText(visitID, visitModel.getvisitID());

    waitForElement(taskTypeDropDown);
    clickOn(taskTypeDropDown);
    selectDropDownByText(taskTypeDropDown, visitModel.getTaskType());

    waitForElement(assignee);
    clickOn(assignee);
    waitForElement(assigneeSearch);
    inputText(assigneeSearch, visitModel.getAssignee());
    waitForElement(waitForSearchSelectTextBox);
    assigneeSearch.sendKeys(Keys.ENTER);

    waitForElement(customer);
    clickOn(customer);
    waitForElement(customerSearch);
    inputText(customerSearch, visitModel.getCustomer());
    waitForElement(waitForSearchSelectTextBox);
    customerSearch.sendKeys(Keys.ENTER);

    if (loginUser.equals("Tenant")) {
      clickOn(branch);
      inputText(branchSearch, visitModel.getBranch());
      waitForElement(waitForSearchSelectTextBox);
      branchSearch.sendKeys(Keys.ENTER);
    }
    waitForElement(addressLine1);
    clickOn(addressLine1);
    inputText(addressLine1, visitModel.getAddressLine1());

    waitForElement(addressLine2);
    clickOn(addressLine2);
    inputText(addressLine2, visitModel.getAddressLine2());

    waitForElement(city);
    clickOn(city);
    inputText(city, visitModel.getCity());

    waitForElement(county);
    clickOn(county);
    inputText(county, visitModel.getCounty());

    waitForElement(country);
    clickOn(country);
    selectDropDownByText(country, visitModel.getCountry());

    waitForElement(postcode);
    clickOn(postcode);
    inputText(postcode, visitModel.getPostcode());

    waitForElement(latitude);
    clickOn(latitude);
    inputText(latitude, visitModel.getLatitude());

    waitForElement(longitude);
    clickOn(longitude);
    inputText(longitude, visitModel.getLongitude());

    waitForElement(createButton);
    clickOn(createButton);
    _waitForJStoLoad();
  }

  /**
   * Verify newly created customer on customer page
   */
  public void clickOnSearch() {
    clickOn(search);
    _waitForJStoLoad();
  }

  /**
   * Search task by visit name
   *
   * @param taskName: Enter task name
   */
  public void serachByVisitName(String taskName) {
    clearSearch();
    inputText(visitSearch, taskName);
    searchButton.click();
    _waitForJStoLoad();
  }

  /**
   * Search task by visit id
   *
   * @param visitID: Enter visit Id
   */
  public void searchByVisitID(String visitID) {
    clearSearch();
    waitAndClick(visitSearchByID);
    inputText(visitSearchByID, visitID);
    searchButton.click();
    _waitForJStoLoad();
  }

  /**
   * click On searched task
   */
  public void clickOnSearchedTask() {
    clickOn(searchedTask);
    _waitForJStoLoad();

  }

  /**
   * click On edit button
   */
  public void clickOnEditButton() {
    _waitForJStoLoad();
    clickOn(editButton);
    _waitForJStoLoad();

  }

  /**
   * click On save button
   */
  public void clickOnSaveButton() {
    clickOn(updateButton);
    _waitForJStoLoad();

  }

  /**
   * Change assignee
   *
   * @param visitModel: Enter visit model id
   */
  public void changeAssignee(TaskModel visitModel) {
    waitForElement(assignee);
    clickOn(assignee);
    waitForElement(assigneeSearch);
    inputText(assigneeSearch, visitModel.getNewAssignee());
    waitForElement(waitForSearchSelectTextBox);
    assigneeSearch.sendKeys(Keys.ENTER);

  }

  /**
   * Verify newly created customer on customer page
   *
   * @param expectedTaskName: Enter task name
   */
  public void verifyCreatedTaskOnTaskPage(String expectedTaskName) {
    _waitForJStoLoad();
    String task = taskName.getText();
    Assert.assertEquals(task, expectedTaskName, "Task name does not match");
  }

  /**
   * Get task name
   *
   * @return: created tasks on tasks page.
   */
  public String getCreatedTaskOnTaskPage() {
    _waitForJStoLoad();
    return taskName.getText();

  }

  /**
   * Verify visit reassignment.
   */
  public void verifyVisitReassignment() {
    String cancelledTask = getText(cancelled);
    String outstandingTask = getText(outstanding);
    Assert.assertEquals(cancelledTask, "Cancelled");
    Assert.assertEquals(outstandingTask, "Outstanding");
  }

  /**
   * Edit visit details
   *
   * @param visitModel: Enter visit details
   * @param randomString: random string
   */
  public void editVisit(TaskModel visitModel, String randomString) {

    clickOn(createdVisit);
    _waitForJStoLoad();
    clickOn(editTaskBtn);
    _waitForJStoLoad();

    waitForElement(visitName);
    clickOn(visitName);
    inputText(visitName, visitModel.getName() + randomString);

    waitForElement(taskTypeDropDown);
    clickOn(taskTypeDropDown);
    selectDropDownByText(taskTypeDropDown, visitModel.getNewTaskType());

    waitForElement(addressLine1);
    clickOn(addressLine1);
    inputText(addressLine1, visitModel.getAddressLine1() + randomString);

    waitForElement(addressLine2);
    clickOn(addressLine2);
    inputText(addressLine2, visitModel.getAddressLine2() + randomString);

    waitForElement(city);
    clickOn(city);
    inputText(city, visitModel.getCity() + randomString);

    waitForElement(county);
    clickOn(county);
    inputText(county, visitModel.getCounty() + randomString);

    waitForElement(buttonUpdate);
    clickOn(buttonUpdate);
    _waitForJStoLoad();
  }

  /**
   * Verify that visit is edited successfully
   *
   * @param visitModel: Enter visit details
   * @param randomString: Enter random string
   */
  public void verifyEditedVisit(TaskModel visitModel, String randomString) {
    clickOn(createdVisit);
    _waitForJStoLoad();

    String visitNameText = "//div[@class='controls']";
    Assert.assertEquals(findElement(ByLocator(visitNameText)).getText(), visitModel.getName() + randomString, "Model name does not match");

    String tasktypeText = "//fieldset/div/div[2]/div/div";
    Assert.assertEquals(findElement(ByLocator(tasktypeText)).getText(), visitModel.getNewTaskType(), "Task type does not match");

    String addressLine1Text = "//div[5]/div/div/div";
    Assert.assertEquals(findElement(ByLocator(addressLine1Text)).getText(), visitModel.getAddressLine1() + randomString, "Address line 1 does not match");

    String addressLine2Text = "//div[5]/div[2]/div/div";
    Assert.assertEquals(findElement(ByLocator(addressLine2Text)).getText(), visitModel.getAddressLine2() + randomString, "Address line 2 does not match");

    String cityText = "//div[5]/div[3]/div/div";
    Assert.assertEquals(findElement(ByLocator(cityText)).getText(), visitModel.getCity() + randomString, "City does not match");

    String countyText = "//div[6]/div/div/div";
    Assert.assertEquals(findElement(ByLocator(countyText)).getText(), visitModel.getCounty() + randomString, "County does not match");

  }

  /**
   * Verify search functionality on tasks page.
   *
   * @param visitModel: Enter visit details
   * @param loginUser: Enter login user name
   */
  public void verifySearchFunctionalityOnTaskPage(TaskModel visitModel, String loginUser) {
    this.clickOnSearch();
    this.serachByVisitName(visitModel.getName());
    Assert.assertEquals(searchedTask.getText(), visitModel.getName(), "Visit name does not match");

    this.searchByVisitID(visitModel.getvisitID());
    Assert.assertEquals(searchedTaskID.getText(), visitModel.getvisitID(), "Visite id does not match");

    if (!loginUser.equals("Carer")) {
      this.searchByAssignee(visitModel.getAssignee());
      Assert.assertEquals(searchedAssignee.getText(), visitModel.getAssignee(), "Assignee not match");
    }
    this.searchByCustomer(visitModel.getCustomer());
    Assert.assertEquals(searchedCustomer.getText(), visitModel.getCustomer(), "Customer name does not match");

    this.searchByStatus(visitModel.getStatus());
    Assert.assertEquals(searchedTaskStatus.getText(), visitModel.getStatus(), "Status does not match");

    if (loginUser.equals("Tenant")) {
      this.searchByBranch(visitModel.getBranch());
      Assert.assertEquals(searchedBranch.getText(), visitModel.getBranch(), "Branch does not match");
    }
    this.searchByStartdate(visitModel.getStartTime());
  }

  /**
   * Search task by start date
   *
   * @param startTime: Enter start time
   */
  private void searchByStartdate(String startTime) {
    clearSearch();
    waitAndClick(visitSearchByStartDate);
    waitAndClick(calenderApplyBtn);
    searchButton.click();
    _waitForJStoLoad();
  }

  /**
   * Search tasks by branch name
   *
   * @param branch: Enter branch
   */
  private void searchByBranch(String branch) {
    clearSearch();
    waitAndClick(visitSearchByBranch);
    inputText(visitSearchByBranch, branch);
    searchButton.click();
    _waitForJStoLoad();
  }

  /**
   * Search tasks by status
   *
   * @param status: Enter status
   */
  private void searchByStatus(String status) {
    clearSearch();
    waitAndClick(visitSearchByStatus);
    selectDropDownByText(visitSearchByStatus, status);
    searchButton.click();
    _waitForJStoLoad();
  }

  /**
   * Search tasks by customer name
   *
   * @param customer: Enter customer name
   */
  private void searchByCustomer(String customer) {
    clearSearch();
    waitAndClick(visitSearchByCustomer);
    inputText(visitSearchByCustomer, customer);
    searchButton.click();
    _waitForJStoLoad();
  }

  /**
   * Search tasks by Assignee
   *
   * @param assignee: Enter assignee name
   */
  private void searchByAssignee(String assignee) {
    clearSearch();
    waitAndClick(visitSearchByAssignee);
    inputText(visitSearchByAssignee, assignee);
    searchButton.click();
    _waitForJStoLoad();
  }

  /**
   * Clear search
   */
  private void clearSearch() {
    waitForElement(clearButton);
    clearButton.click();
    _waitForJStoLoad();
  }

  /**
   * verify that user is unable to create a task if assignee is non shared and belongs to some other branch
   *
   * @param assigneeName: Enter assignee name
   */
  public void verifyUserIsNotAbleToCreateTask(String assigneeName) {
    boolean status = false;
    _waitForJStoLoad();
    this.clickOnNewVisit();
    _waitForJStoLoad();
    clickOn(assignee);
    waitForElement(assigneeSearch);
    inputText(assigneeSearch, assigneeName);
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//li[@class='select2-searching']")));
    if ((noResult.getText()).equals(Validation.NoMatchFound.toString())) {
      status = true;
    }
    Assert.assertTrue(status, "Result not found");
  }
}	

