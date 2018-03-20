package com.natera.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.awt.AWTException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.natera.pageHelper.ContactNateraPageHelper;
import com.natera.pageHelper.CourierPickupPageHelper;
import com.natera.pageHelper.DashboardPageHelper;
import com.natera.pageHelper.EducationCenterPageHelper;
import com.natera.pageHelper.HelpCenterPageHelper;
import com.natera.pageHelper.LoginHelper;
import com.natera.pageHelper.ManageMyClinicPageHelper;
import com.natera.pageHelper.ManagePhysiciansPageHealper;
import com.natera.pageHelper.OrderBrochuresPageHelper;
import com.natera.pageHelper.OrderKitsPageHelper;
import com.natera.pageHelper.OrderTestPageHelper;
import com.natera.pageHelper.PrintReqFormPageHelper;
import com.natera.pageHelper.ScreeningLocatorPageHelper;
import com.natera.pageHelper.TestCostEstimator;
import com.natera.pageHelper.TestOrderingPageHelper;
import com.natera.pageHelper.TestResultsPageHelper;
import com.natera.util.PropertyReader;
import com.natera.util.DriverTestCase;

public abstract class DriverTestCase {


	public PropertyReader propertyReader;
	public LoginHelper loginHelper;
	public TestCostEstimator testcostEstimator;
	public DashboardPageHelper dashboardPageHelper;
	public TestResultsPageHelper testResultsPageHelper;
	public TestOrderingPageHelper testOrderingPageHelper;
	public EducationCenterPageHelper testEducationCenterPageHelper;
	public EducationCenterPageHelper testEducationCenterResource;
	public CourierPickupPageHelper testCourierPickupPageHelper;
	public HelpCenterPageHelper testHelpCenterPageHelper; 
	public ManageMyClinicPageHelper testManageMyClinicPageHelper;
	public ContactNateraPageHelper testContactNateraPageHelper;
	public OrderTestPageHelper testOrderTestPageHelper;
	public PrintReqFormPageHelper testPrintReqFormPageHelper;
	public OrderKitsPageHelper testOrderKitsPageHelper;
	public OrderBrochuresPageHelper testOrderBrochuresPageHelper;
	public ScreeningLocatorPageHelper testScreeningLocatorPageHelper;
	public ManagePhysiciansPageHealper testManagePhysiciansPageHealper; 

	static WebDriver driver;
	static AppiumDriver driver1;

	enum DriverType {
		Firefox, IE, Chrome
	}

	@BeforeTest
	//@BeforeClass
	public void setUp() throws MalformedURLException, AWTException 
	{
		propertyReader = new PropertyReader();		
		String driverType = propertyReader.readApplicationFile("BROWSER");		
		String plateForm = propertyReader.readApplicationFile("PlateForm");
		System.out.println("Test is running on "+plateForm+" plateform");
		if(plateForm.toLowerCase().contains("mobile"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("appium-version", "1.4.16.1");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("platformVersion", "6.0.1");
			capabilities.setCapability("deviceName", "baf8e4a4");
			capabilities.setCapability("browserName", "Chrome");
			capabilities.setCapability("autoAcceptAlerts", true);
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,"100");

			capabilities.setCapability("appPackage", "com.android.chrome");

			driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		}
		else
		{

			FirefoxProfile fprofile = new FirefoxProfile();
			fprofile.setPreference("browser.download.dir","E:\\selenium\\");
			fprofile.setPreference("browser.download.folderList", 2);
			fprofile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip");
			fprofile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
			fprofile.setPreference("browser.helperApps.neverAsk.saveToDisk","image/jpg, text/csv,text/xml,application/xml,application/vnd.ms-excel,application/x-excel,application/x-msexcel,application/excel,application/pdf,application/zip"); 

			if (DriverType.Firefox.toString().equals(driverType)) 
			{							
				driver = new FirefoxDriver();				

			}
			else if (DriverType.IE.toString().equals(driverType)) 
			{
				String strDriverPath = System.getProperty("user.dir")
						+ "\\WebDriver\\IEDriverServer.exe";
				System.setProperty("webdriver.ie.driver", strDriverPath);
				driver = new InternetExplorerDriver();

			}
			else if (DriverType.Chrome.toString().equals(driverType)) 
			{
				String strDriverPath = System.getProperty("user.dir")
						+ "\\WebDriver\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", strDriverPath);

				driver = new ChromeDriver();

			} 
			else
			{
				//driver = new InternetExplorerDriver();
				driver = new FirefoxDriver(fprofile);	
				//driver = new ChromeDriver(Cdriver);
			}
			
			//maxmize window
			driver.manage().window().maximize();
		
			/*Robot rb = new Robot();
			rb.keyPress(KeyEvent.VK_WINDOWS);
			rb.keyPress(KeyEvent.VK_LEFT);
			rb.keyRelease(KeyEvent.VK_LEFT);
			rb.keyRelease(KeyEvent.VK_WINDOWS);*/
		}
		

			//open url
			openURL();
		

			//driver.manage().deleteAllCookies();
						
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		loginHelper = new LoginHelper(driver);
		dashboardPageHelper = new DashboardPageHelper(driver);
		testcostEstimator = new TestCostEstimator(driver);
		testResultsPageHelper =new TestResultsPageHelper(driver);
		testOrderingPageHelper = new TestOrderingPageHelper(driver);
		testEducationCenterPageHelper= new EducationCenterPageHelper(driver);
		testCourierPickupPageHelper = new CourierPickupPageHelper(driver);
		testHelpCenterPageHelper = new HelpCenterPageHelper(driver);
		testManageMyClinicPageHelper = new ManageMyClinicPageHelper(driver);
		testContactNateraPageHelper = new ContactNateraPageHelper(driver);
		testOrderTestPageHelper = new OrderTestPageHelper(driver);
		testPrintReqFormPageHelper = new PrintReqFormPageHelper(driver);
		testOrderKitsPageHelper = new OrderKitsPageHelper (driver);
		testOrderBrochuresPageHelper = new OrderBrochuresPageHelper(driver);
		testScreeningLocatorPageHelper = new ScreeningLocatorPageHelper(driver);
		testManagePhysiciansPageHealper = new ManagePhysiciansPageHealper(driver) ;
	}

	//@BeforeClass
	public void browserSetUp() 
	{
		//driver.manage().window().maximize();
		//openURL();
		System.out.println("Browzser............");

	}


	//@AfterClass
	@AfterTest
	public void CloseURL()
	{
		driver.close();
		driver.quit();
	}	


	@AfterSuite
	public void afterMainMethod()
	{	
		loginHelper = null;	
		dashboardPageHelper= null;
		dashboardPageHelper=null;
		testResultsPageHelper=null;
		testOrderingPageHelper=null;
		// driver.quit(); 
	}

	public WebDriver getWebDriver()
	{
		return driver;
	}
	
	public AppiumDriver getMobileDriver()
	{
		driver1=(AppiumDriver) driver;
		return driver1;
	}

	public String switchPreviewWindow()
	{
		Set<String> windows = getWebDriver().getWindowHandles();
		Iterator<String> iter = windows.iterator();		
		String parent = iter.next();
		getWebDriver().switchTo().window(iter.next());
		return parent;
	}

	public void openURL(){
		//String applicationUrl = "https://staging-connect.natera.com";
		String applicationUrl = propertyReader.readApplicationFile("WebURL");
		getWebDriver().get(applicationUrl);			
	}

}
