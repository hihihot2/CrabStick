package com.crabstick.myapp.cont;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
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
import com.crabstick.myapp.venue.Venue;
import com.crabstick.myapp.venue.VenueService;

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

	@Resource(name = "venueService")
	private VenueService venueService;
	public void setService(VenueService venueService) {
		this.venueService = venueService;
	}

	@Resource(name = "recommendationService")
	private RecommendationService recommendationService;

	public void setRecommendationService(RecommendationService recommendationService) {
		this.recommendationService = recommendationService;
	}

	@RequestMapping(value = "/")
	public ModelAndView main(HttpSession session, HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		String autoPlug="";
		String autoNo="";
		if (cookies!=null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("autoPlug")) {
					autoPlug = cookie.getValue();
				} else if (cookie.getName().equals("autoNo")) {
					autoNo = cookie.getValue();
					if (autoPlug.equals("true") ) {
						session.setAttribute("no", Integer.parseInt(autoNo));
					}
				}
			}
		}
		ModelAndView mav = new ModelAndView("main");
		// 아래로 최신계획정보 띄워주는거
		ArrayList<Plan> recent_plan = new ArrayList<Plan>(); 
		recent_plan = planService.recent_selectPlan();
		mav.addObject("recentPlan", recent_plan);
		return mav;
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

			int mem_id =  (Integer) httpSession.getAttribute("no") ;

			//TODO 회원 설문 결과 얻어오기
			Member member = service.getmem_all(mem_id); 
			String[] survey = member.getMem_survey().split(":");

			//TODO 설문 결과 값을 매개변수로 하여, 추천 도시 값 얻어오기



			ArrayList<City> recommend_City = recommendationService.recommendation_City(survey[1], 1);
			if (recommend_City.size()>=3){
				mav.addObject("city_List",recommend_City);
			}
			// mav.addObject("travel_purpose", survey[0]); //설문지 1번 (목적)
			mav.addObject("travel_accompany", survey[1]); //설문지 2번 (동행)
			// mav.addObject("favor_city", survey[2]);//설문지 3번(여행지 선호도)


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

		ArrayList<Plan> planList = planService.selectPlan(mem_no);

		for(Plan plan : planList) {
			plan.setPathlist(pathService.selectPath(plan.getPlan_no()));
			System.out.println(plan.getPlan_name());
			//			for(Path path : plan.getPathlist()) {
			//				System.out.println(path.getPath_summary());
			//			}
		}

		mav.addObject("PLANLIST" , planList);
		return mav;
	}	

	/*******************************/
	@RequestMapping(value="/viewCont/search.do")
	public ModelAndView searchLoc(@RequestParam(value="loc_name")String loc_name){
		ModelAndView mav =  new ModelAndView("plan/searchByResult");
		ArrayList<City> resultList = recommendationService.searchByName(loc_name);
		mav.addObject("resultList", resultList);
		return mav;
	}


	@RequestMapping(value="/viewCont/findCity.do")
	public String findLoc(@RequestParam(value="loc_name")String loc_name, @RequestParam(value="accompany")String accompany){
		System.out.println("검색시작");
		System.out.println(accompany);
		ArrayList<City> resultList = recommendationService.searchByName(loc_name);

		City resultCity = resultList.get(0);

		String lati = resultCity.getLoc_lati();
		String long2 = resultCity.getLoc_long();
		int weight_1 = resultCity.getLoc_ctweight(); // 가족
		int weight_2 = resultCity.getLoc_hiweight(); // 친구
		int weight_3 = resultCity.getLoc_ntweight(); // 연인
		System.out.println("DB 값 :"+ weight_1); 
		System.out.println("DB 값 :"+weight_2);
		System.out.println("DB 값 :"+weight_3);

		if (accompany.equals("가족")) {
			weight_1++;
			resultCity.setLoc_ctweight(weight_1);	
		} else if (accompany.equals("친구")){
			weight_2++;
			resultCity.setLoc_hiweight(weight_2);	
		} else if (accompany.equals("연인")){
			weight_3++;
			resultCity.setLoc_ntweight(weight_3);	
		} else {

		}
		System.out.println("DB 업뎃"+weight_1); 
		System.out.println("DB 업뎃"+weight_2);
		System.out.println("DB 업뎃"+weight_3);

		int result;

		double Accompany_Value_1 = (double)weight_1/(weight_1+weight_2+weight_3);
		double Accompany_Value_2 = (double)weight_2/(weight_1+weight_2+weight_3);
		double Accompany_Value_3 = (double)weight_3/(weight_1+weight_2+weight_3);
		if (Accompany_Value_1>Accompany_Value_2 && Accompany_Value_1>Accompany_Value_3){
			resultCity.setLoc_code("가족");
			recommendationService.update_Weight(resultCity);
		} else if (Accompany_Value_2>Accompany_Value_1 && Accompany_Value_2>Accompany_Value_3){
			resultCity.setLoc_code("친구");
			recommendationService.update_Weight(resultCity);			
		} else if (Accompany_Value_3>Accompany_Value_1 && Accompany_Value_3>Accompany_Value_2){
			resultCity.setLoc_code("연인");
			recommendationService.update_Weight(resultCity);
		} else { // 기존 카테고리 유지
			recommendationService.update_Weight(resultCity);
		}

		int locno = resultCity.getLoc_no();

		String url = "/placeCont/showMap.do?city_latitude="
				+ lati + "&city_longitude=" + long2
				+ "&cityno=" + locno;

		return "redirect:/" + url;
	}

}



