package Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AttendanceSystemGUI extends JFrame {
    private AttendanceManager manager;
    private JTextArea reportArea;
    private JTextField nameField, idField;
    private JComboBox<String> studentComboBox;
    private JCheckBox presentCheckBox;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public AttendanceSystemGUI() {
        manager = new AttendanceManager();
        setupGUI();
    }

    private void setupGUI() {
        setTitle("Student Attendance System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add Student Section
        JPanel addStudentPanel = new JPanel(new GridBagLayout());
        addStudentPanel.setBorder(BorderFactory.createTitledBorder("Add New Student"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        addStudentPanel.add(new JLabel("Name:"), gbc);
        
        gbc.gridx = 1;
        nameField = new JTextField(20);
        addStudentPanel.add(nameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        addStudentPanel.add(new JLabel("ID:"), gbc);
        
        gbc.gridx = 1;
        idField = new JTextField(20);
        addStudentPanel.add(idField, gbc);
        
        gbc.gridy = 2;
        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> addStudent());
        addStudentPanel.add(addButton, gbc);

        // Mark Attendance Section
        JPanel markAttendancePanel = new JPanel(new GridBagLayout());
        markAttendancePanel.setBorder(BorderFactory.createTitledBorder("Mark Attendance"));
        
        gbc.gridx = 0; gbc.gridy = 0;
        markAttendancePanel.add(new JLabel("Student:"), gbc);
        
        gbc.gridx = 1;
        studentComboBox = new JComboBox<>();
        markAttendancePanel.add(studentComboBox, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        markAttendancePanel.add(new JLabel("Present:"), gbc);
        
        gbc.gridx = 1;
        presentCheckBox = new JCheckBox();
        markAttendancePanel.add(presentCheckBox, gbc);
        
        gbc.gridy = 2;
        JButton markButton = new JButton("Mark Attendance");
        markButton.addActionListener(e -> markAttendance());
        markAttendancePanel.add(markButton, gbc);

        // Reports Section
        JPanel reportsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        reportsPanel.setBorder(BorderFactory.createTitledBorder("Reports"));
        
        JButton weeklyButton = new JButton("Generate Weekly Report");
        weeklyButton.addActionListener(e -> generateWeeklyReport());
        reportsPanel.add(weeklyButton);
        
        JButton monthlyButton = new JButton("Generate Monthly Report");
        monthlyButton.addActionListener(e -> generateMonthlyReport());
        reportsPanel.add(monthlyButton);
        
        JButton absenteesButton = new JButton("Show Absentees");
        absenteesButton.addActionListener(e -> showAbsentees());
        reportsPanel.add(absenteesButton);

        // Report display area
        reportArea = new JTextArea(20, 40);
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Report Output"));

        // Add all panels to main panel
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(addStudentPanel, BorderLayout.NORTH);
        leftPanel.add(markAttendancePanel, BorderLayout.CENTER);
        leftPanel.add(reportsPanel, BorderLayout.SOUTH);
        
        mainPanel.add(leftPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.EAST);

        add(mainPanel);
        pack();
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String id = idField.getText().trim();
        
        if (name.isEmpty() || id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both name and ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            manager.addStudent(name, id);
            updateStudentComboBox();
            nameField.setText("");
            idField.setText("");
            JOptionPane.showMessageDialog(this, "Student added successfully!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void markAttendance() {
        String selectedStudent = (String) studentComboBox.getSelectedItem();
        if (selectedStudent == null) {
            JOptionPane.showMessageDialog(this, "Please select a student", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = selectedStudent.split(" - ")[1];
        boolean present = presentCheckBox.isSelected();
        
        try {
            manager.markAttendance(id, LocalDate.now(), present);
            JOptionPane.showMessageDialog(this, "Attendance marked successfully!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generateWeeklyReport() {
        String report = manager.generateWeeklyReport(LocalDate.now());
        reportArea.setText(report);
    }

    private void generateMonthlyReport() {
        String report = manager.generateMonthlyReport(LocalDate.now());
        reportArea.setText(report);
    }

    private void showAbsentees() {
        List<String> absentees = manager.getAbsentees(LocalDate.now());
        StringBuilder report = new StringBuilder("Absentees for today (" + LocalDate.now().format(dateFormatter) + "):\n");
        report.append("--------------------------------\n");
        for (String name : absentees) {
            report.append(name).append("\n");
        }
        reportArea.setText(report.toString());
    }

    private void updateStudentComboBox() {
        studentComboBox.removeAllItems();
        // Add the student name and ID to the combo box
        studentComboBox.addItem(nameField.getText() + " - " + idField.getText());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new AttendanceSystemGUI().setVisible(true);
        });
    }
} 