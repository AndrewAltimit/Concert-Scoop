package edu.augustana.concertscoop.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

public class JSONParser<E> {
	
	//Creates a new JSONParser object coupled to an HttpResponse
	public JSONParser(HttpResponse serverReply, Class<E> clazz) {
		this(clazz);

		HttpEntity httpEntity = serverReply.getEntity();
		jObj = new JSONObject();
		try {
			is = httpEntity.getContent();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Since our class needs to instantiate objects but we don't know the type
	// at complie time its up to the caller to pass that information in
	public JSONParser(Class<E> clazz) {
		this.clazz = clazz;
	}

	//returns a JSONArray of all the JSONObjects that were in the HttpRequest
	public JSONArray parseAll() throws JSONException {
		getStringFromResponse();
		return new JSONArray(jsonString);
	}

	//returns a JSONObject from an HttpRequest
	public JSONObject parse() {
		getStringFromResponse();
		return null;
	}

	//Takes a JSONArray of JSONObjects and returns an ArrayList of E objects
	public ArrayList<E> JSONtoObjects(JSONArray jArray)
			throws IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, JSONException 
	{
		ArrayList<E> list = new ArrayList<E>();
		for (int i = 0; i < jArray.length(); i++) 
		{
			jObj = (JSONObject) jArray.get(i);
			list.add(createObject());
		}
		return list;
	}
	
	//Takes a JSONObject and returns an E object
	public E JSONtoObject(JSONObject jObject) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		return createObject();
	}
	
	//Takes the HttpResponse object and saves a string that can be used to build JSONArrays or JSONObjects
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
	
	
	//Builds E objects using their JSONObject constructor
	private E createObject() throws IllegalArgumentException,
		InstantiationException, IllegalAccessException,
		InvocationTargetException, NoSuchMethodException {
		return clazz.getDeclaredConstructor(JSONObject.class).newInstance(jObj);
	}

	private JSONObject jObj;
	private InputStream is;
	private String jsonString;
	private final Class<E> clazz;
}
