package com.natera.util;

import org.testng.ITestContext;
import org.testng.ITestListener;
 
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.natera.util.DriverTestCase;


public class NateraReport implements ITestListener
{
	public PropertyReader propertyReader;
	
	 
    public void onStart(ITestContext arg0) {
 
         System.out.println("Start Of Execution(TEST)->"+arg0.getName());
 
    }
 
    public void onTestStart(ITestResult arg0) {
 
        System.out.println("Test Started->"+arg0.getName());
 
    }
 
    public void onTestSuccess(ITestResult arg0) {
 
       // Take screen shot for the Pass test case
    	//captureScreenShot(arg0, "pass");
 
    }
 
    public void onTestFailure(ITestResult arg0) {
 
    	// Take screen shot for fail test case
    	captureScreenShot(arg0, "fail");
             
    }
    
    public void onTestSkipped(ITestResult arg0) {
 
        System.out.println("Test Skipped->"+arg0.getName());
     }
    
   public void onFinish(ITestContext arg0) {
 
      System.out.println("END Of Execution(TEST)->"+arg0.getName());
 
    }
 
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
 
        // TODO Auto-generated method stub
    
    }
    
    
 // Function to capture screenshot.
    public void captureScreenShot(ITestResult result, String status) {
    	
     // AndroidDriver driver=ScreenshotOnPassFail.getDriver();
     String destDir = "";
     String passfailMethod = result.getMethod().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName();
     // To capture screenshot.
     File scrFile = ((TakesScreenshot) DriverTestCase.driver).getScreenshotAs(OutputType.FILE);
     DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
     // If status = fail then set folder name "screenshots/Failures"
     if (status.equalsIgnoreCase("fail")) {
      destDir = "screenshot/Failures";
     }
     // If status = pass then set folder name "screenshots/Success"
     else if (status.equalsIgnoreCase("pass")) {
      destDir = "screenshot/Success";
     }

     // To create folder to store screenshots
     new File(destDir).mkdirs();
     // Set file name with combination of test class name + date time.
     String destFile = passfailMethod + " - " + dateFormat.format(new Date()) + ".png";

     try {
      // Store file at destination folder location
      FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
     } catch (IOException e) {
      e.printStackTrace();
     }
    }
}
