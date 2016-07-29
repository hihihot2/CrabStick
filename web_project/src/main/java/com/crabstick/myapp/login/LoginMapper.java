package com.crabstick.myapp.login;

public interface LoginMapper {
	void insert(Members m);
	int login(Members m);
	int selectno(Members m);
	int selectid(String mem_id);
	String search_pass(Members m);
}
 