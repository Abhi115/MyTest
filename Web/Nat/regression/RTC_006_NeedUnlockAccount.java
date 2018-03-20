package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_006_NeedUnlockAccount extends DriverTestCase{
	
	// Testcase: To verify Need unlock account feature. :: Done
	
	@Test
	public void verifyNeedUnlockAccountLink() throws InterruptedException, IOException
	{
		loginHelper.resendUnlockInstructions();
	}

}
