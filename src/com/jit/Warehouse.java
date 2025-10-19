package com.jit;

import java.util.*;
import java.io.*;

public class Warehouse {
    private String warehouseName;
    private Map<String, Product> inventory = new HashMap<>();
    private List<StockObserver> observers = new ArrayList<>();
   // constructor for new warehouse
    public Warehouse(String name) {
        this.warehouseName = name;
        System.out.println("Warehouse created: " + warehouseName);
    }

    //Register Observer
    public void registerObserver(StockObserver observer) {
        if (observer == null) {
            System.err.println("Cannot register null observer!");
            return;
        }
        observers.add(observer);
    }

    // Add product in inventory 
    public void addProduct(Product p) {
        if (p == null) return;
        if (inventory.containsKey(p.getId())) {
            System.out.println("⚠️ Product already exists.");
        } else {
            inventory.put(p.getId(), p);
            System.out.println("✅ Product added in " + warehouseName + ": " + p.getName());
        }
    }

    // Show all products 
    public void showAllProducts() {
        System.out.println("\n Inventory of " + warehouseName + ":");
        if (inventory.isEmpty()) {
            System.out.println("   (No products yet)");
        } else {
            for (Product p : inventory.values()) {
                System.out.println("   " + p);
            }
        }
    }

    // Receive shipment
    public void receiveShipment(String productId, int qty) {
        try {
            if (qty <= 0) throw new IllegalArgumentException("Quantity must be > 0");
            Product p = inventory.get(productId);
            if (p == null) throw new IllegalArgumentException("Invalid Product ID: " + productId);
            p.setQuantity(p.getQuantity() + qty);
            System.out.println(" Shipment received for " + p.getName() + " (+ " + qty + ")");
        } catch (Exception e) {
            System.err.println("Error in receiveShipment: " + e.getMessage());
        }
    }

    // Fulfill Order 
    public void fulfillOrder(String productId, int qty) {
        try {
            if (qty <= 0) throw new IllegalArgumentException("Quantity must be > 0");
            
            Product p = inventory.get(productId);
            if (p == null) throw new IllegalArgumentException("Invalid Product ID: " + productId);
            if (p.getQuantity() < qty) throw new IllegalStateException("Insufficient stock for " + p.getName());

            p.setQuantity(p.getQuantity() - qty);
            System.out.println(" Order fulfilled for " + p.getName() + " (- " + qty + ")");

            if (p.getQuantity() < p.getThreshold()) {
                notifyLowStock(p);
            }
        } catch (Exception e) {
            System.err.println("Error in fulfillOrder: " + e.getMessage());
        }
    }

    // Notify low stock
    private void notifyLowStock(Product p) {
        for (StockObserver observer : observers) {
            observer.onLowStock(p);
        }
    }

    //  Save inventory to file
    public void saveInventoryToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Product p : inventory.values()) {
                writer.write(p.getId() + "," + p.getName() + "," + p.getQuantity() + "," + p.getThreshold());
                writer.newLine();
            }
            System.out.println(" Inventory saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving inventory: " + e.getMessage());
        }
    }

    //  Load inventory from file
    public void loadInventoryFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            inventory.clear(); // remove old data
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Product p = new Product(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                    inventory.put(p.getId(), p);
                }
            }
            System.out.println(" Inventory loaded from file: " + filename);
        } catch (IOException e) {
            System.err.println("Error loading inventory: " + e.getMessage());
        }
    }

    public String getWarehouseName() {
        return warehouseName;
    }
}
