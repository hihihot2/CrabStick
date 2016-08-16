package com.crabstick.myapp.recommendation;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.crabstick.myapp.venue.Venue;

@Component("recommendationService")
public class RecommendationServiceImpl implements RecommendationService {


	@Resource(name="sqlSession")
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}	

	@Override
	public ArrayList<City> searchByName(String loc_name) {
		RecommendationMapper recommendationMapper = sqlSession.getMapper(RecommendationMapper.class);
		loc_name = "%" + loc_name + "%";
		return recommendationMapper.searchByName(loc_name);
	}


	@Override
	public void update_Weight(City update_City) {
		RecommendationMapper recommendationMapper = sqlSession.getMapper(RecommendationMapper.class);
		recommendationMapper.update_Loc_Code(update_City);
	}


	@Override
	public ArrayList<City> All_City() {
		RecommendationMapper recommendationMapper = sqlSession.getMapper(RecommendationMapper.class);
		return recommendationMapper.select_All();
	}

	@Override
	public ArrayList<Integer> all_Transactions() {
		RecommendationMapper recommendationMapper = sqlSession.getMapper(RecommendationMapper.class);
		return recommendationMapper.select_All_Transaction();
	}

	@Override
	public ArrayList<Venue> all_Data() {
		RecommendationMapper recommendationMapper = sqlSession.getMapper(RecommendationMapper.class);
		return recommendationMapper.all_Data();
	}

	@Override
	public ArrayList<String> all_Sequence() {
		RecommendationMapper recommendationMapper = sqlSession.getMapper(RecommendationMapper.class);
		return recommendationMapper.all_Sequence();
	}

	


	
}
