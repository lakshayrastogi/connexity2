package com.cs130.connexity2;

import org.neo4j.cypher.internal.compiler.v2_1.executionplan.builders.GlobalStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

import com.cs130.connexity2.twitter.TwitterSearchClient;
import com.cs130.connexity2.util.Globals;
import com.cs130.connexity2.objects.ProductQuery;
import com.cs130.connexity2.objects.Query;
import com.cs130.connexity2.objects.SearchResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class SearchController {
	
	@RequestMapping("/")
    public String index() {
        return "redirect:/main";
    }
    
    @RequestMapping(value="/main", method=RequestMethod.GET)
    public String main(Model model){
    	//model.addAttribute("productQuery", new ProductQuery());
    	return "main";
    } 
    
    @RequestMapping(value="/searchResults",method=RequestMethod.GET)
    public String searchResults(@RequestParam(value="keyword",required=true) String keyword, Model model){
    	System.out.println(keyword);
    	if (keyword.isEmpty()) {
    		return "redirect:/main";
    	}
    	// TODO: Add more checks for valid input
    	ProductQuery newQuery = new ProductQuery();
    	newQuery.setQueryType(Globals.SearchType.PRODUCT);
    	newQuery.setKeyword(keyword);
		CatalogSearchClient searchClient = new CatalogSearchClient();
		List<SearchResult> searchResults = searchClient.getSearchResults(newQuery);
		model.addAttribute("searchResults", searchResults);
		//Twitter
		TwitterSearchClient twitterSearchClient = new TwitterSearchClient();
		List<String> tweetHtmlSnippets = null;
		try {
			tweetHtmlSnippets = twitterSearchClient.getHtmlSnippets(keyword);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("tweetHtmlSnippets", tweetHtmlSnippets);
		
		// test output
		for (int i = 0; i < searchResults.size(); i++) {
			System.out.println("Offer " + (i+1) + '\n' + "---------------------------");
			SearchResult searchRes = searchResults.get(i);
			String res = 
					"Title: " + searchRes.getTitle() + '\n' +
					"Brand: " + searchRes.getBrandName() + '\n' +
					"Description: " + searchRes.getDescription() + '\n' +
					"Price: $" + searchRes.getPrice() + '\n' +
					"Merchant Certified: " + searchRes.isMerchantCertified() + '\n' +
					"merchantId: " + searchRes.getMerchantId() + '\n' +
					"id: " + searchRes.getId() + '\n';
			System.out.println(res);
		}
		if (tweetHtmlSnippets != null) {
			System.out.println("Twitter Search Result Html Snippets, " + tweetHtmlSnippets.size() + " results");
			for (int i = 0; i < tweetHtmlSnippets.size(); i++) {
				System.out.println(tweetHtmlSnippets.get(i));
			}
		}
    	return "searchResults";
    	
    }
}

/*
 * Another way to get the user information that is placed directly into the object
 * 
@RequestMapping(value="/main", method=RequestMethod.GET)
public String main(Model model){
	model.addAttribute("productQuery", new ProductQuery());
	return "main";
} 
@RequestMapping(value="/searchResults",method=RequestMethod.GET)
public String searchResults(@ModelAttribute ProductQuery query, Model model){
	System.out.println(query.getKeyword());
	return "searchResults";
}
*/
