package com.jit;

public class MainTest {
	public static void main(String[] args) {
		//Main class run Program
		//System.out.println("Warehouse Inventory Tracker Started....!");
		try {
            Warehouse warehouse = new Warehouse();
            AlertService alert = new AlertService();
            warehouse.registerObserver(alert);

            Product laptop = new Product("P01", "Laptop", 10, 5);
            Product mobile = new Product("P02", "Mobile", 5, 2);

            warehouse.addProduct(laptop);
            warehouse.addProduct(mobile);

            warehouse.showAllProducts();

            //  low stock (testing observer)
           // p1.setQuantity(3);
            //alert.onLowStock(p1);
            // Receive shipments
            warehouse.receiveShipment("P01", 5); // stock = 15
            warehouse.receiveShipment("P02", 3); // stock = 8

            // Fulfill orders (low stock alerts)
            warehouse.fulfillOrder("P01", 12); // Laptop => 3 left (Alert)
            warehouse.fulfillOrder("P02", 7);  // Mobile => 1 left (Alert)

            // Test invalid cases
            warehouse.fulfillOrder("P03", 2);  // invalid ID
            warehouse.receiveShipment("P02", -5); // negative quantity


        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
	}

}
