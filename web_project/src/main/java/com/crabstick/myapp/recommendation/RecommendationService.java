package com.crabstick.myapp.recommendation;

import java.util.ArrayList;

public interface RecommendationService {
	
	//TODO 1. 도시 추천
	/*
	 * process 
	 * 로그인한 사용자일 경우,
	 * 사용자 설문 결과 (주 여행 지역의 답변과 관련 1)대도시, 2)자연경관, 3)유적지, 4)상관없음) 를 대표하는 도시를 추천해줘야함.
	 * 로그인을 하지 않은 사용자일 경우 일반적으로 유명한 도시? or 플래너에 가장 많이 등록된 도시를 추천(추후 논의)
	 *   
	 * */
	public ArrayList<City> recommendation_City(String answer);
	public ArrayList<City> recommendation_City_TOP5(String answer);
	public ArrayList<City> searchByName(String loc_name);
}
