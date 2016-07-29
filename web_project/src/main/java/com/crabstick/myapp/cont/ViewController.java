package com.crabstick.myapp.cont;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.crabstick.myapp.Location;

@Controller
public class ViewController {

	@RequestMapping(value="/viewcont/plan.do")
	public String planStart(){
		System.out.println("viewcont >> plan start");
		return "plan/planner";
	}
	
	@RequestMapping(value="/viewcont/markwifi.do")
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
				loc.add(new Location(
						Double.parseDouble(row.getCell(12).toString()),
						Double.parseDouble(row.getCell(13).toString())
						));
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
}
