package com.natera.pageHelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.natera.locator.LocatorReader;
import com.natera.util.DriverHelper;
import com.natera.util.PropertyReader;

public class OrderBrochuresPageHelper extends DriverHelper{
	private LocatorReader orderBrochures;
	private PropertyReader propertyReader;

	public OrderBrochuresPageHelper(WebDriver driver) {
		super(driver);	
		orderBrochures = new LocatorReader("OrderBrochures.xml");
		propertyReader = new PropertyReader();
	}
	String locator;

	public void CheckOrderBrochuresPage()
	{
		String temp="";
		String demo = "//button[@id='navbar-toggle']";
		clickOnMobile(demo);
		waitForWorkArround(2000);

		locator = orderBrochures.getLocator("MenuNatera.OrderSupplies");
		clickOn(locator);

		waitForWorkArround(2000);

		locator = orderBrochures.getLocator("MenuNatera.SubMenuOrderSupplies.OrderBrochures"); 
		clickOn(locator);
		if(!propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			locator = orderBrochures.getLocator("OrderBrochuresPage.OrderBrochuresPageHeaderTabs.Panorama");
			temp= getText(locator);
			Assert.assertEquals(temp, "Panorama", "Panorama tab is not appearing.");

			locator = orderBrochures.getLocator("OrderBrochuresPage.OrderBrochuresPageHeaderTabs.Horizon");
			temp= getText(locator);
			Assert.assertEquals(temp, "Horizon", "Horizon tab is not appearing.");



			locator = orderBrochures.getLocator("OrderBrochuresPage.OrderBrochuresPageHeaderTabs.Anora");
			temp= getText(locator);
			Assert.assertEquals(temp, "Anora", "Anora tab is not appearing.");

			locator = orderBrochures.getLocator("OrderBrochuresPage.OrderBrochuresPageHeaderTabs.Spectrum");
			temp= getText(locator);
			Assert.assertEquals(temp, "Spectrum", "Spectrum tab is not appearing.");

			locator = orderBrochures.getLocator("OrderBrochuresPage.OrderBrochuresPageHeaderTabs.NateraBilling");
			temp= getText(locator);
			Assert.assertEquals(temp, "Natera Billing", "Spectrum tab is not appearing.");
		}
	}



