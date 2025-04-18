package com.inventory_management_system;

import java.io.*;
import java.util.*;

interface IFileHandler {
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

public class FileHandler implements IFileHandler {
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
        try (BufferedReader fr = new BufferedReader(new FileReader(file))) {
            String line;
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
        try {
            File myFile = new File("output.txt");
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
        try (PrintWriter pw = new PrintWriter(new FileWriter(myFile))) {
            for (Product p : products) {
                pw.println(p.getName() + "," + p.getStock() + "," + p.getPrice());
            }
            System.out.println("✅ Inventory updated and saved to file.");
        } catch (IOException e) {
            System.out.println("⚠️ Failed to save file: " + e.getMessage());
        }
    }
}
