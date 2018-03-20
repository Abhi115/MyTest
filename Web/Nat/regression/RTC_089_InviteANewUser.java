package com.natera.testScripts.regression;

import java.io.IOException;
import java.util.Random;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_089_InviteANewUser extends DriverTestCase{
	
	@Test
	public void verifyInviteANewUser() throws InterruptedException, IOException
	{
		//Client Log into the application with valid credential
		String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
		String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
		loginHelper.enterUserEmail(usernameClient);
		loginHelper.enterUserPassword(passwordClient);
		loginHelper.clickLogin();
		
		//Verify Dashboard page
		dashboardPageHelper.verifyDashboardHeader();
		
		//Verify Manage My Clinic page
		testManageMyClinicPageHelper.checkManageMyClinicPage();
		
		//Switching to a clinic
		testManageMyClinicPageHelper.switchToClinic();
		
		//Invite a new user
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(99999);	
		
		testManageMyClinicPageHelper.inviteUser("akhileshkk"+randomInt+"@360logica.com", "New");		
	}
}
