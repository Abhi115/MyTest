package com.edureka.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


/**
 * This class contains all web driver methods that are used in helper files
 * 
 * @author 360logica
 * 
 */
public abstract class DriverHelper {
    protected PropertyReader propertyReader;
    protected static WebDriverWait ajaxWait;
    private String time;
    private int TIME_OUT;
    private String pageLoadTime;
    private int PAGE_LOAD_TIME_OUT;
    /**
     * Define objects
     */
    protected static WebDriver driver;

    /**
     * Declare objects
     * 
     * @param webdriver
     */
    public DriverHelper(WebDriver webdriver) {
        driver = webdriver;
        propertyReader = new PropertyReader();
        ajaxWait = new WebDriverWait(driver, TIME_OUT);
    }

    /**
     * Return web driver object
     * 
     * @return
     */
    public WebDriver getWebDriver() {
        return driver;
    }

    /**
     * Print message on screen
     * 
     * @param logMsg
     */
    public void Log(String logMsg) {
        System.out.println(logMsg);
    }

    /**
     * Handle locator type
     * 
     * @param locator
     * @return
     */
    public By ByLocator(String locator) {
        By result = null;

        if (locator.startsWith("//")) {
            result = By.xpath(locator);
        } else if (locator.startsWith("css=")) {
            result = By.cssSelector(locator.replace("css=", ""));
        } else if (locator.startsWith("#")) {
            result = By.name(locator.replace("#", ""));
        } else if (locator.startsWith(".")) {
            result = By.name(locator.replace(".", ""));
        } else if (locator.startsWith("link=")) {
            result = By.linkText(locator.replace("l ink=", ""));
        }
        else if(locator.startsWith("xpath")){
            By.xpath(locator);
        }
        else {
            result = By.id(locator);
        }
        return result;
    }

    /**
     * Assert element present
     * 
     * @param locator
     * @return
     */
    public Boolean isElementPresent(String locator) {
        Boolean result = false;
        try {
            getWebDriver().findElement(ByLocator(locator));
            result = true;
        } catch (Exception ex) {
        }
        return result;
    }

