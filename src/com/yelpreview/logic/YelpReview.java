package com.yelpreview.logic;

public class YelpReview
{
    // all within the /businesses/search endpoint
    private String location;
    private String category;
    private String name;
    private double rating;
    private int reviewCount;
    private double safaScore;

    public YelpReview(String location, String category, String name, double rating, int reviewCount)
    {
        this.location = location;
        this.category = category;
        this.name = name;
        this.rating = rating;
        this.reviewCount = reviewCount;
    }

    // overloaded constructor to calculate the safaScore because only rating and reviewCount are needed
    public YelpReview(double rating, int reviewCount)
    {
        this.location = null;
        this.category = null;
        this.name = null;
        this.rating = rating;
        this.reviewCount = reviewCount;
    }

    // so the output of the list of com.yelpreview.logic.YelpReview objects, reviewsList, returns data within the object not the memory address of the object
    @Override
    public String toString()
    {
        return "com.yelpreview.logic.YelpReview {" + "Name: " + name +
                ", Rating: " + rating +
                ", Location: " + location +
                ", Category: " + category +
                ", Number of reviews: " + reviewCount +
                "}";
    }

    // setter and getter methods for safaScore

    public void setSafaScore(double safaScore)
    {
        this.safaScore = safaScore;
    }

    public double getSafaScore()
    {
        return safaScore;
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
