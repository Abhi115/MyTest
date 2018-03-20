package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class ScreeningLocator extends DriverTestCase{
	
	@Test
	public void verifyScreeningLocatorPage() throws InterruptedException, IOException
	{
		
	//Client Log into the application with valid credential
	String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
	String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
	loginHelper.enterUserEmail(usernameClient);
	loginHelper.enterUserPassword(passwordClient);
	loginHelper.clickLogin();
	
	//Verify Dashboard page
	dashboardPageHelper.verifyDashboardHeader();
	
	//Verify Screening Locator page
	testScreeningLocatorPageHelper.CheckScreeningLocatorPage();
	
	//Searching nearest screening location For valid locator
	testScreeningLocatorPageHelper.searchScreeningLocation("12121", "10");
	
	testScreeningLocatorPageHelper.searchScreeningLocation("12121", "100");
	
	//Searching nearest screening location For valid locator
	testScreeningLocatorPageHelper.searchScreeningLocation("121214324", "50");
	
	}

}
