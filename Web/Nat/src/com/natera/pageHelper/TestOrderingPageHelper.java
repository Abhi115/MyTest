package com.natera.pageHelper;

import org.openqa.selenium.WebDriver;

import com.natera.locator.LocatorReader;
import com.natera.util.DriverHelper;

public class TestOrderingPageHelper extends DriverHelper 
{
 
	private LocatorReader testOrderingPage;
	
	   public TestOrderingPageHelper(WebDriver driver) 
	   {
				super(driver);	
				//testOrderingPage = new LocatorReader("TestOrdering.xml");
	   }

	   public void clickOnTestOrderingLink()
		{
			String locator = testOrderingPage.getLocator("TestOrdering.TestOrderingLink");
			clickOn(locator);	
		}
	   
	   public void clickOnPrintReqLink()
	   {
				String locator = testOrderingPage.getLocator("TestOrdering.PrintReqFormLink");
				clickOn(locator);	
	   }
	   
	   public void selectSatelliteClinic()
	   {
		   String locator = testOrderingPage.getLocator("TestOrdering.ClickClinic");
		   String targetValue = testOrderingPage.getLocator("TestOrdering.SelectedClinicValue");
		   selectDropDownByValue(locator, targetValue);			
	   }
	   
	   public void selectTest()
	   {
		   String locator = testOrderingPage.getLocator("TestOrdering.ClickTest");
		   String targetValue = testOrderingPage.getLocator("TestOrdering.SelectedTestValue");
		   selectDropDownByValue(locator, targetValue);			
	   }
	   
	   public void clickOnDownLoadFormLink()
	   {		     
				String locator = testOrderingPage.getLocator("TestOrdering.DownloadForm");
				clickOn(locator);	
	   }
}
