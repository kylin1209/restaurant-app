package src;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;

public class CustomerOrderMenuGUI extends JPanel{
	private static final long serialVersionUID = 1L;
	public CustomerOrderMenuGUI() {
		setLayout(null);
		
		JButton btnHamburgerMenu = new JButton("☰ Menu");
		btnHamburgerMenu.setBounds(10, 11, 89, 23);
		add(btnHamburgerMenu);
		
		JButton btnCart = new JButton("Cart");
		btnCart.setBounds(401, 11, 89, 23);
		add(btnCart);
		
		JLabel lblOrderMenu = new JLabel("Order Menu");
		lblOrderMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderMenu.setFont(new Font("Arial", Font.BOLD, 24));
		lblOrderMenu.setBounds(156, 15, 172, 37);
		add(lblOrderMenu);
		
		JTextPane txtPaneSearch = new JTextPane();
		txtPaneSearch.setBounds(57, 63, 243, 23);
		add(txtPaneSearch);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(335, 63, 89, 23);
		add(btnSearch);
		
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
		list.setBounds(141, 118, 318, 183);
		add(list);
		
		JLabel lblFilters = new JLabel("Filters");
		lblFilters.setHorizontalAlignment(SwingConstants.CENTER);
		lblFilters.setFont(new Font("Arial", Font.PLAIN, 18));
		lblFilters.setBounds(-16, 106, 172, 37);
		add(lblFilters);
		
		JButton btnAppetizers = new JButton("Appetizers");
		btnAppetizers.setBounds(26, 154, 89, 23);
		add(btnAppetizers);
		
		JButton btnMainDish = new JButton("Main Dish");
		btnMainDish.setBounds(26, 203, 89, 23);
		add(btnMainDish);
		
		JButton btnSides = new JButton("Sides");
		btnSides.setBounds(26, 247, 89, 23);
		add(btnSides);
		
		JButton btnDrinks = new JButton("Drinks");
		btnDrinks.setBounds(26, 295, 89, 23);
		add(btnDrinks);
		
		JLabel lblTotalPrice = new JLabel("<<totalPrice>>");
		lblTotalPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalPrice.setFont(new Font("Arial", Font.BOLD, 24));
		lblTotalPrice.setBounds(114, 352, 165, 37);
		add(lblTotalPrice);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Arial", Font.BOLD, 24));
		lblTotal.setBounds(10, 352, 114, 37);
		add(lblTotal);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setBounds(401, 363, 89, 23);
		add(btnCheckout);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(370, 312, 89, 23);
		add(btnAdd);
	}
}
