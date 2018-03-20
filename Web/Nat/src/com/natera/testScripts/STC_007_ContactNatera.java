package com.natera.testScripts;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class STC_007_ContactNatera extends DriverTestCase{

	@Test
	public void verifyContactNateraPage() throws InterruptedException, IOException
	{
		//Client Log into the application with valid credential
		String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
		String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
		loginHelper.enterUserEmail(usernameClient);
		loginHelper.enterUserPassword(passwordClient);
		loginHelper.clickLogin();
		
		
			//Verify Dashboard page
			dashboardPageHelper.verifyDashboardHeader();
			
			//Verify Contact Natera page verification
			testContactNateraPageHelper.CheckContactNateraPage();
			
			//Verification of compose new message
			testContactNateraPageHelper.composeAndSendNewMessage();
		  
	}
}
