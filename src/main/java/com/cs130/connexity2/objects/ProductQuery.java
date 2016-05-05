package com.cs130.connexity2.objects;

public class ProductQuery extends Query{

	private int placementId; //Default 1
	private int categoryId;
	private String keyword;
	private String productId, productIdType; //One of:SZPID,UPC,SKU, MPID, SZOID
	private boolean offersOnly;
	private int merchantId;
	private int brandId;
	private boolean biddedOnly;
	private int minPrice, maxPrice;
	private int minMarkdown;
	private String zipCode;
	private boolean freeShipping; //Default: false
	private int start; //Default: 0; Paging start parameter for Products (or Offers if the offersOnly parameter is "true")	
	private int results; //Default: 10
	private int backFillResults; //Default: 0; Deprecated
	private int startOffers; //Default: 0
	private int resultsOffers; //Default: 0
	private String sort; //One of: price_asc, price_desc, relevancy_desc (default)
	private String attFilter; //Filter results based on attributes. This parameter takes a specially formatted value, the format of which is: attrId + ':' + attrValue (+ ';' + attrId + ':' + attrValue)*. An example would be: attFilter=259:12345;402:19874. Values for this parameter can be discovered through the RelatedAttributes element returned when the showAttributes parameter has the value "true". Maximum is 20.
	private String attWeights;//Apply weights to the attributes. This parameter takes a specially formatted value, the format of which is: attrId + ':' + attrValue + ':' attrWeight (+ ';' + attrId + ':' + attrValue + ':' attrWeight)*. An example would be: attrWeight=259:12345:100;402:19874:200. Values for this parameter can be discovered through the RelatedAttributes element returned when the showAttributes parameter has the value "true". Weight must be a number between 0 and 100000. A weight of 0 will effectively exclude products/offers that match the specified attribute value, eg. 259:12345:0 translates to exclude products/offers having brand id 12345. Maximum is 20.
	private String attributeId; //Can have multiple?
	private int resultsAttribute; //Default: 10; Maximum number of attributes to return in response when the showAttributes parameter has the value "true". Maximum is 20.
	private int resultsAttributeValues; //Default: 10; Maximum number of attribute values to return for all attributes in response when the showAttributes parameter has the value "true". The maximum number of attribute values that can be returned in a single attribute is 512.
	private boolean showAttributes; //Default: false; Show a list of product attributes related to your query which you could use to further refine your request. This parameter can be used in conjunction with the attributeId or resultsAttribute parameters to control which or how many attributes are queried, respectively. It can also be used in conjunction with resultsAttributeValues to control how many attribute values are returned per attribute.
	private boolean showProductAttributes; //Default false; Will display additional detailed attributes for a specific product or offer. Used either in conjunction with the productId parameter and a productIdType of "SZPID" or in conjunction with offersOnly=true and the attributeId parameter. Note that not all products or offers have detailed attribute data.
	private int minRelevancyScore; //Default 100; Deprecated
	private int maxAge; //One of: 1, 2, 3, 7, 30
	private boolean showRawUrl;
	private boolean imageOnly;
	private String reviews; //One of: aggregate, none(default)
	private boolean retailOnly; //Default: false
	private String format; //Default: xml; One of xml, json, js
	private String callback; //Default: callback
	
