package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_005_ResendConfirmationInstructions extends DriverTestCase{
	
	// Testcase: To re-send confirmation instruction verification. :: Done
	
	@Test
	public void verifyNotRecievedConfMessageLink() throws InterruptedException, IOException
	{
		loginHelper.notRecievedConfMessage();
	}

}
