// NOTE: The AppController class below is a stub. --> We will change it as more implementation is involved, for now it is just made to test components as we move on
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


class AppController {
    private JFrame mainFrame;
    private Deliverer currentDeliverer;

    public AppController(JFrame frame, Deliverer deliverer) {
        this.mainFrame = frame;
        this.currentDeliverer = deliverer;
    }
    
    // Method to switch to the Login GUI page
    public void showLoginGUI() {
        // This is a placeholder as per user request to remove login logic
        mainFrame.getContentPane().removeAll();
        JPanel panel = new JPanel();
        panel.add(new JLabel("Login logic removed. Back to main screen."));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showDelivererBookingGUI());
        panel.add(backButton);
        mainFrame.add(panel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    
    // Method to switch back to the previous screen (for Back button)
    public void showPreviousScreen() {
        // go back to booking page
        showDelivererBookingGUI();
    }
    
    // Method to show the Deliverer Past Deliveries GUI
    public void showPastDeliveriesGUI() {
        mainFrame.getContentPane().removeAll();
        DelivererPastOrdersGUI pastOrdersGUI = new DelivererPastOrdersGUI(this, currentDeliverer);
        mainFrame.add(pastOrdersGUI);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    
    // Placeholder for a method to show the Deliverer Booking GUI
    public void showDelivererBookingGUI() {
        mainFrame.getContentPane().removeAll();
        // Create some dummy orders
        List<Order> dummyOrders = new ArrayList<>();
        dummyOrders.add(new Order("101", null));
        dummyOrders.add(new Order("102", null));
        dummyOrders.add(new Order("103", null));
        
        DelivererBookOrderGUI bookingGUI = new DelivererBookOrderGUI(this, dummyOrders);
        mainFrame.add(bookingGUI);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    
    public void handleBookOrder(String orderId) {
        System.out.println("Controller received request to book order: " + orderId);
        JOptionPane.showMessageDialog(mainFrame, "Order " + orderId + " booked! (Simulated)");
    }

    /**
     * The main method to run the application. --> TO BE CHANGED ONCE LOGIN LOGIC IS IMPLEMENTED
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Deliverer Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 500);
            frame.setLocationRelativeTo(null);
            
            // Create a dummy deliverer
            Deliverer dummyDeliverer = new Deliverer("d_test", "pass");

            AppController controller = new AppController(frame, dummyDeliverer);
            controller.showDelivererBookingGUI();
            frame.setVisible(true);
        });
    }
}