package com.cs130.connexity2.objects;

public class Percentage {

	private String percentType;
	private double percent;
	
	public Percentage() {
	}
	
	public Percentage(String percentType, double percent) {
		this.percentType = percentType;
		this.percent = percent;
	}
	
	public String getPercentType() {
		return percentType;
	}
	
	public void setPercentType(String percentType) {
		this.percentType = percentType;
	}
	
	public double getPercent() {
		return percent;
	}
	
	public void setPercent(double percent) {
		this.percent = percent;
	}
	
}
