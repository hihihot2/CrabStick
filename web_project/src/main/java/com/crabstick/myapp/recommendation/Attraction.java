package com.crabstick.myapp.recommendation;

public class Attraction {

	private String title;
	private String addr1;
	private String zipcode;
	private String tel;
	private String mapx;
	private String mapy;
	
	public Attraction() {
		super();
	}

	public Attraction(String title, String addr1, String zipcode, String tel, String mapx, String mapy) {
		super();
		this.title = title;
		this.addr1 = addr1;
		this.zipcode = zipcode;
		this.tel = tel;
		this.mapx = mapx;
		this.mapy = mapy;
	}

	@Override
	public String toString() {
		return "Attraction [title=" + title + ", addr1=" + addr1 + ", zipcode=" + zipcode + ", tel=" + tel + ", mapx="
				+ mapx + ", mapy=" + mapy + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMapx() {
		return mapx;
	}

	public void setMapx(String mapx) {
		this.mapx = mapx;
	}

	public String getMapy() {
		return mapy;
	}

	public void setMapy(String mapy) {
		this.mapy = mapy;
	}
	
	

	
}
