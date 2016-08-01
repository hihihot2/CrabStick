package com.crabstick.api.foursquare.objects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Venue {
	public static final String[] COLUMN_NAMES = {"id", "name", "contact", "location", "categories", "verified", "stats", "url", "hours", "hasMenu", "menu", "price",
			"rating", "ratingColor", "ratingSignals", "allowMenuUrlEdit", "photos"};
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_CONTACT = "contact";
	public static final String COLUMN_LOCATION = "location";
	public static final String COLUMN_CATEGORIES = "categories";
	public static final String COLUMN_VERIFIED = "verified";
	public static final String COLUMN_STATS = "stats";
	public static final String COLUMN_URL = "url";
	public static final String COLUMN_HOURS = "hours";
	public static final String COLUMN_HAS_MENU = "hasMenu";
	public static final String COLUMN_MENU = "menu";
	public static final String COLUMN_PRICE = "price";
	public static final String COLUMN_RATING = "rating";
	public static final String COLUMN_RATING_COLOR = "ratingColor";
	public static final String COLUMN_RATING_SIGNALS = "ratingSignals";
	public static final String COLUMN_ALLOW_MENU_URL_EDIT = "allowMenuUrlEdit";
	public static final String COLUMN_PHOTOS = "photos";
	
	private String id;
	private String name;
	private Contact contact;
	private Location location;
	private Category category;
	private boolean verified;
	private Stats stats;
	private String url;
	private Hour hour;
	private boolean hasMenu;
	private Menu menu;
	private Price price;
	private double rating;
	private String ratingColor;
	private long ratingSignals;
	private boolean allowMenuUrlEdit;
	private Photos photos;
	
	
	@Override
	public String toString() {
		return "Venue [id=" + id + ", name=" + name + ", contact=" + contact + ", location=" + location + ", category="
				+ category + ", verified=" + verified + ", stats=" + stats + ", url=" + url + ", hour=" + hour
				+ ", hasMenu=" + hasMenu + ", menu=" + menu + ", price=" + price + ", rating=" + rating
				+ ", ratingColor=" + ratingColor + ", ratingSignals=" + ratingSignals + ", allowMenuUrlEdit="
				+ allowMenuUrlEdit + ", photos=" + photos + "]";
	}

	public Venue(String id, String name, Contact contact, Location location, Category category, boolean verified,
			Stats stats, String url, Hour hour, boolean hasMenu, Menu menu, Price price, Double rating,
			String ratingColor, long ratingSignals, boolean allowMenuUrlEdit, Photos photos) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.location = location;
		this.category = category;
		this.verified = verified;
		this.stats = stats;
		this.url = url;
		this.hour = hour;
		this.hasMenu = hasMenu;
		this.menu = menu;
		this.price = price;
		this.rating = rating;
		this.ratingColor = ratingColor;
		this.ratingSignals = ratingSignals;
		this.allowMenuUrlEdit = allowMenuUrlEdit;
		this.photos = photos;
	}
	
	public Venue(JSONObject venue) {
		super();
		if(venue.get(COLUMN_ID) != null) id = (String) venue.get(COLUMN_ID);
		if(venue.get(COLUMN_NAME) != null) name = (String) venue.get(COLUMN_NAME);
		if(venue.get(COLUMN_CONTACT) != null) contact = new Contact((JSONObject) venue.get(COLUMN_CONTACT));
		if(venue.get(COLUMN_LOCATION) != null) location = new Location((JSONObject) venue.get(COLUMN_LOCATION));
		if(venue.get(COLUMN_CATEGORIES) != null) category = new Category((JSONObject) ((JSONArray) venue.get(COLUMN_CATEGORIES)).get(0));
		if(venue.get(COLUMN_VERIFIED) != null) verified = (Boolean) venue.get(COLUMN_VERIFIED);
		if(venue.get(COLUMN_STATS) != null) stats = new Stats((JSONObject) venue.get(COLUMN_STATS));
		if(venue.get(COLUMN_URL) != null) url = (String) venue.get(COLUMN_URL);
		if(venue.get(COLUMN_HOURS) != null) hour = new Hour((JSONObject) venue.get(COLUMN_HOURS));
		if(venue.get(COLUMN_HAS_MENU) != null) hasMenu = (Boolean) venue.get(COLUMN_HAS_MENU);
		if(venue.get(COLUMN_PRICE) != null) price = new Price((JSONObject) venue.get(COLUMN_PRICE));
		if(venue.get(COLUMN_RATING) != null) rating = (Double) venue.get(COLUMN_RATING);
		if(venue.get(COLUMN_RATING_COLOR) != null) ratingColor = (String) venue.get(COLUMN_RATING_COLOR);
		if(venue.get(COLUMN_RATING_SIGNALS) != null) ratingSignals = (Long) venue.get(COLUMN_RATING_SIGNALS);
		if(venue.get(COLUMN_ALLOW_MENU_URL_EDIT) != null) allowMenuUrlEdit = (Boolean) venue.get(COLUMN_ALLOW_MENU_URL_EDIT);
		if(venue.get(COLUMN_PHOTOS) != null) {}
	}

	public Venue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Hour getHour() {
		return hour;
	}

	public void setHour(Hour hour) {
		this.hour = hour;
	}

	public boolean isHasMenu() {
		return hasMenu;
	}

	public void setHasMenu(boolean hasMenu) {
		this.hasMenu = hasMenu;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getRatingColor() {
		return ratingColor;
	}

	public void setRatingColor(String ratingColor) {
		this.ratingColor = ratingColor;
	}

	public long getRatingSignals() {
		return ratingSignals;
	}

	public void setRatingSignals(long ratingSignals) {
		this.ratingSignals = ratingSignals;
	}

	public boolean isAllowMenuUrlEdit() {
		return allowMenuUrlEdit;
	}

	public void setAllowMenuUrlEdit(boolean allowMenuUrlEdit) {
		this.allowMenuUrlEdit = allowMenuUrlEdit;
	}

	public Photos getPhotos() {
		return photos;
	}

	public void setPhotos(Photos photos) {
		this.photos = photos;
	}
}
