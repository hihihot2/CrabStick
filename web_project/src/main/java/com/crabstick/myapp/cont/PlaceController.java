package com.crabstick.myapp.cont;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.poi.hssf.util.HSSFColor.GOLD;
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
import com.crabstick.api.foursquare.objects.Venue;
import com.crabstick.myapp.location.Location;
import com.crabstick.myapp.location.LocationService;
import com.crabstick.myapp.login.LoginService;
import com.crabstick.myapp.login.Member;
import com.crabstick.myapp.path.Path;
import com.crabstick.myapp.path.PathService;
import com.crabstick.myapp.plan.Plan;
import com.crabstick.myapp.plan.PlanService;
import com.crabstick.myapp.recommendation.Attraction;
import com.crabstick.myapp.recommendation.RecommendationService;
import com.crabstick.myapp.venue.VenueService;

@Controller
public class PlaceController {
	private String foursquareClientId = "IT0NOIEI42Z0PT4XTEHTPECHU3TO1QFWKDHCNLHAEY1ESDZG";
	private String foursquareClientSecret = "OWVMGV1NWBD1ZWLT0TIWDKT4BOXHZD3YZTAJAX5UYLYK1BJO";
	private String expediaConsumerKey = "D9o6vpXU1ANf0zteRzFIAS83NkccKkoJ";
	private String expediaConsumerSecret = "14Y3QtDfkL4G58kJ";

	@Resource(name = "loginService")
	private LoginService service;
	public void setService(LoginService service) {
		this.service = service;
	}

	@Resource(name = "locationService")
	private LocationService locationService;
	public void setService(LocationService locationService) {
		this.locationService = locationService;
	}

	@Resource(name = "venueService")
	private VenueService venueService;
	public void setService(VenueService venueService) {
		this.venueService = venueService;
	}

	@Resource(name = "pathService")
	private PathService pathService;
	public void setService(PathService pathService) {
		this.pathService = pathService;
	}

	@Resource(name = "planService")
	private PlanService planService;
	public void setService(PlanService planService) {
		this.planService = planService;
	}

	@Resource(name = "recommendationService")
	private RecommendationService recommendationService;
	public void setRecommendationService(RecommendationService recommendationService) {
		this.recommendationService = recommendationService;
	}


