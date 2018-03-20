package com.natera.testScripts;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class STC_014EducationResource extends DriverTestCase{
	
	@Test
	public void verifyDashboardPage() throws InterruptedException, IOException
	{
		//WebDriver driver=getWebDriver();
		//Client Log into the application with valid credential
		String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
		String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
		loginHelper.enterUserEmail(usernameClient);
		loginHelper.enterUserPassword(passwordClient);
		loginHelper.clickLogin();
		
			//Verify Dashboard page
			dashboardPageHelper.verifyDashboardHeader();
			
			testEducationCenterPageHelper.CheckResourcesCenterPage();
			testEducationCenterPageHelper.nateraSubLinkVerification("Billing");
			testEducationCenterPageHelper.panoramaSubLinkVerification("PatientBrochures");
			testEducationCenterPageHelper.horizonSubLinkVerification("ForPatients");
			testEducationCenterPageHelper.anoraSubLinkVerification("PatientSupplements");
			testEducationCenterPageHelper.spectrumSubLinkVerification("ForPatients");
	}

}