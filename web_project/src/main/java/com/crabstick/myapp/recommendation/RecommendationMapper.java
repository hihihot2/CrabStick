package com.crabstick.myapp.recommendation;

import java.util.ArrayList;

public interface RecommendationMapper {
	

	
	ArrayList<City> searchByName(String loc_name);

	void update_Loc_Code(City update_City);

	ArrayList<City> select_All();
	

}
