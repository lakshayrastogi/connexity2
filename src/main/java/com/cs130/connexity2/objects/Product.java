package com.cs130.connexity2.objects;

import java.util.ArrayList;
import java.util.List;

public class Product {
	private int includedResults;
	private int totalResults;
	private double minPrice, maxPrice;
	private List<Offer> offers = new ArrayList<Offer>();
	
	public Product() {
	}

	public Product(int includedResults, int totalResults, double minPrice, double maxPrice, List<Offer> offers) {
		this.includedResults = includedResults;
		this.totalResults = totalResults;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.offers = offers;
	}

	public int getIncludedResults() {
		return includedResults;
	}

	public void setIncludedResults(int includedResults) {
		this.includedResults = includedResults;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
}
