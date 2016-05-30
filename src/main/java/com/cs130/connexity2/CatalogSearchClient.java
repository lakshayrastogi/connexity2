package com.cs130.connexity2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cs130.connexity2.objects.Dimension;
import com.cs130.connexity2.objects.Merchant;
import com.cs130.connexity2.objects.MerchantQuery;
import com.cs130.connexity2.objects.Offer;
import com.cs130.connexity2.objects.Percentage;
import com.cs130.connexity2.objects.ProductQuery;
import com.cs130.connexity2.objects.Query;
import com.cs130.connexity2.objects.SearchResult;
import com.cs130.connexity2.util.Globals;

public class CatalogSearchClient {
	
	public CatalogSearchClient() {}
	
	static private JSONParser jsonParser = new JSONParser();
	
	//readUrl function from http://stackoverflow.com/questions/7467568/parsing-json-from-url
	//Used to read JSON from URL as string
	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	    	String formattedUrl = urlString.replaceAll(" ", "%20");
	        URL url = new URL(formattedUrl);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 
	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
	
	private SearchResult convertToSearchResult(JSONObject jsonRes) {
		SearchResult res = new SearchResult();
		try {
			res.setMerchantId((long) jsonRes.get("merchantId"));
		} catch (Exception e) {res.setMerchantId(-1); e.printStackTrace();}
		try {
			res.setCategoryId((long) jsonRes.get("categoryId"));
		} catch (Exception e) {res.setCategoryId(-1); e.printStackTrace();}
		try {
			res.setId(((long) jsonRes.get("id")));
		} catch (Exception e) {res.setId(-1); e.printStackTrace();}
		try {
			String title = (String) jsonRes.get("title");
			if (title != null) {
				res.setTitle(title);
			}
			else {
				res.setTitle("");
			}
		} catch (Exception e) {res.setTitle(""); e.printStackTrace();}
		try {
			String brandName = (String) ((JSONObject) jsonRes.get("brand")).get("name");
			if (brandName != null) {
				res.setBrandName(brandName);
			}
			else {
				res.setBrandName("");
			}
		} catch (Exception e) {res.setBrandName(""); /*e.printStackTrace();*/}
		try {
			String description = (String) jsonRes.get("description");
			if (description != null) {
				res.setDescription(description);
			}
			else {
				res.setDescription("");
			}
		} catch (Exception e) {res.setDescription(""); e.printStackTrace();}
		try {
			String url = (String) ((JSONObject) jsonRes.get("url")).get("value");
			if (url != null) {
				res.setUrl(url);
			}
			else {
				res.setUrl("");
			}
		} catch (Exception e) {res.setUrl(""); e.printStackTrace();}
		try {
			List<String> images = new ArrayList<>();
			JSONArray jsonArr = (JSONArray) ((JSONObject) jsonRes.get("images")).get("image");
			for (int i = 0; i < jsonArr.size(); i++) {
				String imageUrl = (String) ((JSONObject) jsonArr.get(i)).get("value");
				images.add(imageUrl);
			}
			res.setImages(images);
		} catch (Exception e) {res.setImages(null); e.printStackTrace();}
		try {
			String sku = (String) jsonRes.get("sku");
			if (sku != null) {
				res.setSku(sku);
			}
			else {
				res.setSku("");
			}
		} catch (Exception e) {res.setSku(""); e.printStackTrace();}
		try {
			String detailUrl = (String) ((JSONObject) jsonRes.get("detailUrl")).get("value");
			if (detailUrl != null) {
				res.setDetailUrl(detailUrl);
			}
			else {
				res.setDetailUrl("");
			}
		} catch (Exception e) {res.setDetailUrl(""); e.printStackTrace();}
		try {
			res.setPrice((((Long)((JSONObject) jsonRes.get("price")).get("integral")).doubleValue()/100.0));
		} catch (Exception e) {res.setPrice(-1); e.printStackTrace();}
		try {
			res.setOriginalPrice((((Long)((JSONObject) jsonRes.get("originalPrice")).get("integral")).doubleValue())/100.0);
		} catch (Exception e) {res.setOriginalPrice(-1); e.printStackTrace();}
		try {
			String priceStr = (String) ((JSONObject) jsonRes.get("price")).get("value");
			if (priceStr != null) {
				res.setPriceStr(priceStr);
			}
			else {
				res.setPriceStr("N/A");
			}
		} catch (Exception e) {res.setPriceStr("N/A"); e.printStackTrace();}
		try {
			String originalPriceStr = (String) ((JSONObject) jsonRes.get("originalPrice")).get("value");
			if (originalPriceStr != null) {
				res.setOriginalPriceStr(originalPriceStr);
			}
			else {
				res.setOriginalPriceStr("N/A");
			}
		} catch (Exception e) {res.setOriginalPriceStr("N/A"); e.printStackTrace();}
		try {
			res.setMarkdownPercent((Double) jsonRes.get("markdownPercent"));
		} catch (Exception e) {res.setMarkdownPercent(-1); e.printStackTrace();}
		try {
			res.setBidded ((boolean) jsonRes.get("bidded"));
		} catch (Exception e) {res.setBidded(false); e.printStackTrace();}
		try {
			String merchantName = (String) jsonRes.get("merchantName");
			if (merchantName != null) {
				res.setMerchantName(merchantName);
			}
			else {
				res.setMerchantName("");
			}
		} catch (Exception e) {res.setMerchantName(""); e.printStackTrace();}
		try {
			res.setMerchantCertified((boolean) (((JSONObject) jsonRes.get("merchantCertification")).get("certified")));
		} catch (Exception e) {res.setMerchantCertified(false); e.printStackTrace();}
		try {
			String merchantLogoUrl = (String) jsonRes.get("merchantLogoUrl");
			if (merchantLogoUrl != null) {
				res.setMerchantLogoUrl(merchantLogoUrl);
			}
			else {
				res.setMerchantLogoUrl("");
			}
		} catch (Exception e) {res.setMerchantLogoUrl(""); e.printStackTrace();}
		try {
			String condition = (String) jsonRes.get("condition");
			if (condition != null) {
				res.setCondition(condition);
			}
			else {
				res.setCondition("");
			}
		} catch (Exception e) {res.setCondition(""); e.printStackTrace();}
		try {
			res.setRelevancy((Double) jsonRes.get("relevancy"));
		} catch (Exception e) {res.setRelevancy(-1); e.printStackTrace();}
		return res;
	}
	
