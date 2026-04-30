package view;

import data.DataStore;
import model.Course;
import model.GradeRecord;
import model.StudentProfile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InstructorPanel extends JFrame {

    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color BACKGROUND_COLOR = new Color(245, 247, 250);
    private static final Color PANEL_COLOR = Color.WHITE;
    private static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 22);
    private static final Font SUBTITLE_FONT = new Font("SansSerif", Font.BOLD, 16);
    private static final Font NORMAL_FONT = new Font("SansSerif", Font.PLAIN, 14);

    private final String instructorUsername;

    private JTable gradesTable;
    private DefaultTableModel gradesTableModel;
    private JComboBox<String> courseComboBox;
    private JTextField midtermField;
    private JTextField finalField;
    private String selectedStudentUsername;
    private List<Course> myCourses;

    public InstructorPanel(String instructorUsername) {
        this.instructorUsername = instructorUsername;

        setTitle("SISA | Instructor Panel");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 500));

        add(createMainPanel());
        setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        mainPanel.add(createTabbedPane(), BorderLayout.CENTER);
        return mainPanel;
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(18, 25, 18, 25));

        JLabel titleLabel = new JLabel("SISA Instructor Panel");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(TITLE_FONT);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFocusPainted(false);
        logoutButton.setFont(NORMAL_FONT);
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginPage();
        });

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        rightPanel.setOpaque(false);
        rightPanel.add(logoutButton);

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(rightPanel, BorderLayout.EAST);

        return headerPanel;
    }

    private JTabbedPane createTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(NORMAL_FONT);

        tabbedPane.addTab("My Courses", createMyCoursesPanel());
        tabbedPane.addTab("Enter Grades", createEnterGradesPanel());

        return tabbedPane;
    }

    private JPanel createMyCoursesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel titleLabel = new JLabel("My Courses");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(new Color(44, 62, 80));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        String[] columns = {"Course Code", "Course Name", "Credit", "Quota"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };

        for (Course course : DataStore.getCoursesByInstructor(instructorUsername)) {
            model.addRow(new Object[]{
                    course.getCourseCode(),
                    course.getCourseName(),
                    course.getCredit(),
                    course.getQuota()
            });
        }

        JTable table = new JTable(model);
        table.setFont(NORMAL_FONT);
        table.setRowHeight(28);
        table.getTableHeader().setFont(SUBTITLE_FONT);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createEnterGradesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel titleLabel = new JLabel("Enter / Update Grades");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(new Color(44, 62, 80));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        myCourses = DataStore.getCoursesByInstructor(instructorUsername);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        topPanel.setBackground(BACKGROUND_COLOR);
        JLabel courseLabel = new JLabel("Select Course:");
        courseLabel.setFont(NORMAL_FONT);

        courseComboBox = new JComboBox<>();
        courseComboBox.setFont(NORMAL_FONT);
        courseComboBox.setPreferredSize(new Dimension(220, 30));

        for (Course course : myCourses) {
            courseComboBox.addItem(course.getCourseCode() + " - " + course.getCourseName());
        }

        topPanel.add(courseLabel);
        topPanel.add(courseComboBox);
        String[] columns = {"Student Username", "Full Name", "Midterm", "Final"};
        gradesTableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };

        gradesTable = new JTable(gradesTableModel);
        gradesTable.setFont(NORMAL_FONT);
        gradesTable.setRowHeight(28);
        gradesTable.getTableHeader().setFont(SUBTITLE_FONT);
        gradesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel gradeInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        gradeInputPanel.setBackground(PANEL_COLOR);
        gradeInputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        JLabel midtermLabel = new JLabel("Midterm:");
        midtermLabel.setFont(NORMAL_FONT);
        midtermField = new JTextField(6);
        midtermField.setFont(NORMAL_FONT);

        JLabel finalLabel = new JLabel("Final:");
        finalLabel.setFont(NORMAL_FONT);
        finalField = new JTextField(6);
        finalField.setFont(NORMAL_FONT);

        JButton saveButton = new JButton("Save Grade");
        saveButton.setFont(NORMAL_FONT);
        saveButton.setFocusPainted(false);

        gradeInputPanel.add(midtermLabel);
        gradeInputPanel.add(midtermField);
        gradeInputPanel.add(finalLabel);
        gradeInputPanel.add(finalField);
        gradeInputPanel.add(saveButton);

        courseComboBox.addActionListener(e -> {
            int selectedIndex = courseComboBox.getSelectedIndex();
            if (selectedIndex == -1) return;
            loadStudentsForCourse(myCourses.get(selectedIndex).getCourseCode());
        });

        gradesTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && gradesTable.getSelectedRow() != -1) {
                int row = gradesTable.getSelectedRow();
                selectedStudentUsername = (String) gradesTableModel.getValueAt(row, 0);

                Object midterm = gradesTableModel.getValueAt(row, 2);
                Object finalExam = gradesTableModel.getValueAt(row, 3);

                midtermField.setText(midterm.equals("-") ? "" : midterm.toString());
                finalField.setText(finalExam.equals("-") ? "" : finalExam.toString());
            }
        });

        saveButton.addActionListener(e -> {
            if (selectedStudentUsername == null) {
                JOptionPane.showMessageDialog(this, "Please select a student.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String midtermText = midtermField.getText().trim();
            String finalText = finalField.getText().trim();

            if (midtermText.isEmpty() || finalText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both midterm and final grades.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                double midterm = Double.parseDouble(midtermText);
                double finalExam = Double.parseDouble(finalText);

                if (midterm < 0 || midterm > 100 || finalExam < 0 || finalExam > 100) {
                    JOptionPane.showMessageDialog(this, "Grades must be between 0 and 100.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int selectedIndex = courseComboBox.getSelectedIndex();
                String courseCode = myCourses.get(selectedIndex).getCourseCode();

                DataStore.saveGrade(selectedStudentUsername, courseCode, midterm, finalExam);
                loadStudentsForCourse(courseCode);

                JOptionPane.showMessageDialog(this, "Grade saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric grades.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel centerPanel = new JPanel(new BorderLayout(0, 15));
        centerPanel.setBackground(BACKGROUND_COLOR);
        centerPanel.add(new JScrollPane(gradesTable), BorderLayout.CENTER);
        centerPanel.add(gradeInputPanel, BorderLayout.SOUTH);

        JPanel contentPanel = new JPanel(new BorderLayout(0, 15));
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.add(topPanel, BorderLayout.NORTH);
        contentPanel.add(centerPanel, BorderLayout.CENTER);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);
        if (!myCourses.isEmpty()) {
            loadStudentsForCourse(myCourses.get(0).getCourseCode());
        }

        return panel;
    }

    private void loadStudentsForCourse(String courseCode) {
        gradesTableModel.setRowCount(0);
        selectedStudentUsername = null;
        midtermField.setText("");
        finalField.setText("");

        List<StudentProfile> studentList = DataStore.getStudentsByCourse(courseCode);
        for (StudentProfile student : studentList) {
            GradeRecord grade = DataStore.findGrade(student.getUsername(), courseCode);
            gradesTableModel.addRow(new Object[]{
                    student.getUsername(),
                    student.getFullName(),
                    grade != null ? grade.getMidtermExam() : "-",
                    grade != null ? grade.getFinalExam() : "-"
            });
        }
    }
}