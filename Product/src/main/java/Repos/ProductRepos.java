package Repos;

import Domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepos {
    List<Product> allProduct() throws SQLException;

    void addNewProduct(Product product) throws SQLException;

    Product getProductByName(String name) throws SQLException;

    Product getProductById(int id) throws SQLException;

    void removeProduct(int id) throws SQLException;

    void updateProduct(int id, String name) throws SQLException;

    void updateProduct(int productId, int indicatorId) throws SQLException;
}
