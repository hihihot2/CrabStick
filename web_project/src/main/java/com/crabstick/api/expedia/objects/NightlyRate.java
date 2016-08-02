package com.crabstick.api.expedia.objects;

import org.json.simple.JSONObject;

public class NightlyRate {
	public static final String COLUMN_PROMO = "promo";
	public static final String COLUMN_BASE_RATE = "baseRate";
	public static final String COLUMN_RATE = "rate";
	
	private String promo;
	private String baseRate;
	private String rate;

	@Override
	public String toString() {
		return "NightlyRate [promo=" + promo + ", baseRate=" + baseRate + ", rate=" + rate + "]";
	}

	public NightlyRate(String promo, String baseRate, String rate) {
		super();
		this.promo = promo;
		this.baseRate = baseRate;
		this.rate = rate;
	}

	public NightlyRate(JSONObject jsonObject) {
		if(jsonObject.get(COLUMN_PROMO) != null) promo = (String) jsonObject.get(COLUMN_PROMO);
		if(jsonObject.get(COLUMN_BASE_RATE) != null) baseRate = (String) jsonObject.get(COLUMN_BASE_RATE);
		if(jsonObject.get(COLUMN_RATE) != null) rate= (String) jsonObject.get(COLUMN_RATE);
	}

	public NightlyRate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public String getBaseRate() {
		return baseRate;
	}

	public void setBaseRate(String baseRate) {
		this.baseRate = baseRate;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
	
}
