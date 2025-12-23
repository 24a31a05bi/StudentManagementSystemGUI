import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main {
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createGUI());
    }

    public static void createGUI() {
        JFrame frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Header
        JLabel header = new JLabel("Student Management System", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(header, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JButton addBtn = new JButton("Add Student");
        JButton removeBtn = new JButton("Remove Student");
        JButton updateBtn = new JButton("Update Student");
        JButton displayBtn = new JButton("Display All Students");
        JButton searchBtn = new JButton("Search by ID");
        JButton topBtn = new JButton("Show Top Scorer");

        buttonPanel.add(addBtn);
        buttonPanel.add(removeBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(displayBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(topBtn);

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Action Listeners

        addBtn.addActionListener(e -> {
            JTextField nameField = new JTextField();
            JTextField idField = new JTextField();
            JTextField marksField = new JTextField();
            JTextField emailField = new JTextField();
            Object[] message = {
                    "Name:", nameField,
                    "ID:", idField,
                    "Marks:", marksField,
                    "Email:", emailField
            };
            int option = JOptionPane.showConfirmDialog(frame, message, "Add Student", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    String name = nameField.getText();
                    int id = Integer.parseInt(idField.getText());
                    double marks = Double.parseDouble(marksField.getText());
                    String email = emailField.getText();
                    students.add(new Student(name, id, marks, email));
                    JOptionPane.showMessageDialog(frame, "Student added successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter ID to remove:");
            if (input != null) {
                int id = Integer.parseInt(input);
                Student toRemove = null;
                for (Student s : students) {
                    if (s.getId() == id) {
                        toRemove = s;
                        break;
                    }
                }
                if (toRemove != null) {
                    students.remove(toRemove);
                    JOptionPane.showMessageDialog(frame, "Student removed successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updateBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter ID to update:");
            if (input != null) {
                int id = Integer.parseInt(input);
                for (Student s : students) {
                    if (s.getId() == id) {
                        JTextField nameField = new JTextField(s.getName());
                        JTextField marksField = new JTextField(String.valueOf(s.getMarks()));
                        JTextField emailField = new JTextField(s.getEmail());
                        Object[] message = {
                                "Name:", nameField,
                                "Marks:", marksField,
                                "Email:", emailField
                        };
                        int option = JOptionPane.showConfirmDialog(frame, message, "Update Student", JOptionPane.OK_CANCEL_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            s.setName(nameField.getText());
                            s.setMarks(Double.parseDouble(marksField.getText()));
                            s.setEmail(emailField.getText());
                            JOptionPane.showMessageDialog(frame, "Student updated successfully!");
                        }
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        displayBtn.addActionListener(e -> {
            if (students.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No students available!");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (Student s : students) {
                sb.append(s).append("\n");
            }
            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(450, 250));
            JOptionPane.showMessageDialog(frame, scrollPane, "All Students", JOptionPane.INFORMATION_MESSAGE);
        });

        searchBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter ID to search:");
            if (input != null) {
                int id = Integer.parseInt(input);
                for (Student s : students) {
                    if (s.getId() == id) {
                        JOptionPane.showMessageDialog(frame, s.toString());
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        topBtn.addActionListener(e -> {
            if (students.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No students available!");
                return;
            }
            Student top = students.get(0);
            for (Student s : students) {
                if (s.getMarks() > top.getMarks()) top = s;
            }
            JOptionPane.showMessageDialog(frame, "Top Scorer:\n" + top.toString());
        });

        frame.setVisible(true);
    }
}