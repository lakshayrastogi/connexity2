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
		ITEM,
		MERCHANT
	}
	//Shopzilla Catalog API
	public static final String BASE_URL = "http://catalog.bizrate.com/services/catalog/v1/us/";
	public static final String API_KEY = "f94ab04178d1dea0821d5816dfb8af8d";
	public static final int PUBLISHER_ID = 608865;
	public static final int NUM_SEARCH_RESULTS = 24;
	public static final int NUM_RELATED_OFFERS = 12;
	//Twitter API
	public static final boolean USE_TWITTER = true;
	public static final String TWTR_API_KEY = "u6DPycDM6EgulZDl9665MvMWe";
	public static final String TWTR_API_SECRET = "LX86Q9ERBfeLTfyEJcvUti5kEWEYeB4rpbbZXw7tzltsSHJ9nO";
	public static final String TWTR_SEARCH_URL = "https://api.twitter.com/1.1/search/tweets.json";
	public static final String TWTR_EMBED_URL = "https://publish.twitter.com/oembed";
	public static final int MAX_TWEET_COUNT = 15;
	// JDBC driver name and database URL
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	// MAMP port 8889, if using terminal/linux version of mySQL, use 3306
	public static final String HOST_URL = "jdbc:mysql://localhost:8889/";
	public static final String DB_URL = "jdbc:mysql://localhost:8889/CONNEXITY";

	//  Database credentials
	public static final String USER = "root";
	public static final String PASS = "root";
}
