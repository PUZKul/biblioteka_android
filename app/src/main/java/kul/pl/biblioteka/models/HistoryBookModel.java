package kul.pl.biblioteka.models;

import java.util.Date;
import java.util.UUID;

public class   HistoryBookModel {

    private int id;
    private UUID userId;
    private int bookCopyId;
    private int bookId;
    private String title;
    private Date dateReturn;
    private Date dateIssued ;
    private String imageUrl;

    public HistoryBookModel(int id, UUID userId, int bookCopyId, int bookId, String title, Date dateReturn, Date dateIssued, String imageUrl) {
        this.id = id;
        this.userId = userId;
        this.bookCopyId = bookCopyId;
        this.bookId = bookId;
        this.title = title;
        this.dateReturn = dateReturn;
        this.dateIssued = dateIssued;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public int getBookCopyId() {
        return bookCopyId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public Date getDateReturn() {
        return dateReturn;
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
