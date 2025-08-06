package main;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
    private String password;
    private String address;
    private ArrayList<Order> pastOrders;
    private ArrayList<MenuItem> cart;
    private double totalCartPrice;
    private Order currentOrder;
    
	public Customer(String username, String password, String address) {
		this.username = username;
		this.password = password;
		this.address = address;
		this.currentOrder = null;
		this.pastOrders = new ArrayList<Order>();
		this.cart = new ArrayList<MenuItem>();
		this.setTotalCartPrice(0);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<Order> getPastOrders() {
		return pastOrders;
	}

	public void setPastOrders(ArrayList<Order> pastOrders) {
		this.pastOrders = pastOrders;
	}

	public Order getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(Order currentOrder) {
		this.currentOrder = currentOrder;
	}
	
	public void changeAddress(String newAddress) {
		this.setAddress(newAddress);
	}
	
	public void addToCart(MenuItem item) {
		cart.add(item);
		this.totalCartPrice = Math.round((totalCartPrice + item.getPrice()) * 100.0) / 100.0;
	}

	public double getTotalCartPrice() {
		return totalCartPrice;
	}

	public void setTotalCartPrice(int totalPrice) {
		this.totalCartPrice = totalPrice;
	}

	public ArrayList<MenuItem> getCart() {
		return cart;
	}

	public void setCart(ArrayList<MenuItem> cart) {
		this.cart = cart;
	}
	
	public void addToPastOrders(Order order) {
		pastOrders.add(order);
	}
	
	public void resetCart() {
		cart.clear();
		this.totalCartPrice = 0;
	}
	
	public void removeFromCart(int index) {
		this.totalCartPrice = Math.round((this.totalCartPrice - cart.get(index).getPrice()) * 100.0) / 100.0;
		cart.remove(index);
	}
	
	

    
	
    
}
