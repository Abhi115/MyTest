package com.natera.testScripts;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class STC_15_VerifyTestCostEstimator extends DriverTestCase {

	@Test
	
	public void CheckTestEstimatorLocator() throws InterruptedException, IOException
	{
	//Client Log into the application with valid credential
	String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
	String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
	loginHelper.enterUserEmail(usernameClient);
	loginHelper.enterUserPassword(passwordClient);
	loginHelper.clickLogin();
	
	dashboardPageHelper.verifyDashboardHeader(); 
	
	// For check Page	
	   testcostEstimator.CheckTestCostEstimatorLocatorPage();
	   
	   testcostEstimator.EstimationCost();
	   
	   //for New Page TestcostEstimator
	   testcostEstimator.RequestScreenTest();
	
	}
}
