package service;

import exception.*;
import model.Product;
import observer.StockObserver;

public interface WarehouseService {
    void addProduct(Product product);
    void receiveShipment(int productId, int amount) throws ProductNotFoundException;
    void fulfillOrder(int productId, int amount) throws ProductNotFoundException, InsufficientStockException;
    Product getProductById(int productId) throws ProductNotFoundException;
    void registerObserver(StockObserver observer);
}
