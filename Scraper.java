// need further explanation of these packages and how they make the API work

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.*;

public class Scraper
{
    // we don't need a constructor method for this class because there are no instance variables of the class
    // these aren't considered instance variables because they are static and belong to the class
    private static final String API_KEY = System.getenv("YELP_API_KEY");

    private static final String BASE_URL = "https://api.yelp.com/v3";

    public static List<YelpReview> reviewsList = new ArrayList<>();

    // creating a method called scrapeYelpReviews to return reviewsList - a list of YelpReview values
    // pull params of location and category from user input
    public static List<YelpReview> scrapeReviews(String userLocation, String userCategory) {

        reviewsList.clear();

        try {
            // calling the Business Search endpoint to search businesses by location and category
            String apiURL = BASE_URL + "/businesses/search?location=" + userLocation + "&categories=" + userCategory;
            // TODO: add in logic here (for loop?) to handle spaces if entered in userLocation and userCategory

            URL url = new URL(apiURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // explanation of this line.. bearer tokens and API keys aren't the same thing, right?
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            // API call data we get back is in JSON format so needs to be stored in one big string before it is parsed
            StringBuilder apiResponse = new StringBuilder();

            String line;

            // while there are still lines to be read from the API call, save them to line
            while ((line = br.readLine()) != null) {
                apiResponse.append(line);
            }
            br.close();

            // converting the apiResponse StringBuilder object into a JsonObject
            JsonObject jsonObject = JsonParser.parseString(apiResponse.toString()).getAsJsonObject();

            // saving the list from jsonObject as a JsonArray
            JsonArray jsonArrayBusinesses = jsonObject.getAsJsonArray("businesses");

            // looping through the array to extract instance fields of YelpReview class objects - individual businesses
            // saving each instance to reviewsList
            for (JsonElement businessElement : jsonArrayBusinesses) {
                JsonObject businessObject = businessElement.getAsJsonObject();

                // saving values of instance fields

                String name = businessObject.get("name").getAsString();
                double rating = businessObject.get("rating").getAsDouble();
                int reviewCount = businessObject.get("review_count").getAsInt();

                // more processing work needed to extract location and category because they are saved as arrays within the jsonObject

                JsonObject locationObject = businessObject.getAsJsonObject("location");
                JsonArray displayAddressArray = locationObject.getAsJsonArray("display_address");

                StringBuilder address = new StringBuilder();
                for (JsonElement displayAddress : displayAddressArray)
                {
                    address.append(displayAddress.getAsString()).append(", ");
                }

                String location = address.substring(0, address.length() - 2);

                // TODO: create a method that selects only the title that matches the userCategory
                JsonArray categoryArray = businessObject.getAsJsonArray("categories");
                List<String> categoryTitles = new ArrayList<>();

                for (JsonElement categoryElement : categoryArray)
                {
                    JsonObject categoryObject = categoryElement.getAsJsonObject();
                    categoryTitles.add(categoryObject.get("title").getAsString());
                }

                String category = String.join(", ", categoryTitles);

                // creating an instance of YelpReview to save each business in the loop to
                YelpReview business = new YelpReview(location, category, name, rating, reviewCount);

                // appending (adding) each instance of YelpReview to reviewsList
                reviewsList.add(business);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(reviewsList);
        System.out.println("Your search pulled " + reviewsList.size() + " businesses");

        //new GraphView();

        return reviewsList;
    }

    // creating a getter method to return reviewsList in the ExcelExport class

    public static List <YelpReview> getReviewsList()
    {
        return reviewsList;
    }
}
