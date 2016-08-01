package com.crabstick.api.foursquare.objects;

import org.json.simple.JSONObject;

public class Stats {
	public static final String COLUMN_CHECKINS_COUNT = "checkinsCount";
	public static final String COLUMN_USERS_COUNT = "usersCount";
	public static final String COLUMN_TIP_COUNT = "tipCount";
	
	private long checkinsCount;
	private long usersCount;
	private long tipCount;
	
	@Override
	public String toString() {
		return "Stats [checkinsCount=" + checkinsCount + ", usersCount=" + usersCount + ", tipCount=" + tipCount + "]";
	}

	public Stats(long checkinsCount, long usersCount, long tipCount) {
		super();
		this.checkinsCount = checkinsCount;
		this.usersCount = usersCount;
		this.tipCount = tipCount;
	}
	
	public Stats(JSONObject stats) {
		super();
		if(stats.get(COLUMN_CHECKINS_COUNT) != null) checkinsCount = (Long) stats.get(COLUMN_CHECKINS_COUNT);
		if(stats.get(COLUMN_USERS_COUNT) != null) usersCount = (Long) stats.get(COLUMN_USERS_COUNT);
		if(stats.get(COLUMN_TIP_COUNT) != null) tipCount = (Long) stats.get(COLUMN_TIP_COUNT);
	}

	public Stats() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getCheckinsCount() {
		return checkinsCount;
	}

	public void setCheckinsCount(long checkinsCount) {
		this.checkinsCount = checkinsCount;
	}

	public long getUsersCount() {
		return usersCount;
	}

	public void setUsersCount(long usersCount) {
		this.usersCount = usersCount;
	}

	public long getTipCount() {
		return tipCount;
	}

	public void setTipCount(long tipCount) {
		this.tipCount = tipCount;
	}
	
	
}
