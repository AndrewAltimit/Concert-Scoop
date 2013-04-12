package edu.augustana.concertscoop.models;

import java.util.ArrayList;

public class Concert {

	public void Concert(int server_id){
		//Make a connection with the server
		
		
		//pull data from server about this concert based on the id number
		//And put the data into an arraylist
		ArrayList<String> temp = new ArrayList<String>();
		//Populate fields with the pulled data
		Concert(temp);
		
	}
	public void Concert(ArrayList<String> properties){
		//title = properties(0)
		//date = properties(1)
		//...
	}
	
	//Return the list of concerts
	public static ArrayList<Concert> getConcerts() {
		//http request to server for all concerts (or subset)
		//get back json data
		//parse json into concert objects
			//concert t1 = new Concert(Properties Array);
		//add all concert objects into arraylist
		
		ArrayList<Concert> temp = new ArrayList<Concert>();
		return  temp; 
	}
	
	//Make a new concert entry
	public boolean  update(){
		return true;
	}
	
	private String title;
	
}
