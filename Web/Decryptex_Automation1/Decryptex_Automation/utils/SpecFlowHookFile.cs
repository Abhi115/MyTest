using BoDi;
using Decryptex_Automation.locators;
using Decryptex_Automation.PageHelper;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.IE;
using OpenQA.Selenium.Support.UI;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Linq;
using System.Runtime.ExceptionServices;
using TechTalk.SpecFlow;

namespace Decryptex_Automation.Utils
{
    [Binding]
    public class SpecFlowHookFile
    {
        private static IObjectContainer _objectContainer;
        private static string _browser;
        private static string _applicationUrl;
        protected static readonly PropertyReader _propertyReader = new PropertyReader();

      
        protected static IWebDriver Driver;

        private readonly Lazy<LoginHelper> _loginHelper = new Lazy<LoginHelper>(() => new LoginHelper(Driver), true);
       
        protected LoginHelper Login { get { return _loginHelper.Value; } }

        [BeforeTestRun(Order = 0)]
        public static void InitializeContainer(IObjectContainer objectContainer)
        {
            _objectContainer = objectContainer;

        }

        [BeforeTestRun(Order = 1)]
        public static IWebDriver InitializeDriver()
        {

            InitializeExceptionHandler();

            _browser = _propertyReader["Browser"];
            string driverPath = _propertyReader.GetPath() + @"\Drivers\";

            if (_browser.ToLower().Contains("firefox"))
            {
                FirefoxDriverService service = FirefoxDriverService.CreateDefaultService(driverPath);
                service.FirefoxBinaryPath = @"C:\Program Files (x86)\Mozilla Firefox\firefox.exe"; // May not be necessary
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                TimeSpan time = TimeSpan.FromSeconds(10);
                Driver = new FirefoxDriver(service, firefoxOptions, time);
                _objectContainer.RegisterInstanceAs<IWebDriver>(Driver);
            }
            else if (_browser.ToLower().Contains("ie"))
            {
                Driver = new InternetExplorerDriver(driverPath);
                _objectContainer.RegisterInstanceAs<IWebDriver>(Driver);
            }
            else
            {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.AddArguments("disable-infobars");
                chromeOptions.AddArguments("--start-maximized");
                Driver = new ChromeDriver(driverPath, chromeOptions);
                _objectContainer.RegisterInstanceAs<IWebDriver>(Driver);
            }

            // wrap in an event firing driver which will give us some flexibility
            Driver.Manage().Cookies.DeleteAllCookies();
            return Driver;
        }

        [BeforeFeature]
        public static void TestInitialize()
        {
            // go to the application URL
            _applicationUrl = _propertyReader["ApplicationUrl"];
            Driver.Navigate().GoToUrl(_applicationUrl);
        }
    }
}
