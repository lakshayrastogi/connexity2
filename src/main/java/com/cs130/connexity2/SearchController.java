package com.cs130.connexity2;

import org.neo4j.cypher.internal.compiler.v2_1.executionplan.builders.GlobalStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import com.cs130.connexity2.twitter.TwitterSearchClient;
import com.cs130.connexity2.util.Globals;
import com.cs130.connexity2.objects.ProductQuery;
import com.cs130.connexity2.objects.Query;
import com.cs130.connexity2.objects.SearchResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cs130.connexity2.objects.SearchResultJDBCTemplate;

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
    
    public boolean validKeyword(String keyword) {
    	return (keyword.trim().length() > 0);
    }
    
    @RequestMapping(value="/searchResults",method=RequestMethod.GET)
    public String searchResults(@RequestParam(value="keyword",required=true) String keyword, 
    							@RequestParam(value="name", required=false, defaultValue="false") boolean name,
    							@RequestParam(value="seller", required=false, defaultValue="false") boolean seller,
    							@RequestParam(value="rating", required=false, defaultValue="false") boolean rating,
    							@RequestParam(value="priceLH", required=false, defaultValue="false") boolean priceLH,
    							@RequestParam(value="priceHL", required=false, defaultValue="false") boolean priceHL,
    							Model model){
    	System.out.println(name);
    	if (!validKeyword(keyword)) {
    		return "redirect:/main";
    	}
    	model.addAttribute("keyword", keyword);
    	// TODO: Add more checks for valid input
    	
    	CatalogSearchClient searchClient = new CatalogSearchClient();
		
		FutureTask<List<SearchResult>> catalogSearchTask = new FutureTask<List<SearchResult>>(new Callable<List<SearchResult>>() {
			@Override
			public List<SearchResult> call() throws Exception {
				ProductQuery newQuery = new ProductQuery();
		    	newQuery.setQueryType(Globals.SearchType.PRODUCT);
		    	newQuery.setKeyword(keyword);
		    	newQuery.setResults(Globals.NUM_SEARCH_RESULTS);
				return searchClient.getSearchResults(newQuery);
			}
		});
		
		FutureTask<List<String>> twitterSearchTask = new FutureTask<List<String>>(new Callable<List<String>>() {
			@Override
			public List<String> call() throws Exception {
				TwitterSearchClient twitterSearchClient = new TwitterSearchClient(Globals.TwitterSearchType.SEARCH_RESULTS, null);
				return twitterSearchClient.getHtmlSnippets(keyword);
			}
		});
		ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(catalogSearchTask);
        executor.execute(twitterSearchTask);
        
        List<SearchResult> searchResults = null;
        List<String> tweetHtmlSnippets = null;
        while (true) {
        	if(catalogSearchTask.isDone() && twitterSearchTask.isDone()){
                System.out.println("Both tasks completed");
                //shut down executor service
                executor.shutdown();
                break;
            }
            try {
				searchResults = catalogSearchTask.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
            System.out.println("catalogSearchTask finished");
            try {
				tweetHtmlSnippets = twitterSearchTask.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
            System.out.println("twitterSearchTask finished");
        }
        /*
		SearchResultJDBCTemplate searchJT = new SearchResultJDBCTemplate();
		if (!searchResults.isEmpty()) {
			searchJT.deleteAllRows();
			for (SearchResult searchResult : searchResults) {
				searchJT.insertRecord(searchResult);
			}
		}
		*/
		
		
        final boolean refinedResults = (name || seller || rating || priceLH || priceHL);
        FutureTask<List<SearchResult>> sqlTask = new FutureTask<List<SearchResult>>(new Callable<List<SearchResult>>() {
			@Override
			public List<SearchResult> call() throws Exception {
				SearchResultJDBCTemplate searchJT = new SearchResultJDBCTemplate();
				List<SearchResult> searchResults = catalogSearchTask.get();
				String whereClause = "where 1 ";
				if (priceLH) {
					whereClause += "order by price asc ";
				} else if (priceHL) {
					whereClause += "order by price desc";
				}
				//refinedResults=false implies new keyword search --> replace data in sql database
				if (!searchResults.isEmpty() && !refinedResults) {
					searchJT.deleteAllRows();
					for (SearchResult searchResult : searchResults) {
						searchJT.insertRecord(searchResult);
					}
				}
				return searchJT.getSearchResults(whereClause);
			}
		});
        executor = Executors.newFixedThreadPool(1);
        executor.execute(sqlTask);
        List<SearchResult> sr = null;
        //if refinement specified, wait on sqlTask to complete and
        //sort results using sql data
        if (refinedResults) {
        	try {
    			sr = sqlTask.get();
    		} catch (InterruptedException | ExecutionException e) {
    			e.printStackTrace();
    		}
        	//TODO: refine based on refinement category
        	//...
        	model.addAttribute("searchResults", sr);
        }
        else {
        	model.addAttribute("searchResults", searchResults);
        }
		
		//model.addAttribute("searchResults", sr);
		//model.addAttribute("searchResults", searchResults);
		model.addAttribute("name", name);
		model.addAttribute("seller", seller);
		model.addAttribute("rating", rating);
		model.addAttribute("priceLH", priceLH);
		model.addAttribute("priceHL", priceHL);
		
		// test output
		for (int i = 0; i < searchResults.size(); i++) {
			System.out.println("Offer " + (i+1) + '\n' + "---------------------------");
			SearchResult searchRes = searchResults.get(i);
			String res = 
					"Title: " + searchRes.getTitle() + '\n' +
					"Brand: " + searchRes.getBrandName() + '\n' +
					"Description: " + searchRes.getDescription() + '\n' +
					"Price: $" + searchRes.getPrice() + '\n' +
					"Relevancy: " + searchRes.getRelevancy() + '\n' +
					"merchantId: " + searchRes.getMerchantId() + '\n' +
					"categoryId: " + searchRes.getCategoryId() + '\n' +
					"id: " + searchRes.getId() + '\n';
			System.out.println(res);
		}
		
		//Twitter
		model.addAttribute("tweetHtmlSnippets", tweetHtmlSnippets);
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
