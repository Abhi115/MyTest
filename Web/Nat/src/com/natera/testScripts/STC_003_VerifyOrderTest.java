package com.natera.testScripts;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;
@Listeners(com.natera.util.NateraReport.class)
public class STC_003_VerifyOrderTest extends DriverTestCase{
	
	@Test
	public void verifyOrderTestPage() throws Exception
	{
	
			//Client Log into the application with valid credential
			String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
			String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
			loginHelper.enterUserEmail(usernameClient);
			loginHelper.enterUserPassword(passwordClient);
			loginHelper.clickLogin();
				
			
				//Verify Dashboard page
				dashboardPageHelper.verifyDashboardHeader();
				
				//Verify Order A Test page			
				testOrderTestPageHelper.CheckOrderTestPage();
				
				// Filling Form for Panorma+Horizon-> Save and Print
				testOrderTestPageHelper.fillingForm("Panorama + Horizon", "Akhilesh");
				testOrderTestPageHelper.saveDraftForm();				
				testOrderTestPageHelper.updateAndSaveDraftForm(); 
				
				testOrderTestPageHelper.savePrintDraft("New Test");
	}

}
