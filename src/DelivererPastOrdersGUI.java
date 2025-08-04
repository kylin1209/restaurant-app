package src;


import javax.swing.*;

import src.AppController;
import src.Deliverer;
import src.Order;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The GUI for a deliverer to view their past deliveries.
 */
public class DelivererPastOrdersGUI extends JPanel {
    private AppController controller;
    private DefaultListModel<String> pastDeliveriesListModel;
    private JList<String> pastDeliveriesJList;

    public DelivererPastOrdersGUI(AppController controller, Deliverer deliverer) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create a new panel for the top section
        JPanel topPanel = new JPanel(new BorderLayout(10, 0));
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // Panel for the Back button on the left
        JPanel leftTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addActionListener(e -> controller.returnToDelivererMenu());
        leftTopPanel.add(backButton);

        // Title Label
        JLabel titleLabel = new JLabel("Past Deliveries", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        topPanel.add(leftTopPanel, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // Create the list of past deliveries
        pastDeliveriesListModel = new DefaultListModel<>();
        if (deliverer != null && deliverer.getPastDeliveries() != null) {
            for (Order order : deliverer.getPastDeliveries()) {
                pastDeliveriesListModel.addElement("Delivered Order ID: " + order.getOrderID() + " to " + order.getDestinationAddress());
            }
        }
        pastDeliveriesJList = new JList<>(pastDeliveriesListModel);
        pastDeliveriesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pastDeliveriesJList.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(pastDeliveriesJList);
        add(scrollPane, BorderLayout.CENTER);

        // A simple message at the bottom
        JLabel messageLabel = new JLabel("Completed deliveries are listed above.", SwingConstants.CENTER);
        add(messageLabel, BorderLayout.SOUTH);
    }
}

