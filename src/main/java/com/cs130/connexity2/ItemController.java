package com.cs130.connexity2;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cs130.connexity2.objects.Offer;
import com.cs130.connexity2.objects.ProductQuery;
import com.cs130.connexity2.objects.SearchResult;
import com.cs130.connexity2.twitter.TwitterSearchClient;
import com.cs130.connexity2.util.Globals;

@Controller
public class ItemController {

	@RequestMapping(value="/item",method=RequestMethod.GET)
	public String item(@RequestParam(value="productId",required=true) String productId, 
					   @RequestParam(value="keyword",required=true) String keyword,
					   Model model){
		if (productId.isEmpty()) {
    		return "redirect:/main";
    	}
		CatalogSearchClient searchClient = new CatalogSearchClient();
		
		ProductQuery newQuery = new ProductQuery();
    	newQuery.setQueryType(Globals.SearchType.PRODUCT);
    	newQuery.setProductId(productId);
    	newQuery.setProductIdType("SZOID");
    	
		Offer offerResult = searchClient.getItemInfo(newQuery);
		model.addAttribute("offerResult", offerResult);
		//test output
		System.out.println("Item Offer \n ---------------------------");
		String res = 
				"Title: " + offerResult.getTitle() + '\n' +
				"Brand: " + offerResult.getBrandName() + '\n' +
				"Description: " + offerResult.getDescription() + '\n' +
				"Price: $" + offerResult.getPrice() + '\n' +
				"Relevancy: " + offerResult.getRelevancy() + '\n' +
				"merchantLogoUrl: " + offerResult.getMerchantLogoUrl() + '\n' +
				"merchantId: " + offerResult.getMerchantId() + '\n' +
				"categoryId: " + offerResult.getCategoryId() + '\n' +
				"id: " + offerResult.getId() + '\n';
		System.out.println(res);
		//Category query for related items
		FutureTask<List<SearchResult>> catalogSearchTask = new FutureTask<List<SearchResult>>(new Callable<List<SearchResult>>() {
			@Override
			public List<SearchResult> call() throws Exception {
				ProductQuery catQuery = new ProductQuery();
				catQuery.setQueryType(Globals.SearchType.PRODUCT);
				catQuery.setCategoryId(offerResult.getCategoryId());
				catQuery.setResults(Globals.NUM_RELATED_OFFERS);
				return searchClient.getSearchResults(catQuery);
			}
		});
		//Twitter search query
		FutureTask<List<String>> twitterSearchTask = new FutureTask<List<String>>(new Callable<List<String>>() {
			@Override
			public List<String> call() throws Exception {
				TwitterSearchClient twitterSearchClient = new TwitterSearchClient(Globals.TwitterSearchType.ITEM, 
																				  offerResult.getMerchantName());
				return twitterSearchClient.getHtmlSnippets(keyword);
			}
		});
		ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(catalogSearchTask);
        executor.execute(twitterSearchTask);
        
        List<SearchResult> categorySearchResults = null;
        List<String> tweetHtmlSnippets = null;
        
        while (true) {
        	if(catalogSearchTask.isDone() && twitterSearchTask.isDone()){
                System.out.println("Both tasks completed");
                //shut down executor service
                executor.shutdown();
                break;
            }
            try {
            	categorySearchResults = catalogSearchTask.get();
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
        
  		model.addAttribute("categorySearchResults", categorySearchResults);
  		//test output
  		System.out.println("Related Offers ------------------------------------------");
  		for (int i = 0; i < categorySearchResults.size(); i++) {
  			System.out.println("Offer " + (i+1) + '\n' + "---------------------------");
  			SearchResult searchRes = categorySearchResults.get(i);
  			String catRes = 
  					"Title: " + searchRes.getTitle() + '\n' +
  					"Brand: " + searchRes.getBrandName() + '\n' +
  					"Description: " + searchRes.getDescription() + '\n' +
  					"Price: $" + searchRes.getPrice() + '\n' +
  					"Relevancy: " + searchRes.getRelevancy() + '\n' +
  					"merchantId: " + searchRes.getMerchantId() + '\n' +
  					"categoryId: " + searchRes.getCategoryId() + '\n' +
  					"id: " + searchRes.getId() + '\n';
  			System.out.println(catRes);
  		}
		//Twitter
  		model.addAttribute("tweetHtmlSnippets", tweetHtmlSnippets);
		//test output
		if (tweetHtmlSnippets != null) {
			System.out.println("Twitter Search Result Html Snippets, " + tweetHtmlSnippets.size() + " results");
			for (int i = 0; i < tweetHtmlSnippets.size(); i++) {
				System.out.println(tweetHtmlSnippets.get(i));
			}
		}
		
		return "item";
	}
}
