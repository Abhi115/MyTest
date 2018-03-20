package com.natera.pageHelper;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.natera.locator.LocatorReader;
import com.natera.util.DriverHelper;

public class CourierPickupPageHelper extends DriverHelper{
	
	private LocatorReader courierPickup;
	
	 public CourierPickupPageHelper(WebDriver driver) {
		   super(driver);	
		   courierPickup = new LocatorReader("CourierPickup.xml");
	   }
	   String locator;
	   
	   public void CheckCourierPickupPage() throws InterruptedException
	   {
		   String demo = "//button[@id='navbar-toggle']";
			clickOnMobile(demo);
			waitForWorkArround(2000);
			
		   locator = courierPickup.getLocator("MenuNatera.ClinicServices");
		   clickOn(locator);		   		  
		  // Thread.sleep(1000);
		   String  Courierlocator = courierPickup.getLocator("CourierPickPage.CourierServices");
		   clickOn(Courierlocator);			   
		   //String courierPickPageHeaderTxt= getText(locator);
		   String Locator = courierPickup.getLocator("CourierPickPage.HeaderLocator");
		   String HeaderTxt = getText(Locator);		   
		   String targetTxt= "Medspeed Courier Pickup";		   
		   Assert.assertTrue(HeaderTxt.equalsIgnoreCase(targetTxt), "Afetr clicking Courier Pickup, it does not redirect to correct page.");		   
	   }
	   	

	    public void LoginToMedSpeed() throws InterruptedException 
	    {
		// TODO Auto-generated method stub
		 String Loginlocator= courierPickup.getLocator("LoginMedSpeed.MedSpeedlogin");
		 Thread.sleep(1000);
		 clickOn(Loginlocator);
	    }
}
