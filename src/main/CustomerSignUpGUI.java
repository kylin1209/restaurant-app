package main;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class CustomerSignUpGUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AppController controller;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JTextField addressField;

	public CustomerSignUpGUI(AppController controller) {
	 	this.setController(controller);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setLayout(null);
        
        JLabel lblCustomerSignUp = new JLabel("Customer Sign Up");
        lblCustomerSignUp.setHorizontalAlignment(SwingConstants.CENTER);
        lblCustomerSignUp.setFont(new Font("Arial", Font.BOLD, 24));
        lblCustomerSignUp.setBounds(274, 67, 243, 37);
        add(lblCustomerSignUp);
        
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 18));
        lblUsername.setBounds(115, 115, 172, 37);
        add(lblUsername);
        
        usernameField = new JTextField();
        usernameField.setColumns(10);
        usernameField.setBounds(255, 125, 358, 20);
        add(usernameField);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 18));
        lblPassword.setBounds(115, 163, 172, 37);
        add(lblPassword);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(255, 173, 358, 20);
        add(passwordField);
        
        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.setFont(new Font("Arial", Font.BOLD, 20));
        btnSignUp.setBounds(171, 332, 442, 53);
        btnSignUp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String user = usernameField.getText();
        		String pass = new String(passwordField.getPassword());
        		String confirmPass = new String(confirmPasswordField.getPassword());
        		String addr = addressField.getText();
        		controller.handleCustomerSignUp(user, pass, confirmPass, addr);
        	}
        });
        add(btnSignUp);
        
        JButton btnBackToLogin = new JButton("Back To Login");
        btnBackToLogin.setFont(new Font("Arial", Font.BOLD, 20));
        btnBackToLogin.setBounds(171, 396, 442, 53);
        btnSignUp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controller.showLoginSelectionGUI();
        	}
        });
        add(btnBackToLogin);
        
        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 18));
        lblConfirmPassword.setBounds(85, 211, 172, 37);
        add(lblConfirmPassword);
        
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(255, 222, 358, 20);
        add(confirmPasswordField);
        
        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddress.setFont(new Font("Arial", Font.PLAIN, 18));
        lblAddress.setBounds(115, 259, 172, 37);
        add(lblAddress);
        
        addressField = new JTextField();
        addressField.setColumns(10);
        addressField.setBounds(255, 269, 358, 20);
        add(addressField);
        
        
	}

	public AppController getController() {
		return controller;
	}

	public void setController(AppController controller) {
		this.controller = controller;
	}

}
