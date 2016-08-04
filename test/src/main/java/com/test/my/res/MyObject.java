package com.test.myapp.res;

public class MyObject {
	private String column1;
	private String column2;
	private String column3;
	
	@Override
	public String toString() {
		return "myObject [column1=" + column1 + ", column2=" + column2 + ", column3=" + column3 + "]";
	}

	public MyObject(String column1, String column2, String column3) {
		super();
		this.column1 = column1;
		this.column2 = column2;
		this.column3 = column3;
	}

	public MyObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}
	
	
}
