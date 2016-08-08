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
	public ArrayList<City> recommendation_City(String answer) {
		// TODO 인터페이스에서 정의된 메서드 구체화
		
		RecommendationMapper recommendationMapper = sqlSession.getMapper(RecommendationMapper.class);
		if (answer.equals("대도시")){
			return recommendationMapper.large_City_Rank();
		} else if (answer.equals("자연경관")){
			return recommendationMapper.natural_City_Rank();
		} else if (answer.equals("유적지")){
			return recommendationMapper.historical_City_Rank();
		} else {//상관없음 아직 구현 안함
			return null;
		}
		
	}

	@Override
	public ArrayList<City> recommendation_City_TOP5(String answer) {
		RecommendationMapper recommendationMapper = sqlSession.getMapper(RecommendationMapper.class);
		if (answer.equals("대도시")){
			return recommendationMapper.large_City_Rank_TOP5();
		} else if (answer.equals("자연경관")){
			return recommendationMapper.natural_City_Rank_TOP5();
		} else if (answer.equals("유적지")){
			return recommendationMapper.historical_City_Rank_TOP5();
		} else {//상관없음 아직 구현 안함
			return null;
		}
	}


	@Override
	public ArrayList<City> searchByName(String loc_name) {
		RecommendationMapper recommendationMapper = sqlSession.getMapper(RecommendationMapper.class);
		loc_name = "%" + loc_name + "%";
		return recommendationMapper.searchByName(loc_name);
	}
}
