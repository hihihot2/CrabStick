package com.crabstick.myapp.cont;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.crabstick.api.expedia.Expedia;
import com.crabstick.api.expedia.objects.Response;
import com.crabstick.api.foursquare.Foursquare;
import com.crabstick.api.foursquare.objects.Group;
import com.crabstick.api.foursquare.objects.Venue;
import com.crabstick.myapp.location.Location;
import com.crabstick.myapp.location.LocationService;
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


	@RequestMapping(value="/placeCont/branch.do") // ajax 처리를 위한 함수
	public ModelAndView setBranch(@RequestParam(value="branch")int branch, 
			@RequestParam(value="city_latitude")String lat, @RequestParam(value="city_longitude")String lng){
	
		ModelAndView mav = null;
		ArrayList<Integer> transaction_List = recommendationService.all_Transactions();
		ArrayList<String> sequence_List = recommendationService.all_Sequence();
		ArrayList<com.crabstick.myapp.venue.Venue> Venue_Table_Data = recommendationService.all_Data();
		
		
		Apriori apriori = new Apriori();
		apriori.apriori_Algorithm(transaction_List, sequence_List ,Venue_Table_Data);

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

		} else if(branch == 1) { //맛집 파싱
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
			mav.addObject("VENUES", venueGroups);

		}else if(branch == 2){ //명소 파싱

			//String key = "w7AsuB%2BGDEOxLnV40NaLBqqMrfwXHxoia3eDdF7U0gaeH%2Bdoxr%2BnTzd44cy25eqMTO23boo4lGvOboJp6Sa4CQ%3D%3D";
			//발급 받은 서비스키
			String key = "%2BzkCsJG8T4Mc408ug306EphfPVrmOHMSC9eY52USE%2BzMmV4OZ4%2Fzpzlqh220vkBb9fJAE1am%2B0LtDr%2FAzs2UIA%3D%3D";
			//String key = "t%2FSK%2Brzp5k8nLo7iyovH4M0zZFOdkA8BYVCtjz3k%2BnKAb6MFSz1Eg%2FoZSCOhimTDxDRFSRgHVF1Kw3b2NtlieA%3D%3D";
			mav = new ModelAndView("plan/getAttrJSON");
			ArrayList<Attraction> attraction_list = new ArrayList<Attraction>();

			/* XML 파싱 부분 */
			Document document;
			try {
				//URL접근
				document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
						"http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=12&mapX="+lng+"&mapY="+lat+"&radius=10000&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=1&pageNo=1");
				System.out.println("http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=12&mapX="+lng+"&mapY="+lat+"&radius=10000&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=1&pageNo=1");


				// xpath 생성
				XPath  xpath = XPathFactory.newInstance().newXPath();
				String expression = "//*/totalCount"; //xml <item> </item> 노드 읽기
				int totalCount = Integer.parseInt(xpath.compile(expression).evaluate(document));
				System.out.println(totalCount);

				document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
						"http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=12&mapX="+lng+"&mapY="+lat+"&radius=10000&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows="+totalCount+"&pageNo=1");
				System.out.println("http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=12&mapX="+lng+"&mapY="+lat+"&radius=10000&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows="+totalCount+"&pageNo=1");
				expression = "//*/item";
				NodeList item_Node = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);
				System.out.println("item_Node 길이"+item_Node.getLength());

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

							attraction_list.add(attraction);
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

			mav.addObject("ATTR", attraction_list);


		}else if(branch == 3){ //백화점 or 면세점

			//String key = "w7AsuB%2BGDEOxLnV40NaLBqqMrfwXHxoia3eDdF7U0gaeH%2Bdoxr%2BnTzd44cy25eqMTO23boo4lGvOboJp6Sa4CQ%3D%3D";
			//발급 받은 서비스키
			String key = "%2BzkCsJG8T4Mc408ug306EphfPVrmOHMSC9eY52USE%2BzMmV4OZ4%2Fzpzlqh220vkBb9fJAE1am%2B0LtDr%2FAzs2UIA%3D%3D";
			//String key = "t%2FSK%2Brzp5k8nLo7iyovH4M0zZFOdkA8BYVCtjz3k%2BnKAb6MFSz1Eg%2FoZSCOhimTDxDRFSRgHVF1Kw3b2NtlieA%3D%3D";
			mav = new ModelAndView("plan/getAttrJSON");
			ArrayList<Attraction> attraction_list = new ArrayList<Attraction>();

			/* XML 파싱 부분 */
			Document document;
			try {
				//URL접근
				document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
						"http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=38&mapX="+lng+"&mapY="+lat+"&radius=10000&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=1&pageNo=1");
				System.out.println("http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=38&mapX="+lng+"&mapY="+lat+"&radius=10000&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=1&pageNo=1");


				// xpath 생성
				XPath  xpath = XPathFactory.newInstance().newXPath();
				String expression = "//*/totalCount"; //xml <item> </item> 노드 읽기
				int totalCount = Integer.parseInt(xpath.compile(expression).evaluate(document));
				System.out.println(totalCount);

				document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
						"http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=38&mapX="+lng+"&mapY="+lat+"&radius=10000&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows="+totalCount+"&pageNo=1");
				System.out.println("http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=38&mapX="+lng+"&mapY="+lat+"&radius=10000&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows="+totalCount+"&pageNo=1");
				expression = "//*/item";
				NodeList item_Node = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);
				System.out.println("item_Node 길이"+item_Node.getLength());

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

							attraction_list.add(attraction);
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

			mav.addObject("ATTR", attraction_list);


		} else if (branch == 4) { // 휴식 카테고리

			//String key = "w7AsuB%2BGDEOxLnV40NaLBqqMrfwXHxoia3eDdF7U0gaeH%2Bdoxr%2BnTzd44cy25eqMTO23boo4lGvOboJp6Sa4CQ%3D%3D";
			//발급 받은 서비스키
			String key = "%2BzkCsJG8T4Mc408ug306EphfPVrmOHMSC9eY52USE%2BzMmV4OZ4%2Fzpzlqh220vkBb9fJAE1am%2B0LtDr%2FAzs2UIA%3D%3D";
			//String key = "t%2FSK%2Brzp5k8nLo7iyovH4M0zZFOdkA8BYVCtjz3k%2BnKAb6MFSz1Eg%2FoZSCOhimTDxDRFSRgHVF1Kw3b2NtlieA%3D%3D";
			mav = new ModelAndView("plan/getAttrJSON");
			ArrayList<Attraction> attraction_list = new ArrayList<Attraction>();

			/* XML 파싱 부분 */
			Document document;
			try {
				//URL접근
				document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
						"http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=12&mapX="+lng+"&mapY="+lat+"&radius=10000&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=1&pageNo=1");
				System.out.println("http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=12&mapX="+lng+"&mapY="+lat+"&radius=10000&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=1&pageNo=1");


				// xpath 생성
				XPath  xpath = XPathFactory.newInstance().newXPath();
				String expression = "//*/totalCount"; //xml <item> </item> 노드 읽기
				int totalCount = Integer.parseInt(xpath.compile(expression).evaluate(document));
				System.out.println(totalCount);

				document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
						"http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=12&mapX="+lng+"&mapY="+lat+"&radius=10000&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows="+totalCount+"&pageNo=1");
				System.out.println("http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=12&mapX="+lng+"&mapY="+lat+"&radius=10000&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows="+totalCount+"&pageNo=1");
				expression = "//*/item";
				NodeList item_Node = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);
				System.out.println("item_Node 길이"+item_Node.getLength());

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

							attraction_list.add(attraction);
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
			mav.addObject("ATTR", attraction_list);
		} 
		else if (branch == 5) { //자연
			//String key = "w7AsuB%2BGDEOxLnV40NaLBqqMrfwXHxoia3eDdF7U0gaeH%2Bdoxr%2BnTzd44cy25eqMTO23boo4lGvOboJp6Sa4CQ%3D%3D";
			//발급 받은 서비스키
			String key = "%2BzkCsJG8T4Mc408ug306EphfPVrmOHMSC9eY52USE%2BzMmV4OZ4%2Fzpzlqh220vkBb9fJAE1am%2B0LtDr%2FAzs2UIA%3D%3D";
			//String key = "t%2FSK%2Brzp5k8nLo7iyovH4M0zZFOdkA8BYVCtjz3k%2BnKAb6MFSz1Eg%2FoZSCOhimTDxDRFSRgHVF1Kw3b2NtlieA%3D%3D";
			mav = new ModelAndView("plan/getAttrJSON");
			ArrayList<Attraction> attraction_list = new ArrayList<Attraction>();

			/* XML 파싱 부분 */
			Document document;
			try {
				//URL접근
				document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
						"http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=12&mapX="+lng+"&mapY="+lat+"&radius=10000&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=1&pageNo=1");
				System.out.println("http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=12&mapX="+lng+"&mapY="+lat+"&radius=10000&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=1&pageNo=1");


				// xpath 생성
				XPath  xpath = XPathFactory.newInstance().newXPath();
				String expression = "//*/totalCount"; //xml <item> </item> 노드 읽기
				int totalCount = Integer.parseInt(xpath.compile(expression).evaluate(document));
				System.out.println(totalCount);

				document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
						"http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=12&mapX="+lng+"&mapY="+lat+"&radius=10000&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows="+totalCount+"&pageNo=1");
				System.out.println("http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+key+"&contentTypeId=12&mapX="+lng+"&mapY="+lat+"&radius=10000&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows="+totalCount+"&pageNo=1");
				expression = "//*/item";
				NodeList item_Node = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);
				System.out.println("item_Node 길이"+item_Node.getLength());

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

							attraction_list.add(attraction);
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
			mav.addObject("ATTR", attraction_list);
		}
		mav.addObject("type", branch);
		return mav;
	}
	
	@RequestMapping(value="/placeCont/getRecommandPlaces.do")
	public ModelAndView getRecommandPlaces(@RequestParam(value="lat")String lat, @RequestParam(value="lng")String lng, @RequestParam(value="radius")String radius) {
		
		/**************************************************
		 * 이하 추천 장소를 가져오는 알고리즘 입력 부분
		 * 현재 임의로 맛집, 호텔 각각 10개씩 가져옴
		 **************************************************/
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
		expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_RESULTS_PER_PAGE, "30");
		expedia.addField(Expedia.HOTEL_SEARCH_PARAMETER_SORT_ORDER, "true");

		Response hotels = null;
		try {
			hotels = expedia.getHotels();			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 맛집 가져오기
		Foursquare foursquare = new Foursquare(foursquareClientId, foursquareClientSecret, Foursquare.API_EXPLORE);
		foursquare.addField(Foursquare.EXPLORE_FIELD_LL, lat+","+lng);
		foursquare.addField(Foursquare.EXPLORE_FIELD_SECTION, Foursquare.PARAMETER_SECTION_FOOD);		
		foursquare.addField(Foursquare.EXPLORE_FIELD_RADIUS, radius);
		foursquare.addField(Foursquare.EXPLORE_FIELD_LIMIT, "30");
		
		ArrayList<Venue> venues = null;
		
		try {
			venues = new ArrayList<Venue>();
			ArrayList<Group> venueGroups = foursquare.getVenues();
			for(Group group : venueGroups) {
				for(Venue venue : group.getItems()) {
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
		
		/**************************************************/
		
		ModelAndView mav = new ModelAndView("plan/getRecommandPlacesJSON");
		mav.addObject("VENUES", venues);
		mav.addObject("HOTELS", hotels.getHotelList());
//		아래와 같은 오브젝트들도 같이 전달 되어야 함
//		mav.addObject("SIGHTS", sights);
//		mav.addObject("SHOPPINGS", shoppings);
//		mav.addObject("RESTS", rests);
//		mav.addObject("NATURES", natures);
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
