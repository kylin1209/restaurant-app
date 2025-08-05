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
    private LoginSelectionGUI loginSelectionGUI;
    private DelivererLoginGUI delivererLoginGUI;
    private DelivererSignUpGUI delivererSignUpGUI;

    // A temporary holder for the order currently being viewed in DelivererOrderDetailsGUI
    private Order currentlyViewedOrder;


    /**
     * Constructor for the AppController.
     * Initializes the main JFrame and a list of all orders.
     */
    public AppController() {
        this.allOrders = new ArrayList<>();
        this.mainFrame = new JFrame("Deliverer Application");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure application exits on close
        this.mainFrame.setSize(800, 600);
        this.mainFrame.setLocationRelativeTo(null);

        // Add a WindowListener to save accounts when the frame is closing
        this.mainFrame.addWindowListener(new java.awt.event.WindowAdapter() { // Using fully qualified name
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) { // Using fully qualified name
                AccountManager.getInstance().saveAccounts();
            }
        });
    }

    /**
     * Initializes the application with dummy data and shows the initial GUI.
     */
    public void initializeApp() {
        // Initialize AccountManager (this will also attempt to load existing accounts)
        AccountManager accountManager = AccountManager.getInstance();
        
        // Add dummy deliverer accounts ONLY if no accounts were loaded
        if (accountManager.getDelivererAccounts().isEmpty()) {
            accountManager.getDelivererAccounts().add(new Deliverer("d_smith", "pass123"));
            accountManager.getDelivererAccounts().add(new Deliverer("john_doe", "password"));
        }
        // Initialize dummy customer account (if needed for order creation later)
        if (accountManager.getCustomerAccounts().isEmpty()) {
            accountManager.getCustomerAccounts().add(new Customer("c_jones", "pass456", "123 Main Street"));
        }
        this.currentCustomer = accountManager.getCustomerAccounts().get(0); // Set a dummy customer for order creation

        // Create some dummy orders with MenuItems directly
        // These orders will be added to the allOrders list, which is not persisted
        // In a real app, orders would likely be managed by a separate persistence mechanism
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

        // Initialize all main GUI panels
        this.loginSelectionGUI = new LoginSelectionGUI(this);
        this.delivererLoginGUI = new DelivererLoginGUI(this);
        this.delivererSignUpGUI = new DelivererSignUpGUI(this);
        // delivererBookOrderGUI will be initialized upon successful deliverer login

        // Show the initial GUI (Login Selection)
        showLoginSelectionGUI();
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
    
    // ===================================================================================
    // Login/Signup GUI Navigation
    // ===================================================================================

    /**
     * Displays the initial login selection GUI.
     */
    public void showLoginSelectionGUI() {
        showPanel(loginSelectionGUI, "Welcome: Choose Role");
    }

    /**
     * Displays the deliverer login GUI.
     */
    public void showDelivererLoginGUI() {
        showPanel(delivererLoginGUI, "Deliverer Login");
    }

    /**
     * Displays the deliverer sign-up GUI.
     */
    public void showDelivererSignUpGUI() {
        showPanel(delivererSignUpGUI, "Deliverer Sign Up");
    }

    /**
     * Handles deliverer login attempt.
     * @param username The entered username.
     * @param password The entered password.
     */
    public void handleDelivererLogin(String username, String password) {
        AccountManager accountManager = AccountManager.getInstance();
        Deliverer foundDeliverer = null;
        for (Deliverer d : accountManager.getDelivererAccounts()) {
            if (d.getUsername().equals(username) && d.getPassword().equals(password)) {
                foundDeliverer = d;
                break;
            }
        }

        if (foundDeliverer != null) {
            this.currentDeliverer = foundDeliverer;
            JOptionPane.showMessageDialog(mainFrame, "Login successful! Welcome, " + username + ".", "Success", JOptionPane.INFORMATION_MESSAGE);
            // After successful login, navigate to the deliverer's main screen
            showDelivererBookOrderGUI();
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles deliverer sign-up attempt.
     * @param username The desired username.
     * @param password The desired password.
     * @param confirmPassword The confirmed password.
     */
    public void handleDelivererSignUp(String username, String password, String confirmPassword) {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "All fields are required.", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(mainFrame, "Passwords do not match.", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }

        AccountManager accountManager = AccountManager.getInstance();
        // Check if username already exists
        for (Deliverer d : accountManager.getDelivererAccounts()) {
            if (d.getUsername().equals(username)) {
                JOptionPane.showMessageDialog(mainFrame, "Username already exists. Please choose a different one.", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Create new deliverer account
        Deliverer newDeliverer = new Deliverer(username, password);
        accountManager.getDelivererAccounts().add(newDeliverer);
        // Save accounts immediately after a new one is created
        accountManager.saveAccounts(); 
        JOptionPane.showMessageDialog(mainFrame, "Account created successfully! Please log in.", "Sign Up Success", JOptionPane.INFORMATION_MESSAGE);
        showDelivererLoginGUI(); // Go back to login screen after successful signup
    }


    // ===================================================================================
    // Deliverer GUI Navigation and Actions
    // ===================================================================================
    
    /**
     * Displays the main deliverer booking GUI, refreshing its content.
     */
    public void showDelivererBookOrderGUI() {
        // Ensure delivererBookOrderGUI is initialized only after a deliverer is logged in
        if (delivererBookOrderGUI == null) {
            delivererBookOrderGUI = new DelivererBookOrderGUI(this, currentDeliverer, allOrders);
        } else {
            // Re-initialize for simplicity to ensure it uses the newly logged-in deliverer
            // and updated allOrders list if any changes occurred.
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
            // ONLY show details, do NOT assign or change status yet.
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
            
            // Save accounts after an order is picked up
            AccountManager.getInstance().saveAccounts();

            JOptionPane.showMessageDialog(mainFrame, "Order " + this.currentlyViewedOrder.getOrderID() + " has been picked up. Please proceed to delivery.", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Re-display the current details GUI to refresh its state
            // This will ensure the button text changes to "Order Delivered"
            showDelivererOrderDetailsGUI(currentDeliverer.getCurrentOrder()); 
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
            
            // Save accounts after an order is delivered
            AccountManager.getInstance().saveAccounts();

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
     * Handles logout, returning to the initial login selection screen.
     */
    public void showLoginGUI() {
        this.currentDeliverer = null; // Clear current deliverer on logout
        this.currentlyViewedOrder = null; // Clear any viewed order
        showLoginSelectionGUI(); // Go back to the role selection screen
    }

    // ===================================================================================
    // Utility Method
    // ===================================================================================

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