package kul.pl.biblioteka.models;

import java.util.Date;

public class HistoryBookModel {

    private int id;
    private String userId;
    private int bookCopyId;
    private String title;
    private Date dateIssued;
    private Date dateReturn;
    private String imageUrl;

    public HistoryBookModel(int id, String userId, int bookCopyId, String title, Date dateIssued, Date dateReturn,String imageUrl) {
        this.id = id;
        this.userId = userId;
        this.bookCopyId = bookCopyId;
        this.title = title;
        this.dateIssued = dateIssued;
        this.dateReturn = dateReturn;
        this.imageUrl=imageUrl;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public int getBookCopyId() {
        return bookCopyId;
    }

    public String getTitle() {
        return title;
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    public Date getDateReturn() {
        return dateReturn;
    }
}
