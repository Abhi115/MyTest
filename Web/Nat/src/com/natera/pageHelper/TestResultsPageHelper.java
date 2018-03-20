package com.natera.pageHelper;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.natera.locator.LocatorReader;
import com.natera.util.DriverHelper;


public class TestResultsPageHelper extends DriverHelper 
{
	private LocatorReader testResultsPage;

	public TestResultsPageHelper(WebDriver driver)
	{
		super(driver);	
		testResultsPage = new LocatorReader("TestResults.xml");
	}
	String locator;

	//Verify TestResult page with its header text
	public void ViewTestResult()
	{
		String demo = "//button[@id='navbar-toggle']";
		clickOnMobile(demo);
		waitForWorkArround(2000);

		locator = testResultsPage.getLocator("MenuNatera.TestResults");
		clickOn(locator);

		locator = testResultsPage.getLocator("TestResultPage.PageHeader");
		String headerLocaterTxt= getText(locator);
		String targetTxt= testResultsPage.getLocator("PageData.TestResultPageHeaderTxt");
		Assert.assertTrue(headerLocaterTxt.contains(targetTxt), "After clicking Test Result, it is not redirected to correct page.");	
	}

	String tablelocator;

	//Verification of Cases per page dropdown functionality
	public void CasesPerPage(String testNumbers) throws InterruptedException
	{

		locator= testResultsPage.getLocator("TestResultPage.ResultCountDropdown");
		Select casesDropdown=new Select(getWebDriver().findElement(By.xpath(locator)));
		casesDropdown.selectByVisibleText(testNumbers);
		Assert.assertTrue(getText(locator).contains(testNumbers), "Unable to select value in nubler of cases per page");

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		tablelocator= testResultsPage.getLocator("TestResultPage.TableRows");
		int rowCount = TableRowCount(tablelocator);	
		rowCount--;				
		//System.out.println("count--"+ rowCount);
		String rowCountTxt = Integer.toString(rowCount);	   			
		Assert.assertEquals(rowCountTxt, testNumbers, "Test result page does not display correct number of results." );					
	}

	//Verification of Cases per page dropdown functionality
	public void patientSelection()
	{	
		String checkedLicator = testResultsPage.getLocator("TestResultPage.SelectAllPatientChecked");
		String uncheckedLocator = testResultsPage.getLocator("TestResultPage.SelectAllPatientNotChecked");
		Assert.assertTrue(isElementPresent(uncheckedLocator), "CheckBox is checked and we are looking it unchecked..");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		locator= testResultsPage.getLocator("TestResultPage.SelectAllPatientChecked");
		clickOn(locator);

		Assert.assertTrue(isElementPresent(checkedLicator), "CheckBox is not checked.");			 

		tablelocator= testResultsPage.getLocator("TestResultPage.TableRows");
		int rowCount = TableRowCount(tablelocator);

		for(int i=1; i<=rowCount-1; i++)
		{	
			//WebElement resultElement= getWebDriver().findElement(By.xpath("//table[@id='case-results-table']/tbody/tr["+i+"]"));
			WebElement resultElement= getWebDriver().findElement(By.xpath("//table[contains(@class,'table table-hover table-condensed dataTable')]/tbody/tr["+i+"]"));
			String checkboxClassStatus= resultElement.getAttribute("class");
			System.out.println("my value--" + checkboxClassStatus);

			Assert.assertTrue(checkboxClassStatus.contains("case-results-table__result-row selected"), "This patient is not selected.");									
		} 
	}

	public void downloadPatientResults()
	{
		String checkedLicator = testResultsPage.getLocator("TestResultPage.SelectAllPatientChecked");
		locator= testResultsPage.getLocator("TestResultPage.SelectAllPatientCheckbox");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		clickOn(locator);


		Assert.assertTrue(isElementPresent(checkedLicator), "CheckBox is not checked.");

		locator= testResultsPage.getLocator("TestResultPage.DownloadResultsBtn");
		clickOn(locator);				
	}

