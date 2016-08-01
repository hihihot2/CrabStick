package com.crabstick.api.foursquare.objects;

import java.util.ArrayList;

/********* 안쓰고 있음 *********/
public class Photos {
	private int count;
	private ArrayList<Photo> groups;
	
	@Override
	public String toString() {
		return "Photos [count=" + count + ", groups=" + groups + "]";
	}

	public Photos(int count, ArrayList<Photo> groups) {
		super();
		this.count = count;
		this.groups = groups;
	}

	public Photos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArrayList<Photo> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<Photo> groups) {
		this.groups = groups;
	}
	
	
	public void addPhoto(Photo photo) {
		groups.add(photo);
	}
	
}
