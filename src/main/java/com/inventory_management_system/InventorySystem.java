package com.inventory_management_system;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

interface IInventorySystem {
    void start();

    void addNewProduct();

    void viewAllProducts();

    void editProduct();

    void removeProduct();

    void printMenu();
}

public class InventorySystem implements IInventorySystem {
    private List<Product> products = new ArrayList<Product>();
    private FileHandler fileHandler = new FileHandler();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean isExit = false;

        do {
            printMenu();
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        viewAllProducts();
                        break;
                    case 2:
                        addNewProduct();
                        break;
                    case 3:
                        editProduct();
                        break;
                    case 4:
                        removeProduct();
                        break;
                    case 5:
                        isExit = true;
                        System.out.println("👋 Exiting... Bye!");
                        break;
                    default:
                        System.out.println("❌ Invalid choice.");
                }

            } catch (Exception e) {
                System.out.println("❌ Invalid input. Please enter a number.");
            }

        } while (!isExit);
    }

    public void addNewProduct() {

        try (PrintWriter writer = new PrintWriter(new FileWriter("output.txt", true))) { // append mode
            System.out.print("Enter product name: ");
            String name = scanner.nextLine();

            System.out.print("Enter stock quantity: ");
            int stock = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter price: ");
            double price = Double.parseDouble(scanner.nextLine());

            Product newProduct = new Product(name, stock, price);
            products.add(newProduct);

            writer.println(name + "," + stock + "," + price);
            System.out.println("✅ Product added successfully.");
        } catch (IOException e) {
            System.out.println("⚠️ Failed to write product: " + e.getMessage());
        }
    }

    public void viewAllProducts() {
        products = fileHandler.readFile();
        if(products.isEmpty()){
            System.out.println("Sorry ,there in no products in the inventory.");
            return;
        }
        Collections.sort(products);
        System.out.printf("%-4s | %-20s | %-10s | %-10s%n", "No.", "Name", "Stock", "Price");
        printLine();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.printf("%-4d | %-20s | %-10d | %-10s%n",
                    i + 1,
                    product.getName(),
                    product.getStock(),
                    String.valueOf(product.getPrice()));
        }
    }

    // Method to edit an existing product in the inventory
public void editProduct() {
    try {
        // Prompt user for product number to update
        System.out.print("Enter product number to update: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline

        // Validate product number
        if (choice < 1 || choice > products.size()) {
            System.out.println("❌ Invalid choice.");
            return;
        }

        // Get the selected product from the list
        Product selected = products.get(choice - 1);
        System.out.println("Selected: " + selected);
        System.out.println("What do you want to update?");
        System.out.println("1. Name");
        System.out.println("2. Stock");

        // Prompt user to choose which attribute to update
        int updateChoice = scanner.nextInt();
        scanner.nextLine();

        // Update the selected attribute
        if (updateChoice == 1) {
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();
            selected.setName(newName);
        } else if (updateChoice == 2) {
            System.out.print("Enter new stock value: ");
            int newStock = scanner.nextInt();
            selected.setStock(newStock);
        } else {
            System.out.println("❌ Invalid option.");
            return;
        }

        // Save the updated product list to the file
        fileHandler.writeFile(products);
        System.out.println("✅ Product updated and saved.");

    } catch (InputMismatchException e) {
        // Handle invalid input types (non-numeric values)
        System.out.println("⚠️ Please enter a valid number.");
    } catch (Exception e) {
        // Handle any other unexpected errors
        System.out.println("❌ Unexpected error: " + e.getMessage());
    }
}

// Method to remove a product from the inventory
public void removeProduct() {

    // Check if the products list is empty
    System.out.print("Enter product number to remove: ");
    if (products.isEmpty()) {
        System.out.println("⚠️ No products available to remove.");
        return;
    }
    
    try {
        // Prompt user for product number to remove
        int choice = Integer.parseInt(scanner.nextLine());

        // Validate product number
        if (choice < 1 || choice > products.size()) {
            System.out.println("❌ Invalid product number.");
            return;
        }

        // Remove the selected product from the list
        Product removed = products.remove(choice - 1);

        // Save the updated product list to the file
        fileHandler.writeFile(products);
        System.out.println("✅ " + removed.getName() + " has been removed from the inventory.");

    } catch (NumberFormatException e) {
        // Handle invalid input types (non-numeric values)
        System.out.println("❌ Please enter a valid number.");
    }
}

// Method to print the menu options for the inventory system
public void printMenu() {
    System.out.println("\n===== Inventory System Menu =====");
    System.out.println("1. View All Products");
    System.out.println("2. Add Product");
    System.out.println("3. Edit Product");
    System.out.println("4. Remove Product");
    System.out.println("5. Exit");
}

// Method to print a line for separation or formatting
public void printLine() {
    System.out.println("====================================================");
}

}
