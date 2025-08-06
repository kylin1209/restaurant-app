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

public class CustomerPastOrdersGUI extends JPanel{
	private static final long serialVersionUID = 1L;
	private AppController controller;
	private JTable table;
	
	public CustomerPastOrdersGUI(AppController controller, Customer customer) {
		this.setController(controller);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(689, 11, 89, 23);
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
		
		JLabel lblPastOrders = new JLabel("Past Orders");
		lblPastOrders.setHorizontalAlignment(SwingConstants.CENTER);
		lblPastOrders.setFont(new Font("Arial", Font.BOLD, 24));
		lblPastOrders.setBounds(307, 15, 172, 37);
		add(lblPastOrders);
		
		table = new JTable();
		DefaultTableModel tableModel = new DefaultTableModel(new String[] {"Order ID", "Total"}, 0);
        for (Order order : customer.getPastOrders()) {
        	tableModel.addRow(new Object[] {"Order ID: " + order.getOrderID(), "$" + String.format("$%.2f", order.getTotalPrice())});
        }
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(531);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(41, 63, 693, 418);
		add(scrollPane);
	}

	public AppController getController() {
		return controller;
	}

	public void setController(AppController controller) {
		this.controller = controller;
	}
	
	
}
