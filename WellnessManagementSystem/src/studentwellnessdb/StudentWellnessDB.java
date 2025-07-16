package studentwellnessdb;

import java.sql.Connection;
//import model.Feedback;
//import model.FeedbackDAO;

public class StudentWellnessDB {

    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Connected to JavaDB!");
            DbInit.TableCreation();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
//        Feedback fb = new Feedback("John Doe", 5, "Excellent session!");
//
//        FeedbackDAO.insertFeedback(fb);
    }
}
