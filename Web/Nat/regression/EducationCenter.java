package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

public class EducationCenter extends DriverTestCase {
	@Test
	public void CheckEducationCenter() throws InterruptedException, IOException
	{
		
	//Client Log into the application with valid credential
	String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
	String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
	loginHelper.enterUserEmail(usernameClient);
	loginHelper.enterUserPassword(passwordClient);
	loginHelper.clickLogin();
	
	//Verify Dashboard page
	dashboardPageHelper.verifyDashboardHeader();
	
	//Verify Education Center page
	testEducationCenterPageHelper.CheckEducationCenterPage();
	/*
	//Natera Sub Links
		//Billing Sub Link
			testEducationCenterPageHelper.nateraSubLinkVerification("Billing");
	
	//Panorama Sub Links
		//Patient Brochures Sub Links
			testEducationCenterPageHelper.panoramaSubLinkVerification("PatientBrochures");
		
		
		//Fact Sheets & More Sub Links
			testEducationCenterPageHelper.panoramaSubLinkVerification("FactSheetsMore");
		
	//Horizon sub links
		//For Patients Sub Link
			testEducationCenterPageHelper.horizonSubLinkVerification("ForPatients");
				
		//Post-Test Sub Link
		testEducationCenterPageHelper.horizonSubLinkVerification("Post-Test");
		
		//Fact Sheets & More Sub Link
			//testEducationCenterPageHelper.horizonSubLinkVerification("FactSheetsMore"); // This is not working, element is not enabled.
		
		// Anora Sub links
		//Patient Supplements sub links
		testEducationCenterPageHelper.anoraSubLinkVerification("PatientSupplements");
		
		//Fact Sheets & More sub link
			//testEducationCenterPageHelper.anoraSubLinkVerification("FactSheetsMore"); // This is not working, element is not enabled.
		
		
		*/
		//Spectrum Sublinks
		//For Patients sub link
		testEducationCenterPageHelper.spectrumSubLinkVerification("ForPatients");
		
		//For Provider sub link
		testEducationCenterPageHelper.spectrumSubLinkVerification("ForProvider");
		
		
		
		
		
		
		
		
		

	}
	

}
