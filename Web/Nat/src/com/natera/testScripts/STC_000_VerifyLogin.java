package com.natera.testScripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.natera.util.DriverTestCase;
@Listeners(com.natera.util.NateraReport.class)
public class STC_000_VerifyLogin extends DriverTestCase
{

	@Test
	public void loginInToApplication() throws InterruptedException, IOException
	{			
		WebDriverWait wait = new WebDriverWait(getWebDriver(), 100);
		
		//Client Log into the application with valid credential
		String usernameClient =  propertyReader.readApplicationFile("ClientEmailId");
		System.out.println(usernameClient);
		String passwordClient =  propertyReader.readApplicationFile("ClientPassword");			
		loginHelper.enterUserEmail(usernameClient);
		loginHelper.enterUserPassword(passwordClient);
		loginHelper.clickLogin();
		
		WebElement el = getWebDriver().findElement(By.xpath(""));
		wait.until(ExpectedConditions.elementToBeClickable(el));
		
		//Verify Dashboard page
		dashboardPageHelper.verifyDashboardHeader();

	}
}
