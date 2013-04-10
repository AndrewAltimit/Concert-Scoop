package edu.augustana.concertscoop.views;

import edu.augustana.concertscoop.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class LogInActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }
    
}
