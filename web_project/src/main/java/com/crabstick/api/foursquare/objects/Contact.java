package com.crabstick.api.foursquare.objects;

import org.json.simple.JSONObject;

public class Contact {
	public static final String COLUMN_PHONE = "phone";
	public static final String COLUMN_FORMATTED_PHONE = "formattedPhone";
	public static final String COLUMN_TWITTER = "twitter";
	public static final String COLUMN_FACEBOOK = "facebook";
	public static final String COLUMN_FACEBOOK_USER_NAME = "facebookUserName";
	public static final String COLUMN_FACEBOOK_NAME = "facebookName";
	
	private String phone;
	private String formattedPhone;
	private String twitter;
	private String facebook;
	private String facebookUserName;
	private String facebookName;
	
	public Contact(String phone, String formattedPhone, String twitter, String facebook, String facebookUserName,
			String facebookName) {
		super();
		this.phone = phone;
		this.formattedPhone = formattedPhone;
		this.twitter = twitter;
		this.facebook = facebook;
		this.facebookUserName = facebookUserName;
		this.facebookName = facebookName;
	}
	
	public Contact(JSONObject contact) {
		super();
		if(contact.get(COLUMN_PHONE) != null) phone = (String) contact.get(COLUMN_PHONE);
		if(contact.get(COLUMN_FORMATTED_PHONE) != null) formattedPhone = (String) contact.get(COLUMN_FORMATTED_PHONE);
		if(contact.get(COLUMN_TWITTER) != null) twitter = (String) contact.get(COLUMN_TWITTER);
		if(contact.get(COLUMN_FACEBOOK) != null) facebook = (String) contact.get(COLUMN_FACEBOOK);
		if(contact.get(COLUMN_FACEBOOK_USER_NAME) != null) facebookUserName = (String) contact.get(COLUMN_FACEBOOK_USER_NAME);
		if(contact.get(COLUMN_FACEBOOK_NAME) != null) facebookName = (String) contact.get(COLUMN_FACEBOOK_NAME);
	}

	@Override
	public String toString() {
		return "Contact [phone=" + phone + ", formattedPhone=" + formattedPhone + ", twitter=" + twitter + ", facebook="
				+ facebook + ", facebookUserName=" + facebookUserName + ", facebookName=" + facebookName + "]";
	}

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFormattedPhone() {
		return formattedPhone;
	}

	public void setFormattedPhone(String formattedPhone) {
		this.formattedPhone = formattedPhone;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getFacebookUserName() {
		return facebookUserName;
	}

	public void setFacebookUserName(String facebookUserName) {
		this.facebookUserName = facebookUserName;
	}

	public String getFacebookName() {
		return facebookName;
	}

	public void setFacebookName(String facebookName) {
		this.facebookName = facebookName;
	}
	
	
	
}