	public void addBrochurToCart()
	{
		if(!propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			//Panorama
			locator = orderBrochures.getLocator("OrderBrochuresPage.OrderBrochuresPageHeaderTabs.Panorama");
			clickOn(locator);
		}
		try 
		{
			Thread.sleep(1000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		locator = orderBrochures.getLocator("OrderBrochuresPage.AddToCart.Panorama");
		clickOn(locator);

		//Increating the number of Kits
		locator= orderBrochures.getLocator("OrderBrochuresPage.ShopingCartList.ItemIncrease");
		sendKeys(locator, "2");

		String itemCount= getWebDriver().findElement(By.xpath("//input[contains(@name,'quantity-NAT')]")).getAttribute("value");
		Assert.assertEquals(itemCount, "2", "Not displaying correct item count" );

		if(!propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			//Horizon
			locator = orderBrochures.getLocator("OrderBrochuresPage.OrderBrochuresPageHeaderTabs.Horizon");
			clickOn(locator);
		}
		try 
		{
			Thread.sleep(1000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		locator = orderBrochures.getLocator("OrderBrochuresPage.AddToCart.Horizon");
		clickOn(locator);

		if(!propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			//Anora
			locator = orderBrochures.getLocator("OrderBrochuresPage.OrderBrochuresPageHeaderTabs.Anora");
			clickOn(locator);
		}
		try 
		{
			Thread.sleep(1000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		locator = orderBrochures.getLocator("OrderBrochuresPage.AddToCart.Anora");
		clickOn(locator);
		if(!propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			//Spectrum
			locator = orderBrochures.getLocator("OrderBrochuresPage.OrderBrochuresPageHeaderTabs.Spectrum");
			clickOn(locator);
		}
		try 
		{
			Thread.sleep(1000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		locator = orderBrochures.getLocator("OrderBrochuresPage.AddToCart.Spectrum");
		clickOn(locator);
		if(!propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			//Natera Billing
			locator = orderBrochures.getLocator("OrderBrochuresPage.OrderBrochuresPageHeaderTabs.NateraBilling");
			clickOn(locator);
		}
		try 
		{
			Thread.sleep(1000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		locator = orderBrochures.getLocator("OrderBrochuresPage.AddToCart.NateraBilling");
		clickOn(locator);

		// Requisition Forms
		if(!propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			locator = orderBrochures.getLocator("OrderBrochuresPage.OrderBrochuresPageHeaderTabs.RequisitionForms");
			clickOn(locator);
		}
		try 
		{
			Thread.sleep(1000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		locator = orderBrochures.getLocator("OrderBrochuresPage.AddToCart.RequisitionForms");
		clickOn(locator);

		listlocator= orderBrochures.getLocator("OrderBrochuresPage.ShopingCartList.ListRowCount"); 

		List<WebElement> tableElements = getWebDriver().findElements(By.xpath(listlocator));
		rowNumber = tableElements.size();

		Assert.assertEquals(rowNumber, 6, "Unable to add Brochures for all kits in the cart.");
	}


	String listlocator;
	int rowNumber;

	public void removeBrochures()
	{
		if(!propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			//Clicking on Panorama tab
			locator = orderBrochures.getLocator("OrderBrochuresPage.OrderBrochuresPageHeaderTabs.Panorama");
			clickOn(locator);
		}
		try 
		{
			Thread.sleep(1000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		listlocator= orderBrochures.getLocator("OrderBrochuresPage.ShopingCartList.ListRowCount"); 

		List<WebElement> tableElements = getWebDriver().findElements(By.xpath(listlocator));
		rowNumber = tableElements.size();

		for(int i=0; i< rowNumber; i++)
		{
			int num=rowNumber-i;
			WebElement removeElement= getWebDriver().findElement(By.xpath("//div[@id='shopping-cart-list']/div/table/tbody/tr["+num+"]/td[3]/a/span"));
			removeElement.click();
			try {
				Thread.sleep(3000);
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


	String inPutdata;
	public void fillShippingInfo()
	{
		//Adding Shipping info
		locator = orderBrochures.getLocator("OrderBrochuresPage.ShippingInfo.ContactName");		   
		inPutdata= orderBrochures.getLocator("InputData.ContactName");		   
		sendKeys(locator, inPutdata);

		locator = orderBrochures.getLocator("OrderBrochuresPage.ShippingInfo.SelectClinic");
		inPutdata= orderBrochures.getLocator("InputData.SelectClinic");
		Select clicnicName=new Select(getWebDriver().findElement(By.xpath(locator)));
		clicnicName.selectByVisibleText(inPutdata);

		// locator = orderBrochures.getLocator("OrderBrochuresPage.ShippingInfo.SelectAddress");
		// inPutdata= orderBrochures.getLocator("InputData.SelectAddress");
		// Select clicnicAddress=new Select(getWebDriver().findElement(By.xpath(locator)));
		// clicnicAddress.selectByVisibleText(inPutdata);		   
	}

	public void fillOtherShippingAddress()
	{
		locator = orderBrochures.getLocator("OrderBrochuresPage.ShippingInfo.ShipAnotherAddress");
		clickOn(locator);

		locator = orderBrochures.getLocator("OrderBrochuresPage.ShippingInfo.AnotherAddress.Street");
		inPutdata= orderBrochures.getLocator("InputData.Street");
		sendKeys(locator, inPutdata);

		locator = orderBrochures.getLocator("OrderBrochuresPage.ShippingInfo.AnotherAddress.City");
		inPutdata= orderBrochures.getLocator("InputData.City");
		sendKeys(locator, inPutdata);

		locator = orderBrochures.getLocator("OrderBrochuresPage.ShippingInfo.AnotherAddress.State");
		inPutdata= orderBrochures.getLocator("InputData.State");
		sendKeys(locator, inPutdata);

		locator = orderBrochures.getLocator("OrderBrochuresPage.ShippingInfo.AnotherAddress.Zip");
		inPutdata= orderBrochures.getLocator("InputData.Zip");
		sendKeys(locator, inPutdata);

		locator = orderBrochures.getLocator("OrderBrochuresPage.ShippingInfo.AnotherAddress.Country");		  
		inPutdata= orderBrochures.getLocator("InputData.Country");		   
		Select clicnicCountry=new Select(getWebDriver().findElement(By.xpath(locator)));
		clicnicCountry.selectByVisibleText(inPutdata);

	}

	public void placeBrochuresOrder() throws InterruptedException
	{		   
		locator = orderBrochures.getLocator("OrderBrochuresPage.PlaceOrderBtn");	
		Thread.sleep(2000);
		clickOn(locator);

		locator = orderBrochures.getLocator("OrderBrochuresPage.OrderPlacedMsg");
		String msgTxt= getText(locator);

		//System.out.println(msgTxt);
		String targetText= "Your order has been placed successfully! You should receive your order in 5-7 business days. Thanks!";

		Assert.assertTrue(msgTxt.contains(targetText), "Oredr is not palced correctly. Please recheck it. Thanks!!!!");		   

	}

	public void orderMoreBrochureslink()
	{
		locator = orderBrochures.getLocator("OrderBrochuresPage.OrderMoreKits");
		clickOn(locator);			

		try 
		{
			Thread.sleep(10000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		if(!propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{

			locator = orderBrochures.getLocator("OrderBrochuresPage.OrderBrochuresPageHeaderTabs.Panorama");
			String temp= getText(locator);
			Assert.assertEquals(temp, "Panorama", "Panorama tab is not appearing.");

			locator = orderBrochures.getLocator("OrderBrochuresPage.OrderBrochuresPageHeaderTabs.Horizon");
			temp= getText(locator);
			Assert.assertEquals(temp, "Horizon", "Horizon tab is not appearing.");			   

			locator = orderBrochures.getLocator("OrderBrochuresPage.OrderBrochuresPageHeaderTabs.Anora");
			temp= getText(locator);
			Assert.assertEquals(temp, "Anora", "Anora tab is not appearing.");

			locator = orderBrochures.getLocator("OrderBrochuresPage.OrderBrochuresPageHeaderTabs.Spectrum");
			temp= getText(locator);
			Assert.assertEquals(temp, "Spectrum", "Spectrum tab is not appearing.");

			locator = orderBrochures.getLocator("OrderBrochuresPage.OrderBrochuresPageHeaderTabs.NateraBilling");
			temp= getText(locator);
			Assert.assertEquals(temp, "Natera Billing", "Spectrum tab is not appearing.");
		}
	}

	public void downloadPDF()
	{
		locator = orderBrochures.getLocator("OrderBrochuresPage.Download.Panorama");
		clickOn(locator);

		locator = orderBrochures.getLocator("OrderBrochuresPage.Download.DownloadPDF");
		clickOn(locator);
	}

}
