package edu.augustana.concertscoop.models;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

/** Creates a server connection that runs in the background and gets a reply */
public class ServerRequest extends AsyncTask<String, Void, String> {

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
	 * Begins a connection with the server as an Asynchronous Task
	 * and gets a reply
	 */
	@Override
	protected String doInBackground(String... params) {
		// Making HTTP request
		if (requestType.equals("POST")){
			postConnection();
		} else if (requestType.equals("GET")) {
			createConnection();
		}
		
		return getStringFromResponse();
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
		        //StringEntity params =new StringEntity("{\"city\":\"Rock Island\",\"facebook_page\":null,\"name\":\"Test Concert\",\"start_time\":\"2013-05-11T18:35:13Z\",\"state\":\"IL\",\"twitter_tag\":null,\"zip\":\"61201\"}");
		        StringEntity params =new StringEntity(body);
		        postRequest.addHeader("content-type", "application/json");
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


	/**
	 * Takes the HttpResponse and converts it to a string that can be used to
	 * build JSONArrays or JSONObjects
	 */
	private String getStringFromResponse() {
		String jsonString = "";
		HttpEntity httpEntity = response.getEntity();
		try {
			InputStream is = httpEntity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			jsonString = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}
		return jsonString;
	}

	private int port = 3000;
	private String host = "173.21.202.52";
	//private String host = "10.0.2.2";
	//private String host = "143.226.54.158";
	private String path;
	private String body;
	private HttpResponse response;
	private String requestType;
}
