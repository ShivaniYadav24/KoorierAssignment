package service;

import java.util.*;

public class MultiWarehouseManager {
    private final Map<String, WarehouseImpl> warehouses = new HashMap<>();

    public WarehouseImpl getWarehouse(String name) {
        return warehouses.get(name);
    }

    public void createWarehouse(String name, int threshold) {
        if (warehouses.containsKey(name)) {
            System.out.println(" Warehouse already exists: " + name);
            return;
        }
        WarehouseImpl newWarehouse = new WarehouseImpl(name, threshold);
        warehouses.put(name, newWarehouse);
        System.out.println(" Warehouse " + name + " created with threshold " + threshold);
    }

    public void showAllWarehouses() {
        if (warehouses.isEmpty()) {
            System.out.println("No warehouses created yet.");
            return;
        }
        System.out.println(" Available Warehouses:");
        for (String key : warehouses.keySet()) {
            System.out.println("  → " + key);
        }
    }
}
