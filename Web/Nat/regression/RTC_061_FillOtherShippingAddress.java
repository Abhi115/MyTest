package com.natera.testScripts.regression;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

@Listeners(com.natera.util.NateraReport.class)
public class RTC_061_FillOtherShippingAddress extends DriverTestCase{
	
	@Test
	public void verifyFillOtherShippingAddress() throws Exception
	{
		//Client Log into the application with valid credential
		String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
		String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
		loginHelper.enterUserEmail(usernameClient);
		loginHelper.enterUserPassword(passwordClient);
		loginHelper.clickLogin();
		
		//Verify Dashboard page
		dashboardPageHelper.verifyDashboardHeader();
		
		//Verify Order Brochures page
		testOrderBrochuresPageHelper.CheckOrderBrochuresPage();
		
		//Add to carts	
		testOrderBrochuresPageHelper.addBrochurToCart();
		
		// Filling shipping info
		//testOrderBrochuresPageHelper.fillShippingInfo();
		
		testOrderKitsPageHelper.fillShippingAddress();
		
		// Filling other shipping address
		testOrderBrochuresPageHelper.fillOtherShippingAddress();
		
		
		
	}

}
