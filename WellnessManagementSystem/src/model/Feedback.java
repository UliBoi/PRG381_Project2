package model;

public class Feedback {
    private int id;
    private String student;
    private int rating;
    private String comments;

    //Constructor without ID
    public Feedback(String student, int rating, String comments) {
        this.student = student;
        this.rating = rating;
        this.comments = comments;
    }

    //Constructor with ID
    public Feedback(int id, String student, int rating, String comments) {
        this(student, rating, comments);
        this.id = id;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getStudent() {
        return student;
    }

    public int getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }

    //Setters 
    public void setId(int id) {
        this.id = id;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
