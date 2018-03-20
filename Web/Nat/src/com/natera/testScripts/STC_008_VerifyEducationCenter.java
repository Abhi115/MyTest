package com.natera.testScripts;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class STC_008_VerifyEducationCenter extends DriverTestCase{
	
	@Test
	public void verifyEducationCenter() throws InterruptedException, IOException
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
			
			//Verify Education Center page				
			testEducationCenterPageHelper.CheckEducationCenterPage();	
			testEducationCenterPageHelper.CheckNateraInformation();
			
	}
}