package com.natera.testScripts.regression;

import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

public class RTC_053_AddOtherAddress extends DriverTestCase{
	
	@Test
	public void verifyAddOtherAddress() throws Exception
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
		
		//Filling an other shipping address
		testOrderKitsPageHelper.addOtherAddress();
	}

}
