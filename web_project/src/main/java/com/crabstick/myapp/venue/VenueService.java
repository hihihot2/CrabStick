package com.crabstick.myapp.venue;

import java.util.ArrayList;

public interface VenueService {
	void insertVenue(Venue v);
	ArrayList<Venue> selectVenue(int path_no);

}
