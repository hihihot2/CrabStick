package com.crabstick.myapp.plan;

import java.sql.Date;
import java.util.ArrayList;

import com.crabstick.myapp.path.Path;

public class Plan {

	private int plan_no;
	private String plan_name;
	private String plan_commt;
	private int plan_cost;
	private int plan_persons;
	private Date plan_writedate;
	private char plan_style;
	private int mem_no;
	private ArrayList<Path> pathlist;	
	
	
	
	
	public Plan(int plan_no, String plan_name, String plan_commt, int plan_cost, int plan_persons, Date plan_writedate,
			char plan_style, int mem_no, ArrayList<Path> pathlist) {
		super();
		this.plan_no = plan_no;
		this.plan_name = plan_name;
		this.plan_commt = plan_commt;
		this.plan_cost = plan_cost;
		this.plan_persons = plan_persons;
		this.plan_writedate = plan_writedate;
		this.plan_style = plan_style;
		this.mem_no = mem_no;
		this.pathlist = pathlist;
	}
	public ArrayList<Path> getPathlist() {
		return pathlist;
	}
	public void setPathlist(ArrayList<Path> pathlist) {
		this.pathlist = pathlist;
	}

	public Plan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPlan_no() {
		return plan_no;
	}
	public void setPlan_no(int plan_no) {
		this.plan_no = plan_no;
	}
	public String getPlan_name() {
		return plan_name;
	}
	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}
	public String getPlan_commt() {
		return plan_commt;
	}
	public void setPlan_commt(String plan_commt) {
		this.plan_commt = plan_commt;
	}
	public int getPlan_cost() {
		return plan_cost;
	}
	public void setPlan_cost(int plan_cost) {
		this.plan_cost = plan_cost;
	}
	public int getPlan_persons() {
		return plan_persons;
	}
	public void setPlan_persons(int plan_persons) {
		this.plan_persons = plan_persons;
	}
	public Date getPlan_writedate() {
		return plan_writedate;
	}
	public void setPlan_writedate(Date plan_writedate) {
		this.plan_writedate = plan_writedate;
	}
	public char getPlan_style() {
		return plan_style;
	}
	public void setPlan_style(char plan_style) {
		this.plan_style = plan_style;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	
	
	
	
	
}
