package com.crabstick.myapp;

public class Location {
	private Double lat;
	private Double lang;
	public Location(){}
	public Location(Double lat, Double lang) {
		super();
		this.lat = lat;
		this.lang = lang;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLang() {
		return lang;
	}
	public void setLang(Double lang) {
		this.lang = lang;
	}
}
