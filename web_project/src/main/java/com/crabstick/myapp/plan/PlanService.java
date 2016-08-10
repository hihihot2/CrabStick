package com.crabstick.myapp.plan;

import java.util.ArrayList;

public interface PlanService {
	void insertPlan(Plan p);
	ArrayList<Plan> selectPlan(int mem_no);
	void updatePlan(Plan p);
	ArrayList<Plan> recent_selectPlan();
}
