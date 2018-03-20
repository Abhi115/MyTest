package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)

public class RTC_104CostEstimatorSubmitWithFillingData extends DriverTestCase
{

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
	
	// For Test Cost Estimator  Page	
	   testcostEstimator.CheckTestCostEstimatorLocatorPage();
	   
	   // For Submit Btn with filling Data. 
	   testcostEstimator.EstimationCost();
	   
	  
	}
	
}