/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package view;

import controller.CounselorController;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class CounselorPanel extends JPanel {

    private JTextField nameField, specializationField, availabilityField;
    private JButton addButton, updateButton, deleteButton, clearButton;
    private JTable counselorTable;
    private CounselorController controller; //Added controller declaration

    public CounselorPanel() {
        setLayout(new BorderLayout());      

        // Initialize controller (DB connection, is null for now)
        controller = new CounselorController(null); 

        //Input form 
        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Specialization:"));
        specializationField = new JTextField();
        formPanel.add(specializationField);

        formPanel.add(new JLabel("Availability:"));
        availabilityField = new JTextField();
        formPanel.add(availabilityField);

        add(formPanel, BorderLayout.NORTH);

        //Button setup
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.CENTER);

        //Table setup with mock data
        String[] columns = {"ID", "Name", "Specialization", "Availability"};
        Object[][] data = {
            {1, "Dr. Zanele", "Psychologist", "Available"},
            {2, "Mr. Thabo", "Career Guidance", "Unavailable"}
        };

        DefaultTableModel tableModel = new DefaultTableModel(data, columns);
        counselorTable = new JTable(tableModel);
        add(new JScrollPane(counselorTable), BorderLayout.SOUTH);

        //Row selection autofills form
        counselorTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = counselorTable.getSelectedRow();
                if (selectedRow != -1) {
                    nameField.setText(counselorTable.getValueAt(selectedRow, 1).toString());
                    specializationField.setText(counselorTable.getValueAt(selectedRow, 2).toString());
                    availabilityField.setText(counselorTable.getValueAt(selectedRow, 3).toString());
                }
            }
        });

        //Button listeners with controller integration
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String spec = specializationField.getText();
            String avail = availabilityField.getText();
            
            //Input validation
            if (name.isBlank()) {
               JOptionPane.showMessageDialog(this, "Name cannot be empty.");
                return;
            }

            
            controller.addCounselor(name, spec, avail);
        });

        updateButton.addActionListener(e -> {
            System.out.println("Update pressed");
            
             int selectedRow = counselorTable.getSelectedRow();
    if (selectedRow != -1) {
        int id = (int) counselorTable.getValueAt(selectedRow, 0);
        String name = nameField.getText();
        String spec = specializationField.getText();
        String avail = availabilityField.getText();

        model.Counselor updated = new model.Counselor(id, name, spec, avail);
        controller.updateCounselor(updated);
        loadCounselorsIntoTable(); // Refresh table
    } else {
        JOptionPane.showMessageDialog(this, "Please select a counselor to update.");
    }

        });

        deleteButton.addActionListener(e -> {
            System.out.println("Delete pressed");
            
            int selectedRow = counselorTable.getSelectedRow();
    if (selectedRow != -1) {
        int id = (int) counselorTable.getValueAt(selectedRow, 0);
        controller.deleteCounselor(id);
        loadCounselorsIntoTable(); // Refresh table
    } else {
        JOptionPane.showMessageDialog(this, "Please select a Counselor to delete.");
    }

        });

        clearButton.addActionListener(e -> {
            nameField.setText("");
            specializationField.setText("");
            availabilityField.setText("");
        });
        
        
        
        
    }
     
    private void loadCounselorsIntoTable() {
    DefaultTableModel model = (DefaultTableModel) counselorTable.getModel();
    model.setRowCount(0); // Clear existing rows

    var counselors = controller.getAllCounselors();
    if (counselors != null) {
        for (var c : counselors) {
            Object[] row = {
                c.getId(),
                c.getName(),
                c.getSpecialization(),
                c.getAvailability()
            };
            model.addRow(row);
        }
     
    
  }
    
       

 }
    //Injects controller dynamically
    public void injectController(CounselorController c) {
        this.controller = c;
        loadCounselorsIntoTable();
    }

   
  
}
    


