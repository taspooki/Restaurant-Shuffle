import org.json.JSONObject;
import javax.swing.JOptionPane; 
import java.io.IOException;

public class Main extends utilities {

	public static void main(String[] args) throws IOException {
    // Local variables
    String categories;
    String price;
    String location;
    //
    Restaurant restaurant = new Restaurant();

    JOptionPane.showMessageDialog(null, "Welcome To Restaurant Shuffle", "Restaurant Shuffle", 1); //Welcome Message
		
		 location = JOptionPane.showInputDialog(null, "Enter a City, Zip Code, or an Address To Continue", "Location", 3);//Asks for zip code or city to begin search from user
		
	                 // Stores Input
		
	   price =   JOptionPane.showInputDialog(null, "What Price Range Were You Looking For Today?\nPlease Enter 1-4 or Leave Blank If No Preference.\n 1=$, 2=$$, 3=$$$, 4=$$$$", "Price", 3); //Asks for Price Range
	    			//Stores Int
				 if(price == "") {    
           price = "-1";    //Searches all prices if input is left blank
         }
		
		// TODO: Write exception to detect spaces in categories input, if detected, for loop to ELIMINATE THOSE SPACES		 
	  categories = JOptionPane.showInputDialog(null, "If you would like to search a certain food type please enter type here.\nMultiple can be searched if comma is used. EX: Mexican,Burger,Asian", "Category", 3);  //Asks for Category
	    			//Stores Input 
	  System.out.println(categories);
        if(categories == ""){
          categories = "-1";  //Searches all categories if input is left blank
        }
    System.err.println(categories);
    System.out.println(categories);


    // Populating our restaurant instance
    restaurant = parseAndPopulate(location, categories, price);
    
    // Print out to console information about Restaurant
    System.out.println(restaurant.getName());
    System.out.println(restaurant.getPrice());
		
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
// Also ensure input is valid, either blank, 1,2,3, or 4 are the only acceptable inputs.
// give me a flag if user doesn't input anything. So assign price to -1.

// Prompt user for categories of food they want to search for as well, explain what they can search for "asian" "burger" 
//"fast food" "steakhouse" etc. I leave it up to you to phrase it in the best way to the user. Also, explain commas can be used
// to search for multiple categories. e.g. "fastfood, burger" searches for fastfood and burger.
// This one can be left blank by user
// Give me another flag if user leaves this one blank. assign categories to "-1"
// This is the GUI that the user will use to interact with the program. They shouldn't be confused

// we must get location from the user, redisplay same JOptionPane if no input is given 




// Also ensure input is valid, either blank, 1,2,3, or 4 are the only acceptable inputs.
// give me a flag if user doesn't input anything. So assign price to -1.
