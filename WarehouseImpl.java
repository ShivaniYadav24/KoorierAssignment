package service;

import exception.*;
import model.Product;
import observer.StockObserver;
import java.util.*;

public class WarehouseImpl implements WarehouseService {

    private Map<Integer, Product> products = new HashMap<>();
    private List<StockObserver> observers = new ArrayList<>();

    @Override
    public void addProduct(Product product) {
        if (products.containsKey(product.getId())) {
            System.out.println("Product ID already exists: " + product.getId());
        } else {
            products.put(product.getId(), product);
            System.out.println("✅ Added: " + product.getName());
        }
    }

    @Override
    public void receiveShipment(int productId, int amount) throws ProductNotFoundException {
        Product p = products.get(productId);
        if (p == null) throw new ProductNotFoundException("Product not found with ID: " + productId);
        p.increaseQuantity(amount);
        System.out.println("📦 Shipment received: +" + amount + " → " + p.getName() + " (Now: " + p.getQuantity() + ")");
    }

    @Override
    public void fulfillOrder(int productId, int amount)
            throws ProductNotFoundException, InsufficientStockException {
        Product p = products.get(productId);
        if (p == null) throw new ProductNotFoundException("Product not found with ID: " + productId);

        if (p.getQuantity() < amount)
            throw new InsufficientStockException("Insufficient stock for " + p.getName());

        p.decreaseQuantity(amount);
        System.out.println("🛒 Order fulfilled: -" + amount + " → " + p.getName() + " (Remaining: " + p.getQuantity() + ")");

        // Trigger alert if below threshold
        if (p.getQuantity() < p.getReorderThreshold()) {
            notifyObservers(p);
        }
    }

    @Override
    public Product getProductById(int productId) throws ProductNotFoundException {
        Product p = products.get(productId);
        if (p == null) throw new ProductNotFoundException("Product not found with ID: " + productId);
        return p;
    }

    @Override
    public void registerObserver(StockObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Product product) {
        for (StockObserver observer : observers) {
            observer.onLowStock(product);
        }
    }
}
