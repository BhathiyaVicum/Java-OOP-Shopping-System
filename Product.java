public class Product {
    
    // Private attributes to store product details
    private String id;
    private String name;
    private String description;
    private int quantity;
    private float price;

    // Constructor to initialize a Product object with given details
    public Product(String id, String name, String description, int quantity, float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter methods to retrieve product details
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setter methods to update product details
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Method to display product info
    public void showInfo(){
        System.out.println("Product ID   : " + id);
        System.out.println("Product Name : " + name);
        System.out.println("Description  : " + description);
        System.out.println("Quantity     : " + quantity);
        System.out.println("Price        : " + price);
        System.out.println("-----------------------------------------");
    }
    
}
