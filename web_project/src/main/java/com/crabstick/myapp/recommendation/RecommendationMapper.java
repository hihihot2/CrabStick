package com.crabstick.myapp.recommendation;

import java.util.ArrayList;

import com.crabstick.myapp.venue.Venue;

public interface RecommendationMapper {
	

	
	ArrayList<City> searchByName(String loc_name);

	void update_Loc_Code(City update_City);

	ArrayList<City> select_All();

	ArrayList<Integer> select_All_Transaction();

	ArrayList<Venue> all_Data();

	ArrayList<String> all_Sequence();
	

}
