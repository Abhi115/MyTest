package com.scripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.util.DriverTestCase;
import com.util.ExcelReader;
import com.util.Utilities;

public class FetchBuildingType extends DriverTestCase {

	private String oppID = "260906";
	private String oppName;

	@BeforeClass
	public void doLogin() throws Exception {
		setUp();
		test_01_Navigate_From_C4C_To_CPQ();
	}

	public void test_01_Navigate_From_C4C_To_CPQ() {
		getWebDriver().navigate().to(c4c_url);
		c4cappPage._waitForJStoLoad();
		c4cappPage.loginInToC4CApplication(c4c_userName, c4c_Password);
		c4cappPage.verifyTitle("SAP Hybris Cloud for Customer");
		c4cappPage.goToOpportunityPage();
		opportunityPage.searchOpportunity(oppID);
		opportunityPage.selectParticularOpportunity(Integer.parseInt(oppID));
		oppName = opportunityPage.getOpportunityName();
	}

	@Test
	public void getBuildingType() throws IOException, InterruptedException {
		opportunityPage.switchWindow(oppName);
		opportunityPage.addNewQuoteFromOpportunity();
		reportLog("Add new Quote having Ethernet Hub and Spoke");

		opportunityPage.switchWindow("Transaction");
		reportLog("Switching on CQP tab");

		String Description = "Quote_Desc_" + Utilities.getRandomInteger(1, 9999);

		transactionPage.sendKeys(transactionPage.quoteDescription, Description);
		reportLog("Enter description: " + Description);

		productListPage.AddproductType("Hub");

		ExcelReader ex = new ExcelReader();
		String sheetName = "Sheet1";
		int rows = ex.getNumberofRows(sheetName);

		String bandwidth = "10 Gbps";
		for (int i = 1; i < rows; i++) {

			String siteAddress = ex.getColumnValueFromExcel(sheetName, i, 1);

			modelConfigurationPage.enterHubAddress(bandwidth, siteAddress);

			modelConfigurationPage.click(modelConfigurationPage.update);
			reportLog("Click on to Update button");

			modelConfigurationPage.checkConnectivity();
			reportLog("Click on Update button then CheckConnectivity button");

			String buildingType = modelConfigurationPage.getBuildingType();

			modelConfigurationPage.enterWriteBuildingType(siteAddress, buildingType);

			modelConfigurationPage.navigateToSiteAddress();

		}

	}

	@Test
	public void testEthernetLineResiliency() throws InterruptedException, IOException {

		opportunityPage.switchWindow(oppName);
		opportunityPage.addNewQuoteFromOpportunity();
		reportLog("Add new Quote");

		opportunityPage.switchWindow("Transaction");
		reportLog("Switching on CQP tab");

		String Description = "Quote_Desc_" + Utilities.getRandomInteger(1, 9999);

		transactionPage.sendKeys(transactionPage.quoteDescription, Description);
		reportLog("Enter description: " + Description);

		productListPage.AddproductType("Ethernet");
		reportLog("Adding EtherNet Product");

		c4cappPage.verifyTitle("Model Configuration");
		reportLog("Verifying the title 'Model Configuration'");

		ExcelReader ex = new ExcelReader();
		String sheetName = "EthernetLine";
		int rows = ex.getNumberofRows(sheetName);

		String bandwidth = "10 Gbps";
		for (int i = 1; i < rows; i++) {

			String aEndAddress = ex.getColumnValueFromExcel(sheetName, i, 0);
			String bEndAddress = ex.getColumnValueFromExcel(sheetName, i, 1);

			modelConfigurationPage.selectBandwidth(bandwidth);

			modelConfigurationPage.enterAddresses(aEndAddress, bEndAddress);

			modelConfigurationPage.click(modelConfigurationPage.update);
			reportLog("Click on to Update button");

			modelConfigurationPage.checkConnectivity();
			reportLog("Click on Update button then CheckConnectivity button");

			String coverage = modelConfigurationPage.getCoverage();

			String resiliency = modelConfigurationPage.getSiteResiliency();

			modelConfigurationPage.enterWriteBuildingType(aEndAddress, bEndAddress, resiliency);

			modelConfigurationPage.navigateToSiteAddress();

		}

	}

	@Test
	public void testEthernetHubResiliency() throws InterruptedException, IOException {

		opportunityPage.switchWindow(oppName);
		opportunityPage.addNewQuoteFromOpportunity();
		reportLog("Add new Quote");

		opportunityPage.switchWindow("Transaction");
		reportLog("Switching on CQP tab");

		String Description = "Quote_Desc_" + Utilities.getRandomInteger(1, 9999);

		transactionPage.sendKeys(transactionPage.quoteDescription, Description);
		reportLog("Enter description: " + Description);

		productListPage.AddproductType("Hub");
		reportLog("Adding EtherNet Product");

		c4cappPage.verifyTitle("Model Configuration");
		reportLog("Verifying the title 'Model Configuration'");

		ExcelReader ex = new ExcelReader();
		String sheetName = "EthernetHub";
		int rows = ex.getNumberofRows(sheetName);

		String bandwidth = "10 Gbps";
		for (int i = 1; i < rows; i++) {

			String siteAddress = ex.getColumnValueFromExcel(sheetName, i, 1);

			modelConfigurationPage.enterHubAddress(bandwidth, siteAddress);

			modelConfigurationPage.click(modelConfigurationPage.update);
			reportLog("Click on to Update button");

			modelConfigurationPage.checkConnectivity();
			reportLog("Click on Update button then CheckConnectivity button");

			modelConfigurationPage.navigateToSiteDetail();

			String resiliency = modelConfigurationPage.getSiteResiliency();

			modelConfigurationPage.enterWriteBuildingType(siteAddress, resiliency);

			modelConfigurationPage.navigateToSiteAddress();

		}

	}

