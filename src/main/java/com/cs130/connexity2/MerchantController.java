package com.cs130.connexity2;

import java.io.IOException;
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

import com.cs130.connexity2.objects.Merchant;
import com.cs130.connexity2.objects.MerchantQuery;
import com.cs130.connexity2.objects.Offer;
import com.cs130.connexity2.objects.ProductQuery;
import com.cs130.connexity2.objects.SearchResult;
import com.cs130.connexity2.twitter.TwitterSearchClient;
import com.cs130.connexity2.util.Globals;

@Controller
public class MerchantController {

	@RequestMapping(value="/merchant",method=RequestMethod.GET)
	public String merchant(
			@RequestParam(value="merchantId",required=true) String merchantId,
			@RequestParam(value="merchantName",required=true) String merchantName,
			Model model
			){
		if (merchantId.isEmpty()) {
    		return "redirect:/main";
    	}
		CatalogSearchClient searchClient = new CatalogSearchClient();

		//Category query for related items
		FutureTask<Merchant> catalogSearchTask = new FutureTask<Merchant>(new Callable<Merchant>() {
			@Override
			public Merchant call() throws Exception {
				MerchantQuery newQuery = new MerchantQuery();
		    	newQuery.setQueryType(Globals.SearchType.MERCHANT_INFO);
		    	newQuery.setMerchantId(merchantId);
		    	return searchClient.getMerchantInfo(newQuery);
			}
		});
		
		//Twitter search query
		FutureTask<List<String>> twitterSearchTask = new FutureTask<List<String>>(new Callable<List<String>>() {
			@Override
			public List<String> call() throws Exception {
				TwitterSearchClient twitterSearchClient = new TwitterSearchClient(Globals.TwitterSearchType.SEARCH_RESULTS, 
																				  null);
				return twitterSearchClient.getHtmlSnippets(merchantName);
			}
		});
		ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(catalogSearchTask);
        executor.execute(twitterSearchTask);
        
        Merchant merchant = null;
        List<String> tweetHtmlSnippets = null;
        
        while (true) {
        	if(catalogSearchTask.isDone() && twitterSearchTask.isDone()){
                System.out.println("Both tasks completed");
                //shut down executor service
                executor.shutdown();
                break;
            }
            try {
            	merchant = catalogSearchTask.get();
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
        
  		model.addAttribute("merchant", merchant);
  		//test output
  		System.out.println("Related Offers ------------------------------------------");
  		System.out.println("Offer 1 \n ---------------------------");
		String res = 
				"Name: " + merchant.getName() + '\n' +
				"Url: " + merchant.getUrl() + '\n' +
				"Merchant Url: " + merchant.getMerchantUrl() + '\n' +
				"Merchant Id: " + merchant.getMerchantId() + '\n' +
				"Overall Satisfaction: " + merchant.getOverallSatisfaction();
		System.out.println(res);
		
		//Twitter
  		model.addAttribute("tweetHtmlSnippets", tweetHtmlSnippets);
		//test output
		if (tweetHtmlSnippets != null) {
			System.out.println("Twitter Search Result Html Snippets, " + tweetHtmlSnippets.size() + " results");
			for (int i = 0; i < tweetHtmlSnippets.size(); i++) {
				System.out.println(tweetHtmlSnippets.get(i));
			}
		}
		
		return "merchant";
	}
}
