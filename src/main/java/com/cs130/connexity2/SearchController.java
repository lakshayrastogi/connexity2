package com.cs130.connexity2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.Map;

import com.cs130.connexity2.twitter.TwitterSearchClient;
import com.cs130.connexity2.util.Globals;
import com.cs130.connexity2.objects.ProductQuery;
import com.cs130.connexity2.objects.SearchResult;
import com.google.gson.Gson;

import com.cs130.connexity2.objects.SearchResultJDBCTemplate;

@Controller
public class SearchController {
	
	@RequestMapping("/")
    public String index() {
        return "redirect:/main";
    }
    
    @RequestMapping(value="/main", method=RequestMethod.GET)
    public String main(Model model){
    	return "main";
    } 
    
    public boolean validKeyword(String keyword) {
    	return (keyword.trim().length() > 0);
    }
    
    @RequestMapping(value="/searchResults",method=RequestMethod.GET)
    public String searchResults(@RequestParam(value="keyword",required=true) String keyword, 
    							@RequestParam(value="seller", required=false, defaultValue="false") boolean seller,
    							@RequestParam(value="priceLH", required=false, defaultValue="false") boolean priceLH,
    							@RequestParam(value="priceHL", required=false, defaultValue="false") boolean priceHL,
    							@RequestParam(value="mName", required=false, defaultValue = "") String [] mName,
    							@RequestParam(value="page", required=false, defaultValue = "1") String page,
    							Model model){
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
		    	int pageNum = Integer.parseInt(page);
		    	newQuery.setStart(pageNum);
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
		
        final boolean refinedResults = (seller || priceLH || priceHL);
        FutureTask<List<SearchResult>> sqlTask = new FutureTask<List<SearchResult>>(new Callable<List<SearchResult>>() {
			@Override
			public List<SearchResult> call() throws Exception {
				SearchResultJDBCTemplate searchJT = new SearchResultJDBCTemplate();
				List<SearchResult> searchResults = catalogSearchTask.get();
				String whereClause = "where 1 ";
				String columns = "* ";
				if (seller) {
					// Revert merchant names to have spaces instead of hyphens
			    	if (mName != null) {
			    		if (mName.length > 0) {
			    			whereClause += "and (merchantName in ( ";
			    		}
			    	for (int i = 0; i < mName.length; i++) {
						String merchant = mName[i].replace("-", " ");
						System.out.println(mName[i]);
						if (i == mName.length-1) {
							whereClause += "\"" + merchant + "\")) ";
							break;
						}
						whereClause += "\"" + merchant + "\", ";
					}}
				}
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
				
				List<Map<String, Object>> list = searchJT.selectMerchantNames();
				for (Map<String, Object> map : list) {
					map.replace("merchantName", ((String)map.get("merchantName")).replace(" ", "-"));
				}
				model.addAttribute("merchantNames", list);
				
				return searchJT.selectSearchResults(columns, whereClause);
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
        	
        	model.addAttribute("searchResults", sr);
        }
        else {
        	model.addAttribute("searchResults", searchResults);
        }
		
        Gson gson = new Gson();
		String mNameJson = gson.toJson(mName);
		model.addAttribute("merchantNameIds", mNameJson);
		model.addAttribute("seller", seller);
		model.addAttribute("priceLH", priceLH);
		model.addAttribute("priceHL", priceHL);
		
		
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
