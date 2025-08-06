package main;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class CustomerPaymentGUI extends JPanel{
	private AppController controller;

	public CustomerPaymentGUI(AppController controller, Customer customer) {
	 	this.setController(controller);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setLayout(null);
        
        JLabel lblTotalPrice = new JLabel("$" + Double.toString(customer.getTotalCartPrice()));
		lblTotalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalPrice.setFont(new Font("Arial", Font.BOLD, 14));
		lblTotalPrice.setBounds(542, 327, 126, 37);
		add(lblTotalPrice);
        
		JButton btnHamburgerMenu = new JButton("☰ Menu");
		btnHamburgerMenu.setBounds(10, 11, 89, 23);
		btnHamburgerMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controller.showCustomerOtherOptGUI();
        	}
        });
		add(btnHamburgerMenu);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(685, 11, 89, 23);
		btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controller.showCustomerOrderMenuGUI();
        	}
        });
		add(btnBack);
		
		JLabel lblCheckout = new JLabel("Checkout");
		lblCheckout.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckout.setFont(new Font("Arial", Font.BOLD, 24));
		lblCheckout.setBounds(109, 11, 557, 37);
		add(lblCheckout);
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		
		for (MenuItem item : customer.getCart()) {
		    String displayText = item.getName() + ", $" + item.getPrice();
		    listModel.addElement(displayText);
		}
		
		JList<String> list = new JList<String>();
		list.setModel(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(50, 63, 690, 146);
		add(scrollPane);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(548, 222, 89, 23);
		btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int itemIndex = list.getSelectedIndex();
        		customer.addToCart(customer.getCart().get(itemIndex));
        		listModel.clear();
        		// repopulate display
        		for (MenuItem item : customer.getCart()) {
        		    String displayText = item.getName() + ", $" + item.getPrice();
        		    listModel.addElement(displayText);
        		}
        		lblTotalPrice.setText("$" + Double.toString(customer.getTotalCartPrice()));
        	}
        });
		add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(652, 222, 89, 23);
		btnRemove.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int itemIndex = list.getSelectedIndex();
        		customer.removeFromCart(itemIndex);
        		listModel.clear();
        		// repopulate display
        		for (MenuItem item : customer.getCart()) {
        		    String displayText = item.getName() + ", $" + item.getPrice();
        		    listModel.addElement(displayText);
        		}
        		lblTotalPrice.setText("$" + Double.toString(customer.getTotalCartPrice()));
        	}
        });
		add(btnRemove);
		
		JLabel lblPaymentDetails = new JLabel("Payment Details");
		lblPaymentDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaymentDetails.setFont(new Font("Arial", Font.BOLD, 20));
		lblPaymentDetails.setBounds(157, 279, 172, 37);
		add(lblPaymentDetails);
		
		cardNumField = new JTextField();
		cardNumField.setBounds(200, 327, 233, 20);
		add(cardNumField);
		cardNumField.setColumns(10);
		
		JLabel lblCardNo = new JLabel("Card Number:");
		lblCardNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCardNo.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCardNo.setBounds(55, 318, 111, 37);
		add(lblCardNo);
		
		secField = new JTextField();
		secField.setColumns(10);
		secField.setBounds(200, 358, 50, 20);
		add(secField);
		
		zipField = new JTextField();
		zipField.setColumns(10);
		zipField.setBounds(358, 358, 75, 20);
		add(zipField);
		
		JLabel lblSecurityCode = new JLabel("Security Code:");
		lblSecurityCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecurityCode.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSecurityCode.setBounds(50, 349, 126, 37);
		add(lblSecurityCode);
		
		JLabel lblZipCode = new JLabel("Zip Code:");
		lblZipCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblZipCode.setFont(new Font("Arial", Font.PLAIN, 14));
		lblZipCode.setBounds(241, 349, 126, 37);
		add(lblZipCode);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(651, 335, 89, 23);
		btnConfirm.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String securityCode = secField.getText();
        		String cardNo = cardNumField.getText();
        		String zip = zipField.getText();
        		controller.handleCustomerOrderPayment(securityCode, cardNo, zip);
        	}
        });
		add(btnConfirm);
		
		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Arial", Font.BOLD, 14));
		lblTotal.setBounds(482, 327, 126, 37);
		add(lblTotal);
	
	}
	public AppController getController() {
		return controller;
	}
	public void setController(AppController controller) {
		this.controller = controller;
	}
	private static final long serialVersionUID = 1L;
	private JTextField cardNumField;
	private JTextField secField;
	private JTextField zipField;
	
	
}
