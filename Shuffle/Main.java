import javax.swing.JOptionPane; 
import java.io.IOException;

public class Main extends utilities {

	public static void main(String[] args) throws IOException {
    // Local variables
    String categories;
    String price;
    String location = "";
    // Create new instance of Class Restaurant
    Restaurant restaurant = new Restaurant();   
    // Introduction to program
    JOptionPane.showMessageDialog(null, "Welcome To Restaurant Shuffle", "Restaurant Shuffle", 1); //Welcome Message
    
     // Prompt user for address, ZIP code, or city & initialize location
 	 location = JOptionPane.showInputDialog(null, "Enter a City, ZIP Code, or an Address To Continue", "Location", 3);
	 
	 // Prompt user for price range & initialize price

	 price =   JOptionPane.showInputDialog(null, "What Price Range Were You Looking For Today?\nPlease Enter 1-4 or Leave Blank If No Preference."
	 		+ "\n1=$, 2=$$, 3=$$$, 4=$$$$", "Price", 3);
	 	 
	 // Prompt user for categories they want & initialize categories		 
	 categories = JOptionPane.showInputDialog(null, "If you would like to search a certain food type please enter type here."
	  		+ "\nMultiple can be searched if comma is used. EX: Mexican, Burger, Asian", "Category", 3); 

     // Populating our restaurant instance with function parseAndPopulate()
     restaurant = parseAndPopulate(location, categories, price);
    
     // Display information about restaurant back to user
     JOptionPane.showMessageDialog(null, "Restaurant name: " + restaurant.getName() + "\nNumber: " + restaurant.getPhoneNumber() 
     	    + "\nPrice: " + restaurant.getPrice());
	}
}