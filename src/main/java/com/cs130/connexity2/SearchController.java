package com.cs130.connexity2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import com.cs130.connexity2.util.Globals;
import com.cs130.connexity2.objects.ProductQuery;
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
    	ProductQuery prodQuery = new ProductQuery();
    	prodQuery.setKeyword(keyword);
		CatalogSearchClient searchClient = new CatalogSearchClient();
		List<SearchResult> searchResults = searchClient.getSearchResults(prodQuery, Globals.SearchType.PRODUCT);
		model.addAttribute("searchResults", searchResults);
		// test
		
		for (int i = 0; i < searchResults.size(); i++) {
			System.out.println("Offer " + (i+1) + '\n' + "---------------------------");
			SearchResult offer = searchResults.get(i);
			String res = 
					"Title: " + offer.getTitle() + '\n' +
					"Brand: " + offer.getBrandName() + '\n' +
					"Description: " + offer.getDescription() + '\n' +
					"Price: $" + offer.getPrice() + '\n' +
					"Merchant Certified: " + offer.isMerchantCertified() + '\n';
			System.out.println(res);
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
