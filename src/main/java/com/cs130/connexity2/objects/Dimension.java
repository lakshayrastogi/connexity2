package com.cs130.connexity2.objects;

public class Dimension {
	
	private String name;
	private String imageUrl;
	private long max;
	private long min;
	private double value;
	
	public Dimension() {
	}

	public Dimension(String name, String imageUrl, long max, long min, double value) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.max = max;
		this.min = min;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}

	public long getMin() {
		return min;
	}

	public void setMin(long min) {
		this.min = min;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
