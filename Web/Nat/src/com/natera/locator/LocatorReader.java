package com.natera.locator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.natera.util.PropertyReader;

public class LocatorReader {

	private Document doc;
	private  PropertyReader propObj;  
	
	public LocatorReader(String xmlName) {
		SAXReader reader = new SAXReader();
		try {
			propObj = new PropertyReader();
			String localPath = propObj.getPath();
		    localPath = localPath+"//src//com//natera//Locator//";		
			doc = reader.read(localPath+xmlName);			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getLocator(String locator){
			return doc.selectSingleNode("//" + locator.replace('.', '/')).getText();
		
	}
}
