package com.crabstick.api.expedia.objects;

import org.json.simple.JSONObject;

public class PriceOption {
	public static final String COLUMN_MIN_PRICE = "minPrice";
	public static final String COLUMN_MAX_PRICE = "maxPrice";
	public static final String COLUMN_COUNT = "count";
	
	private String minPrice;
	private String maxPrice;
	private long count;
	
	public PriceOption(String minPrice, String maxPrice, long count) {
		super();
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.count = count;
	}

	@Override
	public String toString() {
		return "PriceOption [minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", count=" + count + "]";
	}

	public PriceOption(JSONObject jsonObject) {
		if(jsonObject.get(COLUMN_MIN_PRICE) != null) minPrice = (String) jsonObject.get(COLUMN_MIN_PRICE);
		if(jsonObject.get(COLUMN_MAX_PRICE) != null) maxPrice = (String) jsonObject.get(COLUMN_MAX_PRICE);
		if(jsonObject.get(COLUMN_COUNT) != null) count = (Long) jsonObject.get(COLUMN_COUNT);
	}

	public PriceOption() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}
