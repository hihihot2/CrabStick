package com.crabstick.myapp.cont;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crabstick.api.foursquare.Foursquare;
import com.crabstick.api.foursquare.objects.Group;

@Controller
public class PlaceController {
	private String foursquareClientId = "IT0NOIEI42Z0PT4XTEHTPECHU3TO1QFWKDHCNLHAEY1ESDZG";
	private String foursquareClientSecret = "OWVMGV1NWBD1ZWLT0TIWDKT4BOXHZD3YZTAJAX5UYLYK1BJO";
	private String expediaConsumerKey = "D9o6vpXU1ANf0zteRzFIAS83NkccKkoJ";
	private String expediaConsumerSecret = "14Y3QtDfkL4G58kJ";

	@RequestMapping(value="/placeCont/getRestaurants.do")
	public ModelAndView getRestaurants(@RequestParam(value="city_latitude") String city_latitude,@RequestParam(value="city_longitude") String city_longitude, @RequestParam(value="cityno") String cityno) {
		Foursquare foursquare = new Foursquare(foursquareClientId, foursquareClientSecret, Foursquare.API_EXPLORE);
		System.out.println(city_latitude+","+city_longitude);
		foursquare.addField(Foursquare.EXPLORE_FIELD_LL, city_latitude+","+city_longitude);
		foursquare.addField(Foursquare.EXPLORE_FIELD_SECTION, Foursquare.PARAMETER_SECTION_FOOD);
		foursquare.addField(Foursquare.EXPLORE_FIELD_RADIUS, "10000");

		ArrayList<Group> venueGroups = null;
		
		
		try {
			venueGroups = foursquare.getVenues();
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
		return mav;
	}
}
