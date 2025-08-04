package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * A simple GUI to thank the deliverer after a delivery and show their earnings.
 */
public class DelivererThankYouGUI extends JPanel {
    private AppController controller;

    public DelivererThankYouGUI(AppController controller, double earnings) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));

        // Main thank you message
        JLabel thankYouLabel = new JLabel("Delivery Complete!", SwingConstants.CENTER);
        thankYouLabel.setFont(new Font("Arial", Font.BOLD, 36));
        add(thankYouLabel, BorderLayout.NORTH);

        // Earnings message
        JLabel earningsLabel = new JLabel("You earned $" + String.format("%.2f", earnings) + " on this delivery.", SwingConstants.CENTER);
        earningsLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(earningsLabel, BorderLayout.CENTER);

        // Back button to return to the main menu
        JButton backButton = new JButton("Back to Available Orders");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.addActionListener(e -> controller.returnToDelivererMenu());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
