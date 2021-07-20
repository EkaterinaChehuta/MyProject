package com.example.testEightList.domain;

public class ShoppingList {
    private int id;
    private Product product;
    private int quantity;

    public ShoppingList() {
    }

    public ShoppingList(Product product) {
        this.product = product;
    }

    public ShoppingList(int id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
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
}
