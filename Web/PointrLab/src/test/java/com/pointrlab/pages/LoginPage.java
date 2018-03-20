package com.pointrlab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.pointrlab.constant.GlobalConstant.ValidationMessage;
import com.pointrlab.selenium.framework.BasePage;
import com.pointrlab.selenium.framework.Configuration;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@name='UserName']")
	public WebElement userNameField;

	@FindBy(xpath = "//input[@name='Password']")
	public WebElement passwordField;

	@FindBy(xpath = "//input[@value='Log in']")
	private WebElement logInButton;

	@FindBy(xpath = "//span[@class='field-validation-error']")
	private WebElement blankUserNameValidation;

	@FindBy(xpath = "//span[@class='field-validation-error']")
	private WebElement blankPasswordValidation;

	@FindBy(xpath = "//div[@class='validation-summary-errors']/ul/li")
	private WebElement wrongUserNameAndPasswordValidation;

	/**
	 * login into the application
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public HomePage login(String username, String password) throws Exception {
		String userName = Configuration.readApplicationFile(username);
		String pwd = Configuration.readApplicationFile(password);
		inputText(userNameField, userName);
		inputText(passwordField, pwd);
		clickOn(logInButton);
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	/**
	 * verify login page
	 */
	public void verifyLoginPage() {
		verifyElementPresent(userNameField);
		verifyElementPresent(passwordField);
	}

	/**
	 * verify invalid login
	 * 
	 * @param userName
	 * @param password
	 * @throws Exception
	 */
	public void verifyInvalidLogin(String userName, String password)
			throws Exception {
		inputText(userNameField, userName);
		inputText(passwordField, password);
		clickOn(logInButton);
		_waitForJStoLoad();

		if ((userName == "") && (password == "")) {
			Assert.assertEquals(blankUserNameValidation.getText(),
					ValidationMessage.BlankUserNameValidation.toString(),
					"validation message not displayed");
			Assert.assertEquals(blankPasswordValidation.getText(),
					ValidationMessage.BlankPasswordValidation.toString(),
					"validation message not displayed");
		}

		else if ((userName != Configuration.readApplicationFile("username"))
				&& (password.equals(Configuration
						.readApplicationFile("password"))))
			Assert.assertEquals(wrongUserNameAndPasswordValidation.getText(),
					ValidationMessage.WrongUserNameValidation.toString(),
					"validation message not displayed");

		else
			Assert.assertEquals(wrongUserNameAndPasswordValidation.getText(),
					ValidationMessage.WrongPasswordValidation.toString(),
					"validation message not displayed");
	}
}
