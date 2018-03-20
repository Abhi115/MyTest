package com.natera.pageHelper;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.natera.locator.LocatorReader;
import com.natera.util.DriverHelper;

public class OrderTestPageHelper extends DriverHelper{

	private LocatorReader orderTest;

	public OrderTestPageHelper(WebDriver driver) {
		super(driver);	
		orderTest = new LocatorReader("OrderTest.xml");
	}
	String locator;

	public void CheckOrderTestPage()
	{
		String demo = "//button[@id='navbar-toggle']";
		clickOnMobile(demo);
		waitForWorkArround(2000);

		locator = orderTest.getLocator("MenuNatera.TestOrdering");
		clickOn(locator);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		locator = orderTest.getLocator("MenuNatera.SubMenuTestOrdering.TestAOrder");
		clickOn(locator);

		locator = orderTest.getLocator("OrderATestPage.PageHeader");

		String pageHeaderTxt = getText(locator);
		String tergerTxt = orderTest.getLocator("OrderAtestPageData.HeaderTxt");

		Assert.assertTrue(pageHeaderTxt.contains(tergerTxt), "On clicking 'Order A Test', it is not navigated to correct page.");
	}


	String resultTxt;
	String targetTxt;

	public void saveAndPrintFormWithoutData()
	{
		locator = orderTest.getLocator("OrderATestPage.SaveAndPrintForm");
		clickOn(locator);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement  element=getWebDriver().findElement(By.xpath("//*[@id='test-selection-modal' and @role='dialog']"));
		String temp =element.getAttribute("style");
		Assert.assertTrue(temp.contains("block"), "Popup is not appearing.");

		locator = orderTest.getLocator("OrderATestPage.TestMissingPop");
		WaitForElementPresent(locator, 200);
		resultTxt= getText(locator);

		Assert.assertTrue(resultTxt.contains("Missing Test Selection"), "Message pop message does not appear.");

		locator = orderTest.getLocator("OrderATestPage.TestMissingPopEditBtn");
		clickOn(locator);

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		element=getWebDriver().findElement(By.xpath("//*[@id='test-selection-modal' and @role='dialog']"));
		temp =element.getAttribute("style");
		Assert.assertTrue(temp.contains("none"), "Popup did not closed.");		  
	}

	// 


