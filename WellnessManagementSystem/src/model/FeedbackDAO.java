package model;

import java.sql.*;
import java.util.*;
import studentwellnessdb.DBConnection;

public class FeedbackDAO {

    // INSERT new feedback
    public void insertFeedback(Feedback fb) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO FEEDBACK (student, rating, comments) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fb.getStudent());
            ps.setInt(2, fb.getRating());
            ps.setString(3, fb.getComments());
            ps.executeUpdate();
            System.out.println("Feedback added.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT all feedback entries
    public List<Feedback> getAllFeedback() {
        List<Feedback> feedbackList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM FEEDBACK";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Feedback fb = new Feedback(
                        rs.getInt("id"),
                        rs.getString("student"),
                        rs.getInt("rating"),
                        rs.getString("comments")
                );
                feedbackList.add(fb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedbackList;
    }

    // UPDATE feedback by ID
    public void updateFeedback(Feedback fb) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE FEEDBACK SET student = ?, rating = ?, comments = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fb.getStudent());
            ps.setInt(2, fb.getRating());
            ps.setString(3, fb.getComments());
            ps.setInt(4, fb.getId());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Feedback updated.");
            } else {
                System.out.println("No feedback found with ID: " + fb.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE feedback by ID
    public void deleteFeedback(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM FEEDBACK WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Feedback deleted.");
            } else {
                System.out.println("No feedback found with ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
