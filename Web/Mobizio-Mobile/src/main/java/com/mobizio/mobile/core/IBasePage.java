package com.mobizio.mobile.core;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileElement;

public interface IBasePage {

	public void clickOn(By by);

	WebElement find(By by);

	WebElement click(By by);

	WebElement clear(By by);

	WebElement setText(By by, String text);

	WebElement appendText(By by, String text);

	boolean isElementPresent(String locator);

	boolean isElementPresent(By by);

	void scrollFormCenter(String direction);
	
	void moveElement(By source, By target);

	String getAttribute(By by, String attribute);

	void TouchScreen();

	List<MobileElement> getMobileElements(By by);

	By ByLocator(String locator);

	String getText(By by);

	void hideKeyboard();

	WebElement click(WebElement element);

	void waitForElementPresent(By by);

	void waitForElementNotPresent(String locator);

	void waitForElementClickable(By by);

}
