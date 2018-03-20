package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_004_ResetPassword extends DriverTestCase{
	
	// Testcase: To verify Reset password feature. :: Done
	
	@Test
	public void verifyForgetPassword() throws InterruptedException, IOException
	{
		loginHelper.forgetPassword();
	}

}
