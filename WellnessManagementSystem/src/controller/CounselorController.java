package controller;

import model.CounselorDAO;
import model.Counselor;
import java.util.List;

public class CounselorController {
    private final CounselorDAO dao;

    public CounselorController() {
        this.dao = new CounselorDAO(); // DAO handles DB connection internally
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

    // Update existing counselor
    public void updateCounselor(Counselor counselor) {
        try {
            dao.updateCounselor(counselor);
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
