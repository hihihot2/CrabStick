package com.crabstick.api.foursquare.objects;

public class Photo {
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_CREATED_AT = "createdAt";
	public static final String COLUMN_PREFIX = "prefix";
	public static final String COLUMN_SUFFIX = "suffix";
	public static final String COLUMN_VISIVILITY = "visibility";
	
	private String id;
	private int createdAt;
	private String prefix;
	private String suffix;
	private String visibility;
	
	@Override
	public String toString() {
		return "Photo [id=" + id + ", createdAt=" + createdAt + ", prefix=" + prefix + ", suffix=" + suffix
				+ ", visibility=" + visibility + "]";
	}

	public Photo(String id, int createdAt, String prefix, String suffix, String visibility) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.prefix = prefix;
		this.suffix = suffix;
		this.visibility = visibility;
	}

	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(int createdAt) {
		this.createdAt = createdAt;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	
}
