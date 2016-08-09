package com.crabstick.myapp.path;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.crabstick.myapp.plan.Plan;

@Component("pathService")
public class PathServiceImpl implements PathService {

	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}	
	
	@Override
	public void insertPath(Path pa) {
		PathMapper pathmapper = sqlSession.getMapper(PathMapper.class);
		pathmapper.insert(pa);
	}

	@Override
	public ArrayList<Path> selectPath(int plan_no) {
		PathMapper pathmapper = sqlSession.getMapper(PathMapper.class);
		return pathmapper.select(plan_no);
	}

}
