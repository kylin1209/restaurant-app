package main;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomerMapGUI extends JPanel{
	private AppController controller;
	public CustomerMapGUI(AppController controller, Customer customer) {
	 	this.setController(controller);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(678, 12, 89, 23);
		btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controller.showCustomerOrderMenuGUI();
        	}
        });
		add(btnBack);
		
		JButton btnHamburgerMenu = new JButton("☰ Menu");
		btnHamburgerMenu.setBounds(10, 11, 89, 23);
		btnHamburgerMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controller.showCustomerOtherOptGUI();
        	}
        });
		add(btnHamburgerMenu);
		
		JLabel lblDeliveryStatus = new JLabel("Delivery Status");
		lblDeliveryStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeliveryStatus.setFont(new Font("Arial", Font.BOLD, 24));
		lblDeliveryStatus.setBounds(109, 12, 556, 37);
		add(lblDeliveryStatus);
		String lblTxt = "";
		if (customer.getCurrentOrder() == null) {
			lblTxt = "You have not ordered a new delivery yet!";
		} else {
			lblTxt = customer.getCurrentOrder().getStatus();
		}
		JLabel lblDeliveryStatusTxt = new JLabel(lblTxt);
		lblDeliveryStatusTxt.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeliveryStatusTxt.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDeliveryStatusTxt.setBounds(49, 146, 664, 37);
		add(lblDeliveryStatusTxt);
		
		JLabel lblEstimatedArrival = new JLabel("Estimated Arrival:");
		lblEstimatedArrival.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstimatedArrival.setFont(new Font("Arial", Font.PLAIN, 18));
		lblEstimatedArrival.setBounds(233, 303, 159, 37);
		add(lblEstimatedArrival);
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a");
		String arrivalTimeStr = "";
		if (customer.getCurrentOrder() == null) {
			arrivalTimeStr = "No order!";
		} else {
			switch (customer.getCurrentOrder().getStatus()) {
			case "Pending":
				arrivalTimeStr = currentDateTime.plusMinutes(20).format(formatter);
				break;
			case "Booked":
				arrivalTimeStr = currentDateTime.plusMinutes(10).format(formatter);
				break;
			case "Delivered":
				arrivalTimeStr = currentDateTime.format(formatter);
				break;
			}
		}
		
		JLabel lblEstimatedArrivalTxt = new JLabel(arrivalTimeStr);
		lblEstimatedArrivalTxt.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstimatedArrivalTxt.setFont(new Font("Arial", Font.PLAIN, 18));
		lblEstimatedArrivalTxt.setBounds(393, 303, 168, 37);
		add(lblEstimatedArrivalTxt);
	}
	public AppController getController() {
		return controller;
	}
	public void setController(AppController controller) {
		this.controller = controller;
	}
	private static final long serialVersionUID = 1L;
}
