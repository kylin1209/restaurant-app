package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The initial GUI screen allowing users to choose between Customer or Deliverer login.
 */
public class LoginSelectionGUI extends JPanel {
    private AppController controller;

    /**
     * Constructor for LoginSelectionGUI.
     * @param controller The main application controller.
     */
    public LoginSelectionGUI(AppController controller) {
        this.controller = controller;
        setLayout(new GridBagLayout()); // Use GridBagLayout for flexible centering
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Add padding

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 0, 15, 0); // Padding between components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Title Label
        JLabel titleLabel = new JLabel("Welcome! Please choose your role:", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        gbc.gridy++;
        add(titleLabel, gbc);

        gbc.insets = new Insets(25, 0, 25, 0); // More padding for buttons
        gbc.ipadx = 50; // Increase button width
        gbc.ipady = 20; // Increase button height

        // Deliverer Login Button
        JButton delivererLoginButton = new JButton("Login as Deliverer");
        delivererLoginButton.setFont(new Font("Arial", Font.BOLD, 20));
        delivererLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showDelivererLoginGUI();
            }
        });
        gbc.gridy++;
        add(delivererLoginButton, gbc);

        // Customer Login Button (Placeholder for future implementation)
        JButton customerLoginButton = new JButton("Login as Customer");
        customerLoginButton.setFont(new Font("Arial", Font.BOLD, 20));
        customerLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(LoginSelectionGUI.this, "Customer login is not yet implemented.", "Coming Soon", JOptionPane.INFORMATION_MESSAGE);
                // controller.showCustomerLoginGUI(); // Placeholder for future customer login screen
            }
        });
        gbc.gridy++;
        add(customerLoginButton, gbc);
    }
}
