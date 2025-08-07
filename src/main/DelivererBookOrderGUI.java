package main;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The GUI for a deliverer to view and book orders.
 */
public class DelivererBookOrderGUI extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AppController controller;
    private Deliverer deliverer;
    private List<Order> allOrders;

    private DefaultListModel<String> pendingOrderListModel;
    private JList<String> pendingOrderJList;
    private JButton bookButton;
    // private JButton refreshButton;
    private JButton deliverButton;
    private JLabel currentOrderLabel;

    /**
     * Constructor for DelivererBookOrderGUI.
     * @param controller The main application controller.
     * @param deliverer The current deliverer.
     * @param allOrders A list of all orders in the system.
     */
    public DelivererBookOrderGUI(AppController controller, Deliverer deliverer, List<Order> allOrders) {
        this.setController(controller);
        this.deliverer = deliverer;
        this.allOrders = allOrders;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top Panel for the title and menu buttons
        JPanel topPanel = new JPanel(new BorderLayout(10, 0));
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // Left side for hamburger menu button
        JPanel leftTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JButton menuButton = new JButton("☰ Menu");
        menuButton.setFont(new Font("Arial", Font.PLAIN, 14));
        leftTopPanel.add(menuButton);
        
        // Right side for back button 
        JPanel rightTopPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addActionListener(e -> controller.returnToDelivererMenu()); 
        rightTopPanel.add(backButton);

        // Add to top panel
        topPanel.add(leftTopPanel, BorderLayout.WEST);
        topPanel.add(rightTopPanel, BorderLayout.EAST);
        
        // Hamburger menu popup
        JPopupMenu hamburgerMenu = new JPopupMenu();
        JMenuItem pastDeliveriesItem = new JMenuItem("Past Deliveries");
        // This line calls showPastDeliveriesGUI() on the controller instance
        pastDeliveriesItem.addActionListener(e -> controller.showDelivererPastOrdersGUI()); // Corrected to call showDelivererPastOrdersGUI
        JMenuItem logoutItem = new JMenuItem("Log Out");
        logoutItem.addActionListener(e -> controller.showLoginGUI());
        hamburgerMenu.add(pastDeliveriesItem);
        hamburgerMenu.add(logoutItem);
        menuButton.addActionListener(e -> hamburgerMenu.show(menuButton, 0, menuButton.getHeight()));
        
        // Title Label
        JLabel titleLabel = new JLabel("Deliverer Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Center Panel for current and pending orders
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Display for the current active order
        currentOrderLabel = new JLabel("", SwingConstants.CENTER);
        currentOrderLabel.setFont(new Font("Arial", Font.BOLD, 18));
        currentOrderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(currentOrderLabel);

        // Button to handle the current order
        deliverButton = new JButton("Manage Current Order");
        deliverButton.setFont(new Font("Arial", Font.BOLD, 16));
        deliverButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deliverButton.addActionListener(e -> {
            if (deliverer.getCurrentOrder() != null) {
                controller.showDelivererOrderDetailsGUI(deliverer.getCurrentOrder());
            } else {
                JOptionPane.showMessageDialog(this, "No active order to manage.", "No Active Order", JOptionPane.WARNING_MESSAGE);
            }
        });
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(deliverButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Label for pending orders
        JLabel pendingLabel = new JLabel("Available Orders:", SwingConstants.CENTER);
        pendingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        pendingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(pendingLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // JList for pending orders
        pendingOrderListModel = new DefaultListModel<>();
        pendingOrderJList = new JList<>(pendingOrderListModel);
        pendingOrderJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pendingOrderJList.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(pendingOrderJList);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(scrollPane);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // "Book" button
        bookButton = new JButton("Book Selected Order");
        bookButton.setFont(new Font("Arial", Font.BOLD, 16));
        bookButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookButton.addActionListener(e -> {
            int selectedIndex = pendingOrderJList.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedOrderText = pendingOrderJList.getSelectedValue();
                String orderId = selectedOrderText.split(" \\| ")[0].split(": ")[1];
                controller.handleBookOrder(orderId); // This calls the AppController's method
            } else {
                JOptionPane.showMessageDialog(this, "Please select an order to book.", "No Order Selected", JOptionPane.WARNING_MESSAGE);
            }
        });
        centerPanel.add(bookButton);
        
//        refreshButton = new JButton("Book Selected Order");
//        refreshButton.setFont(new Font("Arial", Font.BOLD, 16));
//        refreshButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        refreshButton.addActionListener(e -> {
//        	pendingOrderListModel.clear();
//            for (Order order : controller.getAllOrders()) {
//                if (order.getStatus().equals("Pending")) {
//                    pendingOrderListModel.addElement("Order ID: " + order.getOrderID() + " | To: " + order.getDestinationAddress());
//                }
//            }
//        });
//        centerPanel.add(refreshButton);
        
        add(centerPanel, BorderLayout.CENTER);

        updateGUI();
    }

    /**
     * Updates all dynamic components of the GUI.
     */
    public void updateGUI() {
        updateCurrentOrderDisplay();
        updateOrderList();
        updateButtonStates();
    }

    /**
     * Populates the list of available pending orders.
     */
    public void updateOrderList() { // Changed to public
        pendingOrderListModel.clear();
        for (Order order : allOrders) {
            if (order.getStatus().equals("Pending")) {
                pendingOrderListModel.addElement("Order ID: " + order.getOrderID() + " | To: " + order.getDestinationAddress());
            }
        }
    }

    /**
     * Updates the display for the currently active order.
     */
    public void updateCurrentOrderDisplay() { // Changed to public
        if (deliverer.getCurrentOrder() != null) {
            Order currentOrder = deliverer.getCurrentOrder();
            currentOrderLabel.setText("<html><div style='text-align: center;'>Active Order ID: " + currentOrder.getOrderID() + "<br>Address: " + currentOrder.getDestinationAddress() + "</div></html>");
            currentOrderLabel.setForeground(Color.BLUE);
            deliverButton.setEnabled(true);
        } else {
            currentOrderLabel.setText("No active order.");
            currentOrderLabel.setForeground(Color.BLACK);
            deliverButton.setEnabled(false);
        }
    }

    /**
     * Enables/disables buttons based on the presence of an active order.
     */
    public void updateButtonStates() { // Changed to public
        boolean hasActiveOrder = deliverer.getCurrentOrder() != null;
        bookButton.setEnabled(!hasActiveOrder);
        pendingOrderJList.setEnabled(!hasActiveOrder);
        deliverButton.setEnabled(hasActiveOrder); // Ensure deliverButton is enabled/disabled correctly
    }

	public AppController getController() {
		return controller;
	}

	public void setController(AppController controller) {
		this.controller = controller;
	}
}
