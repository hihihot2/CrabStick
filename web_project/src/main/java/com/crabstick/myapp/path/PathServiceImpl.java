package com.crabstick.myapp.path;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component("pathService")
public class PathServiceImpl implements PathService {

	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}	
	
	@Override
	public void insertPath(Path pa) {
		PathMapper pathMapper = sqlSession.getMapper(PathMapper.class);
		pathMapper.insert(pa);
	}

	@Override
	public ArrayList<Path> selectPath(int plan_no) {
		PathMapper pathMapper = sqlSession.getMapper(PathMapper.class);
		return pathMapper.select(plan_no);
	}

	@Override
	public Path getPathByPathNo(int pathNo) {
		PathMapper pathMapper = sqlSession.getMapper(PathMapper.class);
		return pathMapper.selectByPathNo(pathNo);
	}
	
	@Override
	public void removePath(int pathNo) {
		PathMapper pathMapper = sqlSession.getMapper(PathMapper.class);
		pathMapper.delete(pathNo);
	}

	@Override
	public void updatePath(Path p) {
		PathMapper pathMapper = sqlSession.getMapper(PathMapper.class);
		pathMapper.update(p);
	}

	@Override
	public Path getPathSummary(int plan_no) {
		PathMapper pathMapper = sqlSession.getMapper(PathMapper.class);
		return pathMapper.selectPathSummary(plan_no);
	}
	
	
}
