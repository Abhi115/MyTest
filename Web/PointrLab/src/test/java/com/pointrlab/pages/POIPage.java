package com.pointrlab.pages;

import org.junit.Assert;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.pointrlab.datamodel.POIModel;
import com.pointrlab.selenium.framework.BasePage;

public class POIPage extends BasePage {

	public POIPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[@title='Draw a polygon']")
	private WebElement drawPolygon;
	@FindBy(xpath = "//a[@title='Draw a rectangle']")
	private WebElement drawRectangle;
	@FindBy(xpath = "//a[@title='Draw a marker']")
	private WebElement drawMarker;

	@FindBy(xpath = "//button[contains(@class,'addPOIbutton')]")
	private WebElement addBtn;

	@FindBy(xpath = "//div[@id='floor1']//img")
	private WebElement floor0Image;

	@FindBy(xpath = "//input[contains(@class,'POI-name')]")
	private WebElement poiNameField;
	@FindBy(xpath = "//div[@id='POIsidebar']//input[contains(@class,'POI-desc ')]")
	private WebElement poiDescriptionField;
	@FindBy(xpath = "//div[@id='POIsidebar']//select[contains(@class,'POI-type')]")
	private WebElement poiType;
	@FindBy(xpath = "//div[@id='POIsidebar']//div[@id='keywordDiv']/input")
	private WebElement keywordsField;
	@FindBy(xpath = "//div[@class='savePOIbuttons']/button")
	private WebElement poiSaveBtn;

	@FindBy(xpath = "//div[@id='POIsidebar']//span[@class='POI-remove']")
	private WebElement poiDeleteBtn;

	/**
	 * click on add button
	 */
	public void clickOnAddPOIBtn() {
		_waitForJStoLoad();
		clickOn(addBtn);
	}

	/**
	 * verify POI page
	 */
	public void verifyPOIPage() {
		verifyURL("POI");
	}

	/**
	 * verify tool bar options to draw pattern are present
	 */
	public void verifyPOIDrawToolBarOptions() {
		verifyElementPresent(drawPolygon);
		verifyElementPresent(drawRectangle);
		verifyElementPresent(drawMarker);
	}

	/**
	 * add poi
	 * 
	 * @param poiType
	 */
	public void drawPOI(String poiType) {
//		WebElement element = null;
//		switch (poiType) {
//		case "Polygon":
//			element = drawPolygon;
//			break;
//		case "Rectangle":
//			element = drawRectangle;
//			break;
//		case "Marker":
//			element = drawMarker;
//			break;
//		default:
//			Assert.assertNotNull(element);
//		}
//		Point point = floor0Image.getLocation();
//		int xcord = point.getX();
//		int ycord = point.getY();
//		waitAndClick(element);
//		Actions builder = new Actions(driver);
//		builder.moveToElement(floor0Image, xcord, ycord).click()
//				.moveToElement(floor0Image, xcord - 10, ycord + 10).click()
//				.moveToElement(floor0Image, xcord, ycord + 20).click()
//				.moveToElement(floor0Image, xcord + 10, ycord + 10).click()
//				.moveToElement(floor0Image, xcord, ycord).click().build()
//				.perform();
	}

	/**
	 * enter poi data
	 * 
	 * @param poiModel
	 * @throws InterruptedException
	 */
	public void enterPOIData(POIModel poiModel) throws InterruptedException {
		waitForElement(poiNameField);
		inputText(poiNameField, poiModel.getPOIName());
		waitForElement(poiDescriptionField);
		inputText(poiDescriptionField, poiModel.getPOIDescription());
		selectDropDownByText(poiType, poiModel.getPOIType());
		inputText(keywordsField, poiModel.getPOIkeywords());
		waitAndClick(poiSaveBtn);
	}

	/**
	 * delete added poi
	 */
	public void deleteAddedPOI() throws InterruptedException {
		_waitForJStoLoad();
		Point point = floor0Image.getLocation();
		int xcord = point.getX();
		int ycord = point.getY();
		Actions builder = new Actions(driver);
		builder.moveToElement(floor0Image, xcord, ycord).click().build()
				.perform();
		waitAndClick(poiDeleteBtn);
	}
}
