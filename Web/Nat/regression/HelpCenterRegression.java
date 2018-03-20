package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

public class HelpCenterRegression extends DriverTestCase{
	
	@Test
	public void CheckScreeningLocator() throws InterruptedException, IOException
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
	
	//Search the keyword and verify search result
	testHelpCenterPageHelper.searchBox("add/remove");
	
	//Search the keyword and having no search result
		//testHelpCenterPageHelper.searchBox("Akhilesh"); // Not done yet
	
	
	
	}

}
