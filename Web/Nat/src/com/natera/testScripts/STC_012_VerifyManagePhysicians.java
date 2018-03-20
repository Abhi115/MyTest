package com.natera.testScripts;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class STC_012_VerifyManagePhysicians extends DriverTestCase{
	
	@Test
	public void verifyManagePhysiciansPage() throws InterruptedException, IOException
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
					
					// For seaching 
					testManagePhysiciansPageHealper.searchPhysician("David");
					
					//for edit button
					testManagePhysiciansPageHealper.editButton("David", "null", "10");
					
					//for Reset button
					testManagePhysiciansPageHealper.resetButton("David", "newValue", "10");	
					
					// for Contect Setting
					testManagePhysiciansPageHealper.ContactSetting();
	}
}
