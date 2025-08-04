package src;
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
	        	}
	        });
	        btnHamburgerMenu.setBounds(10, 11, 89, 23);
	        add(btnHamburgerMenu);
	        
	        JButton btnBack = new JButton("Back");
	        btnBack.setBounds(401, 11, 89, 23);
	        add(btnBack);
	        
	        JLabel lblNewLabel = new JLabel("My Account");
	        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
	        lblNewLabel.setBounds(155, 46, 172, 37);
	        add(lblNewLabel);
	        
	        JLabel lblUsername = new JLabel("Username:");
	        lblUsername.setFont(new Font("Arial", Font.PLAIN, 18));
	        lblUsername.setBounds(91, 121, 94, 14);
	        add(lblUsername);
	        
	        JLabel lblUserUsername = new JLabel("<<username>>");
	        lblUserUsername.setFont(new Font("Arial", Font.PLAIN, 18));
	        lblUserUsername.setBounds(212, 123, 142, 14);
	        add(lblUserUsername);
	        
	        JLabel lblAddress = new JLabel("Address:");
	        lblAddress.setFont(new Font("Arial", Font.PLAIN, 18));
	        lblAddress.setBounds(91, 174, 94, 14);
	        add(lblAddress);
	        
	        JLabel lblUserAddress = new JLabel("<<address>>");
	        lblUserAddress.setFont(new Font("Arial", Font.PLAIN, 18));
	        lblUserAddress.setBounds(212, 176, 142, 14);
	        add(lblUserAddress);
	        
	        JLabel lblChangeAddress = new JLabel("Change Address:");
	        lblChangeAddress.setFont(new Font("Arial", Font.PLAIN, 18));
	        lblChangeAddress.setBounds(91, 230, 172, 31);
	        add(lblChangeAddress);
	        
	        txtNewAddr = new JTextField();
	        txtNewAddr.setBounds(89, 282, 312, 20);
	        add(txtNewAddr);
	        txtNewAddr.setColumns(10);
	        
	        JButton btnConfirmAddr = new JButton("Confirm");
	        btnConfirmAddr.setBounds(312, 334, 89, 23);
	        add(btnConfirmAddr);
	}
	public AppController getController() {
		return controller;
	}
	public void setController(AppController controller) {
		this.controller = controller;
	}
}
