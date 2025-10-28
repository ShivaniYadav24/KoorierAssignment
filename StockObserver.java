package observer;

import model.Product;

public interface StockObserver {
    void onLowStock(Product product);
}
