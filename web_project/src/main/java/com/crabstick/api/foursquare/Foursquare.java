package com.crabstick.api.foursquare;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import com.crabstick.api.foursquare.objects.Group;

public class Foursquare {
	public static final String API_EXPLORE = "venues/explore";
	public static final String API_SEARCH = "venues/search";
	public static final String EXPLORE_FIELD_LL = "ll";
	public static final String EXPLORE_FIELD_NEAR = "near";
	public static final String EXPLORE_FIELD_LL_ACC = "llAcc";
	public static final String EXPLORE_FIELD_ALT = "alt";
	public static final String EXPLORE_FIELD_ALT_ACC = "altAcc";
	public static final String EXPLORE_FIELD_RADIUS = "radius";
	public static final String EXPLORE_FIELD_SECTION = "section";
	public static final String EXPLORE_FIELD_QUERY = "query";
	public static final String EXPLORE_FIELD_LIMIT = "limit";
	public static final String EXPLORE_FIELD_OFFSET = "offset";
	public static final String EXPLORE_FIELD_NOVELTY = "novelty";
	public static final String EXPLORE_FIELD_FRIEND_VISITS = "friendVisits";
	public static final String EXPLORE_FIELD_TIME = "time";
	public static final String EXPLORE_FIELD_DAY = "day";
	public static final String EXPLORE_FIELD_VENUE_PHOTOS = "venuePhotos";
	public static final String EXPLORE_FIELD_LAST_VENUE = "lastVenue";
	public static final String EXPLORE_FIELD_OPEN_NOW = "openNow";
	public static final String EXPLORE_FIELD_SORT_BY_DISTANCE = "sortByDistance";
	public static final String EXPLORE_FIELD_PRICE = "price";
	public static final String EXPLORE_FIELD_SAVED = "saved";
	public static final String EXPLORE_FIELD_SPECIALS = "specials";
	public static final String PARAMETER_SECTION_FOOD = "food";
	public static final String PARAMETER_SECTION_DRINKS = "drinks";
	public static final String PARAMETER_SECTION_COFFEE = "coffee";
	public static final String PARAMETER_SECTION_SHOPS = "shops";
	public static final String PARAMETER_SECTION_ARTS = "arts";
	public static final String PARAMETER_SECTION_OUTDOORS = "outdoors";
	public static final String PARAMETER_SECTION_SIGHTS = "sights";
	public static final String PARAMETER_SECTION_TRENDING = "trending";
	public static final String PARAMETER_SECTION_SPECIALS = "specials";
	public static final String PARAMETER_SECTION_NEXT_VENUES = "nextVenues";
	
	private String apiUrl = "https://api.foursquare.com/v2/";
	private String apiUrlSuffix;
	private String oauthToken;
	private String clientId;
	private String clientSecret;
	private String v;
	private String apiSelector;
	
	/**
	 * oauth_token을 이용한 request시 사용되는 constructor
	 * @param oauthToken	인증키 
	 * @param apiSelector	어떤 api기능을 사용할 것인가 하는 결정자 
	 */
	public Foursquare(String oauthToken, String apiSelector) {
		this.oauthToken = oauthToken;
		this.apiSelector = apiSelector;
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		v = dateFormat.format(calendar.getTime());
		
		apiUrl += apiSelector + "?oauth_token=" + oauthToken + "&v=" + v;
	}
	
	/**
	 * 사용자 인증 없는 api 기능(search, explore 등) 사용시 application의 client_id, client_secret을 통해 요청 
	 * @param clientId		client_id
	 * @param clientSecret	client_secret
	 * @param apiSelector	어떤 api기능을 사용할 것인가 하는 결정자 
	 */
	public Foursquare(String clientId, String clientSecret, String apiSelector) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		apiUrlSuffix = "";
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		v = dateFormat.format(calendar.getTime());
		
		apiUrl += apiSelector + "?client_id=" + clientId + "&client_secret=" + clientSecret + "&v=" + v;
	}
	
	/**
	 * api의 url의 필드에 속성을 추가하는 메서드 
	 * @param fieldName	'Foursquare.필드명' 으로 접근 가능 
	 * @param value		필드에 들어갈 값 
	 */
	public void addField(String fieldName, String value) {
		apiUrlSuffix += "&" + fieldName + "=" + value;
	}
	
	/**
	 * 장소들(venues) 가져오는 메서드, 1개의 group 객체에 여러개의 venue를 갖는다
	 * @return					Group 리스트 
	 * @throws IOException		
	 * @throws ParseException
	 */
	public ArrayList<Group> getVenues() throws IOException, ParseException {
		ArrayList<Group> venueGroups = new ArrayList<Group>();
		
		URL url = new URL(apiUrl + apiUrlSuffix);
		System.out.println("Request Url: " + apiUrl + apiUrlSuffix);
		apiUrlSuffix = "";
		
		HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
		InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
		
		JSONObject object = (JSONObject)JSONValue.parseWithException(isr);
		JSONObject response = (JSONObject) object.get("response");
		
		if(response != null) {
			JSONArray groups = (JSONArray) response.get("groups");
			Iterator groupIterator = groups.iterator();
			
			while(groupIterator.hasNext()) {
				venueGroups.add(new Group((JSONObject) groupIterator.next()));
			}
		}
		
		return venueGroups;
	}
}
