package model;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private int reorderThreshold;
    private Category category;

    public Product(int id, String name, int quantity, int reorderThreshold, Category category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.reorderThreshold = reorderThreshold;
        this.category = category;
    }

    // --- Getters and Setters ---
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getReorderThreshold() {
        return reorderThreshold;
    }

    public Category getCategory() {
        return category;
    }

    // --- Business Methods ---
    public void increaseQuantity(int amount) {
        if (amount > 0)
            this.quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        if (amount > 0 && this.quantity >= amount)
            this.quantity -= amount;
    }

    @Override
    public String toString() {
        return String.format("Product[ID=%d, Name=%s, Qty=%d, Threshold=%d, Category=%s]",
                id, name, quantity, reorderThreshold, category);
    }
}
