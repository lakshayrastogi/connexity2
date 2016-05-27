package com.cs130.connexity2.objects;

import org.apache.commons.beanutils.converters.BooleanArrayConverter;

public class MerchantQuery extends Query{

	private long placementId; //Default 1
	private String merchantId;
	private String timePeriod; //One of: YESTERDAY, PAST_7_DAYS, PAST_30_DAYS, PAST_60_DAYS, PAST_90_DAYS, ALL, CUSTOM
	private String startDate; //Format YYYY-MM-DD
	private String endDate; //Format YYYY-MM-DD
	private int start;
	private int results; //Default: 50
	private int minOSAT; //Minimum overall satisfaction rating (1-10)
	private int minMerchantOSAT; //Minimum overall satisfaction rating (1-10)
	private boolean showRatings; //Default: true
	private boolean showReviews; //Default: true
	private boolean filterEmptyComments; //Default: false
	private boolean showMerchantDetails; //Default: false
	private boolean ratedOnly; //Default: false
	private boolean certifiedOnly; //Default: false
	private String format; //Default: xml; One of xml, json, js
	private String callback; //Default: callback
	
	public MerchantQuery() {
		this.placementId = 1;
		this.merchantId = "";	// unsigned in API call
		this.timePeriod = "ALL";
		this.startDate = "";
		this.endDate = "";
		this.start = 0;
		this.results = 50;
		this.minOSAT = 1;
		this.minMerchantOSAT = 1;
		this.showRatings = true;
		this.showReviews = true;
		this.filterEmptyComments = false;
		this.showMerchantDetails = false;
		this.ratedOnly = false;
		this.certifiedOnly = false;
		this.format = "json";
		this.callback = "callback";
	}

	public long getPlacementId() {
		return placementId;
	}

	public void setPlacementId(long placementId) {
		this.placementId = placementId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getResults() {
		return results;
	}

	public void setResults(int results) {
		this.results = results;
	}

	public int getMinOSAT() {
		return minOSAT;
	}

	public void setMinOSAT(int minOSAT) {
		this.minOSAT = minOSAT;
	}

	public int getMinMerchantOSAT() {
		return minMerchantOSAT;
	}

	public void setMinMerchantOSAT(int minMerchantOSAT) {
		this.minMerchantOSAT = minMerchantOSAT;
	}

	public boolean isShowRatings() {
		return showRatings;
	}

	public void setShowRatings(boolean showRatings) {
		this.showRatings = showRatings;
	}

	public boolean isShowReviews() {
		return showReviews;
	}

	public void setShowReviews(boolean showReviews) {
		this.showReviews = showReviews;
	}

	public boolean isFilterEmptyComments() {
		return filterEmptyComments;
	}

	public void setFilterEmptyComments(boolean filterEmptyComments) {
		this.filterEmptyComments = filterEmptyComments;
	}

	public boolean isShowMerchantDetails() {
		return showMerchantDetails;
	}

	public void setShowMerchantDetails(boolean showMerchantDetails) {
		this.showMerchantDetails = showMerchantDetails;
	}

	public boolean isRatedOnly() {
		return ratedOnly;
	}

	public void setRatedOnly(boolean ratedOnly) {
		this.ratedOnly = ratedOnly;
	}

	public boolean isCertifiedOnly() {
		return certifiedOnly;
	}

	public void setCertifiedOnly(boolean certifiedOnly) {
		this.certifiedOnly = certifiedOnly;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}
	
}
