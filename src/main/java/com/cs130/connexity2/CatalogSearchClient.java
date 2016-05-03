package com.cs130.connexity2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cs130.connexity2.objects.SearchResult;

public class CatalogSearchClient {
	public CatalogSearchClient() {}
	static private JSONParser jsonParser = new JSONParser();
	//readUrl function from http://stackoverflow.com/questions/7467568/parsing-json-from-url
	//Used to read JSON from URL as string
	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 
	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}

	public List<SearchResult> getSearchResults(String urlString) {
		try {
			List<SearchResult> searchResults = new ArrayList<>();
			//convert url to json object
			JSONObject jsonObj = (JSONObject) jsonParser.parse(readUrl(urlString));
			//obtain json list of offers
			JSONArray jsonArr = (JSONArray) ((JSONObject) jsonObj.get("offers")).get("offer");
			for (int i = 0; i < jsonArr.size(); i++) {
				JSONObject jsonRes = (JSONObject) jsonArr.get(i);
				/*SearchResult constructor parses JSONObject and 
				 * sets member variables accordingly */
				searchResults.add(new SearchResult(jsonRes));
			}
			return searchResults;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
