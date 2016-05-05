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
import org.neo4j.cypher.internal.compiler.v2_0.executionplan.builders.GlobalStrategy;

import com.cs130.connexity2.objects.SearchResult;
import com.cs130.connexity2.util.Globals;

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
	
	private SearchResult convertToSearchResult(JSONObject jsonRes) throws NullPointerException {
		SearchResult res = new SearchResult();
		try {
			res.setMerchantId(((Long) jsonRes.get("merchantId")).intValue());
		} catch (Exception e) {res.setMerchantId(-1); e.printStackTrace();}
		try {
			res.setCategoryId(((Long) jsonRes.get("categoryId")).intValue());
		} catch (Exception e) {res.setCategoryId(-1); e.printStackTrace();}
		try {
			res.setId(((Long) jsonRes.get("id")).intValue());
		} catch (Exception e) {res.setId(-1); e.printStackTrace();}
		try {
			res.setTitle((String) jsonRes.get("title"));
		} catch (Exception e) {res.setTitle(null); e.printStackTrace();}
		try {
			res.setBrandName((String) ((JSONObject) jsonRes.get("brand")).get("name"));
		} catch (Exception e) {res.setBrandName(null); e.printStackTrace();}
		try {
			res.setDescription((String) jsonRes.get("description"));
		} catch (Exception e) {res.setDescription(null); e.printStackTrace();}
		try {
			res.setUrl((String) ((JSONObject) jsonRes.get("url")).get("value"));
		} catch (Exception e) {res.setUrl(null); e.printStackTrace();}
		try {
			List<String> images = new ArrayList<>();
			JSONArray jsonArr = (JSONArray) ((JSONObject) jsonRes.get("images")).get("image");
			for (int i = 0; i < jsonArr.size(); i++) {
				String imageUrl = (String) ((JSONObject) jsonArr.get(i)).get("value");
				images.add(imageUrl);
			}
			res.setImages(images);
		} catch (Exception e) {res.setImages(null); e.printStackTrace();}
		try {
			res.setSku((String) jsonRes.get("sku"));
		} catch (Exception e) {res.setSku(null); e.printStackTrace();}
		try {
			res.setDetailUrl((String) ((JSONObject) jsonRes.get("detailUrl")).get("value"));
		} catch (Exception e) {res.setDetailUrl(null); e.printStackTrace();}
		try {
			res.setPrice((((Long)((JSONObject) jsonRes.get("price")).get("integral")).doubleValue()/100.0));
		} catch (Exception e) {res.setPrice(-1); e.printStackTrace();}
		try {
			res.setOriginalPrice((((Long)((JSONObject) jsonRes.get("originalPrice")).get("integral")).doubleValue())/100.0);
		} catch (Exception e) {res.setOriginalPrice(-1); e.printStackTrace();}
		try {
			res.setMarkdownPercent((Double) jsonRes.get("markdownPercent"));
		} catch (Exception e) {res.setMarkdownPercent(-1); e.printStackTrace();}
		try {
			res.setBidded ((boolean) jsonRes.get("bidded"));
		} catch (Exception e) {res.setBidded(false); e.printStackTrace();}
		try {
			res.setMerchantName((String) jsonRes.get("merchantName"));
		} catch (Exception e) {res.setMerchantName(null); e.printStackTrace();}
		try {
			res.setMerchantCertified((boolean) (((JSONObject) jsonRes.get("merchantCertification")).get("certified")));
		} catch (Exception e) {res.setMerchantCertified(false); e.printStackTrace();}
		try {
			res.setMerchantLogoUrl((String) jsonRes.get("merchantLogoUrl"));
		} catch (Exception e) {res.setMerchantLogoUrl(null); e.printStackTrace();}
		try {
			res.setCondition((String) jsonRes.get("condition"));
		} catch (Exception e) {res.setCondition(null); e.printStackTrace();}
		try {
			res.setRelevancy((Double) jsonRes.get("relevancy"));
		} catch (Exception e) {res.setRelevancy(-1); e.printStackTrace();}
		return res;
	}

	public List<SearchResult> getSearchResults(String urlString, Globals.SearchType searchType) {
		try {
			//publisherId=608865&keyword=shoes&results=20&resultsOffers=10&format=json&offersOnly=true
			List<SearchResult> searchResults = new ArrayList<>();
			String newUrl = Globals.BASE_URL;
			switch(searchType){
				case PRODUCT:
					newUrl+="product?";
					break;
				case BRAND:
					newUrl+="brand?";
					break;
				case ATTRIBUTES:
					newUrl+="attributes?";
					break;
				case TAXONOMY:
					newUrl+="taxonomy?";
					break;
				case MERCHANT_INFO:
					newUrl+="merchantinfo?";
					break;
				case PRODUCT_REVIEW:
					newUrl+="productReviews?";
					break;
				default: break;
			}
			newUrl+="apiKey=" + Globals.API_KEY + "&publisherId=" + Globals.PUBLISHER_ID;
			System.out.println(newUrl);
			//convert url to json object
			JSONObject jsonObj = (JSONObject) jsonParser.parse(readUrl(urlString));
			//obtain json list of offers
			JSONArray jsonArr = (JSONArray) ((JSONObject) jsonObj.get("offers")).get("offer");
			for (int i = 0; i < jsonArr.size(); i++) {
				JSONObject jsonRes = (JSONObject) jsonArr.get(i);
				/*SearchResult constructor parses JSONObject and 
				 * sets member variables accordingly */
				searchResults.add(convertToSearchResult(jsonRes));
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
