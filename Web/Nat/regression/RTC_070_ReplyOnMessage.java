package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_070_ReplyOnMessage extends DriverTestCase{
	
	@Test
	public void verifyReplyOnMessage() throws InterruptedException, IOException
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
		
		// Opening a sent message
		testContactNateraPageHelper.openMessage("just Test");
		
		//Reply on sent message
		testContactNateraPageHelper.replyOnMessage("Reply Test");
		
	}

}