	/*
			public void ResultFilter(String filterType, String filterKey) throws InterruptedException
			{
				String temp=null;
				if (filterType =="Test")
				{
					temp="4";
				}
				else if (filterType =="Status")
				{
					temp="5";
				}
				else if (filterType =="Result")
				{
					temp="8";
				}
				else if (filterType =="Physician")
				{
					temp="9";
				}

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

					WebElement resultElement1= getWebDriver().findElement(By.xpath("//th[contains(text(),'"+filterType+"')]/div/button"));
					resultElement1.click();

					WebElement resultElement= getWebDriver().findElement(By.xpath("//table[@id='case-results-table']/thead/tr/th["+temp+"]/div/ul/li[@class='dropdown-footer']/button[contains(text(),'None')]"));
					resultElement.click();						

					resultElement= getWebDriver().findElement(By.xpath("//input[@value='"+filterKey+"']"));
					resultElement.click();

					locator = testResultsPage.getLocator("TestResultPage.PageHeader");
					clickOn(locator);

					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tablelocator= testResultsPage.getLocator("TestResultPage.TableRows");
					int rowCount = TableRowCount(tablelocator);
					String resultTxt;
					String targetTxt= filterKey;					

					for(int i=1; i<=rowCount; i++)
					{					
						resultElement= getWebDriver().findElement(By.xpath("//table[@id='case-results-table']/tbody/tr["+i+"]/td["+temp+"]"));					
						resultTxt = resultElement.getText();

						WebDriverWait wait = new WebDriverWait(getWebDriver(), 90);
					    wait.until(ExpectedConditions.textToBePresentInElement(resultElement, targetTxt));					    
						Assert.assertTrue(resultTxt.contains(targetTxt), "Test result is not having "+filterKey+" test");						
					}				

			}
	 */
	// Abhishek
	public void ResultFilter(String filterType, String filterKey) throws InterruptedException
	{

		String temp=null;
		if (filterType =="Test")
		{
			temp="5";
		}
		else if (filterType =="Status")
		{
			temp="6";
		}
		else if (filterType =="Result")
		{
			temp="7";
		}
		else if (filterType =="Physician")
		{
			temp="8";
		}

		// Enter Data in search Box
		locator= testResultsPage.getLocator("TestResultPage.SearchBox");
		sendKeys(locator, filterKey);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tablelocator= testResultsPage.getLocator("TestResultPage.TableRows");
		int rowCount = TableRowCount(tablelocator);

		String resultTxt;
		String targetTxt = filterKey;



		for(int i=1; i < rowCount-1; i++)
		{					
			WebElement resultElement = getWebDriver().findElement(By.xpath("//table[contains(@class,'table table-hover table-condensed dataTable')]/tbody/tr["+i+"]/td["+temp+"]"));					
			resultTxt = resultElement.getText();
			//System.out.println("my value- "+ resultElement);

			WebDriverWait wait = new WebDriverWait(getWebDriver(), 90);
			wait.until(ExpectedConditions.textToBePresentInElement(resultElement, targetTxt));					    
			Assert.assertTrue(resultTxt.contains(targetTxt), "Test result is not having "+filterKey+" test");						
		}			


		//clearTextField(locator);
		//sendKeys(locator, " ");


	}


	public void SearchBox(String searchType, String searchKey) throws InterruptedException
	{
		//String filterText = "Horizon";
		locator= testResultsPage.getLocator("TestResultPage.SearchBox");
		sendKeys(locator, searchKey);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tablelocator= testResultsPage.getLocator("TestResultPage.TableRows");
		int rowCount = TableRowCount(tablelocator);

		String resultTxt;
		String targetTxt = searchKey;

		if(searchType == "ID")
		{
			for(int i=1; i < rowCount-1; i++)
			{
				resultTxt= getWebDriver().findElement(By.xpath("//table[contains(@class,'table table-hover table-condensed dataTable')]/tbody/tr["+i+"]/td[2]")).getText();
				Assert.assertTrue(resultTxt.contains(targetTxt), "Test result is not of the same case ID.");			
			}

		}else
			if(searchType == "Test")
			{
				for(int i=1; i<=rowCount-1; i++)
				{
					resultTxt= getWebDriver().findElement(By.xpath("//table[contains(@class,'table table-hover table-condensed dataTable')]/tbody/tr["+i+"]/td[5]")).getText();						
					Assert.assertTrue(resultTxt.contains(targetTxt), "Test result is not of the same Test type.");			
				}
			}else
				if(searchType == "Status")
				{
					for(int i=1; i<=rowCount-1; i++)
					{
						resultTxt= getWebDriver().findElement(By.xpath("//table[contains(@class,'table table-hover table-condensed dataTable')]/tbody/tr["+i+"]/td[6]")).getText();
						Assert.assertTrue(resultTxt.contains(targetTxt), "Test result is not of Delivered status.");			
					}
				}else
					if(searchType == "Result")
					{
						waitForWorkArround(2000);
						for(int i=1; i<=rowCount-1; i++)
						{
							resultTxt= getWebDriver().findElement(By.xpath("//table[contains(@class,'table table-hover table-condensed dataTable')]/tbody/tr["+i+"]/td[7]")).getText();
							Assert.assertTrue(resultTxt.contains(targetTxt), "Test result is not of same result type.");			
						}
					}else
						if(searchType == "Physician")
						{
							for(int i=1; i<=rowCount-1; i++)
							{
								resultTxt= getWebDriver().findElement(By.xpath("//table[contains(@class,'table table-hover table-condensed dataTable')]/tbody/tr["+i+"]/td[9]")).getText();
								Assert.assertTrue(resultTxt.contains(targetTxt), "Test result is not of same physician.");			
							}
						}


		clearTextField(locator);
		sendKeys(locator, " ");
	}

