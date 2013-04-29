package edu.augustana.concertscoop.views;

import java.util.ArrayList;
import edu.augustana.concertscoop.R;
import edu.augustana.concertscoop.models.Concert;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;


/**
 * An activity which lists concerts pulled from the server and provides
 * interaction with each concert object
 */
public class ListConcerts extends ListActivity {

	/** Constructor - Pulls concerts from the server and puts them in a listview */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listconcerts);
		concerts = Concert.getConcerts();
		populateListView();
	}


	/** Populates the listview with concert objects */
	private void populateListView() {
		ListView concertListView = (ListView) findViewById(android.R.id.list);
		ConcertAdapter customAdapter = new ConcertAdapter(this, concerts);
		concertListView.setAdapter(customAdapter);
	}

	/**
	 * OnClickLister for the listview - Starts the ConcertInfo activity and
	 * passes the concert ID of the concert clicked
	 */
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(getApplicationContext(), ConcertInfo.class);
		Bundle b = new Bundle();
		b.putInt("id", concerts.get(position).id); // The concert ID
		intent.putExtras(b); // Transferring ID number to new activity
		startActivity(intent);
		registerForContextMenu(v);

	}
	

	private ArrayList<Concert> concerts;

}
