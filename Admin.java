import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    
    //admin username and password
    private String adminUsername = "admin";
    private String adminPassword = "admin123";
    
    boolean adminLoggedIn = false; // Flag to check if admiin logged in
    Shop st = new Shop();
    private Shop shop; // reference to Shop class
        
    Scanner scan = new Scanner(System.in);
    
     // Constructor to initialize the Admin with a Shop instance
    public Admin(Shop shop) {
        this.shop = shop;
    }    
    
    // Admin login dashboard method
    public void loginAdmin() {
     
        System.out.println("\nAdmin Login");    
        
        //repeat until admin input correct username and password
        while(!adminLoggedIn){
            
            System.out.print("Enter username : "); //admin enter uername
            String inputUsername = scan.next();
            
            System.out.print("Enter password : "); //admin enter password
            String inputPassword = scan.next();
            
            
            // verify credentials
            if (inputUsername.equals(adminUsername) && inputPassword.equals(adminPassword)) {               
                adminLoggedIn = true; //Login Successful
                System.out.println("Login Successful!");
                adminMenu(); // Navigate to admin dashboard
                
            } else {
                System.out.println("Invalid username or password.\n");
                
                while(true){
                    //try again if login fails or exit admin login
                    System.out.println("1.Try Again");
                    System.out.println("2.Exit");
                    System.out.print("Select an option : ");
                    int adminChoice = scan.nextInt();
                    System.out.println("");
                    
                    switch(adminChoice){
                        case 1:
                            loginAdmin(); //back to the admin login dashboard to try 
                            break;
                        case 2:
                            st.loginDashboard(); //back to main dashboard
                            break;
                        default:
                            System.out.println("Invalid Option!");  
                    }
                }
            }
        }
    }
    
    // Admin selection dashboard method
    public void adminMenu(){
        
        while (adminLoggedIn) {
            
            System.out.println("\nWelcome to the Admin Dashboard!");
            System.out.println("1. Manage Products");
            System.out.println("2. View Customer Orders");
            System.out.println("3. Logout");
            System.out.print("Select an option: ");
            int adminChoice = scan.nextInt();
                
            switch(adminChoice){
                case 1: //product managment dashboard
                    manageProducts(); 
                    break;
                case 2: //view customer order status 
                    viewOrders();
                    break;
                case 3: //back to main dashboard
                    System.out.println("Admin Logout...\n"); 
                    st.loginDashboard();
                    break;
                default:
                    System.out.println("Invalid Option!");
            }    
        }
    }
    
    // Admin product management dashboard method
    public void manageProducts(){
        
        while (true) {
            
            System.out.println("\nProducts Management");
            System.out.println("1. Add Product");
            System.out.println("2. Modify Product");
            System.out.println("3. Remove Product");
            System.out.println("4. View Product");
            System.out.println("5. Back to Dashboard");
            System.out.print("Enter your choice: ");
            int adminChoice = scan.nextInt();
                
            switch(adminChoice){
                case 1: //add new product
                    addProduct();
                    break;
                case 2: //update exisiting product
                    updateProduct();
                    break;
                case 3: //remove exisiting product
                    removeProduct();
                    break;
                case 4 : //view available products
                    showProduct();
                    break;
                case 5: //back to dashboard
                    adminMenu();
                    break;
                default:
                    System.out.println("Invalid Option!");
            }
        }
    }
    
    // Method to add products
    public void addProduct(){
        
        showProduct(); // Display product list before added
        
        System.out.println("\n============ Add New Product ============");
       
        System.out.print("Enter Product ID   : ");
        String id = scan.next();
        scan.nextLine(); // Consume the leftover newline
        System.out.print("Enter Product Name : ");
        String name = scan.nextLine();
        System.out.print("Enter Description  : ");
        String description = scan.nextLine();
        System.out.print("Enter Quantity     : ");
        int quantity = scan.nextInt();
        System.out.print("Enter Price        : ");
        float price = scan.nextFloat();
        
        // Add product to the shop's product list
        shop.getProducts().add(new Product(id, name, description,quantity, price));
        System.out.println("Product added successfully!\n");
               
        showProduct(); // Display updated product list

    }
    
    // Method to display all available products
    public void showProduct(){
                
        System.out.println("\n=============== Products ===============");
        
        // Loop through each product in the shop's product list  
        for (Product product : shop.getProducts()) {
            product.showInfo(); // updatedproduct list
        }
        
    }
    
    // Method to update existing products
    public void updateProduct(){
        
        showProduct(); // Display all available products before updating
        
        System.out.println("\n============ Update Product ============");
    
        // admin to enter the product ID for updating
        System.out.print("Enter Product ID : ");
        String id = scan.next();
        
        boolean productFound = false; // Flag to check if product exists
        
        // Loop through the product list to find the matching Id
        for (Product product : shop.getProducts()) {
            if (product.getId().equals(id)) {
                productFound = true; // Mark the product as found

                // Show current product details
                System.out.println("\n=========== Product Details ============");
                System.out.println("Product Name   : " + product.getName());
                System.out.println("Description    : " + product.getDescription());
                System.out.println("Quantity       : " + product.getQuantity());
                System.out.println("Price          : " + product.getPrice());
                
                // Confirm removal from the admin
                System.out.print("\nAre you sure to upadate this product? [yes/no]");
                String upadteOption = scan.next();
                
                if (upadteOption.equalsIgnoreCase("Yes")) {
                    
                    //update product details
                    System.out.println("\n============ Update Product ============");
                    
                    System.out.print("Enter New Product Name : ");
                    scan.nextLine(); // Consume leftover newline
                    String newName = scan.nextLine(); // Consume leftover newline
                    System.out.print("Enter Description      : ");
                    String newDesc = scan.nextLine();
                    System.out.print("Enter New Quantity     : ");
                    int newQty = scan.nextInt();
                    System.out.print("Enter New Price        : ");
                    float newPrice = scan.nextFloat();
                    scan.nextLine(); // Consume leftover newline
              
                    // Update product details
                    product.setName(newName);
                    product.setDescription(newDesc);
                    product.setQuantity(newQty);
                    product.setPrice(newPrice);
        
                    System.out.println("\nProduct updated successfully!");
                    showProduct(); // Display updated product list
                    break; // Exit loop
                }
                else if (upadteOption.equalsIgnoreCase("No")) {
                    // Cancel update and exit the loop
                    System.out.println("\nProduct not updated!");
                    break;
                }
                else {
                     // Handle invalid input for removal confirmation
                    System.out.println("Invalid option!");
                    break;
                }                    
            }
        }
        //if product not found in list
        if (!productFound) {
            System.out.println("Product with ID " + id + " not found.");
        }
    }
    
    // Method to remove an existing product from the inventory
    public void removeProduct(){
        
        showProduct();  // Display the current product list before removal
        
        System.out.println("\n============ Remove Product ============");
        
        boolean productFound = false; // Flag to track if the product exists
        
        System.out.print("Enter Product ID : ");
        String id = scan.next();
        
        // Retrieve the list of products from the shop
        ArrayList<Product> product = shop.getProducts();
        
        // Check through the product list to find the matching ID
        for (int i = 0; i < product.size(); i++) {
            if (product.get(i).getId().equals(id)) {
                // Display product details before removal
                System.out.println("======== Product to be Removed =========");
                product.get(i).showInfo();
                
                // Confirm removal from the admin
                System.out.print("\nAre you sure to remove this product? [yes/no]");
                String removeOption = scan.next();
                
                if (removeOption.equalsIgnoreCase("Yes")) {
                    // Remove the product from the list
                    product.remove(i);
                    System.out.println("Product removed successfully!");
                    productFound = true;
                    showProduct(); // Display the updated product list
                    break; // Exit loop after removal
                }
                else if (removeOption.equalsIgnoreCase("No")) {
                    // Cancel removal and exit the loop
                    System.out.println("Product removal canceled.");
                    productFound = true;
                    break;
                }
                else {
                     // Handle invalid input for removal confirmation
                    System.out.println("Invalid option!");
                    productFound = true;
                    break;
                }
            }
        }
        
        //if product not found
        if (!productFound) {
            System.out.println("Product with ID " + id + " not found.");
        }
    }

    // View all customer orders    
    private void viewOrders() {
        
        // Display no orders if orders not placed by customer
        if (Order.getOrders().isEmpty()) {
            
        System.out.println("\nNo orders yet.");
        
        } else {
        
            System.out.println("\n==== Order History =====");
            
            // // Loop through all the orders
            for (Order order : Order.getOrders()) {
            order.showOrder();  // Display each order with the corresponding invoice number
            }
        }
    }
}
