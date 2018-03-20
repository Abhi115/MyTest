package com.mobizio.pages;

import com.mobizio.datamodel.TaskTypeModel;
import com.mobizio.selenium.framework.BasePage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class TaskTypePage extends BasePage {

  @FindBy(xpath = "//ol[@class='breadcrumb']/li")
  private WebElement taskTypes;

  @FindBy(id = "addNewTaskTypeBtn")
  private WebElement newTaskTypeButton;

  @FindBy(xpath = "//div[@id='content']/section/div/ol/li[2]")
  private WebElement newTaskType;

  @FindBy(name = "name")
  private WebElement name;

  @FindBy(xpath = "//div[@id='associated-forms']/span")
  private List<WebElement> associatedForms;

  @FindBy(id = "btnSave")
  private WebElement createButton;

  @FindBy(xpath = "//table[@id='taskTypesTable']/tbody/tr[1]//a")
  private WebElement newlyCreatedTaskType;

  @FindBy(xpath = "//div[@id='psmobile-container']//div[2]//ul/li")
  private WebElement verifyAssociatedForms;

  public TaskTypePage(WebDriver driver) {
    super(driver);
  }

  /**
   * Verify task type page
   */
  public void verifyTaskTypePage() {
    _waitForJStoLoad();
    String taskTypePageText = taskTypes.getText();
    Assert.assertEquals(taskTypes.getText(), taskTypePageText,"Task type page text does not match");
  }

  /**
   * Click on new task type button
   */
  public void clickOnNewTaskTypeButton() {
    waitForElement(newTaskTypeButton);
    String newTaskTypePage = newTaskTypeButton.getText();
    clickOn(newTaskTypeButton);
    _waitForJStoLoad();
    String newTaskTypePageText = newTaskType.getText();
    Assert.assertEquals(newTaskTypePage, newTaskTypePageText, "New TaskType page text does not match");
  }

  /**
   * Enter name to create new task type
   *
   * @param taskTypeModel: Enter task type
   */
  public void enterName(TaskTypeModel taskTypeModel) {
    waitForElement(name);
    clickOn(name);
    inputText(name, taskTypeModel.getName());
  }

  /**
   * Click on create button
   */
  public void clickOnCreateButton() {
    waitForElement(createButton);
    clickOn(createButton);
  }

  /**
   * Verify newly created task type
   */
  public void verifyNewlyCreatedTasktype(TaskTypeModel taskTypeModel) {
    String newTaskType = newlyCreatedTaskType.getText();
    Assert.assertEquals(newTaskType, taskTypeModel.getName(), "New TaskType model does not match");
    clickOn(newlyCreatedTaskType);
    _waitForJStoLoad();
    Assert.assertEquals(verifyAssociatedForms.getText(), taskTypeModel.getFormName(), "Form name does not match");
  }

  /**
   * Associate forms to new task type
   *
   * @param taskTypeModel: Enter Task type model
   */
  public void associateForm(TaskTypeModel taskTypeModel) {
    int count = associatedForms.size();
    boolean status = false;
    for (int i = 1; i <= count; i++) {
      WebElement formName = getDriver().findElement(By.xpath("//div[@id='associated-forms']/span[" + i + "]/label"));
      String fName = formName.getText();
      if (fName.equals(taskTypeModel.getFormName())) {
        status = true;
        clickOn(formName);
        break;
      }
    }
    Assert.assertTrue(status,"Form not found");
  }
}
