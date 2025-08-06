package main;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class CustomerAccountGUI extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private AppController controller;
	private JTextField txtNewAddr;
	public CustomerAccountGUI(AppController controller, Customer customer) {
		 	this.setController(controller);
	        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	        setLayout(null);
	        
	        JButton btnHamburgerMenu = new JButton("☰ Menu");
	        btnHamburgerMenu.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		controller.showCustomerOtherOptGUI();
	        	}
	        });
	        btnHamburgerMenu.setBounds(10, 11, 89, 23);
	        add(btnHamburgerMenu);
	        
	        JButton btnBack = new JButton("Back");
	        btnBack.setBounds(676, 11, 89, 23);
	        btnBack.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		controller.showCustomerOrderMenuGUI();
	        	}
	        });
	        add(btnBack);
	        
	        JLabel lblNewLabel = new JLabel("My Account");
	        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
	        lblNewLabel.setBounds(156, 47, 460, 37);
	        add(lblNewLabel);
	        
	        JLabel lblUsername = new JLabel("Username:");
	        lblUsername.setFont(new Font("Arial", Font.PLAIN, 18));
	        lblUsername.setBounds(100, 128, 516, 14);
	        add(lblUsername);
	        
	        JLabel lblUserUsername = new JLabel("<<username>>");
	        lblUserUsername.setFont(new Font("Arial", Font.PLAIN, 18));
	        lblUserUsername.setBounds(213, 124, 403, 23);
	        lblUserUsername.setText(customer.getUsername());
	        add(lblUserUsername);
	        
	        JLabel lblAddress = new JLabel("Address:");
	        lblAddress.setFont(new Font("Arial", Font.PLAIN, 18));
	        lblAddress.setBounds(100, 181, 516, 14);
	        add(lblAddress);
	        
	        JLabel lblUserAddress = new JLabel("<<address>>");
	        lblUserAddress.setFont(new Font("Arial", Font.PLAIN, 18));
	        lblUserAddress.setBounds(213, 177, 403, 23);
	        lblUserAddress.setText(customer.getAddress());
	        add(lblUserAddress);
	        
	        JLabel lblChangeAddress = new JLabel("Change Address:");
	        lblChangeAddress.setFont(new Font("Arial", Font.PLAIN, 18));
	        lblChangeAddress.setBounds(102, 230, 580, 31);
	        add(lblChangeAddress);
	        
	        txtNewAddr = new JTextField();
	        txtNewAddr.setBounds(100, 282, 582, 20);
	        add(txtNewAddr);
	        txtNewAddr.setColumns(10);
	        
	        JButton btnConfirmAddr = new JButton("Confirm");
	        btnConfirmAddr.setBounds(571, 334, 111, 23);
	        btnConfirmAddr.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		if (!txtNewAddr.getText().isEmpty()) {
	        			customer.setAddress(txtNewAddr.getText());
	        			lblUserAddress.setText(txtNewAddr.getText());
	        		} else {
	        			controller.showChangeAddrErr();
	        		}
	        	}
	        });
	        add(btnConfirmAddr);
	}
	public AppController getController() {
		return controller;
	}
	public void setController(AppController controller) {
		this.controller = controller;
	}
}
