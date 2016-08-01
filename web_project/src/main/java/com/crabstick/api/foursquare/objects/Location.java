package com.crabstick.api.foursquare.objects;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Location {
	public static final String COLUMN_ADDRESS = "address";
	public static final String COLUMN_CROSS_STREET = "crossStreet";
	public static final String COLUMN_LAT = "lat";
	public static final String COLUMN_LNG = "lng";
	public static final String COLUMN_DISTANCE = "distance";
	public static final String COLUMN_POSTAL_CODE = "postalCode";
	public static final String COLUMN_CC = "cc";
	public static final String COLUMN_CITY = "city";
	public static final String COLUMN_STATE = "state";
	public static final String COLUMN_COUNTRY = "country";
	public static final String COLUMN_FORMATTEDADDRESS = "formattedAddress";
	public static final String COLUMN_ISFUZZED = "isFuzzed";
	
	private String address;
	private String crossStreet;
	private double lat;
	private double lng;
	private long distance;
	private String postalCode;
	private String cc;
	private String city;
	private String state;
	private String country;
	private ArrayList<String> formattedAddress;
	private boolean isFuzzed;
	
	@Override
	public String toString() {
		return "Location [address=" + address + ", crossStreet=" + crossStreet + ", lat=" + lat + ", lng=" + lng
				+ ", distance=" + distance + ", postalCode=" + postalCode + ", cc=" + cc + ", city=" + city + ", state="
				+ state + ", country=" + country + ", formattedAddress=" + formattedAddress + ", isFuzzed=" + isFuzzed
				+ "]";
	}
	
	public Location(String address, String crossStreet, double lat, double lng, long distance, String postalCode,
			String cc, String city, String state, String country, ArrayList<String> formattedAddress,
			boolean isFuzzed) {
		super();
		this.address = address;
		this.crossStreet = crossStreet;
		this.lat = lat;
		this.lng = lng;
		this.distance = distance;
		this.postalCode = postalCode;
		this.cc = cc;
		this.city = city;
		this.state = state;
		this.country = country;
		this.formattedAddress = formattedAddress;
		this.isFuzzed = isFuzzed;
	}


	public Location(JSONObject location) {
		if(location.get(COLUMN_ADDRESS) != null) address = (String) location.get(COLUMN_ADDRESS);
		if(location.get(COLUMN_CROSS_STREET) != null) crossStreet = (String) location.get(COLUMN_CROSS_STREET);
		if(location.get(COLUMN_LAT) != null) lat = (Double) location.get(COLUMN_LAT);
		if(location.get(COLUMN_LNG) != null) lng = (Double) location.get(COLUMN_LNG);
		if(location.get(COLUMN_DISTANCE) != null) distance = (Long) location.get(COLUMN_DISTANCE);
		if(location.get(COLUMN_POSTAL_CODE) != null) postalCode = (String) location.get(COLUMN_POSTAL_CODE);
		if(location.get(COLUMN_CC) != null) cc = (String) location.get(COLUMN_CC);
		if(location.get(COLUMN_CITY) != null) city = (String) location.get(COLUMN_CITY);
		if(location.get(COLUMN_STATE) != null) state = (String) location.get(COLUMN_STATE);
		if(location.get(COLUMN_COUNTRY) != null) country = (String) location.get(COLUMN_COUNTRY);
		
		if(location.get(COLUMN_ADDRESS) != null) {
			formattedAddress = new ArrayList<String>();
			for(Object object : (JSONArray) location.get(COLUMN_FORMATTEDADDRESS)) {
				formattedAddress.add((String) object);
			}
		}
		
		if(location.get(COLUMN_ISFUZZED) != null) isFuzzed = (Boolean) location.get(COLUMN_ISFUZZED);
	}

	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCrossStreet() {
		return crossStreet;
	}

	public void setCrossStreet(String crossStreet) {
		this.crossStreet = crossStreet;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public ArrayList<String> getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(ArrayList<String> formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public boolean isFuzzed() {
		return isFuzzed;
	}

	public void setFuzzed(boolean isFuzzed) {
		this.isFuzzed = isFuzzed;
	}
	


}
