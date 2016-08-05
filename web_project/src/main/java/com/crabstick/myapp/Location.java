package com.crabstick.myapp;

public class Location {
	private String title;
	private Double lat;
	private Double lng;
	public Location(){}
	public Location(String title, Double lat, Double lng) {
		super();
		this.title = title;
		this.lat = lat;
		this.lng = lng;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	@Override
	public String toString() {
		return "Location [title=" + title + ", lat=" + lat + ", lng=" + lng + "]";
	}
}
