import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class utilities {
/**
 * makeRequest()
 * 
 * Private helper method that makes an HTTP request to Yelp API.
 * Gets back JSONObject filled with Restaurant data from request.
 * parseAndPopulate() calls this method.
 * 
 * @param String location   - User input of ZIP code, address, or city.
 * @param String categories - User input of category of food they want.
 * @param String price      - User input of price range user wants.
 * 
 * @return - JSONOBJECT - Object of type JSON that has Yelp API request data.
 * 
 * @throws IOException
 */
	private static JSONObject makeRequest(String location, String categories, String price) throws IOException {
		// Base url for HTTP request to Yelp API
		String url = "https://api.yelp.com/v3/businesses/search?open_now=true&term=food&limit=10";
		
		// Remove any spaces from location, categories, price inputs from user
		location = removeSpaces(location);
		categories = removeSpaces(categories);
		price = removeSpaces(price);
		
		// Concatenate url String with location search query data
		url += "&location=" + location;
				
		// Assign -1 flags empty and incompatible Strings
		if (price == "" || true == containsChar(price)) {
	         price = "-1";    // If price is left blank, assign price to -1 flag for later
		}
		
		if(categories == ""){
	          categories = "-1";  // If categories is left blank, assign price to -1 flag for later
	        }
				
		// Check for -1 flag in price
			if (price != "-1") {
				// If String price is in format we like, concatenate our url String with price search query information
				url += "&price=" + price;
			}
			
		// Check for -1 flag in categories		
		if (categories != "-1") {
			// Concatenate our url String with search categories information if data is valid
			url += "&categories=" + categories;
		}		
		// Debugging information in console
		System.err.println("URL being sent to API: " + url);		
		// Send HTTP Request
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		// Set Request method to GET
		connection.setRequestMethod("GET");
		// Set request Header to Bearer and our Yelp API key
		connection.setRequestProperty("Authorization", "Bearer 4DRYEsEaNvDtwM7yBtS0KaNNLr282Fg0JVe67DrQSpM9ycZHFhZPUrK1-24Y6x-7g9deHjyROXP-w4n0BFMUHP-1EeflzSiBsSGjTOHrW-CvhJUmIfTbfxyUeLWuYXYx");
		
		 // InputStreamReader to read our JSON from Yelp API
		 BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					 
		 // StringBuilder instance to help build our custom String with JSON data
		 StringBuilder stringBuilder = new StringBuilder();
		 String JSONString;
		 
		 // Using stringBuilder to read in data from JSON to String format
		 while ((JSONString = in.readLine()) != null) {
			 stringBuilder.append(JSONString);  
		}       
		 JSONObject yelpJSONObject = new JSONObject(stringBuilder.toString());
            // Debugging info printed to console
            System.out.println();
            System.err.println(yelpJSONObject);
            
            // End connection
            connection.disconnect();
            // Return JSON object with JSON data packed inside of it from Yelp API request
            return yelpJSONObject;            
			
	}	
	/**
	 * parseAndPopulate()
	 * 
	 * Enters JSON directory that is in String form.
	 * Populates new Restaurant instance with key information.
	 * 
	 * @param location
	 * @param categories
	 * @param price
	 * @return Class Restauarnt - 
	 * 
	 * @throws IOException
	 */
	public static Restaurant parseAndPopulate(String location, String categories, String price) throws IOException {
		// Create instance of Restaurant to be populated later
		Restaurant restaurantObject = new Restaurant();
		// Find random number between 0-9
		// rand will be used to enter random directory in JSON String
		int rand;
		Random randNumber = new Random();
		rand = randNumber.nextInt(10);
		
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
		// Catch an issue where too few results are being returned and rand number does not exist in JSON directory
		catch (org.json.JSONException e) {
			// Make another HTTP request to yelp API, returned JSONObject
			 JSONObject root = makeRequest(location, categories, price);
			 
			 // Set rand to a random int between 0-2
			 rand = randNumber.nextInt(3);
			 // Enter JSON directory
			 JSONArray businesses = root.getJSONArray("businesses");
			 // Enter random JSON restaurant entry
			 JSONObject jsonBusiness = businesses.getJSONObject(rand);
				
			 	// Set key variable information within our Restaurant instance.
				restaurantObject.setName(jsonBusiness.getString("name"));
				restaurantObject.setRating(jsonBusiness.getInt("rating"));
				restaurantObject.setReview_count(jsonBusiness.getInt("review_count"));
				restaurantObject.setPhoneNumber(jsonBusiness.getString("display_phone"));
				restaurantObject.setUrl(jsonBusiness.getString("url"));
				restaurantObject.setPrice(jsonBusiness.getString("price"));
		}
		// Return Restaurant instance that is filled with data
		return restaurantObject;
	}
	/**
	 * containsChar()
	 * 
	 * Private helper method that accepts String input.
	 * Method searches given String for alphabetical characters (a-z) and (A-Z).
	 * Returns True if input String contains an alphabetical character.
	 * Returns False if String does not contain an alphabetical character.
	 * 
	 * @param String str - String passed into program to be tested.
	 * 
	 * @return Boolean - True or false if input String has alphabetical chars.
	 */
	private static Boolean containsChar(String str) {
		for (int i = 0; i < str.length(); i++) { // Cycle through entire String input
			if (Character.isAlphabetic(str.charAt(i))) { 
				return true;
			}
		}
		// Case where there are no characters in String
		return false;
	}
	/**
	 * removeSpaces()
	 * 
	 * Private helper method that accepts String input.
	 * Returns a String with no spaces.
	 * 
	 * @param String str - Input for private helper method.
	 * 					 - Could be a String with or without spaces.
	 * 
	 * @return String returnString - new String with no spaces.
	 */
	private static String removeSpaces(String str) {
		// String to be returned later
		String returnString = "";
		// Cycles through String looking for spaces
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ' ') {
				// Only concatenate return String with chars that are not spaces
				returnString += str.charAt(i);
			}
		}
		return returnString;
	}
}