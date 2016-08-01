package com.crabstick.myapp.login;

public interface LoginService {
	void mem_join(Member m);
	int mem_login(Member m);
	int getmem_no(Member m);
	int getmem_id(String mem_id);
	String getmem_pass(Member m);
	Member getmem_all(int mem_no);
	void del_mem(int mem_no);
	int del_memchk(Member m);
}
