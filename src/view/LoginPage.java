package view;

import data.DataStore;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JButton btnLogin;

    public LoginPage() {
        setTitle("SISA | Login");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 5, 8, 5);

        JLabel lblTitle = new JLabel("SISA - Login", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(lblTitle, gbc);

        //username
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Username:"), gbc);
        tfUsername = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(tfUsername, gbc);

        //password
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Password:"), gbc);
        pfPassword = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(pfPassword, gbc);

        //login button
        btnLogin = new JButton("Login");
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 5, 5, 5);
        mainPanel.add(btnLogin, gbc);
        btnLogin.addActionListener(e -> handleLogin());

        add(mainPanel);
        setVisible(true);
    }

    private void handleLogin() {
        String username = tfUsername.getText().trim();
        String password = new String(pfPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Username and password cannot be empty.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        User user = DataStore.findUser(username);
        if (user == null) {
            JOptionPane.showMessageDialog(this, "User not found");
            return;
        }
        if (!user.getPassword().equals(password)) {
            JOptionPane.showMessageDialog(this, "Wrong password");
            return;
        }

        if(user.getRole().equals("ADMIN")) {
            new AdminPanel();
        } else if(user.getRole().equals("INSTRUCTOR")) {
            new InstructorPanel();
        } else if(user.getRole().equals("STUDENT")) {
            new StudentPanel();
        }

        JOptionPane.showMessageDialog(this,
                "Login successful! Welcome, " + username,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
