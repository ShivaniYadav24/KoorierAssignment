package service;

import model.Product;
import observer.StockObserver;
import exception.*;

public interface WarehouseService {
    void addObserver(StockObserver observer); 
    void addProduct(Product product);
    
    void receiveShipment(String productId, int quantity)
        throws ProductNotFoundException;
    
    void fulfillOrder(String productId, int quantity)
        throws ProductNotFoundException, InsufficientStockException;
    
    void showAllProducts();
}
