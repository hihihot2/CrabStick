package com.crabstick.myapp.plan;

import java.util.ArrayList;

import com.crabstick.myapp.location.Location;

public interface PlanMapper {
	void insert(Plan p);
	
	//내가 작성한 plan전부 가져오기
	ArrayList<Plan> select(int mem_no);
	void update(Plan p);
	
	//한개 plan만 가져오기
	Plan selectplan(int plan_no);
	ArrayList<Plan> recent_select();
	
	void delete(int plan_no);
}
