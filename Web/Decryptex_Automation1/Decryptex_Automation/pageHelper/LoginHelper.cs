using Decryptex_Automation.Utils;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;

namespace Decryptex_Automation.PageHelper
{
	public class LoginHelper : DriverHelper
	{
		public LoginHelper(IWebDriver driver) : base(driver, "LoginLocators.xml")
		{
		}

		public void EnterUsername(string userName)
		{
			SetText(Locators["usernameFile"], userName);
		}

		public void EnterPassword(string password)
		{
			SetText(Locators["passwordField"], password);
		}

		public void Login()
		{
			string button = Locators["loginButton"];
			base.Click(button);
		}

		public void WithCredentials(string userName, string password, bool waitToLogin = true)
		{
            DefaultSwitch();
            if (IsElementPresent(Locators["header"]))
            {
                Logout();

                WaitForPageLoad("Decryptex");
            }
            
            if(IsElementPresent(Locators["passwordField"]))
            {
                EnterUsername(userName);
                EnterPassword(password);

                Login();

                if (waitToLogin)
                    FindElement(Locators["header"], ExpectedConditions.ElementExists, 3);
            }
		}

		public void AsAdmin()
		{
			WithCredentials(Properties["Admin_userName"], Properties["Admin_password"]);
		}

		public void AsManager()
		{
			WithCredentials(Properties["Manager_userName"], Properties["Manager_password"]);
		}

		public void AsAnalyst()
		{
			WithCredentials(Properties["Analyst_userName"], Properties["Analyst_password"]);
		}

		public void AsReadonly()
		{
			WithCredentials(Properties["Readonly_userName"], Properties["Readonly_password"]);
		}

		public void Logout()
		{
			string button = Locators["logout"];
			base.Click(button);

            DefaultSwitch();

			WaitForPageLoad("Decryptex");  // TODO: wait for some element to be present since we were already on page Decryptex
            WaitForElementDisplay(Locators["passwordField"]);                               // We have added a javascript code 
		}

		public void AssertLoginError(string expectedError = "Invalid login attempt")
		{
			VerifyError("invalidLoginAttempt", expectedError);
		}

		public void AssertUsernameError(string expectedError = "The User Name field is required.")
		{
			VerifyError("UserNameValidation", expectedError);
		}

		private void VerifyError(string field, string error)
		{
			field = Locators[field];
			string text = GetText(field);
			Assert.IsTrue(text.Contains(error));
		}

		public bool CanLogin
		{
			get
			{
				return IsElementPresent(Locators["loginButton"]);
			}
		}
	}
}
