package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_066_composeAndSendNewMessage extends DriverTestCase{
	
	@Test
	public void verifyComposeAndSendNewMessage() throws InterruptedException, IOException
	{
		//Client Log into the application with valid credential
		String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
		String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
		loginHelper.enterUserEmail(usernameClient);
		loginHelper.enterUserPassword(passwordClient);
		loginHelper.clickLogin();
		
		//Verify Dashboard page
		dashboardPageHelper.verifyDashboardHeader();
		
		//Verify Contact Natera page 
		testContactNateraPageHelper.CheckContactNateraPage();
		
		//Verification of compose and Send new message
		testContactNateraPageHelper.composeAndSendNewMessage();
		
	}

}
