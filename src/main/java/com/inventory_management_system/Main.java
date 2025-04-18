package com.inventory_management_system;

public class Main {

    public static void main(String[] args) {
        // Here is a good example of using Polymorphism
        // We can easily change the implementation of InventorySystemInterface in the future
        // Or we can easily replace with a different implementation of InventorySystemInterface
        InventorySystemInterface inventory = new InventorySystem();
        inventory.start();
    }
}