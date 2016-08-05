package com.crabstick.myapp.venue;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component("venueService")
public class VenueServiceImpl implements VenueService {

	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}		
	
	@Override
	public void insertVenue(Venue v) {
		VenueMapper Venuemapper = sqlSession.getMapper(VenueMapper.class);
		Venuemapper.insert(v);
	}

}
