package com.crabstick.myapp.login;

public interface LoginService {
	void mem_join(Members m);
	int mem_login(Members m);
	int getmem_no(Members m);
	int getmem_id(String mem_id);
	String getmem_pass(Members m);
}
