package com.crabstick.api.expedia.objects;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LowRateInfo {
	public static final String COLUMN_MAX_NIGHTLY_RATE = "maxNightlyRate";
	public static final String COLUMN_AVERAGE_RATE = "averageRate";
	public static final String COLUMN_TAX_STATUS_TYPE = "taxStatusType";
	public static final String COLUMN_AVERAGE_BASE_RATE = "averageBaseRate";
	public static final String COLUMN_NIGHTLY_RATE_TOTAL = "nightlyRateTotal";
	public static final String COLUMN_DISCOUNT_PERCENT = "discountPercent";
	public static final String COLUMN_TOTAL = "total";
	public static final String COLUMN_CURRENCY_CODE = "currencyCode";
	public static final String COLUMN_CURRENCY_SYMBOL = "currencySymbol";
	public static final String COLUMN_NIGHTLY_RATES_PER_ROOM = "nightlyRatesPerRoom";
	public static final String COLUMN_PRICE_TO_SHOW_USERS = "priceToShowUsers";
	public static final String COLUMN_STRIKETHROUGH_PRICE_TO_SHOW_USERS = "strikethroughPriceToShowUsers";
	public static final String COLUMN_STRIKETHROUGH_PRICE_WITH_TAXES_AND_FEES_TO_SHOW_USERS = "strikethroughPriceWithTaxesAndFeesToShowUsers";
	public static final String COLUMN_TOTAL_MANDATORY_FEES = "totalMandatoryFees";
	public static final String COLUMN_TOTAL_PRICE_WITH_MANDATORY_FEES = "totalPriceWithMandatoryFees";
	public static final String COLUMN_FORMATTED_TOTAL_PRICE_WITH_MANDATORY_FEES = "formattedTotalPriceWithMandatoryFees";
	public static final String COLUMN_USER_PRICE_TYPE = "userPriceType";
	public static final String COLUMN_PRICE_ADJUSTMENTS = "priceAdjustments";
	public static final String COLUMN_CHECKOUT_PRICE_TYPE = "checkoutPriceType";
	public static final String COLUMN_AIR_ATTACHED = "airAttached";
	public static final String COLUMN_ROOM_TYPE_CODE = "roomTypeCode";
	public static final String COLUMN_RATE_PLAN_CODE = "ratePlanCode";
	public static final String COLUMN_SHOW_RESORT_FEE_MESSAGE = "showResortFeeMessage";
	public static final String COLUMN_RESORT_FEE_INCLUSION = "resortFeeInclusion";
	
	private String maxNightlyRate;
	private String averageRate;
	private String taxStatusType;
	private String averageBaseRate;
	private String nightlyRateTotal;
	private String discountPercent;
	private String total;
	private String currencyCode;
	private String currencySymbol;
	private ArrayList<NightlyRate> nightlyRatesPerRoom;
	private String priceToShowUsers;
	private String strikethroughPriceToShowUsers;
	private String strikethroughPriceWithTaxesAndFeesToShowUsers;
	private String totalMandatoryFees;
	private String totalPriceWithMandatoryFees;
	private String formattedTotalPriceWithMandatoryFees;
	private String userPriceType;
//	private Object priceAdjustments;
	private String checkoutPriceType;
	private boolean airAttached;
	private String roomTypeCode;
	private String ratePlanCode;
	private boolean showResortFeeMessage;
	private boolean resortFeeInclusion;
	
	@Override
	public String toString() {
		return "LowRateInfo [maxNightlyRate=" + maxNightlyRate + ", averageRate=" + averageRate + ", taxStatusType="
				+ taxStatusType + ", averageBaseRate=" + averageBaseRate + ", nightlyRateTotal=" + nightlyRateTotal
				+ ", discountPercent=" + discountPercent + ", total=" + total + ", currencyCode=" + currencyCode
				+ ", currencySymbol=" + currencySymbol + ", nightlyRatesPerRoom=" + nightlyRatesPerRoom
				+ ", priceToShowUsers=" + priceToShowUsers + ", strikethroughPriceToShowUsers="
				+ strikethroughPriceToShowUsers + ", strikethroughPriceWithTaxesAndFeesToShowUsers="
				+ strikethroughPriceWithTaxesAndFeesToShowUsers + ", totalMandatoryFees=" + totalMandatoryFees
				+ ", totalPriceWithMandatoryFees=" + totalPriceWithMandatoryFees
				+ ", formattedTotalPriceWithMandatoryFees=" + formattedTotalPriceWithMandatoryFees + ", userPriceType="
				+ userPriceType + ", checkoutPriceType=" + checkoutPriceType + ", airAttached=" + airAttached
				+ ", roomTypeCode=" + roomTypeCode + ", ratePlanCode=" + ratePlanCode + ", showResortFeeMessage="
				+ showResortFeeMessage + ", resortFeeInclusion=" + resortFeeInclusion + "]";
	}

	public LowRateInfo(String maxNightlyRate, String averageRate, String taxStatusType, String averageBaseRate,
			String nightlyRateTotal, String discountPercent, String total, String currencyCode, String currencySymbol,
			ArrayList<NightlyRate> nightlyRatesPerRoom, String priceToShowUsers, String strikethroughPriceToShowUsers,
			String strikethroughPriceWithTaxesAndFeesToShowUsers, String totalMandatoryFees,
			String totalPriceWithMandatoryFees, String formattedTotalPriceWithMandatoryFees, String userPriceType,
			String checkoutPriceType, boolean airAttached, String roomTypeCode, String ratePlanCode,
			boolean showResortFeeMessage, boolean resortFeeInclusion) {
		super();
		this.maxNightlyRate = maxNightlyRate;
		this.averageRate = averageRate;
		this.taxStatusType = taxStatusType;
		this.averageBaseRate = averageBaseRate;
		this.nightlyRateTotal = nightlyRateTotal;
		this.discountPercent = discountPercent;
		this.total = total;
		this.currencyCode = currencyCode;
		this.currencySymbol = currencySymbol;
		this.nightlyRatesPerRoom = nightlyRatesPerRoom;
		this.priceToShowUsers = priceToShowUsers;
		this.strikethroughPriceToShowUsers = strikethroughPriceToShowUsers;
		this.strikethroughPriceWithTaxesAndFeesToShowUsers = strikethroughPriceWithTaxesAndFeesToShowUsers;
		this.totalMandatoryFees = totalMandatoryFees;
		this.totalPriceWithMandatoryFees = totalPriceWithMandatoryFees;
		this.formattedTotalPriceWithMandatoryFees = formattedTotalPriceWithMandatoryFees;
		this.userPriceType = userPriceType;
		this.checkoutPriceType = checkoutPriceType;
		this.airAttached = airAttached;
		this.roomTypeCode = roomTypeCode;
		this.ratePlanCode = ratePlanCode;
		this.showResortFeeMessage = showResortFeeMessage;
		this.resortFeeInclusion = resortFeeInclusion;
	}

	public LowRateInfo(JSONObject jsonObject) {
		if(jsonObject.get(COLUMN_MAX_NIGHTLY_RATE) != null) maxNightlyRate = (String) jsonObject.get(COLUMN_MAX_NIGHTLY_RATE);
		if(jsonObject.get(COLUMN_AVERAGE_RATE) != null) averageRate = (String) jsonObject.get(COLUMN_AVERAGE_RATE);
		if(jsonObject.get(COLUMN_TAX_STATUS_TYPE) != null) taxStatusType = (String) jsonObject.get(COLUMN_TAX_STATUS_TYPE);
		if(jsonObject.get(COLUMN_AVERAGE_BASE_RATE) != null) averageBaseRate = (String) jsonObject.get(COLUMN_AVERAGE_BASE_RATE);
		if(jsonObject.get(COLUMN_NIGHTLY_RATE_TOTAL) != null) nightlyRateTotal = (String) jsonObject.get(COLUMN_NIGHTLY_RATE_TOTAL);
		if(jsonObject.get(COLUMN_DISCOUNT_PERCENT) != null) discountPercent = (String) jsonObject.get(COLUMN_DISCOUNT_PERCENT).toString();
		if(jsonObject.get(COLUMN_TOTAL) != null) total = (String) jsonObject.get(COLUMN_TOTAL);
		if(jsonObject.get(COLUMN_CURRENCY_CODE) != null) currencyCode = (String) jsonObject.get(COLUMN_CURRENCY_CODE);
		if(jsonObject.get(COLUMN_CURRENCY_SYMBOL) != null) currencySymbol = (String) jsonObject.get(COLUMN_CURRENCY_SYMBOL);
		if(jsonObject.get(COLUMN_NIGHTLY_RATES_PER_ROOM) != null) {
			nightlyRatesPerRoom = new ArrayList<NightlyRate>();
			JSONArray nightlyRateList = (JSONArray) jsonObject.get(COLUMN_NIGHTLY_RATES_PER_ROOM);
			Iterator nightlyRateIterator = nightlyRateList.iterator();
			
			while(nightlyRateIterator.hasNext()) {
				nightlyRatesPerRoom.add(new NightlyRate((JSONObject) nightlyRateIterator.next()));
			}
		}
		if(jsonObject.get(COLUMN_PRICE_TO_SHOW_USERS) != null) priceToShowUsers = (String) jsonObject.get(COLUMN_PRICE_TO_SHOW_USERS);
		if(jsonObject.get(COLUMN_STRIKETHROUGH_PRICE_TO_SHOW_USERS) != null) strikethroughPriceToShowUsers = (String) jsonObject.get(COLUMN_STRIKETHROUGH_PRICE_TO_SHOW_USERS);
		if(jsonObject.get(COLUMN_STRIKETHROUGH_PRICE_WITH_TAXES_AND_FEES_TO_SHOW_USERS) != null) strikethroughPriceWithTaxesAndFeesToShowUsers = (String) jsonObject.get(COLUMN_STRIKETHROUGH_PRICE_WITH_TAXES_AND_FEES_TO_SHOW_USERS);
		if(jsonObject.get(COLUMN_TOTAL_MANDATORY_FEES) != null) totalMandatoryFees = (String) jsonObject.get(COLUMN_TOTAL_MANDATORY_FEES);
		if(jsonObject.get(COLUMN_TOTAL_PRICE_WITH_MANDATORY_FEES) != null) totalPriceWithMandatoryFees = (String) jsonObject.get(COLUMN_TOTAL_PRICE_WITH_MANDATORY_FEES);
		if(jsonObject.get(COLUMN_FORMATTED_TOTAL_PRICE_WITH_MANDATORY_FEES) != null) formattedTotalPriceWithMandatoryFees = (String) jsonObject.get(COLUMN_FORMATTED_TOTAL_PRICE_WITH_MANDATORY_FEES);
		if(jsonObject.get(COLUMN_USER_PRICE_TYPE) != null) userPriceType = (String) jsonObject.get(COLUMN_USER_PRICE_TYPE);
		if(jsonObject.get(COLUMN_CHECKOUT_PRICE_TYPE) != null) checkoutPriceType = (String) jsonObject.get(COLUMN_CHECKOUT_PRICE_TYPE);
		if(jsonObject.get(COLUMN_AIR_ATTACHED) != null) airAttached = (Boolean) jsonObject.get(COLUMN_AIR_ATTACHED);
		if(jsonObject.get(COLUMN_ROOM_TYPE_CODE) != null) roomTypeCode = (String) jsonObject.get(COLUMN_ROOM_TYPE_CODE);
		if(jsonObject.get(COLUMN_RATE_PLAN_CODE) != null) ratePlanCode = (String) jsonObject.get(COLUMN_RATE_PLAN_CODE);
		if(jsonObject.get(COLUMN_SHOW_RESORT_FEE_MESSAGE) != null) showResortFeeMessage = (Boolean) jsonObject.get(COLUMN_SHOW_RESORT_FEE_MESSAGE);
		if(jsonObject.get(COLUMN_RESORT_FEE_INCLUSION) != null) resortFeeInclusion = (Boolean) jsonObject.get(COLUMN_RESORT_FEE_INCLUSION);
		// TODO: 더 추가해야됨
	}

	public LowRateInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMaxNightlyRate() {
		return maxNightlyRate;
	}

	public void setMaxNightlyRate(String maxNightlyRate) {
		this.maxNightlyRate = maxNightlyRate;
	}

	public String getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(String averageRate) {
		this.averageRate = averageRate;
	}

	public String getTaxStatusType() {
		return taxStatusType;
	}

	public void setTaxStatusType(String taxStatusType) {
		this.taxStatusType = taxStatusType;
	}

	public String getAverageBaseRate() {
		return averageBaseRate;
	}

	public void setAverageBaseRate(String averageBaseRate) {
		this.averageBaseRate = averageBaseRate;
	}

	public String getNightlyRateTotal() {
		return nightlyRateTotal;
	}

	public void setNightlyRateTotal(String nightlyRateTotal) {
		this.nightlyRateTotal = nightlyRateTotal;
	}

	public String getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(String discountPercent) {
		this.discountPercent = discountPercent;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public ArrayList<NightlyRate> getNightlyRatesPerRoom() {
		return nightlyRatesPerRoom;
	}

	public void setNightlyRatesPerRoom(ArrayList<NightlyRate> nightlyRatesPerRoom) {
		this.nightlyRatesPerRoom = nightlyRatesPerRoom;
	}

	public String getPriceToShowUsers() {
		return priceToShowUsers;
	}

	public void setPriceToShowUsers(String priceToShowUsers) {
		this.priceToShowUsers = priceToShowUsers;
	}

	public String getStrikethroughPriceToShowUsers() {
		return strikethroughPriceToShowUsers;
	}

	public void setStrikethroughPriceToShowUsers(String strikethroughPriceToShowUsers) {
		this.strikethroughPriceToShowUsers = strikethroughPriceToShowUsers;
	}

	public String getStrikethroughPriceWithTaxesAndFeesToShowUsers() {
		return strikethroughPriceWithTaxesAndFeesToShowUsers;
	}

	public void setStrikethroughPriceWithTaxesAndFeesToShowUsers(String strikethroughPriceWithTaxesAndFeesToShowUsers) {
		this.strikethroughPriceWithTaxesAndFeesToShowUsers = strikethroughPriceWithTaxesAndFeesToShowUsers;
	}

	public String getTotalMandatoryFees() {
		return totalMandatoryFees;
	}

	public void setTotalMandatoryFees(String totalMandatoryFees) {
		this.totalMandatoryFees = totalMandatoryFees;
	}

	public String getTotalPriceWithMandatoryFees() {
		return totalPriceWithMandatoryFees;
	}

	public void setTotalPriceWithMandatoryFees(String totalPriceWithMandatoryFees) {
		this.totalPriceWithMandatoryFees = totalPriceWithMandatoryFees;
	}

	public String getFormattedTotalPriceWithMandatoryFees() {
		return formattedTotalPriceWithMandatoryFees;
	}

	public void setFormattedTotalPriceWithMandatoryFees(String formattedTotalPriceWithMandatoryFees) {
		this.formattedTotalPriceWithMandatoryFees = formattedTotalPriceWithMandatoryFees;
	}

	public String getUserPriceType() {
		return userPriceType;
	}

	public void setUserPriceType(String userPriceType) {
		this.userPriceType = userPriceType;
	}

	public String getCheckoutPriceType() {
		return checkoutPriceType;
	}

	public void setCheckoutPriceType(String checkoutPriceType) {
		this.checkoutPriceType = checkoutPriceType;
	}

	public boolean isAirAttached() {
		return airAttached;
	}

	public void setAirAttached(boolean airAttached) {
		this.airAttached = airAttached;
	}

	public String getRoomTypeCode() {
		return roomTypeCode;
	}

	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}

	public String getRatePlanCode() {
		return ratePlanCode;
	}

	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}

	public boolean isShowResortFeeMessage() {
		return showResortFeeMessage;
	}

	public void setShowResortFeeMessage(boolean showResortFeeMessage) {
		this.showResortFeeMessage = showResortFeeMessage;
	}

	public boolean isResortFeeInclusion() {
		return resortFeeInclusion;
	}

	public void setResortFeeInclusion(boolean resortFeeInclusion) {
		this.resortFeeInclusion = resortFeeInclusion;
	}
	
	
}
