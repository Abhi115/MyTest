package com.natera.testScripts.regression;

import java.io.IOException;
import java.util.Random;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_003_CorrectEmailIDLinkBehavior extends DriverTestCase{
	
	// Testcase: After signup, Correct email id link verification. :: Done

	@Test
	public void verifyCorrectEmailIDLink() throws InterruptedException, IOException
	{
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(99999);
				
		loginHelper.userSignup("Test"+randomInt+"@natera.com");
		loginHelper.signupVerification();
		loginHelper.correctEmailID();
	}
}