	public ProductQuery(){
		this.placementId = 1;
		this.categoryId = -1;	// unsigned in API call
		this.productId = "";
		this.productIdType = "";
		this.offersOnly = true;
		this.merchantId = -1;	// unsigned in API call
		this.brandId = -1;
		this.biddedOnly = true;
		this.minPrice = -1;	// unsigned in API call
		this.maxPrice = -1;	// unsigned in API call
		this.minMarkdown = -1; // unsigned in API call
		this.zipCode = "";
		this.freeShipping = false;
		this.start = 0;
		this.results = 10;
		this.backFillResults = 0;
		this.startOffers = 0;
		this.resultsOffers = 0;
		this.sort = "relevancy_desc";
		this.attFilter = "";
		this.attWeights = "";
		this.attributeId = "";
		this.resultsAttribute = 10;
		this.resultsAttributeValues = 10;
		this.showAttributes = false;
		this.showProductAttributes = false;
		this.minRelevancyScore = 100;
		this.maxAge = -1;	// one of 1,2,3,7,30 in API call
		this.showRawUrl = false;
		this.imageOnly = false;
		this.reviews = "none";
		this.retailOnly = false;
		this.format = "json";
		this.setCallback("callback");
	}
	
	public int getPlacementId() {
		return placementId;
	}

	public void setPlacementId(int placementId) {
		this.placementId = placementId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductIdType() {
		return productIdType;
	}

	public void setProductIdType(String productIdType) {
		this.productIdType = productIdType;
	}

	public boolean isOffersOnly() {
		return offersOnly;
	}

	public void setOffersOnly(boolean offersOnly) {
		this.offersOnly = offersOnly;
	}

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public boolean isBiddedOnly() {
		return biddedOnly;
	}

	public void setBiddedOnly(boolean biddedOnly) {
		this.biddedOnly = biddedOnly;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getMinMarkdown() {
		return minMarkdown;
	}

	public void setMinMarkdown(int minMarkdown) {
		this.minMarkdown = minMarkdown;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public boolean isFreeShipping() {
		return freeShipping;
	}

	public void setFreeShipping(boolean freeShipping) {
		this.freeShipping = freeShipping;
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

	public int getBackFillResults() {
		return backFillResults;
	}

	public void setBackFillResults(int backFillResults) {
		this.backFillResults = backFillResults;
	}

	public int getStartOffers() {
		return startOffers;
	}

	public void setStartOffers(int startOffers) {
		this.startOffers = startOffers;
	}

	public int getResultsOffers() {
		return resultsOffers;
	}

	public void setResultsOffers(int resultsOffers) {
		this.resultsOffers = resultsOffers;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getAttFilter() {
		return attFilter;
	}

	public void setAttFilter(String attFilter) {
		this.attFilter = attFilter;
	}

	public String getAttWeights() {
		return attWeights;
	}

	public void setAttWeights(String attWeights) {
		this.attWeights = attWeights;
	}

	public String getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}

	public int getResultsAttribute() {
		return resultsAttribute;
	}

	public void setResultsAttribute(int resultsAttribute) {
		this.resultsAttribute = resultsAttribute;
	}

	public int getResultsAttributeValues() {
		return resultsAttributeValues;
	}

	public void setResultsAttributeValues(int resultsAttributeValues) {
		this.resultsAttributeValues = resultsAttributeValues;
	}

	public boolean isShowAttributes() {
		return showAttributes;
	}

	public void setShowAttributes(boolean showAttributes) {
		this.showAttributes = showAttributes;
	}

	public boolean isShowProductAttributes() {
		return showProductAttributes;
	}

	public void setShowProductAttributes(boolean showProductAttributes) {
		this.showProductAttributes = showProductAttributes;
	}

	public int getMinRelevancyScore() {
		return minRelevancyScore;
	}

	public void setMinRelevancyScore(int minRelevancyScore) {
		this.minRelevancyScore = minRelevancyScore;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public boolean isShowRawUrl() {
		return showRawUrl;
	}

	public void setShowRawUrl(boolean showRawUrl) {
		this.showRawUrl = showRawUrl;
	}

	public boolean isImageOnly() {
		return imageOnly;
	}

	public void setImageOnly(boolean imageOnly) {
		this.imageOnly = imageOnly;
	}

	public String getReviews() {
		return reviews;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	public boolean isRetailOnly() {
		return retailOnly;
	}

	public void setRetailOnly(boolean retailOnly) {
		this.retailOnly = retailOnly;
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
