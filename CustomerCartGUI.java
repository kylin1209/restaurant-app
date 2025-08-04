package main;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
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
		btnBack.setBounds(401, 11, 89, 23);
		add(btnBack);
		
		JButton btnHamburgerMenu = new JButton("☰ Menu");
		btnHamburgerMenu.setBounds(10, 11, 89, 23);
		add(btnHamburgerMenu);
		
		JLabel lblCart = new JLabel("Cart");
        lblCart.setHorizontalAlignment(SwingConstants.CENTER);
        lblCart.setFont(new Font("Arial", Font.BOLD, 24));
        lblCart.setBounds(162, 46, 172, 37);
        add(lblCart);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{"Name", "Price", "Servings"},
        		{null, null, null},
        		{null, null, null},
        		{null, null, null},
        		{null, null, null},
        		{null, null, null},
        		{null, null, null},
        		{null, null, null},
        		{null, null, null},
        		{null, null, null},
        		{null, null, null},
        		{null, null, null},
        		{null, null, null},
        		{null, null, null},
        		{null, null, null},
        	},
        	new String[] {
        		"Name", "Price", "Servings"
        	}
        ));
        table.getColumnModel().getColumn(2).setPreferredWidth(489);
        table.setColumnSelectionAllowed(true);
        table.setCellSelectionEnabled(true);
        table.setBounds(46, 94, 414, 240);
        add(table);
        
        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 24));
        lblTotal.setBounds(10, 345, 114, 37);
        add(lblTotal);
        
        JLabel lblTotalPrice = new JLabel("<<totalPrice>>");
        lblTotalPrice.setHorizontalAlignment(SwingConstants.LEFT);
        lblTotalPrice.setFont(new Font("Arial", Font.BOLD, 24));
        lblTotalPrice.setBounds(114, 345, 165, 37);
        add(lblTotalPrice);
        
        JButton btnCheckout = new JButton("Checkout");
        btnCheckout.setBounds(401, 356, 89, 23);
        add(btnCheckout);
	}
	public AppController getController() {
		return controller;
	}
	public void setController(AppController controller) {
		this.controller = controller;
	}
}
