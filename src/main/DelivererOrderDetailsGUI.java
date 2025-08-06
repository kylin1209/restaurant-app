package main;

import javax.swing.*;
import java.awt.*;

/**
 * GUI for deliverers to view detailed order information.
 */
public class DelivererOrderDetailsGUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private AppController controller;
    private Order order;

    private JButton pickupDeliveredButton;
    private JButton cancelButton;
    private JLabel statusLabel;

    public DelivererOrderDetailsGUI(AppController controller, Order order) {
        this.controller = controller;
        this.order = order;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Order Details - " + order.getOrderID(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> controller.returnToDelivererMenu());
        topPanel.add(backButton, BorderLayout.WEST);
        add(topPanel, BorderLayout.NORTH);

        // Details Panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(0, 1, 5, 5));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Order Information"));

        detailsPanel.add(new JLabel("Order ID: " + order.getOrderID()));
        detailsPanel.add(new JLabel("Customer Address: " + order.getDestinationAddress()));
        statusLabel = new JLabel("Current Status: " + order.getStatus());
        detailsPanel.add(statusLabel);

        detailsPanel.add(new JLabel("Items: "));
        if (order.getItems() != null) {
            for (MenuItem item : order.getItems()) {
                detailsPanel.add(new JLabel(" - " + item.getName() + " ($" + String.format("%.2f", item.getPrice()) + ")"));
            }
        }
        detailsPanel.add(new JLabel("Total Price: $" + String.format("%.2f", order.getTotalPrice())));
        add(detailsPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        pickupDeliveredButton = new JButton();
        pickupDeliveredButton.setFont(new Font("Arial", Font.BOLD, 16));

        cancelButton = new JButton("Cancel Order");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16));

        updateButtonState();

        pickupDeliveredButton.addActionListener(e -> {
            String status = order.getStatus();

            if (status.equals("Pending")) {
                order.setStatus("Booked");
                updateButtonState();  // Button becomes "Order Delivered"
            } else if (status.equals("Booked")) {
                order.setStatus("Delivered");
                controller.showDelivererThankYouGUI(order.getTotalPrice()); // Final screen
            }

            updateButtonState(); // Refresh button and label
        });

        cancelButton.addActionListener(e -> {
            if (order.getStatus().equals("Pending")) {
                JOptionPane.showMessageDialog(this, "Order " + order.getOrderID() + " has been cancelled.", "Order Cancelled", JOptionPane.INFORMATION_MESSAGE);
                controller.returnToDelivererMenu();
            } else {
                JOptionPane.showMessageDialog(this, "You cannot cancel an order that has already been picked up.", "Cannot Cancel", JOptionPane.WARNING_MESSAGE);
            }
        });

        buttonPanel.add(cancelButton);
        buttonPanel.add(pickupDeliveredButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateButtonState() {
        String status = order.getStatus();
        statusLabel.setText("Current Status: " + status);

        switch (status) {
            case "Pending":
                pickupDeliveredButton.setText("Order Pickup");
                pickupDeliveredButton.setEnabled(true);
                cancelButton.setEnabled(true);
                break;
            case "Booked":
                pickupDeliveredButton.setText("Order Delivered");
                pickupDeliveredButton.setEnabled(true);
                cancelButton.setEnabled(false);
                break;
            case "Delivered":
                pickupDeliveredButton.setText("Order Delivered");
                pickupDeliveredButton.setEnabled(false);
                cancelButton.setEnabled(false);
                break;
            default:
                pickupDeliveredButton.setText("Unknown Status");
                pickupDeliveredButton.setEnabled(false);
                cancelButton.setEnabled(false);
        }

        revalidate();
        repaint();
    }
}