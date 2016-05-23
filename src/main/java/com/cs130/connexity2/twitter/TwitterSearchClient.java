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
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.OEmbedTweet;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;

import com.cs130.connexity2.util.Globals;

public class TwitterSearchClient {
	private Twitter twitter;
	private Globals.TwitterSearchType searchType;
	private String[] andWords;
	private String[] orWords;
	public TwitterSearchClient(Globals.TwitterSearchType searchType, String merchantName) {
		this.twitter = TwitterAuthenticator.getInstance().getTwitter();
		this.searchType = searchType;
		orWords = new String[]
				{"buy", "bought", "purchase", "purchased", "shopping", "shop", "new", "best"};
		if (searchType == Globals.TwitterSearchType.SEARCH_RESULTS) {
			andWords = new String[] {"review"};
		}
		else if (searchType == Globals.TwitterSearchType.ITEM) {
			andWords = new String[] {merchantName};
		}
	}
	private SearchParameters formatSearchQuery(String keyword) {
		StringBuilder query = new StringBuilder();
		query.append('\"' + keyword + '\"' + ' ');
		for (String word : andWords) {
			query.append(word + ' ');
		}
		for (String word : orWords) {
			query.append(word + " OR ");
		}
		SearchParameters searchParams = new SearchParameters(query.toString());
		searchParams.lang("en");
		searchParams.includeEntities(false);
		searchParams.count(Globals.MAX_TWEET_COUNT);
		return searchParams;
	}

	public List<String> getHtmlSnippets(String keyword) {
		List<String> htmlSnippets = new ArrayList<>();
		SearchResults searchResults = twitter.searchOperations().search(formatSearchQuery(keyword));
		for (Tweet tweet : searchResults.getTweets()) {
			OEmbedTweet eTweet = twitter.timelineOperations().getStatusOEmbed(Long.toString(tweet.getId()));
			htmlSnippets.add(eTweet.getHtml());
		}
		return htmlSnippets;
	}
}
