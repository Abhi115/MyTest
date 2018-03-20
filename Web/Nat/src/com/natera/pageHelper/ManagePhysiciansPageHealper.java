package com.natera.pageHelper;


import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.natera.locator.LocatorReader;
import com.natera.util.DriverHelper;


public class ManagePhysiciansPageHealper extends DriverHelper{

	private LocatorReader managePhysicians;
	DriverHelper helper;

	public ManagePhysiciansPageHealper(WebDriver driver)
	{
		super(driver);	
		managePhysicians = new LocatorReader("ManagePhysicians.xml");				
	}

	String locator;
	String tablelocator;  
	public void verifyManagePhysiciansPage() throws InterruptedException
	{
		String demo = "//button[@id='navbar-toggle']";
		clickOnMobile(demo);
		waitForWorkArround(2000);
		locator = managePhysicians.getLocator("MenuNatera.CourierServices");
		clickOn(locator);

		Thread.sleep(1000);
		locator = managePhysicians.getLocator("MenuNatera.ManagePhysicians");
		clickOn(locator);

		locator = managePhysicians.getLocator("ManagePhysiciansPage.PageHeader");
		String headerTxt= getText(locator);
		String targetTxt= "Manage Providers";

		Assert.assertTrue(headerTxt.equalsIgnoreCase(targetTxt), "Afetr clicking Manage Physicians, it does not redirect to correct page.");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		tablelocator= managePhysicians.getLocator("ManagePhysiciansPage.TableRows");


	}

	public void searchPhysician(String SearchTarget)
	{
		String physicianName;
		physicianName = SearchTarget;
		locator = managePhysicians.getLocator("ManagePhysiciansPage.SearchBox");
		sendKeys(locator, physicianName);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		tablelocator= managePhysicians.getLocator("ManagePhysiciansPage.TableRows");
		int rowCount = TableRowCount(tablelocator);


		WebElement resultElement= getWebDriver().findElement(By.xpath("//table[@id='physician-admin-table']/tbody/tr[1]/td"));
		resultTxt = resultElement.getText();

		if(rowCount == 1 && resultTxt.contains("No matching records found"))
		{
			resultElement= getWebDriver().findElement(By.xpath("//table[@id='physician-admin-table']/tbody/tr[1]/td"));
			Assert.assertTrue(resultTxt.contains("No matching records found"), "Unable to get No matching record message");
		}
		else
		{
			for(int i=1; i<=rowCount; i++)
			{
				resultElement= getWebDriver().findElement(By.xpath("//table[@id='physician-admin-table']/tbody/tr["+i+"]/td[1]"));
				resultTxt = resultElement.getText();
				Assert.assertTrue(resultTxt.contains(SearchTarget), "Not found matching doctor.");
			}

		}
	}
	String resultTxt;
	WebElement editButton;
	public void editButton(String searchTarget, String actionTodo, String newValue) throws InterruptedException
	{
		searchPhysician(searchTarget);
		editButton= getWebDriver().findElement(By.xpath("//a[contains(@href,'/admin/physicians/23/edit')]"));
		editButton.click();		      

		waitForWorkArround(5000);

		//locator = managePhysicians.getLocator("ManagePhysiciansPage.PopupWindow.Header");

		// WaitForElementPresent(locator, 2000);

		//resultTxt = editButton.getText();
		// Assert.assertTrue(getText(locator).contains("Edit Physician"), "Unable to open Edit popup window after clicking Edit button.");

		if(actionTodo == "Cancel"){			   
			//locator = managePhysicians.getLocator("ManagePhysiciansPage.PopupWindow.Cancel");
			//clickOn(locator);
			//WaitForElementNotPresent(locator, 2000); 

		} else
		{	   
			locator = managePhysicians.getLocator("ManagePhysiciansPage.PopupWindow.Fields.panorama_low");
			clearTextField(locator);
			//System.out.println("My Value--" + locator);
			sendKeys(locator, newValue);

			locator = managePhysicians.getLocator("ManagePhysiciansPage.PopupWindow.Fields.horizon_negative");
			clearTextField(locator);
			sendKeys(locator, newValue);

			locator = managePhysicians.getLocator("ManagePhysiciansPage.PopupWindow.Fields.panorama_high");
			clearTextField(locator);
			sendKeys(locator, newValue);

			locator = managePhysicians.getLocator("ManagePhysiciansPage.PopupWindow.Fields.horizon_carrier");
			clearTextField(locator);
			sendKeys(locator, newValue);

			locator = managePhysicians.getLocator("ManagePhysiciansPage.PopupWindow.Fields.view_test");
			clearTextField(locator);
			sendKeys(locator, newValue);

			locator = managePhysicians.getLocator("ManagePhysiciansPage.PopupWindow.Fields.test_canceled");
			clearTextField(locator);
			sendKeys(locator, newValue);	   

			locator = managePhysicians.getLocator("ManagePhysiciansPage.PopupWindow.SaveChanges");
			clickOn(locator);			   

			locator = managePhysicians.getLocator("ManagePhysiciansPage.ChangesDoneMessage");
			//WaitForElementNotPresent(locator, 2000);

			resultTxt= getText(locator);
			Assert.assertTrue(resultTxt.contains("Changes were saved successfully"), "Unable to perform save changes after clicking Edit button.");


			//Verification of changes made
			// editButton= getWebDriver().findElement(By.xpath("//table[@id='physician-admin-table']/tbody/tr[1]/td[3]/div/button"));
			//editButton.click();

			// locator = managePhysicians.getLocator("ManagePhysiciansPage.PopupWindow.Header");			   
			//WaitForElementPresent(locator, 2000);
			// Assert.assertTrue(getText(locator).contains("Edit Physician"), "Unable to open Edit popup window after clicking Edit button.");

			//Verify slider values
			//Thread.sleep(6000);
			locator = managePhysicians.getLocator("ManagePhysiciansPage.Slider.PanoramaLowRiskSlider");			   

			String currentValue = getAttribute(locator, "style");	
			//System.out.println("my  value is: " + currentValue);			 
			Thread.sleep(10000);
			Assert.assertTrue(currentValue.contains("50"), "Panorama Low Risk value is not changed.");

			locator = managePhysicians.getLocator("ManagePhysiciansPage.Slider.HorizonNegativeSlider");
			currentValue = getAttribute(locator, "style");
			Assert.assertTrue(currentValue.contains("50"), "Horizon Negative value is not changed.");

			locator = managePhysicians.getLocator("ManagePhysiciansPage.Slider.PanoramaHighRiskSlider");
			currentValue = getAttribute(locator, "style");
			Assert.assertTrue(currentValue.contains("50"), "Panorama High Risk value is not changed.");	   


			locator = managePhysicians.getLocator("ManagePhysiciansPage.Slider.HorizonPositiveSlider");
			currentValue = getAttribute(locator, "style");
			Assert.assertTrue(currentValue.contains("50"), "Horizon Positive Slider value is not changed.");

			locator = managePhysicians.getLocator("ManagePhysiciansPage.Slider.NoResultSlider");
			currentValue = getAttribute(locator, "style");
			Assert.assertTrue(currentValue.contains("50"), "No Result value is not changed.");

			locator = managePhysicians.getLocator("ManagePhysiciansPage.Slider.TestCanceledSlider");
			currentValue = getAttribute(locator, "style");
			Assert.assertTrue(currentValue.contains("50"), "Test Canceled value is not changed.");			   
		}	 		   
	}

