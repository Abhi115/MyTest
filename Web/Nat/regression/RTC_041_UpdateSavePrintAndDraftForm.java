package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_041_UpdateSavePrintAndDraftForm extends DriverTestCase{

	@Test
	public void verifyUpdateSavePrintAndDraftForm() throws InterruptedException, IOException
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
		
		// Opening a draft message
		testOrderTestPageHelper.openDraftForm();
		
		//Update and Save & Print a Draft form
		testOrderTestPageHelper.savePrintDraft("update");
	}
}
