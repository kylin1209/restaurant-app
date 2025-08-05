package src;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// ONLY ONE ACCOUNTMANAGER CAN BE INSTANTIATED!!!!
// >>>>>>>>>> MULTIPLE ACCOUNTMANAGERS ARE NOT ALLOWED <<<<<<<<<<<<<
// Using Singleton Method Below:
public class AccountManager {
    private static final String DATA_FILE = "data.bin"; // File to save/load data
    private static AccountManager instance;
    private ArrayList<Deliverer> delivererAccounts;
    private ArrayList<Customer> customerAccounts;
    
    private AccountManager() {
        this.setCustomerAccounts(new ArrayList<Customer>());
        this.setDelivererAccounts(new ArrayList<Deliverer>());
        loadAccounts(); // Load accounts when the manager is instantiated
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
    
    /**
     * Saves all deliverer and customer accounts to a file.
     */
    public void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(delivererAccounts);
            oos.writeObject(customerAccounts);
            System.out.println("Accounts saved successfully to " + DATA_FILE);
        } catch (IOException e) {
            System.err.println("Error saving accounts: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Loads all deliverer and customer accounts from a file.
     */
    @SuppressWarnings("unchecked") // Suppress unchecked cast warning for ArrayList deserialization
    private void loadAccounts() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
                delivererAccounts = (ArrayList<Deliverer>) ois.readObject();
                customerAccounts = (ArrayList<Customer>) ois.readObject();
                System.out.println("Accounts loaded successfully from " + DATA_FILE);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading accounts: " + e.getMessage());
                e.printStackTrace();
                // If loading fails, re-initialize empty lists to prevent NullPointerExceptions
                delivererAccounts = new ArrayList<>();
                customerAccounts = new ArrayList<>();
            }
        } else {
            System.out.println("No existing data file found. Starting with empty accounts.");
            delivererAccounts = new ArrayList<>();
            customerAccounts = new ArrayList<>();
        }
    }
}
