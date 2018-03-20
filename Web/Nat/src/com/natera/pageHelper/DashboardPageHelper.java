package com.natera.pageHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.natera.locator.LocatorReader;
import com.natera.util.DriverHelper;
import com.natera.util.PropertyReader;









import org.testng.Assert;

public class DashboardPageHelper extends DriverHelper  
{

	private LocatorReader dashboardPage;
	private PropertyReader propertyReader;
	public DashboardPageHelper(WebDriver driver)
	{
		super(driver);	
		dashboardPage = new LocatorReader("Dashboard.xml");		
		propertyReader=new PropertyReader();
	}

	//Verify Dashboard page links	 
	public void dashboardlinkExist()
	{
		Assert.assertTrue(getText(dashboardPage.getLocator("DashboardLink.Processing")).contains("Processing"), "Processing link is not present on Dahsboard page");
		Assert.assertTrue(getText(dashboardPage.getLocator("DashboardLink.Delivered")).contains("Delivered"),  "Delivered link is not present on Dahsboard page");
		Assert.assertTrue(getText(dashboardPage.getLocator("DashboardLink.ViewResults")).contains("View Test Results"),  "ViewResults link is not present on Dahsboard page");
		Assert.assertTrue(getText(dashboardPage.getLocator("DashboardLink.OrderTests")).contains("Order a Test"),  "OrderTests link is not present on Dahsboard page");
		Assert.assertTrue(getText(dashboardPage.getLocator("DashboardLink.ContactNatera")).contains("Contact Natera"),  "ContactNatera link is not present on Dahsboard page");
		Assert.assertTrue(getText(dashboardPage.getLocator("DashboardLink.OrderKits")).contains("Order Kits"),  "OrderKits link is not present on Dahsboard page");		  
	}

	String locator;

	//Result Duration Dropdown
	public void verifyResultDurationDropdown()
	{
		String durationlocator = dashboardPage.getLocator("DashBoardPageLocators.DurationDropdown");
		Select timeDuration=new Select(getWebDriver().findElement(By.xpath(durationlocator)));
		timeDuration.selectByVisibleText("1 week");
		waitForWorkArround(3000);
		String pLocator = dashboardPage.getLocator("DashBoardPageLocators.ProcessingCount");
		String pCountTxt1w = getText(pLocator);


		String dLocator = dashboardPage.getLocator("DashBoardPageLocators.DeliveredCount");
		String dCountTxt1w = getText(dLocator);

		timeDuration=new Select(getWebDriver().findElement(By.xpath(durationlocator)));
		timeDuration.selectByVisibleText("1 year");		   
		waitForWorkArround(3000);
		Assert.assertTrue(pCountTxt1w.contains(getText(pLocator)), "Processing count is not changing on changing duration.");
		Assert.assertFalse(dCountTxt1w.contains(getText(dLocator)), "Delivered count is not changing on changing duration.");
		clickDdashboard();
	}

	// Result Search Clinic in dropdown

	public void verifySearchClinic() throws InterruptedException
	{

		// driver.navigate().refresh();
		String demo = "//button[@id='navbar-toggle']";
		clickOnMobile(demo);
		waitForWorkArround(2000);

		String searchclinic = dashboardPage.getLocator("DashBoardPageLocators.SearchBoxClinic");		  
		clickOn(searchclinic);

		Thread.sleep(1000);
		String Selectclinic = dashboardPage.getLocator("DashBoardPageLocators.SelectDrop");		  
		clickOn(Selectclinic);

		String Rightpage = dashboardPage.getLocator("DashBoardPageLocators.CheckPage");		   
		actualTxt = getText(Rightpage);		
		targetTxt = "Welcome AHN OBGYN Kokomo";
		Assert.assertFalse(Rightpage.contains(targetTxt), "After Selecting Clinic We are not in correct page.");

		waitForWorkArround(2000);
		clickOnMobile(demo);
		waitForWorkArround(2000);

		String Cancleclinic = dashboardPage.getLocator("DashBoardPageLocators.CancleClinic");		  
		clickOn(Cancleclinic);

	}


