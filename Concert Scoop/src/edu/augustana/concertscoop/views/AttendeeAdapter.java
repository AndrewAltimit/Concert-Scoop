package edu.augustana.concertscoop.views;

import java.util.ArrayList;
import edu.augustana.concertscoop.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**Custom Adapter Class for handling Attendee ListView*/
public class AttendeeAdapter extends BaseAdapter {

	  private LayoutInflater inflater;
	  private ArrayList<String> objects;

	   private class ViewHolder {
	      TextView textView1;
	   }

	   public AttendeeAdapter(Context context, ArrayList<String> objects) {
	      inflater = LayoutInflater.from(context);
	      this.objects = objects;
	   }

	   public int getCount() {
	      return objects.size();
	   }

	   public String getItem(int position) {
	      return objects.get(position);
	   }

	   public long getItemId(int position) {
	      return position;
	   }

	   public View getView(int position, View convertView, ViewGroup parent) {
	      ViewHolder holder = null;
	      if(convertView == null) {
	         holder = new ViewHolder();
	         convertView = inflater.inflate(R.layout.attendeelistview, null);
	         holder.textView1 = (TextView) convertView.findViewById(R.id.textView1);
	         convertView.setTag(holder);
	      } else {
	         holder = (ViewHolder) convertView.getTag();
	      }
	      
	      
	      holder.textView1.setText(objects.get(position));
	      
	      return convertView;
	   }
	}