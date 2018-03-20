package com.natera.testScripts.regression;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_044_HorizonFormSaveAndPrint extends DriverTestCase{

	@Test
	public void verifyHorizonFormSaveAndPrint() throws Exception
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
		
		// Filling Form for Horizon-> Save and Print
		testOrderTestPageHelper.fillingForm("Horizon", "Akhilesh3");
		
		// Save & Print a New Horizon test
		testOrderTestPageHelper.savePrintDraft("New Test");
	}
}
