package com.cs130.connexity2.util;

public class Globals {
	
	public static enum SearchType{
		PRODUCT,
		BRAND,
		ATTRIBUTES,
		TAXONOMY,
		MERCHANT_INFO,
		PRODUCT_REVIEW
	}
	public static enum TwitterSearchType {
		SEARCH_RESULTS,
		ITEM
	}
	//Shopzilla Catalog API
	public static final String BASE_URL = "http://catalog.bizrate.com/services/catalog/v1/us/";
	public static final String API_KEY = "f94ab04178d1dea0821d5816dfb8af8d";
	public static final int PUBLISHER_ID = 608865;
	//Twitter API
	public static final String TWTR_API_KEY = "u6DPycDM6EgulZDl9665MvMWe";
	public static final String TWTR_API_SECRET = "LX86Q9ERBfeLTfyEJcvUti5kEWEYeB4rpbbZXw7tzltsSHJ9nO";
	public static final String TWTR_SEARCH_URL = "https://api.twitter.com/1.1/search/tweets.json";
	public static final String TWTR_EMBED_URL = "https://publish.twitter.com/oembed";
}
