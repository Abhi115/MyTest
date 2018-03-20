package com.pointrlab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pointrlab.selenium.framework.BasePage;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//nav[@class='main-menu']")
	private WebElement mainMenu;

	@FindBy(xpath = "//a[@id='logOutLink']/span")
	private WebElement logOut;
	@FindBy(xpath = "//a[@href='/Configuration/']")
	private WebElement config;

	@FindBy(xpath = "//a[@href='/POI/']")
	private WebElement poi;

	@FindBy(xpath = "//a[@href='/MapDesigner/']")
	private WebElement mapDesigner;

	/**
	 * verify user logged in successfully
	 */
	public void verifyLogin() throws Exception {
		verifyElementPresent(mainMenu);
	}

	/**
	 * logout from the application
	 */
	public LoginPage logOut() {
		scrollToElement(config);
		hoverOverElementAndClick(mainMenu, logOut);
		return PageFactory.initElements(getDriver(), LoginPage.class);
	}

	/**
	 * click on POI
	 */
	public POIPage clickOnPOI() {
		clickOnMainMenuItem(mainMenu, poi);
		return PageFactory.initElements(getDriver(), POIPage.class);
	}

	/**
	 * click on Configure
	 * 
	 * @return
	 */
	public ConfigurePage clickOnConfigure() {
		clickOnMainMenuItem(mainMenu, config);
		return PageFactory.initElements(getDriver(), ConfigurePage.class);
	}

	/**
	 * click on Map Designer
	 */
	public MapDesignerPage clickOnMapDesigner() {
		clickOnMainMenuItem(mainMenu, mapDesigner);
		return PageFactory.initElements(getDriver(), MapDesignerPage.class);
	}
}
