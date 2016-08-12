package com.crabstick.myapp.recommendation;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component("recommendationService")
public class RecommendationServiceImpl implements RecommendationService {


	@Resource(name="sqlSession")
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}	


	@Override
	public ArrayList<City> recommendation_City(String loc_code, int index) {
		// TODO 인터페이스에서 정의된 메서드 구체화

		RecommendationMapper recommendationMapper = sqlSession.getMapper(RecommendationMapper.class);
		if (index == 0){ // 설문 항목 1 (목적)
			return null;
		} else if (index == 1){ // 설문 항목 2 (동행)
			return recommendationMapper.recommend_City_WithAccompany(loc_code);
		} else if (index == 2){ // 설문 항목 3 (여행지 선호도)
			return null;
		} else {
			return null;
		}

	}



	@Override
	public ArrayList<City> searchByName(String loc_name) {
		RecommendationMapper recommendationMapper = sqlSession.getMapper(RecommendationMapper.class);
		loc_name = "%" + loc_name + "%";
		return recommendationMapper.searchByName(loc_name);
	}


	@Override
	public void update_Weight(City resultCity) {
		// TODO Auto-generated method stub
		RecommendationMapper recommendationMapper = sqlSession.getMapper(RecommendationMapper.class);
		recommendationMapper.update_Loc_Code(resultCity);
	}


	
}
