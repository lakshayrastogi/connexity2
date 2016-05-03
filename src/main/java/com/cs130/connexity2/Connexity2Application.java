package com.cs130.connexity2;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cs130.connexity2.objects.SearchResult;

@SpringBootApplication
public class Connexity2Application {

	public static void main(String[] args) {
		SpringApplication.run(Connexity2Application.class, args);
		//TESTING CatalogSearchClient parser
		testCatalogSearchClient();
				
		/*ApplicationContext ctx = SpringApplication.run(Connexity2Application.class, args);
		
		System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) 
			System.out.println(beanName);
		}
	}*/
	}
	
	public static void testCatalogSearchClient() {
		CatalogSearchClient searchClient = new CatalogSearchClient();
		String urlString = "http://catalog.bizrate.com/services/catalog/v1/us/product?apiKey=f94ab04178d1dea0821d5816dfb8af8d&publisherId=608865&keyword=shoes&results=20&resultsOffers=10&format=json&offersOnly=true";
		List<SearchResult> searchResults = searchClient.getSearchResults(urlString);
		System.out.println("\n\nTesting CatalogSearchClient JSON Parser\n\n");
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
	}
}
