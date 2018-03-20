package com.natera.testScripts;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;




//import com.natera.pageHelper.DashboardPageHelper;
//import com.natera.pageHelper.TestResultsPageHelper;
import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class STC_002_VerifyTestresults extends DriverTestCase{
	
	@Test
	public void verifyTestResultsPage() throws InterruptedException, IOException
	{
		//Client Log into the application with valid credential
		String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
		String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
		loginHelper.enterUserEmail(usernameClient);
		loginHelper.enterUserPassword(passwordClient);
		loginHelper.clickLogin();		
		
			//Verify Dashboard page
			dashboardPageHelper.verifyDashboardHeader();
			
			//Clinic selection			
			testResultsPageHelper.ViewTestResult();
			testResultsPageHelper.CasesPerPage("10");
			
			//testResultsPageHelper.SearchBox("Test", "Horizon");
		    testResultsPageHelper.ResultFilter("Result", "HIGH RISK");					
			testResultsPageHelper.PatientDetails();
			testResultsPageHelper.ReleasePatient();
			testResultsPageHelper.DownloadResult();		  
				
		
	}
}