package com.natera.pageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.natera.locator.LocatorReader;
import com.natera.util.DriverHelper;
import com.natera.util.PropertyReader;

public class EducationCenterPageHelper extends DriverHelper  
{

	private LocatorReader educationCenter;
	private PropertyReader propertyReader;

	public EducationCenterPageHelper(WebDriver driver)
	{
		super(driver);	
		educationCenter = new LocatorReader("EducationCenter.xml");	
		propertyReader=new PropertyReader();
	}

	String locator;
	String locatoriframe;

	public void CheckEducationCenterPage()
	{
		String demo = "//button[@id='navbar-toggle']";
		clickOnMobile(demo);
		waitForWorkArround(2000);

		locator = educationCenter.getLocator("MenuNatera.EducationCenter");
		clickOn(locator);		    
		locatoriframe = educationCenter.getLocator("EducationHeader.iframe");		   
		//switchtoframe(locatoriframe);
		clickOn(locatoriframe);

		locator = educationCenter.getLocator("EducationHeader.ContentHeader");
		String headerTxt= getText(locator);
		String targetTxt= "Natera Test Information";
		Assert.assertTrue(headerTxt.equalsIgnoreCase(targetTxt), "After clicking Education Center, it does not redirect to correct page.");

	}
	public void CheckNateraInformation() 
	{

		String locatorPanorama = educationCenter.getLocator("informationTest.Panorama");	 
		clickOn(locatorPanorama);		 
		try 
		{
			Thread.sleep(1000);				
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		String locatorAnora = educationCenter.getLocator("informationTest.Anora");	 
		clickOn(locatorAnora);		
		try 
		{
			Thread.sleep(10000);				
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		String locatorHorizon = educationCenter.getLocator("informationTest.Horizon");	 
		clickOn(locatorHorizon);

		locator= educationCenter.getLocator("informationTest.SelectPanel");
		Select HarizonDropDown=new Select(getWebDriver().findElement(By.xpath(locator)));
		HarizonDropDown.selectByVisibleText("4 (SMA, CF, Fragile X, DMD)");
		//WebElement  element = driver.findElement(By.name("4 (SMA, CF, Fragile X, DMD)"));
		//HarizonDropDown.deselectByValue(element);
		//Assert.assertTrue(getText(locator).contains(HarizonDropDown), "Unable to select value in nubler of cases per page");


		String locatorButton =  educationCenter.getLocator("informationTest.ClickButton");
		clickOn(locatorButton);
	}

	public void checkHorizon(String droplist)
	{
		locator= educationCenter.getLocator("informationTest.SelectPanel");
		Select HarizonDropDown=new Select(getWebDriver().findElement(By.xpath(locator)));
		HarizonDropDown.selectByVisibleText("droplist");
		if(droplist=="4 (SMA, CF, Fragile X, DMD)")
		{
			String locatorButton =  educationCenter.getLocator("informationTest.ClickButton");
			clickOn(locatorButton);
			try 
			{
				Thread.sleep(10000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			// locator= educationCenter.getLocator("EducationCenterPage.Horizon.ForPatientsSubLinkPageHeader");
			//WaitForElementPresent(locator, 10);
			// String actualTxt= getText(locator);
			// Assert.assertTrue(actualTxt.contains("Horizon For Patients Flipbooks"), "Unable to open Horizon For Patients page");
		}
		else if(droplist=="27 (Pan-ethnic standard)")
		{
			String locatorButton =  educationCenter.getLocator("informationTest.ClickButton");
			clickOn(locatorButton);
		}
		else if(droplist=="106 (Comprehensive Jewish)")
		{
			String locatorButton =  educationCenter.getLocator("informationTest.ClickButton");
			clickOn(locatorButton);
		}

		else if(droplist=="274 (Pan-ethnic Extended)")
		{
			String locatorButton =  educationCenter.getLocator("informationTest.ClickButton");
			clickOn(locatorButton);
		}
	}

	public void CheckResourcesCenterPage() throws InterruptedException
	{
		String demo = "//button[@id='navbar-toggle']";
		clickOnMobile(demo);
		waitForWorkArround(2000);
		locator = educationCenter.getLocator("MenuNatera.EducationCenter");
		clickOn(locator);
		waitForWorkArround(2000);

		try
		{
			String locatorRes = educationCenter.getLocator("MenuNatera.Resources");
			clickOn(locatorRes);
		}
		catch(ElementNotVisibleException ex)
		{
			clickOn(locator);
			String locatorRes = educationCenter.getLocator("MenuNatera.Resources");
			clickOn(locatorRes);
		}
		Thread.sleep(1000);		    
		WebElement el = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(el);
		Thread.sleep(10000);

		String ResourceVerifyLocator = educationCenter.getLocator("MenuNatera.ContentHeader");	
		String headerTxt=getText(ResourceVerifyLocator);		   
		String targetTxt="Latest Content";
		Thread.sleep(1000);
		Assert.assertTrue(headerTxt.equalsIgnoreCase(targetTxt), "After clicking Education Center, it does not redirect to correct page.");
	}



	public void nateraSubLinkVerification(String linkTxt) throws InterruptedException
	{		  
		if(linkTxt== "Billing")
		{
			if(propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
			{
				locator= educationCenter.getLocator("EducationCenterPage.Natera.MobileToggle");
				clickOn(locator);
				waitForWorkArround(3000);
				locator= educationCenter.getLocator("EducationCenterPage.Natera.MobileNateraLink");
				clickViaJavaScript(locator);
				waitForWorkArround(2000);
				locator= educationCenter.getLocator("EducationCenterPage.Natera.MobileBillingLink");
				clickViaJavaScript(locator);
			}
			else
			{
				locator= educationCenter.getLocator("EducationCenterPage.Natera.NateraLink");
				clickOn(locator);  

				locator= educationCenter.getLocator("EducationCenterPage.Natera.BillingLink");
				clickOn(locator);
			}
			waitForWorkArround(5000);

			locator= educationCenter.getLocator("EducationCenterPage.Natera.BillingPageHeader");
			WaitForElementPresent(locator, 20);
			String actualTxt= getText(locator);
			Assert.assertTrue(actualTxt.contains("BILLING"), "Unable to open billing page");

		}

	}

	public void panoramaSubLinkVerification(String linkTxt)
	{
		if(propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			locator= educationCenter.getLocator("EducationCenterPage.Natera.MobileToggle");
			clickOn(locator);
			locator=educationCenter.getLocator("EducationCenterPage.Panorama.MobilePanoramaLink");
			clickViaJavaScript(locator);

			if(linkTxt== "PatientBrochures")
			{			   	   
				locator= educationCenter.getLocator("EducationCenterPage.Panorama.MobilePatientBrochuresSubLink");
				clickViaJavaScript(locator);
				waitForWorkArround(10000);
				locator= educationCenter.getLocator("EducationCenterPage.Panorama.PatientBrochuresSubLinkPageHeader");
				WaitForElementPresent(locator, 10);
				String actualTxt= getText(locator);			   
				Assert.assertTrue(actualTxt.contains("Patient Brochures"), "Unable to open Patient Brochures page");
			}
			else
			{
				locator= educationCenter.getLocator("EducationCenterPage.Panorama.MobileFactSheetsMoreSubLink");
				clickViaJavaScript(locator);
				waitForWorkArround(10000);
				locator= educationCenter.getLocator("EducationCenterPage.Panorama.FactSheetsMoreSubLinkPageHeader");
				WaitForElementPresent(locator, 10);
				String actualTxt= getText(locator);
				Assert.assertTrue(actualTxt.contains("Panorama Fact Sheets & More"), "Unable to open Fact Sheets More page");
			}
		}
		else
		{
			locator= educationCenter.getLocator("EducationCenterPage.Panorama.PanoramaLink");
			clickOn(locator);

			if(linkTxt== "PatientBrochures")
			{			   	   
				locator= educationCenter.getLocator("EducationCenterPage.Panorama.PatientBrochuresSubLink");
				clickOn(locator);
				waitForWorkArround(10000);
				locator= educationCenter.getLocator("EducationCenterPage.Panorama.PatientBrochuresSubLinkPageHeader");
				WaitForElementPresent(locator, 10);
				String actualTxt= getText(locator);			   
				Assert.assertTrue(actualTxt.contains("Patient Brochures"), "Unable to open Patient Brochures page");
			}
			else
			{
				locator= educationCenter.getLocator("EducationCenterPage.Panorama.FactSheetsMoreSubLink");
				clickOn(locator);	
				waitForWorkArround(10000);
				locator= educationCenter.getLocator("EducationCenterPage.Panorama.FactSheetsMoreSubLinkPageHeader");
				WaitForElementPresent(locator, 10);
				String actualTxt= getText(locator);
				Assert.assertTrue(actualTxt.contains("Panorama Fact Sheets & More"), "Unable to open Fact Sheets More page");
			}
		}
	}

	public void horizonSubLinkVerification(String linkTxt)
	{
		if(propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			locator= educationCenter.getLocator("EducationCenterPage.Natera.MobileToggle");
			clickOn(locator);
			
			locator= educationCenter.getLocator("EducationCenterPage.Horizon.MobileHorizonLink");
			clickViaJavaScript(locator);

			if(linkTxt== "ForPatients")
			{			   	   
				locator= educationCenter.getLocator("EducationCenterPage.Horizon.MobileForPatientsSubLink");
				clickViaJavaScript(locator);
				waitForWorkArround(10000);
				locator= educationCenter.getLocator("EducationCenterPage.Horizon.ForPatientsSubLinkPageHeader");
				WaitForElementPresent(locator, 10);
				String actualTxt= getText(locator);
				Assert.assertTrue(actualTxt.contains("Horizon For Patients Flipbooks"), "Unable to open Horizon For Patients page");
			}
			else
				if(linkTxt== "Post-Test")
				{
					locator= educationCenter.getLocator("EducationCenterPage.Horizon.MobilePost-TestSubLink");
					clickViaJavaScript(locator);
					waitForWorkArround(10000);
					locator= educationCenter.getLocator("EducationCenterPage.Horizon.Post-TestSubLinkPageHeader");
					WaitForElementPresent(locator, 10);
					String actualTxt= getText(locator);
					Assert.assertTrue(actualTxt.contains("Post-test"), "Unable to open Post-test counseling page");
				}
				else
				{
					locator= educationCenter.getLocator("EducationCenterPage.Horizon.MobileFactSheetsMoreSubLink");
					clickViaJavaScript(locator);	
					waitForWorkArround(10000);
					locator= educationCenter.getLocator("EducationCenterPage.Horizon.FactSheetsMoreSubLinkPageHeader");
					WaitForElementPresent(locator, 10);
					String actualTxt= getText(locator);					   
					Assert.assertTrue(actualTxt.contains("Horizon Fact Sheets & More"), "Unable to open Horizon Fact Sheets & More page");
				}

		}
		else
		{
			locator= educationCenter.getLocator("EducationCenterPage.Horizon.HorizonLink");
			clickOn(locator);

			if(linkTxt== "ForPatients")
			{			   	   
				locator= educationCenter.getLocator("EducationCenterPage.Horizon.ForPatientsSubLink");
				clickOn(locator);
				waitForWorkArround(10000);
				locator= educationCenter.getLocator("EducationCenterPage.Horizon.ForPatientsSubLinkPageHeader");
				WaitForElementPresent(locator, 10);
				String actualTxt= getText(locator);
				Assert.assertTrue(actualTxt.contains("Horizon For Patients Flipbooks"), "Unable to open Horizon For Patients page");
			}
			else
				if(linkTxt== "Post-Test")
				{
					locator= educationCenter.getLocator("EducationCenterPage.Horizon.Post-TestSubLink");
					clickOn(locator);
					waitForWorkArround(10000);
					locator= educationCenter.getLocator("EducationCenterPage.Horizon.Post-TestSubLinkPageHeader");
					WaitForElementPresent(locator, 10);
					String actualTxt= getText(locator);
					Assert.assertTrue(actualTxt.contains("Post-test"), "Unable to open Post-test counseling page");
				}
				else
				{
					locator= educationCenter.getLocator("EducationCenterPage.Horizon.FactSheetsMoreSubLink");
					clickOn(locator);	
					waitForWorkArround(10000);
					locator= educationCenter.getLocator("EducationCenterPage.Horizon.FactSheetsMoreSubLinkPageHeader");
					WaitForElementPresent(locator, 10);
					String actualTxt= getText(locator);					   
					Assert.assertTrue(actualTxt.contains("Horizon Fact Sheets & More"), "Unable to open Horizon Fact Sheets & More page");
				}
		}
	}


	public void anoraSubLinkVerification(String linkTxt)
	{
		if(propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			locator= educationCenter.getLocator("EducationCenterPage.Natera.MobileToggle");
			clickOn(locator);

			locator= educationCenter.getLocator("EducationCenterPage.Anora.MobileAnoraLink");
			clickViaJavaScript(locator);

			if(linkTxt== "PatientSupplements")
			{			   	   
				locator= educationCenter.getLocator("EducationCenterPage.Anora.MobilePatientSupplementsSubLink");
				clickViaJavaScript(locator);	
				waitForWorkArround(10000);
				locator= educationCenter.getLocator("EducationCenterPage.Anora.PatientSupplementsSubLinkPageHeader");
				WaitForElementPresent(locator, 20);
				String actualTxt= getText(locator);			   
				Assert.assertTrue(actualTxt.contains("Anora Patient Supplements"), "Unable to open Anora Patient Supplements page");
			}
			else
			{
				locator= educationCenter.getLocator("EducationCenterPage.Anora.MobileFactSheetsMoreSubLink");
				clickViaJavaScript(locator);
				waitForWorkArround(10000);
				locator= educationCenter.getLocator("EducationCenterPage.Anora.FactSheetsMoreSubLinkPageHeader");
				WaitForElementPresent(locator, 20);
				String actualTxt= getText(locator);					  
				Assert.assertTrue(actualTxt.contains("Anora Fact Sheets & More"), "Unable to open Anora Fact Sheets & More page");
			}
		}
		else
		{
			locator= educationCenter.getLocator("EducationCenterPage.Anora.AnoraLink");
			clickOn(locator);

			if(linkTxt== "PatientSupplements")
			{			   	   
				locator= educationCenter.getLocator("EducationCenterPage.Anora.PatientSupplementsSubLink");
				clickOn(locator);	
				waitForWorkArround(10000);
				locator= educationCenter.getLocator("EducationCenterPage.Anora.PatientSupplementsSubLinkPageHeader");
				WaitForElementPresent(locator, 20);
				String actualTxt= getText(locator);			   
				Assert.assertTrue(actualTxt.contains("Anora Patient Supplements"), "Unable to open Anora Patient Supplements page");
			}
			else
			{
				locator= educationCenter.getLocator("EducationCenterPage.Anora.FactSheetsMoreSubLink");
				clickOn(locator);
				waitForWorkArround(10000);
				locator= educationCenter.getLocator("EducationCenterPage.Anora.FactSheetsMoreSubLinkPageHeader");
				WaitForElementPresent(locator, 20);
				String actualTxt= getText(locator);					  
				Assert.assertTrue(actualTxt.contains("Anora Fact Sheets & More"), "Unable to open Anora Fact Sheets & More page");
			}		
		}
	}

	public void spectrumSubLinkVerification(String linkTxt)
	{
		if(propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			locator= educationCenter.getLocator("EducationCenterPage.Natera.MobileToggle");
			clickOn(locator);
			
			locator= educationCenter.getLocator("EducationCenterPage.Spectrum.MobileSpectrumLink");
			clickViaJavaScript(locator);		   
			if(linkTxt== "ForPatients")
			{			   	   
				locator= educationCenter.getLocator("EducationCenterPage.Spectrum.MobileForPatientsSubLink");
				mouseOver(locator);
				clickViaJavaScript(locator);
				waitForWorkArround(10000);
				locator= educationCenter.getLocator("EducationCenterPage.Spectrum.ForPatientsSubLinkPageHeader");
				WaitForElementPresent(locator, 20);
				waitForWorkArround(5000);
				String actualTxt= getText(locator);			   
				Assert.assertTrue(actualTxt.contains("Spectrum For Patients"), "Unable to open Spectrum For Patients page");
			}
			else
			{
				locator= educationCenter.getLocator("EducationCenterPage.Spectrum.MobileForProviderSubLink");
				mouseOver(locator);
				clickViaJavaScript(locator);

				waitForWorkArround(10000);
				locator= educationCenter.getLocator("EducationCenterPage.Spectrum.ForProviderSubLinkPageHeader");
				WaitForElementPresent(locator, 20);
				String actualTxt= getText(locator);					   
				Assert.assertTrue(actualTxt.contains("Spectrum For Providers"), "Unable to open Spectrum For Providers page");
			}
		}
		else
		{
			locator= educationCenter.getLocator("EducationCenterPage.Spectrum.SpectrumLink");
			clickOn(locator);		   
			if(linkTxt== "ForPatients")
			{			   	   
				locator= educationCenter.getLocator("EducationCenterPage.Spectrum.ForPatientsSubLink");
				mouseOver(locator);
				clickOn(locator);
				waitForWorkArround(10000);
				locator= educationCenter.getLocator("EducationCenterPage.Spectrum.ForPatientsSubLinkPageHeader");
				WaitForElementPresent(locator, 20);
				waitForWorkArround(5000);
				String actualTxt= getText(locator);			   
				Assert.assertTrue(actualTxt.contains("Spectrum For Patients"), "Unable to open Spectrum For Patients page");
			}
			else
			{
				locator= educationCenter.getLocator("EducationCenterPage.Spectrum.ForProviderSubLink");
				mouseOver(locator);
				clickOn(locator);

				waitForWorkArround(10000);
				locator= educationCenter.getLocator("EducationCenterPage.Spectrum.ForProviderSubLinkPageHeader");
				WaitForElementPresent(locator, 20);
				String actualTxt= getText(locator);					   
				Assert.assertTrue(actualTxt.contains("Spectrum For Providers"), "Unable to open Spectrum For Providers page");
			}
		}
	}



}
