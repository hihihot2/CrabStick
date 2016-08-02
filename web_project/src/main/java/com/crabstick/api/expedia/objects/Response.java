package com.crabstick.api.expedia.objects;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Response {
	public static final String COLUMN_NUMBER_OF_ROOMS_REQUESTED = "numberOfRoomsRequested";
	public static final String COLUMN_FILTER_UNAVAILABLE_HOTELS_REQUESTED = "filterUnavailableHotelsRequested";
	public static final String COLUMN_TOTAL_HOTEL_COUNT = "totalHotelCount";
	public static final String COLUMN_AVAILABLE_HOTEL_COUNT = "availableHotelCount";
	public static final String COLUMN_SEARCH_REGION_ID = "searchRegionId";
	public static final String COLUMN_SEARCH_REGION_CITY = "searchRegionCity";
	public static final String COLUMN_DEEP_LINK_URL = "deepLinkUrl";
	public static final String COLUMN_HOTEL_LIST = "hotelList";
	public static final String COLUMN_ALL_NEIGHBORHOODS_IN_SEARCH_REGION = "allNeighborhoodsInSearchRegion";
	public static final String COLUMN_FILTERED_SEARCH_MATCHED_NO_HOTELS = "filteredSearchMatchedNoHotels";
	public static final String COLUMN_AMENITY_FILTER_OPTIONS = "amenityFilterOptions";
	public static final String COLUMN_STAR_OPTIONS = "starOptions";
	public static final String COLUMN_PRICE_OPTIONS = "priceOptions";
	public static final String COLUMN_HOTEL_IDS_MISSING_OR_BAD_REGION_IDS = "hotelIdsMissingOrBadRegionIds";
	
	private long numberOfRoomsRequested;
	private boolean filterUnavailableHotelsRequested;
	private long totalHotelCount;
	private long availableHotelCount;
	private String searchRegionId;
	private String searchRegionCity;
	private String deepLinkUrl;
	private ArrayList<Hotel> hotelList;
	private ArrayList<Neighborhood> allNeighborhoodsInSearchRegion;
	private boolean filteredSearchMatchedNoHotels;
//	private FilterOptions amenityFilterOptions;
	private ArrayList<StarOption> starOptions;
	private ArrayList<PriceOption> priceOptions;
	private ArrayList<String> hotelIdsMissingOrBadRegionIds;
	
	@Override
	public String toString() {
		return "Response [numberOfRoomsRequested=" + numberOfRoomsRequested + ", filterUnavailableHotelsRequested="
				+ filterUnavailableHotelsRequested + ", totalHotelCount=" + totalHotelCount + ", availableHotelCount="
				+ availableHotelCount + ", searchRegionId=" + searchRegionId + ", searchRegionCity=" + searchRegionCity
				+ ", deepLinkUrl=" + deepLinkUrl + ", hotelList=" + hotelList + ", allNeighborhoodsInSearchRegion="
				+ allNeighborhoodsInSearchRegion + ", filteredSearchMatchedNoHotels=" + filteredSearchMatchedNoHotels
				+ ", starOptions=" + starOptions + ", priceOptions=" + priceOptions + ", hotelIdsMissingOrBadRegionIds="
				+ hotelIdsMissingOrBadRegionIds + "]";
	}

	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Response(long numberOfRoomsRequested, boolean filterUnavailableHotelsRequested, long totalHotelCount,
			long availableHotelCount, String searchRegionId, String searchRegionCity, String deepLinkUrl,
			ArrayList<Hotel> hotelList, ArrayList<Neighborhood> allNeighborhoodsInSearchRegion,
			boolean filteredSearchMatchedNoHotels, ArrayList<StarOption> starOptions,
			ArrayList<PriceOption> priceOptions, ArrayList<String> hotelIdsMissingOrBadRegionIds) {
		super();
		this.numberOfRoomsRequested = numberOfRoomsRequested;
		this.filterUnavailableHotelsRequested = filterUnavailableHotelsRequested;
		this.totalHotelCount = totalHotelCount;
		this.availableHotelCount = availableHotelCount;
		this.searchRegionId = searchRegionId;
		this.searchRegionCity = searchRegionCity;
		this.deepLinkUrl = deepLinkUrl;
		this.hotelList = hotelList;
		this.allNeighborhoodsInSearchRegion = allNeighborhoodsInSearchRegion;
		this.filteredSearchMatchedNoHotels = filteredSearchMatchedNoHotels;
		this.starOptions = starOptions;
		this.priceOptions = priceOptions;
		this.hotelIdsMissingOrBadRegionIds = hotelIdsMissingOrBadRegionIds;
	}
	
	public Response(JSONObject object) {
		if(object.get(COLUMN_NUMBER_OF_ROOMS_REQUESTED) != null) numberOfRoomsRequested = (Long) object.get(COLUMN_NUMBER_OF_ROOMS_REQUESTED);
		if(object.get(COLUMN_FILTER_UNAVAILABLE_HOTELS_REQUESTED) != null) filterUnavailableHotelsRequested = (Boolean) object.get(COLUMN_FILTER_UNAVAILABLE_HOTELS_REQUESTED);
		if(object.get(COLUMN_TOTAL_HOTEL_COUNT) != null) totalHotelCount = (Long) object.get(COLUMN_TOTAL_HOTEL_COUNT);
		if(object.get(COLUMN_AVAILABLE_HOTEL_COUNT) != null) availableHotelCount = (Long) object.get(COLUMN_AVAILABLE_HOTEL_COUNT);
		if(object.get(COLUMN_SEARCH_REGION_ID) != null) searchRegionId = (String) object.get(COLUMN_SEARCH_REGION_ID);
		if(object.get(COLUMN_SEARCH_REGION_CITY) != null) searchRegionCity = (String) object.get(COLUMN_SEARCH_REGION_CITY);
		if(object.get(COLUMN_DEEP_LINK_URL) != null) deepLinkUrl = (String) object.get(COLUMN_DEEP_LINK_URL);
		if(object.get(COLUMN_HOTEL_LIST) != null) {
			hotelList = new ArrayList<Hotel>();
			JSONArray hotelListArray = (JSONArray) object.get(COLUMN_HOTEL_LIST);
			Iterator hotelListIterator = hotelListArray.iterator();
			
			while(hotelListIterator.hasNext()) {
				hotelList.add(new Hotel((JSONObject) hotelListIterator.next()));
			}
		}
		if(object.get(COLUMN_ALL_NEIGHBORHOODS_IN_SEARCH_REGION) != null) {
			allNeighborhoodsInSearchRegion = new ArrayList<Neighborhood>();
			JSONArray neighborhoodListArray = (JSONArray) object.get(COLUMN_ALL_NEIGHBORHOODS_IN_SEARCH_REGION);
			Iterator neighborhoodIterator = neighborhoodListArray.iterator();
			
			while(neighborhoodIterator.hasNext()) {
				allNeighborhoodsInSearchRegion.add(new Neighborhood((JSONObject) neighborhoodIterator.next()));
			}
		}
		if(object.get(COLUMN_FILTERED_SEARCH_MATCHED_NO_HOTELS) != null) filteredSearchMatchedNoHotels = (Boolean) object.get(COLUMN_FILTERED_SEARCH_MATCHED_NO_HOTELS);
		if(object.get(COLUMN_STAR_OPTIONS) != null) {
			starOptions = new ArrayList<StarOption>();
			JSONArray starOptionListArray = (JSONArray) object.get(COLUMN_STAR_OPTIONS);
			Iterator starOptionIterator = starOptionListArray.iterator();
			
			while(starOptionIterator.hasNext()) {
				starOptions.add(new StarOption((JSONObject) starOptionIterator.next()));
			}
		}
		if(object.get(COLUMN_PRICE_OPTIONS) != null) {
			priceOptions = new ArrayList<PriceOption>();
			JSONArray priceOptionListArray = (JSONArray) object.get(COLUMN_PRICE_OPTIONS);
			Iterator priceOptionIterator = priceOptionListArray.iterator();
			
			while(priceOptionIterator.hasNext()) {
				priceOptions.add(new PriceOption((JSONObject) priceOptionIterator.next()));
			}
		}
		if(object.get(COLUMN_HOTEL_IDS_MISSING_OR_BAD_REGION_IDS) != null) {
			hotelIdsMissingOrBadRegionIds = new ArrayList<String>();
			JSONArray hotelIdList = (JSONArray) object.get(COLUMN_HOTEL_IDS_MISSING_OR_BAD_REGION_IDS);
			Iterator hotelIdIterator = hotelIdList.iterator();
			
			while(hotelIdIterator.hasNext()) {
				hotelIdsMissingOrBadRegionIds.add((String) hotelIdIterator.next());
			}
		}
	}

	public long getNumberOfRoomsRequested() {
		return numberOfRoomsRequested;
	}

	public void setNumberOfRoomsRequested(long numberOfRoomsRequested) {
		this.numberOfRoomsRequested = numberOfRoomsRequested;
	}

	public boolean isFilterUnavailableHotelsRequested() {
		return filterUnavailableHotelsRequested;
	}

	public void setFilterUnavailableHotelsRequested(boolean filterUnavailableHotelsRequested) {
		this.filterUnavailableHotelsRequested = filterUnavailableHotelsRequested;
	}

	public long getTotalHotelCount() {
		return totalHotelCount;
	}

	public void setTotalHotelCount(long totalHotelCount) {
		this.totalHotelCount = totalHotelCount;
	}

	public long getAvailableHotelCount() {
		return availableHotelCount;
	}

	public void setAvailableHotelCount(long availableHotelCount) {
		this.availableHotelCount = availableHotelCount;
	}

	public String getSearchRegionId() {
		return searchRegionId;
	}

	public void setSearchRegionId(String searchRegionId) {
		this.searchRegionId = searchRegionId;
	}

	public String getSearchRegionCity() {
		return searchRegionCity;
	}

	public void setSearchRegionCity(String searchRegionCity) {
		this.searchRegionCity = searchRegionCity;
	}

	public String getDeepLinkUrl() {
		return deepLinkUrl;
	}

	public void setDeepLinkUrl(String deepLinkUrl) {
		this.deepLinkUrl = deepLinkUrl;
	}

	public ArrayList<Hotel> getHotelList() {
		return hotelList;
	}

	public void setHotelList(ArrayList<Hotel> hotelList) {
		this.hotelList = hotelList;
	}

	public ArrayList<Neighborhood> getAllNeighborhoodsInSearchRegion() {
		return allNeighborhoodsInSearchRegion;
	}

	public void setAllNeighborhoodsInSearchRegion(ArrayList<Neighborhood> allNeighborhoodsInSearchRegion) {
		this.allNeighborhoodsInSearchRegion = allNeighborhoodsInSearchRegion;
	}

	public boolean isFilteredSearchMatchedNoHotels() {
		return filteredSearchMatchedNoHotels;
	}

	public void setFilteredSearchMatchedNoHotels(boolean filteredSearchMatchedNoHotels) {
		this.filteredSearchMatchedNoHotels = filteredSearchMatchedNoHotels;
	}

	public ArrayList<StarOption> getStarOptions() {
		return starOptions;
	}

	public void setStarOptions(ArrayList<StarOption> starOptions) {
		this.starOptions = starOptions;
	}

	public ArrayList<PriceOption> getPriceOptions() {
		return priceOptions;
	}

	public void setPriceOptions(ArrayList<PriceOption> priceOptions) {
		this.priceOptions = priceOptions;
	}

	public ArrayList<String> getHotelIdsMissingOrBadRegionIds() {
		return hotelIdsMissingOrBadRegionIds;
	}

	public void setHotelIdsMissingOrBadRegionIds(ArrayList<String> hotelIdsMissingOrBadRegionIds) {
		this.hotelIdsMissingOrBadRegionIds = hotelIdsMissingOrBadRegionIds;
	}
	
	
}
