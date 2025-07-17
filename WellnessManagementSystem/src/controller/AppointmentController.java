package controller;

import model.Appointment;
import model.AppointmentDAO;
import java.util.List;

public class AppointmentController {
    private final AppointmentDAO dao;

    public AppointmentController() {
        this.dao = new AppointmentDAO(); // DAO handles DB connection internally
    }

    // Book new appointment
    public void addAppointment(Appointment appt) {
    try {
        dao.addAppointment(appt);
        System.out.println("Appointment added.");
    } catch (Exception e) {
        System.err.println("Error booking appointment: " + e.getMessage());
    }
}


    // View all appointments
    public List<Appointment> getAllAppointments() {
        try {
            return dao.getAllAppointments();
        } catch (Exception e) {
            System.err.println("Error retrieving appointments: " + e.getMessage());
            return null;
        }
    }

    // Update appointment status
    public void updateAppointmentStatus(int id, String newStatus) {
        try {
            dao.updateStatus(id, newStatus);
        } catch (Exception e) {
            System.err.println("Error updating status: " + e.getMessage());
        }
    }

    // Cancel appointment
    public void cancelAppointment(int id) {
        try {
            dao.cancelAppointment(id);
        } catch (Exception e) {
            System.err.println("Error cancelling appointment: " + e.getMessage());
        }
    }

    public Appointment.Status[] getStatusOptions() {
        return dao.getPossibleStatuses();
    }
}

