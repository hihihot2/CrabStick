package com.crabstick.myapp.recommendation;

import java.util.ArrayList;

import com.crabstick.myapp.venue.Venue;

public interface RecommendationService {
	
	/* 검색 메서드 */
	public ArrayList<City> searchByName(String loc_name);
	/* 검색 메서드 */
	
	/*검색 업뎃 메서드*/
	public void update_Weight(City update_City);
	/*검색 업뎃 메서드*/
	
	/*도시 DB 모두 불러오기*/
	public ArrayList<City> All_City();
	/*도시 DB 모두 불러오기*/
	
	/* Venues Table로부터 Transaction(경로 정보가 담긴 하나의 행을 의미) 번호 받아오기 */
	public ArrayList<Integer> all_Transactions();

	/* Venues Table의 자료 가져오기*/
	public ArrayList<Venue> all_Data();
	
	/* Venues Table로 부터 빈발 항목 판별을 위한 지역 데이터 가져오기*/
	public ArrayList<String> all_Sequence();

	
}
