package com.crabstick.myapp.cont;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crabstick.myapp.login.LoginService;
import com.crabstick.myapp.login.Member;
import com.crabstick.myapp.recommendation.City;
import com.crabstick.myapp.recommendation.RecommendationService;

@Controller
public class ViewController {
	@Resource(name = "loginService")
	private LoginService service;

	public void setService(LoginService service) {
		this.service = service;
	}
	
	@Resource(name = "recommendationService")
	private RecommendationService recommendationService;
	
	public void setRecommendationService(RecommendationService recommendationService) {
		this.recommendationService = recommendationService;
	}
	
	
	
	/********** 동희 작업구역 **********/
	@RequestMapping(value="/viewcont/goToCity.do")
	public String goToCity(@RequestParam("city")String city) {
		return "redirect:/placeCont/getRestourants.do?city="+city;
	}
	
	
	//TODO 플래너 시작하기
	@RequestMapping(value="/viewcont/startPlan.do")
	public ModelAndView startPlan(HttpSession httpSession){

		ModelAndView mav =  new ModelAndView("plan/startPlan");
		
		if (httpSession.getAttribute("no")!=null) { // (1) 로그인
			System.out.println("로그인");
			int mem_id =  (Integer) httpSession.getAttribute("no") ;
			
			//TODO 회원 설문 결과 얻어오기
			Member member = service.getmem_all(mem_id); 
		    String[] survey = member.getMem_survey().split(":");
		    
		    //TODO 설문 결과 값을 매개변수로 하여, 추천 도시 값 얻어오기
		    ArrayList<City> city_List = recommendationService.recommendation_City(survey[2]);
		    
		    mav.addObject("city_List", city_List);
		    return mav;
		} else { // (2) 비 로그인
			System.out.println("비로그인");
			return null;
		}
		

		
		
	}
	/*******************************/
}
