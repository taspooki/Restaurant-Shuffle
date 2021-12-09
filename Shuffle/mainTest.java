import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Random;

public class mainTest extends utilities {

	public static void main(String[] args) throws IOException {
    // Local variables
    String categories = "waffles";
    String location = "scottsdale";
    int price = -1;
    int rand;
    //
    Random randNumber = new Random();
    rand = randNumber.nextInt(20);
    //
	Restaurant restaurantObject = new Restaurant();
	//
    
    // Prompting input from user with JOptionPane & assigning variables as needed TODO:

	System.err.println(rand);

    // Calling an instance of getJSON()
	try {
		JSONObject root = getJSON(location, categories, price);
		 
		 JSONArray businesses = root.getJSONArray("businesses");

		 JSONObject jsonBusiness = businesses.getJSONObject(rand);
									
			restaurantObject.setName(jsonBusiness.getString("name"));
		
		System.out.println();
		System.err.println(rand);
		System.err.println(restaurantObject.getName());
	} catch (org.json.JSONException e) {
		JSONObject root = getJSON(location, categories, price);
		 rand = root.getInt("total");
		 rand = randNumber.nextInt(rand);
		 System.out.println(rand);
		 JSONArray businesses = root.getJSONArray("businesses");

		 JSONObject jsonBusiness = businesses.getJSONObject(rand);
									
			restaurantObject.setName(jsonBusiness.getString("name"));
		
		System.out.println();
		System.err.println(rand);
		System.err.println(restaurantObject.getName());
	}		 
	}
}

// TODO:
// JOptionPane to prompt user for input
// assign our variables to the input from user
// Remember to make the text displayed to the usable readable and understandable
// This is the GUI that the user will use to interact with the program. They shouldn't be confused

// we must get location from the user, redisplay same JOptionPane if no input is given
// Explain to user that they can enter either a city, zip code, or address for this part

// price is optional, also need to explain 1=$, 2=$$, 3=$$$, 4=$$$$; also explain can use commas for multiple
// i.e. 1,3 searches for $ and $$$
// give me a flag if user doesn't input anything. So assign price to -1.

// Prompt user for categories of food they want to search for as well, explain what they can search for "asian" "burger" 
//"fast food" "steakhouse" etc. I leave it up to you to phrase it in the best way to the user. Also, explain commas can be used
// to search for multiple categories. e.g. "fastfood, burger" searches for fastfood and burger.
// This one can be left blank by user
// Give me another flag if user leaves this one blank. assign categories to "-1"