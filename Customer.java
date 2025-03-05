import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
    
    // Customer username and password
    private String customerUsername ;
    private String customerPassword ;
    
    boolean isCustomerLoggedIn = false; // Flag to check if customer logged in
    Shop st = new Shop();
    private Shop shop;
    
    private String loggedInCustomer = ""; // Stores the username of the currently logged in customer.
    
    Scanner scan = new Scanner(System.in);
    
    private static ArrayList<Customer> customers = new ArrayList<>(); // Customer register store list
    private ArrayList<Product> cart = new ArrayList<>(); // Customer cart to store product list
    private ArrayList<Order> order = new ArrayList<>(); // Customer orders store list   
    
    // Constructor to initialize the shop object
    public Customer(String customerUsername, String customerPassword, Shop shop) {
        this.customerUsername = customerUsername;
        this.customerPassword = customerUsername;
        this.shop = shop;
    }  
    
    // Method to customer login dashboard
    public void loginCustomer(){
        System.out.println("\nCustomer Login");
        System.out.println("1. Create Account");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Select an option : ");
        int choice = scan.nextInt();
        
        switch (choice){
            case 1: // Customer create new account and login
                createAccount();
                authenticateCustomer();
                break;
            case 2: // Customer login
                authenticateCustomer();
                break;
            case 3: // Back to main dashboard
                st.loginDashboard();
            default:
                System.out.println("Invalid Option!");
        } 
    }
    
    // method to create a new customer account    
    public void createAccount(){
        
        System.out.println("\nCreate New Account");
        System.out.print("Enter username : ");
        String newUsername = scan.next();
        
        System.out.print("Enter password : ");
        String newPassword = scan.next();
        
        // Check if the username already exists
        for (Customer custname : customers) { // Iterate over the list of existing customers
            if (custname.customerUsername.equalsIgnoreCase(newUsername)) { // If matched existing account
                System.out.println("Username already exists! Try again.");
                createAccount();
                return;
            }
        }
        
        // Create new customer and add to list
        Customer newCustomer = new Customer(newUsername, newPassword, shop); // Create a new customer object
        customers.add(newCustomer); // Add the new customer to the list of customers
        System.out.println("Account create successfully! You can now login.");
        
    }
    
    
    public void authenticateCustomer(){
        
        System.out.println("\nLogin To Account");
        
        // Repeat until customer input correct username and password
        while (!isCustomerLoggedIn) {
            System.out.print("Enter username: ");
            String inputUsername = scan.next(); // Read the input username
            
            System.out.print("Enter password: ");
            String inputPassword = scan.next(); // Read the input password
            
            // Validate username and password
            for (Customer cus : customers) { // Loop through the list of registered customers
                if (cus.customerUsername.equalsIgnoreCase(inputUsername) && cus.customerPassword.equals(inputPassword)) {
                    isCustomerLoggedIn = true; // Set login flag to true
                    loggedInCustomer = inputUsername; // Store the username of the logged in customer
                    System.out.println("Login Successful!");
                    showCustomerMenu(); // Show the customer menu
                    return;
                }
            }
            
            // If login fails
            System.out.println("Invalid username or password.\n");
            System.out.println("1. Try Again");
            System.out.println("2. Exit");
            System.out.print("Select an option: ");
            int choice = scan.nextInt();
            
            if (choice == 2) { // If the user chooses to exit
                shop.loginDashboard(); // Go back to the login dashboard
                return; // Exit the method
            }
        }
    }
    
    // Method to customer menu after login
    public void showCustomerMenu(){
    
        // Loop runs as long as the customer is logged in
        while (isCustomerLoggedIn) {
            System.out.println("\nWelcome to City Electronics!");
            System.out.println("1. Browse Products");
            System.out.println("2. Place Order");
            System.out.println("3. Logout");
            System.out.print("Select an option: ");
            int customerChoice = scan.nextInt(); // Read the customer’s choice
                
            switch(customerChoice){
                case 1: // browse products
                    browseProducts();
                    break;
                case 2: // place order
                    placeOrder();
                    break;
                case 3: // back to main dashboard
                    System.out.println("Logout...\n"); 
                    st.loginDashboard(); //go back to the login dashboard
                    break;
                default: // If the customer enters an invalid option
                    System.out.println("Invalid Option!");
            }    
        }
    }
    
    // Method to display the list of available products
    public void browseProducts(){
                
        System.out.println("\n============ Browse Products ===========");
        
        // Loop through the list of products in the shop
        for (Product product : shop.getProducts()) {
            
            System.out.println("Product ID     : " + product.getId());
            System.out.println("Product Name   : " + product.getName());
            System.out.println("Description    : " + product.getDescription());
            System.out.println("Price          : " + product.getPrice());
            
            // check if product is in stock or out of stock
            if (product.getQuantity()>0){ 
                System.out.println("Available"); // If the quantity is greater than 0, the product is available
            }
            else {
                System.out.println("Out of Stock");  // If the quantity is 0 or less, the product is out of stock
            }
            System.out.println("-----------------------------------------");
        }
    }
    
    // Method to customer placing order
    public void placeOrder(){
       
        browseProducts(); // Display products to browse
        
        System.out.println("\n============= Place Order ==============");
        Product selectedProduct = null; // Variable to store the selected product
        
        while(true){ // Loop to handle customer order placement
            
            // customer product selection to add to cart by product name or id
            System.out.print("Enter Product ID  : ");
            String productSelection = scan.next();
            
            // checking for the selected product exists
            for (Product product : shop.getProducts()){
                if (product.getId().equalsIgnoreCase(productSelection)){
                    selectedProduct = product; // Store the selected product
                }
            }
             
            if (selectedProduct == null){ // if product not found
                System.out.println("Product not found!");
            } 
            else if (selectedProduct.getQuantity() <= 0 ){ //if product out of stock
                System.out.println("Sorry, out of stock");
            }
            else { //customer quantity enter for the product
                System.out.print("Enter quantity    : ");
                int qty = scan.nextInt();
                
                if (qty <= 0 ){ // if quantity is invalid
                    System.out.println("Invalid quantity. Try again.");
                } 
                else if (qty > selectedProduct.getQuantity()){ // if quantity exceeds stock
                    System.out.println("Insufficient stock!");
                } 
                else { // add product to cart
                    cart.add(new Product(selectedProduct.getId(), selectedProduct.getName(),selectedProduct.getDescription(), qty, selectedProduct.getPrice()));
                    System.out.println(qty + " * " + selectedProduct.getName() + " added to cart.\n");
                }
            }
            
            // customer can continue shopping or go to checkout 
            System.out.print("Press 1 to Continue Shopping or 2 to Go to Checkout : ");
            int choice = scan.nextInt();
            
            if (choice == 2){ // If the user chooses to exit
                checkout(); // Go back to the login dashboard
                return; // Exit the method
            }
        }
    }
    
    // Method to customer checkout menu 
    public void checkout(){
        
        float total = 0.0f; // Variable to store the total order amount 
    
        System.out.println("\n=============== Checkout ===============");
    
        // display all items in the cart and calculate total
        for (Product product : cart) {
            product.showInfo(); // Display product details  
            total += product.getPrice() * product.getQuantity(); // Calculate total amount  
        }
    
        System.out.println("Total Amount : " + total);
        System.out.print("Do you want to confirm the order? [yes/no]: ");
        String orderConfirm = scan.next();

        // order status whether customer confirm order or not
        String status;

        if (orderConfirm.equalsIgnoreCase("yes")) {
            status = "Paid"; // Set status as paid 
            System.out.println("\nOrder Placed Successfully!");
            
            // Reduce the stock quantity of each ordered product
            for (Product orderedProduct : cart) {
                for (Product shopProduct : shop.getProducts()) {
                    if (shopProduct.getId().equals(orderedProduct.getId())) {
                        shopProduct.setQuantity(shopProduct.getQuantity() - orderedProduct.getQuantity());
                    }
                }
            }    
        } else {
            status = "Canceled"; // Set status as canceled  
            System.out.println("\nOrder Canceled.");
        }

        // Create a new order and add it to the orders list
        ArrayList<Product> orderProducts = new ArrayList<>(cart);
        Order newOrder = new Order(loggedInCustomer, orderProducts, total, status); // Create new order  
        Order.getOrders().add(newOrder); // Add order to the order list  
        cart.clear(); // Clear the cart after order
        
    }
    
}
