package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * The GUI for a deliverer to view their past deliveries.
 */
public class DelivererPastOrdersGUI extends JPanel {
    private static final long serialVersionUID = 1L;
    private AppController controller;
    private Deliverer deliverer;
    private JButton menuButton;
    private JTable table;
    private DefaultTableModel tableModel;

    public DelivererPastOrdersGUI(AppController controller, Deliverer deliverer) {
        this.setController(controller);
        this.deliverer = deliverer;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create a new panel for the top section
        JPanel topPanel = new JPanel(new BorderLayout(10, 0));
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // Left side for hamburger menu button
        JPanel leftTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        menuButton = new JButton("☰ Menu");
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
        JMenuItem availableOrdersItem = new JMenuItem("Available Orders");
        availableOrdersItem.addActionListener(e -> controller.returnToDelivererMenu());
        JMenuItem logoutItem = new JMenuItem("Log Out");
        logoutItem.addActionListener(e -> controller.showLoginGUI());
        hamburgerMenu.add(availableOrdersItem);
        hamburgerMenu.add(logoutItem);
        menuButton.addActionListener(e -> hamburgerMenu.show(menuButton, 0, menuButton.getHeight()));

        // Title Label
        JLabel titleLabel = new JLabel("Past Deliveries", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Create the table model
        tableModel = new DefaultTableModel(
            new String[] {"Order ID", "Destination", "Earnings"}, 0);
        
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        
        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(400);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // A simple message at the bottom
        JLabel messageLabel = new JLabel("Completed deliveries are listed above.", SwingConstants.CENTER);
        add(messageLabel, BorderLayout.SOUTH);

        refreshPastDeliveries(); // Initial refresh
    }

    /**
     * Refreshes the list of past deliveries displayed in the GUI.
     */
    public void refreshPastDeliveries() {
        tableModel.setRowCount(0); // Clear existing rows
        
        if (deliverer != null && deliverer.getPastDeliveries() != null) {
            for (Order order : deliverer.getPastDeliveries()) {
                tableModel.addRow(new Object[] {
                    order.getOrderID(),
                    order.getDestinationAddress(),
                    String.format("$%.2f", order.getTotalPrice())
                });
            }
        }
    }

    public AppController getController() {
        return controller;
    }

    public void setController(AppController controller) {
        this.controller = controller;
    }
}