import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Shop {
    
    private ArrayList<Product> products = new ArrayList<>(); // Product store list
    private ArrayList<Order> orders = new ArrayList<>(); // Orders store list
    
    Scanner scan = new Scanner(System.in);
    
    // Constructor to automatically loads the stored products in list
    public Shop() {
        availableProducts();
    }

    // Main method
    public static void main(String[] args) {
        Shop sh = new Shop();
        sh.loginDashboard();
        sh.availableProducts();
    }
    
    // Add products to the shop
    public void availableProducts(){
        products.add(new Product("P001", "Asus Laptop","i7 12th laptop", 50, 175000.0f));
        products.add(new Product("P002", "Samsung S25","12GB RAM, 512GB Storage", 50, 55000.0f));
        products.add(new Product("P003", "Sony Headphone","Blutooth Headphones" ,0, 5400.0f));
        products.add(new Product("P004", "Google Pixel 6", "128GB Storage, 8GB RAM, 5G", 50, 70000.0f));
        products.add(new Product("P005", "Samsung Galaxy Tab S8", "11-inch, 8GB RAM, 128GB Storage", 35, 75000.0f));
        products.add(new Product("P006", "Logitech MX Master 3", "Wireless Mouse with Bluetooth", 100, 6000.0f));
        products.add(new Product("P007", "Bose QuietComfort 35", "Noise Cancelling Headphones",0, 25000.0f));
        products.add(new Product("P009", "Sony PlayStation 5", "Next-Gen Gaming Console", 10, 95000.0f));
    }
    
    // Getter method to return the list of products
    public ArrayList<Product> getProducts() {
        return products;
    }
    
    // Login dashboard method
    public void loginDashboard(){
    
        // Admin and Customer instances with access to the shop
        Admin admin = new Admin(this);
        Customer cust = new Customer("","",this);
        
        System.out.println("\nWelcome to the City Electronics!\n");        
        
        while(true){
            
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Exit");
            System.out.print("Select an option : ");
            
            try {
                int loginOption = scan.nextInt();
                
                switch(loginOption){
                case 1: // admin Login
                    admin.loginAdmin();
                    break;
                case 2: // customer Login
                    cust.loginCustomer();
                    break;
                case 3: // exit
                    System.out.println("Thank you for visiting!");
                    System.exit(0);
                default: // invalid Input
                    System.out.println("Invalid option!");    
                }
                
            } catch (InputMismatchException e) { // if non-integer is entered
                System.out.println("\nInvalid input! Please enter a valid number.");
                scan.nextLine(); // reads and clears that invalid input from the buffer
            } 
        } //program ends 
    }
}
