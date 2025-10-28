import model.*;
import observer.*;
import service.*;
import exception.*;

public class Main {
    public static void main(String[] args) {

        // --- Step 1: Create Warehouse instance ---
        WarehouseService warehouse = new WarehouseImpl();

        // --- Step 2: Register observer (alert system) ---
        warehouse.registerObserver(new ConsoleAlertService());

        // --- Step 3: Add Products ---
        Product laptop = new Product(101, "Laptop", 0, 5, Category.ELECTRONICS);
        Product chair = new Product(102, "Chair", 10, 3, Category.FURNITURE);

        warehouse.addProduct(laptop);
        warehouse.addProduct(chair);

        try {
            // --- Step 4: Receive Shipment ---
            warehouse.receiveShipment(101, 10);  // Laptop now has 10
            warehouse.receiveShipment(102, 5);   // Chair now has 15

            // --- Step 5: Fulfill Orders ---
            warehouse.fulfillOrder(101, 6);  // Laptop left = 4 → triggers alert
            warehouse.fulfillOrder(102, 13); // Chair left = 2 → triggers alert

        } catch (ProductNotFoundException | InsufficientStockException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
}
