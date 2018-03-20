package com.natera.util;

import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.io.FileWriter;
import java.io.IOException;
//import java.io.InputStream;
import java.io.OutputStream;
//import java.io.PrintWriter;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



public class ExecutionLogger 
{
	
	public static void SetFilePath() throws IOException
	{
		
		
		Properties prop = new Properties();
		
		// Get Root Directory Path
		String dirPath = System.getProperty("user.dir");

		File log4jpropertiesfile = new File(dirPath
				+ "//src//com//natera//config//log4j.properties");

		File manuallogfile = new File(dirPath + "//ExecutionLog//Manual.logs");

		File seleniumlogfile = new File(dirPath
				+ "//ExecutionLog//Selenium.logs");

		OutputStream output = null;

		output = new FileOutputStream(log4jpropertiesfile);

		// set the properties value

		// Root Logs

		prop.setProperty("log4j.rootLogger", "DEBUG,file");
		prop.setProperty("log4j.appender.file",
				"org.apache.log4j.RollingFileAppender");
		prop.setProperty("log4j.appender.file.File", seleniumlogfile.toString());
		prop.setProperty("log4j.appender.file.maxFileSize", "900KB");
		prop.setProperty("log4j.appender.file.maxBackupIndex", "5");
		prop.setProperty("log4j.appender.file.layout",
				"org.apache.log4j.PatternLayout");
		prop.setProperty("log4j.appender.file.layout.ConversionPattern",
				"%d{ABSOLUTE} %5p %c<strong><strong>{1}</strong></strong>:%L - %m%n");
		prop.setProperty("log4j.appender.file.Append", "true");

		// Application Logs

		prop.setProperty("log4j.logger.devpinoyLogger", "DEBUG, dest1");
		prop.setProperty("log4j.appender.dest1",
				"org.apache.log4j.RollingFileAppender");
		prop.setProperty("log4j.appender.dest1.maxFileSize", "900KB");
		prop.setProperty("log4j.appender.dest1.maxBackupIndex", "6");
		prop.setProperty("log4j.appender.dest1.layout",
				"org.apache.log4j.PatternLayout");
		prop.setProperty("log4j.appender.dest1.layout.ConversionPattern",
				"%d{dd/MM/yyyy HH:mm:ss} %c %m%n");
		prop.setProperty("log4j.appender.dest1.File", manuallogfile.toString());
		prop.setProperty("log4j.appender.dest1.Append", "true");
		// save properties to project root folder
		prop.store(output, null);
		output.close();
		
      PropertyConfigurator.configure(log4jpropertiesfile.toString());
		
	}
	
	public static void Log(String logtext) throws IOException {

		ExecutionLogger.SetFilePath();
		 
		 Logger log = Logger.getLogger("devpinoyLogger");

		try {

			log.debug(logtext);

		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

	}
	
	public static void LogErrorMessage(Error e) throws IOException {
		
		ExecutionLogger.SetFilePath();
		 
		 Logger log = Logger.getLogger("devpinoyLogger");

		try {

			//log.debug(logtext);
			log.error(e);

		} catch (Exception ex) {// Catch exception if any
			System.err.println("Error: " + ex.getMessage());
		}
		
	}
	
	public static void main(String[] str) {
		//Log("Test execution");
	}

	
}
