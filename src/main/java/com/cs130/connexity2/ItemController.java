package com.cs130.connexity2;

import java.io.IOException;
import java.util.List;

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
		System.out.println("Offer 1 \n ---------------------------");
		String res = 
				"Title: " + offerResult.getTitle() + '\n' +
				"Brand: " + offerResult.getBrandName() + '\n' +
				"Description: " + offerResult.getDescription() + '\n' +
				"Price: $" + offerResult.getPrice() + '\n' +
				"Relevancy: " + offerResult.getRelevancy() + '\n' +
				"merchantId: " + offerResult.getMerchantId() + '\n' +
				"categoryId: " + offerResult.getCategoryId() + '\n' +
				"id: " + offerResult.getId() + '\n';
		System.out.println(res);
		
		//Category query for related items
		ProductQuery catQuery = new ProductQuery();
		catQuery.setQueryType(Globals.SearchType.PRODUCT);
		catQuery.setCategoryId(offerResult.getCategoryId());
		List<SearchResult> categorySearchResults = searchClient.getSearchResults(catQuery);
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
		if (Globals.USE_TWITTER) {
			TwitterSearchClient twitterSearchClient = new TwitterSearchClient(Globals.TwitterSearchType.ITEM, 
																			  offerResult.getMerchantName());
			List<String> tweetHtmlSnippets = null;
			tweetHtmlSnippets = twitterSearchClient.getHtmlSnippets(keyword);
			model.addAttribute("tweetHtmlSnippets", tweetHtmlSnippets);
			//test output
			if (tweetHtmlSnippets != null) {
				System.out.println("Twitter Search Result Html Snippets, " + tweetHtmlSnippets.size() + " results");
				for (int i = 0; i < tweetHtmlSnippets.size(); i++) {
					System.out.println(tweetHtmlSnippets.get(i));
				}
			}
		}
		
		return "item";
	}
}
