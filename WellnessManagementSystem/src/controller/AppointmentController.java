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
        dao.addAppointment(appt);  // Call the DAO method to insert into DB
    } catch (Exception e) {
        System.err.println("Error adding appointment: " + e.getMessage());
    }
}

public void updateAppointment(Appointment appt) {
    try {
        dao.updateAppointment(appt);
    } catch (Exception e) {
        System.err.println("Error updating appointment: " + e.getMessage());
    }
}

public void deleteAppointment(String student, String dateStr, String timeStr) {
    try {
        dao.deleteAppointment(student, dateStr, timeStr);
    } catch (Exception e) {
        System.err.println("Error deleting appointment: " + e.getMessage());
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

   public String[] getStatusOptions() {
    return new String[] { "Scheduled", "Confirmed", "Cancelled", "Completed" };
}

}

