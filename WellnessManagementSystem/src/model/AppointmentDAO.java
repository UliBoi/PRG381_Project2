package model;

import java.sql.*;
import java.util.*;
import studentwellnessdb.DBConnection;

public class AppointmentDAO {


public void addAppointment(Appointment appt) {
    try (Connection conn = DBConnection.getConnection()) {
        String sql = "INSERT INTO Appointments (student, counselor, date, time, status) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, appt.getStudent());
        ps.setString(2, appt.getCounselor());
        ps.setDate(3, appt.getDate());
        ps.setTime(4, java.sql.Time.valueOf(appt.getTime() + ":00"));
        ps.setString(5, appt.getStatus());
        ps.executeUpdate();
        System.out.println("Appointment added successfully.");
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Error adding appointment to database.");
    }
}
public void updateAppointment(Appointment appt) {
    try (Connection conn = DBConnection.getConnection()) {
        String sql = "UPDATE Appointments SET counselor = ?, date = ?, time = ?, status = ? WHERE student = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, appt.getCounselor());
        ps.setDate(2, appt.getDate());
        ps.setTime(3, java.sql.Time.valueOf(appt.getTime() + ":00")); // Make sure it's in HH:mm:ss format
        ps.setString(4, appt.getStatus());
        ps.setString(5, appt.getStudent()); // used for identifying the row to update
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public void deleteAppointment(String student, String dateStr, String timeStr) {
    String sql = "DELETE FROM Appointments WHERE student = ? AND date = ? AND time = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, student);
        ps.setDate(2, java.sql.Date.valueOf(dateStr));
        ps.setTime(3, java.sql.Time.valueOf(timeStr + ":00")); // add :00 to match DB format
        ps.executeUpdate();

        System.out.println("Appointment deleted.");
    } catch (Exception e) {
        e.printStackTrace();
    }
}






    // View all appointments
    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Appointments";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                list.add(new Appointment(
                        rs.getString("student"),
                        rs.getString("counselor"),
                        rs.getDate("date").toString(),
                        rs.getTime("time").toString().substring(0, 5),
                        rs.getString("status")  // Now status is plain string
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Update status
    public void updateStatus(int id, String newStatus) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE Appointments SET status = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newStatus);  // Pass string directly
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Appointment updated." : "Appointment not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cancel (delete)
    public void cancelAppointment(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM Appointments WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Appointment cancelled." : "Appointment not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
