package com.crabstick.myapp.cont;

import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.crabstick.api.expedia.Expedia;
import com.crabstick.api.expedia.objects.Hotel;
import com.crabstick.api.expedia.objects.Response;
import com.crabstick.api.foursquare.Foursquare;
import com.crabstick.api.foursquare.objects.Group;
import com.crabstick.myapp.recommendation.Attraction;

@Controller
public class PlaceController {
	private String foursquareClientId = "IT0NOIEI42Z0PT4XTEHTPECHU3TO1QFWKDHCNLHAEY1ESDZG";
	private String foursquareClientSecret = "OWVMGV1NWBD1ZWLT0TIWDKT4BOXHZD3YZTAJAX5UYLYK1BJO";
	private String expediaConsumerKey = "D9o6vpXU1ANf0zteRzFIAS83NkccKkoJ";
	private String expediaConsumerSecret = "14Y3QtDfkL4G58kJ";

	@RequestMapping(value="/placeCont/branch.do")
	public ModelAndView setBranch(@RequestParam(value="branch")int branch, @RequestParam(value="city_latitude")String lat, @RequestParam(value="city_longitude")String lng){
		System.out.println("placeCont >> setBranch");
		ModelAndView mav = null;
		if(branch == 0){ //호텔 파싱
			mav = new ModelAndView("plan/getHotelJSON");
			Expedia expedia = new Expedia(expediaConsumerKey, Expedia.API_HOTEL_SEARCH);
			expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_LATTITUDE, lat);
			expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_LONGITUDE, lng);
			expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_CHECK_IN_DATE, "2016-08-03");
			expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_CHECK_OUT_DATE, "2016-08-20");
			expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_ROOM1, "2");
			
			Response hotels = null;
			try {
				hotels = expedia.getHotels();			
			} catch (Exception e) {
				e.printStackTrace();
			}
			mav.addObject("HOTELS", hotels.getHotelList());
		}else if(branch == 1){ //맛집 파싱
			
		}else if(branch == 2){
			
		}else if(branch == 3){
			
		}
		return mav;
	}
	
	@RequestMapping(value="/placeCont/getRestaurants.do")
	public ModelAndView getRestaurants(@RequestParam(value="city_latitude") String city_latitude,@RequestParam(value="city_longitude") String city_longitude,
			@RequestParam(value="cityno") String cityno, @RequestParam(value="city_code") String code,
			@RequestParam(value="city_siguncode") String siguncode) {


		//Foursquare
		Foursquare foursquare = new Foursquare(foursquareClientId, foursquareClientSecret, Foursquare.API_EXPLORE);
		Expedia expedia = new Expedia(expediaConsumerKey, Expedia.API_HOTEL_SEARCH);
		System.out.println(city_latitude+","+city_longitude);
		foursquare.addField(Foursquare.EXPLORE_FIELD_LL, city_latitude+","+city_longitude);
		foursquare.addField(Foursquare.EXPLORE_FIELD_SECTION, Foursquare.PARAMETER_SECTION_FOOD);		
		foursquare.addField(Foursquare.EXPLORE_FIELD_RADIUS, "10000");

		//expedia
		expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_CITY, "SEOUL");
		expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_CHECK_IN_DATE, "2016-08-03");
		expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_CHECK_OUT_DATE, "2016-08-20");
		expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_ROOM1, "2");

		ArrayList<Group> venueGroups = null;
		Response hotels = null;
		ArrayList<Attraction> attraction_list = new ArrayList<Attraction>();
		/* 관광 명소 받아오는 XML 파싱 부분 */


		try {
			venueGroups = foursquare.getVenues();
			hotels = expedia.getHotels();			

			if (siguncode.equals("0")){ // siguncode -> 시군 정보 
				siguncode = "";
			}

			//URL접근
			Document document =DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=w7AsuB%2BGDEOxLnV40NaLBqqMrfwXHxoia3eDdF7U0gaeH%2Bdoxr%2BnTzd44cy25eqMTO23boo4lGvOboJp6Sa4CQ%3D%3D&contentTypeId=12&areaCode="+code+"&sigunguCode="+siguncode+"&cat1=A02&cat2=A0201&cat3=&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=200&pageNo=1");
			// xpath 생성
			XPath  xpath = XPathFactory.newInstance().newXPath();
			String expression = "//*/item"; //xml <item> </item> 노드 읽기
			NodeList item_Node = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);

			
			
			for( int idx=0; idx<item_Node.getLength()-1; idx++ ){

				Attraction attraction = new Attraction();
				
				item_Node.item(idx).setTextContent("item_"+idx);

				expression = "//*/title";
				String title = xpath.compile(expression).evaluate(document);
				attraction.setTitle(title);
				System.out.println(attraction.getTitle());
				
				expression = "//*/addr1";
				String addr1 = xpath.compile(expression).evaluate(document);
				attraction.setAddr1(addr1);
				
				expression = "//*/zipcode";
				String zipcode = xpath.compile(expression).evaluate(document);
				attraction.setZipcode(zipcode);
				
				expression = "//*/tel";
				String tel = xpath.compile(expression).evaluate(document);
				attraction.setTel(tel);
				
				expression = "//*/mapx";
				String _longitude = xpath.compile(expression).evaluate(document);
				attraction.setMapx(_longitude);
				
				expression = "//*/mapy";
				String _latitude = xpath.compile(expression).evaluate(document);
				attraction.setMapy(_latitude);

				attraction_list.add(attraction);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		/* Expedia 사용시, 반드시 아래의 4개의 필드는 설정되어야 함(Foursquare는 장소만 검색, Expedia는 실제 예약가능한 장소를 물색하기 때문인듯)
		Expedia expedia = new Expedia(expediaConsumerKey, Expedia.API_HOTEL_SEARCH);

		expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_CITY, "SEOUL");
		expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_CHECK_IN_DATE, "2016-08-03");
		expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_CHECK_OUT_DATE, "2016-08-04");
		expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_ROOM1, "2");

		try {
			Response response = expedia.getHotels();
			for(Hotel hotel : response.getHotelList()) {
				System.out.println("Hotel Name: " + hotel.getName() +", Address: " + hotel.getAddress());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 */

		ModelAndView mav = new ModelAndView("plan/showMap");
		mav.addObject("VENUES", venueGroups);
		mav.addObject("lat",city_latitude);
		mav.addObject("lang",city_longitude);
		mav.addObject("loc_no",cityno);
		mav.addObject("HOTELS", hotels.getHotelList());
		mav.addObject("Attractions", attraction_list);
		
		return mav;
	}


}
