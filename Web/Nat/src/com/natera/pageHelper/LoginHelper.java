package com.natera.pageHelper;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.natera.locator.LocatorReader;
import com.natera.util.DriverHelper;
import com.natera.util.PropertyReader;

public class LoginHelper extends DriverHelper {

	private LocatorReader loginLocator;	
	private PropertyReader propertyReader;

	public LoginHelper(WebDriver driver) 
	{
		super(driver);	
		loginLocator = new LocatorReader("Login.xml");
		propertyReader=new PropertyReader();
	}

	public void verifyLoginPage()
	{		
		locator = loginLocator.getLocator("Login.UserNameTab");
		String userName = getText(locator);
		Assert.assertTrue(userName.contains("Username"));
		String locator1 = loginLocator.getLocator("Login.PasswordTab");
		String password = getText(locator1);		
		Assert.assertTrue(password.contains("Password"));
	}

	public void enterUserEmail(String username)
	{
		locator = loginLocator.getLocator("Login.UserName");
		sendKeys(locator, username); 
	}

	public void enterUserPassword(String password)
	{
		locator = loginLocator.getLocator("Login.Password");
		sendKeys(locator, password);		
	}

	public void clickLogin()
	{
		locator = loginLocator.getLocator("Login.LogIn");
		clickOn(locator);
	}

	String actualTxt;
	String targetTxt;
	String locator;
	String inputTxt;

	// Sign Up method :: Done
	public void userSignup(String UId)
	{
		if(propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			String demo="//button[contains(@class,'navbar-toggle')]";
			clickOnMobile(demo);

			locator = loginLocator.getLocator("SignUp.MobileSignUpLink");
			clickOn(locator);
		}
		else
		{
			locator = loginLocator.getLocator("SignUp.SignUpLink");
			clickOn(locator);
		}

		locator = loginLocator.getLocator("SignUp.PageHeader");
		WaitForElementPresent(locator, 10);
		String hearderTxt = getText(locator);
		targetTxt = "Sign Up";
		Assert.assertTrue(hearderTxt.contains(targetTxt), "On clicking Signup link, it is not opening SignUp page");

		locator = loginLocator.getLocator("SignUp.FirstName");
		inputTxt = loginLocator.getLocator("InputData.SighupInputData.FirstName");
		sendKeys(locator, inputTxt);

		locator = loginLocator.getLocator("SignUp.LastName");
		inputTxt = loginLocator.getLocator("InputData.SighupInputData.LastName");
		sendKeys(locator, inputTxt);		

		locator = loginLocator.getLocator("SignUp.EmailId");
		sendKeys(locator, UId);

		locator = loginLocator.getLocator("SignUp.SignUpBtn");
		clickOn(locator);		
	}

	//SignUp successfully page verification method :: Done
	public void signupVerification()
	{	
		locator = loginLocator.getLocator("SignUp.ExistingIDAlert");
		if(isElementDisplayed(locator))
		{
			System.out.println("Trying to signup with already used ID.");
		}

		locator = loginLocator.getLocator("SignUp.ConfMessage");
		WaitForElementPresent(locator, 10);

		actualTxt = getText(locator);
		targetTxt = "A message with a confirmation link has been sent to your email address. Please follow the link to activate your account.";
		Assert.assertTrue(actualTxt.contains(targetTxt), "On clicking Signup button, Unable to sign up.");

		locator = loginLocator.getLocator("SignUp.CheckYourEmail");		
		actualTxt = getText(locator);
		System.out.println("My Locator" + actualTxt );
		targetTxt = "Check Your Email";
		Assert.assertTrue(actualTxt.contains(targetTxt), "Unable to sign up.");	
	}

	//After sign up Correct Email_ID link verification method :: Done
	public void correctEmailID()
	{
		locator = loginLocator.getLocator("SignUp.CorrectItLink");
		clickOn(locator);

		locator = loginLocator.getLocator("SignUp.PageHeader");
		WaitForElementPresent(locator, 200);
		String hearderTxt = getText(locator);
		System.out.println("My Name " + hearderTxt);
		targetTxt = "Sign Up";
		Assert.assertTrue(hearderTxt.contains(targetTxt), "On clicking 'corret it' link, it is not opening SignUp page");	
	}

	//ID is already used message verification method :: Done
	public void signupWithExistingID() throws InterruptedException
	{		
		locator = loginLocator.getLocator("SignUp.ExistingIDAlert");
		WaitForElementPresent(locator, 10);
		//Thread.sleep(1000);
		actualTxt = getText(locator);        
		targetTxt = "has already been taken";
		Assert.assertTrue(actualTxt.contains(targetTxt), "SignUp with existing ID is not working correctly.");	
	}

