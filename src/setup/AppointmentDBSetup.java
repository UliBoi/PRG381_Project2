import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AppointmentDBSetup {
    public static void main(String[] args) {
        try {
            // Load the Derby driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:derby:StudentWellnessDB/Database/wellnessDB;create=true");
            System.out.println("Connection successful!");

            // Create the Appointments table if it doesn't exist
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE Appointments (" +
                    "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    "student VARCHAR(100), " +
                    "counselor VARCHAR(100), " +
                    "appointment_date DATE, " +
                    "appointment_time TIME, " +
                    "status VARCHAR(20))";

            stmt.executeUpdate(sql);
            System.out.println("Appointments table created successfully!");

            // Close the connection
            conn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not found: " + e.getMessage());
        } catch (SQLException e) {
            // Handle the case where the table already exists
            if (e.getSQLState().equals("X0Y32")) {
                System.out.println("Table 'Appointments' already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }
}