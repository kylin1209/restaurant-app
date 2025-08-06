package main;

import java.util.ArrayList;

public class Menu {
	
	private ArrayList<MenuItem> allMenuItems;
	
	public Menu() {
		this.setAllMenuItems(populateMenu());
	}
	
	private ArrayList<MenuItem> populateMenu() {
		String[][] items = {
				{"Crab Roe", "129.69", "Appetizers"},
				{"Salmon Sashimi", "29.23", "Sides"},
				{"Crab Alfredo", "19.23", "Main"},
				{"Tuna Cake", "9.67", "Appetizers"},
				{"Truffle Fries", "19.90", "Sides"},
				{"Soda", "4.21", "Drinks"},
				{"Tea", "1.59", "Drinks"},
				{"Chicken Burger", "32.49", "Main"},
				{"Crab Burger", "10.23", "Main"},
				{"Chicken Wings", "6.39", "Sides"},
				{"Seasalt Ice Cream and Fries", "98.42", "Sides"},
				{"Salad", "98.23", "Sides"},
				{"Pasta", "46.98", "Main"},
				{"Egg Rolls", "1.30", "Appetizer"},
				{"Lobster Bisque", "22.50", "Appetizers"},
			    {"Shrimp Tempura", "14.75", "Appetizers"},
			    {"Garlic Butter Scallops", "25.00", "Appetizers"},
			    {"Miso Soup", "3.99", "Sides"},
			    {"Fried Calamari", "12.89", "Appetizers"},
			    {"Avocado Sushi Roll", "9.49", "Sides"},
			    {"Beef Teriyaki", "21.95", "Main"},
			    {"Grilled Salmon", "27.30", "Main"},
			    {"Clam Chowder", "8.25", "Sides"},
			    {"Matcha Latte", "5.60", "Drinks"},
			    {"Berry Smoothie", "6.99", "Drinks"},
			    {"Iced Coffee", "4.80", "Drinks"},
			    {"Dragon Roll", "17.45", "Main"},
			    {"Spaghetti Carbonara", "18.99", "Main"},
			    {"Veggie Stir Fry", "13.50", "Main"},
			    {"Stuffed Mushrooms", "11.10", "Appetizers"},
			    {"Pan-Seared Duck", "29.99", "Main"},
			    {"Blueberry Cheesecake", "7.25", "Sides"},
			    {"Chocolate Lava Cake", "8.40", "Sides"},
			    {"Lemonade", "3.70", "Drinks"},
			    {"Sparkling Water", "2.99", "Drinks"},
			    {"Coconut Water", "3.49", "Drinks"},
			    {"Mango Smoothie", "6.25", "Drinks"},
			    {"Taro Milk Tea", "5.30", "Drinks"},
			    {"Thai Iced Tea", "4.95", "Drinks"},
			    {"Peach Iced Tea", "4.50", "Drinks"},
			    {"Cucumber Lime Cooler", "5.10", "Drinks"},
			    {"Honeydew Slush", "5.60", "Drinks"},
			    {"Black Coffee", "3.20", "Drinks"},
			    {"Vanilla Milkshake", "6.10", "Drinks"},
			    {"Strawberry Lemonade", "4.35", "Drinks"},
			    {"Caramel Frappé", "5.80", "Drinks"},
			    {"Cold Brew", "4.70", "Drinks"},
			    {"Passionfruit Green Tea", "5.20", "Drinks"},
			    {"Orange Juice", "3.85", "Drinks"},
			    {"Apple Cider", "4.15", "Drinks"},
			    {"Mint Mojito (Non-Alcoholic)", "5.75", "Drinks"},
			    {"Ginger Ale", "3.40", "Drinks"},
			    {"Lychee Soda", "5.00", "Drinks"},
			    {"Watermelon Juice", "4.90", "Drinks"}
		};
				
		ArrayList<MenuItem> menu = new ArrayList<MenuItem>(); 
		
		for (String[] item : items) {
			String name = item[0];
			double price = Double.parseDouble(item[1]);
			String filter = item[2];
			menu.add(new MenuItem(name, price, filter));
		}
	
		return menu;
	}

	public ArrayList<MenuItem> getAllMenuItems() {
		return allMenuItems;
	}

	public void setAllMenuItems(ArrayList<MenuItem> allMenuItems) {
		this.allMenuItems = allMenuItems;
	}
	
	public ArrayList<MenuItem> searchMenu(String query) {
		ArrayList<MenuItem> searchedMenu = new ArrayList<MenuItem>();
		switch (query) {
		case "Main", "Appetizers", "Sides", "Drinks":
			for (MenuItem item : this.getAllMenuItems()) {
				if (item.getFilter().contains(query)) {
					searchedMenu.add(item);
				}
			}
			break;
		default:
			for (MenuItem item : this.getAllMenuItems()) {
				if (item.getName().toLowerCase().contains(query)) {
					searchedMenu.add(item);
				}
			}	
			break;
		} 
		
		return searchedMenu;
	}

}
