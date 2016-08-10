package com.crabstick.myapp.plan;

import java.util.ArrayList;

public interface PlanMapper {
	void insert(Plan p);
	ArrayList<Plan> select(int mem_no);
	void update(Plan p);
	ArrayList<Plan> recent_select();
}