	//Re-send the instruction to reset password method :: Done
	public void forgetPassword()
	{	
		locator = loginLocator.getLocator("Login.ForgetPasswordLink");
		clickOn(locator);		

		locator = loginLocator.getLocator("ForgetPasswordPage.PageHeader");
		WaitForElementPresent(locator, 10);
		actualTxt = getText(locator);
		targetTxt = "Reset Password";
		Assert.assertTrue(actualTxt.contains(targetTxt), "On clicking Forget password link, it is not redirecting at correct page.");

		locator = loginLocator.getLocator("ForgetPasswordPage.EmailBox");
		sendKeys(locator, "akhileshk@360logica.com");

		locator = loginLocator.getLocator("ForgetPasswordPage.SendResetPasswordBtn");
		clickOn(locator);

		locator = loginLocator.getLocator("ForgetPasswordPage.ConfMessage");
		WaitForElementPresent(locator, 200);
		actualTxt = getText(locator);
		targetTxt = "Forgot Password";
		targetTxt = "You will receive an email with instructions on how to reset your password in a few minutes";
		Assert.assertTrue(actualTxt.contains(targetTxt), "Email with instructions on how to reset your password is not sent.");	
	}

	//Re-send the confirmation message to confirm your Email Id method :: Done
	public void notRecievedConfMessage() throws InterruptedException
	{
		if(propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			String demo="//button[contains(@class,'navbar-toggle')]";
			clickOnMobile(demo);

			locator = loginLocator.getLocator("Login.MobileLoginHelp");
			clickOn(locator);
		}
		else
		{
			locator = loginLocator.getLocator("Login.LoginHelp");
			clickOn(locator);
		}
		Thread.sleep(1000);

		locator = loginLocator.getLocator("Login.NotReceivedConfMsgLink");
		clickOn(locator);		

		locator = loginLocator.getLocator("ResendConfInstruction.PageHeader");
		WaitForElementPresent(locator, 10);
		actualTxt = getText(locator);
		System.out.println("my value--" + actualTxt);
		targetTxt = "Resend Confirmation Instructions";
		System.out.println("my value--" + targetTxt);
		Assert.assertTrue(actualTxt.contains(targetTxt), "On clicking Forget password link, it is not redirecting at correct page.");
		locator = loginLocator.getLocator("ResendConfInstruction.EmailIdField");
		sendKeys(locator, "akhileshk@360logica.com");

		locator = loginLocator.getLocator("ResendConfInstruction.ResendBtn");
		clickOn(locator);

		locator = loginLocator.getLocator("ResendConfInstruction.ConfMessage");
		WaitForElementPresent(locator, 10);
		actualTxt = getText(locator);
		targetTxt = "You will receive an email with instructions for how to confirm your email address in a few minutes.";
		Assert.assertTrue(actualTxt.contains(targetTxt), "Email for insctruction is not sent succesfully.");		
	}

	//Re-send unlock instruction method :: Done
	public void resendUnlockInstructions() throws InterruptedException
	{
		if(propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			String demo="//button[contains(@class,'navbar-toggle')]";
			clickOnMobile(demo);

			locator = loginLocator.getLocator("Login.MobileLoginHelp");
			clickOn(locator);
		}
		else
		{
			locator = loginLocator.getLocator("Login.LoginHelp");
			clickOn(locator);
		}
		Thread.sleep(1000);

		locator = loginLocator.getLocator("Login.UnlockMyAccount");
		clickOn(locator);

		locator = loginLocator.getLocator("ResendUnlockInstructions.PageHeader");
		WaitForElementPresent(locator, 10);
		actualTxt = getText(locator);
		System.out.println("My Link " + actualTxt);
		targetTxt = "Unlock Account";
		Assert.assertTrue(actualTxt.contains(targetTxt), "On clicking resend Unlock instructions link, it is not redirecting at correct page.");
		locator = loginLocator.getLocator("ResendUnlockInstructions.EmailIdField");
		sendKeys(locator, "akhilesh11@gmail.com");

		locator = loginLocator.getLocator("ResendUnlockInstructions.ResendBtn");
		clickOn(locator);		

		locator = loginLocator.getLocator("ResendUnlockInstructions.IdNotLocked");

		if(isElementPresent(locator))
		{
			actualTxt = getText(locator);
			targetTxt = "was not locked";
			Assert.assertTrue(actualTxt.contains(targetTxt), "Account is not unlock.");

		} else{		
			locator = loginLocator.getLocator("ResendUnlockInstructions.ConfMessage");
			WaitForElementPresent(locator, 10);
			actualTxt = getText(locator);
			System.out.println("my value--"+actualTxt);
			targetTxt = "You will receive an email with instructions for how to unlock your account in a few minutes.";
			Assert.assertTrue(actualTxt.contains(targetTxt), "Email of insctruction to unlock the account is not sent succesfully.");
		}	
	}

