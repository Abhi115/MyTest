package com.natera.testScripts.regression;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_042_PanormaHorizonFormSaveAndPrint extends DriverTestCase{
	
	@Test
	public void verifyPanormaHorizonFormSaveAndPrint() throws Exception
	{
		//Client Log into the application with valid credential
		String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
		String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
		loginHelper.enterUserEmail(usernameClient);
		loginHelper.enterUserPassword(passwordClient);
		loginHelper.clickLogin();
		
		//Verify Dashboard page
		dashboardPageHelper.verifyDashboardHeader();
		
		//Verify Test A Order page
		testOrderTestPageHelper.CheckOrderTestPage();
		
		// Filling Form for Panorma+Horizon-> Save and Print
		testOrderTestPageHelper.fillingForm("Panorama + Horizon", "Akhilesh1");
		
		// Save & Print a New Panorama + Horizon test
		testOrderTestPageHelper.savePrintDraft("New Test");
	}

}
