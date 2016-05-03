package com.cs130.connexity2.objects;

public class Category {

	private int id;
	private boolean mature;
	private String name;
	private double minimumBid;
	private double probability;
	private String url;
	
	public Category() {
	}
	
	public Category(int id, boolean mature, String name, double minimumBid, double probability, String url) {
		this.id = id;
		this.mature = mature;
		this.name = name;
		this.minimumBid = minimumBid;
		this.probability = probability;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isMature() {
		return mature;
	}

	public void setMature(boolean mature) {
		this.mature = mature;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMinimumBid() {
		return minimumBid;
	}

	public void setMinimumBid(double minimumBid) {
		this.minimumBid = minimumBid;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
