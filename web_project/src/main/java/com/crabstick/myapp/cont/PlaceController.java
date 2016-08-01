package com.crabstick.myapp.cont;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crabstick.api.foursquare.Foursquare;
import com.crabstick.api.foursquare.objects.Group;
import com.crabstick.api.foursquare.objects.Venue;

@Controller
public class PlaceController {
	private String clientId = "IT0NOIEI42Z0PT4XTEHTPECHU3TO1QFWKDHCNLHAEY1ESDZG";
	private String clientSecret = "OWVMGV1NWBD1ZWLT0TIWDKT4BOXHZD3YZTAJAX5UYLYK1BJO";

	@RequestMapping(value="/placeCont/getRestaurants.do")
	public ModelAndView getRestaurants(@RequestParam(value="city") String city) {
		Foursquare foursquare = new Foursquare(clientId, clientSecret, Foursquare.API_EXPLORE);
		foursquare.addField(Foursquare.EXPLORE_PARAMETER_LL, "37.485430,126.897108");
		ArrayList<Group> venueGroups = null;
		
		try {
			venueGroups = foursquare.getVenues();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView("plan/showMap");
		mav.addObject("VENUES", venueGroups);
		
		return mav;
	}
}
