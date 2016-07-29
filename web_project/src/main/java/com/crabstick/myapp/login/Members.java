package com.crabstick.myapp.login;

public class Members {
	
	int mem_no;
	String mem_id;
	String mem_pwd;
	String mem_name;
	char mem_outchk;
	char mem_admchk;
	
	
	public Members() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Members(int mem_no, String mem_id, String mem_pwd, String mem_name, char mem_outchk, char mem_admchk) {
		super();
		this.mem_no = mem_no;
		this.mem_id = mem_id;
		this.mem_pwd = mem_pwd;
		this.mem_name = mem_name;
		this.mem_outchk = mem_outchk;
		this.mem_admchk = mem_admchk;
	}
	
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pwd() {
		return mem_pwd;
	}
	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public char getMem_outchk() {
		return mem_outchk;
	}
	public void setMem_outchk(char mem_outchk) {
		this.mem_outchk = mem_outchk;
	}
	public char getMem_admchk() {
		return mem_admchk;
	}
	public void setMem_admchk(char mem_admchk) {
		this.mem_admchk = mem_admchk;
	}

}
