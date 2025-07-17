package model;

import java.sql.*;
import java.util.*;
import studentwellnessdb.DBConnection;

public class AppointmentDAO {

    // Book appointment
    public void addAppointment(Appointment appt) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Appointments (student, counselor, date, time, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, appt.getStudent());
            ps.setString(2, appt.getCounselor());
            ps.setDate(3, appt.getDate());
            ps.setTime(4, Time.valueOf(appt.getTime() + ":00"));
            ps.setString(5, appt.getStatusValue());
            ps.executeUpdate();
            System.out.println("Appointment booked successfully.");
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
                        rs.getString("status")  // This will use the String constructor
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Update status using Appointment.Status enum
    public void updateStatus(int id, Appointment.Status newStatus) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE Appointments SET status = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newStatus.name());  // Convert enum to String
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Appointment updated." : " Appointment not found.");
        } catch (Exception e) {
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
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM Appointments WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Appointment cancelled." : " Appointment not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Appointment.Status[] getPossibleStatuses() {
        return Appointment.Status.values();
    }
}