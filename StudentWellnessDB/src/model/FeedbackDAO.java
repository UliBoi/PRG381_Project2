
package model;

public class FeedbackDAO {
    public static void insertFeedback(Feedback fb) {
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

    // Add view, update, delete methods later
}