	//			public void PatientDetails() throws InterruptedException
	//			{
	//				
	//				int rowCount = TableRowCount(tablelocator);
	//				String testResult = testResultsPage.getLocator("PageData.TestResult");
	//				String resultTxt;
	//				for(int i=1; i<=rowCount-1; i++)
	//				{					
	//					resultTxt= getWebDriver().findElement(By.xpath("//table[@id='case-results-table']/tbody/tr["+i+"]/td[2]")).getText();
	//					System.out.println("My value -- " + resultTxt);
	//					
	//					if(resultTxt.equalsIgnoreCase(testResult))
	//					{
	//						getWebDriver().findElement(By.xpath("//table[@id='case-results-table']/tbody/tr["+i+"]/td[2]")).click();
	//						break;
	//					}					
	//				}
	//				
	//				locator= testResultsPage.getLocator("TestResultPage.PatientPageHeader");
	//				isTextPresent(locator, "Results For:");
	//			}		
	//			

	//Abhishek

	public void PatientDetails() throws InterruptedException
	{
		tablelocator= testResultsPage.getLocator("TestResultPage.TableRows");
		int rowCount = TableRowCount(tablelocator);
		String testResult = testResultsPage.getLocator("PageData.TestResult");
		System.out.println("My value -- " + testResult);
		String resultTxt;
		for(int i=1; i<=rowCount-1; i++)
		{					
			resultTxt= getWebDriver().findElement(By.xpath("//div[contains(@class,'link-cell__wrapper')]/a/div/span[contains(@class,'result-info status status-danger')]")).getText();
			System.out.println("My value -- " + resultTxt);

			Thread.sleep(1000);
			if(resultTxt.equalsIgnoreCase(testResult))
			{
				getWebDriver().findElement(By.xpath("//div[contains(@class,'link-cell__wrapper')]/a/div/span[contains(@class,'result-info status status-danger')]")).click();
				break;
			}					
		}
		Thread.sleep(4000);				
		locator= testResultsPage.getLocator("TestResultPage.PatientPageHeader");
		isTextPresent(locator, "Results For:");
	}

	public void ReleasePatient() throws InterruptedException
	{
		locator= testResultsPage.getLocator("TestResultPage.ReleasePatientBtn");
		clickOn(locator);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		locator= testResultsPage.getLocator("ReleasePatientPopup.EditEmailId");
		clickOn(locator);				

		locator= testResultsPage.getLocator("ReleasePatientPopup.PatientEmailBox");
		clearTextField(locator);
		sendKeys(locator, "akhileshk@360logica.com");
		locator= testResultsPage.getLocator("ReleasePatientPopup.SendMeCopyCheckBox");
		clickOn(locator);
		//locator= testResultsPage.getLocator("ReleasePatientPopup.PatientNotesBtn");
		//clickOn(locator);

		locator= testResultsPage.getLocator("ReleasePatientPopup.PatientNoteTxtBox");
		sendKeys(locator, "This is patient notes....");

		locator= testResultsPage.getLocator("ReleasePatientPopup.ReleaseAuthorization");
		clickOn(locator);

		locator= testResultsPage.getLocator("ReleasePatientPopup.ReleaseResultBtn");
		clickOn(locator);

		String targetAlertMsg= "We've sent the patient an e-mail with instructions on viewing this result";
		locator= testResultsPage.getLocator("ReleasePatientPopup.SendResultMsg");
		String alertMsgTxt= getText(locator);
		System.out.println("My value -- " + alertMsgTxt);
		Assert.assertTrue(alertMsgTxt.contains(targetAlertMsg), "Release result is not sent correctly.");
	}

	public void DownloadResult()
	{
		locator= testResultsPage.getLocator("TestResultPage.DownloadResultBtn");
		clickOn(locator);
	}


	//**********************
	public int TableRowCount(String tblLocator)
	{
		List<WebElement> columnElements = getWebDriver().findElements(By.xpath(tblLocator));
		int rowNumber = columnElements.size();
		return rowNumber;				
	}



}




