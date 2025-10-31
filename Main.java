import model.Product;
import service.*;
import exception.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MultiWarehouseManager manager = new MultiWarehouseManager();
        boolean running = true;

        while (running) {
            System.out.println("\n====== MENU ======");
            System.out.println("1. Add Warehouse");
            System.out.println("2. List Warehouses");
            System.out.println("3. Select Warehouse");
            System.out.println("4. Add Product");
            System.out.println("5. Receive Shipment");
            System.out.println("6. Fulfill Order");
            System.out.println("7. Show Inventory");
            System.out.println("8. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter warehouse name: ");
                    String name = sc.nextLine();
                    manager.addWarehouse(name);
                }
                case 2 -> manager.listWarehouses();
                case 3 -> {
                    System.out.print("Enter warehouse name to select: ");
                    String name = sc.nextLine();
                    manager.selectWarehouse(name);
                }
                case 4 -> {
                    WarehouseService warehouse = manager.getCurrentWarehouse();
                    if (warehouse == null) {
                        System.out.println("Please select a warehouse first!");
                        break;
                    }
                    System.out.print("Enter Product ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Category: ");
                    String cat = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Enter Threshold: ");
                    int thr = sc.nextInt();

                    warehouse.addProduct(new Product(id, name, cat, qty, thr));
                }
                case 5 -> {
                    WarehouseService warehouse = manager.getCurrentWarehouse();
                    if (warehouse == null) {
                        System.out.println("Please select a warehouse first!");
                        break;
                    }
                    System.out.print("Enter Product ID: ");
                    String pid = sc.nextLine();
                    System.out.print("Enter Quantity to Add: ");
                    int add = sc.nextInt();
                    try {
                        warehouse.receiveShipment(pid, add);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 6 -> {
                    WarehouseService warehouse = manager.getCurrentWarehouse();
                    if (warehouse == null) {
                        System.out.println("Please select a warehouse first!");
                        break;
                    }
                    System.out.print("Enter Product ID: ");
                    String pid = sc.nextLine();
                    System.out.print("Enter Order Quantity: ");
                    int qty = sc.nextInt();
                    try {
                        warehouse.fulfillOrder(pid, qty);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 7 -> {
                    WarehouseService warehouse = manager.getCurrentWarehouse();
                    if (warehouse == null) {
                        System.out.println("Please select a warehouse first!");
                        break;
                    }
                    warehouse.showAllProducts();
                }
                case 8 -> {
                    System.out.println("Exiting system...");
                    running = false;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
        sc.close();
    }
}
