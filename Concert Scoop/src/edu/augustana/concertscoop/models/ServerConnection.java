package edu.augustana.concertscoop.models;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import android.os.AsyncTask;

public class ServerConnection extends AsyncTask<String, Void, HttpResponse> {

	@Override
	protected HttpResponse doInBackground(String... params) {
		// Making HTTP request
		try {
			file = params[0];
			createConnection();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}


	private void createConnection() throws ClientProtocolException, IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();// defaultHttpClient
		HttpGet getRequest = new HttpGet(getURI());
		getRequest.addHeader("accept", "application/json");
		response = httpClient.execute(getRequest);
	}

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
