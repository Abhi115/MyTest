package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_068_FilterMessageSearchBoxWithMatching extends DriverTestCase{
	
	@Test
	public void verifyFilterMessageSearchBoxWithMatching() throws InterruptedException, IOException
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
				
		//Filter message from Filter message box with matching keyword
		testContactNateraPageHelper.filterMessageSearchBox("This is test A NEW subject");
		
	}

}
