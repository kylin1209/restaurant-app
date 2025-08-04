package src;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI for deliverers to view detailed order information.
 */
public class DelivererOrderDetailsGUI extends JPanel {
    private AppController controller;
    private Order order;
    private boolean isOrderPickedUp = false;

    private JButton pickupDeliveredButton;
    private JButton cancelButton;

    public DelivererOrderDetailsGUI(AppController controller, Order order) {
        this.controller = controller;
        this.order = order;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top Panel with Title and Back button
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Order Details - " + order.getOrderID(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> controller.returnToDelivererMenu());
        topPanel.add(backButton, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);

        // Main details panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(0, 1, 5, 5));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Details"));

        JLabel idLabel = new JLabel("Order ID: " + order.getOrderID());
        JLabel statusLabel = new JLabel("Status: " + order.getStatus());
        JLabel addressLabel = new JLabel("Destination: " + order.getDestinationAddress());

        idLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        detailsPanel.add(idLabel);
        detailsPanel.add(statusLabel);
        detailsPanel.add(addressLabel);

        add(detailsPanel, BorderLayout.CENTER);

        // Bottom Panel with buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        pickupDeliveredButton = new JButton("Order Pickup");
        pickupDeliveredButton.setFont(new Font("Arial", Font.BOLD, 16));
        
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16));
        
        // Add action listeners
        pickupDeliveredButton.addActionListener(e -> {
            if (!isOrderPickedUp) {
                // Change button text and state
                pickupDeliveredButton.setText("Order Delivered");
                cancelButton.setEnabled(false);
                isOrderPickedUp = true;
                controller.handleOrderPickup();
            } else {
                controller.handleOrderDelivered();
            }
        });

        cancelButton.addActionListener(e -> {
            if (isOrderPickedUp) {
                JOptionPane.showMessageDialog(this, "You cannot cancel an order that has been picked up.", "Cannot Cancel", JOptionPane.WARNING_MESSAGE);
            } else {
                controller.showDelivererBookOrderGUI();
            }
        });

        buttonPanel.add(cancelButton);
        buttonPanel.add(pickupDeliveredButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
