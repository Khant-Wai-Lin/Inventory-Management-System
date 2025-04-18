package com.inventory_management_system;
// This class represents a Product in the inventory system
public class Product {
    // Fields (attributes) of the Product
    private String name;       // Name of the product

    private int stock;  // Quantity available in stock
    private double price;   // Price per unit
    // Constructor: creates a new Product with name, stock, and price
    public Product(String name, int stock, double price) {
        this.name = name;    // Sets the product's name to the provided name
        this.stock = stock; // Sets the product's stock to the provided stock value
        this.price = price; // Sets the product's price to the provided price
    }
    // Public method to get the product's name
    public String getName() {   // Public method to get the product's name
        return this.name;   // Returns the current name of the product
    }

    public int getStock() {      // Public method to get the current stock quantity
        return this.stock;       // Returns the current stock level of the product
    }

    public double getPrice() {  // Public method to get the product's price
        return this.price;      // Returns the current price of the product
    }
    // Public method to set a new name for the product
    public void setName(String name) {  // Public method to set a new name for the product
        this.name = name;               // Updates the product's name with the given value
    }
     
    public void setStock(int stock) {   // Public method to set a new stock quantity
        this.stock = stock;             // Updates the product's stock with the given value
    }

    public void setPrice(double price) {    // Public method to set a new price for the product
        this.price = price;                 // Updates the product's price with the given value
    }

    // Comparable implementation
    // This method overrides the compareTo() method from the Comparable interface
    @Override
    public int compareTo(Product other) {
    // Compares this product's name to another product's name alphabetically
    // If this.name comes before other.name, it returns a negative number
    // If they are equal, it returns 0
    // If this.name comes after other.name, it returns a positive number
  
        return this.name.compareTo(other.name);
    }
}

