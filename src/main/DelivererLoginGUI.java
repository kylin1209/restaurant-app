package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI screen for deliverer login.
 */
public class DelivererLoginGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private AppController controller;
    private JTextField usernameField;
    private JPasswordField passwordField;

    /**
     * Constructor for DelivererLoginGUI.
     * @param controller The main application controller.
     */
    public DelivererLoginGUI(AppController controller) {
        this.setController(controller);
        setLayout(new GridBagLayout()); // Use GridBagLayout for flexible centering
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Add padding

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Padding between components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Title Label
        JLabel titleLabel = new JLabel("Deliverer Login", SwingConstants.CENTER);
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

        gbc.gridwidth = 2; // Span two columns for buttons
        gbc.insets = new Insets(20, 0, 10, 0); // More padding for buttons
        gbc.ipadx = 50; // Increase button width
        gbc.ipady = 15; // Increase button height

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // Delegate login logic to AppController
                controller.handleDelivererLogin(username, password);
            }
        });
        gbc.gridy++;
        gbc.gridx = 0;
        add(loginButton, gbc);

        gbc.insets = new Insets(10, 0, 20, 0); // Padding for signup button

        // Sign Up Button
        JButton signUpButton = new JButton("Don't have an account? Sign Up");
        signUpButton.setFont(new Font("Arial", Font.PLAIN, 16));
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showDelivererSignUpGUI();
            }
        });
        gbc.gridy++;
        add(signUpButton, gbc);

        // Back to Role Selection Button
        JButton backButton = new JButton("Back to Role Selection");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showLoginSelectionGUI();
            }
        });
        gbc.gridy++;
        add(backButton, gbc);
    }

	public AppController getController() {
		return controller;
	}

	public void setController(AppController controller) {
		this.controller = controller;
	}
}