package com.crabstick.myapp.location;

public class Location {
	private int plan_no;
	private int loc_no;
	private String loc_name;
	private String loc_lati;
	private String loc_long;
	private int loc_ctweight;
	private int loc_hiweight;
	private int loc_ntweight;
	private int loc_code;
	private int loc_siguncode;
	private String loc_imgpath;
	private String loc_commt;
	
	@Override
	public String toString() {
		return "Location [loc_no=" + loc_no + ", loc_name=" + loc_name + ", loc_lati=" + loc_lati + ", loc_long="
				+ loc_long + ", loc_ctweight=" + loc_ctweight + ", loc_hiweight=" + loc_hiweight + ", loc_ntweight="
				+ loc_ntweight + ", loc_code=" + loc_code + ", loc_siguncode=" + loc_siguncode + ", loc_imgpath="
				+ loc_imgpath + ", loc_commt=" + loc_commt + "]";
	}

	public Location(int loc_no, String loc_name, String loc_lati, String loc_long, int loc_ctweight, int loc_hiweight,
			int loc_ntweight, int loc_code, int loc_siguncode, String loc_imgpath, String loc_commt) {
		super();
		this.loc_no = loc_no;
		this.loc_name = loc_name;
		this.loc_lati = loc_lati;
		this.loc_long = loc_long;
		this.loc_ctweight = loc_ctweight;
		this.loc_hiweight = loc_hiweight;
		this.loc_ntweight = loc_ntweight;
		this.loc_code = loc_code;
		this.loc_siguncode = loc_siguncode;
		this.loc_imgpath = loc_imgpath;
		this.loc_commt = loc_commt;
	}

	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public int getLoc_hiweight() {
		return loc_hiweight;
	}

	public void setLoc_hiweight(int loc_hiweight) {
		this.loc_hiweight = loc_hiweight;
	}

	public int getLoc_ntweight() {
		return loc_ntweight;
	}

	public void setLoc_ntweight(int loc_ntweight) {
		this.loc_ntweight = loc_ntweight;
	}

	public int getLoc_code() {
		return loc_code;
	}

	public void setLoc_code(int loc_code) {
		this.loc_code = loc_code;
	}

	public int getLoc_siguncode() {
		return loc_siguncode;
	}

	public void setLoc_siguncode(int loc_siguncode) {
		this.loc_siguncode = loc_siguncode;
	}

	public String getLoc_imgpath() {
		return loc_imgpath;
	}

	public void setLoc_imgpath(String loc_imgpath) {
		this.loc_imgpath = loc_imgpath;
	}

	public String getLoc_commt() {
		return loc_commt;
	}

	public void setLoc_commt(String loc_commt) {
		this.loc_commt = loc_commt;
	}

	public int getPlan_no() {
		return plan_no;
	}

	public void setPlan_no(int plan_no) {
		this.plan_no = plan_no;
	}
	
	
}
