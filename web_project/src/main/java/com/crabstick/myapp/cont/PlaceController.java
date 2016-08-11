package com.crabstick.myapp.cont;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.crabstick.api.expedia.Expedia;
import com.crabstick.api.expedia.objects.Hotel;
import com.crabstick.api.expedia.objects.Response;
import com.crabstick.api.foursquare.Foursquare;
import com.crabstick.api.foursquare.objects.Group;
import com.crabstick.myapp.recommendation.Attraction;
import com.crabstick.myapp.venue.VenueService;

@Controller
public class PlaceController {
	private String foursquareClientId = "IT0NOIEI42Z0PT4XTEHTPECHU3TO1QFWKDHCNLHAEY1ESDZG";
	private String foursquareClientSecret = "OWVMGV1NWBD1ZWLT0TIWDKT4BOXHZD3YZTAJAX5UYLYK1BJO";
	private String expediaConsumerKey = "D9o6vpXU1ANf0zteRzFIAS83NkccKkoJ";
	private String expediaConsumerSecret = "14Y3QtDfkL4G58kJ";

	@RequestMapping(value="/placeCont/branch.do") // ajax 처리를 위한 함수
	public ModelAndView setBranch(@RequestParam(value="branch")int branch, 
			@RequestParam(value="city_latitude")String lat, @RequestParam(value="city_longitude")String lng,
			@RequestParam(value="city_code") String code,
			@RequestParam(value="siguncode") String siguncode){
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
			for (int i = 0; i<hotels.getHotelList().size(); i++){

				System.out.println("호텔 이름" + hotels.getHotelList().get(i).getName());
				System.out.println("호텔 평점" + hotels.getHotelList().get(i).getHotelGuestRating());
				System.out.println("호텔 좋아요 수" + hotels.getHotelList().get(i).getTotalRecommendations());
				System.out.println("호텔 리뷰 수" + hotels.getHotelList().get(i).getTotalReviews());			
				System.out.println("호텔 추천 비율" + hotels.getHotelList().get(i).getPercentRecommended());
				System.out.println("호텔 사진 주소" + Expedia.MEDIA_URL + hotels.getHotelList().get(i).getLargeThumbnailUrl());
				System.out.println("호텔 주소" + hotels.getHotelList().get(i).getAddress());


			}
			mav.addObject("HOTELS", hotels.getHotelList());

		}else if(branch == 1){ //맛집 파싱
			mav = new ModelAndView("plan/getFoodJSON");
			Foursquare foursquare = new Foursquare(foursquareClientId, foursquareClientSecret, Foursquare.API_EXPLORE);
			foursquare.addField(Foursquare.EXPLORE_FIELD_LL, lat+","+lng);
			foursquare.addField(Foursquare.EXPLORE_FIELD_SECTION, Foursquare.PARAMETER_SECTION_FOOD);		
			foursquare.addField(Foursquare.EXPLORE_FIELD_RADIUS, "10000");

			ArrayList<Group> venueGroups = null;
			try {
				venueGroups = foursquare.getVenues();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int i = 0; i<venueGroups.get(0).getItems().size(); i++){
				System.out.println("음식점 이름"+venueGroups.get(0).getItems().get(i).getName());
				System.out.println("음식점 웹 주소"+venueGroups.get(0).getItems().get(i).getUrl());
				System.out.println("음식점 시간"+venueGroups.get(0).getItems().get(i).getContact());
				System.out.println("평점"+venueGroups.get(0).getItems().get(i).getRating());	
			}
			mav.addObject("VENUES", venueGroups);
		}else if(branch == 2){ //명소 파싱

			mav = new ModelAndView("plan/getAttrJSON");

			if (siguncode.equals("0")){ // siguncode -> 시군 정보 
				siguncode = "";
			}
			ArrayList<Attraction> attraction_list = new ArrayList<Attraction>();

			//URL접근
			Document document;
			try {
				
				
				document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=w7AsuB%2BGDEOxLnV40NaLBqqMrfwXHxoia3eDdF7U0gaeH%2Bdoxr%2BnTzd44cy25eqMTO23boo4lGvOboJp6Sa4CQ%3D%3D&contentTypeId=38&areaCode="+code+"&sigunguCode="+siguncode+"&cat1=A04&cat2=A0401&cat3=A04010300&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=200&pageNo=1");
				
			/* 유적지 	*/
				//	고궁	URL 주소	http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=인증키&contentTypeId=12&areaCode=1&sigunguCode=&cat1=A02&cat2=A0201&cat3=A02010100&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1 
				//  성문 URL 주소 http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=인증키&contentTypeId=12&areaCode=1&sigunguCode=&cat1=A02&cat2=A0201&cat3=A02010300&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1 
				//  성 URL 주소 http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=인증키&contentTypeId=12&areaCode=1&sigunguCode=&cat1=A02&cat2=A0201&cat3=A02010200&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1 
				//  민속 마을 URL 주소 http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=인증키&contentTypeId=12&areaCode=1&sigunguCode=&cat1=A02&cat2=A0201&cat3=A02010600&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1 
				
			/* 휴식 */
				//  국립 공원 URL 주소 http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=인증키&contentTypeId=12&areaCode=1&sigunguCode=&cat1=A01&cat2=A0101&cat3=A01010100&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1 
				//  생태 관광지 URL 주소 http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=인증키&contentTypeId=12&areaCode=1&sigunguCode=&cat1=A01&cat2=A0101&cat3=A01010500&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1 
				//  섬 URL 주소 http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=인증키&contentTypeId=12&areaCode=39&sigunguCode=&cat1=A01&cat2=A0101&cat3=A01011300&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1
				//  테마 공원 URL 주소 http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=인증키&contentTypeId=12&areaCode=1&sigunguCode=&cat1=A02&cat2=A0202&cat3=A02020600&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1 
				//  유람선 URL 주소 http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=인증키&contentTypeId=12&areaCode=1&sigunguCode=&cat1=A02&cat2=A0202&cat3=A02020800&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1 
				//  공원 URL 주소 http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=인증키&contentTypeId=12&areaCode=1&sigunguCode=&cat1=A02&cat2=A0202&cat3=A02020700&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1 
				
			/* 쇼핑 */				
				//  면세점 URL 주소 http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=인증키&contentTypeId=38&areaCode=1&sigunguCode=&cat1=A04&cat2=A0401&cat3=A04010400&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1 
				//  백화점 URL 주소 http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=인증키&contentTypeId=38&areaCode=1&sigunguCode=&cat1=A04&cat2=A0401&cat3=A04010300&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1 
				
				
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
					// System.out.println(attraction.getTitle());

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

					expression = "//*/firstimage";
					String image_Url = xpath.compile(expression).evaluate(document);
					attraction.setImgURL(image_Url);

					System.out.println("명소 이름" + attraction.getTitle());
					System.out.println("명소 사진 URL" + attraction.getImgURL());

					attraction_list.add(attraction);
				}

			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XPathExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			mav.addObject("ATTR", attraction_list);

		}else if(branch == 3){ //백화점 or 면세점

			
		}
		mav.addObject("type", branch);
		return mav;
	}

	@RequestMapping(value="/placeCont/showMap.do")
	public ModelAndView showMap(@RequestParam(value="city_latitude") String city_latitude,
			@RequestParam(value="city_longitude") String city_longitude,
			@RequestParam(value="cityno") String cityno, 
			@RequestParam(value="city_code") String code,
			@RequestParam(value="city_siguncode") String siguncode){
		ModelAndView mav = new ModelAndView("plan/showMap");

		mav.addObject("lat",city_latitude);
		mav.addObject("lang",city_longitude);
		mav.addObject("loc_no",cityno);
		mav.addObject("city_code",code);
		mav.addObject("siguncode", siguncode);
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

		/* 관광 명소 받아오는 XML 파싱 부분 */


		try {
			venueGroups = foursquare.getVenues();
			hotels = expedia.getHotels();			

			if (siguncode.equals("0")){ // siguncode -> 시군 정보 
				siguncode = "";
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
		mav.addObject("isNewPlan", "true");

		return mav;
	}


}
