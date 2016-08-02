package com.crabstick.api.expedia.objects;

import org.json.simple.JSONObject;

public class Neighborhood {
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_ID = "id";
	
	private String name;
	private long id;
	
	@Override
	public String toString() {
		return "Neighborhood [name=" + name + ", id=" + id + "]";
	}

	public Neighborhood(String name, long id) {
		super();
		this.name = name;
		this.id = id;
	}

	public Neighborhood(JSONObject jsonObject) {
		if(jsonObject.get(COLUMN_NAME) != null) name = (String) jsonObject.get(COLUMN_NAME);
		if(jsonObject.get(COLUMN_ID) != null) id = (Long) jsonObject.get(COLUMN_ID);
	}

	public Neighborhood() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
