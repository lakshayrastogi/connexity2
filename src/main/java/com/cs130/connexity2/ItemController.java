package com.cs130.connexity2;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cs130.connexity2.objects.Offer;
import com.cs130.connexity2.objects.ProductQuery;
import com.cs130.connexity2.objects.SearchResult;
import com.cs130.connexity2.util.Globals;

@Controller
public class ItemController {

	@RequestMapping(value="/item",method=RequestMethod.GET)
	public String item(@RequestParam(value="productId",required=true) String productId, Model model){
		if (productId.isEmpty()) {
    		return "redirect:/main";
    	}
		ProductQuery newQuery = new ProductQuery();
    	newQuery.setQueryType(Globals.SearchType.PRODUCT);
    	newQuery.setProductId(productId);
    	newQuery.setProductIdType("MPID");
		CatalogSearchClient searchClient = new CatalogSearchClient();
		Offer offerResult = searchClient.getItemInfo(newQuery);
		model.addAttribute("offerResult", offerResult);
		
		System.out.println("Offer 1 \n ---------------------------");
		String res = 
				"Title: " + offerResult.getTitle() + '\n' +
				"Brand: " + offerResult.getBrandName() + '\n' +
				"Description: " + offerResult.getDescription() + '\n' +
				"Price: $" + offerResult.getPrice() + '\n' +
				"Merchant Certified: " + offerResult.isMerchantCertified() + '\n';
		System.out.println(res);
		
		return "item";
	}
}
