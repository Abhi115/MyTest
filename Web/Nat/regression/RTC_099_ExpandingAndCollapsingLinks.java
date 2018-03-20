package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_099_ExpandingAndCollapsingLinks extends DriverTestCase{
	
	@Test
	public void verifyExpandingAndCollapsingLinks() throws InterruptedException, IOException
	{
		//Client Log into the application with valid credential
		String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
		String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
		loginHelper.enterUserEmail(usernameClient);
		loginHelper.enterUserPassword(passwordClient);
		loginHelper.clickLogin();
		
		//Verify Dashboard page
		dashboardPageHelper.verifyDashboardHeader();
		
		//Verify HelpCenter page
		testHelpCenterPageHelper.CheckHelpCenterPage();
		
		//Expanding and collapsing the links present on Help Center page
		testHelpCenterPageHelper.helpLinksExpandAndCollaps();
					
	}

}