	// Click on Dashboard side menu
	public void clickDdashboard()
	{		   
		locator= dashboardPage.getLocator("DashboardIndex.Dashboard");
		clickOn(locator);		  
		Assert.assertEquals(getText(dashboardPage.getLocator("DashboardHeader.HeaderText")), dashboardPage.getLocator("PageHeaderData.DashboardHeaderData"),  "After clicking on Dashboard, user is not redirected to correct page");
	}

	//Verification of Dashboard page header text
	public void verifyDashboardHeader()
	{
		String dashboardLocator = dashboardPage.getLocator("DashboardHeader.HeaderText");		   
		String dashboardHeaderString = dashboardPage.getLocator("PageHeaderData.DashboardHeaderData");	
		String value=getText(dashboardLocator);
		//Assert.assertTrue(value.contains("Status at a Glance"), "After login we are not on correct page.");
		Assert.assertEquals(dashboardHeaderString , value, "After login we are not on correct page.");
	}	   

	//Clicking on Processing link and verify its respective page
	public void verifyProcessingLink()
	{
		locator = dashboardPage.getLocator("DashboardLink.Processing");	   
		clickOn(locator);

		String ProcessingPageHeaderLocator= dashboardPage.getLocator("PageHeaderLocator.HeaderLocator");
		String ProcessingPageHeaderTxt= dashboardPage.getLocator("PageHeaderData.Processing");	    
		Assert.assertEquals(getText(ProcessingPageHeaderLocator),ProcessingPageHeaderTxt,  "After clicking on Processing link, user is not redirected to correct page");
		clickDdashboard();
	}

	//Clicking on Delivered link and verify its respective page
	public void verifyDeliveredLink()
	{
		locator = dashboardPage.getLocator("DashboardLink.Delivered");
		clickOn(locator);

		String DeliveredPageHeaderLocator= dashboardPage.getLocator("PageHeaderLocator.HeaderLocator");
		String DeliveredPageHeaderTxt= dashboardPage.getLocator("PageHeaderData.Delivered");
		Assert.assertEquals(getText(DeliveredPageHeaderLocator), DeliveredPageHeaderTxt,  "After clicking on Delivered link, user is not redirected to correct page");
		clickDdashboard();
	}

	//Clicking on ViewResults link and verify its respective page
	public void verifyViewResultsLink()
	{
		locator = dashboardPage.getLocator("DashboardLink.ViewResults");
		clickOn(locator);

		String ViewResultsPageHeaderLocator= dashboardPage.getLocator("PageHeaderLocator.HeaderLocator");
		String ViewResultsPageHeaderTxt= dashboardPage.getLocator("PageHeaderData.ViewResults");
		Assert.assertEquals(getText(ViewResultsPageHeaderLocator), ViewResultsPageHeaderTxt,  "After clicking on ViewResults link, user is not redirected to correct page");
		clickDdashboard();
	}

	//Clicking on OrderTests link and verify its respective page
	public void verifyOrderTestsLink()
	{
		locator = dashboardPage.getLocator("DashboardLink.OrderTests");
		clickOn(locator);

		String OrderTestsPageHeaderLocator= dashboardPage.getLocator("PageHeaderLocator.HeaderLocator");
		String OrderTestsPageHeaderTxt= dashboardPage.getLocator("PageHeaderData.OrderTests");		
		Assert.assertEquals(getText(OrderTestsPageHeaderLocator), OrderTestsPageHeaderTxt,  "After clicking on OrderTests link, user is not redirected to correct page");
		clickDdashboard();
	}

