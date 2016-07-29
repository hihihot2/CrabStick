package com.crabstick.myapp.login;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}	

	@Override
	public void mem_join(Members m) {
	LoginMapper loginmapper = sqlSession.getMapper(LoginMapper.class);
	loginmapper.insert(m);

	}

	@Override
	public int mem_login(Members m) {
		LoginMapper loginmapper = sqlSession.getMapper(LoginMapper.class);		
		return loginmapper.login(m);
	}

	@Override
	public int getmem_no(Members m) {
		LoginMapper loginmapper = sqlSession.getMapper(LoginMapper.class);		
		return loginmapper.selectno(m);
	}

	@Override
	public int getmem_id(String mem_id) {
		LoginMapper loginmapper = sqlSession.getMapper(LoginMapper.class);		
		return loginmapper.selectid(mem_id);
	}

	@Override
	public Members getmem_all(int mem_no) {
		LoginMapper loginmapper = sqlSession.getMapper(LoginMapper.class);		
		return loginmapper.selectall(mem_no);
	}

	@Override
	public void del_mem(int mem_no) {
		LoginMapper loginmapper = sqlSession.getMapper(LoginMapper.class);		
		loginmapper.delmem(mem_no);
	}

	@Override
	public int del_memchk(Members m) {
		LoginMapper loginmapper = sqlSession.getMapper(LoginMapper.class);		
		return loginmapper.delchk(m);
	}

}
