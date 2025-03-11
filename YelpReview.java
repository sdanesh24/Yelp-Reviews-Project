public class YelpReview
{
    // all within the /businesses/search endpoint
    private String location;
    private String category;
    private String name;
    private double rating;
    private int reviewCount;

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

    // creating getter methods to access each individual instance field for the ExcelExporter class

    public String getLocation()
    {
        return location;
    }

    public String getCategory()
    {
        return category;
    }

    public String getName()
    {
        return name;
    }

    public double getRating()
    {
        return rating;
    }

    public int getReviewCount()
    {
        return reviewCount;
    }

}