	public void fillingForm(String formType, String pFirstName) throws Exception
	{	   
		String testType;

		testType = formType;

		if( testType == "Panorama + Horizon" || testType == "Panorama")
		{
			locator = orderTest.getLocator("OrderATestPage.SelectATest.PanoramaAndHorizon");
			clickOn(locator);
		}

		if(testType == "Panorama")
		{
			locator = orderTest.getLocator("OrderATestPage.SelectATest.Panorama");
			clickOn(locator);
		}

		if(testType == "Horizon")
		{
			locator = orderTest.getLocator("OrderATestPage.SelectATest.Horizon");
			clickOn(locator);
		}

		if(testType == "Anora")
		{
			locator = orderTest.getLocator("OrderATestPage.SelectATest.Anora");
			clickOn(locator);
		}

		//********Patient Information********

		//Filling first name
		locator = orderTest.getLocator("OrderATestPage.PatientInfo.FName");
		sendKeys(locator, pFirstName);

		//Filling last name
		locator = orderTest.getLocator("OrderATestPage.PatientInfo.LName");
		String lName= orderTest.getLocator("InputData.LName");
		sendKeys(locator, lName);

		//Filling DOB
		locator = orderTest.getLocator("OrderATestPage.PatientInfo.DOB");
		String dOB= orderTest.getLocator("InputData.DOB");
		sendKeys(locator, dOB);

		//Filling Address
		locator = orderTest.getLocator("OrderATestPage.PatientInfo.Adrs");
		String aDdress= orderTest.getLocator("InputData.Adrs");
		sendKeys(locator, aDdress);

		//Filling city name
		locator = orderTest.getLocator("OrderATestPage.PatientInfo.City");
		String cityName= orderTest.getLocator("InputData.City");
		sendKeys(locator, cityName);

		//Filling State name
		Select element=new Select(getWebDriver().findElement(By.xpath("//select[@id='patient_info[state]']")));
		element.selectByVisibleText("New York");

		//Filling Zip code
		locator = orderTest.getLocator("OrderATestPage.PatientInfo.Zip");
		String zip= orderTest.getLocator("InputData.Zip");
		sendKeys(locator, zip);

		//Filling patient email id
		locator = orderTest.getLocator("OrderATestPage.PatientInfo.Email");
		String emailAddress= orderTest.getLocator("InputData.Email");
		sendKeys(locator, emailAddress);

		//Filling phone number
		locator = orderTest.getLocator("OrderATestPage.PatientInfo.PhoneNo");
		String phoneNumber= orderTest.getLocator("InputData.PhoneNo");
		sendKeys(locator, phoneNumber);

		//Filling received status email status
		// Select receiveEmail=new Select(getWebDriver().findElement(By.xpath("//select[@id='patient_info[patient_would_like_to_receive_emails]']")));
		//receiveEmail.selectByVisibleText("Yes");             

		//Filling patient gender 
		Select patientGender=new Select(getWebDriver().findElement(By.xpath("//select[@id='patient_info[gender]']")));
		patientGender.selectByVisibleText("Female"); 

		try {
			Robot robot = new Robot();
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.delay(1000);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.delay(1000);

			robot.keyPress(KeyEvent.VK_Z);
			robot.delay(1000);
			robot.keyRelease(KeyEvent.VK_Z);
			robot.delay(1000);  

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		   WaitForElementPresent("//select[@id='patient_info[patient_pregnancy_status]']", 500);

		   if(isElementPresent("//select[@id='patient_info[patient_pregnancy_status]']")){
		   Select pregnancyStatus=new Select(getWebDriver().findElement(By.xpath("//select[@id='patient_info[patient_pregnancy_status]']")));
		   pregnancyStatus.selectByVisibleText("Z34.81 1st Trimester"); 
		   pregnancyStatus.selectByIndex(1);
		   System.out.println("Tets....................");
		   }
		 */

		//Fill date of sample collected
		locator = orderTest.getLocator("OrderATestPage.PatientInfo.DateOfSampleCollection");
		String collectionDate= orderTest.getLocator("InputData.DateOfSampleCollection");
		sendKeys(locator, collectionDate);



		//************Ordering Clinician ************

		//Fill Clinic name
		locator = orderTest.getLocator("OrderATestPage.OrderingClinician.SelectClinic");
		Select organizationName=new Select(getWebDriver().findElement(By.xpath(locator)));
		organizationName.selectByVisibleText("Reproductive Care Center - Utah");

		/* 

		   WaitForElementPresent("//div[contains(@class, 'has-success')]/select[@id='clinic_id']", 200);

		   if(isElementPresent("//div[contains(@class, 'has-success')]/select[@id='clinic_id']"))
		   {
			   System.out.println("Test>>>>>>>>>>>>>>>>>>");
		   }

		   getWebDriver().findElement(By.xpath("//*[@id='anchor-form-section-ordering-clinician']")).click(); 
		   System.out.println("Test>>>>>>>>>>>>>>>>>>");


		   //Filling Clinic Address
		   locator = orderTest.getLocator("OrderATestPage.OrderingClinician.SelectAddress");
		   Select organizationAddr=new Select(getWebDriver().findElement(By.xpath(locator)));
		   organizationAddr.selectByVisibleText("5574 Earnestine Dale Port Nelliechester, Idaho, 19953"); 
		 */


		try {
			Robot robot = new Robot();
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.delay(1000);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.delay(1000);

			robot.keyPress(KeyEvent.VK_C);
			robot.delay(1000);
			robot.keyRelease(KeyEvent.VK_C);
			robot.delay(1000);  

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Filling Telephone
		locator = orderTest.getLocator("OrderATestPage.OrderingClinician.Telephone");
		clickOn(locator);
		String telephoneNumber= orderTest.getLocator("InputData.TelephoneNumber");		   
		sendKeys(locator, telephoneNumber);

		//Filling Ordering Clinician
		locator = orderTest.getLocator("OrderATestPage.OrderingClinician.OrderingClinicianName");
		Select OClinicianName=new Select(getWebDriver().findElement(By.xpath(locator)));
		OClinicianName.selectByVisibleText("Brad Swelstad"); 

		//Filling Referring Clinic name
		locator = orderTest.getLocator("OrderATestPage.OrderingClinician.ReferringClinician");
		sendKeys(locator, "Test Clinic"); 

		//Filling Fax number
		locator = orderTest.getLocator("OrderATestPage.OrderingClinician.Fax");
		sendKeys(locator, "4576132"); 


		//****************Payment info***********

		locator = orderTest.getLocator("OrderATestPage.PaymentInformation.BillClinic");
		clickOn(locator);

		Select selectItem;

		//****************Panorama Prenatal Screen ****************

		if( testType == "Panorama + Horizon" || testType == "Panorama")
		{

			//Filling Panorama Extended Panel 
			locator = orderTest.getLocator("OrderATestPage.PanoramaPrenatalScreen.PanoramaExtendedPanel");
			clickOn(locator);

			//Fetus Report Checkbox
			locator = orderTest.getLocator("OrderATestPage.PanoramaPrenatalScreen.fetusreportCheckbox");
			clickOn(locator); 

			//Height Feet
			locator = orderTest.getLocator("OrderATestPage.PanoramaPrenatalScreen.HeightFeet");
			sendKeys(locator, "5");

			//Height Inch
			locator = orderTest.getLocator("OrderATestPage.PanoramaPrenatalScreen.HeightInch");
			sendKeys(locator, "5");

			//Maternal Weight
			locator = orderTest.getLocator("OrderATestPage.PanoramaPrenatalScreen.MaternalWeight");
			sendKeys(locator, "50");


			//**********These fields are removed *********

			//		   locator = orderTest.getLocator("OrderATestPage.PanoramaPrenatalScreen.AgeWeeks");
			//		   sendKeys(locator, "10");
			//		   
			//		   locator = orderTest.getLocator("OrderATestPage.PanoramaPrenatalScreen.AgeDays");
			//		   sendKeys(locator, "3");


			//Filling data in 'Is this a twin'
			locator = orderTest.getLocator("OrderATestPage.PanoramaPrenatalScreen.TwinOrMultipleGestation");
			selectItem=new Select(getWebDriver().findElement(By.xpath(locator)));
			selectItem.selectByVisibleText("No");

			//Filling Date
			locator = orderTest.getLocator("OrderATestPage.PanoramaPrenatalScreen.DueDate");
			sendKeys(locator, "12/03/2016");

			//*****************These field has been removed************

			//		   locator = orderTest.getLocator("OrderATestPage.PanoramaPrenatalScreen.EggDonorOrSurrogateUsed");
			//		   selectItem=new Select(getWebDriver().findElement(By.xpath(locator)));
			//		   selectItem.selectByVisibleText("No");
			//		   
			//		   locator = orderTest.getLocator("OrderATestPage.PanoramaPrenatalScreen.KnownMicrodeletionCarrier");
			//		   selectItem=new Select(getWebDriver().findElement(By.xpath(locator)));
			//		   selectItem.selectByVisibleText("No");
			//		   
			//		   locator = orderTest.getLocator("OrderATestPage.PanoramaPrenatalScreen.fatherSampleBeSubmitted");
			//		   selectItem=new Select(getWebDriver().findElement(By.xpath(locator)));
			//		   selectItem.selectByVisibleText("No");

			//Diagnosis Codes checkbox selection
			locator = orderTest.getLocator("OrderATestPage.PanoramaPrenatalScreen.DiagnosisCodes.primigravida1sttrimester");
			clickOn(locator);

		}


		//***************Horizon Carrier Screen*************

		if( testType == "Panorama + Horizon" || testType == "Horizon")
		{

			//DMD checkbox
			locator = orderTest.getLocator("OrderATestPage.HorizonCarrierScreen.HorizonPanelOptions.DMD");
			clickOn(locator);

			//Tay-Sachs Enzyme checkbox
			locator = orderTest.getLocator("OrderATestPage.HorizonCarrierScreen.TaySachsEnzyme");
			clickOn(locator);

			locator = orderTest.getLocator("OrderATestPage.HorizonCarrierScreen.Ethnicity.AfricanAmerican");
			clickOn(locator);

			locator = orderTest.getLocator("OrderATestPage.HorizonCarrierScreen.CurrentlyUsingHormonalMedications");
			selectItem=new Select(getWebDriver().findElement(By.xpath(locator)));
			selectItem.selectByVisibleText("No");

			locator = orderTest.getLocator("OrderATestPage.HorizonCarrierScreen.ICDCodes.disorders228");
			clickOn(locator);
		}

		//***************Anora Carrier Screen*************

		if( testType == "Anora")
		{
			//Anora miscarriage test    
			locator = orderTest.getLocator("OrderATestPage.AnoraProductsOfConception.AnoraMiscarriageTestCheckBox");
			clickOn(locator);


			//***********This field has been removed
			//		   
			//		   //PregnancyLossDate
			//		   locator = orderTest.getLocator("OrderATestPage.AnoraProductsOfConception.PregnancyLossDate");
			//		   sendKeys(locator, "12/03/2015");
			//		   
			//Fill Age At Loss
			locator = orderTest.getLocator("OrderATestPage.AnoraProductsOfConception.AgeAtLoss");
			sendKeys(locator, "10");

			//This Pregnancy Was -> Singleton
			locator = orderTest.getLocator("OrderATestPage.AnoraProductsOfConception.PregnancyWas.Singleton");
			clickOn(locator);

			//EggDonor
			locator = orderTest.getLocator("OrderATestPage.AnoraProductsOfConception.EggDonor");
			selectItem=new Select(getWebDriver().findElement(By.xpath(locator)));
			selectItem.selectByValue("true");
			//selectItem.selectByVisibleText("Yes")

			//Surrogate 
			//		   locator = orderTest.getLocator("OrderATestPage.AnoraProductsOfConception.Surrogate");
			//		   selectItem=new Select(getWebDriver().findElement(By.xpath(locator)));
			//		   selectItem.selectByValue("true");
			//		   //selectItem.selectByVisibleText("Yes")

			try {
				Robot robot = new Robot();
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.delay(1000);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.delay(1000);

				robot.keyPress(KeyEvent.VK_Y);
				robot.delay(1000);
				robot.keyRelease(KeyEvent.VK_Y);
				robot.delay(1000);  

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			//SampleType->Fresh
			locator = orderTest.getLocator("OrderATestPage.AnoraProductsOfConception.SampleTypeFresh");
			clickOn(locator);

			//Blood Sample-> Biological Mother 
			locator = orderTest.getLocator("OrderATestPage.AnoraProductsOfConception.BloodSampleMother");
			clickOn(locator);

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//NameSamplSource
			locator = orderTest.getLocator("OrderATestPage.AnoraProductsOfConception.NameSamplSource");
			sendKeys(locator, "Akhilesh....");

			//ICD-10 Diagnosis Codes -> Missed abortion 
			locator = orderTest.getLocator("OrderATestPage.AnoraProductsOfConception.DiagnosisCodesMissed");
			clickOn(locator);
			//		   
			//		   
			//		   locator = orderTest.getLocator("OrderATestPage.AnoraProductsOfConception.Hospital_SurgicalCenterName");
			//		   sendKeys(locator, "360Logica");
			//		   
			//		   locator = orderTest.getLocator("OrderATestPage.AnoraProductsOfConception.SampleType.Paraffin");
			//		   clickOn(locator);
			//		   
			//		   
			//		   locator = orderTest.getLocator("OrderATestPage.AnoraProductsOfConception.BloodSampleCollectedFrom.BiologicalFather");
			//		   clickOn(locator);
			//		   
			//		   locator = orderTest.getLocator("OrderATestPage.AnoraProductsOfConception.SampleSourceName");
			//		   sendKeys(locator, "TestSample");
			//		   
			//		   locator = orderTest.getLocator("OrderATestPage.AnoraProductsOfConception.DiagnosisCodes.N96");
			//		   clickOn(locator);
			//		   
			//		   locator = orderTest.getLocator("OrderATestPage.AnoraProductsOfConception.DiagnosisCodes.MaternalCare");
			//		   clickOn(locator);
			//		   
			//		   locator = orderTest.getLocator("OrderATestPage.AnoraProductsOfConception.OtherCode");
			//		   sendKeys(locator, "Test code");	   

		}	   

	} 

	public void saveDraftForm()
	{	
		locator = orderTest.getLocator("OrderATestPage.SaveDraftBtn");
		clickOn(locator);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		locator = orderTest.getLocator("OrderATestPage.SaveToDreaftMsg");
		resultTxt = getText(locator);
		targetTxt = "The draft was saved successfully";
		Assert.assertTrue(resultTxt.contains(targetTxt), "Form not saved in draft.");

	}

	public void openDraftForm()
	{
		locator = orderTest.getLocator("OrderATestPage.DraftFormLink");
		clickOn(locator);
		locator = orderTest.getLocator("OrderATestPage.WarningMsgForDraftForm");
		resultTxt = getText(locator);
		targetTxt = "Please create a new test order for each patient. You cannot edit a prior test order to replace a new patient's information. This is because each test order form has a barcode (or case ID) that must be connected with only one patient.";
		Assert.assertTrue(resultTxt.contains(targetTxt), "Unable to open Draft Form.");
	}

	public void updateAndSaveDraftForm()
	{
		/*
		   locator = orderTest.getLocator("OrderATestPage.DraftFormLink");
		   clickOn(locator);
		   locator = orderTest.getLocator("OrderATestPage.WarningMsgForDraftForm");
		   resultTxt = getText(locator);
		   targetTxt = "Please create a new test order for each patient. You cannot edit a prior test order to replace a new patient's information. This is because each test order form has a barcode (or case ID) that must be connected with only one patient.";
		   Assert.assertTrue(resultTxt.contains(targetTxt), "Unable to open Draft Form.");

		 */

		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(99999);	
		String updatedName = "Arona Test"+randomInt;

		locator = orderTest.getLocator("OrderATestPage.PatientInfo.FName");
		//clearTextField(locator);
		sendKeys(locator, updatedName);		   

		locator = orderTest.getLocator("OrderATestPage.SaveChangesBtn");
		clickOn(locator);

		locator = orderTest.getLocator("OrderATestPage.SaveToDreaftMsg");
		resultTxt = getText(locator);
		targetTxt = "The draft was saved successfully";
		Assert.assertTrue(resultTxt.contains(targetTxt), "Unable to update and save Form");		   
	}

	public void savePrintDraft(String key) throws InterruptedException
	{

		if(key == "udpate"){
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(99999);	
			String updatedName = "Arona Test"+randomInt;

			locator = orderTest.getLocator("OrderATestPage.PatientInfo.FName");
			sendKeys(locator, updatedName);
		}

		String baseWindowHdl = getWebDriver().getWindowHandle();

		locator = orderTest.getLocator("OrderATestPage.SaveAndPrintForm");
		scrollingdown();
		clickOn(locator);

		Thread.sleep(2000);
		locator = orderTest.getLocator("OrderATestPage.PrintAnyway");		  
		clickOn(locator);

		//Go to New Window
		for (String winHandle : getWebDriver().getWindowHandles()) {
			getWebDriver().switchTo().window(winHandle);
		}

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getWebDriver().close();
		getWebDriver().switchTo().window(baseWindowHdl);		   
	}	   

}
