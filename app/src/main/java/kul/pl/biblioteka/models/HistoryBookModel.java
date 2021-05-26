package kul.pl.biblioteka.models;

import java.util.Date;

public class HistoryBookModel {

    private long id;
    private String userId;
    private long bookId;
    private long bookCopyId;
    private String title;
    private String imageUrl;
    private Date dateIssued;
    private Date expectedDate;
    private Date dateReturn;

    public HistoryBookModel(long id, String userId, long bookId, long bookCopyId, String title, String imageUrl, Date dateIssued, Date expectedDate, Date dateReturn) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.bookCopyId = bookCopyId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.dateIssued = dateIssued;
        this.expectedDate = expectedDate;
        this.dateReturn = dateReturn;
    }

    public Date getExpectedDate() {
        return expectedDate;
    }

    public long getBookCopyId() {
        return bookCopyId;
    }

    public long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    public Date getDateReturn() {
        return dateReturn;
    }
}