package com.pointr.utils;

import static com.pointr.utils.GlobalConstant.FileName.PoiJsonData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileUtil {

	/**
	 * read json file content from file
	 * 
	 * @param fileName:
	 *            it contains file name
	 * @param input:
	 *            set flag value for input of output file
	 * @return : it will return JSON object of content
	 * @throws Exception
	 */
	public static JSONObject readJsonData(String folderName, String fileName, boolean input) throws Exception {
		if (fileName.isEmpty() || fileName == null)
			throw new Exception("Please provide valid file name");
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		Object obj = null;
		try {
			if (input) {
				obj = parser.parse(new FileReader(folderName+"/" + fileName + ".json"));
			} else {
				obj = parser.parse(new FileReader(folderName+"/" + fileName + ".json"));
			}
			jsonObject = (JSONObject) obj;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 
	 * @param json
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static JSONObject updatePoiJsonInput(JSONObject json, String key, String value) throws Exception {
		if (key.equals("id")) {
			return JsonFileUtil.updateJsonValue(json, key, value + Utilities.getTimeStamp());
		} else {
			JSONObject jsonObj = JsonFileUtil.updateJsonValue(json, "id",
					"poi" + Utilities.getTimeStamp());
			return JsonFileUtil.updateJsonValue(jsonObj, key, value);
		}
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static JSONObject updateJsonValue(JSONObject json, String key, String value) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String> properties = ((Map<String, String>) json.get("properties"));
		properties.put(key, value);
		return json;
	}
	
	/**
	 * Update Json file content for Object and array type both
	 * @param json : Object of json content
	 * @param key :  Keys of json data which need to update
	 * @param value : values of key
	 * @return is return object 
	 * @throws Exception
	 */
	public static Object updateJsonValue(Object json, String key, String value) throws Exception {
		JSONObject jsonObject = null;
		JSONArray jsonArray = null;
		if (json instanceof JSONArray) {
			// It's an array
			jsonArray = (JSONArray) json;
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject obj = (JSONObject) jsonArray.get(i);				
				updateJsonValue(obj, key, value+i);
			}
		} else if (json instanceof JSONObject) {
			// It's an object
			jsonObject = (JSONObject) json;			
			return (Object) updateJsonValue(jsonObject, key, value);			  
		} else {
			throw new Exception("Please provide valied Json");
		}
		return json;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String updateInputJsonPropertiesParameters(String key, String value) throws Exception {
		JSONObject jo = JsonFileUtil.readJsonData(GlobalConstant.poiinput, PoiJsonData.toString(), true);
		@SuppressWarnings("unchecked")
		Map<String, String> properties = ((Map<String, String>) jo.get("properties"));
		properties.put(key, value);
		String updatedJSON = jo.toJSONString();
		return updatedJSON;
	}

	
	public static void main(String[] str) throws Exception {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(
				"{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150210067910419\",\"level\":\"2\",\"name\":\"test2\",\"description\":\"becon\",\"type\":\"14\",\"keywords\":\"\",\"isProximityBeacon\":\"false\",\"uuid\":\"beacon\",\"major\":\"77\",\"minor\":\"645\",\"floorsArray\":\"2\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[21.794754028320312,-79.59233093261719]}}");
		Object updateJson = updateJsonValue(obj, "id", "Testing");
		System.out.println(updateJson.toString());
	}
}