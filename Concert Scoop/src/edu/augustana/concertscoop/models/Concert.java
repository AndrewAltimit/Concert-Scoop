package edu.augustana.concertscoop.models;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import edu.augustana.concertscoop.util.JSONParser;

public class Concert {

	public Concert(int server_id) {
		//
		// Make a connection with the server

		// pull data from server about this concert based on the id number
		// And put the data into an arraylist

		// Populate fields with the pulled data
		// Concert(jConcert);
	}

	public Concert(JSONObject jConcert) throws JSONException {
		city = (String) jConcert.get("city");
		created_at = (Date) jConcert.get("created_at");
		facebook_page = (String) jConcert.get("facebook_page");
		name = (String) jConcert.get("name");
		start_time = (Date) jConcert.get("start_time");
		state = (String) jConcert.get("state");
		twitter_tag = (String) jConcert.get("twitter_tage");
		updated_at = (Date) jConcert.get("updated_at");
		zip = (String) jConcert.get("zip");
	}

	// Connects to the web server and returns an ArrayList of concerts
	public static ArrayList<Concert> getConcerts() {
		try {
			ServerConnection conn = new ServerConnection();
			HttpResponse response;
			response = conn.execute(GET_CONCERTS).get();
			JSONParser jParser = new JSONParser<Concert>(response,
					Concert.class);
			JSONArray concerts = jParser.parseAll();
			return jParser.JSONtoObjects(concerts);
		} catch (IllegalArgumentException e) {

			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Make a new concert entry
	public boolean update() {
		return true;
	}

	public String city;
	public Date created_at;
	public String facebook_page;
	public String name;
	public Date start_time;
	public String state;
	public String twitter_tag;
	public Date updated_at;
	public String zip;

	static final String GET_CONCERTS = "concerts.json";

}
