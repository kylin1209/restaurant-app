import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Structure for customer orders to be accessed between both Deliverers and Customers
 */
public class Order implements Serializable {
    private String orderID;
    private String status;
    private List<MenuItem> items;

    public Order(String orderID, List<MenuItem> items) {
        this.orderID = orderID;
        this.status = "Pending";
        this.items = items;
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
    
    public List<MenuItem> getItems() {
        return items;
    }
}

/**
 * An item on the menu.
 */
class MenuItem implements Serializable {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return name + " - $" + String.format("%.2f", price);
    }
}