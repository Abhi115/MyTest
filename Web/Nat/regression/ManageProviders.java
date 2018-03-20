package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

public class ManageProviders extends DriverTestCase{
	
	@Test
	public void ChecManageProviders() throws InterruptedException, IOException
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
	//testManagePhysiciansPageHealper.searchPhysician("Frederick");
	
	//Search Physician
	//testManagePhysiciansPageHealper.searchPhysician("David");
	//testManagePhysiciansPageHealper.searchPhysician("abcd");
	
	//Edit button function
	//testManagePhysiciansPageHealper.editButton("Frederick", "Cancel");
	
	//testManagePhysiciansPageHealper.editButton("Frederick", "SaveCheanges", "10");
	
	testManagePhysiciansPageHealper.resetButton("Frederick", "SaveCheanges", "10");
	
	}

}
