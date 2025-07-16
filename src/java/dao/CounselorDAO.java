/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package dao;


import model.Counselor;
import java.sql.*;
import java.util.*;




public class CounselorDAO {

    private Connection conn;

    public CounselorDAO(Connection conn) {
        this.conn = conn;
    }

    //Creates  table 
    public void initializeTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS Counselors (
                id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                name VARCHAR(100),
                specialization VARCHAR(100),
                availability VARCHAR(50)
            )
        """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    //Adds a new counselor to the DB
    public void addCounselor(Counselor counselor) throws SQLException {
        String sql = "INSERT INTO Counselors (name, specialization, availability) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, counselor.getName());
            stmt.setString(2, counselor.getSpecialization());
            stmt.setString(3, counselor.getAvailability());
            stmt.executeUpdate();
        }
    }

    //Retrieves all counselors as a list
    public List<Counselor> getAllCounselors() throws SQLException {
        List<Counselor> counselors = new ArrayList<>();
        String sql = "SELECT * FROM Counselors";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                counselors.add(new Counselor(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("specialization"),
                    rs.getString("availability")
                ));
            }
        }
        return counselors;
    }

    //Updates an existing counselor
    public void updateCounselor(Counselor counselor) throws SQLException {
        String sql = "UPDATE Counselors SET name=?, specialization=?, availability=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, counselor.getName());
            stmt.setString(2, counselor.getSpecialization());
            stmt.setString(3, counselor.getAvailability());
            stmt.setInt(4, counselor.getId());
            stmt.executeUpdate();
        }
    }

    //Deletes a counselor by ID
    public void deleteCounselor(int id) throws SQLException {
        String sql = "DELETE FROM Counselors WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}