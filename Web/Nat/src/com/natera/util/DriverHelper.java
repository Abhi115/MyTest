package com.natera.util;

import io.appium.java_client.AppiumDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class DriverHelper
{

	protected WebDriver driver;
	protected AppiumDriver driver1;

	public DriverHelper(WebDriver webdriver)
	{
		driver = webdriver;	
		
	}

	public WebDriver getWebDriver()
	{
		return driver;
	}
	
	public AppiumDriver getMobileDriver()
	{
		driver1 = (AppiumDriver) driver;
		return driver1;
	}


	public void Log(String logMsg)
	{
		System.out.println(logMsg);
	}

	public By ByLocator(String locator) 
	{
		By result = null;

		if (locator.startsWith("//"))
		{
			result = By.xpath(locator);
		}
		else if (locator.startsWith("css=")) 
		{
			result = By.cssSelector(locator.replace("css=", ""));
		} 
		else if (locator.startsWith("#")) 
		{
			result = By.name(locator.replace("#", ""));			
		} 
		else if (locator.startsWith("link=")) {
			result = By.linkText(locator.replace("link=", ""));
		}
		else if(locator.startsWith("class=")) 
		{
			result=By.className(locator.replace("class=", ""));
		}
		else if(locator.startsWith("name=")) 
		{
			result=By.name(locator.replace("name=", ""));
		}		  
		else if(locator.startsWith("id=")) 
		{
			result=By.id(locator.replace("id=", ""));
		}
		else if(locator.startsWith("(")) {
			result = By.xpath(locator);
		}
		else 
		{
			result = By.id(locator);
		}
		return result;
	}


	public Boolean isElementPresent(String locator) 
	{
		Boolean result = false;
		try 
		{
			getWebDriver().findElement(ByLocator(locator));
			result = true;
		} catch (Exception ex) 
		{

		}

		return result;
	}


	public Boolean isElementDisplayed(String locator) 
	{

		Boolean result = false;
		try 
		{
			getWebDriver().findElement(ByLocator(locator)).isDisplayed();
			result = true;
		} catch (Exception ex) 
		{

		}
		return result;
	}


	public void WaitForElementPresent(String locator, int timeout)
	{

		for (int i = 0; i < timeout; i++) 
		{
			if (isElementPresent(locator)) 
			{
				break;
			}

			try 
			{
				Thread.sleep(1000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}

	public void WaitForElementNotPresent(String locator, int timeout) 
	{

		for (int i = 0; i < timeout; i++) 
		{
			if (!isElementPresent(locator)) 
			{
				break;
			}

			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	public void WaitForElementEnabled(String locator, int timeout) 
	{
		for (int i = 0; i < timeout; i++) 
		{
			if (isElementPresent(locator)) 
			{
				if (getWebDriver().findElement(ByLocator(locator)).isEnabled()) 
				{
					break;
				}
			}

			try 
			{
				Thread.sleep(1000);
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void WaitForElementNotEnabled(String locator, int timeout) 
	{

		for (int i = 0; i < timeout; i++) 
		{
			if (isElementPresent(locator)) 
			{
				if (!getWebDriver().findElement(ByLocator(locator)).isEnabled()) 
				{
					break;
				}
			}

			try 
			{
				Thread.sleep(1000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}

	public void WaitForElementVisible(String locator, int timeout) 
	{

		for (int i = 0; i < timeout; i++) 
		{
			if (isElementPresent(locator)) 
			{
				if (getWebDriver().findElement(ByLocator(locator)).isDisplayed()) 
				{
					break;
				}
			}

			try 
			{
				Thread.sleep(1000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}

	public void WaitForElementNotVisible(String locator, int timeout) 
	{

		for (int i = 0; i < timeout; i++) 
		{
			if (isElementPresent(locator)) 
			{
				if (!getWebDriver().findElement(ByLocator(locator)).isDisplayed()) 
				{
					break;
				}
			}

			try 
			{
				Thread.sleep(1000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}

	public void mouseOver(String locator)
	{		
		this.WaitForElementPresent(locator, 50);		
		WebElement el = getWebDriver().findElement(ByLocator(locator));

		Actions builder = new Actions(getWebDriver());    
		builder.moveToElement(el).build().perform();		
	}

	public void mouseDoubleClick(String locator)
	{
		this.WaitForElementPresent(locator, 50);		
		WebElement el = getWebDriver().findElement(ByLocator(locator));
		Actions builder = new Actions(getWebDriver());    
		builder.doubleClick(el).perform();
	}

	public void dragAndDrop(String draggable, String to)
	{
		WebElement elDraggable = getWebDriver().findElement(ByLocator(draggable));
		WebElement todrag = getWebDriver().findElement(ByLocator(to));
		Actions builder = new Actions(getWebDriver());   
		builder.dragAndDrop(elDraggable, todrag).perform();		
	}

	public void clickOn(String locator)
	{		
		this.WaitForElementPresent(locator, 30);
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		WebElement el = getWebDriver().findElement(ByLocator(locator));			
		el.click();
	}
	
	public void clickViaJavaScript(String locator)
	{
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void clearTextField(String locator)
	{		
		this.WaitForElementPresent(locator, 30);
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		WebElement el = getWebDriver().findElement(ByLocator(locator));			
		el.clear();
	}



	public void sendKeys(String locator, String userName)
	{		
		this.WaitForElementPresent(locator, 30);		
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"+locator+" Not found");
		WebElement el = getWebDriver().findElement(ByLocator(locator));
		el.clear();
		el.sendKeys(userName);
	}


	public void selectDropDownByVisibleText(String locator, String targetValue)
	{ 
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		this.WaitForElementPresent(locator, 20);
		new Select(getWebDriver().findElement(ByLocator(locator))).selectByVisibleText(targetValue);		
	}

	public void selectDropDownByValue(String locator, String targetValue)
	{ 
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		this.WaitForElementPresent(locator, 20);
		new Select(getWebDriver().findElement(ByLocator(locator))).selectByValue(targetValue);		
	}

	public boolean isTextPresent(String locator, String str)
	{
		Assert.assertTrue(isElementPresent(locator) ,"Element Locator :"+locator+" Not found");

		String message = getWebDriver().findElement(ByLocator(locator)).getText();		
		System.out.println("my value--" + message);
		if(message.contains(str)){return true;}
		else {	return false; }
	}

	public String getText(String locator)
	{
		WaitForElementPresent(locator, 30);
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		String text = getWebDriver().findElement(ByLocator(locator)).getText();			
		return text;
	}
	public void switchtoframe(String locator){
		driver.switchTo().frame(locator);
	}
	public boolean isChecked(String locator)
	{
		boolean checkStatus = false;
		WaitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		WebElement el = getWebDriver().findElement(ByLocator(locator));	    
		checkStatus = el.isSelected();	    
		return checkStatus;

	}

	public String getAttribute(String locator, String attribute)
	{
		WaitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		String text = getWebDriver().findElement(ByLocator(locator)).getAttribute(attribute);	
		return text;
	}
	public Integer getInt(String strString)
	{
		Pattern intsOnly = Pattern.compile("\\d+");
		String input = strString;
		Matcher makeMatch = intsOnly.matcher(input);
		makeMatch.find();
		String digitStr  = makeMatch.group();
		Integer digit = Integer.parseInt(digitStr);            
		return digit;
	}

	public void javaScriptExecute(String javascrpt)
	{
		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
		js.executeScript(javascrpt);
	}

	public void rightClick(String locator)
	{
		WebElement el = getWebDriver().findElement(ByLocator(locator));

		//build and perform the mouseOver with Advanced User Interactions API
		Actions builder = new Actions(getWebDriver());    
		builder.contextClick(el).build().perform();
	}


	//click on yes at the delete video popup
	public void acceptPopup()
	{
		getWebDriver().findElement(By.xpath("//button[contains(text(), 'Accept')]")).click();

	}

	public void clickTextField(String locator)
	{		
		this.WaitForElementPresent(locator, 30);
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		WebElement el = getWebDriver().findElement(ByLocator(locator));			
		el.clear();
	}

	// scroll to Particular element
	public void scrollToElement(String locator) {
		WebElement element = getWebDriver().findElement(ByLocator(locator));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void scrollingdown()
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		//((JavascriptExecutor) d).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	public void scrollingup()
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,0)", "");
	}

	public void waitForWorkArround(int Milisecond)
	{ 
		try 
		{
			Thread.sleep(Milisecond);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

	//**********************************************MOBILE METHODS**********************************************// 
	public void clickOnMobile(String locator)
	{		
		try
		{
			this.WaitForElementPresent(locator, 30);
			Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
			WebElement el = getWebDriver().findElement(ByLocator(locator));			
			el.click();
		}
		catch(ElementNotVisibleException ex)
		{
			System.out.println("Running Test on System no need to use this button");
		}
	}
}
