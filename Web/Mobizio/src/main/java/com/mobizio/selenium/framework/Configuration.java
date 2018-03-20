package com.mobizio.selenium.framework;

import com.mobizio.Utilities.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class Configuration {

  private String fileName;

  public Configuration(String fileName) {
    this.fileName = fileName;
  }

  public static String readApplicationFile(String key) throws Exception {
    String value;
    try {
      Properties prop = new Properties();
      File f = new File(Utils.getPath() + "//ServerLogin.properties");
      if (f.exists()) {
        prop.load(new FileInputStream(f));
        value = prop.getProperty(key);
      } else {
        throw new Exception("File not found");
      }
    } catch (FileNotFoundException ex) {
      System.out.println("Failed to read from application.properties file.");
      throw ex;
    }
    if (value == null) {
      throw new Exception("Key not found in properties file");
    }
    return value;
  }

  public Properties readApplicationData() throws Exception {
    Properties prop = new Properties();
    try {
      File f = new File(
          Utils.getPath() + "//src//test//resources//testdata//" + fileName + ".properties");
      if (f.exists()) {
        prop.load(new FileInputStream(f));
      }
    } catch (FileNotFoundException e) {
      System.out.println("Failed to read from application.properties file.");
      throw new FileNotFoundException("File not found " + fileName);
    }
    return prop;
  }
}
