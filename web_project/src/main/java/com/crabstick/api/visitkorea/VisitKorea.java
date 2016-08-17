package com.crabstick.api.visitkorea;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.crabstick.myapp.recommendation.Attraction;

public class VisitKorea {
	private String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList";
	private String serviceKey;
	
	public VisitKorea(String serviceKey) {
		this.serviceKey = serviceKey;
		url += "?serviceKey=" + serviceKey;
	}
	
	public List<Attraction> getSights(String lat, String lng, String radius) throws SAXException, IOException, ParserConfigurationException {
		List<Attraction> sightList = new ArrayList<Attraction>();
		String uri = url + "&mapX=" + lng + "&mapY=" + lat + "&radius=" + radius + "&contentTypeId=12&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=B&numOfRows=10&pageNo=1";
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(uri);
		
		return sightList;
	}
}
