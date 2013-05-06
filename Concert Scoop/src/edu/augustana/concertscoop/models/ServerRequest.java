package edu.augustana.concertscoop.models;

import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

/** Creates a server connection that runs in the background and gets a reply */
public class ServerRequest extends AsyncTask<String, Void, HttpResponse> {

	public ServerRequest(String path, String body, String requestType){
		this.path = path;
		this.body = body;
		this.requestType = requestType.toUpperCase();
	}
	
	public ServerRequest(String path, String requestType){
		this.path = path;
		this.requestType = requestType.toUpperCase();
	}
	
	
	
	/**
	 * Constructor - Begins a connection with the server as an Asynchronous Task
	 * and gets a reply
	 * 
	 * @param String
	 *            [0] URI filename
	 *            [1] Post data
	 */
	@Override
	protected HttpResponse doInBackground(String... params) {
		// Making HTTP request

		if (requestType.equals("POST")){
			postConnection();
		} else if (requestType.equals("GET")) {
			createConnection();
		}
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
	
	private void postConnection() {
		    try {
		    	DefaultHttpClient httpClient = new DefaultHttpClient();
		        HttpPost postRequest = new HttpPost(getURI());
		        StringEntity params =new StringEntity("concert={\"city\":\"Rock Island\",\"created_at\":\"2013-05-06T18:35:13Z\",\"facebook_page\":null,\"id\":null,\"name\":\"Test Concert\",\"start_time\":\"2013-05-11T18:35:13Z\",\"state\":\"IL\",\"twitter_tag\":null,\"updated_at\":\"2013-05-06T18:35:13Z\",\"zip\":\"61201\"}");
		        postRequest.addHeader("content-type", "application/x-www-form-urlencoded");
		        postRequest.setEntity(params);
		        response = httpClient.execute(postRequest);
		        
		    }catch (Exception e) {
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
			return new URI("http://" + host + ":" + port + "/" + path);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	private int port = 3000;
	private String host = "10.0.2.2";
	private String path;
	private String body;
	private HttpResponse response;
	private String requestType;
}
