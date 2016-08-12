package com.crabstick.myapp.recommendation;

import java.util.ArrayList;

public interface RecommendationMapper {
	
	ArrayList<City> recommend_City_WithAccompany(String loc_code);
	
	ArrayList<City> searchByName(String loc_name);
}