	/**
	 * session이 있는 경우 컨트롤러
	 * @param lat 위도
	 * @param lng 경도
	 * @param radius 반경
	 * @return mav
	 * 
	 * 
	 * 익스피디아, 포스퀘어, 한국관광공사 API 접근 
	 */
	@RequestMapping(value="/placeCont/getRecommandPlacesHasSession.do")
	public ModelAndView getRecommandPlaces(@RequestParam(value="lat")String lat,
			@RequestParam(value="lng")String lng,
			@RequestParam(value="radius")String radius,
			@RequestParam(value="order")int order,
			@RequestParam(value="mem_no")int mem_no,
			@RequestParam(value="type")String type) {

		if (type.equals("")){
			type = "noValue";
		}
		
		

		ModelAndView mav = new ModelAndView("plan/getRecommandPlacesJSON");
		ArrayList<Attraction> recommandList = new ArrayList<Attraction>();
		//String key = "w7AsuB%2BGDEOxLnV40NaLBqqMrfwXHxoia3eDdF7U0gaeH%2Bdoxr%2BnTzd44cy25eqMTO23boo4lGvOboJp6Sa4CQ%3D%3D";
		//String key = "%2BzkCsJG8T4Mc408ug306EphfPVrmOHMSC9eY52USE%2BzMmV4OZ4%2Fzpzlqh220vkBb9fJAE1am%2B0LtDr%2FAzs2UIA%3D%3D";
		String key = "t%2FSK%2Brzp5k8nLo7iyovH4M0zZFOdkA8BYVCtjz3k%2BnKAb6MFSz1Eg%2FoZSCOhimTDxDRFSRgHVF1Kw3b2NtlieA%3D%3D";

		/* session을 통한 설문지 처리 */
		Member member = service.getmem_all(mem_no); 
		String[] survey = member.getMem_survey().split(":");
		/* session을 통한 설문지 처리 */

		/* DB 데이터 분석 부분 */		
		ArrayList<Integer> transaction_List = recommendationService.all_Transactions();
		ArrayList<String> sequence_List = recommendationService.all_Sequence();
		ArrayList<com.crabstick.myapp.venue.Venue> Venue_Table_Data = recommendationService.all_Data();
		Apriori apriori = new Apriori();
		apriori.init_Path(mem_no, order);
		ArrayList<String> recommend_Venue_List = apriori.apriori_Algorithm(transaction_List, sequence_List, Venue_Table_Data, order, lat+":"+lng, mem_no);
		/* DB 데이터 분석 부분 */

		// TODO 호텔 가져오기
		if(order!=0){
			if (!type.equals("0")){
				Expedia expedia = new Expedia(expediaConsumerKey, Expedia.API_HOTEL_SEARCH);
				expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_LATTITUDE, lat);
				expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_LONGITUDE, lng);

				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				calendar.add(Calendar.DAY_OF_MONTH, 100);
				expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_CHECK_IN_DATE, sdf.format(calendar.getTime()));
				calendar.add(Calendar.DAY_OF_MONTH, 2);
				expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_CHECK_OUT_DATE, sdf.format(calendar.getTime()));
				if (survey[1].equals("혼자")){
					expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_ROOM1, "1");	
				} else if (survey[1].equals("가족")){
					expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_ROOM1, "4");
				} else {
					expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_ROOM1, "2");
				}
				expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_RESULTS_PER_PAGE, "10");
				expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_SORT_ORDER, "true");

				Response hotels = null;

				try {
					/* 모든 호텔 정보 */
					hotels = expedia.getHotels();
					List<Hotel> parsing_List = hotels.getHotelList();
					List<Hotel> refreshing_List = new ArrayList<Hotel>();

					for (Hotel hotel: parsing_List){
						boolean isMatched = false;
						if (hotel.getHotelGuestRating()!=null){
							for (String info: recommend_Venue_List){
								if ( (hotel.getLatitude()+":"+hotel.getLongitude()).equals(info)){
									Attraction attr = new Attraction();
									attr.setAddr1(hotel.getAddress());
									attr.setTitle(hotel.getName()+"(평점 : "+hotel.getHotelGuestRating()+"/5점)");
									attr.setMapy(hotel.getLatitude());
									attr.setMapx(hotel.getLongitude());
									recommandList.add(attr);
									isMatched = true;
								}
							}
							if (!isMatched){
								if (!apriori.check_Path(hotel.getLatitude()+":"+hotel.getLongitude())){
									String name_Merge_Rating = hotel.getName()+"(평점 : "+hotel.getHotelGuestRating()+"/5점)";
									hotel.setName(name_Merge_Rating);
									refreshing_List.add(hotel);
								}
							}

						}
					}

					hotels.setHotelList((ArrayList<Hotel>)refreshing_List);
				} catch (Exception e) {
					e.printStackTrace();
				}
				mav.addObject("HOTELS", hotels);
			} else {
			}
		}


		// TODO 맛집 가져오기
		if (order!=0 || (order==0&survey[0].equals("지역 음식 체험"))){
			if (!type.equals("1")){
				Foursquare foursquare = new Foursquare(foursquareClientId, foursquareClientSecret, Foursquare.API_EXPLORE);
				foursquare.addField(Foursquare.EXPLORE_FIELD_LL, lat+","+lng);
				foursquare.addField(Foursquare.EXPLORE_FIELD_SECTION, Foursquare.PARAMETER_SECTION_FOOD);
				if (order==0){
					String new_Radius = Integer.toString((Integer.parseInt(radius)*3));
					foursquare.addField(Foursquare.EXPLORE_FIELD_RADIUS, new_Radius);
					foursquare.addField(Foursquare.EXPLORE_FIELD_LIMIT, "40");
				} else {
					foursquare.addField(Foursquare.EXPLORE_FIELD_RADIUS, radius);
					foursquare.addField(Foursquare.EXPLORE_FIELD_LIMIT, "10");
				}
				ArrayList<Venue> venues = null;

				try {
					venues = new ArrayList<Venue>();
					ArrayList<Group> venueGroups = foursquare.getVenues();
					for(Group group : venueGroups) {
						for(Venue venue : group.getItems()) {
							/* 모든 맛집 정보 */
							boolean isMatched = false;
							if (venue.getRating() != null){
								for (String info: recommend_Venue_List){
									if ( (venue.getLocation().getLat()+":"+venue.getLocation().getLng()).equals(info)){
										Attraction attr = new Attraction();
										attr.setAddr1(venue.getLocation().getAddress());
										attr.setTitle(venue.getName()+"(평점 : "+venue.getRating()+"/10점)");
										attr.setMapy(Double.toString(venue.getLocation().getLat()));
										attr.setMapx(Double.toString(venue.getLocation().getLng()));
										recommandList.add(attr);
										isMatched = true;
									}
								}
								if (!isMatched){
									if (!apriori.check_Path( Double.toString(venue.getLocation().getLat())+":"+Double.toString(venue.getLocation().getLng())) ){
										String name_Merge_Rating = venue.getName()+"(평점 : "+venue.getRating()+"/10점)";
										venue.setName(name_Merge_Rating);
										venues.add(venue);
									}
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
				mav.addObject("VENUES", venues);
			} else {
			}
		}

		ArrayList<Attraction> attraction_List = new ArrayList<Attraction>();
		ArrayList<Attraction> attraction_List_Nature = new ArrayList<Attraction>();
		ArrayList<Attraction> attraction_List_Rest = new ArrayList<Attraction>();

		/* XML 파싱 부분 */
		Document document;
		try {
			// xpath 생성
			XPath  xpath = XPathFactory.newInstance().newXPath();
			if (order==0){
				document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
						"http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=12&mapX="+lng+"&mapY="+lat+"&radius="+radius+"&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=100&pageNo=1");
			} else {
				String new_Radius = Integer.toString((Integer.parseInt(radius)*3));
				document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
						"http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=12&mapX="+lng+"&mapY="+lat+"&radius="+new_Radius+"&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=40&pageNo=1");
			}
			String expression = "//*/item";
			NodeList item_Node = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);
			if (item_Node.getLength()>0){
				for( int idx=-1; idx<item_Node.getLength()-1; idx++ ){// -1 부터 시작하는 이유는 맨 처음 item에 setTextContent 를 부여 못하기때문
					Attraction attraction = new Attraction();
					if (idx>=0){
						item_Node.item(idx).setTextContent("item_"+idx);
					}

					expression = "//*/cat3";
					String cat3 = xpath.compile(expression).evaluate(document);

					/* TODO 명소 받아오기 */
					if (order!=0 || (order==0&survey[0].equals("지역 문화 탐방")) || (order==0&survey[2].equals("유적지"))){

						if (cat3.equals("A02010100") || cat3.equals("A02010200") || cat3.equals("A02010300") || cat3.equals("A02010400") || cat3.equals("A02010900") || cat3.equals("A02010600") || cat3.equals("A02010700")){
							expression = "//*/title";
							String title = xpath.compile(expression).evaluate(document);
							attraction.setTitle(title);

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
							
							
							boolean isMatched = false;
							for (String info: recommend_Venue_List){
								if ((_latitude+":"+_longitude).equals(info)){
									Attraction attr = new Attraction();
									attr.setAddr1(addr1);
									attr.setTitle(title+"("+tel+")");
									attr.setMapy(_latitude);
									attr.setMapx(_longitude);
									recommandList.add(attr);
									isMatched = true;
								}
							}
							if (!isMatched){
								if (!apriori.check_Path(_latitude+":"+_longitude)){
									attraction_List.add(attraction);
								}
							}

							if (attraction_List.size()==15 && order!=0){
								break;
							}
						}

					}
					/* TODO 자연 불러오기 */
					if (order!=0 || (order==0&survey[2].equals("자연경관"))){
						if (cat3.equals("A01010100") || cat3.equals("A01010200") || cat3.equals("A01010400") || cat3.equals("A01010600") || cat3.equals("A01010700") || cat3.equals("A01010800") || cat3.equals("A01010900") || cat3.equals("A01011000") || cat3.equals("A01011100") || cat3.equals("A01011200") || cat3.equals("A01011300") || cat3.equals("A01011400")){
							expression = "//*/title";
							String title = xpath.compile(expression).evaluate(document);
							attraction.setTitle(title);

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

							boolean isMatched = false;
							for (String info: recommend_Venue_List){
								if ((_latitude+":"+_longitude).equals(info)){
									Attraction attr = new Attraction();
									attr.setAddr1(addr1);
									attr.setTitle(title+"("+tel+")");
									attr.setMapy(_latitude);
									attr.setMapx(_longitude);
									recommandList.add(attr);
									isMatched = true;
								}
							}
							if (!isMatched){
								if (!apriori.check_Path(_latitude+":"+_longitude)){
									attraction_List_Nature.add(attraction);
								}
							}

							if (attraction_List_Nature.size()==15 && order!=0){
								break;
							}
						}
					}
					/* TODO 휴식 카테고리 */
					if (order!=0 || (order==0&survey[0].equals("휴식"))){
						if (cat3.equals("A02020100") || cat3.equals("A02020200") || cat3.equals("A02020300") || cat3.equals("A02020600") || cat3.equals("A02020700")){
							expression = "//*/title";
							String title = xpath.compile(expression).evaluate(document);
							attraction.setTitle(title);

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

							
							
							boolean isMatched = false;
							for (String info: recommend_Venue_List){
								if ((_latitude+":"+_longitude).equals(info)){
									Attraction attr = new Attraction();
									attr.setAddr1(addr1);
									attr.setTitle(title+"("+tel+")");
									attr.setMapy(_latitude);
									attr.setMapx(_longitude);
									recommandList.add(attr);
									isMatched = true;
								}
							}
							if (!isMatched){
								if (!apriori.check_Path(_latitude+":"+_longitude)){
									attraction_List_Rest.add(attraction);
								}
							}

							
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if (attraction_List.size()!=0){
			mav.addObject("SIGHTS", attraction_List);	
		}
		if (attraction_List_Nature.size()!=0){
			mav.addObject("NATURES", attraction_List_Nature);		
		}
		if (attraction_List_Rest.size()!=0){
			mav.addObject("RESTS", attraction_List_Rest);	
		}

		/* TODO 쇼핑 센터 불러오기 */
		if (order!=0 || (order==0&survey[0].equals("쇼핑"))) {
			if (!type.equals("3")){
				ArrayList<Attraction> attraction_List_Shopping = new ArrayList<Attraction>();
				/* XML 파싱 부분 */
				try {
					// xpath 생성
					if(order==0){
						document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
								"http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=38&mapX="+lng+"&mapY="+lat+"&radius="+radius+"&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=200&pageNo=1");	
					} else {
						String new_Radius = Integer.toString((Integer.parseInt(radius)*3));
						document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
								"http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=38&mapX="+lng+"&mapY="+lat+"&radius="+new_Radius+"&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=30&pageNo=1");
					}
					XPath  xpath = XPathFactory.newInstance().newXPath();
					String expression = "//*/item";
					NodeList item_Node = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);

					if (item_Node.getLength()>0){

						for( int idx=-1; idx<item_Node.getLength()-1; idx++ ){// -1 부터 시작하는 이유는 맨 처음 item에 setTextContent 를 부여 못하기때문

							Attraction attraction = new Attraction();

							if (idx>=0){
								item_Node.item(idx).setTextContent("item_"+idx);
							}

							expression = "//*/cat3";
							String cat3 = xpath.compile(expression).evaluate(document);

							if (cat3.equals("A04010300") || cat3.equals("A04010400") || cat3.equals("A04010800") ){
								expression = "//*/title";
								String title = xpath.compile(expression).evaluate(document);
								attraction.setTitle(title);

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

								boolean isMatched = false;
								for (String info: recommend_Venue_List){
									if ((_latitude+":"+_longitude).equals(info)){
										Attraction attr = new Attraction();
										attr.setAddr1(addr1);
										attr.setTitle(title+"("+tel+")");
										attr.setMapy(_latitude);
										attr.setMapx(_longitude);
										recommandList.add(attr);
										isMatched = true;
									}
								}
								if (!isMatched){
									if (!apriori.check_Path(_latitude+":"+_longitude)){
										attraction_List_Shopping.add(attraction);
									}
								}

								if (attraction_List_Shopping.size()==10 && order!=0){
									break;
								}
								
							}
						}
					}
					System.out.println("쇼핑센터 사이즈:"+attraction_List_Shopping.size());
				} catch (Exception e) {
					e.printStackTrace();
				} 
				mav.addObject("SHOPPINGS", attraction_List_Shopping);
			}
		}

		mav.addObject("RECOMMENDS", recommandList);
		return mav;
	}


	/**************************************************/
	/**************************************************/
	/**************************************************/
	/**************************************************/


	/**
	 * session이 없는 경우 컨트롤러
	 * @param lat 위도
	 * @param lng 경도
	 * @param radius 반경
	 * @return mav
	 * 
	 * 
	 * 익스피디아, 포스퀘어, 한국관광공사 API 접근 
	 */
	@RequestMapping(value="/placeCont/getRecommandPlaces.do")
	public ModelAndView getRecommandPlaces(@RequestParam(value="lat")String lat, @RequestParam(value="lng")String lng, @RequestParam(value="radius")String radius, @RequestParam(value="order")int order) {


		// 호텔 가져오기
		Expedia expedia = new Expedia(expediaConsumerKey, Expedia.API_HOTEL_SEARCH);
		expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_LATTITUDE, lat);
		expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_LONGITUDE, lng);

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendar.add(Calendar.DAY_OF_MONTH, 100);
		expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_CHECK_IN_DATE, sdf.format(calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, 2);
		expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_CHECK_OUT_DATE, sdf.format(calendar.getTime()));

		expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_ROOM1, "2");
		expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_RESULTS_PER_PAGE, "10");
		expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_SORT_ORDER, "true");

		Response hotels = null;
		try {
			/* 모든 호텔 정보 */
			hotels = expedia.getHotels();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 맛집 가져오기
		Foursquare foursquare = new Foursquare(foursquareClientId, foursquareClientSecret, Foursquare.API_EXPLORE);
		foursquare.addField(Foursquare.EXPLORE_FIELD_LL, lat+","+lng);
		foursquare.addField(Foursquare.EXPLORE_FIELD_SECTION, Foursquare.PARAMETER_SECTION_FOOD);		
		foursquare.addField(Foursquare.EXPLORE_FIELD_RADIUS, radius);
		foursquare.addField(Foursquare.EXPLORE_FIELD_LIMIT, "10");
		ArrayList<Venue> venues = null;

		try {
			venues = new ArrayList<Venue>();
			ArrayList<Group> venueGroups = foursquare.getVenues();
			for(Group group : venueGroups) {
				for(Venue venue : group.getItems()) {
					/* 모든 맛집 정보 */
					venues.add(venue);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		/* 명소 받아오기 */
		//발급 받은 서비스키
		String key = "w7AsuB%2BGDEOxLnV40NaLBqqMrfwXHxoia3eDdF7U0gaeH%2Bdoxr%2BnTzd44cy25eqMTO23boo4lGvOboJp6Sa4CQ%3D%3D";
		//		String key = "%2BzkCsJG8T4Mc408ug306EphfPVrmOHMSC9eY52USE%2BzMmV4OZ4%2Fzpzlqh220vkBb9fJAE1am%2B0LtDr%2FAzs2UIA%3D%3D";
		//String key = "t%2FSK%2Brzp5k8nLo7iyovH4M0zZFOdkA8BYVCtjz3k%2BnKAb6MFSz1Eg%2FoZSCOhimTDxDRFSRgHVF1Kw3b2NtlieA%3D%3D";
		ArrayList<Attraction> attraction_List = new ArrayList<Attraction>();

		/* XML 파싱 부분 */
		Document document;
		try {
			// xpath 생성
			XPath  xpath = XPathFactory.newInstance().newXPath();

			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
					"http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=12&mapX="+lng+"&mapY="+lat+"&radius="+radius+"&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=10&pageNo=1");
			String expression = "//*/item";
			NodeList item_Node = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);

			if (item_Node.getLength()>0){

				for( int idx=-1; idx<item_Node.getLength()-1; idx++ ){// -1 부터 시작하는 이유는 맨 처음 item에 setTextContent 를 부여 못하기때문

					Attraction attraction = new Attraction();

					if (idx>=0){
						item_Node.item(idx).setTextContent("item_"+idx);
					}

					expression = "//*/cat3";
					String cat3 = xpath.compile(expression).evaluate(document);

					if (cat3.equals("A02010100") || cat3.equals("A02010200") || cat3.equals("A02010300") || cat3.equals("A02010400") || cat3.equals("A02010900") || cat3.equals("A02010600") || cat3.equals("A02010700")){
						expression = "//*/title";
						String title = xpath.compile(expression).evaluate(document);
						attraction.setTitle(title);

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

						attraction_List.add(attraction);
						if (attraction_List.size()==20){
							break;
						}
					}
				}
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

		/* 자연 불러오기 */


		ArrayList<Attraction> attraction_List_Nature = new ArrayList<Attraction>();
		try {

			// xpath 생성
			XPath  xpath = XPathFactory.newInstance().newXPath();

			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
					"http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=12&mapX="+lng+"&mapY="+lat+"&radius="+radius+"&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=10&pageNo=1");
			String expression = "//*/item";
			NodeList item_Node = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);

			if (item_Node.getLength()>0){

				for( int idx=-1; idx<item_Node.getLength()-1; idx++ ){// -1 부터 시작하는 이유는 맨 처음 item에 setTextContent 를 부여 못하기때문

					Attraction attraction = new Attraction();

					if (idx>=0){
						item_Node.item(idx).setTextContent("item_"+idx);
					}

					expression = "//*/cat3";
					String cat3 = xpath.compile(expression).evaluate(document);

					if (cat3.equals("A01010100") || cat3.equals("A01010200") || cat3.equals("A01010400") || cat3.equals("A01010600") || cat3.equals("A01010700") || cat3.equals("A01010800") || cat3.equals("A01010900") || cat3.equals("A01011000") || cat3.equals("A01011100") || cat3.equals("A01011200") || cat3.equals("A01011300") || cat3.equals("A01011400")){
						expression = "//*/title";
						String title = xpath.compile(expression).evaluate(document);
						attraction.setTitle(title);

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

						attraction_List_Nature.add(attraction);
						if (attraction_List_Nature.size()==20){
							break;
						}
					}
				}
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

		/* 휴식 카테고리 */

		ArrayList<Attraction> attraction_List_Rest = new ArrayList<Attraction>();

		try {
			// xpath 생성
			XPath  xpath = XPathFactory.newInstance().newXPath();

			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
					"http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=12&mapX="+lng+"&mapY="+lat+"&radius="+radius+"&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=10&pageNo=1");
			String expression = "//*/item";
			NodeList item_Node = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);

			if (item_Node.getLength()>0){

				for( int idx=-1; idx<item_Node.getLength()-1; idx++ ){// -1 부터 시작하는 이유는 맨 처음 item에 setTextContent 를 부여 못하기때문

					Attraction attraction = new Attraction();

					if (idx>=0){
						item_Node.item(idx).setTextContent("item_"+idx);
					}

					expression = "//*/cat3";
					String cat3 = xpath.compile(expression).evaluate(document);

					if (cat3.equals("A02020100") || cat3.equals("A02020200") || cat3.equals("A02020300") || cat3.equals("A02020600") || cat3.equals("A02020700")){
						expression = "//*/title";
						String title = xpath.compile(expression).evaluate(document);
						attraction.setTitle(title);

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

						attraction_List_Rest.add(attraction);
						if (attraction_List_Rest.size()==20){
							break;
						}
					}
				}
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

		/* 쇼핑 센터 불러오기 */

		ArrayList<Attraction> attraction_List_Shopping = new ArrayList<Attraction>();
		try {
			// xpath 생성

			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
					"http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=38&mapX="+lng+"&mapY="+lat+"&radius="+radius+"&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=10&pageNo=1");
			XPath  xpath = XPathFactory.newInstance().newXPath();
			String expression = "//*/item";
			NodeList item_Node = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);

			if (item_Node.getLength()>0){

				for( int idx=-1; idx<item_Node.getLength()-1; idx++ ){// -1 부터 시작하는 이유는 맨 처음 item에 setTextContent 를 부여 못하기때문

					Attraction attraction = new Attraction();

					if (idx>=0){
						item_Node.item(idx).setTextContent("item_"+idx);
					}

					expression = "//*/cat3";
					String cat3 = xpath.compile(expression).evaluate(document);

					if (cat3.equals("A04010300") || cat3.equals("A04010400") || cat3.equals("A04010800") ){
						expression = "//*/title";
						String title = xpath.compile(expression).evaluate(document);
						attraction.setTitle(title);

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

						attraction_List_Shopping.add(attraction);
						if (attraction_List_Shopping.size()==20){
							break;
						}
					}
				}
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
		/**************************************************/

		ModelAndView mav = new ModelAndView("plan/getRecommandPlacesJSON");

		mav.addObject("VENUES", venues);
		mav.addObject("HOTELS", hotels);
		mav.addObject("SIGHTS", attraction_List);
		mav.addObject("SHOPPINGS", attraction_List_Shopping);
		mav.addObject("RESTS", attraction_List_Rest);
		mav.addObject("NATURES", attraction_List_Nature);
		return mav;
	}

	/**
	 * PLAN을 새로 만들 때 호출되는 메서드
	 * @param city_latitude
	 * @param city_longitude
	 * @param cityno
	 * @return
	 */
	@RequestMapping(value="/placeCont/showMap.do")
	public ModelAndView showMap(@RequestParam(value="city_latitude") String city_latitude,
			@RequestParam(value="city_longitude") String city_longitude,
			@RequestParam(value="cityno") String cityno){

		ModelAndView mav = new ModelAndView("plan/showMap");
		mav.addObject("lat",city_latitude);
		mav.addObject("lang",city_longitude);
		mav.addObject("loc_no",cityno);

		return mav;
	}

	/**
	 * 기존에 존재하는 PLAN을 읽을때 호출되는 메서드
	 * @param planNo
	 * @return
	 */
	@RequestMapping(value="/placeCont/showMyMap.do")
	public ModelAndView showMyMap(@RequestParam(value="planNo")int planNo) {
		System.out.println("Run 'showMyMap'");
		System.out.println("Plan No: " + planNo);
		Location location = locationService.getLocationByPlanNo(planNo);
		Plan plan = planService.getPlan(planNo);
		System.out.println("Plan Name: " + plan.getPlan_name());
		plan.setPathlist(pathService.selectPath(planNo));
		for(Path path : plan.getPathlist()) {
			path.setVenuelist(venueService.selectVenue(path.getPath_no()));
		}

		ModelAndView mav = new ModelAndView("plan/showMap");
		mav.addObject("lat", location.getLoc_lati());
		mav.addObject("lang", location.getLoc_long());
		mav.addObject("loc_no", location.getLoc_no());
		mav.addObject("PLAN", plan);
		return mav;
	}




}
