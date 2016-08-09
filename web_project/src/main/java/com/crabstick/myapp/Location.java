package com.crabstick.myapp;

public class Location {
	private String title;
	private String addr;
	private Double lat;
	private Double lng;
	public Location(){}
	
	public Location(String title, String addr, Double lat, Double lng) {
		super();
		this.title = title;
		this.addr = addr;
		this.lat = lat;
		this.lng = lng;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
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
		return "Location [title=" + title + ", addr=" + addr + ", lat=" + lat + ", lng=" + lng + "]";
	}
}
