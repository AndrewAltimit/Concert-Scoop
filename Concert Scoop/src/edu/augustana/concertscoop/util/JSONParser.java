package edu.augustana.concertscoop.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import android.util.Log;

/**
 * Utlity Class - Takes an HTTP response and parses it into either a JSONObject
 * or a JSONArray of JSONObjects
 */
public class JSONParser {

	/**
	 * Constructor - Creates a new JSONParser object coupled to an HttpResponse
	 * 
	 * @param serverReply
	 *            The HTTPResponse
	 */
	public JSONParser(String serverReply) {
		jsonString = serverReply;
	}

	/**
	 * Parse an HTTPRequest given that the request has multiple JSONObjects
	 * 
	 * @return JSONArray All the JSONObjects that were in the HttpRequest
	 */
	public JSONArray parseAll() {
		try {
			JSONArray jArray;
			jArray = new JSONArray(jsonString);
			return jArray;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JSONArray();
	}

	/**
	 * Parse an HTTPRequest given that the request has multiple JSONObjects
	 * 
	 * @return JSONArray All the JSONObjects that were in the HttpRequest
	 * @param emptyValue
	 *            Replace null JSONObject values with emptyValue
	 */
	public JSONArray parseAll(String emptyValue) {
		JSONArray jArray = parseAll();
		try {
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject jObject = (JSONObject) jArray.get(i);
				Iterator<?> keys = jObject.keys();
				while (keys.hasNext()) {
					String key = (String) keys.next();
					if (jObject.isNull(key)) {
						jObject.put(key, emptyValue);
						jArray.put(i, jObject);
					}
				}
			}
		} catch (Exception e) {

		}
		return jArray;
	}

	/**
	 * Parse an HTTPRequest given that the request only has one JSONObject
	 * 
	 * @return JSONObject The JSONObject from the HttpRequest
	 */
	public JSONObject parse() {
		try {
			JSONObject jObject;
			jObject = new JSONObject(jsonString);
			return jObject;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JSONObject();
	}

	/**
	 * Parse an HTTPRequest given that the request only has one JSONObject
	 * 
	 * @return JSONObject The JSONObject from the HttpRequest
	 * @param emptyValue
	 *            Replace null JSONObject values with emptyValue
	 */
	public JSONObject parse(String emptyValue) {
		JSONObject jObject = parse();
		try {
			Iterator<?> keys = jObject.keys();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				if (jObject.isNull(key)) {
					jObject.put(key, emptyValue);
				}
			}
		} catch (Exception e) {

		}
		return jObject;
	}

	private String jsonString;
}
