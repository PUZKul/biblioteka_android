package kul.pl.biblioteka.models;

public class UserBookDetails {

    private long totalBook;

    private long currentBook;

    public UserBookDetails(long totalBooks, long currentBooks) {
        this.totalBook = totalBooks;
        this.currentBook = currentBooks;
    }

    public long getTotalBooks() {
        return totalBook;
    }

    public long getCurrentBooks() {
        return currentBook;
    }
}
