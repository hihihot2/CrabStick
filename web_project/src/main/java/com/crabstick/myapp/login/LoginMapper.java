package com.crabstick.myapp.login;

public interface LoginMapper {
	void insert(Member m);
	int login(Member m);
	int selectno(Member m);
	int selectid(String mem_id);
	String search_pass(Member m);
	Member selectall(int mem_no);
	void delmem(int mem_no);
	int delchk(Member m);
	void updatePass(Member m);
}
 