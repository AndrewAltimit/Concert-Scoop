package edu.augustana.concertscoop.views;

import java.util.ArrayList;
import edu.augustana.concertscoop.R;
import edu.augustana.concertscoop.models.Concert;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ConcertAdapter extends BaseAdapter {

	  private LayoutInflater inflater;
	  private ArrayList<Concert> objects;

	   private class ViewHolder {
	      TextView textView1;
	      TextView textView2;
	      TextView textView3;
	      TextView textView4;
	      TextView textView5;
	   }

	   public ConcertAdapter(Context context, ArrayList<Concert> objects) {
	      inflater = LayoutInflater.from(context);
	      this.objects = objects;
	   }

	   public int getCount() {
	      return objects.size();
	   }

	   public Concert getItem(int position) {
	      return objects.get(position);
	   }

	   public long getItemId(int position) {
	      return position;
	   }

	   public View getView(int position, View convertView, ViewGroup parent) {
	      ViewHolder holder = null;
	      if(convertView == null) {
	         holder = new ViewHolder();
	         convertView = inflater.inflate(R.layout.concertlistview, null);
	         holder.textView1 = (TextView) convertView.findViewById(R.id.textView1);
	         holder.textView2 = (TextView) convertView.findViewById(R.id.textView2);
	         holder.textView3 = (TextView) convertView.findViewById(R.id.textView3);
	         holder.textView4 = (TextView) convertView.findViewById(R.id.textView4);
	         holder.textView5 = (TextView) convertView.findViewById(R.id.textView5);
	         convertView.setTag(holder);
	      } else {
	         holder = (ViewHolder) convertView.getTag();
	      }
	      holder.textView1.setText(objects.get(position).name);
	      holder.textView2.setText("City: " + objects.get(position).city);
	      
	      //Parse out day and time from start_time
	      String[] date = objects.get(position).start_time.split("T");
	      holder.textView3.setText("Day: " +date[0]);
	      holder.textView4.setText("Time: " + date[1].replace("Z"," UTC"));
	      
	      holder.textView5.setText("State: " + objects.get(position).state + ", " + objects.get(position).zip);
	      
	      return convertView;
	   }
	}