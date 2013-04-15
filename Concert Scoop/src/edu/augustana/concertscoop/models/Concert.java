package edu.augustana.concertscoop.models;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Concert {

	public void Concert(int server_id) {
		// Make a connection with the server

		// pull data from server about this concert based on the id number
		// And put the data into an arraylist
		ArrayList<String> temp = new ArrayList<String>();
		// Populate fields with the pulled data
		Concert(temp);

	}

	public void Concert(ArrayList<String> properties) {
		// title = properties(0)
		// date = properties(1)
		// ...
	}

	// Return the list of concerts
	public static ArrayList<Concert> getConcerts() {
		
		//Pseudocode
		// http request to server for all concerts (or subset)
		// get back json data
		// parse json into concert objects
		// concert t1 = new Concert(Properties Array);
		// add all concert objects into arraylist

		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		jParser.execute(file);
		JSONObject json;
		
		
		try {
			json = jParser.get();
			//DEBUGGING Show contents of JSON Data
			System.out.print(json.toString());

			//Go through all the elements in the JSON String
			try {
			    // Getting Array of Concerts
				JSONArray concerts;
				concerts = json.getJSONArray("TAG_CONCERTS");

			    // looping through All Concerts
			    for(int i = 0; i < concerts.length(); i++){
			        JSONObject c = concerts.getJSONObject(i);
			        
			        //Debugging
			        System.out.print(c.toString());
			        
			        
			        // Storing each json item in variable
			        //String title = c.getString("Title");

			    }
			} catch (JSONException e) {
			    e.printStackTrace();
			}
			
			
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		//Debugging Purposes - Return empty array
		ArrayList<Concert> temp = new ArrayList<Concert>();
		return temp;
	}

	// Make a new concert entry
	public boolean update() {
		return true;
	}

	private String title;
	static String file = "/concerts.json";

}
