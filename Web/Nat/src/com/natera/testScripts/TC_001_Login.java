package com.natera.testScripts;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
enum DriverType {
	Firefox, IE, Chrome;}

public class TC_001_Login extends DriverTestCase
{
	
	@Test
	public void loginInToApplication() throws InterruptedException, IOException
	{			
			//Client Log into the application with valid credential
			String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
			System.out.println(usernameClient);
			String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
			
			loginHelper.enterUserEmail(usernameClient);
			loginHelper.enterUserPassword(passwordClient);
			loginHelper.clickLogin();
			
			dashboardPageHelper.verifyDashboardHeader();
			
			//Verify user SignOut
			loginHelper.userSignOut();
	}
	
	@Test
	public void loginInWithInvalidCredentials() throws InterruptedException, IOException
	{			
			//Client Log into the application with valid credential
			String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
			System.out.println(usernameClient);
			String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
			
			loginHelper.enterUserEmail(usernameClient);
			loginHelper.enterUserPassword("INVALIDPASSWORD");
			loginHelper.clickLogin();
			
			dashboardPageHelper.verifyDashboardHeader();
	}
	
}