	private Offer convertToOffer(JSONObject jsonRes) {
		Offer offer = new Offer();
		try {
			offer.setMerchantId((long) jsonRes.get("merchantId"));
		} catch (Exception e) {offer.setMerchantId(-1); e.printStackTrace();}
		try {
			offer.setCategoryId((long) jsonRes.get("categoryId"));
		} catch (Exception e) {offer.setCategoryId(-1); e.printStackTrace();}
		try {
			offer.setId((long) jsonRes.get("id"));
		} catch (Exception e) {offer.setId(-1); e.printStackTrace();}
		try {
			String title = (String) jsonRes.get("title");
			if (title != null) {
				offer.setTitle(title);
			}
			else {
				offer.setTitle("");
			}
		} catch (Exception e) {offer.setTitle(""); e.printStackTrace();}
		try {
			offer.setBrandName((String) ((JSONObject) jsonRes.get("brand")).get("name"));
		} catch (Exception e) {offer.setBrandName(""); /*e.printStackTrace();*/}
		try {
			offer.setDescription((String) jsonRes.get("description"));
		} catch (Exception e) {offer.setDescription(null); e.printStackTrace();}
		try {
			offer.setManufacturer((String) jsonRes.get("manufacturer"));
		} catch (Exception e) {offer.setManufacturer(null); e.printStackTrace();}
		try {
			offer.setUrl((String) ((JSONObject) jsonRes.get("url")).get("value"));
		} catch (Exception e) {offer.setUrl(null); e.printStackTrace();}
		try {
			List<String> images = new ArrayList<>();
			JSONArray jsonArr = (JSONArray) ((JSONObject) jsonRes.get("images")).get("image");
			for (int i = 0; i < jsonArr.size(); i++) {
				String imageUrl = (String) ((JSONObject) jsonArr.get(i)).get("value");
				images.add(imageUrl);
			}
			offer.setImages(images);
		} catch (Exception e) {offer.setImages(null); e.printStackTrace();}
		try {
			offer.setSku((String) jsonRes.get("sku"));
		} catch (Exception e) {offer.setSku(null); e.printStackTrace();}
		try {
			offer.setDetailUrl((String) ((JSONObject) jsonRes.get("detailUrl")).get("value"));
		} catch (Exception e) {offer.setDetailUrl(null); e.printStackTrace();}
		try {
			offer.setPrice((((Long)((JSONObject) jsonRes.get("price")).get("integral")).doubleValue()/100.0));
		} catch (Exception e) {offer.setPrice(-1); e.printStackTrace();}
		try {
			offer.setOriginalPrice((((Long)((JSONObject) jsonRes.get("originalPrice")).get("integral")).doubleValue())/100.0);
		} catch (Exception e) {offer.setOriginalPrice(-1); e.printStackTrace();}
		try {
			offer.setMarkdownPercent((Double) jsonRes.get("markdownPercent"));
		} catch (Exception e) {offer.setMarkdownPercent(-1); e.printStackTrace();}
		try {
			offer.setBidded ((boolean) jsonRes.get("bidded"));
		} catch (Exception e) {offer.setBidded(false); e.printStackTrace();}
		try {
			offer.setMerchantProductId((String) jsonRes.get("merchantProductId"));
		} catch (Exception e) {offer.setMerchantProductId(null); e.printStackTrace();}
		try {
			offer.setMerchantName((String) jsonRes.get("merchantName"));
		} catch (Exception e) {offer.setMerchantName(null); e.printStackTrace();}
		try {
			offer.setMerchantCertified((boolean) (((JSONObject) jsonRes.get("merchantCertification")).get("certified")));
		} catch (Exception e) {offer.setMerchantCertified(false); e.printStackTrace();}
		try {
			offer.setMerchantCertificationLevel((String) (((JSONObject) jsonRes.get("merchantCertification")).get("level")));
		} catch (Exception e) {offer.setMerchantCertificationLevel(null); e.printStackTrace();}
		try {
			String merchantLogoUrl = (String) jsonRes.get("merchantLogoUrl");
			if (merchantLogoUrl != null) {
				offer.setMerchantLogoUrl(merchantLogoUrl);
			}
			else {
				offer.setMerchantLogoUrl("");
			}
		} catch (Exception e) {offer.setMerchantLogoUrl(""); e.printStackTrace();}
		try {
			offer.setStock((String) jsonRes.get("stock"));
		} catch (Exception e) {offer.setStock(null); e.printStackTrace();}
		try {
			offer.setCondition((String) jsonRes.get("condition"));
		} catch (Exception e) {offer.setCondition(null); e.printStackTrace();}
		try {
			offer.setShipAmount((((Long)((JSONObject) jsonRes.get("shipAmount")).get("integral")).doubleValue())/100.0);
		} catch (Exception e) {offer.setShipAmount(-1); e.printStackTrace();}
		try {
			offer.setShipType((String) jsonRes.get("shipType"));
		} catch (Exception e) {offer.setShipType(null); e.printStackTrace();}
		try {
			offer.setShipWeight((Double) jsonRes.get("shipWeight"));
		} catch (Exception e) {offer.setShipWeight(-1); e.printStackTrace();}
		try {
			offer.setRelevancy((Double) jsonRes.get("relevancy"));
		} catch (Exception e) {offer.setRelevancy(-1); e.printStackTrace();}
		return offer;
	}
	
