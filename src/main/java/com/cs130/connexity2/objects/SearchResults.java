package com.cs130.connexity2.objects;

import java.util.List;

public class SearchResults {
	private int merchantId, categoryId, id;
	private String title;
	private String brandName;
	private String description;
	private String url;
	private List<String> Images;
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
	
	public SearchResults() {
	}
	
	public SearchResults(int merchantId, int categoryId, int id, String title, String brandName, String description,
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
		Images = images;
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
		return Images;
	}

	public void setImages(List<String> images) {
		Images = images;
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
