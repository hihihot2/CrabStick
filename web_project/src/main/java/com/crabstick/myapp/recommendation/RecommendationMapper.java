package com.crabstick.myapp.recommendation;

import java.util.ArrayList;

public interface RecommendationMapper {
	
	ArrayList<City> large_City_Rank();
	ArrayList<City> natural_City_Rank();
	ArrayList<City> historical_City_Rank();
	ArrayList<City> historical_City_Rank_TOP5();
	ArrayList<City> large_City_Rank_TOP5();
	ArrayList<City> natural_City_Rank_TOP5();
	ArrayList<City> searchByName(String loc_name);
}