	//Clicking on ContactNatera link and verify its respective page
	public void verifyContactNateraLink()
	{
		locator = dashboardPage.getLocator("DashboardLink.ContactNatera");
		clickOn(locator);

		String ContactNateraPageHeaderLocator= dashboardPage.getLocator("PageHeaderLocator.HeaderLocator");
		String ContactNateraPageHeaderTxt= dashboardPage.getLocator("PageHeaderData.ContactNatera");	
		Assert.assertEquals(getText(ContactNateraPageHeaderLocator), ContactNateraPageHeaderTxt,  "After clicking on ContactNatera link, user is not redirected to correct page");
		clickDdashboard();
	}

	//Clicking on OrderKits link and verify its respective page
	public void verifyOrderKitsLink()
	{
		locator = dashboardPage.getLocator("DashboardLink.OrderKits");
		clickOn(locator);

		String OrderKitsPageHeaderLocator= dashboardPage.getLocator("PageHeaderLocator.OrderKits.Panorama");
		String OrderKitsPageHeaderTxt= dashboardPage.getLocator("PageHeaderData.OrderKits.L1");			
		Assert.assertEquals(getText(OrderKitsPageHeaderLocator), OrderKitsPageHeaderTxt,  "After clicking on OrderKits link, user is not redirected to correct page");

		OrderKitsPageHeaderLocator= dashboardPage.getLocator("PageHeaderLocator.OrderKits.Horizon");
		OrderKitsPageHeaderTxt= dashboardPage.getLocator("PageHeaderData.OrderKits.L2");	    
		Assert.assertEquals(getText(OrderKitsPageHeaderLocator), OrderKitsPageHeaderTxt,  "After clicking on OrderKits link, user is not redirected to correct page");

		OrderKitsPageHeaderLocator= dashboardPage.getLocator("PageHeaderLocator.OrderKits.ComboKit");
		OrderKitsPageHeaderTxt= dashboardPage.getLocator("PageHeaderData.OrderKits.L3");   
		Assert.assertEquals(getText(OrderKitsPageHeaderLocator), OrderKitsPageHeaderTxt,  "After clicking on OrderKits link, user is not redirected to correct page");

		OrderKitsPageHeaderLocator= dashboardPage.getLocator("PageHeaderLocator.OrderKits.Anora");
		OrderKitsPageHeaderTxt= dashboardPage.getLocator("PageHeaderData.OrderKits.L4");      
		Assert.assertEquals(getText(OrderKitsPageHeaderLocator), OrderKitsPageHeaderTxt,  "After clicking on OrderKits link, user is not redirected to correct page");

		OrderKitsPageHeaderLocator= dashboardPage.getLocator("PageHeaderLocator.OrderKits.Spectrum");
		OrderKitsPageHeaderTxt= dashboardPage.getLocator("PageHeaderData.OrderKits.L5");     
		Assert.assertEquals(getText(OrderKitsPageHeaderLocator), OrderKitsPageHeaderTxt,  "After clicking on OrderKits link, user is not redirected to correct page");
		clickDdashboard();
	}

	String actualTxt;
	String targetTxt;

	//Searching a testCase from universal SearchBox
	public void searchCaseFromSearchBox(String testCaseId)
	{	   
		if(propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			String demo="//button[@data-target='#search-menu']";
			clickOnMobile(demo);
			waitForWorkArround(1000);
		}
		locator = dashboardPage.getLocator("DashBoardPageLocators.CasesSearchBox");
		sendKeys(locator, testCaseId);
		waitForWorkArround(5000);
		String emptySearchList = dashboardPage.getLocator("DashBoardPageLocators.ResultList");

		if(isElementPresent(emptySearchList))
		{
			locator= emptySearchList;
			actualTxt = getText(locator);
			Assert.assertTrue(actualTxt.contains("No Results Found"), "We have some test result.");
		}
		else
		{
			//locator= SearchList; CaseId
			//locator = dashboardPage.getLocator("DashBoardPageLocators.ResultCaseId");

			WaitForElementPresent(locator, 10);

			if(isElementPresent(locator)){
				mouseOver(locator);

				System.out.println("Akhilesh"+getText(locator));

				Actions action = new Actions(getWebDriver());
				WebElement el = getWebDriver().findElement(ByLocator(locator));
				WaitForElementPresent(locator, 10);
				action.moveToElement(el).perform();

				el.sendKeys(Keys.ENTER);
			}
			//locator = dashboardPage.getLocator("DashBoardPageLocators.ResultList");

			//clickOn(locator);
			waitForWorkArround(2000);

			locator = dashboardPage.getLocator("DashBoardPageLocators.CaseResultVerification.ResultPaseHeader");
			actualTxt = getText(locator);
			System.out.println("My Locator" + actualTxt);
			targetTxt = "Status Details";
			Assert.assertTrue(actualTxt.contains(targetTxt), "Unable to load case result.");

			locator = dashboardPage.getLocator("DashBoardPageLocators.CaseResultVerification.ResultCaseId");
			actualTxt = getText(locator);
			System.out.println("My value--" + actualTxt);
			targetTxt = testCaseId;
			Assert.assertEquals(actualTxt, targetTxt, "Not loaded correct case Id result");	

			clickDdashboard();
		}

	}

