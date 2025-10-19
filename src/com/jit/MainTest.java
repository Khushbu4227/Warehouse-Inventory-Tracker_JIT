package com.jit;


public class MainTest {
    public static void main(String[] args) {
        AlertService alert = new AlertService();

        //  Create multiple warehouses
        Warehouse warehouse1 = new Warehouse("Indore");
        Warehouse warehouse2 = new Warehouse("Pune");

        warehouse1.registerObserver(alert);
        warehouse2.registerObserver(alert);

        // Load data from files (if exists)
        warehouse1.loadInventoryFromFile("warehouse1.txt");
        warehouse2.loadInventoryFromFile("warehouse2.txt");

        // Add products
        Product laptop = new Product("P01", "Laptop", 10, 5);
        Product mobile = new Product("P02", "Mobile", 5, 2);

        warehouse1.addProduct(laptop);
        warehouse2.addProduct(mobile);

        // Shipments
        warehouse1.receiveShipment("P01", 5);
        warehouse2.receiveShipment("P02", 2);

        // Orders
        warehouse1.fulfillOrder("P01", 12); // trigger alert
        warehouse2.fulfillOrder("P02", 5);  // trigger alert

        // Show products
        warehouse1.showAllProducts();
        warehouse2.showAllProducts();

        // Save to file
        warehouse1.saveInventoryToFile("warehouse1.txt");
        warehouse2.saveInventoryToFile("warehouse2.txt");

        System.out.println("\n  All operations completed successfully!");
    }
}