	@Test
	public void testEthernetHubAndSpokeResiliency() throws InterruptedException, IOException {

		opportunityPage.switchWindow(oppName);
		opportunityPage.addNewQuoteFromOpportunity();
		reportLog("Add new Quote");

		opportunityPage.switchWindow("Transaction");
		reportLog("Switching on CQP tab");

		String Description = "Quote_Desc_" + Utilities.getRandomInteger(1, 9999);

		transactionPage.sendKeys(transactionPage.quoteDescription, Description);
		reportLog("Enter description: " + Description);

		ExcelReader ex = new ExcelReader();
		String sheetName = "EthernetHub_Spoke";
		int rows = ex.getNumberofRows(sheetName);

		String hubBandwidth = "10 Gbps";
		String spokeBandwidth = "1 Gbps";
		for (int i = 1; i < rows; i++) {
			System.out.println("Test");
			String hubResiliency = ex.getColumnValueFromExcel(sheetName, i, 1);
			String hubAddress = ex.getColumnValueFromExcel(sheetName, i, 0);
			String spokeAddress = ex.getColumnValueFromExcel(sheetName, i, 2);

			String spokeResiliency = modelConfigurationPage.AddHubAndSpokeProduct(hubBandwidth, hubAddress,
					hubResiliency, spokeBandwidth, spokeAddress);

			modelConfigurationPage.enterWriteBuildingType(hubAddress, spokeAddress, spokeResiliency);

		}

	}

	@Test
	public void testEthernetWaveResiliency() throws InterruptedException, IOException {

		opportunityPage.switchWindow(oppName);
		opportunityPage.addNewQuoteFromOpportunity();
		reportLog("Add new Quote");

		opportunityPage.switchWindow("Transaction");
		reportLog("Switching on CQP tab");

		String Description = "Quote_Desc_" + Utilities.getRandomInteger(1, 9999);

		transactionPage.sendKeys(transactionPage.quoteDescription, Description);
		reportLog("Enter description: " + Description);

		productListPage.AddproductType("Wave");
		reportLog("Adding Wave Product");

		c4cappPage.verifyTitle("Model Configuration");
		reportLog("Verifying the title 'Model Configuration'");

		ExcelReader ex = new ExcelReader();
		String sheetName = "Wave";
		int rows = ex.getNumberofRows(sheetName);

		String bandwidth = "10 Gbps";
		for (int i = 1; i < rows; i++) {

			String aEndAddress = ex.getColumnValueFromExcel(sheetName, i, 0);
			String bEndAddress = ex.getColumnValueFromExcel(sheetName, i, 1);

			// modelConfigurationPage.selectBandwidth(bandwidth);

			modelConfigurationPage.enterAddresses(aEndAddress, bEndAddress);

			modelConfigurationPage.click(modelConfigurationPage.update);
			reportLog("Click on to Update button");

			modelConfigurationPage.checkConnectivity();
			reportLog("Click on Update button then CheckConnectivity button");

			String coverage = modelConfigurationPage.getCoverage();

			String resiliency = modelConfigurationPage.getSiteResiliency();

			modelConfigurationPage.enterWriteBuildingType(aEndAddress, bEndAddress, resiliency);

			modelConfigurationPage.navigateToSiteAddress();

		}

	}

	@Test
	public void testCoverage() throws InterruptedException, IOException {

		opportunityPage.switchWindow(oppName);
		opportunityPage.addNewQuoteFromOpportunity();
		reportLog("Add new Quote");

		opportunityPage.switchWindow("Transaction");
		reportLog("Switching on CQP tab");

		String Description = "Quote_Desc_" + Utilities.getRandomInteger(1, 9999);

		transactionPage.sendKeys(transactionPage.quoteDescription, Description);
		reportLog("Enter description: " + Description);

		productListPage.AddproductType("Ethernet");
		reportLog("Adding EtherNet Product");

		ExcelReader ex = new ExcelReader();
		String fileName = "GetDetails";
		String sheetName = "Sheet1";
		int rows = ex.getRows(fileName, sheetName);

		String bandwidth = "2 Mbps";
		for (int i = 1; i <= rows; i++) {

			String aEndAddress = ex.getColumnValueFromExcel(fileName, sheetName, i, 2);
			String bEndAddress = ex.getColumnValueFromExcel(fileName, sheetName, i, 4);

			modelConfigurationPage.selectBandwidth(bandwidth);

			modelConfigurationPage.enterAddresses(aEndAddress, bEndAddress);

			modelConfigurationPage.click(modelConfigurationPage.update);
			reportLog("Click on to Update button");

			modelConfigurationPage.checkConnectivity();
			reportLog("Click on Update button then CheckConnectivity button");

			String coverage = modelConfigurationPage.getCoverage();
			String buildingtype = modelConfigurationPage.buildingType();

			modelConfigurationPage.enterWriteBuildingType(aEndAddress, bEndAddress, coverage, buildingtype);

			modelConfigurationPage.navigateToSiteAddress();

		}
	}
	
}
