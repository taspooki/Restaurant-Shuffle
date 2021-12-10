import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class utilities {

	private static JSONObject makeRequest(String location, String categories, String price) throws IOException {
		// Base url for HTTP request to Yelp API
		String url = "https://api.yelp.com/v3/businesses/search?open_now=true&term=food";
		// Concatenate url String with location search query data
		url += "&location=" + location;
		// Check for -1 flag
		try {
			if (Integer.parseInt(price) != -1 || containsChar(price) == false) {
				// If String price is in format we like, concatenate our url String with price search query information
				url += "&price=" + price;
			}
		} catch (java.lang.NumberFormatException e) {
			// TODO: handle exception
		}
		
		// Check for -1 flag
		if (categories != "-1") {
			// Concatenate our url String with search categories information if data is valid
			url += "&categories=" + categories;
		}
		//
		System.err.println("URL being sent to API: " + url);
		// Send HTTP Request
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		// Set Request method to GET
		connection.setRequestMethod("GET");
		// Set request Header to Bearer and our Yelp API key
		connection.setRequestProperty("Authorization", "Bearer 4DRYEsEaNvDtwM7yBtS0KaNNLr282Fg0JVe67DrQSpM9ycZHFhZPUrK1-24Y6x-7g9deHjyROXP-w4n0BFMUHP-1EeflzSiBsSGjTOHrW-CvhJUmIfTbfxyUeLWuYXYx");
		
		// TODO: fix try & catch for this section, handle exception, possibly remove throws clause
		
		//
		try (BufferedReader in = new BufferedReader(
				// InputStreamReader to read our JSON from Yelp API
                new InputStreamReader(connection.getInputStream()))) {
			// StringBuilder instance to help build our custom String with JSON data
            StringBuilder stringBuilder = new StringBuilder();
            String JSONString;

            while ((JSONString = in.readLine()) != null) {
            	stringBuilder.append(JSONString);            
            }            
            JSONObject yelpJSONObject = new JSONObject(stringBuilder.toString());
            // Testing, TODO Delete after
            System.out.println();
            System.err.println(yelpJSONObject);
            // End connection
            connection.disconnect();
            // Return JSON object with JSON data packed inside of it from Yelp API request
            return yelpJSONObject;            
	}		
	}	
	public static Restaurant parseAndPopulate(String location, String categories, String price) throws IOException {
		// Create instance of Restaurant to be populated later
		Restaurant restaurantObject = new Restaurant();
		int rand;
		Random randNumber = new Random();
		rand = randNumber.nextInt(20);
		try {
			 // Make HTTP request to yelp API, returned JSONObject
			 JSONObject root = makeRequest(location, categories, price);
			 // Enter JSON directory
			 JSONArray businesses = root.getJSONArray("businesses");
			 // Enter random JSON restaurant entry
			 JSONObject jsonBusiness = businesses.getJSONObject(rand);
				
			 	// Set key information in Restaurant instance
				restaurantObject.setName(jsonBusiness.getString("name"));
				restaurantObject.setRating(jsonBusiness.getInt("rating"));
				restaurantObject.setReview_count(jsonBusiness.getInt("review_count"));
				restaurantObject.setPhoneNumber(jsonBusiness.getString("display_phone"));
				restaurantObject.setUrl(jsonBusiness.getString("url"));
				restaurantObject.setPrice(jsonBusiness.getString("price"));
		}
		// Catch an issue where too few results are being returned and rand number does not exist in JSON
		catch (org.json.JSONException e) {
			// Make new HTTP request
			JSONObject root = makeRequest(location, categories, price);
			 // Find int for total amount of restaurants returned
			 rand = root.getInt("total");
			 rand = randNumber.nextInt(rand/2);
			 System.err.println(rand); // information 
			 JSONArray businesses = root.getJSONArray("businesses");

			 JSONObject jsonBusiness = businesses.getJSONObject(rand);
										
				restaurantObject.setName(jsonBusiness.getString("name"));
				restaurantObject.setRating(jsonBusiness.getInt("rating"));
				restaurantObject.setReview_count(jsonBusiness.getInt("review_count"));
				restaurantObject.setPhoneNumber(jsonBusiness.getString("display_phone"));
				restaurantObject.setUrl(jsonBusiness.getString("url"));
				restaurantObject.setPrice(jsonBusiness.getString("price"));
		}
		return restaurantObject;
	}
	private static Boolean containsChar(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (Character.isAlphabetic(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}
}