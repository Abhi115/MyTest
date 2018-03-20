using OpenQA.Selenium;
using OpenQA.Selenium.Interactions;
using OpenQA.Selenium.Support.UI;
using System;
using System.Collections.ObjectModel;
using System.Diagnostics;
using System.Linq;
using Decryptex_Automation.locators;
using System.Threading;

namespace Decryptex_Automation.Utils
{
	public class DriverHelper
	{
        public IWebDriver WebDriver { get; }

		public PropertyReader Properties { get; }

		public LocatorReader Locators { get; }

		public DriverHelper(IWebDriver driver, string locatorFile)
		{
			WebDriver = driver;
			Properties = new PropertyReader();

			if (locatorFile != null)
				Locators = new LocatorReader(locatorFile);
        }

		public By ByLocator(string locator)
		{
			By result;

			if (locator.StartsWith("//"))
			{
				result = By.XPath(locator);
			}
			else if (locator.StartsWith("css="))
			{
				result = By.CssSelector(locator.Replace("css=", ""));
			}
			else if (locator.StartsWith("#"))
			{
				result = By.Name(locator.Replace("#", ""));
			}
			else if (locator.StartsWith("Link="))
			{
				result = By.LinkText(locator.Replace("Link=", ""));
			}
			else if (locator.StartsWith("class="))
			{
				result = By.ClassName(locator.Replace("class=", ""));
			}
			else if (locator.StartsWith("name="))
			{
				result = By.Name(locator.Replace("name=", ""));
			}
			else if (locator.StartsWith("id="))
			{
				result = By.Id(locator.Replace("id=", ""));
			}
			else if (locator.StartsWith("("))
			{
				result = By.XPath(locator);
			}
			else
			{
				result = By.XPath(locator);
			}
			return result;
		}

		public object ExecuteScript(string javascrpt, params object[] args)
		{
			IJavaScriptExecutor js = (IJavaScriptExecutor)WebDriver;
			return js.ExecuteScript(javascrpt, args);
		}

		public TResult Wait<TResult>(Func<IWebDriver, TResult> predicate, int timeoutSeconds)
		{
			WebDriverWait wait = new WebDriverWait(WebDriver, TimeSpan.FromSeconds(timeoutSeconds));
            wait.IgnoreExceptionTypes(typeof(StaleElementReferenceException));
            wait.IgnoreExceptionTypes(typeof(InvalidElementStateException));
            wait.PollingInterval = TimeSpan.FromMilliseconds(timeoutSeconds);
			return wait.Until(predicate);
		}

		// find an element satisfying a specific predicate allowing for some time before the condition is satisfied
		public IWebElement FindElement(string locator, Func<By, Func<IWebDriver, IWebElement>> predicate, int timeoutSeconds, bool throwError = true)
		{
			try
			{
				return Wait(predicate(ByLocator(locator)), timeoutSeconds);
			}
			catch
			{
				if (throwError)
					throw;
			}

			return null;
		}

		// find all elements satisfying a specific predicate allowing for some time before the condition is satisfied
		public ReadOnlyCollection<IWebElement> FindElements(string locator, Func<By, Func<IWebDriver, ReadOnlyCollection<IWebElement>>> predicate, int timeoutSeconds, bool throwError = true)
		{
			try
			{
				WebDriverWait wait = new WebDriverWait(WebDriver, TimeSpan.FromSeconds(timeoutSeconds));
				return wait.Until(predicate(ByLocator(locator)));
			}
			catch
			{
				if (throwError)
					throw;
			}

			return null;
		}

        //Get available elements count
		public int GetElementCount(string locator)
		{
			ReadOnlyCollection<IWebElement> elements = FindElements(locator, ExpectedConditions.PresenceOfAllElementsLocatedBy, 10, false);
			return elements == null ? 0 : elements.Count;
		}

        //Verify element is present or not
		public bool IsElementPresent(string locator)
		{
			return FindElement(locator, ExpectedConditions.ElementExists, 3, false) != null;
		}

        //Wait until element doesn't hide
        public void WaitForElementHide(string locator,int timeoutSeconds=10)
        {
            GetWait(timeoutSeconds).Until(ExpectedConditions.InvisibilityOfElementLocated(ByLocator(locator)));
        }

        //Wait until elemenent doesn't display
        public void WaitForElementDisplay(string locator, int timeoutSeconds=10)
        {

            GetWait(timeoutSeconds).Until(ExpectedConditions.ElementIsVisible(ByLocator(locator)));
        }

        //Wait for element element until it gets enable
        public void waitForElementEnabled(string locator,int timeoutSeconds=10)
        {
            IWebElement el = FindElement(locator, ExpectedConditions.ElementToBeClickable, timeoutSeconds);

            GetWait(timeoutSeconds).Until(drv => el.Enabled);
        }

        //Verify element is enabled or not
        public bool IsElementEnabled(string locator)
		{
			return FindElement(locator, ExpectedConditions.ElementToBeClickable, 3, false) != null;
		}

        //Verify element is visible or not
		public bool IsElementDisplayed(string locator,int timeout=3)
		{
			return FindElement(locator, ExpectedConditions.ElementIsVisible, timeout, false) != null;
		}

        //Perform mouse over action on an element
		public void MouseOver(string locator)
		{
			IWebElement el = FindElement(locator, ExpectedConditions.ElementExists, 10);

			Actions builder = new Actions(WebDriver);
			builder.MoveToElement(el).Build().Perform();
		}

