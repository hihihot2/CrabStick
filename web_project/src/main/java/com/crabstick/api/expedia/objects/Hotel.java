package com.crabstick.api.expedia.objects;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Hotel {
	public static final String COLUMN_SORT_INDEX = "sortIndex";
	public static final String COLUMN_HOTEL_ID = "hotelId";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_LOCALIZED_NAME = "localizedName";
	public static final String COLUMN_NON_LOCALIZED_NAME = "nonLocalizedName";
	public static final String COLUMN_ADDRESS = "address";
	public static final String COLUMN_CITY = "city";
	public static final String COLUMN_STATE_PROVINCE_CODE = "stateProvinceCode";
	public static final String COLUMN_COUNTRY_CODE = "countryCode";
	public static final String COLUMN_POSTAL_CODE = "postalCode";
	public static final String COLUMN_AIRPORT_CODE = "airportCode";
	public static final String COLUMN_SUPPLIER_TYPE = "supplierType";
	public static final String COLUMN_HOTEL_STAR_RATING = "hotelStarRating";
	public static final String COLUMN_HOTEL_STAR_RATING_CSS_CLASS_NAME = "hotelStarRatingCssClassName";
	public static final String COLUMN_HOTEL_GUEST_RATING = "hotelGuestRating";
	public static final String COLUMN_TOTAL_RECOMMENDATIONS = "totalRecommendations";
	public static final String COLUMN_PERCENT_RECOMMENDED = "percentRecommended";
	public static final String COLUMN_TOTAL_REVIEWS = "totalReviews";
	public static final String COLUMN_SHORT_DESCRIPTION = "shortDescription";
	public static final String COLUMN_LOCATION_DESCRIPTION = "locationDescription";
	public static final String COLUMN_LOCATION_ID = "locationId";
	public static final String COLUMN_LOW_RATE = "lowRate";
	public static final String COLUMN_LOW_RATE_INFO = "lowRateInfo";
	public static final String COLUMN_RATE_CURRENCY_CODE = "rateCurrencyCode";
	public static final String COLUMN_RATE_CURRENCY_SYMBOL = "rateCurrencySymbol";
	public static final String COLUMN_ROOMS_LEFT_AT_THIS_RATE = "roomsLeftAtThisRate";
	public static final String COLUMN_LATITUDE = "latitude";
	public static final String COLUMN_LONGITUDE = "longitude";
	public static final String COLUMN_PROXIMITY_DISTANCE_IN_MILES = "proximityDistanceInMiles";
	public static final String COLUMN_PROXIMITY_DISTANCE_IN_KILO_METERS = "proximityDistanceInKiloMeters";
	public static final String COLUMN_LARGE_THUMBNAIL_URL = "largeThumbnailUrl";
	public static final String COLUMN_THUMBNAIL_URL = "thumbnailUrl";
	public static final String COLUMN_DISCOUNT_MESSAGE = "discountMessage";
	public static final String COLUMN_IS_DISCOUNT_RESTRICTED_TO_CURRENT_SOURCE_TYPE = "isDiscountRestrictedToCurrentSourceType";
	public static final String COLUMN_IS_SAME_DAY_DRR = "isSameDayDRR";
	public static final String COLUMN_IS_HOTEL_AVAILABLE = "isHotelAvailable";
	public static final String COLUMN_NOT_AVAILABLE_MESSAGE = "notAvailableMessage";
	public static final String COLUMN_IS_SPONSORED_LISTING = "isSponsoredListing";
	public static final String COLUMN_HAS_FREE_CANCELLATION = "hasFreeCancellation";
	public static final String COLUMN_AMENITIES = "amenities";
	public static final String COLUMN_DISTANCE_UNIT = "distanceUnit";
	public static final String COLUMN_DID_GET_BACK_HIGHEST_PRICE_FROM_SURVEY = "didGetBackHighestPriceFromSurvey";
	public static final String COLUMN_IS_DUDLEY = "isDudley";
	public static final String COLUMN_IS_VIP_ACCESS = "isVipAccess";
	public static final String COLUMN_IS_PAYMENT_CHOICE_AVAILABLE = "isPaymentChoiceAvailable";
	public static final String COLUMN_IS_SHOW_ETP_CHOICE = "isShowEtpChoice";
	public static final String COLUMN_JSON_HOTEL_BRAND = "jsonHotelBrand";
	public static final String COLUMN_IS_MEMBER_DEAL = "isMemberDeal";
	public static final String COLUMN_SHORT_DISCOUNT_MESSAGE = "shortDiscountMessage";
	public static final String COLUMN_IS_MOBILE_EXCLUSIVE = "isMobileExclusive";
	
	private String sortIndex;
	private String hotelId;
	private String name;
	private String localizedName;
	private String nonLocalizedName;
	private String address;
	private String city;
	private String stateProvinceCode;
	private String countryCode;
	private String postalCode;
	private String airportCode;
	private String supplierType;
	private String hotelStarRating;
	private String hotelStarRatingCssClassName;
	private String hotelGuestRating;
	private String totalRecommendations;
	private String percentRecommended;
	private String totalReviews;
	private String shortDescription;
	private String locationDescription;
	private String locationId;
	private String lowRate;
	private LowRateInfo lowRateInfo;
	private String rateCurrencyCode;
	private String rateCurrencySymbol;
	private String roomsLeftAtThisRate;
	private String latitude;
	private String longitude;
	private String proximityDistanceInMiles;
	private String proximityDistanceInKiloMeters;
	private String largeThumbnailUrl;
	private String thumbnailUrl;
	private String discountMessage;
	private boolean isDiscountRestrictedToCurrentSourceType;
	private boolean isSameDayDRR;
	private boolean isHotelAvailable;
	private String notAvailableMessage;
	private boolean isSponsoredListing;
	private boolean hasFreeCancellation;
	private ArrayList<Amenity> amenities;
	private String distanceUnit;
	private boolean didGetBackHighestPriceFromSurvey;
	private boolean isDudley;
	private boolean isVipAccess;
	private boolean isPaymentChoiceAvailable;
	private boolean isShowEtpChoice;
	private JsonHotelBrand jsonHotelBrand;
	private boolean isMemberDeal;
	private String shortDiscountMessage;
	private boolean isMobileExclusive;
	
	@Override
	public String toString() {
		return "Hotel [sortIndex=" + sortIndex + ", hotelId=" + hotelId + ", name=" + name + ", localizedName="
				+ localizedName + ", nonLocalizedName=" + nonLocalizedName + ", address=" + address + ", city=" + city
				+ ", stateProvinceCode=" + stateProvinceCode + ", countryCode=" + countryCode + ", postalCode="
				+ postalCode + ", airportCode=" + airportCode + ", supplierType=" + supplierType + ", hotelStarRating="
				+ hotelStarRating + ", hotelStarRatingCssClassName=" + hotelStarRatingCssClassName
				+ ", hotelGuestRating=" + hotelGuestRating + ", totalRecommendations=" + totalRecommendations
				+ ", percentRecommended=" + percentRecommended + ", totalReviews=" + totalReviews
				+ ", shortDescription=" + shortDescription + ", locationDescription=" + locationDescription
				+ ", locationId=" + locationId + ", lowRate=" + lowRate + ", lowRateInfo=" + lowRateInfo
				+ ", rateCurrencyCode=" + rateCurrencyCode + ", rateCurrencySymbol=" + rateCurrencySymbol
				+ ", roomsLeftAtThisRate=" + roomsLeftAtThisRate + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", proximityDistanceInMiles=" + proximityDistanceInMiles + ", proximityDistanceInKiloMeters="
				+ proximityDistanceInKiloMeters + ", largeThumbnailUrl=" + largeThumbnailUrl + ", thumbnailUrl="
				+ thumbnailUrl + ", discountMessage=" + discountMessage + ", isDiscountRestrictedToCurrentSourceType="
				+ isDiscountRestrictedToCurrentSourceType + ", isSameDayDRR=" + isSameDayDRR + ", isHotelAvailable="
				+ isHotelAvailable + ", notAvailableMessage=" + notAvailableMessage + ", isSponsoredListing="
				+ isSponsoredListing + ", hasFreeCancellation=" + hasFreeCancellation + ", amenities=" + amenities
				+ ", distanceUnit=" + distanceUnit + ", didGetBackHighestPriceFromSurvey="
				+ didGetBackHighestPriceFromSurvey + ", isDudley=" + isDudley + ", isVipAccess=" + isVipAccess
				+ ", isPaymentChoiceAvailable=" + isPaymentChoiceAvailable + ", isShowEtpChoice=" + isShowEtpChoice
				+ ", jsonHotelBrand=" + jsonHotelBrand + ", isMemberDeal=" + isMemberDeal + ", shortDiscountMessage="
				+ shortDiscountMessage + ", isMobileExclusive=" + isMobileExclusive + "]";
	}
	
	public Hotel(JSONObject hotel) {
		if(hotel.get(COLUMN_SORT_INDEX) != null) sortIndex = (String) hotel.get(COLUMN_SORT_INDEX);
		if(hotel.get(COLUMN_HOTEL_ID) != null) hotelId = (String) hotel.get(COLUMN_HOTEL_ID);
		if(hotel.get(COLUMN_NAME) != null) name = (String) hotel.get(COLUMN_NAME);
		if(hotel.get(COLUMN_LOCALIZED_NAME) != null) localizedName = (String) hotel.get(COLUMN_LOCALIZED_NAME);
		if(hotel.get(COLUMN_NON_LOCALIZED_NAME) != null) nonLocalizedName = (String) hotel.get(COLUMN_NON_LOCALIZED_NAME);
		if(hotel.get(COLUMN_ADDRESS) != null) address = (String) hotel.get(COLUMN_ADDRESS);
		if(hotel.get(COLUMN_CITY) != null) city = (String) hotel.get(COLUMN_CITY);
		if(hotel.get(COLUMN_STATE_PROVINCE_CODE) != null) stateProvinceCode = (String) hotel.get(COLUMN_STATE_PROVINCE_CODE);
		if(hotel.get(COLUMN_COUNTRY_CODE) != null) countryCode = (String) hotel.get(COLUMN_COUNTRY_CODE);
		if(hotel.get(COLUMN_POSTAL_CODE) != null) postalCode = (String) hotel.get(COLUMN_POSTAL_CODE);
		if(hotel.get(COLUMN_HOTEL_GUEST_RATING) != null) hotelGuestRating = (String) hotel.get(COLUMN_HOTEL_GUEST_RATING);
		if(hotel.get(COLUMN_TOTAL_RECOMMENDATIONS) != null) totalRecommendations = (String) hotel.get(COLUMN_TOTAL_RECOMMENDATIONS);
		if(hotel.get(COLUMN_PERCENT_RECOMMENDED) != null) percentRecommended = (String) hotel.get(COLUMN_PERCENT_RECOMMENDED);
		if(hotel.get(COLUMN_TOTAL_REVIEWS) != null) totalReviews = (String) hotel.get(COLUMN_TOTAL_REVIEWS);
		if(hotel.get(COLUMN_SHORT_DESCRIPTION) != null) shortDescription = (String) hotel.get(COLUMN_SHORT_DESCRIPTION);
		if(hotel.get(COLUMN_LOCATION_DESCRIPTION) != null) locationDescription = (String) hotel.get(COLUMN_LOCATION_DESCRIPTION);
		if(hotel.get(COLUMN_LOCATION_ID) != null) locationId = (String) hotel.get(COLUMN_LOCATION_ID);
		if(hotel.get(COLUMN_LOW_RATE) != null) lowRate = (String) hotel.get(COLUMN_LOW_RATE);
		if(hotel.get(COLUMN_LOW_RATE_INFO) != null) lowRateInfo = new LowRateInfo((JSONObject) hotel.get(COLUMN_LOW_RATE_INFO));
		if(hotel.get(COLUMN_RATE_CURRENCY_CODE) != null) rateCurrencyCode = (String) hotel.get(COLUMN_RATE_CURRENCY_CODE);
		if(hotel.get(COLUMN_RATE_CURRENCY_SYMBOL) != null) rateCurrencySymbol = (String) hotel.get(COLUMN_RATE_CURRENCY_SYMBOL);
		if(hotel.get(COLUMN_ROOMS_LEFT_AT_THIS_RATE) != null) roomsLeftAtThisRate = (String) hotel.get(COLUMN_ROOMS_LEFT_AT_THIS_RATE);
		if(hotel.get(COLUMN_LATITUDE) != null) latitude = (String) hotel.get(COLUMN_LATITUDE);
		if(hotel.get(COLUMN_LONGITUDE) != null) longitude = (String) hotel.get(COLUMN_LONGITUDE);
		if(hotel.get(COLUMN_PROXIMITY_DISTANCE_IN_MILES) != null) proximityDistanceInMiles = (String) hotel.get(COLUMN_PROXIMITY_DISTANCE_IN_MILES);
		if(hotel.get(COLUMN_PROXIMITY_DISTANCE_IN_KILO_METERS) != null) proximityDistanceInKiloMeters = (String) hotel.get(COLUMN_PROXIMITY_DISTANCE_IN_KILO_METERS);
		if(hotel.get(COLUMN_LARGE_THUMBNAIL_URL) != null) largeThumbnailUrl = (String) hotel.get(COLUMN_LARGE_THUMBNAIL_URL);
		if(hotel.get(COLUMN_THUMBNAIL_URL) != null) thumbnailUrl = (String) hotel.get(COLUMN_THUMBNAIL_URL);
		if(hotel.get(COLUMN_DISCOUNT_MESSAGE) != null) discountMessage = (String) hotel.get(COLUMN_DISCOUNT_MESSAGE);
		if(hotel.get(COLUMN_IS_DISCOUNT_RESTRICTED_TO_CURRENT_SOURCE_TYPE) != null) isDiscountRestrictedToCurrentSourceType = (Boolean) hotel.get(COLUMN_IS_DISCOUNT_RESTRICTED_TO_CURRENT_SOURCE_TYPE);
		if(hotel.get(COLUMN_IS_SAME_DAY_DRR) != null) isSameDayDRR = (Boolean) hotel.get(COLUMN_IS_SAME_DAY_DRR);
		if(hotel.get(COLUMN_IS_HOTEL_AVAILABLE) != null) isHotelAvailable = (Boolean) hotel.get(COLUMN_IS_HOTEL_AVAILABLE);
		if(hotel.get(COLUMN_NOT_AVAILABLE_MESSAGE) != null) notAvailableMessage = (String) hotel.get(COLUMN_NOT_AVAILABLE_MESSAGE);
		if(hotel.get(COLUMN_IS_SPONSORED_LISTING) != null) isSponsoredListing = (Boolean) hotel.get(COLUMN_IS_SPONSORED_LISTING);
		if(hotel.get(COLUMN_HAS_FREE_CANCELLATION) != null) hasFreeCancellation = (Boolean) hotel.get(COLUMN_HAS_FREE_CANCELLATION);
		if(hotel.get(COLUMN_AMENITIES) != null) {
			amenities = new ArrayList<Amenity>();
			JSONArray amenityArray = (JSONArray) hotel.get(COLUMN_AMENITIES);
			Iterator amenityIterator = amenityArray.iterator();
			
			while(amenityIterator.hasNext()) {
				amenities.add(new Amenity((JSONObject) amenityIterator.next()));
			}
		}
		if(hotel.get(COLUMN_DID_GET_BACK_HIGHEST_PRICE_FROM_SURVEY) != null) didGetBackHighestPriceFromSurvey = (Boolean) hotel.get(COLUMN_DID_GET_BACK_HIGHEST_PRICE_FROM_SURVEY);
		if(hotel.get(COLUMN_IS_DUDLEY) != null) isDudley = (Boolean) hotel.get(COLUMN_IS_DUDLEY);
		if(hotel.get(COLUMN_IS_VIP_ACCESS) != null) isVipAccess = (Boolean) hotel.get(COLUMN_IS_VIP_ACCESS);
		if(hotel.get(COLUMN_IS_PAYMENT_CHOICE_AVAILABLE) != null) isPaymentChoiceAvailable = (Boolean) hotel.get(COLUMN_IS_PAYMENT_CHOICE_AVAILABLE);
		if(hotel.get(COLUMN_IS_SHOW_ETP_CHOICE) != null) isShowEtpChoice = (Boolean) hotel.get(COLUMN_IS_SHOW_ETP_CHOICE);
		if(hotel.get(COLUMN_JSON_HOTEL_BRAND) != null) jsonHotelBrand = new JsonHotelBrand((JSONObject) hotel.get(COLUMN_JSON_HOTEL_BRAND));
		if(hotel.get(COLUMN_IS_MEMBER_DEAL) != null) isMemberDeal = (Boolean) hotel.get(COLUMN_IS_MEMBER_DEAL);
		if(hotel.get(COLUMN_SHORT_DISCOUNT_MESSAGE) != null) shortDiscountMessage = (String) hotel.get(COLUMN_SHORT_DISCOUNT_MESSAGE);
		if(hotel.get(COLUMN_IS_MOBILE_EXCLUSIVE) != null) isMobileExclusive = (Boolean) hotel.get(COLUMN_IS_MOBILE_EXCLUSIVE);
	}

	public Hotel(String sortIndex, String hotelId, String name, String localizedName, String nonLocalizedName,
			String address, String city, String stateProvinceCode, String countryCode, String postalCode,
			String airportCode, String supplierType, String hotelStarRating, String hotelStarRatingCssClassName,
			String hotelGuestRating, String totalRecommendations, String percentRecommended, String totalReviews,
			String shortDescription, String locationDescription, String locationId, String lowRate,
			LowRateInfo lowRateInfo, String rateCurrencyCode, String rateCurrencySymbol, String roomsLeftAtThisRate,
			String latitude, String longitude, String proximityDistanceInMiles, String proximityDistanceInKiloMeters,
			String largeThumbnailUrl, String thumbnailUrl, String discountMessage,
			boolean isDiscountRestrictedToCurrentSourceType, boolean isSameDayDRR, boolean isHotelAvailable,
			String notAvailableMessage, boolean isSponsoredListing, boolean hasFreeCancellation,
			ArrayList<Amenity> amenities, String distanceUnit, boolean didGetBackHighestPriceFromSurvey,
			boolean isDudley, boolean isVipAccess, boolean isPaymentChoiceAvailable, boolean isShowEtpChoice,
			JsonHotelBrand jsonHotelBrand, boolean isMemberDeal, String shortDiscountMessage,
			boolean isMobileExclusive) {
		super();
		this.sortIndex = sortIndex;
		this.hotelId = hotelId;
		this.name = name;
		this.localizedName = localizedName;
		this.nonLocalizedName = nonLocalizedName;
		this.address = address;
		this.city = city;
		this.stateProvinceCode = stateProvinceCode;
		this.countryCode = countryCode;
		this.postalCode = postalCode;
		this.airportCode = airportCode;
		this.supplierType = supplierType;
		this.hotelStarRating = hotelStarRating;
		this.hotelStarRatingCssClassName = hotelStarRatingCssClassName;
		this.hotelGuestRating = hotelGuestRating;
		this.totalRecommendations = totalRecommendations;
		this.percentRecommended = percentRecommended;
		this.totalReviews = totalReviews;
		this.shortDescription = shortDescription;
		this.locationDescription = locationDescription;
		this.locationId = locationId;
		this.lowRate = lowRate;
		this.lowRateInfo = lowRateInfo;
		this.rateCurrencyCode = rateCurrencyCode;
		this.rateCurrencySymbol = rateCurrencySymbol;
		this.roomsLeftAtThisRate = roomsLeftAtThisRate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.proximityDistanceInMiles = proximityDistanceInMiles;
		this.proximityDistanceInKiloMeters = proximityDistanceInKiloMeters;
		this.largeThumbnailUrl = largeThumbnailUrl;
		this.thumbnailUrl = thumbnailUrl;
		this.discountMessage = discountMessage;
		this.isDiscountRestrictedToCurrentSourceType = isDiscountRestrictedToCurrentSourceType;
		this.isSameDayDRR = isSameDayDRR;
		this.isHotelAvailable = isHotelAvailable;
		this.notAvailableMessage = notAvailableMessage;
		this.isSponsoredListing = isSponsoredListing;
		this.hasFreeCancellation = hasFreeCancellation;
		this.amenities = amenities;
		this.distanceUnit = distanceUnit;
		this.didGetBackHighestPriceFromSurvey = didGetBackHighestPriceFromSurvey;
		this.isDudley = isDudley;
		this.isVipAccess = isVipAccess;
		this.isPaymentChoiceAvailable = isPaymentChoiceAvailable;
		this.isShowEtpChoice = isShowEtpChoice;
		this.jsonHotelBrand = jsonHotelBrand;
		this.isMemberDeal = isMemberDeal;
		this.shortDiscountMessage = shortDiscountMessage;
		this.isMobileExclusive = isMobileExclusive;
	}

	public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(String sortIndex) {
		this.sortIndex = sortIndex;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocalizedName() {
		return localizedName;
	}

	public void setLocalizedName(String localizedName) {
		this.localizedName = localizedName;
	}

	public String getNonLocalizedName() {
		return nonLocalizedName;
	}

	public void setNonLocalizedName(String nonLocalizedName) {
		this.nonLocalizedName = nonLocalizedName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateProvinceCode() {
		return stateProvinceCode;
	}

	public void setStateProvinceCode(String stateProvinceCode) {
		this.stateProvinceCode = stateProvinceCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	public String getHotelStarRating() {
		return hotelStarRating;
	}

	public void setHotelStarRating(String hotelStarRating) {
		this.hotelStarRating = hotelStarRating;
	}

	public String getHotelStarRatingCssClassName() {
		return hotelStarRatingCssClassName;
	}

	public void setHotelStarRatingCssClassName(String hotelStarRatingCssClassName) {
		this.hotelStarRatingCssClassName = hotelStarRatingCssClassName;
	}

	public String getHotelGuestRating() {
		return hotelGuestRating;
	}

	public void setHotelGuestRating(String hotelGuestRating) {
		this.hotelGuestRating = hotelGuestRating;
	}

	public String getTotalRecommendations() {
		return totalRecommendations;
	}

	public void setTotalRecommendations(String totalRecommendations) {
		this.totalRecommendations = totalRecommendations;
	}

	public String getPercentRecommended() {
		return percentRecommended;
	}

	public void setPercentRecommended(String percentRecommended) {
		this.percentRecommended = percentRecommended;
	}

	public String getTotalReviews() {
		return totalReviews;
	}

	public void setTotalReviews(String totalReviews) {
		this.totalReviews = totalReviews;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLowRate() {
		return lowRate;
	}

	public void setLowRate(String lowRate) {
		this.lowRate = lowRate;
	}

	public LowRateInfo getLowRateInfo() {
		return lowRateInfo;
	}

	public void setLowRateInfo(LowRateInfo lowRateInfo) {
		this.lowRateInfo = lowRateInfo;
	}

	public String getRateCurrencyCode() {
		return rateCurrencyCode;
	}

	public void setRateCurrencyCode(String rateCurrencyCode) {
		this.rateCurrencyCode = rateCurrencyCode;
	}

	public String getRateCurrencySymbol() {
		return rateCurrencySymbol;
	}

	public void setRateCurrencySymbol(String rateCurrencySymbol) {
		this.rateCurrencySymbol = rateCurrencySymbol;
	}

	public String getRoomsLeftAtThisRate() {
		return roomsLeftAtThisRate;
	}

	public void setRoomsLeftAtThisRate(String roomsLeftAtThisRate) {
		this.roomsLeftAtThisRate = roomsLeftAtThisRate;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getProximityDistanceInMiles() {
		return proximityDistanceInMiles;
	}

	public void setProximityDistanceInMiles(String proximityDistanceInMiles) {
		this.proximityDistanceInMiles = proximityDistanceInMiles;
	}

	public String getProximityDistanceInKiloMeters() {
		return proximityDistanceInKiloMeters;
	}

	public void setProximityDistanceInKiloMeters(String proximityDistanceInKiloMeters) {
		this.proximityDistanceInKiloMeters = proximityDistanceInKiloMeters;
	}

	public String getLargeThumbnailUrl() {
		return largeThumbnailUrl;
	}

	public void setLargeThumbnailUrl(String largeThumbnailUrl) {
		this.largeThumbnailUrl = largeThumbnailUrl;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getDiscountMessage() {
		return discountMessage;
	}

	public void setDiscountMessage(String discountMessage) {
		this.discountMessage = discountMessage;
	}

	public boolean isDiscountRestrictedToCurrentSourceType() {
		return isDiscountRestrictedToCurrentSourceType;
	}

	public void setDiscountRestrictedToCurrentSourceType(boolean isDiscountRestrictedToCurrentSourceType) {
		this.isDiscountRestrictedToCurrentSourceType = isDiscountRestrictedToCurrentSourceType;
	}

	public boolean isSameDayDRR() {
		return isSameDayDRR;
	}

	public void setSameDayDRR(boolean isSameDayDRR) {
		this.isSameDayDRR = isSameDayDRR;
	}

	public boolean isHotelAvailable() {
		return isHotelAvailable;
	}

	public void setHotelAvailable(boolean isHotelAvailable) {
		this.isHotelAvailable = isHotelAvailable;
	}

	public String getNotAvailableMessage() {
		return notAvailableMessage;
	}

	public void setNotAvailableMessage(String notAvailableMessage) {
		this.notAvailableMessage = notAvailableMessage;
	}

	public boolean isSponsoredListing() {
		return isSponsoredListing;
	}

	public void setSponsoredListing(boolean isSponsoredListing) {
		this.isSponsoredListing = isSponsoredListing;
	}

	public boolean isHasFreeCancellation() {
		return hasFreeCancellation;
	}

	public void setHasFreeCancellation(boolean hasFreeCancellation) {
		this.hasFreeCancellation = hasFreeCancellation;
	}

	public ArrayList<Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(ArrayList<Amenity> amenities) {
		this.amenities = amenities;
	}

	public String getDistanceUnit() {
		return distanceUnit;
	}

	public void setDistanceUnit(String distanceUnit) {
		this.distanceUnit = distanceUnit;
	}

	public boolean isDidGetBackHighestPriceFromSurvey() {
		return didGetBackHighestPriceFromSurvey;
	}

	public void setDidGetBackHighestPriceFromSurvey(boolean didGetBackHighestPriceFromSurvey) {
		this.didGetBackHighestPriceFromSurvey = didGetBackHighestPriceFromSurvey;
	}

	public boolean isDudley() {
		return isDudley;
	}

	public void setDudley(boolean isDudley) {
		this.isDudley = isDudley;
	}

	public boolean isVipAccess() {
		return isVipAccess;
	}

	public void setVipAccess(boolean isVipAccess) {
		this.isVipAccess = isVipAccess;
	}

	public boolean isPaymentChoiceAvailable() {
		return isPaymentChoiceAvailable;
	}

	public void setPaymentChoiceAvailable(boolean isPaymentChoiceAvailable) {
		this.isPaymentChoiceAvailable = isPaymentChoiceAvailable;
	}

	public boolean isShowEtpChoice() {
		return isShowEtpChoice;
	}

	public void setShowEtpChoice(boolean isShowEtpChoice) {
		this.isShowEtpChoice = isShowEtpChoice;
	}

	public JsonHotelBrand getJsonHotelBrand() {
		return jsonHotelBrand;
	}

	public void setJsonHotelBrand(JsonHotelBrand jsonHotelBrand) {
		this.jsonHotelBrand = jsonHotelBrand;
	}

	public boolean isMemberDeal() {
		return isMemberDeal;
	}

	public void setMemberDeal(boolean isMemberDeal) {
		this.isMemberDeal = isMemberDeal;
	}

	public String getShortDiscountMessage() {
		return shortDiscountMessage;
	}

	public void setShortDiscountMessage(String shortDiscountMessage) {
		this.shortDiscountMessage = shortDiscountMessage;
	}

	public boolean isMobileExclusive() {
		return isMobileExclusive;
	}

	public void setMobileExclusive(boolean isMobileExclusive) {
		this.isMobileExclusive = isMobileExclusive;
	}
	
	
}
