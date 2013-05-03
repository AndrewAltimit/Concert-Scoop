package edu.augustana.concertscoop.views;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import edu.augustana.concertscoop.R;
import edu.augustana.concertscoop.models.Concert;

public class CreateConcert extends Activity implements OnClickListener{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_createconcert);
		
		submit = (Button) findViewById(R.id.SubmitConcert);
		submit.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Map<String, Object> concertFields = new HashMap<String, Object>();

		concertFields.put("name", ((EditText) findViewById(R.id.ConcertName)).getText().toString());
		concertFields.put("facebook_page", ((EditText) findViewById(R.id.ConcertFacebook)).getText().toString());
		concertFields.put("city", ((EditText) findViewById(R.id.ConcertCity)).getText().toString());
		concertFields.put("start_time", getDate() + " " + getTime());
		concertFields.put("state", ((EditText) findViewById(R.id.ConcertState)).getText().toString());
		concertFields.put("twitter_tag", ((EditText) findViewById(R.id.ConcertTwitter)).getText().toString());
		concertFields.put("zip", ((EditText) findViewById(R.id.ConcertZip)).getText().toString());
		Concert concert = new Concert(concertFields);
		if(concert.postToServer()){
			System.out.println(concert.toString());
			Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getApplicationContext(), concert.getError(), Toast.LENGTH_LONG).show();
		}
		
		
	}
	
	private String getTime(){
		TimePicker timeObj = (TimePicker) findViewById(R.id.ConcertTime);
		timeObj.clearFocus();
		String time = String.format("%02d", timeObj.getCurrentHour()) + ":" +  String.format("%02d", timeObj.getCurrentMinute()) + ":00";
		return time;
	}
	
	private String getDate(){
		DatePicker dateObj = (DatePicker) findViewById(R.id.ConcertDate);
		dateObj.clearFocus();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = sdf.format(dateObj);
		return dateString;
	}
	
	private Button submit;
}
