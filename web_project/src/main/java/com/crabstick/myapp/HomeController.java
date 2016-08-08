package com.crabstick.myapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		System.out.println("hi2");
		Workbook sb = null;
		ModelAndView mav = new ModelAndView("home");
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
	@RequestMapping(value="test2")
	public String test2(){
		return "home";
	}
}
