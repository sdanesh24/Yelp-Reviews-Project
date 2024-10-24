// name of the object
public class API
{
    String apiKey;
    String urlVar;

    //constructor special method because no return type
    public API(String urlVar, String apiKey)
    {
        this.apiKey = apiKey;
        this.urlVar = urlVar;
    }
    // yes return type normal method
    public String apiString()
    {
        return apiKey + urlVar;
    }

    public Reviews fetchReviews()
    {
        return getReviews(urlVar, apiKey);
    }

}
