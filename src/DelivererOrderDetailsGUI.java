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
    private boolean isOrderPickedUp; // Flag to track if the order has been picked up

    private JButton pickupDeliveredButton;
    private JButton cancelButton;

    /**
     * Constructor for DelivererOrderDetailsGUI.
     * @param controller The main application controller.
     * @param order The order whose details are to be displayed.
     */
    public DelivererOrderDetailsGUI(AppController controller, Order order) {
        this.controller = controller;
        this.order = order;
        
        // Initialize isOrderPickedUp based on the current order status
        this.isOrderPickedUp = order.getStatus().equals("Picked Up");

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top Panel with Title and Back button
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Order Details - " + order.getOrderID(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        // Back button action: returns to the deliverer menu, clearing any 'currentlyViewedOrder'
        backButton.addActionListener(e -> controller.returnToDelivererMenu());
        topPanel.add(backButton, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);

        // Main details panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(0, 1, 5, 5));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Order Information"));

        detailsPanel.add(new JLabel("Order ID: " + order.getOrderID()));
        detailsPanel.add(new JLabel("Customer Address: " + order.getDestinationAddress()));
        
        // Items in the order
        detailsPanel.add(new JLabel("Items: "));
        if (order.getItems() != null) {
            for (MenuItem item : order.getItems()) {
                detailsPanel.add(new JLabel(" - " + item.getName() + " ($" + String.format("%.2f", item.getPrice()) + ")"));
            }
        }
        detailsPanel.add(new JLabel("Total Price: $" + String.format("%.2f", order.getTotalPrice())));
        
        add(detailsPanel, BorderLayout.CENTER);

        // Bottom Panel with buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        pickupDeliveredButton = new JButton(); // Initialize without text
        pickupDeliveredButton.setFont(new Font("Arial", Font.BOLD, 16));
        
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16));
        
        // Set initial button text and enabled state based on order status
        if (isOrderPickedUp) {
            pickupDeliveredButton.setText("Order Delivered");
            cancelButton.setEnabled(false); // Cannot cancel once picked up
        } else {
            pickupDeliveredButton.setText("Order Pickup");
            cancelButton.setEnabled(true);
        }

        // Add action listeners
        pickupDeliveredButton.addActionListener(e -> {
            if (!isOrderPickedUp) {
                // If the order hasn't been picked up yet, this button acts as "Order Pickup"
                // Change button text and state
                pickupDeliveredButton.setText("Order Delivered");
                cancelButton.setEnabled(false); // Cannot cancel once picked up
                isOrderPickedUp = true; // Mark as picked up in this GUI instance
                controller.handleOrderPickup(); // Delegate actual model update to AppController
            } else {
                // If the order has already been picked up, this button acts as "Order Delivered"
                controller.handleOrderDelivered(); // Delegate delivery completion to AppController
            }
        });

        cancelButton.addActionListener(e -> {
            if (isOrderPickedUp) {
                // If the order has been picked up, it cannot be cancelled
                JOptionPane.showMessageDialog(this, "You cannot cancel an order that has been picked up.", "Cannot Cancel", JOptionPane.WARNING_MESSAGE);
            } else {
                // If not picked up, simply return to the previous menu.
                // The order's status in the model (AppController) is still "Pending".
                controller.returnToDelivererMenu();
            }
        });

        buttonPanel.add(cancelButton);
        buttonPanel.add(pickupDeliveredButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}