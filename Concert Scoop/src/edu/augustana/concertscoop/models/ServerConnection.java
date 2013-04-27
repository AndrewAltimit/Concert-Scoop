package edu.augustana.concertscoop.models;

import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

/** Creates a server connection that runs in the background and gets a reply */
public class ServerConnection extends AsyncTask<String, Void, HttpResponse> {

	/**
	 * Constructor - Begins a connection with the server as an Asynchronous Task
	 * and gets a reply
	 * 
	 * @param String
	 *            [0] filename
	 */
	@Override
	protected HttpResponse doInBackground(String... params) {
		// Making HTTP request
		file = params[0];
		createConnection();
		return response;
	}

	/**
	 * Helper method for the constructor - Saves the reply from the server
	 */
	private void createConnection() {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();// defaultHttpClient
			HttpGet getRequest = new HttpGet(getURI());
			getRequest.addHeader("accept", "application/json");
			response = httpClient.execute(getRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Builds a URI for connection to the server
	 * 
	 * @return URI The address for the server connection
	 */
	private URI getURI() {
		try {
			return new URI("http://" + host + ":" + port + "/" + file);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	private int port = 3000;
	private String host = "10.0.2.2";
	private String file;
	private HttpResponse response;
}
