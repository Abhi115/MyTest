package com.natera.pageHelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.natera.locator.LocatorReader;
import com.natera.util.DriverHelper;

public class HelpCenterPageHelper extends DriverHelper{
	
	private LocatorReader helpCenter;
	
	 public HelpCenterPageHelper(WebDriver driver) {
		   super(driver);	
		   helpCenter = new LocatorReader("HelpCenter.xml");
	   }
	   
	 String locator;
	   
	   public void CheckHelpCenterPage() throws InterruptedException
	   {
			String demo = "//button[@id='navbar-toggle']";
			clickOnMobile(demo);
			waitForWorkArround(2000);
		   locator = helpCenter.getLocator("MenuNatera.EducationCenter");
		   clickOn(locator);
		   String  FAQlocator= helpCenter.getLocator("HelpCenterHeader.FAQ");
		   Thread.sleep(1000);
		   clickOn(FAQlocator);		   
		   String HelpText = helpCenter.getLocator("HelpCenterHeader.HeaderTxt");
		   String headerTxt= getText(HelpText);			
		   String targetTxt= "Help Center";
		   Assert.assertEquals(headerTxt, targetTxt, "After clicking Help Center, it doesn't on navigate to correct page");		   
	   }
	   
	   public void searchBox(String searchKey)
	   {
		   String actualTxt;
		   locator = helpCenter.getLocator("HelpCenterPage.SearchBox");
		   sendKeys(locator, searchKey);
		   
		   try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		   locator = helpCenter.getLocator("HelpCenterPage.NoResult");		   
		   String str = getAttribute(locator, "style");
		   if(!(str.contains("display: none"))){
			  locator = helpCenter.getLocator("HelpCenterPage.NoResultPara");
			  String txt= getText(locator) ;
			  Assert.assertTrue(txt.contains("Can't find what you're looking"), "Matching exist for search key word.");
		   }else {
			   List<WebElement> allLinks = getWebDriver().findElements(By.xpath("//div[@id='help-center-accordion']/div[@class='category results-and-reporting' or @class='category ordering-and-logistics' or @class='category ordering-and-logistics' or @class='category genetic-counseling' or @class='category insurance-and-payment' or @class='category connect-help']/ul/li[not(contains(@style,'display: none'))]/h4/a"));
			   
			   for (WebElement temp : allLinks) {
				   actualTxt= temp.getText();
				   Assert.assertTrue(actualTxt.contains(searchKey), "Search result does not contains search keyword.");
				}	
		   } 
	   }
	   
	   public void helpLinksExpandAndCollaps() throws InterruptedException
	   {		   
		   List<WebElement> allLinks = getWebDriver().findElements(By.xpath("//ul[@class='list-group']/li[not(contains(@style,'display: none'))]/h4/a"));
		   System.out.println(allLinks.size());
		   for(int i=0;i<allLinks.size();i++) {
			   
			   allLinks.get(i).click();
			   Thread.sleep(1000);
			   String expanded= allLinks.get(i).getAttribute("aria-expanded");
			   System.out.println("Linke"+allLinks.get(i).getText()+" Expanded"+expanded);
			   Assert.assertTrue(expanded.contains("true"), "On clicking over link it does not expand.");
			   
			   try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			   allLinks.get(i).click();
			   Thread.sleep(2000);
			   scrollingdown();
			   Thread.sleep(2000);
			   expanded= allLinks.get(i).getAttribute("aria-expanded");
			   Assert.assertTrue(expanded.contains("false"), "On clicking over link it does not collapse.");				
			}		  
	   }
}
