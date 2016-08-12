package com.crabstick.myapp.recommendation;

/**
 * @author Administrator
 *
 */
public class City {
	// CITY INFORMATION DTO 

	//Locations DB Columns
	private int loc_no;
	private String loc_name;
	private String loc_lati;
	private String loc_long;
	private String loc_imgpath;
	private String loc_commt;
	
	//Location DB Weight Columns(purpose)
	private int loc_p_cult;
	private int loc_p_food;
	private int loc_p_shop;
	private int loc_p_rest;
	
	//Location DB weight Columns (accompany)
	private int loc_a_solo;
	private int loc_a_fam;
	private int loc_a_frnd;
	private int loc_a_coup;

	//Location DB weight Columns (Ciry)
	private int loc_c_capt;
	private int loc_c_his;
	private int loc_c_natu;
	
	//Constructors
	public City() {
	}
	public City(int loc_no, String loc_name, String loc_lati, String loc_long, String loc_imgpath, String loc_commt,
			int loc_p_cult, int loc_p_food, int loc_p_shop, int loc_p_rest, int loc_a_solo, int loc_a_fam,
			int loc_a_frnd, int loc_a_coup, int loc_c_capt, int loc_c_his, int loc_c_natu) {
		super();
		this.loc_no = loc_no;
		this.loc_name = loc_name;
		this.loc_lati = loc_lati;
		this.loc_long = loc_long;
		this.loc_imgpath = loc_imgpath;
		this.loc_commt = loc_commt;
		this.loc_p_cult = loc_p_cult;
		this.loc_p_food = loc_p_food;
		this.loc_p_shop = loc_p_shop;
		this.loc_p_rest = loc_p_rest;
		this.loc_a_solo = loc_a_solo;
		this.loc_a_fam = loc_a_fam;
		this.loc_a_frnd = loc_a_frnd;
		this.loc_a_coup = loc_a_coup;
		this.loc_c_capt = loc_c_capt;
		this.loc_c_his = loc_c_his;
		this.loc_c_natu = loc_c_natu;
	}

	//toString
	@Override
	public String toString() {
		return "City [loc_no=" + loc_no + ", loc_name=" + loc_name + ", loc_lati=" + loc_lati + ", loc_long=" + loc_long
				+ ", loc_imgpath=" + loc_imgpath + ", loc_commt=" + loc_commt + ", loc_p_cult=" + loc_p_cult
				+ ", loc_p_food=" + loc_p_food + ", loc_p_shop=" + loc_p_shop + ", loc_p_rest=" + loc_p_rest
				+ ", loc_a_solo=" + loc_a_solo + ", loc_a_fam=" + loc_a_fam + ", loc_a_frnd=" + loc_a_frnd
				+ ", loc_a_coup=" + loc_a_coup + ", loc_c_capt=" + loc_c_capt + ", loc_c_his=" + loc_c_his
				+ ", loc_c_natu=" + loc_c_natu + "]";
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
	public int getLoc_p_cult() {
		return loc_p_cult;
	}
	public void setLoc_p_cult(int loc_p_cult) {
		this.loc_p_cult = loc_p_cult;
	}
	public int getLoc_p_food() {
		return loc_p_food;
	}
	public void setLoc_p_food(int loc_p_food) {
		this.loc_p_food = loc_p_food;
	}
	public int getLoc_p_shop() {
		return loc_p_shop;
	}
	public void setLoc_p_shop(int loc_p_shop) {
		this.loc_p_shop = loc_p_shop;
	}
	public int getLoc_p_rest() {
		return loc_p_rest;
	}
	public void setLoc_p_rest(int loc_p_rest) {
		this.loc_p_rest = loc_p_rest;
	}
	public int getLoc_a_solo() {
		return loc_a_solo;
	}
	public void setLoc_a_solo(int loc_a_solo) {
		this.loc_a_solo = loc_a_solo;
	}
	public int getLoc_a_fam() {
		return loc_a_fam;
	}
	public void setLoc_a_fam(int loc_a_fam) {
		this.loc_a_fam = loc_a_fam;
	}
	public int getLoc_a_frnd() {
		return loc_a_frnd;
	}
	public void setLoc_a_frnd(int loc_a_frnd) {
		this.loc_a_frnd = loc_a_frnd;
	}
	public int getLoc_a_coup() {
		return loc_a_coup;
	}
	public void setLoc_a_coup(int loc_a_coup) {
		this.loc_a_coup = loc_a_coup;
	}
	public int getLoc_c_capt() {
		return loc_c_capt;
	}
	public void setLoc_c_capt(int loc_c_capt) {
		this.loc_c_capt = loc_c_capt;
	}
	public int getLoc_c_his() {
		return loc_c_his;
	}
	public void setLoc_c_his(int loc_c_his) {
		this.loc_c_his = loc_c_his;
	}
	public int getLoc_c_natu() {
		return loc_c_natu;
	}
	public void setLoc_c_natu(int loc_c_natu) {
		this.loc_c_natu = loc_c_natu;
	}

}