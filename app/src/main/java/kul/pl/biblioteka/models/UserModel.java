package kul.pl.biblioteka.models;

import java.util.UUID;

public class UserModel {

    private UUID id;
    private String username;
    private String email;
    private int maxBooks;
    private int points;
    private int warnings;

    public UserModel(UUID id, String username, String email, int maxBooks, int points, int warnings) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.maxBooks = maxBooks;
        this.points = points;
        this.warnings = warnings;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getMaxBooks() {
        return maxBooks;
    }

    public int getPoints() {
        return points;
    }

    public int getWarnings() {
        return warnings;
    }
}
