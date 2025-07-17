package controller;

import model.Feedback;
import model.FeedbackDAO;
import java.util.List;

public class FeedbackController {

    private final FeedbackDAO dao;

    public FeedbackController() {
        this.dao = new FeedbackDAO(); 
    }

    // Submit new feedback
    public void submitFeedback(String student, int rating, String comments) {
        Feedback fb = new Feedback(student, rating, comments);
        try {
            dao.insertFeedback(fb);
            System.out.println("Feedback submitted.");
        } catch (Exception e) {
            System.err.println("Error submitting feedback: " + e.getMessage());
        }
    }

    // Get all feedback entries
    public List<Feedback> getAllFeedback() {
        try {
            return dao.getAllFeedback();
        } catch (Exception e) {
            System.err.println("Error retrieving feedback: " + e.getMessage());
            return null;
        }
    }

    // Update existing feedback
    public void updateFeedback(int id, String student, int rating, String comments) {
        Feedback fb = new Feedback(id, student, rating, comments);
        try {
            dao.updateFeedback(fb);
        } catch (Exception e) {
            System.err.println("Error updating feedback: " + e.getMessage());
        }
    }

    // Delete feedback by ID
    public void deleteFeedback(int id) {
        try {
            dao.deleteFeedback(id);
        } catch (Exception e) {
            System.err.println("Error deleting feedback: " + e.getMessage());
        }
    }
}
