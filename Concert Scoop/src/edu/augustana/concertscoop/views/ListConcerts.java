package edu.augustana.concertscoop.views;

import java.lang.reflect.Array;
import java.util.ArrayList;
import edu.augustana.concertscoop.R;
import edu.augustana.concertscoop.models.Concert;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class ListConcerts extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listconcerts);
		concerts = Concert.getConcerts();
		populateListView(concerts);
	}
	
	private void populateListView(ArrayList<Concert> concerts){
		
	}

	private String[] ConcertsForListView(){
		//How do you want the concert formatted??
		return new String[1];
	}
	private ArrayList<Concert> concerts;

}
