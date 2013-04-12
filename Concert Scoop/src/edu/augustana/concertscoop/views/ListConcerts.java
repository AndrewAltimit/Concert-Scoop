package edu.augustana.concertscoop.views;

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
    	
    	if (concerts.isEmpty()) {
        	Toast.makeText(getApplicationContext(),
                    "Empty!",
                     Toast.LENGTH_LONG).show();   		
    	} else {
        	Toast.makeText(getApplicationContext(),
                    "Not Empty!",
                     Toast.LENGTH_LONG).show();
    	}

    	
    }
    
    
    private ArrayList<Concert> concerts;
    
}
