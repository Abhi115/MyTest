package com.natera.testScripts;

import java.io.IOException;



import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;
@Listeners(com.natera.util.NateraReport.class)
public class STC_004_VerifyPrintReqForm extends DriverTestCase{
	
	@Test
	public void verifyPrintReqFormPage() throws InterruptedException, IOException
	{
	//Client Log into the application with valid credential
	String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
	String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
	loginHelper.enterUserEmail(usernameClient);
	loginHelper.enterUserPassword(passwordClient);
	loginHelper.clickLogin();
	
	//Verify Dashboard page
	dashboardPageHelper.verifyDashboardHeader();
	
	//Verify Print Req Form page
	testPrintReqFormPageHelper.CheckPrintReqFormPage();	
	//Verify Download Form
	testPrintReqFormPageHelper.DownloadPrintForm("Download");	
	//Verify Print Form 
	testPrintReqFormPageHelper.DownloadPrintForm("Print");
	System.out.println("Print form button clicked");
	Thread.sleep(10000);
	
	   Actions action = new Actions(getWebDriver());
	    action.sendKeys(Keys.ESCAPE).build().perform();
	   
	   //System.out.println("Action Performed to Escape keys clikcked");
	//getWebDriver().findElement(By.xpath("//a[@id='print-req-form-button']")).sendKeys(Keys.ENTER);
	
	}

}
