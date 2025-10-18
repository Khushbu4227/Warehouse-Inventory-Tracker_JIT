package com.jit;

/*import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;*/
import java.util.*;


public class Warehouse {
// Manage All Products
	private Map<String, Product> inventory = new HashMap<>();
    private List<StockObserver> observers = new ArrayList<>();

    //  Register observer (AlertService)
    public void registerObserver(StockObserver observer) {
        if (observer != null) {
            observers.add(observer);
        } else {
            System.err.println("Cannot register a null observer!");
        }
    }

    //  Add product with validation
    public void addProduct(Product p) {
        try {
            if (p == null) {
                throw new NullPointerException("Product cannot be null while adding to warehouse!");
            }
            if (inventory.containsKey(p.getId())) {
                System.out.println("Product with ID " + p.getId() + " already exists.");
            } else {
                inventory.put(p.getId(), p);
                System.out.println("✅ Product added: " + p.getName());
            }
        } catch (Exception e) {
            System.err.println("Error while adding product: " + e.getMessage());
        }
    }

    //  Simple inventory list
    public void showAllProducts() {
        if (inventory.isEmpty()) {
            System.out.println("No products in warehouse.");
        } else {
            System.out.println("📦 Current Inventory:");
            for (Product p : inventory.values()) {
                System.out.println("   " + p);
            }
        }
    }
 //   Receive shipment (increase stock)
    public void receiveShipment(String productId, int qty) {
        try {
            if (qty <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero.");
            }
            Product p = inventory.get(productId);
            if (p == null) {
                throw new IllegalArgumentException("Invalid product ID: " + productId);
            }
            p.setQuantity(p.getQuantity() + qty);
            System.out.println("Shipment received for " + p.getName() + " (+ " + qty + ")");
        } catch (Exception e) {
            System.err.println("Error in receiveShipment: " + e.getMessage());
        }
    }

    // Fulfill customer order (decrease stock)
    public void fulfillOrder(String productId, int qty) {
        try {
            if (qty <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero.");
            }
            Product p = inventory.get(productId);
            if (p == null) {
                throw new IllegalArgumentException("Invalid product ID: " + productId);
            }
            if (p.getQuantity() < qty) {
                throw new IllegalStateException("Insufficient stock for " + p.getName());
            }

            // Decrease stock
            p.setQuantity(p.getQuantity() - qty);
            System.out.println(" Order fulfilled for " + p.getName() + " (- " + qty + ")");

            //  alert if stock below threshold
            if (p.getQuantity() < p.getThreshold()) {
                notifyLowStock(p);
            }
        } catch (Exception e) {
            System.err.println("Error in fulfillOrder: " + e.getMessage());
        }
    }
        // notify low stock method
        private void notifyLowStock(Product p) {
            for (StockObserver observer : observers) {
                observer.onLowStock(p);
            }
        }
}
