package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * AppController manages the overall application flow and GUI transitions.
 * This class is the central hub for all actions and state changes.
 */

public class AppController {

    private JFrame mainFrame;
    private Deliverer currentDeliverer;
    private Customer currentCustomer;
    private List<Order> allOrders;

    /**
     * Constructor for the AppController.
     * Initializes the main JFrame and a list of all orders.
     */
    public AppController() {
        this.allOrders = new ArrayList<>();
        this.mainFrame = new JFrame("Deliverer Application");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(800, 600);
        this.mainFrame.setLocationRelativeTo(null);
    }

    /**
     * Initializes the application with dummy data and shows the initial GUI.
     */
    public void initializeApp() {
        // We simulate a logged-in deliverer and customer for demonstration
        this.currentDeliverer = new Deliverer("d_smith", "pass123");
        this.currentCustomer = new Customer("c_jones", "pass456", "456 Oak Ave");

        // Simulate some pending orders
        this.allOrders.add(new Order("101", "Pending", "123 Main St."));
        this.allOrders.add(new Order("102", "Pending", "456 Pine Blvd."));
        this.allOrders.add(new Order("103", "Pending", "789 Elm Rd."));
        
        // Simulate a past order for the deliverer
        Order pastDelivery = new Order("001", "Delivered", "999 Delivery St.");
        this.currentDeliverer.getPastDeliveries().add(pastDelivery);

        // Simulate a past order for the customer
        this.currentCustomer.getPastOrders().add(new Order("C-001", "Delivered", "456 Oak Ave"));

        // Start the application by showing the deliverer's main booking screen
        showDelivererBookOrderGUI();
        this.mainFrame.setVisible(true);
    }

    /**
     * A utility method to switch the content of the main frame to a new JPanel.
     * @param panel The JPanel to display.
     */
    private void switchPanel(JPanel panel) {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(panel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    /**
     * Displays the main deliverer booking GUI.
     */
    public void showDelivererBookOrderGUI() {
        // Filter for pending orders
        List<Order> pendingOrders = allOrders.stream()
            .filter(order -> order.getStatus().equals("Pending"))
            .collect(Collectors.toList());
        
        DelivererBookOrderGUI bookingGUI = new DelivererBookOrderGUI(this, currentDeliverer, allOrders);
        switchPanel(bookingGUI);
    }

    /**
     * Displays the deliverer's order details GUI for a specific order.
     * @param orderToShow The order to display details for.
     */
    public void showDelivererOrderDetailsGUI(Order orderToShow) {
        if (orderToShow != null) {
            DelivererOrderDetailsGUI detailsGUI = new DelivererOrderDetailsGUI(this, orderToShow);
            switchPanel(detailsGUI);
        } else {
            // Should not happen if app flow is correct, but good to handle
            JOptionPane.showMessageDialog(mainFrame, "No active order to view details for.", "Error", JOptionPane.ERROR_MESSAGE);
            showDelivererBookOrderGUI();
        }
    }

    /**
     * Displays the deliverer's past deliveries GUI.
     */
    public void showDelivererPastOrdersGUI() {
        DelivererPastOrdersGUI pastOrdersGUI = new DelivererPastOrdersGUI(this, currentDeliverer);
        switchPanel(pastOrdersGUI);
    }
    
    /**
     * Displays the thank you screen for a completed delivery.
     */
    public void showDelivererThankYouGUI(double earnings) {
        DelivererThankYouGUI thankYouGUI = new DelivererThankYouGUI(this, earnings);
        switchPanel(thankYouGUI);
    }
    
    /**
     * Method to handle a potential typo in other classes, routing to the correct past deliveries GUI.
     */
    public void showPastDeliveriesGUI() {
        showDelivererPastOrdersGUI();
    }

  
    /**
     * Handles the logic for a deliverer booking a new order.
     * @param orderId The ID of the order to book.
     */
    public void handleBookOrder(String orderId) {
        if (currentDeliverer.getCurrentOrder() != null) {
            JOptionPane.showMessageDialog(mainFrame, "You already have an active order. Please complete it first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Order orderToBook = findOrderById(orderId);
        if (orderToBook != null && orderToBook.getStatus().equals("Pending")) {
            currentDeliverer.bookOrder(orderToBook);
            // Status remains 'Pending' until pickup is confirmed
            showDelivererOrderDetailsGUI(orderToBook); // Navigate to the details screen with the booked order
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Order not found or is no longer available.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles the logic for a deliverer picking up an order.
     */
    public void handleOrderPickup() {
        if (currentDeliverer.getCurrentOrder() != null) {
            // Now we change the status when the order is picked up
            currentDeliverer.getCurrentOrder().setStatus("Picked Up");
            JOptionPane.showMessageDialog(mainFrame, "Order picked up. Please proceed to delivery.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Handles the logic for a deliverer completing a delivery.
     */
    public void handleOrderDelivered() {
        Order deliveredOrder = currentDeliverer.getCurrentOrder();
        if (deliveredOrder != null) {
            double earnings = 5.0; // Hardcoded earnings for simplicity
            deliveredOrder.setStatus("Delivered");
            currentDeliverer.addPastDelivery(deliveredOrder);
            currentDeliverer.setCurrentOrder(null); // Clear the active order
            
            showDelivererThankYouGUI(earnings); // Navigate to the thank you screen
        }
    }
    
    /**
     * Returns to the main deliverer menu.
     */
    public void returnToDelivererMenu() {
        showDelivererBookOrderGUI();
    }
    
    /**
     * Placeholder method for a login GUI, as requested by the user's original code structure.
     */
    public void showLoginGUI() {
        JOptionPane.showMessageDialog(mainFrame, "Returning to a simulated login screen.", "Log Out", JOptionPane.INFORMATION_MESSAGE);
        // For this example, we just restart the flow
        initializeApp();
    }

    /**
     * Finds an order in the list by its ID.
     * @param orderId The ID of the order to find.
     * @return The Order object if found, otherwise null.
     */
    private Order findOrderById(String orderId) {
        for (Order order : allOrders) {
            if (order.getOrderID().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    /**
     * The main method to run the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppController app = new AppController();
            app.initializeApp();
        });
    }
}
