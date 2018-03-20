package com.natera.pageHelper;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.natera.locator.LocatorReader;
import com.natera.util.DriverHelper;
import com.natera.util.PropertyReader;

public class OrderKitsPageHelper extends DriverHelper{
	private LocatorReader orderKits;
	private PropertyReader propertyReader;

	public OrderKitsPageHelper(WebDriver driver) {
		super(driver);	
		orderKits = new LocatorReader("OrderKits.xml");
		propertyReader = new PropertyReader();
	}
	String locator;

	// Verify Order Kits page
	public void CheckOrderKitsPage()
	{
		String type = propertyReader.readApplicationFile("PlateForm");
		String demo = "//button[@id='navbar-toggle']";
		clickOnMobile(demo);
		waitForWorkArround(2000);


		locator = orderKits.getLocator("MenuNatera.OrderSupplies");

		clickOn(locator);

		locator = orderKits.getLocator("MenuNatera.SubMenuOrderSupplies.OrderKits");
		clickOn(locator);

		if(!type.toLowerCase().contains("mobile"))
		{

			locator = orderKits.getLocator("OrderKitsPage.OrderKitsHeaderTabs.Panorama");
			String temp= getText(locator);
			Assert.assertEquals(temp, "Panorama", "Panorama tab is not appearing.");

			locator = orderKits.getLocator("OrderKitsPage.OrderKitsHeaderTabs.Horizon");
			temp= getText(locator);
			Assert.assertEquals(temp, "Horizon", "Horizon tab is not appearing.");

			locator = orderKits.getLocator("OrderKitsPage.OrderKitsHeaderTabs.ComboKit");
			temp= getText(locator);
			Assert.assertEquals(temp, "Combo Kit", "Combo Kit tab is not appearing.");

			locator = orderKits.getLocator("OrderKitsPage.OrderKitsHeaderTabs.Anora");
			temp= getText(locator);
			Assert.assertEquals(temp, "Anora", "Anora tab is not appearing.");

			locator = orderKits.getLocator("OrderKitsPage.OrderKitsHeaderTabs.Spectrum");
			temp= getText(locator);
			Assert.assertEquals(temp, "Spectrum", "Spectrum tab is not appearing.");
		}
	}

	// Adding Kits to the cart
	public void addCart() throws InterruptedException
	{

		if(!propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			//Panorama
			locator = orderKits.getLocator("OrderKitsPage.OrderKitsHeaderTabs.Panorama");
			clickOn(locator);		   
		}
		locator = orderKits.getLocator("OrderKitsPage.AddToCart.Panorama");
		clickOn(locator);	   


		//Increasing the number of Kits
		locator= orderKits.getLocator("OrderKitsPage.ShopingCartList.ItemIncrease");
		sendKeys(locator, "2");		   	

		String itemCount= getWebDriver().findElement(By.xpath("//input[contains(@name,'quantity-NAT')]")).getAttribute("value");
		Assert.assertEquals(itemCount, "2", "Not displaying correct item count" );

		if(!propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			//Horizon
			locator = orderKits.getLocator("OrderKitsPage.OrderKitsHeaderTabs.Horizon");
			clickOn(locator);
		}
		locator = orderKits.getLocator("OrderKitsPage.AddToCart.Horizon");
		clickOn(locator);

		//ComboKit
		Thread.sleep(3000);
		if(!propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			locator = orderKits.getLocator("OrderKitsPage.OrderKitsHeaderTabs.ComboKit");
			// WaitForElementEnabled(locator, 30);
			clickOn(locator);
		}


		String locatorCombo = orderKits.getLocator("OrderKitsPage.AddToCart.ComboKit");	

		// WaitForElementEnabled(locatorCombo, 30);
		clickOn(locatorCombo);

		//driverHelper.scrollToElement(locator);

		//Anora
		Thread.sleep(3000);
		if(!propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			locator = orderKits.getLocator("OrderKitsPage.OrderKitsHeaderTabs.Anora");
			// WaitForElementEnabled(locator, 30);
			clickOn(locator);
		}
		locator = orderKits.getLocator("OrderKitsPage.AddToCart.Anora");
		//WaitForElementEnabled(locator, 30);
		clickOn(locator);

		//Spectrum
		Thread.sleep(3000);
		if(!propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			locator = orderKits.getLocator("OrderKitsPage.OrderKitsHeaderTabs.Spectrum");

			clickOn(locator);
		}
		locator = orderKits.getLocator("OrderKitsPage.AddToCart.Spectrum");
		//WaitForElementEnabled(locator, 30);
		clickOn(locator);
		scrollingup();	 


		listlocator= orderKits.getLocator("OrderKitsPage.ShopingCartList.ListRowCount");

		List<WebElement> tableElements = getWebDriver().findElements(By.xpath(listlocator));
		rowNumber = tableElements.size();
		Assert.assertEquals(rowNumber, 5, "Unable to add all type of kits in the cart.");
	}

