package com.cs130.connexity2.twitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import com.cs130.connexity2.util.Globals;
//referenced http://www.coderslexicon.com/demo-of-twitter-application-only-oauth-authentication-using-java/
//Singleton class, access using object using getInstance method
public class TwitterAuthenticator {
	public static TwitterAuthenticator instance = new TwitterAuthenticator();
	public static TwitterAuthenticator getInstance() { return instance; }
	private Twitter twitter = new TwitterTemplate(requestBearerToken());
	// Encodes the consumer key and secret to create the basic authorization key
	private String encodeKeys(String consumerKey, String consumerSecret) {
		try {
			String encodedConsumerKey = URLEncoder.encode(consumerKey, "UTF-8");
			String encodedConsumerSecret = URLEncoder.encode(consumerSecret, "UTF-8");
			
			String fullKey = encodedConsumerKey + ":" + encodedConsumerSecret;
			byte[] encodedBytes = Base64.encodeBase64(fullKey.getBytes());
			return new String(encodedBytes);  
		}
		catch (UnsupportedEncodingException e) {
			return new String();
		}
	}
	public Twitter getTwitter() {
		return this.twitter;
	}
	// Constructs the request for requesting a bearer token and returns that token as a string
	private String requestBearerToken()  {
		HttpsURLConnection connection = null;
		String encodedCredentials = encodeKeys(Globals.TWTR_API_KEY,Globals.TWTR_API_SECRET);
			
		try {
			URL url = new URL("https://api.twitter.com/oauth2/token"); 
			connection = (HttpsURLConnection) url.openConnection();           
			connection.setDoOutput(true);
			connection.setDoInput(true); 
			connection.setRequestMethod("POST"); 
			connection.setRequestProperty("Host", "api.twitter.com");
			connection.setRequestProperty("User-Agent", "Corradr");
			connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8"); 
			connection.setRequestProperty("Content-Length", "29");
			connection.setUseCaches(false);
				
			writeRequest(connection, "grant_type=client_credentials");
				
			// Parse the JSON response into a JSON mapped object to fetch fields from.
			JSONObject obj = (JSONObject)JSONValue.parse(readResponse(connection));
				
			if (obj != null) {
				String tokenType = (String)obj.get("token_type");
				String token = (String)obj.get("access_token");
			
				return ((tokenType.equals("bearer")) && (token != null)) ? token : "";
			}
			return new String();
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	// Writes a request to a connection
	private boolean writeRequest(HttpsURLConnection connection, String textBody) {
		try {
			BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
			wr.write(textBody);
			wr.flush();
			wr.close();
			return true;
		}
		catch (IOException e) { return false; }
	}
		
		
	// Reads a response for a given connection and returns it as a string.
	private String readResponse(HttpsURLConnection connection) {
		try {
			StringBuilder str = new StringBuilder();
				
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = "";
			while((line = br.readLine()) != null) {
				str.append(line + System.getProperty("line.separator"));
			}
			return str.toString();
		}
		catch (IOException e) { return new String(); }
	}
}
