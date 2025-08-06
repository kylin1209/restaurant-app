package main;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerLoginGUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AppController controller;
	private JTextField usernameField;
	private JPasswordField passwordField;
	public CustomerLoginGUI(AppController controller) {
	 	this.setController(controller);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setLayout(null);
        
        JLabel lblCustomerLogin = new JLabel("Customer Login");
        lblCustomerLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblCustomerLogin.setFont(new Font("Arial", Font.BOLD, 24));
        lblCustomerLogin.setBounds(262, 68, 243, 37);
        add(lblCustomerLogin);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                controller.handleCustomerLogin(username, password);
        	}
        });
        btnLogin.setFont(new Font("Arial", Font.BOLD, 20));
        btnLogin.setBounds(159, 222, 442, 53);
        add(btnLogin);
        
        JButton btnSignUp = new JButton("Don't Have an account? Sign Up");
        btnSignUp.setFont(new Font("Arial", Font.BOLD, 20));
        btnSignUp.setBounds(159, 286, 442, 53);
        btnSignUp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controller.showCustomerSignUpGUI();
        	}
        });
        add(btnSignUp);
        
        JButton btnRoleSelection = new JButton("Back To Role Selection");
        btnRoleSelection.setFont(new Font("Arial", Font.BOLD, 20));
        btnRoleSelection.setBounds(159, 397, 442, 53);
        btnRoleSelection.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controller.showLoginSelectionGUI();
        	}
        });
        add(btnRoleSelection);
        
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 18));
        lblUsername.setBounds(103, 116, 172, 37);
        add(lblUsername);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 18));
        lblPassword.setBounds(103, 164, 172, 37);
        add(lblPassword);
        
        usernameField = new JTextField();
        usernameField.setBounds(243, 126, 358, 20);
        add(usernameField);
        usernameField.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(243, 174, 358, 20);
        add(passwordField);
        
	}
	public AppController getController() {
		return controller;
	}
	public void setController(AppController controller) {
		this.controller = controller;
	}
}
