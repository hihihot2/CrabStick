package com.crabstick.myapp.cont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

	@RequestMapping(value="/viewcont/plan.do", method = RequestMethod.GET)
	public String planStart(){
		System.out.println("viewcont >> planning");
		return "plan/step1";
	}
}
