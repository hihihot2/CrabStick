package com.crabstick.myapp.plan;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component("planService")
public class PlanServiceImpl implements PlanService {
	
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}	
	
	@Override
	public void insertPlan(Plan p) {
		PlanMapper planmapper = sqlSession.getMapper(PlanMapper.class);
		planmapper.insert(p);
	}

}