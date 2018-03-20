package com.natera.pageHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.natera.locator.LocatorReader;
import com.natera.util.DriverHelper;

public class PrintReqFormPageHelper extends DriverHelper{
	
	private LocatorReader printReqForm;
	
	 public PrintReqFormPageHelper(WebDriver driver) {
		   super(driver);	
		   printReqForm = new LocatorReader("PrintReqForm.xml");
	   }
	 
	   String locator;
	   
	   public void CheckPrintReqFormPage()
	   {
		   String demo = "//button[@id='navbar-toggle']";
			clickOnMobile(demo);
			waitForWorkArround(2000);
			
		   locator = printReqForm.getLocator("MenuNatera.TestOrdering");
		   clickOn(locator);
		   
		   try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		   locator = printReqForm.getLocator("MenuNatera.SubMenuTestOrdering.PrintReqForm");
		   clickOn(locator);
		   
		   locator = printReqForm.getLocator("PrintReqFormPage.UseAsHeader");
		   
		   String useHeaderTxt= getText(locator);
		   
		   String targetTxt= printReqForm.getLocator("PageData.UseAsHeaderTxt");
		   
		   Assert.assertTrue(useHeaderTxt.contains(targetTxt), "After clicking 'Print Req Form' page, it is not navigated to correct page");
		   
	   }
	   
	   public void DownloadPrintForm(String commandTxt)
	   {
		   String keyword= commandTxt;
		   locator = printReqForm.getLocator("PrintReqFormPage.SelectClinic");
		   Select clinicName=new Select(getWebDriver().findElement(By.xpath(locator)));
		   clinicName.selectByVisibleText("Reproductive Care Center - Utah");
		   
		   try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   locator = printReqForm.getLocator("PrintReqFormPage.SelectAddrs");
		   Select clinicAddrs=new Select(getWebDriver().findElement(By.xpath(locator)));
		   clinicAddrs.selectByVisibleText("Clearfield/Layton Office 1725 East 1450 South Suite 300 Clearfield, UT, 84015");
		   
		   locator = printReqForm.getLocator("PrintReqFormPage.SelectTest");
		   Select testType=new Select(getWebDriver().findElement(By.xpath(locator)));
		   testType.selectByVisibleText("Panorama or Horizon");
		   
		   locator = printReqForm.getLocator("PrintReqFormPage.FormPDFLoad");
		   WaitForElementPresent(locator, 5);
		   
		   if(keyword == "Download")
		   {
			 //Download Form Button 
			  locator = printReqForm.getLocator("PrintReqFormPage.DownloadFormBtn");
			  clickOn(locator);
			   
			 //Download button
			   //locator = printReqForm.getLocator("PrintReqFormPage.DownloadBtn");
			   //clickOn(locator);
		   
			  
			  //**************This control has been removed*******************************
			  
			   //locator = printReqForm.getLocator("PrintReqFormPage.DownloadIcon");
			   //clickOn(locator);
		   }
		   
		   if(keyword == "Print")
		   {
			 //Print Form Button
			   locator = printReqForm.getLocator("PrintReqFormPage.PrintFormBtn");
			   clickOn(locator);
			   
			  
		
			   
			 //Print Button  
			   //locator = printReqForm.getLocator("PrintReqFormPage.PrintBtn");
			  // clickOn(locator);
		   		   
		   }
	   }

}
