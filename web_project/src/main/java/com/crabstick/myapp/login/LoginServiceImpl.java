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
/*		LoginMapper loginmapper = new 
*/
	}

	@Override
	public int mem_login(Members m) {
		// TODO Auto-generated method stub
		return 0;
	}

}
