package main;

import javax.swing.*;
import java.awt.*;

/**
 * A simple GUI to thank the deliverer after a delivery and show their earnings.
 */
public class DelivererThankYouGUI extends JPanel {
    private static final long serialVersionUID = 1L;
    private AppController controller;
    private JButton menuButton;

    public DelivererThankYouGUI(AppController controller, double earnings) {
        this.setController(controller);
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top panel with menu and title
        JPanel topPanel = new JPanel(new BorderLayout());
        
        // Left side for hamburger menu button
        JPanel leftTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        menuButton = new JButton("☰ Menu");
        menuButton.setFont(new Font("Arial", Font.PLAIN, 14));
        leftTopPanel.add(menuButton);
        topPanel.add(leftTopPanel, BorderLayout.WEST);

        // Hamburger menu popup
        JPopupMenu hamburgerMenu = new JPopupMenu();
        JMenuItem availableOrdersItem = new JMenuItem("Available Orders");
        availableOrdersItem.addActionListener(e -> controller.returnToDelivererMenu());
        JMenuItem pastDeliveriesItem = new JMenuItem("Past Deliveries");
        pastDeliveriesItem.addActionListener(e -> controller.showDelivererPastOrdersGUI());
        JMenuItem logoutItem = new JMenuItem("Log Out");
        logoutItem.addActionListener(e -> controller.showLoginGUI());
        hamburgerMenu.add(availableOrdersItem);
        hamburgerMenu.add(pastDeliveriesItem);
        hamburgerMenu.add(logoutItem);
        menuButton.addActionListener(e -> hamburgerMenu.show(menuButton, 0, menuButton.getHeight()));

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

    public AppController getController() {
        return controller;
    }

    public void setController(AppController controller) {
        this.controller = controller;
    }
}