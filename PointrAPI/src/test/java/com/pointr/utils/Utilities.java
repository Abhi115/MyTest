package com.pointr.utils;

import java.io.File;
import java.sql.Timestamp;
import java.util.Random;

import org.apache.commons.lang3.RandomUtils;


public class Utilities {

	// Get random integer
	public static int getRandomInteger(int aStart, int aEnd) {
		Random aRandom = new Random();
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		// get the range, casting to long to avoid overflow problems
		long range = (long) aEnd - (long) aStart + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long) (range * aRandom.nextDouble());
		int randomNumber = (int) (fraction + aStart);
		return randomNumber;
	}

	// Get random string
	public static String randomString(int len) {
		String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}
	
	//Get Random Number
	public static int getRandomNumber(int min,int max){
		int  num = RandomUtils.nextInt(min, max);
		return num;		
	}
		

	//Get Random Number
	public static long getTimeStamp(){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp.getTime();		 	
	}
	
	
	//Get Number of count in directory
	public static int getFilesCount(File file) {
		  File[] files = file.listFiles();
		  int count = 0;
		  for (File f : files)
		    if (f.isDirectory())
		      count += getFilesCount(f);
		    else
		      count++;

		  return count;
		}
}
