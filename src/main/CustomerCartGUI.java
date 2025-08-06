package main;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomerCartGUI extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private AppController controller;
	private JTable table;
	public CustomerCartGUI(AppController controller, Customer customer) {
		this.setController(controller);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(690, 11, 89, 23);
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
		
		JLabel lblCart = new JLabel("Cart");
        lblCart.setHorizontalAlignment(SwingConstants.CENTER);
        lblCart.setFont(new Font("Arial", Font.BOLD, 24));
        lblCart.setBounds(124, 11, 546, 37);
        add(lblCart);
        
        table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(new String[] {"Name", "Price"}, 0);
        for (MenuItem item : customer.getCart()) {
        	tableModel.addRow(new Object[] {item.getName(), String.format("$%.2f", item.getPrice())});
        }
        table.setModel(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(545);
        table.getColumnModel().getColumn(1).setPreferredWidth(15);
        table.setBounds(37, 94, 707, 379);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(37, 94, 707, 379);
        add(scrollPane);
        
        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 24));
        lblTotal.setBounds(25, 484, 117, 37);
        add(lblTotal);
        
        JLabel lblTotalPrice = new JLabel("$" + Double.toString(customer.getTotalCartPrice()));
        lblTotalPrice.setHorizontalAlignment(SwingConstants.LEFT);
        lblTotalPrice.setFont(new Font("Arial", Font.BOLD, 24));
        lblTotalPrice.setBounds(152, 484, 349, 37);
        add(lblTotalPrice);
        
        JButton btnCheckout = new JButton("Checkout");
        btnCheckout.setBounds(655, 484, 89, 23);
        btnCheckout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controller.showCustomerPaymentGUI();
        	}
        });
        add(btnCheckout);
	}
	public AppController getController() {
		return controller;
	}
	public void setController(AppController controller) {
		this.controller = controller;
	}
}
