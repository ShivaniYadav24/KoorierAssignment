package service;

import model.Product;
import observer.StockObserver;
import observer.ConsoleAlertService;
import exception.*;

import java.util.*;

public class WarehouseImpl implements WarehouseService {
    private final String name;
    private final int threshold;
    private final Map<String, Product> inventory = new HashMap<>();
    private final List<StockObserver> observers = new ArrayList<>();

    public WarehouseImpl(String name, int threshold) {
        this.name = name;
        this.threshold = threshold;
        addObserver(new ConsoleAlertService());
    }

    @Override
    public void addObserver(StockObserver observer) {
        if (observer != null) observers.add(observer);
    }

    @Override
    public void addProduct(Product product) {
        if (inventory.containsKey(product.getId())) {
            System.out.println(" Product already exists in " + name);
            return;
        }
        inventory.put(product.getId(), product);
        System.out.println("Added " + product.getName() + " to " + name);
    }

    @Override
    public void receiveShipment(String productId, int qty) throws ProductNotFoundException {
        Product p = inventory.get(productId);
        if (p == null) throw new ProductNotFoundException("Product not found: " + productId);
        p.setQuantity(p.getQuantity() + qty);
        System.out.println("Shipment received for " + p.getName() + " in " + name + ". New stock: " + p.getQuantity());
    }

    @Override
    public void fulfillOrder(String productId, int qty)
            throws ProductNotFoundException, InsufficientStockException {
        Product p = inventory.get(productId);
        if (p == null) throw new ProductNotFoundException("Product not found: " + productId);
        if (p.getQuantity() < qty) throw new InsufficientStockException("Insufficient stock for " + p.getName());
        p.setQuantity(p.getQuantity() - qty);
        System.out.println("Order fulfilled for " + p.getName() + " in " + name + ". Remaining: " + p.getQuantity());
        if (p.getQuantity() < threshold) notifyObservers(p);
    }

    @Override
    public void showAllProducts() {
        if (inventory.isEmpty()) {
            System.out.println(" No products in " + name);
            return;
        }
        System.out.println("Inventory of " + name + ":");
        for (Product p : inventory.values()) {
            System.out.println("  → " + p);
        }
    }

    private void notifyObservers(Product p) {
        for (StockObserver obs : observers) {
            obs.onLowStock(p);
        }
    }

    public String getName() {
        return name;
    }
}
