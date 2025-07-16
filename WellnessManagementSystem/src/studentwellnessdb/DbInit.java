package studentwellnessdb;

import java.sql.Connection;
import java.sql.Statement;

public class DbInit {
    public static void TableCreation() {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Create APPOINTMENTS table
            String createAppointments = "CREATE TABLE APPOINTMENTS (" +
                    "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "student VARCHAR(50) NOT NULL, " +
                    "counselor VARCHAR(50) NOT NULL, " +
                    "date DATE NOT NULL, " +
                    "time VARCHAR(10) NOT NULL, " +
                    "status VARCHAR(20) NOT NULL" +
                    ")";
            stmt.executeUpdate(createAppointments);
            System.out.println("APPOINTMENTS table created successfully.");

            // Create COUNSELORS table
            String createCounselors = "CREATE TABLE COUNSELORS (" +
                    "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "specialization VARCHAR(50) NOT NULL, " +
                    "availability VARCHAR(100)" +
                    ")";
            stmt.executeUpdate(createCounselors);
            System.out.println("COUNSELORS table created successfully.");
            
            // Create FEEDBACK table
            String createFeedback = "CREATE TABLE FEEDBACK (" +
                    "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "student VARCHAR(50) NOT NULL, " +
                    "rating INT CHECK (rating >= 1 AND rating <= 5), " +
                    "comments VARCHAR(255)" +
                    ")";
            stmt.executeUpdate(createFeedback);
            System.out.println("FEEDBACK table created successfully.");

        } catch (Exception e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }
}

