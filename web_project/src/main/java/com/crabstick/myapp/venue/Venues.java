package com.crabstick.myapp.venue;

public class Venues {

	private int ven_no;
	private String ven_name;
	private String ven_lati;
	private String ven_long;
	private String ven_commt;
	private String ven_type;
	private int ven_order;
	private int path_no;
	private int loc_no;
	
	
	public Venues() {
		super();
	}
	public Venues(int ven_no, String ven_name, String ven_lati, String ven_long, String ven_commt, String ven_type,
			int ven_order, int path_no, int loc_no) {
		super();
		this.ven_no = ven_no;
		this.ven_name = ven_name;
		this.ven_lati = ven_lati;
		this.ven_long = ven_long;
		this.ven_commt = ven_commt;
		this.ven_type = ven_type;
		this.ven_order = ven_order;
		this.path_no = path_no;
		this.loc_no = loc_no;
	}
	public int getVen_no() {
		return ven_no;
	}
	public void setVen_no(int ven_no) {
		this.ven_no = ven_no;
	}
	public String getVen_name() {
		return ven_name;
	}
	public void setVen_name(String ven_name) {
		this.ven_name = ven_name;
	}
	public String getVen_lati() {
		return ven_lati;
	}
	public void setVen_lati(String ven_lati) {
		this.ven_lati = ven_lati;
	}
	public String getVen_long() {
		return ven_long;
	}
	public void setVen_long(String ven_long) {
		this.ven_long = ven_long;
	}
	public String getVen_commt() {
		return ven_commt;
	}
	public void setVen_commt(String ven_commt) {
		this.ven_commt = ven_commt;
	}
	public String getVen_type() {
		return ven_type;
	}
	public void setVen_type(String ven_type) {
		this.ven_type = ven_type;
	}
	public int getVen_order() {
		return ven_order;
	}
	public void setVen_order(int ven_order) {
		this.ven_order = ven_order;
	}
	public int getPath_no() {
		return path_no;
	}
	public void setPath_no(int path_no) {
		this.path_no = path_no;
	}
	public int getLoc_no() {
		return loc_no;
	}
	public void setLoc_no(int loc_no) {
		this.loc_no = loc_no;
	}
	@Override
	public String toString() {
		return "Venue [ven_no=" + ven_no + ", ven_name=" + ven_name + ", ven_lati=" + ven_lati + ", ven_long="
				+ ven_long + ", ven_commt=" + ven_commt + ", ven_type=" + ven_type + ", ven_order=" + ven_order
				+ ", path_no=" + path_no + ", loc_no=" + loc_no + "]";
	}
	
	
}
