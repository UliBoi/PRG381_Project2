/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import dao.CounselorDAO;
import model.Counselor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CounselorController {
    private CounselorDAO dao;

    public CounselorController(Connection conn) {
        dao = new CounselorDAO(conn);
    }

    public void addCounselor(String name, String spec, String avail) {
        Counselor counselor = new Counselor(0, name, spec, avail);
        try {
            dao.addCounselor(counselor);
            System.out.println("Counselor added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Counselor> getAllCounselors() {
        try {
            return dao.getAllCounselors();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateCounselor(Counselor counselor) {
        try {
            dao.updateCounselor(counselor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCounselor(int id) {
        try {
            dao.deleteCounselor(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
