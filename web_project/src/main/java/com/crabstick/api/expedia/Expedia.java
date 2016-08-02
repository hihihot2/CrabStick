package com.crabstick.api.expedia;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import com.crabstick.api.expedia.objects.Response;

public class Expedia {
	public static final String API_HOTEL_SEARCH = "mhotels/search";
	public static final String HOTEL_SEARCH_PARAMETER_CITY = "city";
	public static final String HOTEL_SEARCH_PARAMETER_REGION_ID = "regionId";
	public static final String HOTEL_SEARCH_PARAMETER_LATTITUDE = "latitude";
	public static final String HOTEL_SEARCH_PARAMETER_LONGITUDE = "longitude";
	public static final String HOTEL_SEARCH_PARAMETER_FILTER_UNAVAILABLE = "filterUnavailable";
	public static final String HOTEL_SEARCH_PARAMETER_FILTER_HOTEL_NAME = "filterHotelName";
	public static final String HOTEL_SEARCH_PARAMETER_FILTER_STAR_RATINGS = "filterStarRatings";
	public static final String HOTEL_SEARCH_PARAMETER_FILTER_AMENITIES = "filterAmenities";
	public static final String HOTEL_SEARCH_PARAMETER_SORT_ORDER = "sortOrder";
	public static final String HOTEL_SEARCH_PARAMETER_RESULTS_PER_PAGE = "resultsPerPage";
	public static final String HOTEL_SEARCH_PARAMETER_SOURCE_TYPE = "sourceType";
	public static final String HOTEL_SEARCH_PARAMETER_CHECK_IN_DATE = "checkInDate";
	public static final String HOTEL_SEARCH_PARAMETER_CHECK_OUT_DATE = "checkOutDate";
	public static final String HOTEL_SEARCH_PARAMETER_ROOM1 = "room1";
	public static final String HOTEL_SEARCH_PARAMETER_ROOM = "room";
	
	private String apiUrl = "http://terminal2.expedia.com:80/x/";
	private String consumerKey;
	private String apiSelector;
	private String apiUrlSuffix;
	
	public Expedia(String consumerKey, String apiSelector) {
		super();
		this.consumerKey = consumerKey;
		this.apiSelector = apiSelector;
		apiUrlSuffix = "";
		
		apiUrl += apiSelector + "?apikey=" + consumerKey;
	}
	
	public void addField(String fieldName, String value) {
		apiUrlSuffix += "&" + fieldName + "=" + value;
	}
	
	public Response getHotels() throws IOException, ParseException {
		URL url = new URL(apiUrl + apiUrlSuffix);
		System.out.println("Request Url: " + apiUrl + apiUrlSuffix);
		apiUrlSuffix = "";
		
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
		
		JSONObject object = (JSONObject)JSONValue.parseWithException(isr);
		Response response = new Response(object);
		
		return response;
	}
}
