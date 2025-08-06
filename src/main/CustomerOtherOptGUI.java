package main;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class CustomerOtherOptGUI extends JPanel{
	private AppController controller;

	public CustomerOtherOptGUI(AppController controller, Customer customer) {
	 	this.setController(controller);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setLayout(null);
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblOptions.setFont(new Font("Arial", Font.BOLD, 24));
		lblOptions.setBounds(304, 11, 172, 37);
		add(lblOptions);
		
		JButton btnCart = new JButton("Cart");
		btnCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showCustomerCartGUI();
			}
		});
		btnCart.setBounds(680, 11, 89, 23);
		add(btnCart);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 89, 23);
		btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controller.showCustomerOrderMenuGUI();
        	}
        });
		add(btnBack);
		
		JButton btnCheckDelivery = new JButton("Check Delivery");
		btnCheckDelivery.setFont(new Font("Arial", Font.BOLD, 20));
		btnCheckDelivery.setBounds(171, 59, 442, 53);
		btnCheckDelivery.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controller.showCustomerMapGUI();
        	}
        });
		add(btnCheckDelivery);
		
		JButton btnPastOrders = new JButton("Past Orders");
		btnPastOrders.setFont(new Font("Arial", Font.BOLD, 20));
		btnPastOrders.setBounds(171, 139, 442, 53);
		btnPastOrders.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controller.showCustomerPastOrdersGUI();
        	}
        });
		add(btnPastOrders);
		
		JButton btnMyAccount = new JButton("My Account");
		btnMyAccount.setFont(new Font("Arial", Font.BOLD, 20));
		btnMyAccount.setBounds(171, 222, 442, 53);
		btnMyAccount.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controller.showCustomerAccountGUI();
        	}
        });
		add(btnMyAccount);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setFont(new Font("Arial", Font.BOLD, 20));
		btnLogOut.setBounds(171, 301, 442, 53);
		btnLogOut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controller.showLoginGUI();
        	}
        });
		add(btnLogOut);
	}
	public AppController getController() {
		return controller;
	}
	public void setController(AppController controller) {
		this.controller = controller;
	}
	private static final long serialVersionUID = 1L;
	
	
}
