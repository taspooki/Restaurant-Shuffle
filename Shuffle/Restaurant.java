public class Restaurant {
	
	private String name;
	private String phoneNumber;
	private String url;
	private String price;
	private int rating;
	private int review_count;
		
public Restaurant() {
	this.name = null;
	this.phoneNumber = null;
	this.price = null;
	this.url = null;
	this.rating = -1;
	this.review_count = -1;
}



// Getters and setters

public String getPrice() {
	return price;
}



public void setPrice(String price) {
	this.price = price;
}

public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

public int getRating() {
	return rating;
}

public void setRating(int rating) {
	this.rating = rating;
}

public int getReview_count() {
	return review_count;
}

public void setReview_count(int review_count) {
	this.review_count = review_count;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
}