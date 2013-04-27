package edu.augustana.concertscoop.models;

import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import edu.augustana.concertscoop.util.JSONParser;
/** Models an instance of a concert. Provides an API to get all concerts from the server and CRUD opperations */
public class Concert {

	/** Constructor that builds a concert from an existing concert on the server 
	 * @param server_id The PK for the concert on the server
	 * */
	public Concert(int server_id) {
		ServerConnection conn = new ServerConnection();
		HttpResponse response;
		try {
			String filename = "concerts/" + server_id + ".json";
			response = conn.execute(filename).get();
			
			JSONParser jParser = new JSONParser(response);
			setFields(jParser.parse(""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Constructor that builds a concert from JSON data representing the concert 
	 * @param jConcert A JSON representation of the concert
	 * */
	public Concert(JSONObject jConcert){
		setFields(jConcert);
	}

	
	/** Connects to the web server and returns an ArrayList of all the Concerts objects on the server
	 * @return ArrayList<Concert> of all concerts on the server
	 */
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

	/**
	 * Connects to the web server and creates a new concert entry
	 * @return true if a new concert was successfully created on the server
	 */
	public boolean update() {
		return true;
	}
	
	/** Creates a string containing all the concert data fields
	 * @param jConcert JSON object whose keys are the same as the names of the member variables
	 * @return String representing the concert data
	 */
	public String toString() {
		String result = "";
		result = "City: " + city + " Created At: " + created_at
				+ " Facebook Page: " + facebook_page + " Name: " + name
				+ " Start Time: " + start_time + " State: " + state
				+ " Twitter Tag: " + twitter_tag + " Updated At: " + updated_at
				+ " Zip: " + zip;
		return result;
	}
	
	/** Helper method for the constructors, sets member variables values by matching the JSON object's keys to Concert fields 
	 * 
	 * @param jConcert JSON object whose keys are the same as the names of the member variables
	 */
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
	/** URL for getting a list of concerts */ 
	public static final String GET_CONCERTS = "concerts.json";

}
