package src;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

/**
 * Structure for customer orders to be accessed between both Deliverers and Customers
 */
public class Order implements Serializable {
    // Unique identifier for the serialized class
    private static final long serialVersionUID = 1L;

    private String orderID;
    private String status; // e.g., "Pending", "Booked", "Delivered"
    private String destinationAddress;
    private ArrayList<MenuItem> items; // Added to store menu items in the order
    private double totalPrice; // Added to store the total price of the order

    public Order(String orderID, String status, String destinationAddress, List<MenuItem> items) {
        this.orderID = orderID;
        this.status = status;
        this.destinationAddress = destinationAddress;
        this.items = items != null ? new ArrayList<>(items) : new ArrayList<>(); // Initialize items list
        this.totalPrice = calculateTotalPrice(); // Calculate total price upon creation
    }

    /**
     * Calculates the total price of all items in the order.
     * @return The calculated total price.
     */
    private double calculateTotalPrice() {
        double total = 0.0;
        if (items != null) {
            for (MenuItem item : items) {
                total += item.getPrice();
            }
        }
        return total;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }
    
    /**
     * Returns the list of menu items in this order.
     * @return A list of MenuItem objects.
     */
    public ArrayList<MenuItem> getItems() {
        return items;
    }

    /**
     * Returns the total price of this order.
     * @return The total price.
     */
    public double getTotalPrice() {
        return totalPrice;
    }
}