package com.crabstick.myapp.plan;

import java.util.ArrayList;

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

	@Override
	public ArrayList<Plan> selectPlan(int mem_no) {
		PlanMapper planmapper = sqlSession.getMapper(PlanMapper.class);

		return planmapper.select(mem_no);
	}
	
	@Override
	public void updatePlan(Plan p) {
		PlanMapper planmapper = sqlSession.getMapper(PlanMapper.class);
		planmapper.update(p);	
	}

}
