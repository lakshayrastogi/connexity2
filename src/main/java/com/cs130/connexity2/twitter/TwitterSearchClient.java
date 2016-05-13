package com.cs130.connexity2.twitter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
		q.append("?q=");
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
	//Returns list of encoded tweet urls
	private List<String> getSearchResultUrls(String searchQuery) throws IOException {
		HttpsURLConnection connection = null;
		
		try {
			String requestUrl = Globals.TWTR_SEARCH_URL + formatSearchQuery(searchQuery);
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
			JSONObject jsonRes = (JSONObject)JSONValue.parse(twitterAuth.readResponse(connection));
			//Extract screen_name and id for each tweet to form tweet urls
			List<String> tweetUrls = new ArrayList<>();
			JSONArray jTweetsArr = (JSONArray) jsonRes.get("statuses");
			for (int i = 0; i < jTweetsArr.size(); i++) {
				JSONObject jTweet = (JSONObject) jTweetsArr.get(i);
				String tweetId = (String) jTweet.get("id_str");
				String userScreenName = (String) ((JSONObject) jTweet.get("user")).get("screen_name");
				String tweetUrl = String.format("https://twitter.com/%s/status/%s", userScreenName, tweetId);
				String encodedUrl;
				try {
					encodedUrl = URLEncoder.encode(tweetUrl, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					encodedUrl = tweetUrl;
					e.printStackTrace();
				}
				tweetUrls.add(encodedUrl);
			}
			return tweetUrls;
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
	
	public List<String> getHtmlSnippets(String searchQuery) throws IOException {
		List<String> searchResultUrls = getSearchResultUrls(searchQuery);
		List<String> htmlSnippets = new ArrayList<>();
		HttpsURLConnection connection = null;
		for (int i = 0; i < searchResultUrls.size(); i++) {
			try {
				//can specify maxwidth parameter in request URL if need be
				String requestUrl = Globals.TWTR_EMBED_URL + "?url=" + searchResultUrls.get(i);
				URL url = new URL(requestUrl); 
				connection = (HttpsURLConnection) url.openConnection();           
				connection.setDoOutput(true);
				connection.setDoInput(true); 
				connection.setRequestMethod("GET"); 
				connection.setRequestProperty("Host", "publish.twitter.com");
				connection.setRequestProperty("User-Agent", "Corradr");
				connection.setUseCaches(false);
				//System.out.println("Bearer Token: " + bearerToken);
				// Parse the JSON response into a JSON mapped object to fetch fields from.
				JSONObject jsonRes = (JSONObject)JSONValue.parse(twitterAuth.readResponse(connection));
				String htmlSnippet = (String) jsonRes.get("html");
				htmlSnippets.add(htmlSnippet);
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
		return htmlSnippets;

	}
}
