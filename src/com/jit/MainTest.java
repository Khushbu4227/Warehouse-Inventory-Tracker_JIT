package com.jit;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AlertService alert = new AlertService();

        Warehouse warehouse1 = new Warehouse("Indore");
        Warehouse warehouse2 = new Warehouse("Pune");

        warehouse1.registerObserver(alert);
        warehouse2.registerObserver(alert);

        warehouse1.loadInventoryFromFile("warehouse1.txt");
        warehouse2.loadInventoryFromFile("warehouse2.txt");

        System.out.println("\n WAREHOUSE INVENTORY TRACKER MENU ");

        boolean running = true;
        while (running) {
            System.out.println("\n1. Add Product");
            System.out.println("2. Receive Shipment");
            System.out.println("3. Fulfill Order");
            System.out.println("4. Show Inventory");
            System.out.println("5. Save & Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            Warehouse current;
            System.out.print("Select Warehouse :-    1 = Indore, 2 = Pune:  ");
            int wChoice = sc.nextInt();
            sc.nextLine();
            current = (wChoice == 1) ? warehouse1 : warehouse2;

            switch (choice) {
            // case 1 for add product
                case 1:
                    System.out.print("Enter Product ID: ");
                    String id = sc.nextLine().trim();
                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Enter Threshold: ");
                    int th = sc.nextInt();
                    sc.nextLine();
                    current.addProduct(new Product(id, name, qty, th));
                    break;
            //case 2 for Recieve Shipment
                case 2:
                    System.out.print("Enter Product ID to receive shipment: ");
                    String shipId = sc.nextLine().trim();
                    System.out.print("Enter Quantity received: ");
                    int sQty = sc.nextInt();
                    sc.nextLine();
                    current.receiveShipment(shipId, sQty);
                    break;
            // fulfil oreder
                case 3:
                    System.out.print("Enter Product ID to fulfill order: ");
                    String orderId = sc.nextLine().trim();
                    System.out.print("Enter Quantity ordered: ");
                    int oQty = sc.nextInt();
                    sc.nextLine();
                    current.fulfillOrder(orderId, oQty);
                    break;
                // show  all products
                case 4:
                    warehouse1.showAllProducts();
                    warehouse2.showAllProducts();
                    break;
                 // Save Product in files
                case 5:
                    warehouse1.saveInventoryToFile("warehouse1.txt");
                    warehouse2.saveInventoryToFile("warehouse2.txt");
                    System.out.println(" Inventory saved. Exiting...");
                    running = false;
                    break;

                default:
                    System.out.println(" Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}
