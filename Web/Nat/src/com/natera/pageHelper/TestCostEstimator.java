package com.natera.pageHelper;



import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;

import com.natera.locator.LocatorReader;
import com.natera.util.DriverHelper;
import com.natera.util.PropertyReader;

public class TestCostEstimator extends DriverHelper {

	private LocatorReader testcostEstimator;
	DriverHelper driverhelper;
	private PropertyReader propertyReader;
	public TestCostEstimator(WebDriver driver)
	{
		super(driver);
		// TODO Auto-generated constructor stub
		propertyReader = new PropertyReader();
		testcostEstimator = new LocatorReader("TestCostEstimator.xml");
	}

	String locator;	

	public void CheckTestCostEstimatorLocatorPage() throws InterruptedException 
	{
		String demo = "//button[@id='navbar-toggle']";
		clickOnMobile(demo);
		waitForWorkArround(2000);

		locator = testcostEstimator.getLocator("MenuNatera.PatientServices");
		clickOn(locator);

		Thread.sleep(1000);
		String  testCostlocator = testcostEstimator.getLocator("MenuNatera.TestCostEstimator");
		//(Keys.PAGE_DOWN).perform();
		clickOn(testCostlocator);

		locator = testcostEstimator.getLocator("TestCostEstimatorPage.PageHeasder");
		String headerTxt = getText(testCostlocator);		 

		String targetTxt = "Test Cost Estimator";
		Assert.assertTrue(headerTxt.contains(targetTxt), "After clicking Screening Locator, it does not redirect to correct page");	

	}


	String Inputdata;
	public void EstimationCost() throws InterruptedException 
	{
		// TODO Auto-generated method stub

		Thread.sleep(5000);
		WebElement payment= driver.findElement(By.className("natera_services"));
		driver.switchTo().frame(payment);
		Thread.sleep(2000);
		scrollingdown();
		
		if(!propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			String Mylocator = testcostEstimator.getLocator("TestCostEstimatorPage.SelectPayment");
			clickOn(Mylocator);
			Thread.sleep(1000);	   	  

			String  locator = testcostEstimator.getLocator("TestCostEstimatorPage.SelectDropdownMenu");
			clickOn(locator);

			locator = testcostEstimator.getLocator("TestCostEstimatorPage.TestSections");
			clickOn(locator); 	  

			String Drop = testcostEstimator.getLocator("TestCostEstimatorPage.SelectDwnTest");
			clickOn(Drop);	
		}
		else
		{
			scrollToElement("//h3[text()='Estimate Cost']");
			
			String Mylocator = testcostEstimator.getLocator("TestCostEstimatorPage.MobileSelectPayment");
			waitForWorkArround(2000);
			selectDropDownByVisibleText(Mylocator, "Self Pay");	  

			String  locator = testcostEstimator.getLocator("TestCostEstimatorPage.MobileTestSections");
			selectDropDownByVisibleText(locator, "Panorama");
		}

		locator = testcostEstimator.getLocator("TestCostEstimatorPage.SubmitBtn");
		clickOn(locator);	    	   	 
	}

	// Submit Button click without Selecting Data

	public void ClickBtn() throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement payment= driver.findElement(By.className("natera_services"));
		driver.switchTo().frame(payment);
		locator = testcostEstimator.getLocator("TestCostEstimatorPage.SubmitBtn");
		clickOn(locator);	
	}

	// For New Page Verification In Test Cost Estimator

	public void ForNewPage()
	{
		locator = testcostEstimator.getLocator("TestCostNextPage.PageHeader");
		String headerTxt = getText(locator);		  

		String targetTxt = "Please call Natera to inquire about the self pay rate for";
		Assert.assertTrue(headerTxt.contains(targetTxt), "After clicking Screening Locator, it does not redirect to correct page"); 
	}

	public void RequestScreenTest() throws InterruptedException
	{	 
		//Thread.sleep(5000);

		locator = testcostEstimator.getLocator("TestCostNextPage.PageHeader");
		String headerTxt = getText(locator);		  

		String targetTxt = "Please call Natera to inquire about the self pay rate for";
		Assert.assertTrue(headerTxt.contains(targetTxt), "After clicking Screening Locator, it does not redirect to correct page"); 

		Thread.sleep(2000); 
		String  Reqlocator = testcostEstimator.getLocator("TestCostNextPage.RequestBtn");
		clickOn(Reqlocator);
	}


}
