package com.crabstick.myapp.recommendation;

public class City {
	// CITY INFORMATION DTO 
	
	//Locations DB Columns
	private int loc_no;
	private String loc_name;
	private String loc_lati;
	private String loc_long;
	private int loc_ctweight;
	private int loc_hiweight;
	private int loc_ntweight;
	
	
	
	//Constructor
	public City() {
		super();
	}
	
	//Constructor	
	public City(int loc_no, String loc_name, String loc_lati, String loc_long, int loc_ctweight, int loc_hitweight,
			int loc_ntweight) {
		super();
		this.loc_no = loc_no;
		this.loc_name = loc_name;
		this.loc_lati = loc_lati;
		this.loc_long = loc_long;
		this.loc_ctweight = loc_ctweight;
		this.loc_hiweight = loc_hitweight;
		this.loc_ntweight = loc_ntweight;
	}


	//Getters and Setters
	public int getLoc_no() {
		return loc_no;
	}
	public void setLoc_no(int loc_no) {
		this.loc_no = loc_no;
	}
	public String getLoc_name() {
		return loc_name;
	}
	public void setLoc_name(String loc_name) {
		this.loc_name = loc_name;
	}
	public String getLoc_lati() {
		return loc_lati;
	}
	public void setLoc_lati(String loc_lati) {
		this.loc_lati = loc_lati;
	}
	public String getLoc_long() {
		return loc_long;
	}
	public void setLoc_long(String loc_long) {
		this.loc_long = loc_long;
	}
	public int getLoc_ctweight() {
		return loc_ctweight;
	}
	public void setLoc_ctweight(int loc_ctweight) {
		this.loc_ctweight = loc_ctweight;
	}
	public int getLoc_hitweight() {
		return loc_hiweight;
	}
	public void setLoc_hitweight(int loc_hitweight) {
		this.loc_hiweight = loc_hitweight;
	}
	public int getLoc_ntweight() {
		return loc_ntweight;
	}
	public void setLoc_ntweight(int loc_ntweight) {
		this.loc_ntweight = loc_ntweight;
	}

	@Override
	public String toString() {
		return "City [loc_no=" + loc_no + ", loc_name=" + loc_name + ", loc_lati=" + loc_lati + ", loc_long=" + loc_long
				+ ", loc_ctweight=" + loc_ctweight + ", loc_hitweight=" + loc_hiweight + ", loc_ntweight="
				+ loc_ntweight + "]";
	}
	
	
	

}
