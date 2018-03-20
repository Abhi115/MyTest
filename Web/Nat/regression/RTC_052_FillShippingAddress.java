package com.natera.testScripts.regression;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_052_FillShippingAddress extends DriverTestCase{
	
	@Test
	public void verifyFillShippingAddress() throws Exception
	{
		//Client Log into the application with valid credential
		String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
		String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
		loginHelper.enterUserEmail(usernameClient);
		loginHelper.enterUserPassword(passwordClient);
		loginHelper.clickLogin();
		
		//Verify Dashboard page
		dashboardPageHelper.verifyDashboardHeader();
		
		//Verify Order Kits  page
		testOrderKitsPageHelper.CheckOrderKitsPage();
		
		//Verify add to cart 
		testOrderKitsPageHelper.addCart();
		
		//Filling the Shipping info  
		testOrderKitsPageHelper.fillShippingAddress();
	}

}