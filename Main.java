import service.*;
import model.*;
import exception.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MultiWarehouseManager manager = new MultiWarehouseManager();

        while (true) {
            System.out.println("\n===== Warehouse Inventory Tracker =====");
            System.out.println("1. Create Warehouse");
            System.out.println("2. Add Product");
            System.out.println("3. Receive Shipment");
            System.out.println("4. Fulfill Order");
            System.out.println("5. Show Inventory");
            System.out.println("6. Show All Warehouses");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(sc.nextLine());
            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Warehouse Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Warehouse Threshold: ");
                        int threshold = Integer.parseInt(sc.nextLine());
                        manager.createWarehouse(name, threshold);
                    }
                    case 2 -> {
                        System.out.print("Enter Warehouse Name: ");
                        String whName = sc.nextLine();
                        WarehouseImpl warehouse = manager.getWarehouse(whName);
                        if (warehouse == null) {
                            System.out.println(" Warehouse not found.");
                            break;
                        }
                        System.out.print("Enter Product ID: ");
                        String id = sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Category: ");
                        String category = sc.nextLine();
                        System.out.print("Enter Quantity: ");
                        int qty = Integer.parseInt(sc.nextLine());
                        warehouse.addProduct(new Product(id, name, category, qty));
                    }
                    case 3 -> {
                        System.out.print("Enter Warehouse Name: ");
                        String whName = sc.nextLine();
                        WarehouseImpl warehouse = manager.getWarehouse(whName);
                        if (warehouse == null) {
                            System.out.println(" Warehouse not found.");
                            break;
                        }
                        System.out.print("Enter Product ID: ");
                        String pid = sc.nextLine();
                        System.out.print("Enter Quantity to Add: ");
                        int qty = Integer.parseInt(sc.nextLine());
                        warehouse.receiveShipment(pid, qty);
                    }
                    case 4 -> {
                        System.out.print("Enter Warehouse Name: ");
                        String whName = sc.nextLine();
                        WarehouseImpl warehouse = manager.getWarehouse(whName);
                        if (warehouse == null) {
                            System.out.println(" Warehouse not found.");
                            break;
                        }
                        System.out.print("Enter Product ID: ");
                        String pid = sc.nextLine();
                        System.out.print("Enter Quantity to Fulfill: ");
                        int qty = Integer.parseInt(sc.nextLine());
                        warehouse.fulfillOrder(pid, qty);
                    }
                    case 5 -> {
                        System.out.print("Enter Warehouse Name: ");
                        String whName = sc.nextLine();
                        WarehouseImpl warehouse = manager.getWarehouse(whName);
                        if (warehouse == null) {
                            System.out.println(" Warehouse not found.");
                            break;
                        }
                        warehouse.showAllProducts();
                    }
                    case 6 -> manager.showAllWarehouses();
                    case 7 -> {
                        System.out.println(" Exiting. Thank you!");
                        System.exit(0);
                    }
                    default -> System.out.println(" Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
