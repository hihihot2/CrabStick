package com.crabstick.myapp.location;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component("locationService")
public class LocationServiceImpl implements LocationService {

	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public Location getLocationByPlanNo(int planNo) {
		LocationMapper locationMapper = sqlSession.getMapper(LocationMapper.class);
		return locationMapper.selectLocationByPlanNo(planNo);
	}
}
