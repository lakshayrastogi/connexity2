package com.cs130.connexity2.objects;

import java.util.List;

public class Offer {

	private long merchantId, categoryId, id, brandId;
	private String title;
	private String brandName;
	private String description;
	private String manufacturer;
	private String url;
	private List<String> Images;
	private String sku;
	private String detailUrl;
	private double price, originalPrice;
	private double markdownPercent;
	private boolean bidded;
	private String merchantProductId;
	private String merchantName;
	private boolean merchantCertified;
	private String merchantCertificationLevel;
	private String merchantLogoUrl;
	private String condition, stock;
	private double shipAmount;
	private String shipType;
	private double shipWeight;
	private double relevancy;
	
	public Offer(){
		
	}

	public Offer(long merchantId, long categoryId, long id, String title, long brandId, String brandName,
			String description, String manufacturer, String url, List<String> images, String sku, String detailUrl,
			double price, double originalPrice, double markdownPercent, boolean bidded, String merchantProductId,
			String merchantName, boolean merchantCertified, String merchantCertificationLevel, String merchantLogoUrl,
			String condition, String stock, double shipAmount, String shipType, double shipWeight, double relevancy) {
		
		this.merchantId = merchantId;
		this.categoryId = categoryId;
		this.id = id;
		this.title = title;
		this.brandId = brandId;
		this.brandName = brandName;
		this.description = description;
		this.manufacturer = manufacturer;
		this.url = url;
		Images = images;
		this.sku = sku;
		this.detailUrl = detailUrl;
		this.price = price;
		this.originalPrice = originalPrice;
		this.markdownPercent = markdownPercent;
		this.bidded = bidded;
		this.merchantProductId = merchantProductId;
		this.merchantName = merchantName;
		this.merchantCertified = merchantCertified;
		this.merchantCertificationLevel = merchantCertificationLevel;
		this.merchantLogoUrl = merchantLogoUrl;
		this.condition = condition;
		this.stock = stock;
		this.shipAmount = shipAmount;
		this.shipType = shipType;
		this.shipWeight = shipWeight;
		this.relevancy = relevancy;
	}

	public long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getBrandId() {
		return brandId;
	}

	public void setBrandId(long brandId) {
		this.brandId = brandId;
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

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
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

	public String getMerchantProductId() {
		return merchantProductId;
	}

	public void setMerchantProductId(String merchantProductId) {
		this.merchantProductId = merchantProductId;
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

	public String getMerchantCertificationLevel() {
		return merchantCertificationLevel;
	}

	public void setMerchantCertificationLevel(String merchantCertificationLevel) {
		this.merchantCertificationLevel = merchantCertificationLevel;
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

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public double getShipAmount() {
		return shipAmount;
	}

	public void setShipAmount(double shipAmount) {
		this.shipAmount = shipAmount;
	}

	public String getShipType() {
		return shipType;
	}

	public void setShipType(String shipType) {
		this.shipType = shipType;
	}

	public double getShipWeight() {
		return shipWeight;
	}

	public void setShipWeight(double shipWeight) {
		this.shipWeight = shipWeight;
	}

	public double getRelevancy() {
		return relevancy;
	}

	public void setRelevancy(double relevancy) {
		this.relevancy = relevancy;
	}
	
}
