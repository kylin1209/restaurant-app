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
    private Menu menu; // Added for customer order menu

    // GUI instances to maintain state and allow updates
    private DelivererBookOrderGUI delivererBookOrderGUI;


    /**
     * Constructor for the AppController.
     * Initializes the main JFrame and a list of all orders.
     */
    public AppController() {
        this.allOrders = new ArrayList<>();
        this.menu = new Menu(); // Initialize menu
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
        // Ensure Order constructor matches the one in Order.java (with List<MenuItem> items)
        List<MenuItem> dummyItems1 = new ArrayList<>();
        dummyItems1.add(new MenuItem("Burger", 10.00));
        dummyItems1.add(new MenuItem("Fries", 3.00));
        this.allOrders.add(new Order("101", "Pending", "123 Main St.", dummyItems1));

        List<MenuItem> dummyItems2 = new ArrayList<>();
        dummyItems2.add(new MenuItem("Pizza", 15.00));
        this.allOrders.add(new Order("102", "Pending", "456 Pine Blvd.", dummyItems2));

        List<MenuItem> dummyItems3 = new ArrayList<>();
        dummyItems3.add(new MenuItem("Salad", 8.00));
        this.allOrders.add(new Order("103", "Pending", "789 Elm Rd.", dummyItems3));
        
        // Simulate a past order for the deliverer
        List<MenuItem> pastItems = new ArrayList<>();
        pastItems.add(new MenuItem("Coffee", 2.50));
        Order pastDelivery = new Order("001", "Delivered", "999 Delivery St.", pastItems);
        this.currentDeliverer.getPastDeliveries().add(pastDelivery);

        // Simulate a past order for the customer
        List<MenuItem> customerPastItems = new ArrayList<>();
        customerPastItems.add(new MenuItem("Sandwich", 7.00));
        this.currentCustomer.getPastOrders().add(new Order("C-001", "Delivered", "456 Oak Ave", customerPastItems));

        // Initialize the DelivererBookOrderGUI here
        this.delivererBookOrderGUI = new DelivererBookOrderGUI(this, currentDeliverer, allOrders);

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
        // The bookingGUI is now an instance variable and its update methods are called
        if (delivererBookOrderGUI == null) {
            delivererBookOrderGUI = new DelivererBookOrderGUI(this, currentDeliverer, allOrders);
        }
        delivererBookOrderGUI.updateOrderList();
        delivererBookOrderGUI.updateCurrentOrderDisplay();
        delivererBookOrderGUI.updateButtonStates();
        switchPanel(delivererBookOrderGUI);
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
        pastOrdersGUI.refreshPastDeliveries(); // Ensure the list is updated
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
            orderToBook.setStatus("Picked Up"); // Status changes directly to "Picked Up" upon booking
            // No direct customer GUI update here yet, as per previous discussion.
            // This will be handled when connecting customer side.
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
            // The status is already "Picked Up" from booking, as per the latest request.
            // This method now serves as a confirmation of physical pickup.
            JOptionPane.showMessageDialog(mainFrame, "Order picked up. Please proceed to delivery.", "Success", JOptionPane.INFORMATION_MESSAGE);
            // No explicit status change here as it's already "Picked Up"
            // The DelivererOrderDetailsGUI's statusLabel is updated directly in its ActionListener.
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

    // ====================================================================================
    // Utility Method
    // ====================================================================================

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