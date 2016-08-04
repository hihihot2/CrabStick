package com.crabstick.myapp.cont;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
	
	@RequestMapping(value = "/")
	public String main(HttpSession session, HttpServletRequest req) {
		/*Cookie[] cookies = req.getCookies(); 
		String autoID = "";
		String autoPwd = "";
		String autoLogin = "";
		Member m = new Member();
		System.out.println(cookies[3]);
		if (cookies[3]) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("autoID")) {
					autoID = cookie.getValue();
				} else if (cookie.getName().equals("autoPwd")) {
					autoPwd = cookie.getValue();
				} else if (cookie.getName().equals("autoLogin")) {
					autoLogin = cookie.getValue();
				}
			}
			if (autoLogin.equals("ture")) {
				m.setMem_id(autoID);
				m.setMem_pwd(autoPwd);
				int no = service.getmem_no(m);
				session.setAttribute("no", no);
			}
		} else {
			return "main";
		}*/
		return "main";
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
