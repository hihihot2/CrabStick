package com.crabstick.myapp.cont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

	/********** 동희 작업구역 **********/
	@RequestMapping(value="/viewcont/goToCity.do")
	public String goToCity(@RequestParam("city")String city) {
		return "redirect:/placeCont/getRestourants.do?city="+city;
	}
	
	@RequestMapping(value="/viewcont/startPlan.do")
	public String startPlan(){
		return "plan/startPlan";
	}
	/*******************************/
}
