package com.cs130.connexity2.objects;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

public class Merchant {

	private long mid;
	private String name;
	private String url, merchantUrl, logoUrl;
	private String countryCode;
	
	//Merchant Rating Info
	private long ratingPeriod;
	private ArrayList<Dimension> dimensionalAverages;
	private ArrayList<Percentage> periodNinety;
	private String certificationUrl;
	private String certificationValue;
	private String ratingUrl;
	private String ratingDetailUrl;
	
	public Merchant() {
		this.mid = -1;
		this.name = "";
		this.url = "";
		this.merchantUrl = "";
		this.logoUrl = "";
		this.countryCode = "";
		this.ratingPeriod = -1;
		this.certificationUrl = "";
		this.certificationValue = "";
		this.ratingUrl = "";
		this.ratingDetailUrl = "";
	}

	public Merchant(long mid, String name, String url, String merchantUrl, String logoUrl,
			String countryCode, long ratingPeriod, ArrayList<Dimension> dimensionalAverages,
			ArrayList<Percentage> periodNinety, String certificationUrl, String certificationValue, String ratingUrl,
			String ratingDetailUrl) {
		this.mid = mid;
		this.name = name;
		this.url = url;
		this.merchantUrl = merchantUrl;
		this.logoUrl = logoUrl;
		this.countryCode = countryCode;
		this.ratingPeriod = ratingPeriod;
		this.dimensionalAverages = dimensionalAverages;
		this.periodNinety = periodNinety;
		this.certificationUrl = certificationUrl;
		this.certificationValue = certificationValue;
		this.ratingUrl = ratingUrl;
		this.ratingDetailUrl = ratingDetailUrl;
	}

	public long getMid() {
		return mid;
	}

	public void setMid(long mid) {
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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public long getRatingPeriod() {
		return ratingPeriod;
	}

	public void setRatingPeriod(long ratingPeriod) {
		this.ratingPeriod = ratingPeriod;
	}

	public ArrayList<Dimension> getDimensionalAverages() {
		return dimensionalAverages;
	}

	public void setDimensionalAverages(ArrayList<Dimension> dimensionalAverages) {
		this.dimensionalAverages = dimensionalAverages;
	}

	public ArrayList<Percentage> getPeriodNinety() {
		return periodNinety;
	}

	public void setPeriodNinety(ArrayList<Percentage> periodNinety) {
		this.periodNinety = periodNinety;
	}

	public String getCertificationUrl() {
		return certificationUrl;
	}

	public void setCertificationUrl(String certificationUrl) {
		this.certificationUrl = certificationUrl;
	}

	public String getCertificationValue() {
		return certificationValue;
	}

	public void setCertificationValue(String certificationValue) {
		this.certificationValue = certificationValue;
	}

	public String getRatingUrl() {
		return ratingUrl;
	}

	public void setRatingUrl(String ratingUrl) {
		this.ratingUrl = ratingUrl;
	}
	
	public String getRatingDetailUrl() {
		return ratingDetailUrl;
	}

	public void setRatingDetailUrl(String ratingDetailUrl) {
		this.ratingDetailUrl = ratingDetailUrl;
	}
}
