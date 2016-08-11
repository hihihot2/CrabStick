package com.crabstick.myapp.plan;

import java.util.ArrayList;

import com.crabstick.myapp.location.Location;

public interface PlanService {
	void insertPlan(Plan p);
	//내가 작성한 plan전부 가져오기
	ArrayList<Plan> selectPlan(int mem_no);
	
	void updatePlan(Plan p);
	
	//한개 plan만 가져오기
	Plan getPlan(int plan_no);
	ArrayList<Plan> recent_selectPlan();
	Location getLocationByPlanNo(int planNo);
}
