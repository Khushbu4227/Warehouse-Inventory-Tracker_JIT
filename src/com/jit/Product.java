	package com.jit;
	//Store the Product Information
	public class Product {
		// Data Encapsulated with Private variables
	    private String id;
	    private String name;
	    private int quantity;
	    private int threshold;

	    //  Constructor
	    public Product(String id, String name, int quantity, int threshold) {
	        if (id == null || id.trim().isEmpty()) {
	            throw new IllegalArgumentException("Product ID cannot be empty.");
	        }
	        if (name == null || name.trim().isEmpty()) {
	            throw new IllegalArgumentException("Product name cannot be empty.");
	        }
	        if (quantity < 0) {
	            throw new IllegalArgumentException("Quantity cannot be negative.");
	        }
	        if (threshold < 0) {
	            throw new IllegalArgumentException("Threshold cannot be negative.");
	        }

	        this.id = id;
	        this.name = name;
	        this.quantity = quantity;
	        this.threshold = threshold;
	    }

	    // Getters
	    public String getId() { return id; }
	    public String getName() { return name; }
	    public int getQuantity() { return quantity; }
	    public int getThreshold() { return threshold; }

	    //  Quantity Update with Error Handling
	    public void setQuantity(int newQty) {
	        if (newQty < 0) {
	            throw new IllegalArgumentException("Quantity cannot be negative.");
	        }
	        this.quantity = newQty;
	    }

	   // override toSteing method
	    public String toString() {
	        return "Product[ID=" + id + ", Name=" + name + ", Quantity=" + quantity +
	                ", Threshold=" + threshold + "]";
	    }
	}


