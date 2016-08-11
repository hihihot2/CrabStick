package com.crabstick.myapp.path;

import java.util.ArrayList;

import com.crabstick.myapp.plan.Plan;

public interface PathMapper {
	void insert(Path pa);
	ArrayList<Path> select(int plan_no);
	Path selectByPathNo(int pathNo);
	void delete(int pathNo);
	void update(Path p);
	Path selectPathSummary(int plan_no);
	
}
