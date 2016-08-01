package com.crabstick.myapp.cont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PlanController {

	@RequestMapping(value="plancont/loc.do")
	public String locations(){
		return null;
	}
}
