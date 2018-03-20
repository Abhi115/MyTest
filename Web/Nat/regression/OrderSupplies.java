package com.natera.testScripts.regression;

import java.io.IOException;

import org.testng.annotations.Test;

import com.natera.util.DriverTestCase;

public class OrderSupplies extends DriverTestCase{
	
	@Test
	public void verifyOrderKitsPage() throws InterruptedException, IOException
	{
	//Client Log into the application with valid credential
	String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
	String passwordClient =  propertyReader.readApplicationFile("ClientPassword");
	loginHelper.enterUserEmail(usernameClient);
	loginHelper.enterUserPassword(passwordClient);
	loginHelper.clickLogin();

	
	//Verify Dashboard page
	dashboardPageHelper.verifyDashboardHeader();
	
	/*
	//Verify Order Kits  page
	testOrderKitsPageHelper.CheckOrderKitsPage();
	
	//Verify add to cart 
	testOrderKitsPageHelper.addCart();
	
	//Remove items from cart 	
		//testOrderKitsPageHelper.removeItem();
	
	//Filling the Shipping info  
	testOrderKitsPageHelper.fillShippingAddress();
	
	//Filling an other shipping address
		testOrderKitsPageHelper.addOtherAddress();
	
	//Place order
		testOrderKitsPageHelper.placeOrder();
	
	//"Need to order more kits?" link
		testOrderKitsPageHelper.moreOrderlink();		
		
		//*****************************************************  */
		
	//Verify Order Brochures page
		testOrderBrochuresPageHelper.CheckOrderBrochuresPage();
		
	//Downloading Brochures	
		testOrderBrochuresPageHelper.downloadPDF();
		
	//Add to carts	
		testOrderBrochuresPageHelper.addBrochurToCart();
		
	// Remove from Cart
		//testOrderBrochuresPageHelper.removeBrochures();
		
	// Filling shipping info
		testOrderBrochuresPageHelper.fillShippingInfo();
		
	// Filling other shipping address
		testOrderBrochuresPageHelper.fillOtherShippingAddress();
		
	//Place order
		testOrderBrochuresPageHelper.placeBrochuresOrder();
		
	//"Need to order more kits?" link
		testOrderBrochuresPageHelper.orderMoreBrochureslink();
	
	
	
	
	
	
	
	
	
	}

}
