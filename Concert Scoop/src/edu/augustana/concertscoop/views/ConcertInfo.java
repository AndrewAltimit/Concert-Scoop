package edu.augustana.concertscoop.views;

import java.util.ArrayList;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import edu.augustana.concertscoop.R;
import edu.augustana.concertscoop.models.Concert;

public class ConcertInfo extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_concertinfo);
		Bundle b = getIntent().getExtras();
		concertid = b.getInt("id");
		currentConcert = new Concert(concertid);
		populateDataFields();
		populateListView();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return true;
	}
	
	private void populateDataFields() {
		
		TextView header = (TextView) findViewById(R.id.header);
		header.setText(currentConcert.name);
		
		TextView date = (TextView) findViewById(R.id.Date);
		date.setText("Date: " + currentConcert.start_time);
		
		TextView city = (TextView) findViewById(R.id.City);
		city.setText("City: " + currentConcert.city);
		
		TextView state = (TextView) findViewById(R.id.State);
		state.setText("State: " + currentConcert.state);
		
		//If No Facebook Page Specified, Leave Data Field Blank
		TextView facebook = (TextView) findViewById(R.id.Facebook);
		if (currentConcert.facebook_page.equals("")){
			facebook.setText("");
		} else {
			facebook.setText("Facebook: " + currentConcert.facebook_page);
		}
		
		TextView attendees = (TextView) findViewById(R.id.Attendees);
		attendees.setText("Attendees");
	}
	
	private void populateListView() {
		//Populate ListView with Attendees
		ListView concertListView = (ListView) findViewById(android.R.id.list);
		//Example Data
		ArrayList<String> attendeeNames = new ArrayList<String>();
		attendeeNames.add("Joe Smith");
		attendeeNames.add("John Adams");
		attendeeNames.add("Marquesha Williams");
		
		AttendeeAdapter customAdapter = new AttendeeAdapter(this, attendeeNames);
		concertListView.setAdapter(customAdapter);
		
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		//Attendee Clicked. Start IM Activity

	}
	
	
	private int concertid;
	private Concert currentConcert;

}
