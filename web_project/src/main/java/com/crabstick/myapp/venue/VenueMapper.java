package com.crabstick.myapp.venue;

import java.util.ArrayList;

public interface VenueMapper {
	void insert(Venue v);
	ArrayList<Venue> select(int path_no);
	void deleteAllByPathNo(int pathNo);
}
