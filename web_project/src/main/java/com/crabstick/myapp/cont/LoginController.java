package com.crabstick.myapp.cont;

import javax.annotation.Resource;

import com.crabstick.myapp.login.LoginService;

public class LoginController {

	@Resource(name="LoginService")
	private LoginService service; 	
	
	public void setService(LoginService service) {
		this.service = service;
	}

	
	
}
