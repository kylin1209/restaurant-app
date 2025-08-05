package src;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that holds all information regarding a deliverer
 * Serializable to save in between programs
 */
public class Deliverer implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for Serializable classes
    private String username;
    private String password;
    private Order currentOrder; // Note: Order must also be Serializable
    private List<Order> pastDeliveries; // List of past delivered orders

    /**
     * Constructor for the Deliverer.
     * @param username The deliverer's username.
     * @param password The deliverer's password.
     */
    public Deliverer(String username, String password) {
        this.username = username;
        this.password = password;
        this.pastDeliveries = new ArrayList<>();
    }

    // Getters and Setters

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }
    
    /**
     * Sets the deliverer's current active order.
     * @param currentOrder The order to set as current. Can be null.
     */
    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public List<Order> getPastDeliveries() {
        return pastDeliveries;
    }

    /**
     * Books a new order for the deliverer.
     * @param order The order to book.
     */
    public void bookOrder(Order order) {
        this.currentOrder = order;
    }
    
    /**
     * Adds a completed delivery to the deliverer's past deliveries list.
     * @param order The order that was delivered.
     */
    public void addPastDelivery(Order order) {
        if (order != null) {
            this.pastDeliveries.add(order);
        }
    }
}

