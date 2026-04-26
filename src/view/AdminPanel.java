package view;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JFrame {

    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color BACKGROUND_COLOR = new Color(245, 247, 250);
    private static final Color PANEL_COLOR = Color.WHITE;
    private static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 22);
    private static final Font SUBTITLE_FONT = new Font("SansSerif", Font.BOLD, 16);
    private static final Font NORMAL_FONT = new Font("SansSerif", Font.PLAIN, 14);

    public AdminPanel() {
        setTitle("SISA | Admin Panel");
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

        JLabel titleLabel = new JLabel("SISA Admin Dashboard");
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

        tabbedPane.addTab("Dashboard", createDashboardPanel());
        tabbedPane.addTab("Add User", createAddUserPanel());
        tabbedPane.addTab("Add Student", createAddStudentPanel());
        tabbedPane.addTab("Add Course", createAddCoursePanel());
        tabbedPane.addTab("Reports", createReportsPanel());

        return tabbedPane;
    }

    private JPanel createDashboardPanel() {
        JPanel dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBackground(BACKGROUND_COLOR);
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel welcomeLabel = new JLabel("Welcome to the Student Information System");
        welcomeLabel.setFont(TITLE_FONT);
        welcomeLabel.setForeground(new Color(44, 62, 80));

        JPanel cardsPanel = new JPanel(new GridLayout(1, 4, 20, 20));
        cardsPanel.setBackground(BACKGROUND_COLOR);
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        cardsPanel.add(createDashboardCard("Users", "Manage system users"));
        cardsPanel.add(createDashboardCard("Students", "Create student profiles"));
        cardsPanel.add(createDashboardCard("Courses", "Add and manage courses"));
        cardsPanel.add(createDashboardCard("Reports", "View system reports"));

        dashboardPanel.add(welcomeLabel, BorderLayout.NORTH);
        dashboardPanel.add(cardsPanel, BorderLayout.CENTER);

        return dashboardPanel;
    }

    private JPanel createDashboardCard(String title, String description) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(PANEL_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(SUBTITLE_FONT);
        titleLabel.setForeground(PRIMARY_COLOR);

        JLabel descriptionLabel = new JLabel(description);
        descriptionLabel.setFont(NORMAL_FONT);
        descriptionLabel.setForeground(new Color(90, 90, 90));

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(descriptionLabel, BorderLayout.CENTER);

        return card;
    }

    private JPanel createAddUserPanel() {
        JPanel panel = createFormContainer();

        JLabel titleLabel = createSectionTitle("Add New User");

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"ADMIN", "INSTRUCTOR", "STUDENT"});
        JTextField fullNameField = new JTextField();
        JTextField referenceIdField = new JTextField();

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(PANEL_COLOR);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));

        GridBagConstraints gbc = createDefaultGbc();

        addFormRow(formPanel, gbc, 0, "Username:", usernameField);
        addFormRow(formPanel, gbc, 1, "Password:", passwordField);
        addFormRow(formPanel, gbc, 2, "Role:", roleComboBox);
        addFormRow(formPanel, gbc, 3, "Full Name:", fullNameField);
        addFormRow(formPanel, gbc, 4, "Reference ID:", referenceIdField);

        JButton saveButton = createPrimaryButton("Save User");
        //TODO datastore will be added.
        saveButton.addActionListener(e -> JOptionPane.showMessageDialog(
                this,
                "User save action will be implemented with DataStore.",
                "Info",
                JOptionPane.INFORMATION_MESSAGE
        ));

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(PANEL_COLOR);
        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(createButtonPanel(saveButton), BorderLayout.SOUTH);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createAddStudentPanel() {
        JPanel panel = createFormContainer();

        JLabel titleLabel = createSectionTitle("Create Student Profile");

        JTextField studentIdField = new JTextField();
        JTextField fullNameField = new JTextField();
        JTextField departmentField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField usernameField = new JTextField();

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(PANEL_COLOR);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));

        GridBagConstraints gbc = createDefaultGbc();

        addFormRow(formPanel, gbc, 0, "Student ID:", studentIdField);
        addFormRow(formPanel, gbc, 1, "Full Name:", fullNameField);
        addFormRow(formPanel, gbc, 2, "Department:", departmentField);
        addFormRow(formPanel, gbc, 3, "Year:", yearField);
        addFormRow(formPanel, gbc, 4, "Username:", usernameField);

        JButton saveButton = createPrimaryButton("Save Student");

        saveButton.addActionListener(e -> JOptionPane.showMessageDialog(
                this,
                "Student save action will be implemented later.",
                "Info",
                JOptionPane.INFORMATION_MESSAGE
        ));

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(PANEL_COLOR);
        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(createButtonPanel(saveButton), BorderLayout.SOUTH);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createAddCoursePanel() {
        JPanel panel = createFormContainer();

        JLabel titleLabel = createSectionTitle("Add New Course");

        JTextField courseCodeField = new JTextField();
        JTextField courseNameField = new JTextField();
        JTextField creditField = new JTextField();
        JTextField quotaField = new JTextField();
        JTextField instructorUsernameField = new JTextField();

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(PANEL_COLOR);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));

        GridBagConstraints gbc = createDefaultGbc();

        addFormRow(formPanel, gbc, 0, "Course Code:", courseCodeField);
        addFormRow(formPanel, gbc, 1, "Course Name:", courseNameField);
        addFormRow(formPanel, gbc, 2, "Credit:", creditField);
        addFormRow(formPanel, gbc, 3, "Quota:", quotaField);
        addFormRow(formPanel, gbc, 4, "Instructor Username:", instructorUsernameField);

        JButton saveButton = createPrimaryButton("Save Course");

        saveButton.addActionListener(e -> JOptionPane.showMessageDialog(
                this,
                "Course save action will be implemented later.",
                "Info",
                JOptionPane.INFORMATION_MESSAGE
        ));

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(PANEL_COLOR);
        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(createButtonPanel(saveButton), BorderLayout.SOUTH);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createReportsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel titleLabel = createSectionTitle("System Reports");

        String[] columns = {"Report Type", "Description", "Status"};
        Object[][] data = {
                {"Users", "List all registered users", "Ready"},
                {"Students", "List all student profiles", "Pending"},
                {"Courses", "List all courses", "Pending"},
                {"Enrollments", "List course registrations", "Pending"}
        };

        JTable table = new JTable(data, columns);
        table.setFont(NORMAL_FONT);
        table.setRowHeight(28);
        table.getTableHeader().setFont(SUBTITLE_FONT);

        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createFormContainer() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        return panel;
    }

    private JLabel createSectionTitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(TITLE_FONT);
        label.setForeground(new Color(44, 62, 80));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        return label;
    }

    private GridBagConstraints createDefaultGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 8, 10, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        return gbc;
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, JComponent field) {
        JLabel label = new JLabel(labelText);
        label.setFont(NORMAL_FONT);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        gbc.ipadx = 20;
        panel.add(label, gbc);

        field.setFont(NORMAL_FONT);

        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.weightx = 1;
        gbc.ipadx = 0;
        panel.add(field, gbc);
    }

    private JButton createPrimaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(NORMAL_FONT);
        button.setFocusPainted(false);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(PRIMARY_COLOR, 1, true));
        button.setPreferredSize(new Dimension(140, 35));
        return button;
    }

    private JPanel createButtonPanel(JButton button) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(PANEL_COLOR);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        buttonPanel.add(button);
        return buttonPanel;
    }
}