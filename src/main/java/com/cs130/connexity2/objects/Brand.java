package com.cs130.connexity2.objects;

public class Brand {

	private String name;
	private int productCount;
	private String url;
	
	public Brand() {
	}

	public Brand(String name, int productCount, String url) {
		this.name = name;
		this.productCount = productCount;
		this.url = url;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
