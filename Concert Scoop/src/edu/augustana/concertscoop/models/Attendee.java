package edu.augustana.concertscoop.models;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.augustana.concertscoop.util.JSONParser;


public class Attendee {

	public Attendee(JSONObject jAttendee) {
		try {
			email = jAttendee.get("user_id").toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public String getEmail(){
		return email;
	}
	
	public static ArrayList<Attendee> getAttendees(int id) {
		ServerRequest conn = new ServerRequest("concerts/" + Integer.toString(id) + "/attendees.json","GET");
		HttpResponse response;
		try {
			response = conn.execute().get();
			JSONParser jParser = new JSONParser(response);
			JSONArray jsonAttendees;
			jsonAttendees = jParser.parseAll("");
			ArrayList<Attendee> attendeeslist = new ArrayList<Attendee>();
			for (int i = 0; i < jsonAttendees.length(); i++) {
				attendeeslist.add(new Attendee((JSONObject) jsonAttendees.get(i)));
			}
			return attendeeslist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Attendee>();
	}
	
	private String email = "";
	
}
