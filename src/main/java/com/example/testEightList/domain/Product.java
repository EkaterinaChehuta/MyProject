package com.example.testEightList.domain;

public class Product {
    private int id;
    private String name;
    private String indicator;

    public Product() {
    }

    public Product(String name, String indicator) {
        this.name = name;
        this.indicator = indicator;
    }

    public Product(int id, String name, String indicator) {
        this.id = id;
        this.name = name;
        this.indicator = indicator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", indicator='" + indicator + '\'' +
                '}';
    }
}
