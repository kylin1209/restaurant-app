package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI screen for deliverer sign-up.
 */
public class DelivererSignUpGUI extends JPanel {
    private AppController controller;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    /**
     * Constructor for DelivererSignUpGUI.
     * @param controller The main application controller.
     */
    public DelivererSignUpGUI(AppController controller) {
        this.controller = controller;
        setLayout(new GridBagLayout()); // Use GridBagLayout for flexible centering
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Add padding

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Padding between components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Title Label
        JLabel titleLabel = new JLabel("Deliverer Sign Up", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        gbc.gridwidth = 2; // Span two columns
        add(titleLabel, gbc);

        gbc.gridwidth = 1; // Reset to one column for subsequent components
        gbc.insets = new Insets(10, 5, 10, 5); // Adjust padding for labels and fields

        // Username Label and Field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridy++;
        gbc.gridx = 0;
        add(usernameLabel, gbc);

        usernameField = new JTextField(20); // 20 columns wide
        usernameField.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        add(usernameField, gbc);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridy++;
        gbc.gridx = 0;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(20); // 20 columns wide
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        add(passwordField, gbc);

        // Confirm Password Label and Field
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridy++;
        gbc.gridx = 0;
        add(confirmPasswordLabel, gbc);

        confirmPasswordField = new JPasswordField(20); // 20 columns wide
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        add(confirmPasswordField, gbc);

        gbc.gridwidth = 2; // Span two columns for buttons
        gbc.insets = new Insets(20, 0, 10, 0); // More padding for buttons
        gbc.ipadx = 50; // Increase button width
        gbc.ipady = 15; // Increase button height

        // Sign Up Button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 20));
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                // Delegate signup logic to AppController
                controller.handleDelivererSignUp(username, password, confirmPassword);
            }
        });
        gbc.gridy++;
        gbc.gridx = 0;
        add(signUpButton, gbc);

        gbc.insets = new Insets(10, 0, 20, 0); // Padding for back button

        // Back to Login Button
        JButton backButton = new JButton("Back to Login");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showDelivererLoginGUI();
            }
        });
        gbc.gridy++;
        add(backButton, gbc);
    }
}
