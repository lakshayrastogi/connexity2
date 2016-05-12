package com.cs130.connexity2.twitter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.cs130.connexity2.util.Globals;

public class TwitterSearchClient {
	private String bearerToken;
	private TwitterAuthenticator twitterAuth = TwitterAuthenticator.getInstance();
	public TwitterSearchClient() {
		try {
			bearerToken = twitterAuth.requestBearerToken();
		} catch (IOException e) {
			bearerToken = "";
			e.printStackTrace();
		}
	}
	private String formatSearchQuery(String query) {
		StringBuilder q = new StringBuilder();
		q.append("tweets.json?q=");
		String formattedQuery;
		try {
			formattedQuery = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			formattedQuery = query;
			e.printStackTrace();
		}
		q.append(formattedQuery);
		return q.toString();
	}
	public JSONObject getSearchResults(String searchQuery) throws IOException {
		HttpsURLConnection connection = null;
		
		try {
			String requestUrl = Globals.TWTR_BASE_URL + formatSearchQuery(searchQuery);
			URL url = new URL(requestUrl); 
			connection = (HttpsURLConnection) url.openConnection();           
			connection.setDoOutput(true);
			connection.setDoInput(true); 
			connection.setRequestMethod("GET"); 
			connection.setRequestProperty("Host", "api.twitter.com");
			connection.setRequestProperty("User-Agent", "Corradr");
			connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
			connection.setUseCaches(false);
			//System.out.println("Bearer Token: " + bearerToken);
			// Parse the JSON response into a JSON mapped object to fetch fields from.
			JSONObject jsonSearchResults = (JSONObject)JSONValue.parse(twitterAuth.readResponse(connection));
			return jsonSearchResults;
		}
		catch (MalformedURLException e) {
			throw new IOException("Invalid endpoint URL specified.", e);
		}
		finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
