package com.pointrlab.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.pointrlab.selenium.framework.BasePage;

public class MapDesignerPage extends BasePage {

	public MapDesignerPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@data-id='0']//div[@class='pull-left layer-icon']")
	private WebElement arrowIcon;

	@FindBy(xpath = "//div[@data-id='2']//div[@class='pull-left layer-icon']")
	private WebElement selectlayerArrowIcon;

	@FindBy(xpath = "//select[@class='layer-select']")
	private WebElement layerDropdown;

	@FindBy(xpath = "//span[contains(@class,'layer-add')]")
	private WebElement layerAddButn;

	@FindBy(xpath = "//a[@title='Draw a polygon']")
	private WebElement drawPolygon;

	@FindBy(xpath = "//a[@title='Draw a rectangle']")
	private WebElement drawRectangle;

	@FindBy(xpath = "//li[@data-id='2']/ul/li")
	private List<WebElement> poiShapes;

	@FindBy(xpath = "//button[@name='delete']")
	private WebElement deleteBtn;

	@FindBy(xpath = "//select[@class='layer-select']")
	private WebElement selectPOILayer;

	@FindBy(xpath = "//img[@class='leaflet-image-layer leaflet-zoom-animated']")
	private WebElement floorImage;

	@FindBy(xpath = "//a[@title='Finish drawing']")
	private WebElement finishDrawing;

	@FindBy(xpath = "//input[@name='name']")
	private WebElement poiName;

	@FindBy(xpath = "//textarea[@name='description']")
	private WebElement poiDescription;

	@FindBy(xpath = "//input[@name='keywords']")
	private WebElement poiKeywords;

	@FindBy(xpath = "//button[@name='save']")
	private WebElement poiSaveBtn;

	@FindBy(xpath = "//li[@data-id='2']/ul/li/ul/li")
	private WebElement addedPOI;

	@FindBy(xpath = "//button[@name='update']")
	private WebElement poiUpdateBtn;

	/**
	 * verify map designer page
	 */
	public void verifyMapDesignerPage() {
		_waitForJStoLoad();
		verifyURL("MapDesigner");
	}

	/**
	 * click on arrow icon for level 1
	 */
	public void clickOnArrowIcon() {
		waitAndClick(arrowIcon);
	}

	/**
	 * select poi layer from dropdown
	 * 
	 * @param poiType
	 */
	public void selectPOILayer(String poiType) {
		_waitForJStoLoad();
		selectDropDownByText(layerDropdown, poiType);
	}

	/**
	 * click on + button to add layer
	 */
	public void clickOnPlusButtonToAddLayer() {
		_waitForJStoLoad();
		clickOn(layerAddButn);
	}

	/**
	 * verify tool bar options to draw pattern are present
	 */
	public void verifyPOIDrawToolBarOptions() {
		verifyElementPresent(drawPolygon);
		verifyElementPresent(drawRectangle);
	}

	/**
	 * click on arrow icon on left of select layer dropdown
	 */
	public void clickOnSelectLayerArrowIcon() {
		waitAndClick(selectlayerArrowIcon);
	}

	/**
	 * delete all available shapes
	 */
	public void deleteCreatedShapesOnPOILayers() {
		for (int i = 0; i < poiShapes.size(); i++) {
			List<WebElement> layerList = driver.findElements(By
					.xpath("//li[@data-id='2']/ul/li[" + (i + 1) + "]/ul/li"));
			if (layerList.size() > 0) {
				getDriver()
						.findElement(
								By.xpath("//li[@data-id='2']/ul/li["
										+ (i + 1)
										+ "]/ul/li/../../div//div[@class='pull-left layer-icon']/span"))
						.click();
				for (int j = 0; j < layerList.size(); j++) {
					layerList.get(j).click();
					clickOn(deleteBtn);
					_waitForJStoLoad();
				}
			}
		}
	}

	/**
	 * add poi on layer
	 * 
	 * @param layer
	 */
	public void addPOI(String layer) {
		getDriver().manage().window().setSize(new Dimension(1250, 768));
		selectDropDownByText(selectPOILayer, layer);
		clickOn(layerAddButn);
		clickOn(drawPolygon);
		Point point = floorImage.getLocation();
		int xcord = point.getX();
		int ycord = point.getY();
		Actions builder = new Actions(driver);
		builder.moveToElement(floorImage, xcord, ycord).click().perform();
		waitForAjaxRequestsToComplete();
		builder.moveToElement(floorImage, xcord - 10, ycord + 10).click()
				.perform();
		waitForAjaxRequestsToComplete();
		builder.moveToElement(floorImage, xcord, ycord + 20).click().perform();
		waitForAjaxRequestsToComplete();
		builder.moveToElement(floorImage, xcord + 10, ycord + 10).click()
				.perform();
		_waitForJStoLoad();
		waitAndClick(finishDrawing);
		getDriver().manage().window().maximize();
	}

	/**
	 * enter added poi information
	 * 
	 * @param name
	 * @param description
	 * @param Keywords
	 */
	public void enterPOIInfo(String name, String description, String Keywords) {
		waitForElement(poiName);
		inputText(poiName, name);
		waitForElement(poiDescription);
		inputText(poiDescription, description);
		waitForElement(poiKeywords);
		inputText(poiKeywords, Keywords);
	}

	/**
	 * click on poi save button
	 */
	public void clickOnPOISaveBtn() {
		waitAndClick(poiSaveBtn);
		_waitForJStoLoad();
	}

	/**
	 * verify added poi
	 * 
	 * @param layer
	 * @param name
	 * @param description
	 * @param Keywords
	 */
	public void verifyAddedPOI(String layer, String name, String description,
			String Keywords) {
		getDriver().navigate().refresh();
		_waitForJStoLoad();
		waitAndClick(arrowIcon);
		waitForAjaxRequestsToComplete();
		waitAndClick(selectlayerArrowIcon);
		boolean status = false;
		for (int i = 0; i < poiShapes.size(); i++) {
			List<WebElement> layerList = getDriver()
					.findElements(
							By.xpath("//li[@data-id='2']/ul/li[" + (i + 1)
									+ "]/ul/li"));
			if (layerList.size() > 0) {
				waitForAjaxRequestsToComplete();
				getDriver()
						.findElement(
								By.xpath("//li[@data-id='2']/ul/li["
										+ (i + 1)
										+ "]/ul/li/../../div//div[@class='pull-left layer-icon']/span"))
						.click();
				getDriver().findElement(
						By.xpath("//li[@data-id='2']/ul/li[" + (i + 1)
								+ "]/ul/li")).click();
				Assert.assertEquals(
						driver.findElement(By.xpath("//input[@name='name']"))
								.getAttribute("value"), name);
				Assert.assertEquals(
						driver.findElement(
								By.xpath("//textarea[@name='description']"))
								.getText(), description);
				Assert.assertEquals(
						driver.findElement(
								By.xpath("//input[@name='keywords']"))
								.getAttribute("value"), Keywords);
				status = true;
			}
		}
		Assert.assertTrue(status);
	}

	/**
	 * update added POI details
	 * 
	 * @param layer
	 * @param newName
	 * @param newDescription
	 * @param newKeywords
	 */
	public void updateAddedPOI(String layer, String newName,
			String newDescription, String newKeywords) {
		inputText(poiName, newName);
		inputText(poiDescription, newDescription);
		inputText(poiKeywords, newKeywords);
	}

	/**
	 * click on POI update button
	 */
	public void clickOnUpdateBtn() {
		waitAndClick(poiUpdateBtn);
		_waitForJStoLoad();
	}
	
	/**
	  * Add Rectangle in Map
	  * @param layer
	  */
	 public void addRectangle(String layer){
	  getDriver().manage().window().setSize(new Dimension(1250, 768));
	  selectDropDownByText(selectPOILayer, layer);
	  clickOn(layerAddButn);
	  clickOn(drawRectangle);
	  Point point = floorImage.getLocation();
	  int xcord = point.getX();
	  int ycord = point.getY();
	  Actions builder = new Actions(driver);
	  builder.moveToElement(floorImage, xcord , ycord).clickAndHold().dragAndDropBy(floorImage, xcord+100, ycord+100).build().perform();
	  waitForAjaxRequestsToComplete();
	  builder.release();
	  waitForAjaxRequestsToComplete();
	  getDriver().manage().window().maximize();
	 }
}
