package edu.augustana.concertscoop.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import edu.augustana.concertscoop.util.JSONParser;

/**
 * Models an instance of a concert. Provides an API to get all concerts from the
 * server and CRUD operations
 */
public class Concert {

	/**
	 * Constructor that builds a concert from an existing concert on the server
	 * 
	 * @param server_id
	 *            The PK for the concert on the server
	 * */
	public Concert(int server_id) {
		ServerRequest conn = new ServerRequest("concerts/" + server_id + ".json", "GET");
		HttpResponse response;
		try {
			response = conn.execute().get();

			JSONParser jParser = new JSONParser(response);
			setFields(jParser.parse(""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor that builds a concert from JSON data representing the concert
	 * 
	 * @param jConcert
	 *            A JSON representation of the concert
	 * */
	public Concert(JSONObject jConcert) {
		setFields(jConcert);
	}

	public Concert(Map<?, ?> concert) {
		JSONObject jConcert = new JSONObject(concert);
		setFields(jConcert);
	}

	/**
	 * Connects to the web server and returns an ArrayList of all the Concerts
	 * objects on the server
	 * 
	 * @return ArrayList<Concert> of all concerts on the server
	 */
	public static ArrayList<Concert> getConcerts() {
		ServerRequest conn = new ServerRequest(GET_CONCERTS,"GET");
		HttpResponse response;
		try {
			response = conn.execute().get();
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
	 * 
	 * @return true if a new concert was successfully created on the server
	 */
	public boolean update() {
		return true;
	}

	/**
	 * Creates a string containing all the concert data fields
	 * 
	 * @param jConcert
	 *            JSON object whose keys are the same as the names of the member
	 *            variables
	 * @return String representing the concert data
	 */
	public String toString() {
		String result = "";
		result = "City: " + city + " Facebook Page: " + facebook_page
				+ " Name: " + name + " Start Time: " + start_date_time + " State: "
				+ state + " Twitter Tag: " + twitter_tag + " Zip: " + zip;
		return result;
	}

	
	public boolean postToServer(){
		//Validate Fields
		
		//If Validation is Good (True) then post to server using server connection
		return validateRequiredFields();
	}
	
	/*Getters*/
	public String getName(){
		return name;
	}
	public String getCity(){
		return city;
	}
	public String getFacebookPage(){
		return facebook_page;
	}
	public String getTwitterTag(){
		return twitter_tag;
	}
	public String getStartTime(){
		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("hh:mm a");
		return df.format((start_date_time.getTime()));
	}
	public String getStartDate(){
		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("MM/dd/yyyy");
		return df.format(start_date_time.getTime());
	}
	public String getState(){
		return state;
	}
	public String getZip(){
		return city;
	}
	public int getId(){
		return id;
	}
	public String getError(){
		return error;
	}
	
	public JSONObject getJSON(){
		return jObject;
	}
	
	/**
	 * Helper method for the constructors, sets member variables values by
	 * matching the JSON object's keys to Concert fields
	 * 
	 * @param jConcert
	 *            JSON object whose keys are the same as the names of the member
	 *            variables
	 */
	private void setFields(JSONObject jConcert) {
		try {
			city = (String) jConcert.get("city");
			facebook_page = (String) jConcert.get("facebook_page");
			name = (String) jConcert.get("name");
			start_date_time = parseStringToCal((String) jConcert.get("start_time"));
			state = (String) jConcert.get("state");
			twitter_tag = (String) jConcert.get("twitter_tag");
			zip = (String) jConcert.get("zip");
			jObject = jConcert;
			if (jConcert.get("id") != null){
				id = (Integer) jConcert.get("id");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private boolean validateRequiredFields(){
		error = "";
		if (name.equals("")){
			error = error + "Must enter a concert name\n";
		}
		if (city.equals("")) {
			error = error + "Must enter a concert city\n";
		}
		if (state.equals("")) {
			error = error + "Must enter a concert state\n";
		}
		
		return error.equals("");
	}
	
	private Calendar parseStringToCal(String cal){
		Calendar c = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		try {
			c.setTime(sdf.parse(cal));
			return c;
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return null;
	}
	

	
	private String city = "";
	private String facebook_page = "";
	private String name = "";
	private Calendar start_date_time;
	private String state = "";
	private String twitter_tag = "";
	private String zip = "";
	private String error = "";
	private JSONObject jObject;
	private int id;
	/** URL for getting a list of concerts */
	public static final String GET_CONCERTS = "concerts.json";

}
