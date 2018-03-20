package com.natera.testScripts.regression;


import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class TestOrderATestPage extends DriverTestCase{
	
	@Test
	public void verifyTestOrderATestPage() throws Exception
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
		
		//Verify "Save and Print" form without filling data
			//testOrderTestPageHelper.saveAndPrintFormWithoutData();
		
		// Filling Form for Panorma & Horizon and save it in draft
			//testOrderTestPageHelper.fillingForm("Panorama + Horizon", "Akhilesh1");
			//testOrderTestPageHelper.saveDraftForm();              
		
		// Opening a draft message
			//testOrderTestPageHelper.openDraftForm();
		
		//Update and save Draft form		
			testOrderTestPageHelper.updateAndSaveDraftForm();
		
		//Update and Save & Print a Draft form
			//testOrderTestPageHelper.updateAndSavePrintDraftForm();
		
		
		
		// Filling Form for Panorma+Horizon-> Save and Print
			//testOrderTestPageHelper.fillingForm("Panorama + Horizon", "Akhilesh1");
		
		// Filling Form for Panorma -> Save and Print
			testOrderTestPageHelper.fillingForm("Panorama", "Akhilesh2");
		
		// Filling Form for Horizon-> Save and Print
			//testOrderTestPageHelper.fillingForm("Horizon", "Akhilesh3");
			
		// Filling Form for Anora-> Save and Print
			//testOrderTestPageHelper.fillingForm("Anora", "Akhilesh4");
		
		//****************************************
		
		testPrintReqFormPageHelper.CheckPrintReqFormPage();
		testPrintReqFormPageHelper.DownloadPrintForm("Download");
	}

}
