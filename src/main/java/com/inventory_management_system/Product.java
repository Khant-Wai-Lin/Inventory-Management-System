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

    public int getStock() {
        return this.stock;
    }

    public double getPrice() {
        return this.price;
    }
    // Public method to set a new name for the product
    public void setName(String name) {  // Public method to set a new name for the product
        this.name = name;               // Updates the product's name with the given value
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

