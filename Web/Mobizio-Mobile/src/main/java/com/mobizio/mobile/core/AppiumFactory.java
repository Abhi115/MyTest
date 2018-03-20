package com.mobizio.mobile.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;

import com.mobizio.utils.Configuration;


public class AppiumFactory {

	protected AppiumDriver<MobileElement> driver;
	private static final Logger logger = LoggerFactory.getLogger(AppiumFactory.class);
	protected int timeOut = 120;
	private int port = 0;
	private String udid;

	public AppiumFactory(String udid, int port) {
		this.udid = udid;
		this.port = port;
	}
	public AppiumFactory() {}
	
	/**
	 * it create session of Appium object (iOS and Android )
	 * @return : return appium driver object
	 * @throws Exception : throw exception
	 */
	public AppiumDriver<MobileElement> setUp() throws Exception {
		// setup port
		if (port == 0)
			port = Integer.parseInt(Configuration.readApplicationFile("Port"));
		
		String host = Configuration.readApplicationFile("Host");
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://" + host + ":" + port + "/wd/hub"),
					getDesiredCapabilities(getAppAbsoultePath()));
		} catch (Exception e) {
			logger.debug("appium server not stated");
			throw new Exception(e);
		}
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
		return driver;

	}

	/**
	 * Destroy appium driver session 
	 * @throws Exception
	 */
	public void closeAppiumSession() throws Exception {
		driver.quit();
	}

	/**
	 * @author Get absolute path of android application apk file. Apk file is
	 *         under the app folder
	 * @param no
	 *            parameter
	 * @return absolute path.
	 * @throws Exception
	 */
	public String getAppAbsoultePath() throws Exception {
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "/app");
		File app = new File(appDir, Configuration.readApplicationFile("APKFileName") + ".apk");
		String appName = app.getAbsolutePath();
		return appName;
	}

	/**
	 * @author Setup configuration in DesiredCapabilities which appium used to
	 *         run test
	 * @param appPath
	 *            application absolute path
	 * @return object of DesiredCapabilities.
	 */
	public DesiredCapabilities getDesiredCapabilities(String appPath) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android");
		capabilities.setCapability("platformVersion", "7.0");
		capabilities.setCapability("unicodeKeyboard ", "true");
		capabilities.setCapability("app", appPath);
		
		if (StringUtils.isNoneBlank(udid)) {
			capabilities.setCapability("udid", udid);
		}
		return capabilities;
	}

	/**
	 * close and re launch application
	 */
	public void relaunchApp() {
		driver.closeApp();
		driver.launchApp();
	}

	/***
	 * get application name from app folder
	 * 
	 * @return : return applicaion name
	 */
	public String getAppName() {
		String fileName = "";
		File folder = new File("app");
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles == null) {
			throw new NullPointerException("app directory is null");
		}
		for (File listFile : listOfFiles) {
			String fileExtension = FilenameUtils.getExtension(listFile.getAbsolutePath());
			if (fileExtension.equals("apk")) {
				fileName = listFile.getName();
				break;
			}
		}
		return fileName;
	}

	/**
	 * read all connect devices udid
	 * 
	 * @return : it return list of udid of connected devices 
	 */
	public static List<String> getAttachedDevicesList() {

		List<String> devicesID = new ArrayList<String>();
		try {
			Process process = Runtime.getRuntime().exec(getAndroidPath() + "//platform-tools//adb devices");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String s;
			while ((s = reader.readLine()) != null) {
				if (s.contains("device") && !s.contains("attached")) {
					String[] device = s.split("\t");
					devicesID.add(device[0]);
				}
			}

		} catch (IOException e) {
			logger.debug("adb command not executed to capture devices");
		}
		return devicesID;
	}
	
	
	/**
	 * prepare data provider for connected devices
	 * @return : return objects of connected devices and port number
	 */
	@DataProvider(name = "listDevices", parallel = true)
	public static Object[][] listDevices() {

		Object obj[][] = new Object[getAttachedDevicesList().size()][2];
		List<String> devicesList = getAttachedDevicesList();
		for (int i = 0; i < devicesList.size(); i++) {
			obj[i][0] = devicesList.get(i);
			obj[i][1] = 4723 + i;
		}
		return obj;
	}

	/**
	 * get android home path
	 * 
	 * @return : return ANDROID HOME path
	 */
	public static String getAndroidPath() {
		String androidHome = System.getProperty("ANDROID_HOME");
		if (androidHome == null) {
			androidHome = System.getenv("ANDROID_HOME");
		}
		if (StringUtils.isEmpty(androidHome)) {
			throw new NullPointerException("Android Home path not set in machine");
		}
		return androidHome;
	}

	/**
	 * Reset application
	 */
	public void appReset() {
		driver.resetApp();
	}

	/***
	 * Created folder
	 * 
	 * @param folderPath:
	 *            folder name
	 */
	public void createFolder(String folderPath) {
		File file = new File(folderPath);
		if (!file.exists()) {
			file.mkdir();
		}
	}

	/**
	 * Capture screenshot and same into screen shot folder
	 * 
	 * @param fileName : file name of screen shot
	 */
	public void captureScreenshot(String fileName) {
		String folderPath = "build/outputs/screenshots/";
		createFolder("build/outputs");
		createFolder(folderPath);
		try {
			FileOutputStream out = new FileOutputStream(folderPath + fileName + ".jpg");
			out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			out.close();
		} catch (Exception e) {
		}
	}

	/**
	 * report log message in html report file
	 * 
	 * @param message
	 */
	public void reportLog(String message) {
		Reporter.log(message + "<br>");
	}

	/**
	 * Switch in View like web view/native view
	 * @param view : type of view
	 * @throws Exception 
	 */
	public void switchOtherView(String view) throws Exception {		
		if(StringUtils.isEmpty(view) || view.equals(null))
			throw new Exception("Please provide view name");
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
			if (StringUtils.contains(contextName.toLowerCase(), view.toLowerCase())) {
				System.out.println("switched");
				driver.context(contextName);
			}

		}
	}

}
