package com.example.testEightList.Repos;

import com.example.testEightList.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepos {
    List<Product> allProduct() throws SQLException;
    void addNewProduct(Product product) throws SQLException;
    Product getProductByName(String name) throws SQLException;
}
