/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;
import java.sql.Connection;
import dao.DBConnection;
import controller.CounselorController;
import view.CounselorPanel;
import dao.CounselorDAO;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                //Database connection
                Connection conn = DBConnection.getConnection();

                //Setup DAO 
                CounselorDAO dao = new CounselorDAO(conn);
                dao.initializeTable();

                //Controller creation (optionally pass DAO if needed)
                CounselorController controller = new CounselorController(conn); 

                //Panel and inject controller
                CounselorPanel panel = new CounselorPanel();
                panel.injectController(controller); // Injects active logic layer

                //GUI frame setup
                JFrame frame = new JFrame("Test Counselor Panel");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(600, 400);
                frame.add(panel);
                frame.setVisible(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Failed to start application:\n" + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}