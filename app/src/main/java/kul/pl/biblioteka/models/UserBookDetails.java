package kul.pl.biblioteka.models;

public class UserBookDetails {

    private long totalBooks;

    private long currentBooks;

    public UserBookDetails(long totalBooks, long currentBooks) {
        this.totalBooks = totalBooks;
        this.currentBooks = currentBooks;
    }

    public long getTotalBooks() {
        return totalBooks;
    }

    public long getCurrentBooks() {
        return currentBooks;
    }
}
