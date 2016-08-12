package com.crabstick.myapp.recommendation;

import java.util.ArrayList;

public interface RecommendationMapper {
	
	//설문 결과에 의한 도시 추천
	ArrayList<City> recommend_City_WithAccompany(String loc_code);
	
	ArrayList<City> searchByName(String loc_name);

	void update_Loc_Code(City resultCity);
	

}
