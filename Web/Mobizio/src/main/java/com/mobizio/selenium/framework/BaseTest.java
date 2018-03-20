/*
  Class to initialize all application page objects and manage WebDriver browser
  object. Each and every test script class must extend this. This class does
  not use any of the Selenium APIs directly, and adds support to make this
  framework tool independent.

  @author 360Logica
 * @since 1.0
 *
 * @version 1.0
 */
package com.mobizio.selenium.framework;

import com.mobizio.Utilities.Utils;
import com.mobizio.enums.DriverType;
import com.mobizio.pages.BranchPage;
import com.mobizio.pages.CaseListPage;
import com.mobizio.pages.ConfigurationPage;
import com.mobizio.pages.CustomerPage;
import com.mobizio.pages.DashboardPage;
import com.mobizio.pages.DevicePage;
import com.mobizio.pages.FormPage;
import com.mobizio.pages.GroupPage;
import com.mobizio.pages.LoginPage;
import com.mobizio.pages.RolePage;
import com.mobizio.pages.SectionPage;
import com.mobizio.pages.ServicesPage;
import com.mobizio.pages.TaskPage;
import com.mobizio.pages.TaskTypePage;
import com.mobizio.pages.TenantPage;
import com.mobizio.pages.UserPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public abstract class BaseTest {

  private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
  private static final String BREAK_LINE = "\n";// "</br>";
  public static ExtentTest test;
  public static ExtentReports extent;
  // pages object initialization
  public LoginPage loginPage;
  public String[] loggedInUserTypes;
  protected DashboardPage dashBoardPage;
  protected CustomerPage customerPage;
  protected SectionPage sectionPage;
  protected GroupPage groupPage;
  protected TaskTypePage taskTypePage;
  protected RolePage rolePage;
  protected ConfigurationPage configurationPage;
  protected ServicesPage servicesPage;
  protected CaseListPage caseListPage;
  protected FormPage formPage;
  protected TaskPage taskPage;
  protected BranchPage branchPage;
  protected UserPage userPage;
  protected TenantPage tenantPage;
  protected DevicePage devicePage;
  private String browserType;
  private WebDriver driver;
  private String applicationUrl;

  public BaseTest(String browser, String[] loggedInUserTypes) {
    loginPage = new LoginPage(driver);
    this.browserType = browser;
    this.loggedInUserTypes = loggedInUserTypes;

  }

  @BeforeSuite
  public void before() {
    extent = new ExtentReports("target/surefire-reports/CustomReport.html", true);
  }

  @BeforeClass
  public void setUp() throws Exception {
    if (browserType == null) {
      browserType = Configuration.readApplicationFile("Browser");
    }

    this.applicationUrl = Configuration.readApplicationFile("URL");

    if (DriverType.Firefox.toString().toLowerCase().equals(browserType.toLowerCase())) {
      System.setProperty("webdriver.gecko.driver", Utils.getPath() + "//src//test//resources//webdriver/geckodriver.exe");
      DesiredCapabilities capabilities = DesiredCapabilities.firefox();
      capabilities.setCapability("marionette", true);
      driver = new FirefoxDriver();
    } else if (DriverType.IE.toString().toLowerCase().equals(browserType.toLowerCase())) {
      System.setProperty("webdriver.ie.driver", Utils.getPath() + "//src//test//resources//webdriver/IEDriverServer.exe");
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
      driver = new InternetExplorerDriver(capabilities);
    } else if (DriverType.Chrome.toString().toLowerCase().equals(browserType.toLowerCase())) {
      System.setProperty("webdriver.chrome.driver", Utils.getPath() + "//src//test//resources//webdriver/chromedriver.exe");
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--disable-extensions");
      driver = new ChromeDriver(options);
    } else {
      throw new Exception("Please pass a valid browser type value");
    }

    /**
     * Maximize window
     */
    driver.manage().window().maximize();

    /**
     * Delete cookies
     */
    driver.manage().deleteAllCookies();

    /**
     * Open application URL
     */
    getWebDriver().navigate().to(applicationUrl);
    loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
  }

  @AfterMethod
  public void afterMainMethod(ITestResult result) throws IOException, InterruptedException {
    if (result.getStatus() == ITestResult.FAILURE) {
      captureScreenshot(result);
    }
    driver.quit();
    extent.endTest(test);
  }

  @BeforeMethod
  public void setTest(Method method) {
    test = extent.startTest(method.getName(), this.getClass().getName());
    test.assignAuthor("360Logica");
    test.assignCategory(this.getClass().getSimpleName());
  }

  @AfterSuite
  public void tearDownSuite() {
    extent.flush();
    extent.close();
  }

  public WebDriver getWebDriver() {
    return driver;
  }

  /**
   * Handle child windows
   *
   * @return: Parent window name
   */
  public String switchPreviewWindow() {
    Set<String> windows = getWebDriver().getWindowHandles();
    Iterator<String> iterator = windows.iterator();
    String parent = iterator.next();
    getWebDriver().switchTo().window(iterator.next());
    return parent;
  }

  /**
   * Get absolute path
   *
   * @return: Absolute path
   */
  public String getPathUpload() {
    String path;
    File file = new File("");
    String absolutePathOfFirstFile = file.getAbsolutePath();
    path = absolutePathOfFirstFile.replaceAll("/", "//");
    return path;
  }

  /**
   * Capturing screenshot once script is failed
   */
  public void captureScreenshot(ITestResult result) {
    try {
      String screenshotName = Utilities.getFileName(result.getName());
      File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      String path = Utilities.getPath();
      String screen = path + "/screenshots/" + screenshotName + ".png";
      File screenshotLocation = new File(screen);
      FileUtils.copyFile(screenshot, screenshotLocation);
      Thread.sleep(2000);
      InputStream is = new FileInputStream(screenshotLocation);
      byte[] imageBytes = IOUtils.toByteArray(is);
      Thread.sleep(2000);
      String base64 = Base64.getEncoder().encodeToString(imageBytes);
      test.log(LogStatus.FAIL, result.getThrowable() + " \n Snapshot below: "
          + test.addBase64ScreenShot("data:image/png;base64," + base64));
      Reporter.log(
          "<a href= '" + screen + "'target='_blank' ><img src='" + screen + "'>"
              + screenshotName + "</a>");
    } catch (Exception e) {
      System.out.println(e.getStackTrace());
    }
  }

  /**
   * Report logs
   *
   * @param : message
   */
  public void reportLog(String message) {
    test.log(LogStatus.PASS, message);
    message = BREAK_LINE + message;
    logger.info("Message: " + message);
    Reporter.log(message);
  }
}
