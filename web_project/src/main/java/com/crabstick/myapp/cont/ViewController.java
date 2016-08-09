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
import com.crabstick.myapp.path.Path;
import com.crabstick.myapp.path.PathService;
import com.crabstick.myapp.plan.Plan;
import com.crabstick.myapp.plan.PlanService;
import com.crabstick.myapp.recommendation.City;
import com.crabstick.myapp.recommendation.RecommendationService;

@Controller
public class ViewController {
	@Resource(name = "loginService")
	private LoginService service;

	public void setService(LoginService service) {
		this.service = service;
	}
	
	@Resource(name = "planService")
	private PlanService planService;
	public void setService(PlanService planService) {
		this.planService = planService;
	}
	@Resource(name = "pathService")
	private PathService pathService;
	public void setService(PathService pathService) {
		this.pathService = pathService;
	}
	
	
	@Resource(name = "recommendationService")
	private RecommendationService recommendationService;
	
	public void setRecommendationService(RecommendationService recommendationService) {
		this.recommendationService = recommendationService;
	}
	
	@RequestMapping(value = "/")
	public String main(HttpSession session, HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		String autoPlug="";
		String autoNo="";
		if (cookies!=null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("autoPlug")) {
					autoPlug = cookie.getValue();
				} else if (cookie.getName().equals("autoNo")) {
					autoNo = cookie.getValue();
				}
			}
			if (autoPlug.equals("true")) {
				session.setAttribute("no", Integer.parseInt(autoNo));
			}
		} else {
			return "main";
		}
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
		    ArrayList<City> city_List = recommendationService.recommendation_City_TOP5(survey[2]);
		    mav.addObject("city_List", city_List);
		    mav.addObject("travel_purpose", survey[0]);
		    return mav;
		} else { // (2) 비 로그인
			System.out.println("비로그인");
			return null;
		}
	}
	
	//나의 계획 페이지로 가기
	//세션값을 이용해 plan데이터를 갖고와서 넘긴다.
	@RequestMapping(value="/viewcont/showMyPlan.do")
	public ModelAndView myPlan(HttpSession httpSession){
		System.out.println("나의 계획페이지");
		ModelAndView mav =  new ModelAndView("plan/myPlan");		
		int mem_no = (Integer) httpSession.getAttribute("no");
		System.out.println(mem_no);
		
		ArrayList<Plan> plan = planService.selectPlan(mem_no);
		

		mav.addObject("plan", plan);
		
		return mav;

	}

	
	
	
	
	/*******************************/
	@RequestMapping(value="/viewCont/search.do")
	public ModelAndView searchLoc(@RequestParam(value="loc_name")String loc_name){
		ModelAndView mav =  new ModelAndView("plan/searchByResult");
		System.out.println("검색시작");
		ArrayList<City> resultList = recommendationService.searchByName(loc_name);
		System.out.println(resultList.toString());
		mav.addObject("resultList", resultList);
		return mav;
	}
	
	@RequestMapping(value="/viewCont/detailDOC.do")
	public ModelAndView windowShow(@RequestParam(value="loc_name")String loc_name){
		ModelAndView mav = new ModelAndView("plan/showLocation");
		System.out.println(loc_name);
		ArrayList<City> detailList = recommendationService.searchByName(loc_name);
		System.out.println(detailList.toString());
		mav.addObject("detailList", detailList);
		return mav;
	}
}
