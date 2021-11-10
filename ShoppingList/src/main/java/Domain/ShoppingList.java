package Domain;

import java.io.Serializable;

public class ShoppingList implements Serializable {
    private int id;
    private Product product;
    private int quantity;
    private boolean purchased;


    public ShoppingList() {
    }

    public ShoppingList(Product product, int quantity, boolean purchased) {
        this.product = product;
        this.quantity = quantity;
        this.purchased = purchased;
    }

    public ShoppingList(int id, Product product, int quantity, boolean purchased) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.purchased = purchased;
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
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", isPurchased=" + purchased +
                '}';
    }
}
