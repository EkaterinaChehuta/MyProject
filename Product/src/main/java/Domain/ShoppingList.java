package Domain;

import java.io.Serializable;

public class ShoppingList implements Serializable {
    private int id;
    private Product product;
    private int quantity;
    private boolean isPurchased;


    public ShoppingList() {
    }

    public ShoppingList(Product product) {
        this.product = product;
    }

    public ShoppingList(int id, Product product, int quantity, boolean isPurchased) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.isPurchased = isPurchased;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isPurchased() {
        return isPurchased;
    }

    public void setPurchased(boolean purchased) {
        isPurchased = purchased;
    }
}