	public void userSignOut()
	{

		if(propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
		{
			String demo=loginLocator.getLocator("UserSetting.MobileUserMenu");
			clickOnMobile(demo);
			locator = loginLocator.getLocator("UserSetting.MobileSignOut");
			WaitForElementPresent(locator, 20);
			clickOn(locator);
		}
		else
		{
			locator = loginLocator.getLocator("UserSetting.UserMenu");
			clickOn(locator);

			locator = loginLocator.getLocator("UserSetting.SignOut");
			WaitForElementPresent(locator, 20);
			clickOn(locator);
		}
		locator = loginLocator.getLocator("Login.PageHeader");
		WaitForElementPresent(locator, 20);
		actualTxt = getText(locator);
		System.out.println("My Value--" + actualTxt);
		targetTxt = "";
		Assert.assertTrue(actualTxt.contains(targetTxt), "User is not logged out succesfully.");		
	}

	public void userSingIn(String uId, String pwd)
	{	
		locator = loginLocator.getLocator("Login.PageHeader");
		WaitForElementPresent(locator, 200);
		actualTxt = getText(locator);
		targetTxt = "";
		Assert.assertTrue(actualTxt.contains(targetTxt), "User is not logged out succesfully.");

		locator = loginLocator.getLocator("Login.UserName");
		sendKeys(locator, uId);

		locator = loginLocator.getLocator("Login.Password");
		sendKeys(locator, pwd);

		locator = loginLocator.getLocator("Login.LogIn");
		clickOn(locator);

		locator = loginLocator.getLocator("Login.InvalidIdMsg");

		if(isElementPresent(locator))
		{
			actualTxt = getText(locator);
			//targetTxt = "Invalid email or password.";
			targetTxt = "Invalid login or password.";
			Assert.assertTrue(actualTxt.contains(targetTxt), "Not getting alert message.");
		}
		else
		{
			if(!propertyReader.readApplicationFile("PlateForm").toLowerCase().contains("mobile"))
			{
				locator = loginLocator.getLocator("UserSetting.UserMenu");
				clickOn(locator);

				locator = loginLocator.getLocator("UserSetting.Username");
				WaitForElementPresent(locator, 200);
				actualTxt = getText(locator);
				targetTxt = uId;
				Assert.assertTrue(actualTxt.contains(targetTxt), "User is logged-In succesfully.");
			}
			else
			{
					Assert.assertTrue(getWebDriver().getTitle().contains("Dashboard"));
			}
		}						
	}

	public void rememberMeCheckBox(String uId, String pwd)
	{
		locator = loginLocator.getLocator("Login.PageHeader");
		WaitForElementPresent(locator, 200);
		actualTxt = getText(locator);
		targetTxt = "Natera Connect Login";
		Assert.assertTrue(actualTxt.contains(targetTxt));

		locator = loginLocator.getLocator("Login.UserName");
		sendKeys(locator, uId);

		locator = loginLocator.getLocator("Login.Password");
		sendKeys(locator, pwd);

		locator = loginLocator.getLocator("Login.RememberCheckBox");
		clickOn(locator);

		Assert.assertTrue(isChecked(locator));

		locator = loginLocator.getLocator("Login.LogIn");
		clickOn(locator);

		userSignOut(); 

		locator = loginLocator.getLocator("Login.PageHeader");
		WaitForElementPresent(locator, 200);
		actualTxt = getText(locator);
		targetTxt = "Natera Connect Login";
		Assert.assertTrue(actualTxt.contains(targetTxt));

		locator = loginLocator.getLocator("Login.UserName");
		actualTxt = getText(locator);
		Assert.assertTrue(actualTxt.contains(uId));

		locator = loginLocator.getLocator("Login.Password");
		actualTxt = getText(locator);
		Assert.assertTrue(actualTxt.contains(pwd));

		locator = loginLocator.getLocator("Login.RememberCheckBox");
		Assert.assertTrue(isChecked(locator));
	}
}