	String listlocator;
	int rowNumber;

	//Remove items from the cart
	// public void removeItem()
	//{
	//		 // Clicking on Panorama tab to make page up
	//		   locator = orderKits.getLocator("OrderKitsPage.OrderKitsHeaderTabs.Panorama");
	//		   clickOn(locator);
	//		   
	//		   listlocator= orderKits.getLocator("OrderKitsPage.ShopingCartList.ListRowCount"); 
	//		   		   
	//		   List<WebElement> tableElements = getWebDriver().findElements(By.xpath(listlocator));
	//		   rowNumber = tableElements.size();
	//		   System.out.println("row value-- " + rowNumber);
	//		   for(int i=1; i< rowNumber; i++)
	//		   {
	//			  //int num=rowNumber-i;
	//			  scrollingup();
	//			  WebElement removeElement= getWebDriver().findElement(By.xpath("//div[@id='shopping-cart-list']/div/table/tbody/tr["+i+"]/td[3]/a/span"));
	//			  removeElement.click();
	//			  
	//		   try {
	//				Thread.sleep(5000);
	//			} catch (InterruptedException e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//			}
	//		   tableElements = getWebDriver().findElements(By.xpath(listlocator));
	//		   int rowNum = tableElements.size();
	//		   
	//		   if(i-1 <=0)
	//		   {
	//			   break;
	//		   }
	//		   
	//		   Assert.assertEquals(rowNum, i-1, "Unable to delete kits from the cart." );
	//		   }

	//Remove items from the cart

