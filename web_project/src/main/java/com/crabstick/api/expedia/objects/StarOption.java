package com.crabstick.api.expedia.objects;

import org.json.simple.JSONObject;

public class StarOption {
	public static final String COLUMN_STAR_RATING = "starRating";
	public static final String COLUMN_COUNT = "count";
	
	private long starRating;
	private long count;
	
	@Override
	public String toString() {
		return "StarOption [starRating=" + starRating + ", count=" + count + "]";
	}

	public StarOption(long starRating, long count) {
		super();
		this.starRating = starRating;
		this.count = count;
	}

	public StarOption(JSONObject jsonObject) {
		if(jsonObject.get(COLUMN_STAR_RATING) != null) starRating = (Long) jsonObject.get(COLUMN_STAR_RATING);
		if(jsonObject.get(COLUMN_COUNT) != null) count = (Long) jsonObject.get(COLUMN_COUNT);
	}

	public StarOption() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getStarRating() {
		return starRating;
	}

	public void setStarRating(long starRating) {
		this.starRating = starRating;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
