package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Appointment;

public class AppointmentDAO {
    private static final String DB_URL = "jdbc:derby:StudentWellnessDB/Database/wellnessDB;create=true";

    // Book appointment
    public void addAppointment(Appointment appt) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO Appointments (student, counselor, appointment_date, appointment_time, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, appt.getStudent());
            ps.setString(2, appt.getCounselor());
            ps.setDate(3, Date.valueOf(appt.getDate()));
            ps.setTime(4, Time.valueOf(appt.getTime() + ":00"));
            ps.setString(5, appt.getStatusValue());  // Use getStatusValue() instead of getStatus()
            ps.executeUpdate();
            System.out.println("✅ Appointment booked successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View all appointments
    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT * FROM Appointments";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                list.add(new Appointment(
                        rs.getString("student"),
                        rs.getString("counselor"),
                        rs.getDate("appointment_date").toString(),
                        rs.getTime("appointment_time").toString().substring(0, 5),
                        rs.getString("status")  // This will use the String constructor
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Update status - now using Appointment.Status enum
    public void updateStatus(int id, Appointment.Status newStatus) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "UPDATE Appointments SET status = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newStatus.name());  // Convert enum to String
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Appointment updated." : " Appointment not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateStatus(int id, String newStatus) {
        try {
            updateStatus(id, Appointment.Status.valueOf(newStatus));
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid status value: " + newStatus);
        }
    }

    // Cancel (delete)
    public void cancelAppointment(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "DELETE FROM Appointments WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Appointment cancelled." : " Appointment not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Appointment.Status[] getPossibleStatuses() {
        return Appointment.Status.values();
    }
}