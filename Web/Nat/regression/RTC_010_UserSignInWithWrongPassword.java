package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_010_UserSignInWithWrongPassword extends DriverTestCase{
	
	@Test
	public void verifySignInWithWrongPassword() throws InterruptedException, IOException
	{
		loginHelper.userSingIn("clinic_admin@natera.com", "test_admin");
						
	}

}
