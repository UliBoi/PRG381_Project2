package controller;

import model.CounselorDAO;
import model.Counselor;
import java.util.List;

public class CounselorController {

    private final CounselorDAO dao;

    public CounselorController() {
        this.dao = new CounselorDAO();
    }

    // Add a new counselor
    public void addCounselor(String name, String spec, String avail) {
        Counselor counselor = new Counselor(0, name, spec, avail);
        try {
            dao.addCounselor(counselor);
            System.out.println("Counselor added.");
        } catch (Exception e) {
            System.err.println("Error adding counselor: " + e.getMessage());
        }
    }

    // Get all counselors
    public List<Counselor> getAllCounselors() {
        try {
            return dao.getAllCounselors();
        } catch (Exception e) {
            System.err.println("Error retrieving counselors: " + e.getMessage());
            return null;
        }
    }

    // Update an existing counselor
    public void updateCounselor(int id, String name, String specialization, String availability) {
        Counselor updated = new Counselor(id, name, specialization, availability);
        try {
            dao.updateCounselor(updated);
            System.out.println("Counselor updated.");
        } catch (Exception e) {
            System.err.println("Error updating counselor: " + e.getMessage());
        }
    }

    // Delete counselor by ID
    public void deleteCounselor(int id) {
        try {
            dao.deleteCounselor(id);
            System.out.println("Counselor deleted.");
        } catch (Exception e) {
            System.err.println("Error deleting counselor: " + e.getMessage());
        }
    }
}
