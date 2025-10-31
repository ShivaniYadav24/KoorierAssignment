package observer;

import model.Product;

public class ConsoleAlertService implements StockObserver {
    @Override
    public void onLowStock(Product product) {
        System.out.println(" ALERT: Low stock for " + product.getName() +
                " (Only " + product.getQuantity() + " left!)");
    }
}
