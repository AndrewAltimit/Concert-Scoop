package edu.augustana.concertscoop.views;

import edu.augustana.concertscoop.R;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class NavigationBar extends Fragment {
	
	

	/*Android calls onCreateView for each fragment in an Activity XML file.  It expects that the method will return a view.  
	 * Even though our fragment doesn't add any a view to the UI, we need to return a view to prevent Android from throwing exceptions 
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = new View(getActivity());
		return v;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	/** Callback for the ActionBar */
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.main, menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		System.out.println(item.toString());
		return true;
	}

}
