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

    // so the output of the list of YelpReview objects, reviewsList, returns data within the object not the memory address of the object
    @Override
    public String toString()
    {
        return "YelpReview {" + "Name: " + name +
                ", Rating: " + rating +
                ", Location: " + location +
                ", Category: " + category +
                ", Number of reviews: " + reviewCount +
                "}";
    }
}
