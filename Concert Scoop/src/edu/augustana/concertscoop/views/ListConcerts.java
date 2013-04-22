package edu.augustana.concertscoop.views;

import java.util.ArrayList;
import edu.augustana.concertscoop.R;
import edu.augustana.concertscoop.models.Concert;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class ListConcerts extends ListActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listconcerts);
		concerts = Concert.getConcerts();
		populateListView();
	}

	private void populateListView() {

		ListView concertListView = (ListView) findViewById(android.R.id.list);
		ConcertAdapter customAdapter = new ConcertAdapter(this, concerts);
		concertListView.setAdapter(customAdapter);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	private ArrayList<Concert> concerts;

}