    /**
     * Wait for element present
     * 
     * @param locator
     * @param timeout
     */
    public void WaitForElementPresent(String locator, int timeout) {
        for (int i = 0; i < timeout; i++) {
            if (isElementPresent(locator)) {
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Wait for element not present
     * 
     * @param locator
     * @param timeout
     */
    public void WaitForElementNotPresent(String locator, int timeout) {
        for (int i = 0; i < timeout; i++) {
            if (!isElementPresent(locator)) {
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Wait for element enabled
     * 
     * @param locator
     * @param timeout
     */
    public void WaitForElementEnabled(String locator, int timeout) {
        for (int i = 0; i < timeout; i++) {
            if (isElementPresent(locator)) {
                if (getWebDriver().findElement(ByLocator(locator)).isEnabled()) {
                    break;
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Wait for element not enabled
     * 
     * @param locator
     * @param timeout
     */
    public void WaitForElementNotEnabled(String locator, int timeout) {
        for (int i = 0; i < timeout; i++) {
            if (isElementPresent(locator)) {
                if (!getWebDriver().findElement(ByLocator(locator)).isEnabled()) {
                    break;
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Wait for element visible
     * 
     * @param locator
     * @param timeout
     */
    public void WaitForElementVisible(String locator, int timeout) {
        for (int i = 0; i < timeout; i++) {
            if (isElementPresent(locator)) {
                if (getWebDriver().findElement(ByLocator(locator))
                        .isDisplayed()) {
                    break;
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Wait for element not visible
     * 
     * @param locator
     * @param timeout
     */
    public void WaitForElementNotVisible(String locator, int timeout) {
        for (int i = 0; i < timeout; i++) {
            if (isElementPresent(locator)) {
                if (!getWebDriver().findElement(ByLocator(locator))
                        .isDisplayed()) {
                    break;
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Wait for text present
     * 
     * @param locator
     * @param text
     * @param timeout
     */
    public void WaitForTextPresent(String locator, String text, int timeout) {
        WaitForElementPresent(locator, timeout);
        for (int i = 0; i < timeout; i++) {
            if (isTextPresent(locator, text)) {
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handle mouse over action
     * 
     * @param locator
     */
    public void mouseOver(String locator) {
        WaitForElementPresent(locator, 50);
        WebElement el = getWebDriver().findElement(ByLocator(locator));

        // build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(getWebDriver());
        builder.moveToElement(el).build().perform();
    }

    /**
     * Handle mouse double click action
     * 
     * @param locator
     */
    public void mouseDoubleClick(String locator) {
        WaitForElementPresent(locator, 50);
        WebElement el = getWebDriver().findElement(ByLocator(locator));

        // build and perform the mouse click with Advanced User Interactions API
        Actions builder = new Actions(getWebDriver());
        builder.doubleClick(el).perform();
        builder.doubleClick().perform();
    }

    /**
     * Handle drag and drop action
     * 
     * @param draggable
     * @param to
     */
    public void dragAndDrop(String draggable, String to) {
        WaitForElementPresent(draggable, 50);
        WaitForElementPresent(to, 50);
        WebElement elDraggable = getWebDriver().findElement(
                ByLocator(draggable));
        WebElement todrag = getWebDriver().findElement(ByLocator(to));

        // build and perform drag and drop with Advanced User Interactions API
        Actions builder = new Actions(getWebDriver());
        builder.dragAndDrop(elDraggable, todrag).perform();
    }

    /**
     * Handle click action
     * 
     * @param locator
     * @throws InterruptedException 
     */
    public void clickOn(String locator){
        WaitForElementPresent(locator, 30);
        Assert.assertTrue(isElementPresent(locator), "Element Locator :"
                + locator + " Not found");
        WebElement el = getWebDriver().findElement(ByLocator(locator));
        el.click();
    }

    /**
     * Handle send keys action
     * 
     * @param locator
     * @param text
     */
    public void sendKeys(String locator, String text) {
        WaitForElementPresent(locator, 30);
        Assert.assertTrue(isElementPresent(locator), "Element Locator :"
                + locator + " Not found");
        WebElement el = getWebDriver().findElement(ByLocator(locator));
        el.clear();
        el.sendKeys(text);
    }

    public String getCSSValue(String locator, String text) {
        WaitForElementPresent(locator, 20);
        Assert.assertTrue(isElementPresent(locator), "Element Locator :"
                + locator + " Not found");
        String value = getWebDriver().findElement(ByLocator(locator)).getCssValue(text);
        return value;
    }

    /**
     * Handle send keys action
     * 
     * @param locator
     * @param text
     */
    public void sendKeysByKeyboard(String locator, String text) {
        WaitForElementPresent(locator, 30);
        Assert.assertTrue(isElementPresent(locator), "Element Locator :"
                + locator + " Not found");
        WebElement el = getWebDriver().findElement(ByLocator(locator));
        el.clear();
        String val = text; 
        for (int i = 0; i < val.length(); i++){
            char c = val.charAt(i);
            String s = new StringBuilder().append(c).toString();
            el.sendKeys(s);
        } 
    }
    /**
     * Get size of element
     * 
     * @param locator
     * @return
     */
    public int getSize(String locator) {
        WaitForElementPresent(locator, 30);
        Assert.assertTrue(isElementPresent(locator), "Element Locator :"
                + locator + " Not found");
        int total = getWebDriver().findElements(ByLocator(locator)).size();
        return total;
    }

    /**
     * Select value from drop down
     * 
     * @param locator
     * @param targetValue
     */
    public void selectDropDown(String locator, String targetValue) {
        Assert.assertTrue(isElementPresent(locator), "Element Locator :"
                + locator + " Not found");
        WaitForElementPresent(locator, 20);
        new Select(getWebDriver().findElement(ByLocator(locator)))
        .selectByVisibleText(targetValue);

    }

    /**
     * Assert text present
     * 
     * @param locator
     * @param str
     * @return
     */
    public boolean isTextPresent(String locator, String str) {
        Assert.assertTrue(isElementPresent(locator), "Element Locator :"
                + locator + " Not found");
        String message = getWebDriver().findElement(ByLocator(locator))
                .getText();
        if (message.contains(str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Store text from a locator
     * 
     * @param locator
     * @return
     */
    public String getText(String locator) {
        WaitForElementPresent(locator, 20);
        Assert.assertTrue(isElementPresent(locator), "Element Locator :"
                + locator + " Not found");
        String text = getWebDriver().findElement(ByLocator(locator)).getText();
        return text;
    }

    /**
     * Assert check box selected
     * 
     * @param locator
     * @return
     */
    public boolean isChecked(String locator) {
        boolean checkStatus = false;
        WaitForElementPresent(locator, 20);
        Assert.assertTrue(isElementPresent(locator), "Element Locator :"
                + locator + " Not found");
        WebElement el = getWebDriver().findElement(ByLocator(locator));
        checkStatus = el.isSelected();
        return checkStatus;
    }

    /**
     * Get attribute value
     * 
     * @param locator
     * @param attribute
     * @return
     */
    public String getAttribute(String locator, String attribute) {
        WaitForElementPresent(locator, 20);
        Assert.assertTrue(isElementPresent(locator), "Element Locator :"
                + locator + " Not found");
        String text = getWebDriver().findElement(ByLocator(locator))
                .getAttribute(attribute);
        return text;
    }

    /**
     * Execute java script
     * 
     * @param javascrpt
     */
    public void javaScriptExecute(String javascrpt) {
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript(javascrpt);
    }

    /**
     * To handle the alert pop up
     */
    public void acceptAlert() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Alert alert = getWebDriver().switchTo().alert();
        alert.accept();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Get the Total Rows of table
     * 
     * @param locator
     * @return
     */
    public int getTotalRow(String locator) {
        Assert.assertTrue(isElementPresent(locator), "Element " + locator
                + " is not present");
        int number = driver.findElements(ByLocator(locator)).size();
        return number;
    }

    /**
     * Scroll window
     * 
     * @param x
     * @param y
     */
    public void scrollWindow(final int x, final int y) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(" + x + "," + y + ")");
    }

    /**
     * Mouse click by locator
     * 
     * @param locator
     * @param element
     */
    public void mouseClickByLocator(final String locator, final String element) {
        WaitForElementPresent(locator, 20);
        Assert.assertTrue(isElementPresent(locator), "Element Locator :"
                + locator + " Not found for element" + element);
        final WebElement el = driver.findElement(ByLocator(locator));
        final Actions builder = new Actions(driver);
        builder.moveToElement(el).click(el);
        builder.perform();
    }

    /**
     * Initialize Time out values
     */
    public void initializeTimeOutValues() {
        time = propertyReader.readApplicationFile("Time_Out");
        TIME_OUT = Integer.parseInt(time);
        pageLoadTime = propertyReader.readApplicationFile("Page_Load_Time_Out");
        PAGE_LOAD_TIME_OUT = Integer.parseInt(pageLoadTime);
    }

    /**
     * This function returns the time out value
     * 
     * @return
     */
    public int getTimeOut() {
        return TIME_OUT;
    }

    /**
     * This function returns the page load timeout value
     * 
     * @return
     */
    public int getPageLoadTimeOut() {
        return PAGE_LOAD_TIME_OUT;
    }
    /**
     * Get random string
     * 
     * @param len
     * @return
     */
    public String randomString(int len) {
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public void check(String locator) throws Exception {
        WaitForElementPresent(locator, 5);
        WebElement ele = driver.findElement(By.xpath(locator));

        if (ele.isSelected() == false) {
            ele.click();
        }
    }

    /**
     *  Wait for webElement Present
     * @param element
     */
    public static void waitForWebElementPresent(WebElement element) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(element));
    }

    /**
     *  Switch Tab
     */
    public void switchToTab() {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"\t");
        driver.switchTo().defaultContent();  
    }

    /**
     *  Get Present Date
     * @return
     */
    public String presenttDate(){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentDate = format.format(date);
        return currentDate;
    }

    /** 
     * Get Next Date
     * @return
     * @throws ParseException 
     */
    public String nexDayDate() throws ParseException{
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentDate = format.format(date);

        date = format.parse(currentDate);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        format.format(calendar.getTime());
        return format.format(calendar.getTime());
    }
    /**
     *  Get after 7 years date 
     * @return
     * @throws ParseException
     */
    public String endDate() throws ParseException{
        final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        final String currentDate = format.format(date);

        date = format.parse(currentDate);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 7);
        format.format(calendar.getTime());
        return format.format(calendar.getTime());
    }

    /**
     *  Wait for Loading 
     * @throws InterruptedException
     */
    public void waitForLoading() throws InterruptedException {
        String loading = "//*[@id='ajax-loader']";
        WaitForElementPresent(loading, getTimeOut());
        WaitForElementNotVisible(loading, getTimeOut());
    }

    /**
     *  Verify Class Loading
     * @throws InterruptedException
     */
    public void waitForClassLoading() throws InterruptedException {
        String classLoader = "//p[@id='spinner-message']";
        WaitForElementPresent(classLoader, getTimeOut());
        WaitForElementNotVisible(classLoader, 30);
    }

    /**
     *  Page Loading
     * @throws InterruptedException
     */
    public void pageLoading() throws InterruptedException {
        String loading = "//*[@id='ajax-loader']";
        WaitForElementPresent(loading, getTimeOut());
        Thread.sleep(15000);
    }

    public void waitForElement() throws InterruptedException {
        Thread.sleep(2000);
    }

    /**
     *   Make value in round
     * @param value
     * @param numberOfDigitsAfterDecimalPoint
     * @return
     */
    public static double round(double value, int numberOfDigitsAfterDecimalPoint) {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(numberOfDigitsAfterDecimalPoint, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.doubleValue();
    }


    /**
     * Webdriver Wait
     * @param timeoutInSeconds
     * @return
     */
    private WebDriverWait _getWebDriverWait(int timeoutInSeconds) {
        return new WebDriverWait(DriverHelper.driver, timeoutInSeconds);
    }

    /**
     * Wait for complete javascript to load till timeout
     * @return
     */
    public boolean _waitForJStoLoad() {
        WebDriverWait wait = this._getWebDriverWait(120);
        boolean waitDone = wait.until(this.jQueryLoad) && wait.until(this.jsLoad);
        return waitDone;
    }

    /**
     *  wait for jQuery to load
     */
    private final ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
        @Override
        public Boolean apply(WebDriver theDriver) {
            try {
                return ((Long) DriverHelper.this._executeJavaScript("return jQuery.active") == 0);
            } catch (Exception e) {
                return true;
            }
        }
    };

    /**
     * This method will wait till document Ready
     * 
     */
    private final ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
        @Override
        public Boolean apply(WebDriver theDriver) {
            Object rsltJs = DriverHelper.this._executeJavaScript("return document.readyState");
            if (rsltJs == null) {
                rsltJs = "";
            }
            return rsltJs.toString().equals("complete") || rsltJs.toString().equals("loaded");
        }
    };

    private Object _executeJavaScript(String jsCode) {
        return ((JavascriptExecutor) DriverHelper.driver).executeScript(jsCode);
    }
    
}