	// Abhishek  
	public void removeItem() throws InterruptedException
	{
		if(!propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			locator = orderKits.getLocator("OrderKitsPage.OrderKitsHeaderTabs.Panorama");
			clickOn(locator);
		}
		try 
		{
			Thread.sleep(1000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		listlocator= orderKits.getLocator("OrderKitsPage.ShopingCartList.ListRowCount"); 

		List<WebElement> tableElements = getWebDriver().findElements(By.xpath(listlocator));
		rowNumber = tableElements.size();

		for(int i=0; i< rowNumber; i++)
		{
			int num=rowNumber-i;
			Thread.sleep(1000);
			WebElement removeElement= getWebDriver().findElement(By.xpath("//div[@id='shopping-cart-list']/div/table/tbody/tr["+num+"]/td[3]/a/span"));
			removeElement.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tableElements = getWebDriver().findElements(By.xpath(listlocator));
			int rowNum = tableElements.size();
			if(num-1 <=0)
			{
				break;
			}

			Assert.assertEquals(rowNum, num-1, "Unable to delete Brochures from the cart." );
		}


	}


	//Adding Shipping info
	String inPutdata;
	public void fillShippingAddress() throws Exception
	{
		scrollingup();

		//Adding Shipping info
		locator = orderKits.getLocator("OrderKitsPage.ShippingInfo.ContactName");		   
		inPutdata= orderKits.getLocator("InputData.ContactName");		   
		sendKeys(locator, inPutdata);

		if(propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			inPutdata= orderKits.getLocator("InputData.SelectClinic");
			locator = orderKits.getLocator("OrderKitsPage.ShippingInfo.SelectClinic");
			selectDropDownByVisibleText(locator, inPutdata);

			locator = orderKits.getLocator("OrderKitsPage.ShippingInfo.SelectAddress");
			inPutdata= orderKits.getLocator("InputData.SelectAddress");
			//selectDropDownByVisibleText(locator,inPutdata);
		}
		else
		{

			try {
				Robot robot = new Robot();
				Thread.sleep(1000);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.delay(500);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.delay(500);

				robot.keyPress(KeyEvent.VK_R);
				robot.delay(500);
				robot.keyRelease(KeyEvent.VK_R);
				robot.delay(500);

				robot.keyPress(KeyEvent.VK_TAB);
				robot.delay(500);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.delay(500);

				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.delay(500);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.delay(500);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.delay(500);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.delay(500);

				robot.keyPress(KeyEvent.VK_C);
				robot.delay(500);
				robot.keyRelease(KeyEvent.VK_C);
				robot.delay(500);			     

			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
		}
	}

	//Adding other Shipping address
	public void addOtherAddress() throws InterruptedException
	{
		Thread.sleep(2000);
		String  locator1 = orderKits.getLocator("OrderKitsPage.ShippingInfo.ShipAnotherAddress");			
		scrollingdown();		   
		clickOn(locator1);

		Thread.sleep(1000);
		locator = orderKits.getLocator("OrderKitsPage.ShippingInfo.AnotherAddress.Street");
		inPutdata= orderKits.getLocator("InputData.Street");
		sendKeys(locator, inPutdata);

		locator = orderKits.getLocator("OrderKitsPage.ShippingInfo.AnotherAddress.City");
		inPutdata= orderKits.getLocator("InputData.City");
		sendKeys(locator, inPutdata);

		locator = orderKits.getLocator("OrderKitsPage.ShippingInfo.AnotherAddress.State");
		inPutdata= orderKits.getLocator("InputData.State");
		sendKeys(locator, inPutdata);

		locator = orderKits.getLocator("OrderKitsPage.ShippingInfo.AnotherAddress.Zip");
		inPutdata= orderKits.getLocator("InputData.Zip");
		sendKeys(locator, inPutdata);

		locator = orderKits.getLocator("OrderKitsPage.ShippingInfo.AnotherAddress.Country");		  
		inPutdata= orderKits.getLocator("InputData.Country");		   
		Select clicnicCountry=new Select(getWebDriver().findElement(By.xpath(locator)));
		clicnicCountry.selectByVisibleText(inPutdata);

	}

	//Placing the order
	public void placeOrder()
	{
		locator = orderKits.getLocator("Locators.OrderKitsPage.PlaceOrderBtn");
		System.out.println(locator + "placeOrder btn locator");
		clickOn(locator);


		locator = orderKits.getLocator("OrderKitsPage.OrderPlacedMsg");
		String msgTxt= getText(locator);
		String targetText= "Your order has been placed successfully!";		   
		Assert.assertTrue(msgTxt.contains(targetText));
	}

	// Verification of "Need to order more kits?" link		
	public void moreOrderlink()
	{
		locator = orderKits.getLocator("OrderKitsPage.OrderMoreKits");
		clickOn(locator);

		if(!propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			locator = orderKits.getLocator("OrderKitsPage.OrderKitsHeaderTabs.Panorama");
			String temp= getText(locator);			   
			Assert.assertEquals(temp, "Panorama", "Panorama tab is not appearing.");

			locator = orderKits.getLocator("OrderKitsPage.OrderKitsHeaderTabs.Horizon");
			temp= getText(locator);
			Assert.assertEquals(temp, "Horizon", "Horizon tab is not appearing.");

			locator = orderKits.getLocator("OrderKitsPage.OrderKitsHeaderTabs.ComboKit");
			temp= getText(locator);
			Assert.assertEquals(temp, "Combo Kit", "Combo Kit tab is not appearing.");

			locator = orderKits.getLocator("OrderKitsPage.OrderKitsHeaderTabs.Anora");
			temp= getText(locator);
			Assert.assertEquals(temp, "Anora", "Anora tab is not appearing.");

			locator = orderKits.getLocator("OrderKitsPage.OrderKitsHeaderTabs.Spectrum");
			temp= getText(locator);
			Assert.assertEquals(temp, "Spectrum", "Spectrum tab is not appearing.");	
		}
		else
		{
			locator = orderKits.getLocator("OrderKitsPage.AddToCart.Panorama");
			Assert.assertTrue(isElementPresent(locator));
		}
	}
}
