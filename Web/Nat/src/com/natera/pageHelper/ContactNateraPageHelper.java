package com.natera.pageHelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.natera.locator.LocatorReader;
import com.natera.util.DriverHelper;

public class ContactNateraPageHelper extends DriverHelper{

	private LocatorReader contactNatera;

	public ContactNateraPageHelper(WebDriver driver) {
		super(driver);	
		contactNatera = new LocatorReader("ContactNatera.xml");
	}
	String locator;

	public void CheckContactNateraPage()
	{
		String demo = "//button[@id='navbar-toggle']";
		clickOnMobile(demo);
		waitForWorkArround(2000);

		locator = contactNatera.getLocator("MenuNatera.ContactNatera");
		clickOn(locator);

		locator = contactNatera.getLocator("ContactNateraPage.PageHeaderLocator");
		String headerTxt = getText(locator);
		String targetTxt= contactNatera.getLocator("PageData.Headertxt");

		Assert.assertTrue(headerTxt.equalsIgnoreCase(targetTxt), "Afetr clicking Contact Natera, it does not redirect to correct page.");
	}

	String tablelocator;
	public void messagePerPages(String msgCount)
	{
		locator= contactNatera.getLocator("ContactNateraPage.MessageHistory");
		clickOn(locator);

		scrollingdown();
		locator= contactNatera.getLocator("ContactNateraPage.MessageCountPerPage");
		Select messageCountDropdown=new Select(getWebDriver().findElement(By.xpath(locator)));		
		messageCountDropdown.selectByVisibleText(msgCount);
		waitForWorkArround(4000);
		Assert.assertTrue(getText(locator).contains(msgCount), "Unable to select value in nubler of cases per page");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tablelocator= contactNatera.getLocator("ContactNateraPage.TableRows");
		int rowCount = TableRowCount(tablelocator);
		String rowCountTxt = Integer.toString(rowCount);	   			
		Assert.assertEquals(rowCountTxt, msgCount, "Test result page does not display correct number of results." );		

	}

	String resultTxt;
	String targetTxt;
	public String filterMessageSearchBox(String messageSubject)
	{
		locator= contactNatera.getLocator("ContactNateraPage.FilterMessageSearchBox");
		sendKeys(locator, messageSubject);

		tablelocator= contactNatera.getLocator("ContactNateraPage.TableRows");
		int rowCount = TableRowCount(tablelocator);
		locator= contactNatera.getLocator("ContactNateraPage.NoMatchingRecord");

		if(isElementPresent(locator))
		{
			resultTxt= getText(locator);
			Assert.assertTrue(resultTxt.contains("No matching records found"), "Some result are appearing.");		
			return "none";
		}else
		{
			waitForWorkArround(1000);
			getWebDriver().findElement(By.xpath("//*[@id='message-center-table']/thead/tr/th[1]")).click();
			waitForWorkArround(1000);
			for(int i=1; i<=rowCount; i++)
			{
				resultTxt= getWebDriver().findElement(By.xpath("//table[@id='message-center-table']/tbody/tr["+i+"]/td[1]")).getText();

				Assert.assertTrue(resultTxt.contains(messageSubject), "Test result is not of the same case ID.");			
			}
			return messageSubject;
		}

	}

	public void composeAndSendNewMessage()
	{
		/* locator = contactNatera.getLocator("ContactNateraPage.NewMsgBtn");
		 clickOn(locator);
		 try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 */
		locator = contactNatera.getLocator("NewMsgPouup.SelectClinicBox");
		Select clinicName=new Select(getWebDriver().findElement(By.xpath(locator)));
		clinicName.selectByVisibleText("Reproductive Care Center - Utah");

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		locator = contactNatera.getLocator("NewMsgPouup.QuestionAbout");
		Select questionAbout=new Select(getWebDriver().findElement(By.xpath(locator)));
		questionAbout.selectByVisibleText("General Inquiry");

		locator = contactNatera.getLocator("NewMsgPouup.SubjectTxtBox");
		sendKeys(locator, "This is test A NEW  subject");

		locator = contactNatera.getLocator("NewMsgPouup.MessageBox");
		sendKeys(locator, "This is test message for new message feature testing.");

		locator = contactNatera.getLocator("NewMsgPouup.SendBtn");
		clickOn(locator);

		locator = contactNatera.getLocator("VerifyMsgSent");
		String verificationTxt= getText(locator);
		Assert.assertTrue(verificationTxt.contains("Your message has been sent to customer support"), "Message is not sent.");

	}

	public void openMessage(String messageKeyword)
	{
		String getValue = filterMessageSearchBox(messageKeyword);

		if(getValue == "none")
		{
			System.out.println("No message fount for related keyword.");
		}
		else
		{ 
			waitForWorkArround(1000);
			getWebDriver().findElement(By.xpath("//table[@id='message-center-table']/tbody/tr[1]/td[1]")).click();
			locator = contactNatera.getLocator("MessagePage.MessagePageHeader");
			resultTxt = getText(locator);
			Assert.assertTrue(resultTxt.contains(getValue), "Message is not opened");			 

		}
	}

	public void replyOnMessage(String msgSub)
	{
		locator = contactNatera.getLocator("MessagePage.ReplyTxtBox");
		sendKeys(locator, msgSub);

		locator = contactNatera.getLocator("MessagePage.ReplySendBtn");

		Actions action = new Actions(getWebDriver());
		WebElement el = getWebDriver().findElement(ByLocator(locator));
		WaitForElementPresent(locator, 10);
		action.moveToElement(el).perform();

		clickOn(locator);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		locator = contactNatera.getLocator("MessagePage.ReplySuccessfulMessage");
		resultTxt = getText(locator);
		Assert.assertTrue(resultTxt.contains("Your reply was sent successfully."), "Unable to send reply.");

		//Back button

		driver.navigate().to("https://staging-connect.natera.com/messages");
		//locator = contactNatera.getLocator("MessagePage.BackBtn");
		//clickOn(locator);

		locator = contactNatera.getLocator("ContactNateraPage.PageHeaderLocator");
		String headerTxt = getText(locator);		 
		String targetTxt= contactNatera.getLocator("PageData.Headertxt");

		Assert.assertTrue(headerTxt.equalsIgnoreCase(targetTxt), "Afetr clicking back button, it does not redirect to correct page.");



	}

	//**********************
	public int TableRowCount(String tblLocator)
	{
		List<WebElement> columnElements = getWebDriver().findElements(By.xpath(tblLocator));
		int rowNumber = columnElements.size();
		return rowNumber;				
	}

}
