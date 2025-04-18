package com.inventory_management_system;

import java.io.*;
import java.util.*;

// An interface to define the methods for the FileHandler
// This allows for better organization and separation of concerns
interface FileHandlerInterface {
    /**
     * Reads the product data from the file and returns it as a list of Product objects.
     * @return List of Product objects read from the file.
     */
    List<Product> readFile();

    /**
     * Creates a new file if it does not exist.
     */
    void createFile();

    void writeFile(List<Product> products);
}

public class FileHandler implements FileHandlerInterface {
    /**
     * Reads the product data from "output.txt".
     * If the file does not exist, it creates a new file and returns an empty list.
     * Each line in the file is expected to contain product details in the format: name,stock,price.
     * @return List of Product objects read from the file.
     */
    public List<Product> readFile() {
        List<Product> products = new ArrayList<>();
        File file = new File("output.txt");
        if (!file.exists()) {
            System.out.println("⚠️ File not found! Creating a new one...");
            createFile();
            return products;
        }
        // check for errors that may ouccr when reading file
        // using BufferReader in try catch block to handle errors
        // and close the file after writing to it
        try (BufferedReader fr = new BufferedReader(new FileReader(file))) {
            String line;

            //BufferedReader is used to read the file line by line
            while ((line = fr.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String name = data[0];
                    int stock = Integer.parseInt(data[1]);
                    double price = Double.parseDouble(data[2]);
                    products.add(new Product(name, stock, price));
                }
            }
        } catch (IOException e) {
            System.out.println("Error when reading the file!!!");
        }
        return products;
    }

    /**
     * Creates a new file named "output.txt".
     * If the file already exists, it does nothing.
     * If the file creation fails, an error message is printed.
     */
    public void createFile() {
        // check for errors that may ouccr when creating a new file
        try {
            File myFile = new File("output.txt");
            // Use PrintWriter to create a new file
            // If the file already exists, it will not be created again
            PrintWriter writer = new PrintWriter(new FileWriter(myFile));
            writer.close();
            System.out.println("✅ New file created: output.txt");
        } catch (IOException e) {
            System.out.println("⚠️ Failed to create file: " + e.getMessage());
        }
    }

    /**
     * Writes the list of Product objects to "output.txt".
     * Each product is written in the format: name,stock,price.
     * If the file writing fails, an error message is printed.
     * @param products List of Product objects to be written to the file.
     */
    public void writeFile(List<Product> products) {
        File myFile = new File("output.txt");
        // check for errors that may ouccr when writing file
        // using Printer Wrtier in try catch block to handle errors
        // and close the file after writing to it
        try (PrintWriter pw = new PrintWriter(new FileWriter(myFile))) {
            for (Product p : products) {
                // use PrintWriter to write the product details to the file
                // each product is written in the format: name,stock,price
                pw.println(p.getName() + "," + p.getStock() + "," + p.getPrice());
            }
            System.out.println("✅ Inventory updated and saved to file.");
        } catch (IOException e) {
            System.out.println("⚠️ Failed to save file: " + e.getMessage());
        }
    }
}
