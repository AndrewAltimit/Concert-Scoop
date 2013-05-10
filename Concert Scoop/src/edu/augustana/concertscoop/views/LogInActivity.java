package edu.augustana.concertscoop.views;

import edu.augustana.concertscoop.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class LogInActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        
        
        ImageButton b1 = (ImageButton)findViewById(R.id.twitterButton);
        b1.setOnClickListener(new OnClickListener()
		{
			//Attached to the twitter button
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListConcerts.class);
                startActivity(intent);
            }
		});
        
        
        ImageButton b2 = (ImageButton)findViewById(R.id.googleButton);
        b2.setOnClickListener(new OnClickListener()
		{
			//Attached to the google button
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListConcerts.class);
                startActivity(intent);
            }
		}); 
		
        ImageButton b3 = (ImageButton)findViewById(R.id.facebookButton);
        b3.setOnClickListener(new OnClickListener()
		{
			//Attached to the facebook button
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListConcerts.class);
                startActivity(intent);
            }
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }
    
}
