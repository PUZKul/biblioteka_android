package kul.pl.biblioteka.models;

import java.util.Date;

public class BookModel {

    private int id;
    private String title;
    private String authors;
    private double rating;
    private double popularity;
    private int pages;
    private Date year;
    private String publisher;
    private String imageUrl;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public double getRating() {
        return rating;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getPages() {
        return pages;
    }

    public Date getYear() {
        return year;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
