package com.crabstick.api.foursquare.objects;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Category {
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_ICON = "icon";
	private static final String COLUMN_PLURAL_NAME = "pluralName";
	private static final String COLUMN_SHORT_NAME = "shortName";
	private static final String COLUMN_PRIMARY = "primary";
	private static final String COLUMN_CATEGORIES = "categories";
	
	private String id;
	private String name;
	private Icon icon;
	private String pluralName;
	private String shortName;
	private boolean primary;
	private ArrayList<Category> categories;
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", icon=" + icon + ", pluralName=" + pluralName
				+ ", shortName=" + shortName + ", primary=" + primary + ", categories=" + categories + "]";
	}

	public Category(String id, String name, Icon icon, String pluralName, String shortName, boolean primary,
			ArrayList<Category> categories) {
		super();
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.pluralName = pluralName;
		this.shortName = shortName;
		this.primary = primary;
		this.categories = categories;
	}
	
	public Category(JSONObject category) {
		super();
		if(category.get(COLUMN_ID) != null) id = (String) category.get(COLUMN_ID);
		if(category.get(COLUMN_NAME) != null) name = (String) category.get(COLUMN_NAME);
		if(category.get(COLUMN_ICON) != null) icon = new Icon((JSONObject) category.get(COLUMN_ICON));
		if(category.get(COLUMN_PLURAL_NAME) != null) pluralName = (String) category.get(COLUMN_PLURAL_NAME);
		if(category.get(COLUMN_SHORT_NAME) != null) shortName = (String) category.get(COLUMN_SHORT_NAME);
		if(category.get(COLUMN_PRIMARY) != null) primary = (Boolean) category.get(COLUMN_PRIMARY);
		if(category.get(COLUMN_CATEGORIES) != null) categories = (ArrayList) category.get(COLUMN_CATEGORIES);
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public String getPluralName() {
		return pluralName;
	}

	public void setPluralName(String pluralName) {
		this.pluralName = pluralName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}
	
	
}
