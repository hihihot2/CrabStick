package com.crabstick.api.foursquare.objects;

import org.json.simple.JSONObject;

public class Price {
	public static final String COLUMN_TIER = "tier";
	public static final String COLUMN_MESSAGE = "message";
	public static final String COLUMN_CURRENCY = "currency";
	
	private long tier;
	private String message;
	private String currency;
	
	@Override
	public String toString() {
		return "Price [tier=" + tier + ", message=" + message + ", currency=" + currency + "]";
	}

	public Price(long tier, String message, String currency) {
		super();
		this.tier = tier;
		this.message = message;
		this.currency = currency;
	}
	
	public Price(JSONObject price) {
		super();
		if(price.get(COLUMN_TIER) != null) tier = (Long) price.get(COLUMN_TIER);
		if(price.get(COLUMN_MESSAGE) != null) message = (String) price.get(COLUMN_MESSAGE);
		if(price.get(COLUMN_CURRENCY) != null) currency = (String) price.get(COLUMN_CURRENCY);
	}

	public Price() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getTier() {
		return tier;
	}

	public void setTier(long tier) {
		this.tier = tier;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
}
