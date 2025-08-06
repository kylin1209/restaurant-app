package main;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class CustomerOrderMenuGUI extends JPanel{
	private static final long serialVersionUID = 1L;
	private Menu menu;
	private AppController controller;

	public CustomerOrderMenuGUI(AppController controller, Customer customer) {
		this.setMenu(new Menu());
	 	this.setController(controller);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setLayout(null);
		
		JButton btnHamburgerMenu = new JButton("☰ Menu");
		btnHamburgerMenu.setBounds(10, 11, 89, 23);
		btnHamburgerMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showCustomerOtherOptGUI();
			}
		});
		add(btnHamburgerMenu);
		
		JButton btnCart = new JButton("Cart");
		btnCart.setBounds(690, 11, 89, 23);
		btnCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showCustomerCartGUI();
			}
		});
		add(btnCart);
		
		JLabel lblOrderMenu = new JLabel("Order Menu");
		lblOrderMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderMenu.setFont(new Font("Arial", Font.BOLD, 24));
		lblOrderMenu.setBounds(290, 11, 172, 37);
		add(lblOrderMenu);
		
		JTextPane txtPaneSearch = new JTextPane();
		txtPaneSearch.setBounds(74, 59, 555, 23);
		add(txtPaneSearch);
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		
		for (MenuItem item : menu.getAllMenuItems()) {
		    String displayText = item.getName() + ", $" + item.getPrice();
		    listModel.addElement(displayText);
		}

		JList<String> menuList = new JList<>(listModel);
		menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(menuList);
		scrollPane.setBounds(195, 93, 570, 359);
		add(scrollPane);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = txtPaneSearch.getText();
				listModel.clear();
				for (MenuItem item : menu.searchMenu(query)) {
				    String displayText = item.getName() + ", $" + item.getPrice();
				    listModel.addElement(displayText);
				}
			}
		});
		btnSearch.setBounds(676, 59, 89, 23);
		add(btnSearch);
		
		JLabel lblFilters = new JLabel("Filters");
		lblFilters.setHorizontalAlignment(SwingConstants.CENTER);
		lblFilters.setFont(new Font("Arial", Font.PLAIN, 18));
		lblFilters.setBounds(13, 98, 172, 37);
		add(lblFilters);
		
		JButton btnAppetizers = new JButton("Appetizers");
		btnAppetizers.setBounds(42, 146, 121, 23);
		btnAppetizers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
				for (MenuItem item : menu.searchMenu("Appetizers")) {
				    String displayText = item.getName() + ", $" + item.getPrice();
				    listModel.addElement(displayText);
				}
			}
		});
		add(btnAppetizers);
		
		JButton btnMainDish = new JButton("Main Dish");
		btnMainDish.setBounds(42, 195, 121, 23);
		btnMainDish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
				for (MenuItem item : menu.searchMenu("Main")) {
				    String displayText = item.getName() + ", $" + item.getPrice();
				    listModel.addElement(displayText);
				}
			}
		});
		add(btnMainDish);
		
		JButton btnSides = new JButton("Sides");
		btnSides.setBounds(42, 239, 121, 23);
		btnSides.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
				for (MenuItem item : menu.searchMenu("Sides")) {
				    String displayText = item.getName() + ", $" + item.getPrice();
				    listModel.addElement(displayText);
				}
			}
		});
		add(btnSides);
		
		JButton btnDrinks = new JButton("Drinks");
		btnDrinks.setBounds(42, 287, 121, 23);
		btnDrinks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
				for (MenuItem item : menu.searchMenu("Drinks")) {
				    String displayText = item.getName() + ", $" + item.getPrice();
				    listModel.addElement(displayText);
				}
			}
			});
		add(btnDrinks);
		
		JLabel lblTotalPrice = new JLabel("$" + Double.toString(customer.getTotalCartPrice()));
		lblTotalPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalPrice.setFont(new Font("Arial", Font.BOLD, 24));
		lblTotalPrice.setBounds(288, 463, 165, 37);
		add(lblTotalPrice);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Arial", Font.BOLD, 24));
		lblTotal.setBounds(184, 463, 114, 37);
		add(lblTotal);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setBounds(690, 518, 89, 23);
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showCustomerPaymentGUI();
			}
		});
		add(btnCheckout);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(676, 474, 89, 23);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int menuIndex = menuList.getSelectedIndex();
				MenuItem itemToAdd = menu.getAllMenuItems().get(menuIndex);
				customer.addToCart(itemToAdd);
				lblTotalPrice.setText("$" + Double.toString(customer.getTotalCartPrice()));
			}
		});
		add(btnAdd);
	}

	public AppController getController() {
		return controller;
	}

	public void setController(AppController controller) {
		this.controller = controller;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
