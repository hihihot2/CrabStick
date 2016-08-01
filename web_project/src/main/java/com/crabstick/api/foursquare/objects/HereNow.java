package com.crabstick.api.foursquare.objects;

import java.util.ArrayList;



/********* 안쓰고 있음 *********/
public class HereNow {
	private int count;
	private String summary;
	private ArrayList<Object> groups;
	
	@Override
	public String toString() {
		return "HereNow [count=" + count + ", summary=" + summary + ", groups=" + groups + "]";
	}

	public HereNow(int count, String summary, ArrayList<Object> groups) {
		super();
		this.count = count;
		this.summary = summary;
		this.groups = groups;
	}

	public HereNow() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public ArrayList<Object> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<Object> groups) {
		this.groups = groups;
	}
	
//	public void add
}
