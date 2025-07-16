package model;

public class Feedback {
    private int id;
    private String student;
    private int rating;
    private String comments;

    public Feedback(String student, int rating, String comments) {
        this.student = student;
        this.rating = rating;
        this.comments = comments;
    }

    public Feedback(int id, String student, int rating, String comments) {
        this(student, rating, comments);
        this.id = id;
    }

    // Getters & Setters
}

