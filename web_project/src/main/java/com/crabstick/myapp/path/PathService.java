package com.crabstick.myapp.path;

import java.util.ArrayList;


public interface PathService {
	void insertPath(Path pa);
	ArrayList<Path> selectPath(int plan_no);
	Path getPathByPathNo(int pathNo);
	void removePath(int pathNo);
}
