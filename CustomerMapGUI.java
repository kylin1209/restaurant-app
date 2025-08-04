package main;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Canvas;
import java.awt.Color;

public class CustomerMapGUI extends JPanel{
	public CustomerMapGUI() {
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(401, 11, 89, 23);
		add(btnBack);
		
		JButton btnHamburgerMenu = new JButton("☰ Menu");
		btnHamburgerMenu.setBounds(10, 11, 89, 23);
		add(btnHamburgerMenu);
		
		Canvas canvas = new Canvas();
		canvas.setBackground(new Color(128, 128, 192));
		canvas.setBounds(90, 71, 315, 184);
		add(canvas);
		
		JLabel lblDeliveryStatus = new JLabel("Delivery Status");
		lblDeliveryStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeliveryStatus.setFont(new Font("Arial", Font.BOLD, 24));
		lblDeliveryStatus.setBounds(159, 15, 172, 37);
		add(lblDeliveryStatus);
		
		JLabel lblDeliveryStatusTxt = new JLabel("<<deliveryStatus>>");
		lblDeliveryStatusTxt.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeliveryStatusTxt.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDeliveryStatusTxt.setBounds(51, 273, 402, 37);
		add(lblDeliveryStatusTxt);
		
		JLabel lblEstimatedArrival = new JLabel("Estimated Arrival:");
		lblEstimatedArrival.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstimatedArrival.setFont(new Font("Arial", Font.PLAIN, 18));
		lblEstimatedArrival.setBounds(61, 321, 163, 37);
		add(lblEstimatedArrival);
		
		JLabel lblEstimatedArrivalTxt = new JLabel("<<estArrival>>");
		lblEstimatedArrivalTxt.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstimatedArrivalTxt.setFont(new Font("Arial", Font.PLAIN, 18));
		lblEstimatedArrivalTxt.setBounds(264, 321, 163, 37);
		add(lblEstimatedArrivalTxt);
	}
	private static final long serialVersionUID = 1L;
}
