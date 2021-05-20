package kul.pl.biblioteka.models;

import java.util.Date;

public class HistoryBookModel {

    private int id;
    private String userId;
    private String bookId;
    private String bookCopyId;
    private String title;
    private String imageUri;
    private Date dateIssued;
    private Date dateReturn;

    public HistoryBookModel(int id, String userId, String bookId, String bookCopyId, String title, String imageUri, Date dateIssued, Date dateReturn) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.bookCopyId = bookCopyId;
        this.title = title;
        this.imageUri = imageUri;
        this.dateIssued = dateIssued;
        this.dateReturn = dateReturn;
    }

    public String getBookCopyId() {
        return bookCopyId;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUri() {
        return imageUri;
    }

    public Date getDateReservation() {
        return dateIssued;
    }

    public Date getDateBorrow() {
        return dateReturn;
    }
}