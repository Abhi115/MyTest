package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_090_InviteAnAlreadyInvitedUser extends DriverTestCase{
	
	@Test
	public void verifyInviteAnAlreadyInvitedUser() throws InterruptedException, IOException
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
		
		//Invite a already invited user
		testManageMyClinicPageHelper.inviteUser("akhileshk@360logica.com", "Invited");
		
	}

}