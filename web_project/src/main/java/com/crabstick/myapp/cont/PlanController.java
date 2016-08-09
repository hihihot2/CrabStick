package com.crabstick.myapp.cont;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.crabstick.myapp.Location;
import com.crabstick.myapp.path.Path;
import com.crabstick.myapp.path.PathService;
import com.crabstick.myapp.plan.Plan;
import com.crabstick.myapp.plan.PlanService;
import com.crabstick.myapp.venue.Venue;
import com.crabstick.myapp.venue.VenueService;

@Controller
public class PlanController {
	
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
	
	private String clientId = "ej3ANIP8b0vPSY8tXHEG";
	private String clientSecret = "FNeWBxiKdd";
	
	

	@RequestMapping(value="plancont/searchloc.do") //검색 목록 ajax처리 함수
	public ModelAndView searchLocation(@RequestParam(value="data")String data){
		System.out.println("planCont >> "+data);
		ModelAndView mav = new ModelAndView("plan/searchLocXML");
		String searchUrl = "https://openapi.naver.com/v1/search/local.xml?query="+data;
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(searchUrl);
		Document document = null;
		InputSource is = null;
		XPath xpath = null;
		List<Location> list = new ArrayList<Location>();
		
		request.addHeader("Content-Type", "application/xml");
		request.addHeader("X-Naver-Client-Id", clientId);
		request.addHeader("X-Naver-Client-Secret", clientSecret);
		try {
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			
			is = new InputSource(new StringReader(EntityUtils.toString(entity)));
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			
			xpath = XPathFactory.newInstance().newXPath();
			
			NodeList title = (NodeList) xpath.evaluate("//channel/item/title", document, XPathConstants.NODESET);
			NodeList addr = (NodeList) xpath.evaluate("//channel/item/address", document, XPathConstants.NODESET);
			
			for(int i = 0 ; i < title.getLength() ; i++){
				list.add(new Location(title.item(i).getTextContent(), addr.item(i).getTextContent(),0.0, 0.0));
			}
			mav.addObject("SLIST", list);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	@RequestMapping(value="plancont/searchlatlng.do")
	public ModelAndView getLatLng(String addr){
		String convUrl = "https://openapi.naver.com/v1/map/geocode?output=xml&query=";
		System.out.println("addr : "+addr);
		ByteArrayOutputStream requestOutputStream = new ByteArrayOutputStream();
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = null;
		Document document = null;
		InputSource is = null;
		HttpResponse response = null;
		XPath xpath = null;
		ModelAndView mav = new ModelAndView("plan/getLatLngJSON");
		
		try {
			request = new HttpGet(convUrl+URLEncoder.encode(addr, "UTF-8"));
			
			request.addHeader("Content-Type", "application/xml");
			request.addHeader("X-Naver-Client-Id", clientId);
			request.addHeader("X-Naver-Client-Secret", clientSecret);
			response = client.execute(request);
			HttpEntity entity = response.getEntity();
			
			is = new InputSource(new StringReader(EntityUtils.toString(entity)));
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			
			xpath = XPathFactory.newInstance().newXPath();
			String lat = (String) xpath.evaluate("//result/items/item/point/y", document, XPathConstants.STRING);
			String lng = (String) xpath.evaluate("//result/items/item/point/x", document, XPathConstants.STRING);
			System.out.println("latlng : "+lat+", "+lng);
			mav.addObject("lat", lat);
			mav.addObject("lng", lng);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="plancont/sel_loc.do") //location.jsp -> select User's wish destination
	public ModelAndView select_location(@RequestParam(value="loc_num")int loc_num){
		System.out.println("plancont >> Choose location : "+loc_num);
		ModelAndView mav = new ModelAndView("plan/planner");
		
		switch(loc_num){
		case 0: //서울
			mav.addObject("lat", 37.5666102);
			mav.addObject("lang", 126.9783881);
			break;
		case 1:
			mav.addObject("lat", 35.1798159);
			mav.addObject("lang", 129.0750222);
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
		mav.addObject("loc_num", loc_num);
		
		return mav;
	}
	
	@RequestMapping(value="plancont/markwifi.do")
	public ModelAndView getWifiList(){
		System.out.println("viewcont >> get Wifi List");
		ModelAndView mav = new ModelAndView("plan/wifiJSON");
		
		Workbook sb = null;
		String path = 
"/C:/Users/Administrator/Desktop/GitSource/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/web_project/resources/wifi_data.xlsx";
		//System.out.println("p : "+this.getClass().getResource("").getPath());
		File file = new File(path);
		//ArrayList<Double> lat = new ArrayList<Double>();
		//ArrayList<Double> lang = new ArrayList<Double>();
		ArrayList<Location> loc = new ArrayList<Location>();
		
		if(file != null)
			System.out.println("File Open");
		
		try {
			sb = new XSSFWorkbook(new FileInputStream(file));
			
			for(Row row : sb.getSheetAt(0)){
				if(row.getRowNum() == 0)//12 13
					continue;
				//String num = row.getCell(12).toString();
				//lat.add(Double.parseDouble(row.getCell(12).toString()));
				//lang.add(Double.parseDouble(row.getCell(13).toString()));
				//System.out.println(row.getCell(12));
				/*loc.add(new Location(
						Double.parseDouble(row.getCell(12).toString()),
						Double.parseDouble(row.getCell(13).toString())
						));*/
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//mav.addObject("LAT", lat);
		//mav.addObject("LANG", lang);
		mav.addObject("LOC", loc);
		return mav;
	}
	
	@RequestMapping(value="/planCont/addPath.do")
	public String addPath(@RequestParam("plan")String plan, HttpSession session) {
		System.out.println("일정추가 시작");
		if(plan != null) {
			System.out.println("plan: " + plan);
			JSONObject planObject = (JSONObject)JSONValue.parse(plan);
			String planName = (String) planObject.get("planName");
			String planComment = (String) planObject.get("planComment");
			long planCost = (Long) planObject.get("planCost");
			long planPersons = (Long) planObject.get("planPersons");
			long planStyle = (Long) planObject.get("planStyle");
			int memberNo = Integer.parseInt(session.getAttribute("no").toString());
			
			Plan newPlan = new Plan(0, planName, planComment, (int) planCost, (int) planPersons, null, (char) (planStyle+48), memberNo);
			planService.insertPlan(newPlan);
			int planNo = newPlan.getPlan_no();
			System.out.println("Plan No: " + planNo);
			
			Path newPath = new Path(0, "일정 1", null, planNo);
			pathService.insertPath(newPath);
			int pathNo = newPath.getPath_no();
			System.out.println("Path No: " + pathNo);
			
			JSONArray path = (JSONArray) planObject.get("path");
			Iterator iterator = path.iterator();
			int order = 1;
			while(iterator.hasNext()) {
				JSONObject venue = (JSONObject) iterator.next();
				String venueName = (String) venue.get("venueName");
				String venueComment = (String) venue.get("venueComment");
				String venueType = (String) venue.get("venueType");
				String lat = (String) venue.get("lat");
				String lng = (String) venue.get("lng");
				
				venueService.insertVenue(new Venue(0, venueName, lat, lng, venueComment, venueType, order, pathNo, 1));
				order++;
			}
			
			
		} else {
			System.out.println("응 널이야~");
		}
		
		return null;
	}
	
	
	
	
	
}
