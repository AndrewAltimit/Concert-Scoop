package edu.augustana.concertscoop.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {

	// Creates a new JSONParser object coupled to an HttpResponse
	public JSONParser(HttpResponse serverReply) {
		HttpEntity httpEntity = serverReply.getEntity();
		try {
			is = httpEntity.getContent();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// returns a JSONArray of all the JSONObjects that were in the HttpRequest
	public JSONArray parseAll() {
		getStringFromResponse();
		try {
			JSONArray jArray;
			jArray = new JSONArray(jsonString);
			return jArray;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JSONArray();
	}

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

	// returns a JSONArray of all the JSONObjects that were in the HttpRequest
	public JSONObject parse() {
		getStringFromResponse();
		try {
			JSONObject jObject;
			jObject = new JSONObject(jsonString);
			return jObject;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JSONObject();
	}

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

	// Takes the HttpResponse object and saves a string that can be used to
	// build JSONArrays or JSONObjects
	private void getStringFromResponse() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			jsonString = sb.toString();

		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}
	}

	private InputStream is;
	private String jsonString;
}
