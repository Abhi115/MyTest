package com.mobizio.pages;

import com.mobizio.datamodel.RoleModel;
import com.mobizio.selenium.framework.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class RolePage extends BasePage {

  @FindBy(id = "addNewServiceRoleBtn")
  private WebElement newRole;

  @FindBy(id = "serviceRoleName")
  private WebElement roleName;

  @FindBy(xpath = "//*[@id='s2id_autogen1']")
  private WebElement usersTextBox;

  @FindBy(xpath = "//*[@class='select2-match']")
  private WebElement searchedText;

  @FindBy(id = "serviceRoleDescription")
  private WebElement description;

  @FindBy(xpath = "//*[@id='rbacMatrix']/thead/tr[2]/th[1]/div/ins")
  private WebElement create;

  @FindBy(xpath = "//*[@id='rbacMatrix']/thead/tr[2]/th[2]/div/ins")
  private WebElement read;

  @FindBy(xpath = "//*[@id='rbacMatrix']/thead/tr[2]/th[3]/div/ins")
  private WebElement update;

  @FindBy(xpath = "//*[@id='rbacMatrix']/thead/tr[2]/th[4]/div/ins")
  private WebElement delete;

  @FindBy(xpath = "//input[@id='allCases']/../ins")
  private WebElement allcases;

  @FindBy(className = "btn btn-success")
  private WebElement submitButton;

  @FindBy(id = "btnSave")
  private WebElement createButton;

  @FindBy(xpath = "//*[@id='serviceRolesTable_filter']/label/input")
  private WebElement search;

  @FindBy(xpath = "//*[@id='serviceRolesTable']/tbody/tr[1]/td[1]")
  private WebElement newlyCreatedRole;

  private String createRole = "//*[@id='addNewServiceRoleBtn']";

  public RolePage(WebDriver driver) {

    super(driver);
  }

  /**
   * click on add new branch button
   */
  public void clickOnNewRole() {
    waitForElement(newRole);
    clickOn(newRole);
    _waitForJStoLoad();
  }

  /**
   * Enter a role name
   *
   * @param roleModel: Enter role details
   * @throws InterruptedException: Throws exception
   */
  public void enterRoleDetails(RoleModel roleModel) throws InterruptedException {
    waitForElement(roleName);
    clickOn(roleName);
    inputText(roleName, roleModel.getName());

    waitForElement(description);
    clickOn(description);
    inputText(description, roleModel.getDescription());

    waitForElement(usersTextBox);
    clickOn(usersTextBox);
    inputText(usersTextBox, roleModel.getUser());
    waitForElement(searchedText);
    usersTextBox.sendKeys(Keys.ENTER);

    clickOn(create);
    clickOn(read);
    clickOn(update);
    clickOn(delete);
    waitForElement(createButton);
    clickOn(createButton);
    _waitForJStoLoad();
    clickOn(allcases);
    clickOn(submitButton);
    waitForElementPresent(ByLocator(createRole), 60);
    _waitForJStoLoad();

  }

  /**
   * Verify that new role has been created
   *
   * @param roleModel: Enter role details
   */
  public void verifyNewRole(RoleModel roleModel) {
    waitForElement(search);
    clickOn(search);
    inputText(search, roleModel.getName());
    search.sendKeys(Keys.ENTER);
    _waitForJStoLoad();
    waitForElementPresent(ByLocator(createRole), 60);
    Assert.assertEquals(newlyCreatedRole.getText(), roleModel.getName(), "Role name does not match");
  }


}
