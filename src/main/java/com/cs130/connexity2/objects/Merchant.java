package com.cs130.connexity2.objects;

import javax.validation.constraints.NotNull;

public class Merchant {
	
	@NotNull
	private int mid;
	@NotNull
	private String name;
	
	private String url, merchantUrl, logoUrl;
	private int totalProductCount;
	private String countryCode;
	private int rating;
	private String merchantReview;
	
	public Merchant() {
	}

	public Merchant(int mid, String name, String url, String merchantUrl, String logoUrl, int totalProductCount,
			String countryCode, int rating, String merchantReview) {
		this.mid = mid;
		this.name = name;
		this.url = url;
		this.merchantUrl = merchantUrl;
		this.logoUrl = logoUrl;
		this.totalProductCount = totalProductCount;
		this.countryCode = countryCode;
		this.rating = rating;
		this.merchantReview = merchantReview;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMerchantUrl() {
		return merchantUrl;
	}

	public void setMerchantUrl(String merchantUrl) {
		this.merchantUrl = merchantUrl;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public int getTotalProductCount() {
		return totalProductCount;
	}

	public void setTotalProductCount(int totalProductCount) {
		this.totalProductCount = totalProductCount;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getMerchantReview() {
		return merchantReview;
	}

	public void setMerchantReview(String merchantReview) {
		this.merchantReview = merchantReview;
	}
	
}
