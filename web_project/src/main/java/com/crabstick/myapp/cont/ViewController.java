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
import com.crabstick.myapp.path.PathService;
import com.crabstick.myapp.plan.Plan;
import com.crabstick.myapp.plan.PlanService;
import com.crabstick.myapp.recommendation.City;
import com.crabstick.myapp.recommendation.RecommendationService;
import com.crabstick.myapp.recommendation.Weight_Loc;
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

	/*  Main */
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
			String[] purpose_Name = {"지역 문화 탐방","지역 음식 체험","쇼핑","휴식"};
			String[] accompany_Name = {"연인","가족","친구","혼자"};
			String[] city_Name = {"대도시","유적지","자연경관","상관없음"};

			ArrayList<City> All_City = recommendationService.All_City();
			
			
			int parentValue; //분모
			double childValue; //분자
			ArrayList<Double> Weight_Purpose = new ArrayList<Double>();
			ArrayList<Double> Weight_Accompany = new ArrayList<Double>();
			ArrayList<Double> Weight_City = new ArrayList<Double>();

			
			/* 가중치 계산 부분 */
			for (int i=0; i<purpose_Name.length; i++){

				if (survey[0].equals(purpose_Name[i])){
					for (int j=0; j<All_City.size(); j++){
						parentValue = All_City.get(j).getLoc_p_cult()+All_City.get(j).getLoc_p_food()+All_City.get(j).getLoc_p_shop()+All_City.get(j).getLoc_p_rest();

						if (parentValue!=0){
							if (i==0){
								childValue = (double)All_City.get(j).getLoc_p_cult();
							} else if (i==1){
								childValue = (double)All_City.get(j).getLoc_p_food();
							} else if (i==2){
								childValue = (double)All_City.get(j).getLoc_p_shop();
							} else {
								childValue = (double)All_City.get(j).getLoc_p_rest();
							}

							Weight_Purpose.add(childValue/parentValue);

						} else {
							Weight_Purpose.add(0.0);
						}

					}
				}

			}

			for (int i=0; i<accompany_Name.length; i++){
				if (survey[1].equals(accompany_Name[i])){
					for (int j=0; j<All_City.size(); j++){
						parentValue = All_City.get(j).getLoc_a_coup()+All_City.get(j).getLoc_a_fam()+All_City.get(j).getLoc_a_frnd()+All_City.get(j).getLoc_a_solo();

						if (parentValue!=0){
							if (i==0){
								childValue = (double)All_City.get(j).getLoc_a_coup();
							} else if (i==1){
								childValue = (double)All_City.get(j).getLoc_a_fam();
							} else if (i==2){
								childValue = (double)All_City.get(j).getLoc_a_frnd();
							} else {
								childValue = (double)All_City.get(j).getLoc_a_solo();
							}

							Weight_Accompany.add(childValue/parentValue);

						} else {
							Weight_Accompany.add(0.0);
						}

					}
				}
			}


			for (int i=0; i<city_Name.length; i++){
				if (survey[2].equals(city_Name[i])){
					for (int j=0; j<All_City.size(); j++){
						parentValue = All_City.get(j).getLoc_c_capt()+All_City.get(j).getLoc_c_his()+All_City.get(j).getLoc_c_natu();

						if (parentValue!=0){
							if (i==0){
								childValue = (double)All_City.get(j).getLoc_c_capt();
							} else if (i==1){
								childValue = (double)All_City.get(j).getLoc_c_his();
							} else if (i==2){
								childValue = (double)All_City.get(j).getLoc_c_natu();
							} else {
								childValue = parentValue;
							}

							Weight_City.add(childValue/parentValue);

						} else {
							Weight_City.add(0.0);
						}

					}
				}
			}
			/* 가중치 계산 부분 */
			
			
			/* 가중치에 따른 정렬 알고리즘*/
			for (int index=0; index<All_City.size(); index++){
				for (int index_2=index+1; index_2<All_City.size(); index_2++){
					
					double first = Weight_Purpose.get(index) * Weight_Accompany.get(index) * Weight_City.get(index);	
				    double second = Weight_Purpose.get(index_2) * Weight_Accompany.get(index_2) * Weight_City.get(index_2);
				    
				    if (first < second){
				    	double temp_P = Weight_Purpose.get(index); 
				    	double temp_A = Weight_Accompany.get(index);
				    	double temp_C = Weight_City.get(index);
				    	
				    	Weight_Purpose.set(index, Weight_Purpose.get(index_2));
				    	Weight_Accompany.set(index, Weight_Accompany.get(index_2));
				    	Weight_City.set(index, Weight_City.get(index_2));
				    	
				    	Weight_Purpose.set(index_2, temp_P);
				    	Weight_Accompany.set(index_2, temp_A);
				    	Weight_City.set(index_2, temp_C);
				    	
				    	City city_Temp = All_City.get(index);
				    	All_City.set(index, All_City.get(index_2));
				    	All_City.set(index_2, city_Temp); 
				    }
				}
			}
			/* 가중치에 따른 정렬 알고리즘*/
			ArrayList<City> recommend_City = new ArrayList<City>();
			for (int i=0; i<6; i++){
				recommend_City.add(All_City.get(i));
			}
			mav.addObject("city_List",recommend_City);
			mav.addObject("travel_purpose", survey[0]); //설문지 1번 (목적)
			mav.addObject("travel_accompany", survey[1]); //설문지 2번 (동행)
			mav.addObject("favor_city", survey[2]);//설문지 3번(여행지 선호도)
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
	public String findLoc(@RequestParam(value="loc_name")String loc_name,
			@RequestParam(value="accompany")String accompany,
			@RequestParam(value="purpose")String purpose,
			@RequestParam(value="favor_city")String favor_city){
		System.out.println("검색시작");
		ArrayList<City> resultList = recommendationService.searchByName(loc_name);

		City resultCity = resultList.get(0);
		City update_City = Weight_Loc.updateCity(resultCity , accompany , purpose , favor_city);
		recommendationService.update_Weight(update_City);
		
		String lati = resultCity.getLoc_lati();
		String long2 = resultCity.getLoc_long();


		int locno = resultCity.getLoc_no();

		String url = "/placeCont/showMap.do?city_latitude="
				+ lati + "&city_longitude=" + long2
				+ "&cityno=" + locno;

		return "redirect:/" + url;
	}


}



