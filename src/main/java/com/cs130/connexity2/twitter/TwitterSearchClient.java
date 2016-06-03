package com.cs130.connexity2.twitter;

import java.util.ArrayList;
import java.util.List;

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
			andWords = new String[] {"just bought", "-shipping", "-sale", "-sell", "-deals"};
		}
		else if (searchType == Globals.TwitterSearchType.ITEM) {
			andWords = new String[] {merchantName};
		}
		else if (searchType == Globals.TwitterSearchType.MERCHANT) {
			andWords = new String[] {"review"};
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
