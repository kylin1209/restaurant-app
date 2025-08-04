package main;

import java.util.ArrayList;

// ONLY ONE ACCOUNTMANAGER CAN BE INSTANTIATED!!!!
// >>>>>>>>>> MULTIPLE ACCOUNTMANAGERS ARE NOT ALLOWED <<<<<<<<<<<<<
// Using Singleton Method Below:
public class AccountManager {
	private static AccountManager instance;
	private ArrayList<Deliverer> delivererAccounts;
	private ArrayList<Customer> customerAccounts;
	
	private AccountManager() {
		this.setCustomerAccounts(new ArrayList<Customer>());
		this.setDelivererAccounts(new ArrayList<Deliverer>());
	}
	
	public static AccountManager getInstance() {
		if (instance == null) {
			instance = new AccountManager();
		}
		 return instance;
	}

	public ArrayList<Deliverer> getDelivererAccounts() {
		return delivererAccounts;
	}

	public void setDelivererAccounts(ArrayList<Deliverer> delivererAccounts) {
		this.delivererAccounts = delivererAccounts;
	}

	public ArrayList<Customer> getCustomerAccounts() {
		return customerAccounts;
	}

	public void setCustomerAccounts(ArrayList<Customer> customerAccounts) {
		this.customerAccounts = customerAccounts;
	}
	

}
