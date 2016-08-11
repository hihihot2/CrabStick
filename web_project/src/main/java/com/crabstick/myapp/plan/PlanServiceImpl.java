package com.crabstick.myapp.plan;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.crabstick.myapp.location.Location;

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

	@Override
	public Plan getPlan(int plan_no) {
		PlanMapper planmapper = sqlSession.getMapper(PlanMapper.class);
		return planmapper.selectplan(plan_no);
	}
		
	public ArrayList<Plan> recent_selectPlan() {
		PlanMapper planmapper = sqlSession.getMapper(PlanMapper.class);
		return planmapper.recent_select();
	}
	
	@Override
	public Location getLocationByPlanNo(int planNo) {
		PlanMapper planmapper = sqlSession.getMapper(PlanMapper.class);
		return planmapper.selectLocationByPlanNo(planNo);
	}

	

}
