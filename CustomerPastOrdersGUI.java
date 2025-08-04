package main;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
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
		btnBack.setBounds(401, 11, 89, 23);
		add(btnBack);
		
		JButton btnHamburgerMenu = new JButton("☰ Menu");
		btnHamburgerMenu.setBounds(10, 11, 89, 23);
		add(btnHamburgerMenu);
		
		JLabel lblPastOrders = new JLabel("Past Orders");
		lblPastOrders.setHorizontalAlignment(SwingConstants.CENTER);
		lblPastOrders.setFont(new Font("Arial", Font.BOLD, 24));
		lblPastOrders.setBounds(152, 15, 172, 37);
		add(lblPastOrders);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Ordered Items", "Total"},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Ordered Items", "Total"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(531);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBounds(41, 63, 414, 303);
		add(table);
	}

	public AppController getController() {
		return controller;
	}

	public void setController(AppController controller) {
		this.controller = controller;
	}
	
	
}
