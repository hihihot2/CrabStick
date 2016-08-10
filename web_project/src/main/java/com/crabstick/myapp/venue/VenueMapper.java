package com.crabstick.myapp.venue;

import java.util.ArrayList;

public interface VenueMapper {
	void insert(Venue v);
	ArrayList<Venue> selectByPathNo(int pathNo);
}
