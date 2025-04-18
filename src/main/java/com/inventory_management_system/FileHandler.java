package com.inventory_management_system;

import java.io.*;
import java.util.*;

interface IFileHandler {
    List<Product> readFile();

    void createFile();

    void writeFile(List<Product> products);
}

public class FileHandler implements IFileHandler {
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
