package com.crabstick.api.foursquare.objects;

import org.json.simple.JSONObject;

public class Icon {
	public static final String COLUMN_PREFIX = "prefix";
	public static final String COLUMN_SUFFIX = "suffix";
	
	private String prefix;
	private String suffix;
	
	@Override
	public String toString() {
		return "Icon [prefix=" + prefix + ", suffix=" + suffix + "]";
	}

	public Icon(String prefix, String suffix) {
		super();
		this.prefix = prefix;
		this.suffix = suffix;
	}

	public Icon(JSONObject icon) {
		super();
		if(icon.get(COLUMN_PREFIX) != null) prefix = (String) icon.get(COLUMN_PREFIX);
		if(icon.get(COLUMN_SUFFIX) != null) suffix = (String) icon.get(COLUMN_SUFFIX);
	}
	
	public Icon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}