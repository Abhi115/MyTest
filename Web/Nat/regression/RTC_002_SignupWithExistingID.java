package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_002_SignupWithExistingID extends DriverTestCase{
	
	// Testcase: To signup with an already registered ID. :: Done

	@Test
	public void verifySignupWithExistingID() throws InterruptedException, IOException
	{
		loginHelper.userSignup("Test992@natera.com");
		loginHelper.signupWithExistingID();
	}
}
