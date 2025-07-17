package model;

import studentwellnessdb.DBConnection;
import java.sql.*;
import java.util.*;

public class CounselorDAO {

    // INSERT new counselor
    public void addCounselor(Counselor counselor){
        
        try (Connection conn = DBConnection.getConnection()){
            String sql = "INSERT INTO COUNSELORS (name, specialization, availability) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, counselor.getName());
            stmt.setString(2, counselor.getSpecialization());
            stmt.setString(3, counselor.getAvailability());
            stmt.executeUpdate();
         
            System.out.println("Counselor added successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // GET all counselors
    public List<Counselor> getAllCounselors() {
        List<Counselor> list = new ArrayList<>();
        String sql = "SELECT * FROM COUNSELORS";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Counselor(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("specialization"),
                    rs.getString("availability")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE counselor by ID
    public void updateCounselor(Counselor counselor) {
        String sql = "UPDATE COUNSELORS SET name = ?, specialization = ?, availability = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, counselor.getName());
            stmt.setString(2, counselor.getSpecialization());
            stmt.setString(3, counselor.getAvailability());
            stmt.setInt(4, counselor.getId());
            stmt.executeUpdate();

            System.out.println("Counselor updated.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // DELETE counselor by ID
    public void deleteCounselor(int id) {
        String sql = "DELETE FROM COUNSELORS WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Counselor deleted.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
