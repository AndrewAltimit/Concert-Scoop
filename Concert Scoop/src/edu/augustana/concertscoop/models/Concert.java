package edu.augustana.concertscoop.models;

import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import edu.augustana.concertscoop.util.JSONParser;

public class Concert {

	public Concert(int server_id) {
		//
		// Make a connection with the server
		JSONObject jConcert = getConcert(server_id);
		setFields(jConcert);
	}

	public Concert(JSONObject jConcert){
		setFields(jConcert);
	}

	
	private void setFields(JSONObject jConcert){
		try {
			city = (String) jConcert.get("city");
			created_at = (String) jConcert.get("created_at");
			facebook_page = (String) jConcert.get("facebook_page");
			name = (String) jConcert.get("name");
			start_time = (String) jConcert.get("start_time");
			state = (String) jConcert.get("state");
			twitter_tag = (String) jConcert.get("twitter_tag");
			updated_at = (String) jConcert.get("updated_at");
			zip = (String) jConcert.get("zip");
			id =  (Integer) jConcert.get("id");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	// Connects to the web server and returns an ArrayList of concerts
	public static ArrayList<Concert> getConcerts() {
		ServerConnection conn = new ServerConnection();
		HttpResponse response;
		try {
			response = conn.execute(GET_CONCERTS).get();
			JSONParser jParser = new JSONParser(response);
			JSONArray jsonConcerts;
			jsonConcerts = jParser.parseAll("");
			ArrayList<Concert> concertslist = new ArrayList<Concert>();
			for (int i = 0; i < jsonConcerts.length(); i++) {
				concertslist.add(new Concert((JSONObject) jsonConcerts.get(i)));
			}
			return concertslist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Concert>();
	}

	// Make a new concert entry
	public boolean update() {
		return true;
	}
	
	private JSONObject getConcert(int server_id) {
		ServerConnection conn = new ServerConnection();
		HttpResponse response;
		try {
			String filename = "concerts/" + server_id + ".json";
			response = conn.execute(filename).get();
			
			JSONParser jParser = new JSONParser(response);
			JSONObject jsonConcert;
			jsonConcert = jParser.parse("");
			return jsonConcert;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String toString() {
		String result = "";
		result = "City: " + city + " Created At: " + created_at
				+ " Facebook Page: " + facebook_page + " Name: " + name
				+ " Start Time: " + start_time + " State: " + state
				+ " Twitter Tag: " + twitter_tag + " Updated At: " + updated_at
				+ " Zip: " + zip;
		return result;
	}

	public String city = "";
	public String created_at = "";
	public String facebook_page = "";
	public String name = "";
	public String start_time = "";
	public String state = "";
	public String twitter_tag = "";
	public String updated_at = "";
	public String zip = "";
	public int id;

	static final String GET_CONCERTS = "concerts.json";

}
