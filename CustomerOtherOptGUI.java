package main;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class CustomerOtherOptGUI extends JPanel{
	public CustomerOtherOptGUI() {
		setLayout(null);
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblOptions.setFont(new Font("Arial", Font.BOLD, 24));
		lblOptions.setBounds(162, 15, 172, 37);
		add(lblOptions);
		
		JButton btnCart = new JButton("Cart");
		btnCart.setBounds(401, 11, 89, 23);
		add(btnCart);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 89, 23);
		add(btnBack);
		
		JButton btnCheckDelivery = new JButton("Check Delivery");
		btnCheckDelivery.setFont(new Font("Arial", Font.BOLD, 20));
		btnCheckDelivery.setBounds(29, 63, 442, 53);
		add(btnCheckDelivery);
		
		JButton btnPastOrders = new JButton("Past Orders");
		btnPastOrders.setFont(new Font("Arial", Font.BOLD, 20));
		btnPastOrders.setBounds(29, 143, 442, 53);
		add(btnPastOrders);
		
		JButton btnMyAccount = new JButton("My Account");
		btnMyAccount.setFont(new Font("Arial", Font.BOLD, 20));
		btnMyAccount.setBounds(29, 226, 442, 53);
		add(btnMyAccount);
	}
	private static final long serialVersionUID = 1L;
	
	
}
