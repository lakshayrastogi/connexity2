package com.cs130.connexity2.objects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SearchResult {
	private int merchantId, categoryId, id;
	private String title;
	private String brandName;
	private String description;
	private String url;
	private List<String> images;
	private String sku;
	private String detailUrl;
	private double price, originalPrice;
	private double markdownPercent;
	private boolean bidded;
	private String merchantName;
	private boolean merchantCertified;
	private String merchantLogoUrl;
	private String condition;
	private double relevancy;
	
	public SearchResult() {
	}
	//constructor parses JSONObject and sets member variables accordingly
	public SearchResult(JSONObject jsonRes) throws NullPointerException {
		try {
			merchantId = ((Long) jsonRes.get("merchantId")).intValue();
		} catch (Exception e) {merchantId = -1; e.printStackTrace();}
		try {
			categoryId = ((Long) jsonRes.get("categoryId")).intValue();
		} catch (Exception e) {categoryId = -1; e.printStackTrace();}
		try {
			id = ((Long) jsonRes.get("id")).intValue();
		} catch (Exception e) {id = -1; e.printStackTrace();}
		try {
			title = (String) jsonRes.get("title");
		} catch (Exception e) {title = null; e.printStackTrace();}
		//brandName only field to throw NullPointerException so far (aka no brand name for some offers)
		try {
			brandName = (String) ((JSONObject) jsonRes.get("brand")).get("name");
		} catch (Exception e) {brandName = null; /*e.printStackTrace();*/}
		try {
			description = (String) jsonRes.get("description");
		} catch (Exception e) {description = null; e.printStackTrace();}
		try {
			url = (String) ((JSONObject) jsonRes.get("url")).get("value");
		} catch (Exception e) {url = null; e.printStackTrace();}
		try {
			images = new ArrayList<>();
			JSONArray jsonArr = (JSONArray) ((JSONObject) jsonRes.get("images")).get("image");
			for (int i = 0; i < jsonArr.size(); i++) {
				String imageUrl = (String) ((JSONObject) jsonArr.get(i)).get("value");
				images.add(imageUrl);
			}
		} catch (Exception e) {images = null; e.printStackTrace();}
		try {
			sku = (String) jsonRes.get("sku");
		} catch (Exception e) {sku = null; e.printStackTrace();}
		try {
			detailUrl = (String) ((JSONObject) jsonRes.get("detailUrl")).get("value");
		} catch (Exception e) {detailUrl = null; e.printStackTrace();}
		try {
			price = (((Long)((JSONObject) jsonRes.get("price")).get("integral")).doubleValue()/100.0);
		} catch (Exception e) {price = -1; e.printStackTrace();}
		try {
			originalPrice = (((Long)((JSONObject) jsonRes.get("originalPrice")).get("integral")).doubleValue())/100.0;
		} catch (Exception e) {originalPrice = -1; e.printStackTrace();}
		try {
			markdownPercent = (Double) jsonRes.get("markdownPercent");
		} catch (Exception e) {markdownPercent = -1; e.printStackTrace();}
		try {
			bidded = (boolean) jsonRes.get("bidded");
		} catch (Exception e) {bidded = false; e.printStackTrace();} //should bool default to false in case of exception?
		try {
			merchantName = (String) jsonRes.get("merchantName");
		} catch (Exception e) {merchantName = null; e.printStackTrace();}
		try {
			merchantCertified = (boolean) (((JSONObject) jsonRes.get("merchantCertification")).get("certified"));
		} catch (Exception e) {merchantCertified = false; e.printStackTrace();}
		try {
			merchantLogoUrl = (String) jsonRes.get("merchantLogoUrl");
		} catch (Exception e) {merchantLogoUrl = null; e.printStackTrace();}
		try {
			condition = (String) jsonRes.get("condition");
		} catch (Exception e) {condition = null; e.printStackTrace();}
		try {
			relevancy = (Double) jsonRes.get("relevancy");
		} catch (Exception e) {relevancy = -1; e.printStackTrace();}
	}
	public SearchResult(int merchantId, int categoryId, int id, String title, String brandName, String description,
			String url, List<String> images, String sku, String detailUrl, double price, double originalPrice,
			double markdownPercent, boolean bidded, String merchantName, boolean merchantCertified,
			String merchantLogoUrl, String condition, double relevancy) {
		this.merchantId = merchantId;
		this.categoryId = categoryId;
		this.id = id;
		this.title = title;
		this.brandName = brandName;
		this.description = description;
		this.url = url;
		this.images = images;
		this.sku = sku;
		this.detailUrl = detailUrl;
		this.price = price;
		this.originalPrice = originalPrice;
		this.markdownPercent = markdownPercent;
		this.bidded = bidded;
		this.merchantName = merchantName;
		this.merchantCertified = merchantCertified;
		this.merchantLogoUrl = merchantLogoUrl;
		this.condition = condition;
		this.relevancy = relevancy;
	}

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public double getMarkdownPercent() {
		return markdownPercent;
	}

	public void setMarkdownPercent(double markdownPercent) {
		this.markdownPercent = markdownPercent;
	}

	public boolean isBidded() {
		return bidded;
	}

	public void setBidded(boolean bidded) {
		this.bidded = bidded;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public boolean isMerchantCertified() {
		return merchantCertified;
	}

	public void setMerchantCertified(boolean merchantCertified) {
		this.merchantCertified = merchantCertified;
	}

	public String getMerchantLogoUrl() {
		return merchantLogoUrl;
	}

	public void setMerchantLogoUrl(String merchantLogoUrl) {
		this.merchantLogoUrl = merchantLogoUrl;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getRelevancy() {
		return relevancy;
	}

	public void setRelevancy(double relevancy) {
		this.relevancy = relevancy;
	}
}
