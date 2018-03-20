package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class TestConmtactNateraPage extends DriverTestCase{
	
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
		
		//Verify Contact Natera page 
		testContactNateraPageHelper.CheckContactNateraPage();
		
		// Message per page		
			//testContactNateraPageHelper.messagePerPages("25");
				
		//Verification of compose and Send new message
			testContactNateraPageHelper.composeAndSendNewMessage();
		
		//Filter message from Filter message box with no matching keyword
			//testContactNateraPageHelper.filterMessageSearchBox("fdgdfsubject");	
			
		//Filter message from Filter message box with matching keyword
			//testContactNateraPageHelper.filterMessageSearchBox("This is test subject");
			
		// Opening a sent message
			testContactNateraPageHelper.openMessage("This is test subject");
			
		//Reply on sent message
			testContactNateraPageHelper.replyOnMessage("reply test");
		
		
		
	}

}
