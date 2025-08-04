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
    private String orderID;
    private String status; // e.g., "Pending", "Booked", "Delivered"
    private String destinationAddress;

    public Order(String orderID, String status, String destinationAddress) {
        this.orderID = orderID;
        this.status = status;
        this.destinationAddress = destinationAddress;
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
}
