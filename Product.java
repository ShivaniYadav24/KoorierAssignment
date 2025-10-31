package model;

public class Product {
    private String id;
    private String name;
    private String category;
    private int quantity;

    public Product(String id, String name, String category, int quantity, int threshold) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.threshold = threshold;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }
  
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
   public String toString() {
        return "Product{id='" + id + "', name='" + name + "', category='" + category + "', qty=" + quantity + "}";
    }
}
