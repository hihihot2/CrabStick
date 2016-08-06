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
	public void mem_join(Member m) {
	LoginMapper loginmapper = sqlSession.getMapper(LoginMapper.class);
	loginmapper.insert(m);

	}

	@Override
	public int mem_login(Member m) {
		LoginMapper loginmapper = sqlSession.getMapper(LoginMapper.class);		
		return loginmapper.login(m);
	}

	@Override
	public int getmem_no(Member m) {
		LoginMapper loginmapper = sqlSession.getMapper(LoginMapper.class);		
		return loginmapper.selectno(m);
	}

	@Override
	public int getmem_id(String mem_id) {
		LoginMapper loginmapper = sqlSession.getMapper(LoginMapper.class);		
		return loginmapper.selectid(mem_id);
	}

	@Override
	public String getmem_pass(Member m) {
		LoginMapper loginmapper = sqlSession.getMapper(LoginMapper.class);
		return loginmapper.search_pass(m);
	}
	
	public Member getmem_all(int mem_no) {
		LoginMapper loginmapper = sqlSession.getMapper(LoginMapper.class);		
		return loginmapper.selectall(mem_no);
	}

	@Override
	public void del_mem(int mem_no) {
		LoginMapper loginmapper = sqlSession.getMapper(LoginMapper.class);		
		loginmapper.delmem(mem_no);
	}

	@Override
	public int del_memchk(Member m) {
		LoginMapper loginmapper = sqlSession.getMapper(LoginMapper.class);		
		return loginmapper.delchk(m);
	}

	@Override
	public void updatePass(Member m) {
		LoginMapper loginmapper = sqlSession.getMapper(LoginMapper.class);		
		loginmapper.updatePass(m);
	}

	@Override
	public void updateSurvey(String survey_Answer, int no) {
		LoginMapper loginmapper = sqlSession.getMapper(LoginMapper.class);
		Member m = new Member();
		m.setMem_no(no);
		m.setMem_survey(survey_Answer);
		loginmapper.updateSurvey(m);
	}

}