	private Merchant convertToMerchant(JSONObject jsonRes) {
		Merchant merchant = new Merchant();
		JSONObject jMerchantInfo = (JSONObject) ((JSONObject) jsonRes.get("merchantInfo"));
		JSONObject jRating = (JSONObject) ((JSONObject) jsonRes.get("merchantRating")).get("rating");
		try {
			merchant.setMerchantId((long) jsonRes.get("mid"));
		} catch (Exception e) {merchant.setMerchantId(-1); e.printStackTrace();}
		try {
			String name = (String) jMerchantInfo.get("name");
			if (name != null) {
				merchant.setName(name);
			}
			else {
				merchant.setName("");
			}
		} catch (Exception e) {merchant.setName(""); e.printStackTrace();}
		try {
			String url = (String) jMerchantInfo.get("url");
			if (url != null) {
				merchant.setUrl(url);
			}
			else {
				merchant.setUrl("");
			}
		} catch (Exception e) {merchant.setUrl(""); e.printStackTrace();}
		try {
			String merchantUrl = (String) jMerchantInfo.get("merchantUrl");
			if (merchantUrl != null) {
				merchant.setMerchantUrl(merchantUrl);
			}
			else {
				merchant.setMerchantUrl("");
			}
		} catch (Exception e) {merchant.setMerchantUrl(""); e.printStackTrace();}
		try {
			String logoUrl = (String) jMerchantInfo.get("logoUrl");
			if (logoUrl != null) {
				merchant.setLogoUrl(logoUrl);
			}
			else {
				merchant.setLogoUrl("");
			}
		} catch (Exception e) {merchant.setLogoUrl(""); e.printStackTrace();}
		try {
			merchant.setRatingPeriod((long) jRating.get("ratingPeriod"));
		} catch (Exception e) {merchant.setRatingPeriod(-1); e.printStackTrace();}		
		try {
			
			String certificationUrl = (String) ((JSONObject)((JSONObject) jRating.get("status")).get("certification")).get("url");
			if (certificationUrl != null) {
				merchant.setCertificationUrl(certificationUrl);
			}
			else {
				merchant.setCertificationUrl("");
			}
		} catch (Exception e) {merchant.setCertificationUrl(""); e.printStackTrace();}
		try {
			merchant.setCertificationValue((boolean) ((JSONObject)((JSONObject) jRating.get("status")).get("certification")).get("value"));
		} catch (Exception e) {merchant.setCertificationValue(false); e.printStackTrace();}
		try {
			String ratingUrl = (String) jRating.get("url");
			if (ratingUrl != null) {
				merchant.setRatingUrl(ratingUrl);
			}
			else {
				merchant.setRatingUrl("");
			}
		} catch (Exception e) {merchant.setRatingUrl(""); e.printStackTrace();}
		try {
			String ratingDetailUrl = (String) jRating.get("urlDetail");
			if (ratingDetailUrl != null) {
				merchant.setRatingDetailUrl(ratingDetailUrl);
			}
			else {
				merchant.setRatingDetailUrl("");
			}
		} catch (Exception e) {merchant.setRatingDetailUrl(""); e.printStackTrace();}
		
		
		/*
		private ArrayList<Dimension> dimensionalAverages;
		private ArrayList<Percentage> periodNinety;
			 */
		try {
			List<Dimension> dimensionalAverages = new ArrayList<>();
			String dimensionName;
			String imageUrl;
			long max;
			long min;
			double value;
			JSONArray jsonArr = (JSONArray) ((JSONObject) jRating.get("dimensionalAverages")).get("average");
			for (int i = 0; i < jsonArr.size(); i++) {
				dimensionName = (String) ((JSONObject) jsonArr.get(i)).get("dimension");
				if (!(dimensionName.equals("ffCustomerSupport") || dimensionName.equals("ffOverallSatisfaction") || dimensionName.equals("ffLikelihoodToBuyAgain") || dimensionName.equals("posOverallRating")))
					continue;
				imageUrl = (String) ((JSONObject) jsonArr.get(i)).get("image");
				max = (long) ((JSONObject) jsonArr.get(i)).get("max");
				min = (long) ((JSONObject) jsonArr.get(i)).get("min");
				value = (double) ((JSONObject) jsonArr.get(i)).get("value");
				Dimension newDimension = new Dimension(dimensionName, imageUrl, max, min, value);
				dimensionalAverages.add(newDimension);
			}
			merchant.setDimensionalAverages(dimensionalAverages);
		} catch (Exception e) {merchant.setDimensionalAverages(null); e.printStackTrace();}
		
		try {
			List<Percentage> percentages = new ArrayList<>();
			String percentType;
			double percent;
			JSONArray jsonArr2 = (JSONArray) ((JSONObject) jsonRes.get("percentages")).get("percentagePeriod");
			//90 day period is last element of list
			JSONArray jsonArr = (JSONArray) jsonArr2.get(jsonArr2.size()-1);
			for (int i = 0; i < jsonArr.size(); i++) {
				//String percentType, long percent
				percentType = (String) ((JSONObject) jsonArr.get(i)).get("percentType");
				percent = (double) ((JSONObject) jsonArr.get(i)).get("percent");
				Percentage newPercentage = new Percentage(percentType, percent);
				percentages.add(newPercentage);
			}
			merchant.setPeriodNinety(percentages);
		} catch (Exception e) {merchant.setPeriodNinety(null); e.printStackTrace();}
		
		return merchant;
	}