	//Verify ContactNatera icon functionality
	public void contactNateraIconFunction()
	{
		locator = dashboardPage.getLocator("DashBoardPageLocators.ContactNateraIcon"); 
		clickOn(locator);

		locator = dashboardPage.getLocator("DashBoardPageLocators.ContactnateraPageHeader"); 
		actualTxt = getText(locator);
		targetTxt = "Contact Natera";
		Assert.assertTrue(actualTxt.contains(targetTxt), "Unable to load Contact Natera page.");
	}

	//Verify manageAccount icon's sub options
	public void manageAccountIcon()
	{
		String signout="";
		if(propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			String demo=dashboardPage.getLocator("DashBoardPageLocators.UserSetting.MobileUserMenu");
			clickOn(demo);
			waitForWorkArround(2000);
			locator = dashboardPage.getLocator("DashBoardPageLocators.UserSetting.MobileProfile"); 
			signout = dashboardPage.getLocator("DashBoardPageLocators.UserSetting.MobileSignOut"); 
		}
		else
		{
			locator = dashboardPage.getLocator("DashBoardPageLocators.UserSetting.UserMenu"); 
			clickOn(locator);

			locator = dashboardPage.getLocator("DashBoardPageLocators.UserSetting.Profile"); 
			signout = dashboardPage.getLocator("DashBoardPageLocators.UserSetting.SignOut"); 
		}

		actualTxt = getText(locator);
		targetTxt = "Profile";
		Assert.assertTrue(actualTxt.contains(targetTxt),"We are in not a correct page.");


		actualTxt = getText(signout);
		targetTxt = "Sign out";
		Assert.assertTrue(actualTxt.contains(targetTxt));
	}

	//Verify userProfile page
	public void userProfile()
	{
		if(propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			locator = dashboardPage.getLocator("DashBoardPageLocators.UserSetting.MobileUserMenu"); 
			clickOn(locator);	

			locator = dashboardPage.getLocator("DashBoardPageLocators.UserSetting.MobileProfile");
			WaitForElementPresent(locator, 200);
			clickOn(locator);
		}
		else
		{
			locator = dashboardPage.getLocator("DashBoardPageLocators.UserSetting.UserMenu"); 
			clickOn(locator);

			locator = dashboardPage.getLocator("DashBoardPageLocators.UserSetting.Profile");
			WaitForElementPresent(locator, 200);
			clickOn(locator);
		}
		locator = dashboardPage.getLocator("DashBoardPageLocators.UserSetting.UserProfilePageHeader");
		WaitForElementPresent(locator, 200);
		actualTxt = getText(locator);
		System.out.println("My Value -- " + actualTxt);
		targetTxt = "User Account for clinic_admin@natera.com";
		Assert.assertTrue(actualTxt.contains(targetTxt), "On clicking Profile, User profile page is not loaded.");

		// Assert.assertTrue(actualTxt.contains(uName), "Not logIn with correct user.");
	}
}
