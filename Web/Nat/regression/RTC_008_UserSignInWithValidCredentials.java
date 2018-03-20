package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_008_UserSignInWithValidCredentials extends DriverTestCase{
	
	@Test
	public void verifySignInWithValidCredentials() throws InterruptedException, IOException
	{
		loginHelper.userSingIn("clinic_admin@natera.com", "clinic_admin");
				
				
	}

}
