import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that holds all information regarding a deliverer
 * Serializable to save in between programs
 */
public class Deliverer implements Serializable {
    private String username;
    private String password; // to be implemented with login logic later
    private List<Order> pastDeliveries;
    private Order currentOrder;

    public Deliverer(String username, String password) {
        this.username = username;
        this.password = password;
        this.pastDeliveries = new ArrayList<>();
        this.currentOrder = null;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Books an order, making it the deliverer's responsibility.
     *
     * @param order The order to book.
     */
    public void bookOrder(Order order) {
        if (this.currentOrder == null) {
            this.currentOrder = order;
        } else {
            // error message
            System.err.println("Deliverer already has an active order.");
        }
    }

    /**
     * Marks the current order as delivered and adds it to past deliveries.
     */
    public void deliverOrder() {
        if (this.currentOrder != null) {
            this.currentOrder.setStatus("Delivered");
            this.pastDeliveries.add(this.currentOrder);
            this.currentOrder = null;
        } else {
            // Error message
            System.err.println("No active order to deliver.");
        }
    }

    public Order getCurrentOrder() {
        return this.currentOrder;
    }

    public List<Order> getPastDeliveries() {
        return pastDeliveries;
    }
}