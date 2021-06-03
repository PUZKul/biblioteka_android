package kul.pl.biblioteka.models;

import java.util.Date;

public class CopiesOfBookModel {

    private long id;
    private long bookId;
    private boolean borrow;
    private boolean access;
    private String code;
    private Date approximateDate;

    public CopiesOfBookModel(long id, long bookId, boolean borrow, boolean access, String code, Date approximateDate) {
        this.id = id;
        this.bookId = bookId;
        this.borrow = borrow;
        this.access = access;
        this.code = code;
        this.approximateDate = approximateDate;
    }

    public long getId() {
        return id;
    }

    public long getBookId() {
        return bookId;
    }

    public boolean isBorrow() {
        return borrow;
    }

    public boolean isAccess() {
        return access;
    }

    public String getCode() {
        return code;
    }

    public Date getApproximateDate() {
        return approximateDate;
    }
}
