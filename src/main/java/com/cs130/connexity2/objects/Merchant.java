package com.cs130.connexity2.objects;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class Merchant {

	private long merchantId;
	private String name;
	private String url, merchantUrl, logoUrl;
	
	//Merchant Rating Info
	private long ratingPeriod;
	private List<Dimension> dimensionalAverages;
	private Dimension overallSatisfaction, customerSupport, likelihoodToBuyAgain, overallRating;
	private List<Percentage> periodNinety;
	private String certificationUrl;
	private boolean certificationValue;
	private String ratingUrl;
	private String ratingDetailUrl;
	
	public Merchant() {
		this.merchantId = -1;
		this.name = "";
		this.url = "";
		this.merchantUrl = "";
		this.logoUrl = "";
		this.ratingPeriod = -1;
		this.certificationUrl = "";
		this.certificationValue = false;
		this.ratingUrl = "";
		this.ratingDetailUrl = "";
	}

	public Merchant(long mid, String name, String url, String merchantUrl, String logoUrl,
			long ratingPeriod, List<Dimension> dimensionalAverages, Dimension overallSatisfaction, 
			Dimension customerSupport, Dimension likelihoodToBuyAgain, Dimension overallRating,
			List<Percentage> periodNinety, String certificationUrl, boolean certificationValue, String ratingUrl,
			String ratingDetailUrl) {
		this.merchantId = mid;
		this.name = name;
		this.url = url;
		this.merchantUrl = merchantUrl;
		this.logoUrl = logoUrl;
		this.ratingPeriod = ratingPeriod;
		this.dimensionalAverages = dimensionalAverages;
		this.overallSatisfaction = overallSatisfaction;
		this.customerSupport = customerSupport;
		this.likelihoodToBuyAgain = likelihoodToBuyAgain;
		this.periodNinety = periodNinety;
		this.certificationUrl = certificationUrl;
		this.certificationValue = certificationValue;
		this.ratingUrl = ratingUrl;
		this.ratingDetailUrl = ratingDetailUrl;
	}

	public long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
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

	public long getRatingPeriod() {
		return ratingPeriod;
	}

	public void setRatingPeriod(long ratingPeriod) {
		this.ratingPeriod = ratingPeriod;
	}

	public List<Dimension> getDimensionalAverages() {
		return dimensionalAverages;
	}
	public void setDimensionalAverages(List<Dimension> dimensionalAverages) {
		this.dimensionalAverages = dimensionalAverages;
	}
	
	public Dimension getLikelihoodToBuyAgain() {
		return likelihoodToBuyAgain;
	}
	public void setLikelihoodToBuyAgain(Dimension likelihoodToBuyAgain) {
		this.likelihoodToBuyAgain = likelihoodToBuyAgain;
	}
	
	public Dimension getCustomerSupport() {
		return customerSupport;
	}
	public void setCustomerSupport(Dimension customerSupport) {
		this.customerSupport = customerSupport;
	}
	
	public Dimension getOverallSatisfaction() {
		return overallSatisfaction;
	}
	public void setOverallSatisfaction(Dimension overallSatisfaction) {
		this.overallSatisfaction = overallSatisfaction;
	}
	
	public Dimension getOverallRating() {
		return overallRating;
	}
	public void setOverallRating(Dimension overallRating) {
		this.overallRating = overallRating;
	}
	
	public List<Percentage> getPeriodNinety() {
		return periodNinety;
	}

	public void setPeriodNinety(List<Percentage> periodNinety) {
		this.periodNinety = periodNinety;
	}

	public String getCertificationUrl() {
		return certificationUrl;
	}

	public void setCertificationUrl(String certificationUrl) {
		this.certificationUrl = certificationUrl;
	}

	public boolean getCertificationValue() {
		return certificationValue;
	}

	public void setCertificationValue(boolean certificationValue) {
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
