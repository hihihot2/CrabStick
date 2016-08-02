package com.test.myapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
//	@RequestMapping(value="/getObjects.do")
//	public String getObjects(MyObject[] objects) {
//		if(objects != null) {
//			for(MyObject object : objects) {
//				System.out.println("column1: " + object.getColumn1());
//				System.out.println("column2: " + object.getColumn2());
//				System.out.println("column3: " + object.getColumn3());
//			}
//		}
//		return null;
//	}
	@RequestMapping(value="/getObjects.do")
	public void getObjects(@RequestParam("json")String json) {
		if(json != null) {
			System.out.println("json: " + json);
			JSONArray jsonArray = (JSONArray)JSONValue.parse(json);
			Iterator iterator = jsonArray.iterator();
			
			while(iterator.hasNext()) {
				JSONObject object = (JSONObject) iterator.next();
				System.out.println("-------------------");
				System.out.println(object.get("column1"));
				System.out.println(object.get("column2"));
				System.out.println(object.get("column3"));
			}
		} else {
			System.out.println("응 널이야~");
		}
	}
	
	
}
