package com.crabstick.myapp.venue;

import java.util.ArrayList;

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
		VenueMapper venueMapper = sqlSession.getMapper(VenueMapper.class);
		venueMapper.insert(v);
	}
	


	@Override
	public ArrayList<Venue> selectVenue(int path_no) {
		VenueMapper Venuemapper = sqlSession.getMapper(VenueMapper.class);
		return Venuemapper.select(path_no);
	}

	@Override
	public void removeAllVenuesByPathNo(int pathNo) {
		VenueMapper Venuemapper = sqlSession.getMapper(VenueMapper.class);
		Venuemapper.deleteAllByPathNo(pathNo);		
	}
}
