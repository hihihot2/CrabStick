package com.crabstick.myapp.cont;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
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
import org.json.simple.JSONValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.crabstick.myapp.Location;
import com.crabstick.myapp.path.Path;
import com.crabstick.myapp.path.PathService;
import com.crabstick.myapp.plan.Plan;
import com.crabstick.myapp.plan.PlanService;
import com.crabstick.myapp.venue.VenueService;
import com.crabstick.myapp.venue.Venues;

@Controller
public class PlanController {
	
	@Resource(name = "venueService")
	private VenueService venueservice;
	public void setService(VenueService venueservice) {
		this.venueservice = venueservice;
	}
	
	@Resource(name = "pathService")
	private PathService pathservice;
	public void setService(PathService pathservice) {
		this.pathservice = pathservice;
	}
	
	@Resource(name = "planService")
	private PlanService planservice;
	public void setService(PlanService planservice) {
		this.planservice = planservice;
	}
	

	private String clientId = "ej3ANIP8b0vPSY8tXHEG";
	private String clientSecret = "FNeWBxiKdd";
	
	

	@RequestMapping(value="plancont/searchloc.do")
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
			
			for(int i = 0 ; i < addr.getLength() ; i++){
				Location loc = getLatLng(addr.item(i).getTextContent());
				loc.setTitle(title.item(i).getTextContent());
				list.add(loc);
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
	public Location getLatLng(String addr){
		String convUrl = "https://openapi.naver.com/v1/map/geocode?output=xml&query=";
		
		ByteArrayOutputStream requestOutputStream = new ByteArrayOutputStream();
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = null;
		Document document = null;
		InputSource is = null;
		HttpResponse response = null;
		XPath xpath = null;
		
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
			return new Location(null, Double.parseDouble(lat), Double.parseDouble(lng));
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
		return null;
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
	public void addPath(@RequestParam("json")String json) {
		System.out.println("일정추가 시작");
		Date date = new Date(0);
		 
		System.out.println(date);
		
		if(json != null) {
			System.out.println("json: " + json);
			JSONArray jsonArray = (JSONArray)JSONValue.parse(json);
			Iterator iterator = jsonArray.iterator();
			int count = 1;
			Venues v = new Venues();
			Path pa = new Path();			
			Plan p = new Plan();
			
			
			
			
			
/*			planservice.insertPlan(p);
*/			
			
			/*pa.setPath_name("첫째날");
			pa.setPlan_no(count);			
			pathservice.insertPath(pa);			
			
			
			
			System.out.println("path_no = " + pa.getPath_no());
			while(iterator.hasNext()) {
				JSONObject object = (JSONObject) iterator.next();
				
				System.out.println("-------------------");
				System.out.println(object.get("ven_name"));
				System.out.println(object.get("ven_lati"));
				System.out.println(object.get("ven_long"));
				System.out.println(object.get("ven_order"));
				System.out.println(object.get("loc_no"));
				
				v.setVen_name((String) object.get("ven_name"));
				v.setVen_lati((String) object.get("ven_lati"));
				v.setVen_long((String) object.get("ven_long"));
				v.setVen_commt("Test");
				v.setVen_order(count);
				v.setPath_no(pa.getPath_no());
				v.setVen_type("1");
				v.setLoc_no((Integer.parseInt(object.get("loc_no").toString())));
				count++;							
				venueservice.insertVenue(v);
			}
			*/
		} else {
			System.out.println("응 널이야~");
		}
	}
	
}
