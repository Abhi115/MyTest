package com.natera.testScripts;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;
@Listeners(com.natera.util.NateraReport.class)
public class STC_001_VerifyDashboard extends DriverTestCase{
	
	@Test
	public void verifyDashboardPage() throws InterruptedException, IOException
	{
		//Client Log into the application with valid credential
		String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
		String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
		loginHelper.enterUserEmail(usernameClient);
		loginHelper.enterUserPassword(passwordClient);
		loginHelper.clickLogin();
		
		
			//Verify Dashboard page
			dashboardPageHelper.verifyDashboardHeader();
			
			//Verify all links are present on Dashboard  
			dashboardPageHelper.dashboardlinkExist();
					
			//Verify all links of Dashboard are working		
			//dashboardPageHelper.verifyProcessingLink();
			//dashboardPageHelper.verifyDeliveredLink();
			//dashboardPageHelper.verifyViewResultsLink();
			//dashboardPageHelper.verifyOrderTestsLink();
			//dashboardPageHelper.verifyContactNateraLink();
			//dashboardPageHelper.verifyOrderKitsLink();
			
			//dashboardPageHelper.searchCaseFromSearchBox("694901");
			
			//dashboardPageHelper.verifyResultDurationDropdown();
			
			dashboardPageHelper.verifySearchClinic();
		  
		
	}
}
