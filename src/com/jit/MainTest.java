package com.jit;

public class MainTest {
	public static void main(String[] args) {
		//Main class run Program
		//System.out.println("Wharehouse Inventory Tracker Started....!");
		try {
            Warehouse warehouse = new Warehouse();
            AlertService alert = new AlertService();
            warehouse.registerObserver(alert);

            Product p1 = new Product("P01", "Laptop", 10, 5);
            warehouse.addProduct(p1);

            warehouse.showAllProducts();

            //  low stock (testing observer)
            p1.setQuantity(3);
            alert.onLowStock(p1);

        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
	}

}
