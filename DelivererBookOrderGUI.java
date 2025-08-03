import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The GUI for a deliverer to view and book orders.
 */
public class DelivererBookOrderGUI extends JPanel {
    private AppController controller;
    private DefaultListModel<String> orderListModel;
    private JList<String> orderJList;

    public DelivererBookOrderGUI(AppController controller, List<Order> availableOrders) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create a new panel for the top section 
        JPanel topPanel = new JPanel(new BorderLayout(10, 0));
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // Panel for the Back and Cancel buttons on the left
        JPanel leftTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addActionListener(e -> controller.showPreviousScreen()); 
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
        cancelButton.addActionListener(e -> controller.showLoginGUI()); 
        leftTopPanel.add(backButton);
        leftTopPanel.add(cancelButton);
        topPanel.add(leftTopPanel, BorderLayout.WEST);

        // Create the title label in the center
        JLabel titleLabel = new JLabel("Available Orders", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // Panel for the Hamburger Menu button on the right
        JPanel rightTopPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        JButton menuButton = new JButton("☰ Menu");
        menuButton.setFont(new Font("Arial", Font.BOLD, 16));
        
        // Create the actual popup menu
        JPopupMenu hamburgerMenu = new JPopupMenu();
        JMenuItem pastDeliveriesItem = new JMenuItem("Past Deliveries");
        pastDeliveriesItem.addActionListener(e -> controller.showPastDeliveriesGUI());
        JMenuItem logoutItem = new JMenuItem("Log Out");
        logoutItem.addActionListener(e -> controller.showLoginGUI());
        hamburgerMenu.add(pastDeliveriesItem);
        hamburgerMenu.add(logoutItem);

        // Set the action for the menu button to show the popup menu
        menuButton.addActionListener(e -> hamburgerMenu.show(menuButton, 0, menuButton.getHeight()));

        rightTopPanel.add(menuButton);
        topPanel.add(rightTopPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Create the list of orders
        orderListModel = new DefaultListModel<>();
        if (availableOrders != null) {
            for (Order order : availableOrders) {
                if (order.getStatus().equals("Pending")) {
                    orderListModel.addElement("Order ID: " + order.getOrderID());
                }
            }
        }
        orderJList = new JList<>(orderListModel);
        orderJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderJList.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(orderJList);
        add(scrollPane, BorderLayout.CENTER);

        // Create the 'Book' button
        JButton bookButton = new JButton("Book Selected Order");
        bookButton.setFont(new Font("Arial", Font.BOLD, 18));
        
        bookButton.addActionListener(e -> {
            int selectedIndex = orderJList.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedOrderText = orderJList.getSelectedValue();
                String orderId = selectedOrderText.split(": ")[1];
                // Call the controller's method to handle the booking logic
                controller.handleBookOrder(orderId);
            } else {
                JOptionPane.showMessageDialog(this, "Please select an order to book.", "No Order Selected", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Add the button to a panel for better layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(bookButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}