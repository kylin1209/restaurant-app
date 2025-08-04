package src;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;

public class CustomerPaymentGUI extends JPanel{
	public CustomerPaymentGUI() {
		setLayout(null);
		
		JButton btnHamburgerMenu = new JButton("☰ Menu");
		btnHamburgerMenu.setBounds(10, 11, 89, 23);
		add(btnHamburgerMenu);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(401, 11, 89, 23);
		add(btnBack);
		
		JLabel lblCheckout = new JLabel("Checkout");
		lblCheckout.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckout.setFont(new Font("Arial", Font.BOLD, 24));
		lblCheckout.setBounds(154, 15, 172, 37);
		add(lblCheckout);
		
		JList<String> list = new JList<String>();
		list.setModel(new DefaultListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"foodName, price, amountAdded"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(66, 63, 364, 146);
		add(list);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(237, 220, 89, 23);
		add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(341, 220, 89, 23);
		add(btnRemove);
		
		JLabel lblPaymentDetails = new JLabel("Payment Details");
		lblPaymentDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaymentDetails.setFont(new Font("Arial", Font.BOLD, 20));
		lblPaymentDetails.setBounds(154, 254, 172, 37);
		add(lblPaymentDetails);
		
		textField = new JTextField();
		textField.setBounds(197, 302, 233, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblCardNo = new JLabel("Card Number:");
		lblCardNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCardNo.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCardNo.setBounds(52, 293, 111, 37);
		add(lblCardNo);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(197, 333, 50, 20);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(355, 333, 75, 20);
		add(textField_2);
		
		JLabel lblSecurityCode = new JLabel("Security Code:");
		lblSecurityCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecurityCode.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSecurityCode.setBounds(47, 324, 126, 37);
		add(lblSecurityCode);
		
		JLabel lblZipCode = new JLabel("Zip Code:");
		lblZipCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblZipCode.setFont(new Font("Arial", Font.PLAIN, 14));
		lblZipCode.setBounds(238, 324, 126, 37);
		add(lblZipCode);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(401, 366, 89, 23);
		add(btnConfirm);
		
		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Arial", Font.BOLD, 14));
		lblTotal.setBounds(238, 358, 126, 37);
		add(lblTotal);
		
		JLabel lblTotal_1 = new JLabel("<<total>>");
		lblTotal_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblTotal_1.setBounds(292, 358, 126, 37);
		add(lblTotal_1);
	}
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	
}
