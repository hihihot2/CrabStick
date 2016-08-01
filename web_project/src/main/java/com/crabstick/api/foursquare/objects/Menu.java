package com.crabstick.api.foursquare.objects;

import org.json.simple.JSONObject;

public class Menu {
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_LABEL = "label";
	public static final String COLUMN_ANCHOR = "anchor";
	public static final String COLUMN_URL = "url";
	public static final String COLUMN_MOBILE_URL = "mobileUrl";
	
	private String type;
	private String label;
	private String anchor;
	private String url;
	private String mobileUrl;
	
	@Override
	public String toString() {
		return "Menu [type=" + type + ", label=" + label + ", anchor=" + anchor + ", url=" + url + ", mobileUrl="
				+ mobileUrl + "]";
	}

	public Menu(String type, String label, String anchor, String url, String mobileUrl) {
		super();
		this.type = type;
		this.label = label;
		this.anchor = anchor;
		this.url = url;
		this.mobileUrl = mobileUrl;
	}
	
	public Menu(JSONObject menu) {
		super();
		if(menu.get(COLUMN_TYPE) != null) type = (String) menu.get(COLUMN_TYPE);
		if(menu.get(COLUMN_LABEL) != null) label = (String) menu.get(COLUMN_LABEL);
		if(menu.get(COLUMN_ANCHOR) != null) anchor = (String) menu.get(COLUMN_ANCHOR);
		if(menu.get(COLUMN_URL) != null) url = (String) menu.get(COLUMN_URL);
		if(menu.get(COLUMN_MOBILE_URL) != null) mobileUrl = (String) menu.get(COLUMN_MOBILE_URL);
	}

	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getAnchor() {
		return anchor;
	}

	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMobileUrl() {
		return mobileUrl;
	}

	public void setMobileUrl(String mobileUrl) {
		this.mobileUrl = mobileUrl;
	}
	
	
}
