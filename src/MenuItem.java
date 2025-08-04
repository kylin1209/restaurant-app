package src;
import java.io.Serializable;

/**
 * A model class representing a single menu item with its name and price.
 * It is marked as Serializable to allow its objects to be saved and transmitted.
 */
public class MenuItem implements Serializable {

    // Unique identifier for the serialized class. Not strictly necessary for this simple example,
    // but good practice for Serializable classes.
    private static final long serialVersionUID = 1L;

    // Instance variables to store the name and price of the menu item.
    private String name;
    private double price;

    /**
     * Constructor for the MenuItem class.
     *
     * @param name The name of the menu item.
     * @param price The price of the menu item.
     */
    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Getter method for the menu item's name.
     *
     * @return The name of the menu item.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the menu item's price.
     *
     * @return The price of the menu item.
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @return A formatted string with the item's name and price.
     */
    @Override
    public String toString() {
        return name + " - $" + String.format("%.2f", price);
    }

}