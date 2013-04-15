package edu.augustana.concertscoop.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.util.Log;

public class JSONParser extends AsyncTask<String, Void, JSONObject> {
	
	
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";

	// constructor
	public JSONParser() {

	}
	
	private void createConnection(String file) throws ClientProtocolException, IOException{
		// defaultHttpClient
		URI uri;
		try {
			uri = new URI("http://127.0.0.1:3000/" + file);
			httpClient = new DefaultHttpClient();
		    getRequest(uri);
		    
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
	}
	
	
	private void getRequest(URI uri) throws ClientProtocolException, IOException{
		HttpPost httpPost = new HttpPost(uri);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();
		is = httpEntity.getContent();
	}

	@Override
	protected JSONObject doInBackground(String... params) {
	    // Making HTTP request
		try {
			createConnection(params[0]);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	    
	    
	    /**
	    try {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(
	                is, "iso-8859-1"), 8);
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	        is.close();
	        json = sb.toString();
	    } catch (Exception e) {
	        Log.e("Buffer Error", "Error converting result " + e.toString());
	    }

	    // try parse the string to a JSON object
	    try {
	        jObj = new JSONObject(json);
	    } catch (JSONException e) {
	        Log.e("JSON Parser", "Error parsing data " + e.toString());
	    }

	    // return JSON String
	    result = jObj;
	    */
	    
	    return null;
	}
	
    protected JSONObject onPostExecute() {
        //do stuff
         return result;
     
   }
    
    
    /**
     * Original Code - was making a connection it seems, but there was a network thread error
     * 
public JSONObject getJSONFromUrl(String file) {
	
	    // Making HTTP request
	    try {
	        // defaultHttpClient
	    	URI uri;
			try {
				uri = new URI("http://127.0.0.1:3000/" + file);
				httpClient = new DefaultHttpClient();
		        HttpPost httpPost = new HttpPost(uri);
		        HttpResponse httpResponse = httpClient.execute(httpPost);
		        HttpEntity httpEntity = httpResponse.getEntity();
		        is = httpEntity.getContent();
		        
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    try {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(
	                is, "iso-8859-1"), 8);
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	        is.close();
	        json = sb.toString();
	    } catch (Exception e) {
	        Log.e("Buffer Error", "Error converting result " + e.toString());
	    }

	    // try parse the string to a JSON object
	    try {
	        jObj = new JSONObject(json);
	    } catch (JSONException e) {
	        Log.e("JSON Parser", "Error parsing data " + e.toString());
	    }

	    // return JSON String
	    return jObj;

	}
    
    */
    
    
    
    
	/**
	 * Possibly use this instead of AsycTask
	 * 
    Thread thread = new Thread(new Runnable(){
        @Override
        public void run() {
            try {
                //Your code goes here
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    thread.start(); 
    
    */
    
	private JSONObject result;
	private DefaultHttpClient httpClient;
}
