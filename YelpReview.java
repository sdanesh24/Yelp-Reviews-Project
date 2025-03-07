public class YelpReview
{
    // all within the /businesses/search endpoint
    String location;
    String category;
    String name;
    double rating;
    int reviewCount;

    public YelpReview(String location, String category, String name, double rating, int reviewCount)
    {
        this.location = location;
        this.category = category;
        this.name = name;
        this.rating = rating;
        this.reviewCount = reviewCount;
    }
}
