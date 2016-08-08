package com.crabstick.myapp.path;

import java.sql.Date;

public class Path {

	private int path_no;
	private String path_name;
	private Date path_date;
	private int plan_no;
	public Path(int path_no, String path_name, Date path_date, int plan_no) {
		super();
		this.path_no = path_no;
		this.path_name = path_name;
		this.path_date = path_date;
		this.plan_no = plan_no;
	}
	public Path() {
	}
	public int getPath_no() {
		return path_no;
	}
	public void setPath_no(int path_no) {
		this.path_no = path_no;
	}
	public String getPath_name() {
		return path_name;
	}
	public void setPath_name(String path_name) {
		this.path_name = path_name;
	}
	public Date getPath_date() {
		return path_date;
	}
	public void setPath_date(Date path_date) {
		this.path_date = path_date;
	}
	public int getPlan_no() {
		return plan_no;
	}
	public void setPlan_no(int plan_no) {
		this.plan_no = plan_no;
	}
	@Override
	public String toString() {
		return "Path [path_no=" + path_no + ", path_name=" + path_name + ", path_date=" + path_date + ", plan_no="
				+ plan_no + "]";
	}
	
	
}