	//Manage Physicians Reset Feature
	public void resetButton(String searchTarget, String actionTodo, String newValue) throws InterruptedException
	{
		//editButton(searchTarget, actionTodo, newValue);

		//locator = managePhysicians.getLocator("ManagePhysiciansPage.PopupWindow.Cancel");
		//clickOn(locator);


		// editButton= getWebDriver().findElement(By.xpath("//table[@id='physician-admin-table']/tbody/tr[1]/td[3]/div/button"));
		//editButton.click();

		// locator = managePhysicians.getLocator("ManagePhysiciansPage.PopupWindow.Header");			   
		//WaitForElementPresent(locator, 2000);
		// Assert.assertTrue(getText(locator).contains("Edit Physician"), "Unable to open Edit popup window after clicking Edit button.");

		searchPhysician(searchTarget);
		editButton= getWebDriver().findElement(By.xpath("//a[contains(@href,'/admin/physicians/23/edit')]"));
		editButton.click();		      

		waitForWorkArround(5000);
		
		locator = managePhysicians.getLocator("ManagePhysiciansPage.PopupWindow.ResetBtn");
		clickOn(locator);
		Thread.sleep(6000);
		scrollToElement(locator);
		//Verify slider values
		locator = managePhysicians.getLocator("ManagePhysiciansPage.PopupWindow.SaveChanges");
		clickOn(locator);		   

	}


	//**********************
	public int TableRowCount(String tblLocator)
	{
		List<WebElement> columnElements = getWebDriver().findElements(By.xpath(tblLocator));
		int rowNumber = columnElements.size();
		return rowNumber;				
	}



	public void ContactSetting() throws InterruptedException 
	{
		// TODO Auto-generated method stub
		driver.navigate().refresh();
		locator = managePhysicians.getLocator("ManagePhysiciansPage.ContectSetting");
		clickOn(locator);
		// Click on the add contacts
		String Addlocator = managePhysicians.getLocator("ManagePhysiciansPage.AddContect");
		clickOn(Addlocator);

		//Enter the data in Textbox
		String locatotFirstName = managePhysicians.getLocator("ManagePhysiciansPage.FirstName");
		sendKeys(locatotFirstName, "Akhilesh");

		String locatotLastName = managePhysicians.getLocator("ManagePhysiciansPage.LastName");
		sendKeys(locatotLastName, "Kumar");

		String locatotEmail = managePhysicians.getLocator("ManagePhysiciansPage.Email");
		sendKeys(locatotEmail, "akhileshkq@360logica.com");

		String locatorPhon = managePhysicians.getLocator("ManagePhysiciansPage.PhonNumber");
		sendKeys(locatorPhon, "9541414232");

		// Save data 
		Thread.sleep(2000);
		String SaveChange = managePhysicians.getLocator("ManagePhysiciansPage.SaveChanges");
		clickOn(SaveChange);				 

		String Removetxt = managePhysicians.getLocator("ManagePhysiciansPage.Removebtn");
		clickOn(Removetxt);

		Thread.sleep(2000);
		String CaseRelese = managePhysicians.getLocator("ManagePhysiciansPage.CaseReleseSetting");
		clickOn(CaseRelese);
	}


}
