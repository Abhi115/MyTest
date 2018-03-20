package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_093_SearchPhysicanHavingResult extends DriverTestCase{
	
	@Test
	public void verifySearchPhysicanHavingSearchResult() throws InterruptedException, IOException
	{
		//Client Log into the application with valid credential
		String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
		String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
		loginHelper.enterUserEmail(usernameClient);
		loginHelper.enterUserPassword(passwordClient);
		loginHelper.clickLogin();
		
		//Verify Dashboard page
		dashboardPageHelper.verifyDashboardHeader();
		
		//Vertify Manage Physicians page
		testManagePhysiciansPageHealper.verifyManagePhysiciansPage();
		
		//Search Physician
		testManagePhysiciansPageHealper.searchPhysician("Frederick");
		
				
	}


}
