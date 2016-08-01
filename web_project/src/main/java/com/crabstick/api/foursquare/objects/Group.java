package com.crabstick.api.foursquare.objects;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Group {
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_ITEMS = "items";
	
	private String type;
	private String name;
	private ArrayList<Venue> items;
	
	@Override
	public String toString() {
		return "Group [type=" + type + ", name=" + name + ", items=" + items + "]";
	}

	public Group(String type, String name, ArrayList<Venue> items) {
		super();
		this.type = type;
		this.name = name;
		this.items = items;
	}
	
	public Group(JSONObject group) {
		super();
		if(group.get(COLUMN_TYPE) != null) type = (String) group.get(COLUMN_TYPE);
		if(group.get(COLUMN_NAME) != null) name = (String) group.get(COLUMN_NAME);
		if(group.get(COLUMN_ITEMS) != null) {
			items = new ArrayList<Venue>();
			JSONArray venues = (JSONArray) group.get(COLUMN_ITEMS);
			Iterator venuesIterator = venues.iterator();
			
			while(venuesIterator.hasNext()) {
				items.add(new Venue((JSONObject) ((JSONObject) venuesIterator.next()).get("venue")));
			}			
		}
		
	}

	public Group() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Venue> getItems() {
		return items;
	}

	public void setVenues(ArrayList<Venue> items) {
		this.items = items;
	}
	
	
}
