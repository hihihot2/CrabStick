package com.crabstick.api.expedia.objects;

import org.json.simple.JSONObject;

public class Amenity {
	public static final String COLUMN_ID ="id";
	public static final String COLUMN_DESCRIPTION = "description";
	
	private String id;
	private String description;
	
	@Override
	public String toString() {
		return "Amenity [id=" + id + ", description=" + description + "]";
	}
	
	public Amenity(JSONObject jsonObject) {
		if(jsonObject.get(COLUMN_ID) != null) id = (String) jsonObject.get(COLUMN_ID);
		if(jsonObject.get(COLUMN_DESCRIPTION) != null) description = (String) jsonObject.get(COLUMN_DESCRIPTION);
	}

	public Amenity(String id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public Amenity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
