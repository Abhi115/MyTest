package com.natera.pageHelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.natera.locator.LocatorReader;
import com.natera.util.DriverHelper;

public class ManageMyClinicPageHelper extends DriverHelper{

	private LocatorReader manageMyClinic;

	public ManageMyClinicPageHelper(WebDriver driver) {
		super(driver);	
		manageMyClinic = new LocatorReader("ManageMyClinic.xml");
	}

	String locator;
	String ClinicServices;
	String ManageClinic;

	public void checkManageMyClinicPage() throws InterruptedException
	{
		String demo = "//button[@id='navbar-toggle']";
		clickOnMobile(demo);
		waitForWorkArround(2000);

		ClinicServices = manageMyClinic.getLocator("MenuNatera.CourierServices");		   
		clickOn(ClinicServices);

		Thread.sleep(1000);
		ManageClinic = manageMyClinic.getLocator("MenuNatera.ManageMyClinic");		  
		clickOn(ManageClinic);	

		locator = manageMyClinic.getLocator("MenuNatera.HeaderLocator");		  
		String HeaderTxt = getText(locator);		   
		String targetTxt= "Manage Clinics";

		Assert.assertTrue(HeaderTxt.contains(targetTxt), "After clicking Manage My Clinic, it does not redirect to correct page.");

	}

	public void switchToClinic() throws InterruptedException
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int rowCount= TableRowCount();
		String ResultTxt=null;
		for(int i=1; i<=rowCount; i++)
		{
			ResultTxt= getWebDriver().findElement(By.xpath("//table[@id='clinic-admin-table']/tbody/tr["+i+"]/td[1]")).getText();


			if(ResultTxt.contains("Reproductive Care Center - Utah"))
			{
				getWebDriver().findElement(By.xpath("//table[@id='clinic-admin-table']/tbody/tr["+i+"]/td[4]/div/a[1]")).click();
				System.out.println("My Result " + ResultTxt );
				break;
			}
		}
		String demo = "//button[@id='navbar-toggle']";
		clickOnMobile(demo);
		waitForWorkArround(2000);
		Thread.sleep(1000);
		clinicSearchBoxLocator = manageMyClinic.getLocator("MenuNatera.ClinicSearchBox");
		// System.out.println("My Locator " + clinicNameInClinicTxtBox );
		// WaitForElementPresent(clinicSearchBoxLocator, 10);		   
		clinicNameInClinicTxtBox = getText(clinicSearchBoxLocator);

		Assert.assertTrue(clinicNameInClinicTxtBox.contains("Reproductive Care Center"), "Unable to switch to the clinic");

		ClinicServices = manageMyClinic.getLocator("MenuNatera.CourierServices");		   
		clickOn(ClinicServices);

		Thread.sleep(1000);
		ManageClinic = manageMyClinic.getLocator("MenuNatera.ManageMyClinic");		  
		clickOn(ManageClinic);		   


		Assert.assertTrue(ResultTxt.contains("Reproductive Care Center - Utah"));
	}

	String clinicNameInClinicTxtBox;
	String clinicSearchBoxLocator;

	public void inviteUser(String emailId , String type) throws InterruptedException
	{
		String userEmailAdds= emailId;
		String key= type;
		//Invite user
		locator = manageMyClinic.getLocator("ManageClinicPage.InviteUserBtn");
		clickOn(locator);

		Thread.sleep(1000);
		locator = manageMyClinic.getLocator("InviteSomeOnePopup.UserEmailBox");
		WaitForElementPresent(locator, 10);	
		sendKeys(locator, userEmailAdds);

		locator = manageMyClinic.getLocator("InviteSomeOnePopup.MedicalProfessionalCheckBox");
		clickOn(locator);		  

		// These fields has been removed.
		//locator = manageMyClinic.getLocator("InviteSomeOnePopup.ADSalesCheckBox");
		//clickOn(locator);		 

		// These fields has been removed.
		//		   locator = manageMyClinic.getLocator("InviteSomeOnePopup.ASRSalesCheckbox");
		//		   clickOn(locator);
		//		   
		//		   locator = manageMyClinic.getLocator("InviteSomeOnePopup.ClinicAdmin");
		//		   clickOn(locator);

		locator = manageMyClinic.getLocator("InviteSomeOnePopup.InviteUserBtn");
		clickOn(locator);

		locator = manageMyClinic.getLocator("InvitationSendConfLocator");
		String confMsg= getText(locator);

		if(key == "New")
		{
			Assert.assertTrue(confMsg.contains("has been invited to the clinic"), "Invitation is not sent.");
		}

		if(key == "Invited")
		{
			Assert.assertTrue(confMsg.contains("has already been invited to this clinic"), "Invitation is not sent.");
		}
	}

	public void clearClinicSelection() throws InterruptedException
	{
		//Clear Clinic Selection
		String clearClSelection = manageMyClinic.getLocator("ManageClinicPage.ClearClinicSelectionBtn");		   
		clickOn(clearClSelection);	
		Thread.sleep(4000);
		String demo = "//button[@id='navbar-toggle']";
		clickOnMobile(demo);
		waitForWorkArround(2000);
		String clinicSearchBoxLocator = manageMyClinic.getLocator("MenuNatera.SearchBox");	

		clinicNameInClinicTxtBox = getText(clinicSearchBoxLocator);
		System.out.println("My Clini-" + clinicSearchBoxLocator);
		Assert.assertTrue(clinicNameInClinicTxtBox.contains("Search Clinic"), "Clinic selection is not cleared.");

	}


	public int TableRowCount()
	{
		List<WebElement> columnElements = getWebDriver().findElements(By.xpath("//table[@id='clinic-admin-table']/tbody/tr"));
		int rowNumber = columnElements.size();
		return rowNumber;
	}


}
