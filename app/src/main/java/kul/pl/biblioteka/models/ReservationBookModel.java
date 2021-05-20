
package kul.pl.biblioteka.models;

        import java.util.Date;

public class ReservationBookModel {

    private int id;
    private String userId;
    private String bookId;
    private String title;
    private String bookCopyId;
    private String imageUrl;
    private Date dateReservation;
    private Date dateBorrow;


    public ReservationBookModel(int id, String userId, String bookId, String bookCopyId, String title, String imageUri, Date dateReservation, Date dateBorrow) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.title = title;
        this.bookCopyId = bookCopyId;
        this.imageUrl = imageUri;
        this.dateReservation = dateReservation;
        this.dateBorrow = dateBorrow;
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
        return imageUrl;
    }

    public String getBookCopyId() {
        return bookCopyId;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public Date getDateBorrow() {
        return dateBorrow;
    }

}
