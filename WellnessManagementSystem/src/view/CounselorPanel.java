package view;

import controller.CounselorController;
import model.Counselor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CounselorPanel extends JPanel {
    private JTextField nameField, specializationField, availabilityField;
    private JButton addButton, updateButton, deleteButton, clearButton;
    private JTable counselorTable;
    private CounselorController controller;

    public CounselorPanel() {
        setLayout(new BorderLayout());

        // Top form panel
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

        // Buttons
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

        // Table
        String[] columns = {"ID", "Name", "Specialization", "Availability"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        counselorTable = new JTable(tableModel);
        add(new JScrollPane(counselorTable), BorderLayout.SOUTH);

        // Table row listener (fills form)
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

        // Add Button
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String spec = specializationField.getText().trim();
            String avail = availabilityField.getText().trim();

            if (name.isBlank() || spec.isBlank() || avail.isBlank()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            controller.addCounselor(name, spec, avail);
            loadCounselorsIntoTable();
            clearForm();
        });

        // Update Button
        updateButton.addActionListener(e -> {
            int selectedRow = counselorTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) counselorTable.getValueAt(selectedRow, 0);
                String name = nameField.getText();
                String spec = specializationField.getText();
                String avail = availabilityField.getText();

                Counselor updated = new Counselor(id, name, spec, avail);
                controller.updateCounselor(updated);
                loadCounselorsIntoTable();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a counselor to update.");
            }
        });

        // Delete Button
        deleteButton.addActionListener(e -> {
            int selectedRow = counselorTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) counselorTable.getValueAt(selectedRow, 0);
                controller.deleteCounselor(id);
                loadCounselorsIntoTable();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a counselor to delete.");
            }
        });

        // Clear Button
        clearButton.addActionListener(e -> clearForm());
    }

    private void clearForm() {
        nameField.setText("");
        specializationField.setText("");
        availabilityField.setText("");
        counselorTable.clearSelection();
    }

    private void loadCounselorsIntoTable() {
        DefaultTableModel model = (DefaultTableModel) counselorTable.getModel();
        model.setRowCount(0); // Clear table

        List<Counselor> counselors = controller.getAllCounselors();
        for (Counselor c : counselors) {
            model.addRow(new Object[]{
                c.getId(), c.getName(), c.getSpecialization(), c.getAvailability()
            });
        }
    }

    // Use this after creating DB connection + controller
    public void injectController(CounselorController c) {
        this.controller = c;
        loadCounselorsIntoTable();
    }
}
