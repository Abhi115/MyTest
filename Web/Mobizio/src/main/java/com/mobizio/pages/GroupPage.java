package com.mobizio.pages;

import com.mobizio.datamodel.GroupModel;
import com.mobizio.enums.Action;
import com.mobizio.selenium.framework.BasePage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class GroupPage extends BasePage {

  @FindBy(xpath = "//div[@id='userGroupsTable_wrapper']")
  private WebElement groups;

  @FindBy(id = "addUserGroupBtn")
  private WebElement newGroupButton;

  @FindBy(name = "name")
  private WebElement name;

  @FindBy(xpath = "//li[contains(text(),'New Group')]")
  private WebElement newGroup;

  @FindBy(xpath = "//div[@id='s2id_userNameInput']")
  private WebElement searchSelectTextBox;

  @FindBy(xpath = "//div[@id='s2id_userNameInput']//input")
  private WebElement usersName;

  @FindBy(xpath = "//div[@id='s2id_userGroupNameInput']")
  private WebElement groupSearchSelectTextBox;

  @FindBy(xpath = "//div[@id='s2id_userGroupNameInput']//input")
  private WebElement groupName;

  @FindBy(name = "btnSave")
  private WebElement createButton;

  @FindBy(xpath = "//table[@id='userGroupsTable']/tbody/tr[1]//a")
  private WebElement newlyCreatedGroup;

  @FindBy(id = "editUserGroupBtn")
  private WebElement editGroupBtn;

  @FindBy(xpath = "//input[@type='search']")
  private WebElement search;

  @FindBy(xpath = "//table[@id='userGroupsTable']/tbody/tr/td/a")
  private WebElement userName;

  @FindBy(xpath = "//div[@id='psmobile-container']/div/div/fieldset/div/div/div/div")
  private WebElement mobileName;

  public GroupPage(WebDriver driver) {

    super(driver);
  }

  /**
   * Verify Groups Page
   */
  public void verifyGroupPage() {
    _waitForJStoLoad();
    Assert.assertTrue(isElementPresent(groups), "Element is not present");
  }

  /**
   * Click on new group button
   */
  public void clickOnNewGroupButton() {
    waitForElement(newGroupButton);
    clickOn(newGroupButton);
  }

  /**
   * Verify new group page
   */
  public void verifyNewGroupPage() {
    _waitForJStoLoad();
    String groupPageText = newGroup.getText();
    Assert.assertEquals(Action.NewGroup.toString(), groupPageText, "Page text does not match");
  }

  /**
   * click on create button
   */
  public void clickOnCreateButton() {
    waitForElement(createButton);
    clickOn(createButton);
  }

  /**
   * verify newly created group
   *
   * @param expectedGroupName: Enter group name
   */
  public void verifyCreatedGroupOnGroupPage(String expectedGroupName) {
    String groupName = newlyCreatedGroup.getText();
    Assert.assertEquals(groupName, expectedGroupName, "Group name does not match");
  }

  /**
   * Enter new group details
   *
   * @param groupModel: Enter group details
   */
  public void enterNewGroupDetails(GroupModel groupModel) {
    clickOn(name);
    inputText(name, groupModel.getName());

    waitForElement(searchSelectTextBox);
    clickOn(searchSelectTextBox);
    inputText(usersName, groupModel.getUser1());
    verifyNewGroupPage();
    usersName.sendKeys(Keys.ENTER);

    waitForElement(groupSearchSelectTextBox);
    clickOn(groupSearchSelectTextBox);
    inputText(groupName, groupModel.getGroup1());
    verifyNewGroupPage();
    groupName.sendKeys(Keys.ENTER);

    if (groupModel.getUser2() != null) {
      waitForElement(searchSelectTextBox);
      clickOn(searchSelectTextBox);
      inputText(usersName, groupModel.getUser2());
      verifyNewGroupPage();
      usersName.sendKeys(Keys.ENTER);
    }

    if (groupModel.getGroup2() != null) {
      waitForElement(groupSearchSelectTextBox);
      clickOn(groupSearchSelectTextBox);
      inputText(groupName, groupModel.getGroup2());
      verifyNewGroupPage();
      groupName.sendKeys(Keys.ENTER);
    }
  }


  /**
   * Edit created group
   *
   * @param groupModel: Enter group details
   * @param random: Enter random integer number
   */
  public void editGroup(GroupModel groupModel, int random) {

    clickOn(newlyCreatedGroup);
    _waitForJStoLoad();
    clickOn(editGroupBtn);
    clickOn(name);
    inputText(name, groupModel.getName() + random);
    waitForElement(searchSelectTextBox);
    clickOn(searchSelectTextBox);
    inputText(usersName, groupModel.getUser3());
    usersName.sendKeys(Keys.ENTER);
    clickOn(createButton);
  }

  /**
   * Verify that group is edited
   *
   * @param groupModel:Enter Group details
   * @param random: Enter random number
   */
  public void verifyEditedGroup(GroupModel groupModel, int random) {
    _waitForJStoLoad();
    clickOn(newlyCreatedGroup);
    _waitForJStoLoad();
    Assert.assertEquals(mobileName.getText(), groupModel.getName() + random, "Mobile name does not match");
    List<WebElement> listElement = driver.findElements(By.xpath("//ul[@class='select2-choices']/li"));
    Boolean status = false;
    for (WebElement aListElement : listElement) {
      String value = aListElement.getText();
      if (value.contains(groupModel.getUser3())) {
        status = true;
        break;
      }
    }
    Assert.assertTrue(status, "User name not displayed");
  }

  /**
   * Verify search functionality
   *
   * @param groupModel:Enter group details
   * @throws InterruptedException: Throws interrupted exception
   */
  public void verifySearchfunctionlaity(GroupModel groupModel) {
    _waitForJStoLoad();
    inputText(search, groupModel.getName());
    waitForElement(userName);
    Assert.assertEquals(userName.getText(), groupModel.getName(), "User name does not match");
  }
}



