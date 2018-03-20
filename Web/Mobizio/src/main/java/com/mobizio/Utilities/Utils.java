package com.mobizio.Utilities;

import java.io.File;

public class Utils {

  public static String getPath() {
    String path;
    File file = new File("");
    String absolutePathOfFirstFile = file.getAbsolutePath();
    path = absolutePathOfFirstFile.replaceAll("\\\\+", "/");
    return path;
  }
}