	public List<SearchResult> getSearchResults(Query query) {
		try {
			//publisherId=608865&keyword=shoes&results=20&resultsOffers=10&format=json&offersOnly=true
			List<SearchResult> searchResults = new ArrayList<>();
			String newUrl = Globals.BASE_URL;
			switch(query.getQueryType()){
				case PRODUCT:
					// Type cast to the right query type - Can make a private variable too of ProductQuery and assign the object to that in Controller
					ProductQuery prodQuery = (ProductQuery) query;	
					newUrl += "product?";
					newUrl += "keyword=" + prodQuery.getKeyword() + "&format=" + prodQuery.getFormat() + "&offersOnly=" + prodQuery.isOffersOnly()
							+ "&placementId=" + prodQuery.getPlacementId() + "&categoryId=" + (prodQuery.getCategoryId()==-1 ? "" : prodQuery.getCategoryId()) + "&productId=" + prodQuery.getProductId()
							+ "&productIdType=" + prodQuery.getProductIdType() + "&merchantId=" + (prodQuery.getMerchantId()==-1 ? "" : prodQuery.getMerchantId()) 
							+ "&brandId=" + (prodQuery.getBrandId()==-1 ? "" : prodQuery.getBrandId()) + "&biddedOnly=" + prodQuery.isBiddedOnly() + "&minPrice=" + (prodQuery.getMinPrice()==-1 ? "" : prodQuery.getMinPrice())
							+ "&maxPrice=" + (prodQuery.getMaxPrice()==-1 ? "" : prodQuery.getMaxPrice()) + "&minMarkdown=" + (prodQuery.getMinMarkdown()==-1 ? "" : prodQuery.getMinMarkdown())
							+ "&zipCode=" + prodQuery.getZipCode() + "&freeShipping=" + prodQuery.isFreeShipping() + "&start=" + prodQuery.getStart() + "&results=" + prodQuery.getResults()
							+ "&backFillResults=" + prodQuery.getBackFillResults() + "&startOffers=" + prodQuery.getStartOffers() + "&resultsOffers=" + prodQuery.getResultsOffers()
							+ "&sort=" + prodQuery.getSort() + "&attFilter=" + prodQuery.getAttFilter() + "&attWeights=" + prodQuery.getAttWeights() + "&attributeId=" + prodQuery.getAttributeId()
							+ "&resultsAttribute=" + prodQuery.getResultsAttribute() + "&resultsAttributeValues=" + prodQuery.getResultsAttributeValues() + "&showAttributes=" + prodQuery.isShowAttributes()
							+ "&showProductAttributes=" + prodQuery.isShowProductAttributes() + "&minRelevancyScore=" + prodQuery.getMinRelevancyScore() + "&maxAge=" + (prodQuery.getMaxAge() == -1 ? "" : prodQuery.getMaxAge())
							+ "&showRawUrl=" + prodQuery.isShowRawUrl() + "&imageOnly=" + prodQuery.isImageOnly() + "&reviews=" + prodQuery.getReviews() + "&retailOnly=" + prodQuery.isRetailOnly()
							+ "&callback=" + prodQuery.getCallback(); 
					break;
				case BRAND:
					newUrl+="brand?";
					break;
				case ATTRIBUTES:
					newUrl+="attributes?";
					break;
				case TAXONOMY:
					newUrl+="taxonomy?";
					break;
				case MERCHANT_INFO:
					newUrl+="merchantinfo?";
					break;
				case PRODUCT_REVIEW:
					newUrl+="productReviews?";
					break;
				default: break;
			}
			newUrl+="&apiKey=" + Globals.API_KEY + "&publisherId=" + Globals.PUBLISHER_ID;
			System.out.println(newUrl);
			//convert url to json object
			JSONObject jsonObj = (JSONObject) jsonParser.parse(readUrl(newUrl));
			//obtain json list of offers
			JSONArray jsonArr = (JSONArray) ((JSONObject) jsonObj.get("offers")).get("offer");
			for (int i = 0; i < jsonArr.size(); i++) {
				JSONObject jsonRes = (JSONObject) jsonArr.get(i);
				/*SearchResult constructor parses JSONObject and 
				 * sets member variables accordingly */
				searchResults.add(convertToSearchResult(jsonRes));
			}
			return searchResults;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Offer getItemInfo(Query query) {
		try {
			Offer currentOffer = new Offer();
			String newUrl = Globals.BASE_URL;
			ProductQuery prodQuery = (ProductQuery) query;	
			newUrl += "product?";
			newUrl += "&format=" + prodQuery.getFormat() 
						+ "&offersOnly=" + prodQuery.isOffersOnly()
						+ "&placementId=" + prodQuery.getPlacementId() 
						+ "&productId=" + prodQuery.getProductId()
						+ "&productIdType=" + prodQuery.getProductIdType() 
						+ "&merchantId=" + (prodQuery.getMerchantId()==-1 ? "" : prodQuery.getMerchantId()) 
						+ "&brandId=" + (prodQuery.getBrandId()==-1 ? "" : prodQuery.getBrandId()) 
						+ "&maxPrice=" + (prodQuery.getMaxPrice()==-1 ? "" : prodQuery.getMaxPrice()) 
						+ "&minMarkdown=" + (prodQuery.getMinMarkdown()==-1 ? "" : prodQuery.getMinMarkdown())
						+ "&results=" + prodQuery.getResults()
						+ "&resultsOffers=" + prodQuery.getResultsOffers()
						+ "&sort=" + prodQuery.getSort()
						+ "&showAttributes=" + prodQuery.isShowAttributes()
						+ "&reviews=" + prodQuery.getReviews()
						+ "&callback=" + prodQuery.getCallback();
			newUrl+="&apiKey=" + Globals.API_KEY + "&publisherId=" + Globals.PUBLISHER_ID;
			System.out.println(newUrl);
			//convert url to json object
			JSONObject jsonObj = (JSONObject) jsonParser.parse(readUrl(newUrl));
			//obtain json offer
			JSONArray jsonArr = (JSONArray) ((JSONObject) jsonObj.get("offers")).get("offer");
			JSONObject jsonRes = (JSONObject) jsonArr.get(0);
			//Convert the Json to an offer object
			currentOffer = convertToOffer(jsonRes);
			return currentOffer;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Merchant getMerchantInfo(Query query) {
		try {
			Merchant currentMerchant = new Merchant();

			String newUrl = Globals.BASE_URL;
			MerchantQuery merchQuery = (MerchantQuery) query;	
			newUrl += "merchantinfo?";
			newUrl += "&format=" + merchQuery.getFormat()
						+ "&placementId=" + merchQuery.getPlacementId() 
						+ "&merchantId=" + merchQuery.getMerchantId()
						+ "&timePeriod=" + merchQuery.getTimePeriod()
						+ "&showRatings=" + merchQuery.isShowRatings()
						+ "&showReviews=" + merchQuery.isShowReviews()
						+ "&filterEmptyComments=" + merchQuery.isFilterEmptyComments()
						+ "&showMerchantDetails=" + merchQuery.isShowMerchantDetails()
						+ "&ratedOnly=" + merchQuery.isRatedOnly()
						+ "&certifiedOnly=" + merchQuery.isCertifiedOnly()
						+ "&callback=" + merchQuery.getCallback();
			newUrl+="&apiKey=" + Globals.API_KEY + "&publisherId=" + Globals.PUBLISHER_ID;
			System.out.println(newUrl);
			//convert url to json object
			JSONObject jsonObj = (JSONObject) jsonParser.parse(readUrl(newUrl));
			//obtain json merchant info
			JSONArray jsonArr = (JSONArray) jsonObj.get("merchant");
			JSONObject jsonRes = (JSONObject) jsonArr.get(0);
			//Convert the Json to an offer object
			currentMerchant = convertToMerchant(jsonRes);
			return currentMerchant;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
