import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public abstract class utilities {

	public static JSONObject getJSON(String location, String categories, int price) throws IOException {
String url = "https://api.yelp.com/v3/businesses/search?open_now=true&term=food";
url += "&location=" + location;

if (price != -1) {
	url += "&price=" + price;
}
if (categories != "-1") {
	url += "&categories=" + categories;
}
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Authorization", "Bearer 4DRYEsEaNvDtwM7yBtS0KaNNLr282Fg0JVe67DrQSpM9ycZHFhZPUrK1-24Y6x-7g9deHjyROXP-w4n0BFMUHP-1EeflzSiBsSGjTOHrW-CvhJUmIfTbfxyUeLWuYXYx");
		
		//
		try (BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);            
            }            
            JSONObject test = new JSONObject(response.toString());
            System.out.println();
            System.err.println(test);
            // End connection
            connection.disconnect();
            return test;            
	}		
	}	
}