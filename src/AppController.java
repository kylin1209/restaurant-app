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
    private Customer currentCustomer; // Keep Customer for dummy data/basic reference if needed by Order
    private List<Order> allOrders;

    // GUI instances to maintain state and allow updates
    private DelivererBookOrderGUI delivererBookOrderGUI;

    // A temporary holder for the order currently being viewed in DelivererOrderDetailsGUI
    private Order currentlyViewedOrder;


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
        this.currentCustomer = new Customer("c_jones", "pass456", "123 Main Street"); // Keep for Order creation

        // Create some dummy orders with MenuItems directly
        List<MenuItem> order1Items = new ArrayList<>();
        order1Items.add(new MenuItem("Burger", 10.00));
        order1Items.add(new MenuItem("Fries", 3.00));
        Order order1 = new Order("ORD-001", "Pending", "456 Oak Avenue", order1Items);

        List<MenuItem> order2Items = new ArrayList<>();
        order2Items.add(new MenuItem("Soda", 1.99));
        order2Items.add(new MenuItem("Pizza", 15.00));
        Order order2 = new Order("ORD-002", "Pending", "789 Pine Lane", order2Items);

        this.allOrders.add(order1);
        this.allOrders.add(order2);
        
        // Link customer to their dummy order (for testing delivery status updates)
        this.currentCustomer.setCurrentOrder(order1);

        // Initialize deliverer-specific GUI panels
        this.delivererBookOrderGUI = new DelivererBookOrderGUI(this, currentDeliverer, allOrders);


        // Show the initial GUI (Deliverer's view for demonstration)
        showDelivererBookOrderGUI();
        this.mainFrame.setVisible(true);
    }

    /**
     * Changes the main frame's content panel to the given JPanel.
     * @param panel The JPanel to display.
     * @param title The title to set for the main frame.
     */
    private void showPanel(JPanel panel, String title) {
        mainFrame.setTitle(title);
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(panel);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }
    

    
    /**
     * Displays the main deliverer booking GUI, refreshing its content.
     */
    public void showDelivererBookOrderGUI() {
        if (delivererBookOrderGUI == null) {
            delivererBookOrderGUI = new DelivererBookOrderGUI(this, currentDeliverer, allOrders);
        }
        delivererBookOrderGUI.updateOrderList();
        delivererBookOrderGUI.updateCurrentOrderDisplay();
        delivererBookOrderGUI.updateButtonStates();
        showPanel(delivererBookOrderGUI, "Deliverer: Available Orders");
    }

    /**
     * Displays the deliverer's order details GUI for a specific order.
     * This method now sets the 'currentlyViewedOrder' but does NOT assign it to the deliverer yet.
     * @param orderToShow The order to display details for.
     */
    public void showDelivererOrderDetailsGUI(Order orderToShow) {
        if (orderToShow != null) {
            this.currentlyViewedOrder = orderToShow; // Store the order being viewed
            DelivererOrderDetailsGUI detailsGUI = new DelivererOrderDetailsGUI(this, orderToShow);
            showPanel(detailsGUI, "Deliverer: Order Details");
        } else {
            JOptionPane.showMessageDialog(mainFrame, "No order selected to view details for.", "Error", JOptionPane.ERROR_MESSAGE);
            showDelivererBookOrderGUI();
        }
    }
    
    /**
     * Handles the logic for a deliverer selecting an order to view its details.
     * This method does NOT assign the order to the deliverer or change its status.
     * It simply navigates to the details screen.
     * @param orderId The ID of the order to view.
     */
    public void handleBookOrder(String orderId) {
        // If a deliverer already has an active order, they cannot book another.
        if (currentDeliverer.getCurrentOrder() != null) {
            JOptionPane.showMessageDialog(mainFrame, "You already have an active order. Please complete it first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Order orderToView = findOrderById(orderId);
        if (orderToView != null && orderToView.getStatus().equals("Pending")) {
            // Only show details, do NOT assign or change status yet.
            showDelivererOrderDetailsGUI(orderToView); 
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Order not found or is no longer available.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles the logic for a deliverer confirming the pickup of an order.
     * This is where the order is assigned to the deliverer and its status changes to "Picked Up".
     * This method no longer navigates away, allowing the details GUI to update its state.
     */
    public void handleOrderPickup() {
        if (this.currentlyViewedOrder != null && this.currentlyViewedOrder.getStatus().equals("Pending")) {
            // Assign the order to the current deliverer
            currentDeliverer.setCurrentOrder(this.currentlyViewedOrder);
            // Update the order status
            this.currentlyViewedOrder.setStatus("Picked Up");
            
            JOptionPane.showMessageDialog(mainFrame, "Order " + this.currentlyViewedOrder.getOrderID() + " has been picked up. Please proceed to delivery.", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            
            // Re-display the current details GUI to refresh its state
            showDelivererOrderDetailsGUI(currentDeliverer.getCurrentOrder()); // Pass the now-active order
        } else if (currentDeliverer.getCurrentOrder() != null && currentDeliverer.getCurrentOrder().getStatus().equals("Picked Up")) {
             // This case handles if they hit "Order Pickup" again after it's already picked up
             JOptionPane.showMessageDialog(mainFrame, "Order is already picked up.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(mainFrame, "No pending order to pick up or order already active.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles the logic for a deliverer completing a delivery.
     * Sets the order status to "Delivered" and updates records.
     */
    public void handleOrderDelivered() {
        Order deliveredOrder = currentDeliverer.getCurrentOrder();
        if (deliveredOrder != null && deliveredOrder.getStatus().equals("Picked Up")) {
            double earnings = 5.0; // Hardcoded earnings for simplicity
            deliveredOrder.setStatus("Delivered");
            currentDeliverer.addPastDelivery(deliveredOrder);
            currentDeliverer.setCurrentOrder(null); // Clear the active order for the deliverer
            
            showDelivererThankYouGUI(earnings); // Navigate to the thank you screen
        } else {
            JOptionPane.showMessageDialog(mainFrame, "No active order to deliver or order not yet picked up.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Displays the deliverer's past deliveries GUI.
     */
    public void showDelivererPastOrdersGUI() {
        DelivererPastOrdersGUI pastOrdersGUI = new DelivererPastOrdersGUI(this, currentDeliverer);
        pastOrdersGUI.refreshPastDeliveries(); // Ensure the list is updated
        showPanel(pastOrdersGUI, "Deliverer: Past Deliveries");
    }

    /**
     * Displays the thank you screen for a completed delivery.
     * @param earnings The earnings from the completed delivery.
     */
    private void showDelivererThankYouGUI(double earnings) {
        DelivererThankYouGUI thankYouGUI = new DelivererThankYouGUI(this, earnings);
        showPanel(thankYouGUI, "Deliverer: Delivery Complete");
    }

    /**
     * Returns to the main deliverer menu (available orders screen).
     */
    public void returnToDelivererMenu() {
        // If there was an order being viewed, clear it when returning to main menu
        this.currentlyViewedOrder = null; 
        showDelivererBookOrderGUI();
    }
    
    /**
     * Placeholder method for a login GUI, as requested by the user's original code structure.
     * For this example, it restarts the application flow.
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
    public Order findOrderById(String orderId) {
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
