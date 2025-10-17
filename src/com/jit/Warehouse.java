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
}
