package com.natera.testScripts.regression;

import java.io.IOException;
import java.util.Random;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_001_Signup extends DriverTestCase{	
	
	// Testcase: To signup with a new ID. :: Done
	
	@Test
	public void verifySignupWithNewID() throws InterruptedException, IOException
	{	
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(99999);	    				
		loginHelper.userSignup("Test"+randomInt+"@natera.com");	
		loginHelper.signupVerification();
		
		/*
		loginHelper.userSignup("Test@gmail.com");
		loginHelper.signupVerification();
					*/
	}
}
