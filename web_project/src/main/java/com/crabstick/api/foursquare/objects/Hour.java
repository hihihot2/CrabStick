package com.crabstick.api.foursquare.objects;

import org.json.simple.JSONObject;

public class Hour {
	public static final String COLUMN_STATUS = "status";
	public static final String COLUMN_IS_OPEN = "isOpen";
	public static final String COLUMN_IS_LOCAL_HOLIDAY = "isLocalHoliday";
	
	private String status;
	private boolean isOpen;
	private boolean isLocalHoliday;
	
	@Override
	public String toString() {
		return "Hour [isOpen=" + isOpen + ", isLocalHoliday=" + isLocalHoliday + "]";
	}

	public Hour(boolean isOpen, boolean isLocalHoliday) {
		super();
		this.isOpen = isOpen;
		this.isLocalHoliday = isLocalHoliday;
	}
	
	public Hour(JSONObject hour) {
		if(hour.get(COLUMN_STATUS) != null) status = (String) hour.get(COLUMN_STATUS);
		if(hour.get(COLUMN_IS_OPEN) != null) isOpen = (Boolean) hour.get(COLUMN_IS_OPEN);
		if(hour.get(COLUMN_IS_LOCAL_HOLIDAY) != null) isLocalHoliday = (Boolean) hour.get(COLUMN_IS_LOCAL_HOLIDAY);
	}

	public Hour() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public boolean isLocalHoliday() {
		return isLocalHoliday;
	}

	public void setLocalHoliday(boolean isLocalHoliday) {
		this.isLocalHoliday = isLocalHoliday;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
