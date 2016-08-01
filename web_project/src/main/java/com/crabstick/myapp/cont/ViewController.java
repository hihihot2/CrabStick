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
		return "redirect:/plancont/loc.do";
	}
}
