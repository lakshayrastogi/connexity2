package com.cs130.connexity2.objects;

public class Percentage {

	private String percentType;
	private long percent;
	
	public Percentage() {
	}
	
	public Percentage(String percentType, long percent) {
		this.percentType = percentType;
		this.percent = percent;
	}
	
	public String getPercentType() {
		return percentType;
	}
	
	public void setPercentType(String percentType) {
		this.percentType = percentType;
	}
	
	public long getPercent() {
		return percent;
	}
	
	public void setPercent(long percent) {
		this.percent = percent;
	}
	
}
