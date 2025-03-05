import java.util.ArrayList;

public class Order {
    
    // Counter to generate unique order IDs
    private static int orderCounter = 1;
    
    // Order attributes
    private int orderId;
    private String customerName;
    private ArrayList<Product> products;
    private float total;
    private String status;

    private static boolean historyAdded = false; // Flag to check if order history has been added
    
    private static ArrayList<Order> orders = new ArrayList<>(); // List to store all orders

    // Constructor to create a new order
    public Order(String customerName, ArrayList<Product> products, float total, String status) {
        this.orderId = orderCounter++;
        this.customerName = customerName;
        this.products = products;
        this.total = total;
        this.status = status;
    }
    
    // Displays the order details in an invoice format
    public void showOrder() {
        System.out.println("\n========================");
        System.out.println("Order ID : " + orderId);
        System.out.println("Customer : " + customerName);
        System.out.println("------------------------");
        
         // Loop through products and display their names and quantities
        for (Product product : products) {
            System.out.println(product.getName() + " : " + product.getQuantity());
        }

        System.out.println("------------------------");
        System.out.println("Total    : Rs." + total);
        System.out.println("Status   : " + status);
        System.out.println("========================");
    }
    
    // Returns the list of all orders
    public static ArrayList<Order> getOrders() {
        return orders;
    }
    
}
