package com.crabstick.api.expedia.objects;

import org.json.simple.JSONObject;

public class JsonHotelBrand {
	public static final String COLUMN_BRAND_ID = "brandId";
	public static final String COLUMN_BRAND_NAME = "brandName";
	
	private String brandId;
	private String brandName;
	
	@Override
	public String toString() {
		return "JsonHotelBrand [brandId=" + brandId + ", brandName=" + brandName + "]";
	}

	public JsonHotelBrand(String brandId, String brandName) {
		super();
		this.brandId = brandId;
		this.brandName = brandName;
	}

	public JsonHotelBrand(JSONObject jsonObject) {
		super();
		if(jsonObject.get(COLUMN_BRAND_ID) != null) brandId = (String) jsonObject.get(COLUMN_BRAND_ID);
		if(jsonObject.get(COLUMN_BRAND_NAME) != null) brandName = (String) jsonObject.get(COLUMN_BRAND_NAME);
	}

	public JsonHotelBrand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	
}
