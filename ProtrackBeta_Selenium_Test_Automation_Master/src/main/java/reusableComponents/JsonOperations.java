package reusableComponents;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonOperations {
	JSONObject jsonobject;
	//create json Object
		public JsonOperations(String jsonFilePath) {
			try {
			JSONParser jp = new JSONParser();
			 jsonobject = (JSONObject) jp.parse(new FileReader(jsonFilePath));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		public String get(String key) {	
			return (String) jsonobject.get(key);
		}
		
		
	//	public static 
}