        //Perform drag and drop action
		public void DragAndDrop(string draggable, string to)
		{
			IWebElement elDraggable = FindElement(draggable, ExpectedConditions.ElementIsVisible, 10);
			IWebElement todrag = FindElement(to, ExpectedConditions.ElementIsVisible, 10);

			Actions builder = new Actions(WebDriver);
			builder.DragAndDrop(elDraggable, todrag).Perform();
		}

        //Simple click on an element
		public void Click(string locator, params string[] modifiers)
		{
			IWebElement el = FindElement(locator, ExpectedConditions.ElementToBeClickable, 10);
            el = FindElement(locator, ExpectedConditions.ElementIsVisible, 10);

            Actions action = new Actions(WebDriver);
			// press modifier keys (if any)
			if (modifiers != null && modifiers.Length > 0)
			{
				foreach (string key in modifiers)
					action = action.KeyDown(key);
			}

			el.Click();

			// release modifier keys (if any)
			if (modifiers != null && modifiers.Length > 0)
			{
				foreach (string key in modifiers.Reverse())
					action = action.KeyUp(key);
			}
		}
        
        //Click on an element using javaScript
		public void ClickByJS(string locator)
		{
            IWebElement el = FindElement(locator, ExpectedConditions.ElementExists, 10); 
            ExecuteScript("arguments[0].click();", el);
        }

        //Select value from dropdown
		public void SelectDropDown(string locator, string targetValue)
		{
			IWebElement el = FindElement(locator, ExpectedConditions.ElementToBeClickable, 10);
			new SelectElement(el).SelectByText(targetValue);
			Wait(d => ExpectedConditions.ElementToBeSelected(el), 5);
		}

        //Verify element contains text or not
		public bool IsTextPresent(string locator, string str)
		{
			return GetText(locator).Contains(str);
		}

        //Get available text of an element
        //Updated time from 5 to 15 because element was not being visible in 5 sec.
        //While using ElementExists we were getting blank as the test so used ElementIsVisible
        public string GetText(string locator)
		{
            //IWebElement el = FindElement(locator, ExpectedConditions.ElementExists, 10);
            IWebElement el = FindElement(locator, ExpectedConditions.ElementIsVisible, 10);
            return el.Text;
		}

        //Enter data in the field without clearing it
		public void SendKeys(string locator, string text, bool clear = false)
		{
			IWebElement el = FindElement(locator, ExpectedConditions.ElementToBeClickable, 15);

			if (clear)
				el.Clear();

			el.SendKeys(text);
		}

        //Enter data in the field after clearing it
		public void SetText(string locator, string text)
		{
			SendKeys(locator, text, true);
		}

        //Some time selenium sendkeys was giving error so used this method to set text field value 
		public void SetTextByJS(string locator, string text)
		{
			IWebElement el = FindElement(locator, ExpectedConditions.ElementExists, 15);
			ExecuteScript("arguments[0].setAttribute('value', '" + text + "')", el);
			el.SendKeys(Keys.Enter);
		}

        //Verify element checked or not
		public bool IsChecked(string locator)
		{
			IWebElement el = FindElement(locator, ExpectedConditions.ElementIsVisible, 15);
			return el.Selected;
		}

        //Get value of an attribute of given element
		public string GetAttribute(string locator, string attribute)
		{
			IWebElement el = FindElement(locator, ExpectedConditions.ElementExists, 15);
			return el.GetAttribute(attribute);
		}

        //Wait untill full page load
		public void WaitForPageLoad(string title, int timeoutSeconds = 10)
		{
            //Updated : added javascript code to wait untill full document loaded
            Wait(ExpectedConditions.TitleContains(title), timeoutSeconds);
            Wait(d => ((IJavaScriptExecutor)WebDriver).ExecuteScript("return document.readyState").Equals("complete"), timeoutSeconds);
        }

        //In small screen/ low resolution full grid doesn't displayed this method scroll the age to the down
		public void ScrollToBottom()
		{
			ExecuteScript("window.scrollTo(0, document.body.scrollHeight);");
		}

        //This method scroll the age to the top
        public void ScrollToTop()
		{
            //ExecuteScript("window.scrollTo(0,0)");
            ExecuteScript("window.scrollBy(0,-300)");

        }

		public void ScrollIntoView(string locator)
		{
			IWebElement el = FindElement(locator, ExpectedConditions.ElementExists, 15);
			ExecuteScript("arguments[0].scrollIntoView();", el);
		}

        //wait for frame to be available and switch into it
		public void SwitchToFrame(string xpath)
		{
			Wait(ExpectedConditions.FrameToBeAvailableAndSwitchToIt(xpath), 15);
		}

        //To get back from frame
		public void DefaultSwitch()
		{
			WebDriver.SwitchTo().DefaultContent();
		}

        //Get current project path
		public string GetPath()
		{
			return Properties.GetPath();
		}

        //Update System time zone
        public void SetSystemTimeZone(string timeZoneId)
        {
            var process = Process.Start(new ProcessStartInfo
            {
                FileName = "tzutil.exe",
                Arguments = "/s \"" + timeZoneId + "\"",
                UseShellExecute = false,
                CreateNoWindow = true
            });

            if (process != null)
            {
                process.WaitForExit();
                TimeZoneInfo.ClearCachedData();
            }
        }

        public WebDriverWait GetWait(int timeOutSeconds)
        {
            return new WebDriverWait(WebDriver, TimeSpan.FromSeconds(timeOutSeconds));
        }

        //Perform Zoom in-out action
        public void Zoom(int percentage = 100)
        {
            ExecuteScript("document.body.style.zoom = '" + percentage + "%';");
        }
    }
}