package com.natera.testScripts;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;
@Listeners(com.natera.util.NateraReport.class)
public class STC_005_VerifyOrderKits extends DriverTestCase{
	
	@Test
	public void verifyOrderKitsPage() throws Exception
	{
		
	//Client Log into the application with valid credential
	String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
	String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
	loginHelper.enterUserEmail(usernameClient);
	loginHelper.enterUserPassword(passwordClient);
	loginHelper.clickLogin();
	
		//Verify Dashboard page
		dashboardPageHelper.verifyDashboardHeader();

		//Verify Order Kits page
		testOrderKitsPageHelper.CheckOrderKitsPage();
		testOrderKitsPageHelper.addCart();
		
		testOrderKitsPageHelper.fillShippingAddress();				
		//testOrderKitsPageHelper.addOtherAddress();		
		testOrderKitsPageHelper.placeOrder();		
		testOrderKitsPageHelper.moreOrderlink();
		
	  	
	}
}
