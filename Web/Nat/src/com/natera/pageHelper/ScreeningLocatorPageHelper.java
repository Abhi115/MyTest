package com.natera.pageHelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.natera.locator.LocatorReader;
import com.natera.util.DriverHelper;

public class ScreeningLocatorPageHelper extends DriverHelper{

	private LocatorReader screeningLocator;

	public ScreeningLocatorPageHelper(WebDriver driver) {
		super(driver);	
		screeningLocator = new LocatorReader("ScreeningLocator.xml");
	}
	String locator;

	public void CheckScreeningLocatorPage()
	{  
		String demo = "//button[@id='navbar-toggle']";
		clickOnMobile(demo);
		waitForWorkArround(2000);
		locator = screeningLocator.getLocator("MenuNatera.PatientServices");
		clickOn(locator);

		locator = screeningLocator.getLocator("MenuNatera.ScreeningLocator");
		clickOn(locator);

		locator = screeningLocator.getLocator("ScrnneingLocatorPage.PageHeasder");
		String headerTxt = getText(locator);
		String targetTxt = "Help your patients find their nearest screening location";
		Assert.assertTrue(headerTxt.contains(targetTxt), "After clicking Screening Locator, it does not redirect to correct page");		   
		WebElement frameElement= getWebDriver().findElement(By.xpath("//iframe[contains(@src,'https://psc.natera.com')]"));
		getWebDriver().switchTo().frame(frameElement);		   
	}

	public void searchScreeningLocation(String location, String distance) throws InterruptedException
	{
		locator = screeningLocator.getLocator("ScrnneingLocatorPage.CityStateZip");
		Assert.assertTrue(isElementPresent(locator), "City/State Zip text field is not present.");
		clearTextField(locator);

		String zipNumber=location;
		sendKeys(locator, zipNumber);

		locator = screeningLocator.getLocator("ScrnneingLocatorPage.Distance");		   

		Select organizationAddr=new Select(getWebDriver().findElement(By.xpath(locator)));
		organizationAddr.selectByVisibleText(distance+" Miles");
		Thread.sleep(1000);
		locator = screeningLocator.getLocator("ScrnneingLocatorPage.SearchBtn");
		clickOn(locator);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		locator = screeningLocator.getLocator("ScrnneingLocatorPage.SearchResultTxt");
		String resultTxt = getText(locator);
		String targetResultTxt= "Locations near you";

		if(resultTxt.contains(targetResultTxt)){
			Assert.assertTrue(resultTxt.contains(targetResultTxt), "Unable to search Clinic.");
			locator = screeningLocator.getLocator("ScrnneingLocatorPage.TableRow");
			List<WebElement> tableRowWlement = getWebDriver().findElements(By.xpath(locator));
			int rowNumber = tableRowWlement.size();
			String tableRows= String.valueOf(rowNumber);
			if(resultTxt.contains(tableRows)){
				System.out.println("Showing correct number of clinic.");  
			}			   
		} else{			  
			System.out.println("No Clinic found near your loacetion.");
		}		   
	}
}
