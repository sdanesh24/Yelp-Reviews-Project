// need further explanation of these packages and how the make the API work

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
    private static final String API_KEY = "6zHHvZ8FsLC9qBnjcUi4uP5bTEpDN49g0VY7bbPCnTLl1Mbhm2QE5YghsHNUuX7y4eys4bOE9a7-MKp8XlfP0Jvitk3ZDT4aDdGP4sYdaF1XxqrfGb95C1cv7vPFZ3Yx";

    private static final String BASE_URL = "https://api.yelp.com/v3";

    // creating a method called scrapeYelpReviews to return reviewsList - a list of YelpReview values
    // pull params of location and category from user input
    public static List<YelpReview> scrapeReviews(String userLocation, String userCategory)
    {
        List<YelpReview> reviewsList = new ArrayList<>();

        try
        {
            // calling the Business Search endpoint to search businesses by location and category
            String apiURL = BASE_URL + "/businesses/search" + userLocation + userCategory;
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
            while ((line = br.readLine()) != null)
            {
                apiResponse.append(line);
            }
            br.close();

            // converting the apiResponse StringBuilder object into a JsonObject
            JsonObject jsonObject = JsonParser.parseString(apiResponse.toString()).getAsJsonObject();

            // saving the list from jsonObject as a JsonArray
            JsonArray jsonArrayBusinesses = jsonObject.getAsJsonArray("businesses");

            // looping through the array to extract instance fields of YelpReview class objects - individual businesses
            // saving each instance to reviewsList
            for (JsonElement businessElement : jsonArrayBusinesses)
            {
                JsonObject businessObject = businessElement.getAsJsonObject();

                // saving values of instance fields
                String location = businessObject.get("location").getAsString();
                String category = businessObject.get("category").getAsString();
                String name = businessObject.get("name").getAsString();
                double rating = businessObject.get("rating").getAsDouble();
                int reviewCount = businessObject.get("review_count").getAsInt();

                // creating an instance of YelpReview to save each business in the loop to
                YelpReview business = new YelpReview(location, category, name, rating, reviewCount);

                // appending (adding) each instance of YelpReview to reviewsList
                reviewsList.add(business);
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return(reviewsList);
    }
}
